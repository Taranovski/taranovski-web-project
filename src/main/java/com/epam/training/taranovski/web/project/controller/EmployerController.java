/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.BasicSkill;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.OfferBid;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.service.BusinessService;
import com.epam.training.taranovski.web.project.service.EmployeeService;
import com.epam.training.taranovski.web.project.service.EmployerService;
import com.epam.training.taranovski.web.project.service.VacancyService;
import com.epam.training.taranovski.web.project.service.ValidationService;
import java.math.BigDecimal;
import java.util.List;
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
@SessionAttributes(value = {"user", "vacancies", "checkDocuments"})
public class EmployerController {

    private static final String TELEPHONE_NUMBER_ERROR = "tel_error";
    private static final String SALARY_ERROR = "salary_error";
    private static final String UPDATE_ERROR = "update_error";
    private static final String DELETE_ERROR = "delete_error";
    private static final String ADD_ERROR = "add_error";
    private static final String VACANCY_DELETE_ERROR = "vacancy_delete_error";
    private static final String SAVE_SUCCESS = "save_success";

    private static final String VACANCY_OFFER_ERROR = "vacancy_offer_error";
    private static final String VACANCY_OFFER_DELETE_ERROR = "vacancy_delete_error";
    private static final String VACANCY_ACCEPT_BID_ERROR = "vacancy_accept_error";

    @Autowired
    private EmployerService employerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private VacancyService vacancyService;
    

    @RequestMapping("/editEmployerInfo")
    public ModelAndView editEmployerPersonalInfo(ModelAndView modelAndView) {
        modelAndView.setViewName("employerEditInformation.jsp");
        return modelAndView;
    }

    @RequestMapping("/dontSaveEmployerInfo")
    public ModelAndView toEmployerPage(
            @ModelAttribute(value = "user") Employer employer,
            ModelAndView modelAndView) {
        modelAndView.addObject("vacancies", vacancyService.getActiveVacancyList(employer));
        
        modelAndView.addObject("checkDocuments", businessService.getAllCheckDocuments(employer));
        modelAndView.setViewName("employer.jsp");
        return modelAndView;
    }

    @RequestMapping("/saveEmployerInfo")
    public ModelAndView saveEmployerPersonalInfo(
            @ModelAttribute(value = "user") Employer employer,
            @RequestParam(value = "companyName") String companyName,
            @RequestParam(value = "field") String field,
            @RequestParam(value = "adress") String adress,
            @RequestParam(value = "telephoneNumber") String telephoneNumber,
            ModelAndView modelAndView) {

        if (validationService.telephoneNumberIsValid(telephoneNumber)) {
            employer.setAdress(adress);
            employer.setCompanyName(companyName);
            employer.setField(field);
            employer.setTelephoneNumber(telephoneNumber);

            employerService.save(employer);

            modelAndView.addObject("user", employer);
            modelAndView.setViewName("employer.jsp");
        } else {
            modelAndView.addObject(TELEPHONE_NUMBER_ERROR, TELEPHONE_NUMBER_ERROR);
            modelAndView.setViewName("employerEditInformation.jsp");
        }

        return modelAndView;
    }

    /**
     *
     * @param vacancyId
     * @param modelAndView
     * @return
     */
    @RequestMapping("/editEmployerVacancy")
    public ModelAndView editEmployerVacancy(
            @RequestParam(value = "vacancyId") String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(Integer.parseInt(vacancyId));

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject("employees", businessService.getAvailableEmployees(vacancy));
        modelAndView.addObject("offers", businessService.getOffersForVacancy(vacancy));
        modelAndView.addObject("bids", businessService.getBidsForVacancy(vacancy));

        modelAndView.setViewName("employerEditVacancy.jsp");
        return modelAndView;
    }

    @RequestMapping("/editEmployerVacancyInfo")
    public ModelAndView editEmployervacancyInfo(
            @RequestParam(value = "vacancyId") String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(Integer.parseInt(vacancyId));
        List<Employee> employees = businessService.getAvailableEmployees(vacancy);

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("employees", employees);

        modelAndView.setViewName("vacancyEditInformation.jsp");
        return modelAndView;
    }

