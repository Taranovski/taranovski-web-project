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
        modelAndView.setViewName("employeeEditInformation.jsp");
        return modelAndView;
    }

    @RequestMapping("/dontSaveEmployeePersonalInfo")
    public ModelAndView toEmployeePage(
            @ModelAttribute(value = "user") Employee employee,
            ModelAndView modelAndView) {

        if ("free".equals(employee.getStatus())) {
            modelAndView.addObject("vacancies", businessService.getAvailableVacancies(employee));
            modelAndView.addObject("offers", businessService.getOffersForEmployee(employee));
            modelAndView.addObject("bids", businessService.getBidsForEmployee(employee));
            modelAndView.addObject("skills", employeeService.getSkillList(employee));
            modelAndView.setViewName("employee.jsp");
        } else if ("hired".equals(employee.getStatus())) {
            modelAndView.addObject("checkDocument", businessService.getJobCheckDocument(employee));
            modelAndView.setViewName("employeeHired.jsp");
        }

        return modelAndView;
    }

    @RequestMapping("/saveEmployeePersonalInfo")
    public ModelAndView saveEmployeePersonalInfo(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "patronymic") String patronymic,
            @RequestParam(value = "qualification") String qualification,
            @RequestParam(value = "occupation") String occupation,
            ModelAndView modelAndView) {

        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setQualification(qualification);
        employee.setOccupation(occupation);

        employeeService.save(employee);

        modelAndView.addObject("user", employee);
        modelAndView.setViewName("employee.jsp");

        return modelAndView;
    }

    @RequestMapping("/editEmployeeSkills")
    public ModelAndView toEditSkillsPage(
            @ModelAttribute(value = "user") Employee employee,
            ModelAndView modelAndView) {

        modelAndView.addObject("skillsToAdd", employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName("employeeEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/updateExperience")
    public ModelAndView updateSkill(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "skillId") int skillId,
            @RequestParam(value = "experience") String experience,
            ModelAndView modelAndView) {

        boolean error = !validationService.experienceIsValid(experience);
        int exp = Integer.parseInt(experience);

        if (!error) {
            error = !employeeService.updateSkill(skillId, exp);
        }
        if (error) {
            modelAndView.addObject(UPDATE_ERROR, UPDATE_ERROR);
        }

        modelAndView.addObject("skills", employeeService.getSkillList(employee));
        modelAndView.addObject("skillsToAdd", employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName("employeeEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/deleteSkill")
    public ModelAndView deleteSkill(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "skillId") int skillId,
            ModelAndView modelAndView) {

        boolean error = false;

        if (!error) {
            error = !employeeService.deleteSkill(skillId);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject("skills", employeeService.getSkillList(employee));
        modelAndView.addObject("skillsToAdd", employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName("employeeEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/deleteAllSkills")
    public ModelAndView deleteAllSkills(
            @ModelAttribute(value = "user") Employee employee,
            ModelAndView modelAndView) {

        boolean error = false;

        if (!error) {
            error = !employeeService.deleteAllSkills(employee);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject("skills", employeeService.getSkillList(employee));
        modelAndView.addObject("skillsToAdd", employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName("employeeEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/addSkill")
    public ModelAndView addSkill(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "skillsToAddList") int skillId,
            @RequestParam(value = "experience") String experience,
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

        modelAndView.addObject("skills", employeeService.getSkillList(employee));
        modelAndView.addObject("skillsToAdd", employeeService.getSkillsToAddList(employee));
        modelAndView.setViewName("employeeEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/employeeAskVacancy")
    public ModelAndView employeeAskVacancy(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "vacancyId") int vacancyId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        boolean success = businessService.bidForVacancy(employee, vacancy);
        if (!success) {
            modelAndView.addObject(VACANCY_ASK_ERROR, VACANCY_ASK_ERROR);
        }

        modelAndView.addObject("bids", businessService.getBidsForEmployee(employee));
        modelAndView.setViewName("employee.jsp");
        return modelAndView;
    }

    @RequestMapping("/employeeAcceptVacancy")
    public ModelAndView employeeAcceptVacancy(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "vacancyId") int vacancyId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        OfferBid offerBid = businessService.getOffer(employee, vacancy);

        boolean success = businessService.acceptOffer(offerBid);

        if (success) {
            modelAndView.addObject("checkDocument", businessService.getJobCheckDocument(employee));
            modelAndView.setViewName("employeeHired.jsp");
        } else {
            modelAndView.addObject(ACCEPT_OFFER_ERROR, ACCEPT_OFFER_ERROR);
            modelAndView.setViewName("employee.jsp");
        }

        return modelAndView;
    }

    @RequestMapping("/employeeDeleteBid")
    public ModelAndView employeeDeleteBid(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "vacancyId") int vacancyId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        OfferBid offerBid = businessService.getBid(employee, vacancy);

        boolean success = businessService.deleteOfferBid(offerBid);

        if (!success) {
            modelAndView.addObject(DELETE_BID_ERROR, DELETE_BID_ERROR);
        }

        modelAndView.addObject("bids", businessService.getBidsForEmployee(employee));
        modelAndView.setViewName("employee.jsp");
        return modelAndView;
    }

}
