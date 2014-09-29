/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.BasicSkill;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.service.EmployerService;
import com.epam.training.taranovski.web.project.service.ValidationService;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@SessionAttributes(value = {"user", "vacancies", "vacancy"})
public class EmployerController {

    private static final String TELEPHONE_NUMBER_ERROR = "tel_error";
    private static final int TWENTY = 20;
    private static final String SALARY_ERROR = "salary_error";
    private static final String UPDATE_ERROR = "update_error";
    private static final String DELETE_ERROR = "delete_error";
    private static final String ADD_ERROR = "add_error";


    @Autowired
    private EmployerService employerService;

    @Autowired
    private ValidationService validationService;

    @RequestMapping("/editEmployerInfo")
    public ModelAndView editEmployerPersonalInfo(ModelAndView modelAndView) {
        modelAndView.setViewName("employerEditInformation.jsp");
        return modelAndView;
    }

    @RequestMapping("/dontSaveEmployerInfo")
    public ModelAndView dontSaveEmployerPersonalInfo(
            @ModelAttribute(value = "user") Employer employer,
            ModelAndView modelAndView) {
        modelAndView.addObject("vacancies", employerService.getVacancyList(employer));
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
        Vacancy vacancy = employerService.getVacancyById(Integer.parseInt(vacancyId));
        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", employerService.getVacancySkills(vacancy));
        modelAndView.setViewName("employerEditVacancy.jsp");
        return modelAndView;
    }

    @RequestMapping("/editEmployerVacancyInfo")
    public ModelAndView editEmployervacancyInfo(
            @RequestParam(value = "vacancyId") String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = employerService.getVacancyById(Integer.parseInt(vacancyId));
        modelAndView.addObject("vacancy", vacancy);
        modelAndView.setViewName("vacancyEditInformation.jsp");
        return modelAndView;
    }

    @RequestMapping("/saveVacancyInfo")
    public ModelAndView saveVacancyInfo(
            @RequestParam(value = "vacancyId") String vacancyId,
            @RequestParam(value = "position") String position,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "salary") String salary,
            ModelAndView modelAndView) {
        Vacancy vacancy = employerService.getVacancyById(Integer.parseInt(vacancyId));

        boolean success = validationService.salaryIsValid(salary);
        
        if (success) {
            vacancy.setPosition(position);
            vacancy.setDescription(description);
            
            BigDecimal sal = BigDecimal.valueOf(Double.parseDouble(salary));
            sal = sal.setScale(TWENTY, RoundingMode.DOWN);
            
            vacancy.setSalary(sal);
            
            success = employerService.updateVacancyInformation(vacancy);
            modelAndView.addObject("vacancy", vacancy);
        }
        
        if (!success){
            modelAndView.addObject(SALARY_ERROR, SALARY_ERROR);
        }

        modelAndView.setViewName("vacancyEditInformation.jsp");
        return modelAndView;
    }
    
    @RequestMapping("/editEmployerVacancySkills")
    public ModelAndView editEmployerVacancySkills(
            @RequestParam(value = "vacancyId") String vacancyId,
            ModelAndView modelAndView) {
        Vacancy vacancy = employerService.getVacancyById(Integer.parseInt(vacancyId));
        List<VacancySkill> list = employerService.getVacancySkills(vacancy);
        List<BasicSkill> skillsToAdd = employerService.getVacancySkillsToAdd(vacancy);
        
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
        Vacancy vacancy = employerService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.updateVacancySkill(skillId, exp);
        }
        if (error) {
            modelAndView.addObject(UPDATE_ERROR, UPDATE_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        
        modelAndView.addObject("skills", employerService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", employerService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/vacancyDeleteSkill")
    public ModelAndView vacancyDeleteSkill(
            @RequestParam(value = "vacancyId") int vacancyId,
            @RequestParam(value = "skillId") int skillId,
            ModelAndView modelAndView) {

        boolean error = false;
        Vacancy vacancy = employerService.getVacancyById(vacancyId);
        
        if (!error) {
            error = !employerService.deleteVacancySkill(skillId);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", employerService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", employerService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }

    @RequestMapping("/vacancyDeleteAllSkills")
    public ModelAndView vacancyDeleteAllSkills(
            @RequestParam(value = "vacancyId") int vacancyId,
            ModelAndView modelAndView) {

        boolean error = false;
        Vacancy vacancy = employerService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.deleteAllVacancySkills(vacancy);
        }
        if (error) {
            modelAndView.addObject(DELETE_ERROR, DELETE_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", employerService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", employerService.getVacancySkillsToAdd(vacancy));
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
        Vacancy vacancy = employerService.getVacancyById(vacancyId);

        if (!error) {
            error = !employerService.addSkill(vacancy, skill, exp);
        }
        if (error) {
            modelAndView.addObject(ADD_ERROR, ADD_ERROR);
        }

        modelAndView.addObject("vacancy", vacancy);
        modelAndView.addObject("skills", employerService.getVacancySkills(vacancy));
        modelAndView.addObject("skillsToAdd", employerService.getVacancySkillsToAdd(vacancy));
        modelAndView.setViewName("vacancyEditSkills.jsp");
        return modelAndView;
    }
    
}
