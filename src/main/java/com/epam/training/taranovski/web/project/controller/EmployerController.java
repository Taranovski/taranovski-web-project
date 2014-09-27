/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.service.EmployerService;
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
@SessionAttributes(value = {"user", "vacancies"})
public class EmployerController {

    private static final String TELEPHONE_NUMBER_ERROR = "tel_error";
    
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
    public ModelAndView dontSaveEmployerPersonalInfo(ModelAndView modelAndView) {
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

}
