/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.OfferBid;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.service.BusinessService;
import com.epam.training.taranovski.web.project.service.EmployeeService;
import com.epam.training.taranovski.web.project.service.VacancyService;
import com.epam.training.taranovski.web.project.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alyx
 */
@Controller
@SessionAttributes(value = {"user", "skills", "bids", "offers"})
public class EmployeeController {

    private static final String UPDATE_ERROR = "update_error";
    private static final String DELETE_ERROR = "delete_error";
    private static final String ADD_ERROR = "add_error";
    private static final String VACANCY_ASK_ERROR = "vacancy_ask_error";
    private static final String ACCEPT_OFFER_ERROR = "accept_offer_error";
    private static final String DELETE_BID_ERROR = "delete_bid_error";

    private static final String EMPLOYEE_PAGE = "employee.jsp";
    private static final String EMPLOYEE_HIRED_PAGE = "employeeHired.jsp";
    private static final String EMPLOYEE_EDIT_INFO_PAGE = "employeeEditInformation.jsp";
    private static final String EMPLOYEE_EDIT_SKILLS_PAGE = "employeeEditSkills.jsp";

    private static final String PARAMETER_USER = "user";
    private static final String PARAMETER_BIDS = "bids";
    private static final String PARAMETER_VACANCIES = "vacancies";
    private static final String PARAMETER_OFFERS = "offers";
    private static final String PARAMETER_SKILLS_TO_ADD_LIST = "skillsToAddList";
    private static final String PARAMETER_NAME = "name";
    private static final String PARAMETER_SURNAME = "surname";
    private static final String PARAMETER_PATRONYMIC = "patronymic";
    private static final String PARAMETER_QUALIFICATION = "qualification";
    private static final String PARAMETER_OCCUPATION = "occupation";
    private static final String PARAMETER_SKILLS = "skills";
    private static final String PARAMETER_CHECKDOCUMENT = "checkDocument";
    private static final String PARAMETER_SKILLS_TO_ADD = "skillsToAdd";
    private static final String PARAMETER_SKILL_ID = "skillId";
    private static final String PARAMETER_EXPERIENCE = "experience";
    private static final String PARAMETER_VACANCY_ID = "vacancyId";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private VacancyService vacancyService;

    @RequestMapping("/editEmployeePersonalInfo")
    public ModelAndView editEmployeePersonalInfo(ModelAndView modelAndView) {
        modelAndView.setViewName(EMPLOYEE_EDIT_INFO_PAGE);
        return modelAndView;
    }

    @RequestMapping("/dontSaveEmployeePersonalInfo")
    public ModelAndView toEmployeePage(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            ModelAndView modelAndView) {

        if ("free".equals(employee.getStatus())) {
            modelAndView.addObject(PARAMETER_VACANCIES, businessService.getAvailableVacancies(employee));
            modelAndView.addObject(PARAMETER_OFFERS, businessService.getOffersForEmployee(employee));
            modelAndView.addObject(PARAMETER_BIDS, businessService.getBidsForEmployee(employee));
            modelAndView.addObject(PARAMETER_SKILLS, employeeService.getSkillList(employee));
            modelAndView.setViewName(EMPLOYEE_PAGE);
        } else if ("hired".equals(employee.getStatus())) {
            modelAndView.addObject(PARAMETER_CHECKDOCUMENT, businessService.getJobCheckDocument(employee));
            modelAndView.setViewName(EMPLOYEE_HIRED_PAGE);
        }

        return modelAndView;
    }

    @RequestMapping("/saveEmployeePersonalInfo")
    public ModelAndView saveEmployeePersonalInfo(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            @RequestParam(value = PARAMETER_NAME) String name,
            @RequestParam(value = PARAMETER_SURNAME) String surname,
            @RequestParam(value = PARAMETER_PATRONYMIC) String patronymic,
            @RequestParam(value = PARAMETER_QUALIFICATION) String qualification,
            @RequestParam(value = PARAMETER_OCCUPATION) String occupation,
            ModelAndView modelAndView) {

        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setQualification(qualification);
        employee.setOccupation(occupation);

        employeeService.save(employee);

        modelAndView.addObject(PARAMETER_USER, employee);
        modelAndView.setViewName(EMPLOYEE_PAGE);

        return modelAndView;
    }