    @RequestMapping("/saveVacancyInfo")
    public ModelAndView saveVacancyInfo(
            @ModelAttribute(value = "user") Employer employer,
            @RequestParam(value = "vacancyId") String vacancyId,
            @RequestParam(value = "position") String position,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "salary") String salary,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(Integer.parseInt(vacancyId));

        boolean success = validationService.salaryIsValid(salary);

        if (success) {
            vacancy.setPosition(position);
            vacancy.setDescription(description);
            vacancy.setSalary(new BigDecimal(salary));

            success = employerService.updateVacancyInformation(vacancy);
        }

        if (!success) {
            modelAndView.addObject(SALARY_ERROR, SALARY_ERROR);
        } else {
            modelAndView.addObject(SAVE_SUCCESS, SAVE_SUCCESS);
            modelAndView.addObject("vacancy", vacancy);
            modelAndView.addObject("vacancies", vacancyService.getActiveVacancyList(employer));
        }

        modelAndView.setViewName("vacancyEditInformation.jsp");
        return modelAndView;
    }

    @RequestMapping("/editEmployerVacancySkills")
    public ModelAndView editEmployerVacancySkills(
            @RequestParam(value = "vacancyId") String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(Integer.parseInt(vacancyId));
        List<VacancySkill> list = vacancyService.getVacancySkills(vacancy);
        List<BasicSkill> skillsToAdd = vacancyService.getVacancySkillsToAdd(vacancy);

        modelAndView.addObject("skillsToAdd", skillsToAdd);
        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", list);
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/vacancyUpdateExperience")
    public ModelAndView vacancyUpdateExperience(
            @RequestParam(value = "vacancyId") int vacancyId,
            @RequestParam(value = "skillId") int skillId,
            @RequestParam(value = "experience") String experience,
            ModelAndView modelAndView) {

        boolean error = !validationService.experienceIsValid(experience);
        int exp = Integer.parseInt(experience);
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.updateVacancySkill(skillId, exp);
        }
        if (error) {
            modelAndView.addObject(UPDATE_ERROR, UPDATE_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);

        modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/vacancyDeleteSkill")
    public ModelAndView vacancyDeleteSkill(
            @RequestParam(value = "vacancyId") int vacancyId,
            @RequestParam(value = "skillId") int skillId,
            ModelAndView modelAndView) {

        boolean error = false;
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.deleteVacancySkill(skillId);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/vacancyDeleteAllSkills")
    public ModelAndView vacancyDeleteAllSkills(
            @RequestParam(value = "vacancyId") int vacancyId,
            ModelAndView modelAndView) {

        boolean error = false;
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.deleteAllVacancySkills(vacancy);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/vacancyAddSkill")
    public ModelAndView vacancyAddSkill(
            @RequestParam(value = "vacancyId") int vacancyId,
            @RequestParam(value = "skillsToAddList") int skillId,
            @RequestParam(value = "experience") String experience,
            ModelAndView modelAndView) {

        boolean error = !validationService.experienceIsValid(experience);

        int skill = skillId;
        int exp = Integer.parseInt(experience);
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.addSkill(vacancy, skill, exp);
        }
        if (error) {
            modelAndView.addObject(ADD_ERROR, ADD_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/deleteEmployerVacancy")
    public ModelAndView deleteEmployerVacancy(
            @ModelAttribute(value = "user") Employer employer,
            @RequestParam(value = "vacancyId") int vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        boolean success = employerService.deleteVacancy(vacancy);
        
        if (success) {
            modelAndView.addObject("vacancies", vacancyService.getActiveVacancyList(employer));
        } else {
            modelAndView.addObject(VACANCY_DELETE_ERROR, VACANCY_DELETE_ERROR);
        }

        modelAndView.setViewName("employer.jsp");
        return modelAndView;
    }

    @RequestMapping("/toAddEmployerVacancy")
    public ModelAndView toAddEmployerVacancy(ModelAndView modelAndView) {
        modelAndView.setViewName("employerAddVacancy.jsp");
        return modelAndView;
    }

    @RequestMapping("/addEmployerVacancy")
    public ModelAndView addEmployerVacancy(
            @ModelAttribute(value = "user") Employer employer,
            @RequestParam(value = "position") String position,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "salary") String salary,
            ModelAndView modelAndView) {

        boolean success = validationService.salaryIsValid(salary);

        Vacancy vacancy = new Vacancy();
        if (success) {
            vacancy.setDescription(description);
            vacancy.setPosition(position);
            vacancy.setSalary(new BigDecimal(salary));
            vacancy.setEmployer(employer);
            vacancy.setStatus("active");
            success = employerService.createVacancy(vacancy);
        }

        if (success) {
            modelAndView.addObject("vacancies", vacancyService.getActiveVacancyList(employer));
            modelAndView.setViewName("employer.jsp");
        } else {
            modelAndView.addObject(SALARY_ERROR, SALARY_ERROR);
            modelAndView.setViewName("employerAddVacancy.jsp");
        }

        return modelAndView;
    }

    @RequestMapping("/employerOfferVacancy")
    public ModelAndView employerOfferVacancy(
            @RequestParam(value = "vacancyId") int vacancyId,
            @RequestParam(value = "employeeId") int employeeId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        boolean success = businessService.offerVacancy(employee, vacancy);
        if (!success) {
            modelAndView.addObject(VACANCY_OFFER_ERROR, VACANCY_OFFER_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject("employees", businessService.getAvailableEmployees(vacancy));
        modelAndView.addObject("offers", businessService.getOffersForVacancy(vacancy));
        modelAndView.addObject("bids", businessService.getBidsForVacancy(vacancy));

        modelAndView.setViewName("employerEditVacancy.jsp");

        return modelAndView;
    }

    @RequestMapping("/employerDeleteOfferVacancy")
    public ModelAndView employerDeleteOfferVacancy(
            @RequestParam(value = "vacancyId") int vacancyId,
            @RequestParam(value = "employeeId") int employeeId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        OfferBid offerBid = businessService.getOffer(employee, vacancy);

        boolean success = businessService.deleteOfferBid(offerBid);

        if (!success) {
            modelAndView.addObject(VACANCY_OFFER_DELETE_ERROR, VACANCY_OFFER_DELETE_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject("employees", businessService.getAvailableEmployees(vacancy));
        modelAndView.addObject("offers", businessService.getOffersForVacancy(vacancy));
        modelAndView.addObject("bids", businessService.getBidsForVacancy(vacancy));

        modelAndView.setViewName("employerEditVacancy.jsp");

        return modelAndView;
    }

    @RequestMapping("/employerAcceptBidVacancy")
    public ModelAndView employerAcceptBidVacancy(
            @RequestParam(value = "vacancyId") int vacancyId,
            @RequestParam(value = "employeeId") int employeeId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        OfferBid offerBid = businessService.getBid(employee, vacancy);
        boolean success = businessService.acceptBid(offerBid);

        if (success) {
            modelAndView.addObject("checkDocument", businessService.getJobCheckDocument(employee));
            modelAndView.setViewName("employerVacancyClosed.jsp");
        } else {
            modelAndView.addObject(VACANCY_ACCEPT_BID_ERROR, VACANCY_ACCEPT_BID_ERROR);

            modelAndView.addObject("vacancy", vacancy);
            modelAndView.addObject("skills", vacancyService.getVacancySkills(vacancy));
            modelAndView.addObject("employees", businessService.getAvailableEmployees(vacancy));
            modelAndView.addObject("offers", businessService.getOffersForVacancy(vacancy));
            modelAndView.addObject("bids", businessService.getBidsForVacancy(vacancy));

            modelAndView.setViewName("employerEditVacancy.jsp");
        }

        return modelAndView;
    }
}
