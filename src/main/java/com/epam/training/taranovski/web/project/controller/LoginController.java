/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.service.BusinessService;
import com.epam.training.taranovski.web.project.service.EmployeeService;
import com.epam.training.taranovski.web.project.service.EmployerService;
import com.epam.training.taranovski.web.project.service.LoginService;
import com.epam.training.taranovski.web.project.service.VacancyService;
import com.epam.training.taranovski.web.project.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Oleksandr_Taranovsky
 */
@Controller
@SessionAttributes(value = {"user", "skills", "vacancies"})
public class LoginController {

    private static final String LOGIN_ERROR = "login_error";

    @Autowired
    private LoginService loginService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployerService employerService;

    @Autowired
    private BusinessService businessService;
    
    @Autowired
    private VacancyService vacancyService;

    /**
     *
     * @param modelAndView
     * @param sessionStatus
     * @return
     */
    @RequestMapping("/toLoginPage")
    public ModelAndView logout(ModelAndView modelAndView, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        modelAndView.setViewName("login.jsp");
        return modelAndView;
    }

    /**
     *
     * @param userName
     * @param password
     * @param modelAndView
     * @return
     */
    @RequestMapping("/loginSystem")
    public ModelAndView login(
            @RequestParam(required = false, value = "username") String userName,
            @RequestParam(required = false, value = "password") String password,
            ModelAndView modelAndView
    ) {

        boolean error = false;
        User user = null;
        if (!validationService.userNameIsValid(userName)
                || !validationService.passwordIsValid(password)) {
            error = true;
        } else {
            user = loginService.login(userName, password);
            error = (user == null);
        }

        if (error) {
            modelAndView.addObject(LOGIN_ERROR, LOGIN_ERROR);
            modelAndView.setViewName("login.jsp");
        } else {
            String type = user.getUserType();
            modelAndView.addObject("user", user);

            switch (type) {
                case "employee": {

                    if (((Employee) user).getStatus().equals("free")) {
                        modelAndView.addObject("vacancies", businessService.getAvailableVacancies((Employee) user));
                        modelAndView.addObject("offers", businessService.getOffers((Employee) user));
                        modelAndView.addObject("skills", employeeService.getSkillList((Employee) user));
                        modelAndView.setViewName("employee.jsp");
                    } else if (((Employee) user).getStatus().equals("hired")){
                        modelAndView.addObject("checkDocument", businessService.getJobCheckDocument((Employee) user));
                        modelAndView.setViewName("employeeHired.jsp");
                    }

                    break;
                }
                case "employer": {
                    modelAndView.addObject("vacancies", vacancyService.getVacancyList((Employer) user));
                    modelAndView.setViewName("employer.jsp");
                    break;
                }
                case "admin": {
                    modelAndView.setViewName("admin.jsp");
                    break;
                }
                default: {
                    modelAndView.addObject(LOGIN_ERROR, LOGIN_ERROR);
                    modelAndView.setViewName("login.jsp");
                }
            }
        }
        return modelAndView;
    }
}