    @RequestMapping("/editEmployeeSkills")
    public ModelAndView toEditSkillsPage(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            ModelAndView modelAndView) {

        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName(EMPLOYEE_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/updateExperience")
    public ModelAndView updateSkill(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            @RequestParam(value = PARAMETER_SKILL_ID) int skillId,
            @RequestParam(value = PARAMETER_EXPERIENCE) String experience,
            ModelAndView modelAndView) {

        boolean error = !validationService.experienceIsValid(experience);
        int exp = Integer.parseInt(experience);

        if (!error) {
            error = !employeeService.updateSkill(skillId, exp);
        }
        if (error) {
            modelAndView.addObject(UPDATE_ERROR, UPDATE_ERROR);
        }

        modelAndView.addObject(PARAMETER_SKILLS, employeeService.getSkillList(employee));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName(EMPLOYEE_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/deleteSkill")
    public ModelAndView deleteSkill(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            @RequestParam(value = PARAMETER_SKILL_ID) int skillId,
            ModelAndView modelAndView) {

        boolean error = false;

        if (!error) {
            error = !employeeService.deleteSkill(skillId);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject(PARAMETER_SKILLS, employeeService.getSkillList(employee));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName(EMPLOYEE_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/deleteAllSkills")
    public ModelAndView deleteAllSkills(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            ModelAndView modelAndView) {

        boolean error = false;

        if (!error) {
            error = !employeeService.deleteAllSkills(employee);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject(PARAMETER_SKILLS, employeeService.getSkillList(employee));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName(EMPLOYEE_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/addSkill")
    public ModelAndView addSkill(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            @RequestParam(value = PARAMETER_SKILLS_TO_ADD_LIST) int skillId,
            @RequestParam(value = PARAMETER_EXPERIENCE) String experience,
            ModelAndView modelAndView) {

        boolean error = !validationService.experienceIsValid(experience);

        int skill = skillId;
        int exp = Integer.parseInt(experience);

        if (!error) {
            error = !employeeService.addSkill(employee, skill, exp);
        }
        if (error) {
            modelAndView.addObject(ADD_ERROR, ADD_ERROR);
        }

        modelAndView.addObject(PARAMETER_SKILLS, employeeService.getSkillList(employee));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName(EMPLOYEE_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/employeeAskVacancy")
    public ModelAndView employeeAskVacancy(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        boolean success = businessService.bidForVacancy(employee, vacancy);
        if (!success) {
            modelAndView.addObject(VACANCY_ASK_ERROR, VACANCY_ASK_ERROR);
        }

        modelAndView.addObject(PARAMETER_BIDS, businessService.getBidsForEmployee(employee));
        modelAndView.setViewName(EMPLOYEE_PAGE);
        return modelAndView;
    }

    @RequestMapping("/employeeAcceptVacancy")
    public ModelAndView employeeAcceptVacancy(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        OfferBid offerBid = businessService.getOffer(employee, vacancy);

        boolean success = businessService.acceptOffer(offerBid);

        if (success) {
            modelAndView.addObject(PARAMETER_CHECKDOCUMENT, businessService.getJobCheckDocument(employee));
            modelAndView.setViewName(EMPLOYEE_HIRED_PAGE);
        } else {
            modelAndView.addObject(ACCEPT_OFFER_ERROR, ACCEPT_OFFER_ERROR);
            modelAndView.setViewName(EMPLOYEE_PAGE);
        }

        return modelAndView;
    }

    @RequestMapping("/employeeDeleteBid")
    public ModelAndView employeeDeleteBid(
            @ModelAttribute(value = PARAMETER_USER) Employee employee,
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        OfferBid offerBid = businessService.getBid(employee, vacancy);

        boolean success = businessService.deleteOfferBid(offerBid);

        if (!success) {
            modelAndView.addObject(DELETE_BID_ERROR, DELETE_BID_ERROR);
        }

        modelAndView.addObject(PARAMETER_BIDS, businessService.getBidsForEmployee(employee));
        modelAndView.setViewName(EMPLOYEE_PAGE);
        return modelAndView;
    }

}
