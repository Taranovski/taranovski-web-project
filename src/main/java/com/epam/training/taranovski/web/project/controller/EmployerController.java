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

    private static final String EMPLOYER_PAGE = "employer.jsp";
    private static final String EMPLOYER_VACANCY_EDIT_SKILLS_PAGE = "vacancyEditSkills.jsp";
    private static final String EMPLOYER_EDIT_INFORMATION_PAGE = "employerEditInformation.jsp";

    private static final String EMPLOYER_EDIT_VACANCY_PAGE = "employerEditVacancy.jsp";
    private static final String EMPLOYER_VACANCY_EDIT_INFORMATION_PAGE = "vacancyEditInformation.jsp";
    private static final String EMPLOYER_ADD_VACANCY_PAGE = "employerAddVacancy.jsp";
    private static final String EMPLOYER_VACANCY_CLOSED_PAGE = "employerVacancyClosed.jsp";

    private static final String PARAMETER_USER = "user";
    private static final String PARAMETER_VACANCIES = "vacancies";
    private static final String PARAMETER_BIDS = "bids";
    private static final String PARAMETER_SKILLS = "skills";
    private static final String PARAMETER_SKILLS_TO_ADD = "skillsToAdd";
    private static final String PARAMETER_SKILL_ID = "skillId";
    private static final String PARAMETER_EXPERIENCE = "experience";
    private static final String PARAMETER_VACANCY_ID = "vacancyId";
    private static final String PARAMETER_VACANCY = "vacancy";
    private static final String PARAMETER_EMPLOYEES = "employees";
    private static final String PARAMETER_OFFERS = "offers";
    private static final String PARAMETER_SKILLS_TO_ADD_LIST = "skillsToAddList";
    private static final String PARAMETER_CHECKDOCUMENTS = "checkDocuments";
    private static final String PARAMETER_COMPANY_NAME = "companyName";
    private static final String PARAMETER_FIELD = "field";
    private static final String PARAMETER_ADRESS = "adress";
    private static final String PARAMETER_TELEPHONE_NUMBER = "telephoneNumber";
    private static final String PARAMETER_POSITION = "position";
    private static final String PARAMETER_DESCRIPTION = "description";
    private static final String PARAMETER_SALARY = "salary";
    private static final String PARAMETER_VACANCY_STATUS_ACTIVE = "active";
    private static final String PARAMETER_EMPLOYEE_ID = "employeeId";
    private static final String PARAMETER_CHECKDOCUMENT = "checkDocument";

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
        modelAndView.setViewName(EMPLOYER_EDIT_INFORMATION_PAGE);
        return modelAndView;
    }

    @RequestMapping("/dontSaveEmployerInfo")
    public ModelAndView toEmployerPage(
            @ModelAttribute(value = PARAMETER_USER) Employer employer,
            ModelAndView modelAndView) {
        modelAndView.addObject(PARAMETER_VACANCIES, vacancyService.getActiveVacancyList(employer));

        modelAndView.addObject(PARAMETER_CHECKDOCUMENTS, businessService.getAllCheckDocuments(employer));
        modelAndView.setViewName(EMPLOYER_PAGE);
        return modelAndView;
    }

    @RequestMapping("/saveEmployerInfo")
    public ModelAndView saveEmployerPersonalInfo(
            @ModelAttribute(value = PARAMETER_USER) Employer employer,
            @RequestParam(value = PARAMETER_COMPANY_NAME) String companyName,
            @RequestParam(value = PARAMETER_FIELD) String field,
            @RequestParam(value = PARAMETER_ADRESS) String adress,
            @RequestParam(value = PARAMETER_TELEPHONE_NUMBER) String telephoneNumber,
            ModelAndView modelAndView) {

        if (validationService.telephoneNumberIsValid(telephoneNumber)) {
            employer.setAdress(adress);
            employer.setCompanyName(companyName);
            employer.setField(field);
            employer.setTelephoneNumber(telephoneNumber);

            employerService.save(employer);

            modelAndView.addObject(PARAMETER_USER, employer);
            modelAndView.setViewName(EMPLOYER_PAGE);
        } else {
            modelAndView.addObject(TELEPHONE_NUMBER_ERROR, TELEPHONE_NUMBER_ERROR);
            modelAndView.setViewName(EMPLOYER_EDIT_INFORMATION_PAGE);
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
            @RequestParam(value = PARAMETER_VACANCY_ID) String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(Integer.parseInt(vacancyId));

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject(PARAMETER_EMPLOYEES, businessService.getAvailableEmployees(vacancy));
        modelAndView.addObject(PARAMETER_OFFERS, businessService.getOffersForVacancy(vacancy));
        modelAndView.addObject(PARAMETER_BIDS, businessService.getBidsForVacancy(vacancy));

        modelAndView.setViewName(EMPLOYER_EDIT_VACANCY_PAGE);
        return modelAndView;
    }

    @RequestMapping("/editEmployerVacancyInfo")
    public ModelAndView editEmployervacancyInfo(
            @RequestParam(value = PARAMETER_VACANCY_ID) String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(Integer.parseInt(vacancyId));
        List<Employee> employees = businessService.getAvailableEmployees(vacancy);

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_EMPLOYEES, employees);

        modelAndView.setViewName(EMPLOYER_VACANCY_EDIT_INFORMATION_PAGE);
        return modelAndView;
    }

    @RequestMapping("/saveVacancyInfo")
    public ModelAndView saveVacancyInfo(
            @ModelAttribute(value = PARAMETER_USER) Employer employer,
            @RequestParam(value = PARAMETER_VACANCY_ID) String vacancyId,
            @RequestParam(value = PARAMETER_POSITION) String position,
            @RequestParam(value = PARAMETER_DESCRIPTION) String description,
            @RequestParam(value = PARAMETER_SALARY) String salary,
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
            modelAndView.addObject(PARAMETER_VACANCY, vacancy);
            modelAndView.addObject(PARAMETER_VACANCIES, vacancyService.getActiveVacancyList(employer));
        }

        modelAndView.setViewName(EMPLOYER_VACANCY_EDIT_INFORMATION_PAGE);
        return modelAndView;
    }

    @RequestMapping("/editEmployerVacancySkills")
    public ModelAndView editEmployerVacancySkills(
            @RequestParam(value = PARAMETER_VACANCY_ID) String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(Integer.parseInt(vacancyId));
        List<VacancySkill> list = vacancyService.getVacancySkills(vacancy);
        List<BasicSkill> skillsToAdd = vacancyService.getVacancySkillsToAdd(vacancy);

        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, skillsToAdd);
        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_SKILLS, list);
        modelAndView.setViewName(EMPLOYER_VACANCY_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/vacancyUpdateExperience")
    public ModelAndView vacancyUpdateExperience(
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            @RequestParam(value = PARAMETER_SKILL_ID) int skillId,
            @RequestParam(value = PARAMETER_EXPERIENCE) String experience,
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

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);

        modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName(EMPLOYER_VACANCY_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/vacancyDeleteSkill")
    public ModelAndView vacancyDeleteSkill(
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            @RequestParam(value = PARAMETER_SKILL_ID) int skillId,
            ModelAndView modelAndView) {

        boolean error = false;
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.deleteVacancySkill(skillId);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName(EMPLOYER_VACANCY_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/vacancyDeleteAllSkills")
    public ModelAndView vacancyDeleteAllSkills(
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            ModelAndView modelAndView) {

        boolean error = false;
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.deleteAllVacancySkills(vacancy);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName(EMPLOYER_VACANCY_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/vacancyAddSkill")
    public ModelAndView vacancyAddSkill(
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            @RequestParam(value = PARAMETER_SKILLS_TO_ADD_LIST) int skillId,
            @RequestParam(value = PARAMETER_EXPERIENCE) String experience,
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

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject(PARAMETER_SKILLS_TO_ADD, vacancyService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName(EMPLOYER_VACANCY_EDIT_SKILLS_PAGE);
        return modelAndView;
    }

    @RequestMapping("/deleteEmployerVacancy")
    public ModelAndView deleteEmployerVacancy(
            @ModelAttribute(value = PARAMETER_USER) Employer employer,
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        boolean success = employerService.deleteVacancy(vacancy);

        if (success) {
            modelAndView.addObject(PARAMETER_VACANCIES, vacancyService.getActiveVacancyList(employer));
        } else {
            modelAndView.addObject(VACANCY_DELETE_ERROR, VACANCY_DELETE_ERROR);
        }

        modelAndView.setViewName(EMPLOYER_PAGE);
        return modelAndView;
    }

    @RequestMapping("/toAddEmployerVacancy")
    public ModelAndView toAddEmployerVacancy(ModelAndView modelAndView) {
        modelAndView.setViewName(EMPLOYER_ADD_VACANCY_PAGE);
        return modelAndView;
    }

    @RequestMapping("/addEmployerVacancy")
    public ModelAndView addEmployerVacancy(
            @ModelAttribute(value = PARAMETER_USER) Employer employer,
            @RequestParam(value = PARAMETER_POSITION) String position,
            @RequestParam(value = PARAMETER_DESCRIPTION) String description,
            @RequestParam(value = PARAMETER_SALARY) String salary,
            ModelAndView modelAndView) {

        boolean success = validationService.salaryIsValid(salary);

        Vacancy vacancy = new Vacancy();
        if (success) {
            vacancy.setDescription(description);
            vacancy.setPosition(position);
            vacancy.setSalary(new BigDecimal(salary));
            vacancy.setEmployer(employer);
            vacancy.setStatus(PARAMETER_VACANCY_STATUS_ACTIVE);
            success = employerService.createVacancy(vacancy);
        }

        if (success) {
            modelAndView.addObject(PARAMETER_VACANCIES, vacancyService.getActiveVacancyList(employer));
            modelAndView.setViewName(EMPLOYER_PAGE);
        } else {
            modelAndView.addObject(SALARY_ERROR, SALARY_ERROR);
            modelAndView.setViewName(EMPLOYER_ADD_VACANCY_PAGE);
        }

        return modelAndView;
    }

    @RequestMapping("/employerOfferVacancy")
    public ModelAndView employerOfferVacancy(
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            @RequestParam(value = PARAMETER_EMPLOYEE_ID) int employeeId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        boolean success = businessService.offerVacancy(employee, vacancy);
        if (!success) {
            modelAndView.addObject(VACANCY_OFFER_ERROR, VACANCY_OFFER_ERROR);
        }

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject(PARAMETER_EMPLOYEES, businessService.getAvailableEmployees(vacancy));
        modelAndView.addObject(PARAMETER_OFFERS, businessService.getOffersForVacancy(vacancy));
        modelAndView.addObject(PARAMETER_BIDS, businessService.getBidsForVacancy(vacancy));

        modelAndView.setViewName(EMPLOYER_EDIT_VACANCY_PAGE);

        return modelAndView;
    }

    @RequestMapping("/employerDeleteOfferVacancy")
    public ModelAndView employerDeleteOfferVacancy(
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            @RequestParam(value = PARAMETER_EMPLOYEE_ID) int employeeId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        OfferBid offerBid = businessService.getOffer(employee, vacancy);

        boolean success = businessService.deleteOfferBid(offerBid);

        if (!success) {
            modelAndView.addObject(VACANCY_OFFER_DELETE_ERROR, VACANCY_OFFER_DELETE_ERROR);
        }

        modelAndView.addObject(PARAMETER_VACANCY, vacancy);
        modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
        modelAndView.addObject(PARAMETER_EMPLOYEES, businessService.getAvailableEmployees(vacancy));
        modelAndView.addObject(PARAMETER_OFFERS, businessService.getOffersForVacancy(vacancy));
        modelAndView.addObject(PARAMETER_BIDS, businessService.getBidsForVacancy(vacancy));

        modelAndView.setViewName(EMPLOYER_EDIT_VACANCY_PAGE);

        return modelAndView;
    }

    @RequestMapping("/employerAcceptBidVacancy")
    public ModelAndView employerAcceptBidVacancy(
            @RequestParam(value = PARAMETER_VACANCY_ID) int vacancyId,
            @RequestParam(value = PARAMETER_EMPLOYEE_ID) int employeeId,
            ModelAndView modelAndView) {

        Vacancy vacancy = vacancyService.getVacancyById(vacancyId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        OfferBid offerBid = businessService.getBid(employee, vacancy);
        boolean success = businessService.acceptBid(offerBid);

        if (success) {
            modelAndView.addObject(PARAMETER_CHECKDOCUMENT, businessService.getJobCheckDocument(employee));
            modelAndView.setViewName(EMPLOYER_VACANCY_CLOSED_PAGE);
        } else {
            modelAndView.addObject(VACANCY_ACCEPT_BID_ERROR, VACANCY_ACCEPT_BID_ERROR);

            modelAndView.addObject(PARAMETER_VACANCY, vacancy);
            modelAndView.addObject(PARAMETER_SKILLS, vacancyService.getVacancySkills(vacancy));
            modelAndView.addObject(PARAMETER_EMPLOYEES, businessService.getAvailableEmployees(vacancy));
            modelAndView.addObject(PARAMETER_OFFERS, businessService.getOffersForVacancy(vacancy));
            modelAndView.addObject(PARAMETER_BIDS, businessService.getBidsForVacancy(vacancy));

            modelAndView.setViewName(EMPLOYER_EDIT_VACANCY_PAGE);
        }

        return modelAndView;
    }
}
