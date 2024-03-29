/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Admin;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.service.LoginService;
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
    private static final String LOGIN_PAGE = "login.jsp";
    
    private static final String PARAMETER_USER = "user";
    private static final String PARAMETER_USERNAME = "username";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_EMPLOYEE = "employee";
    private static final String PARAMETER_EMPLOYER = "employer";
    private static final String PARAMETER_ADMIN = "admin";
    

    @Autowired
    private LoginService loginService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployerController employerController;
    
    @Autowired
    private AdminController adminController;

    /**
     *
     * @param modelAndView
     * @param sessionStatus
     * @return
     */
    @RequestMapping("/toLoginPage")
    public ModelAndView logout(ModelAndView modelAndView, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        modelAndView.setViewName(LOGIN_PAGE);
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
            @RequestParam(required = false, value = PARAMETER_USERNAME) String userName,
            @RequestParam(required = false, value = PARAMETER_PASSWORD) String password,
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
            modelAndView.setViewName(LOGIN_PAGE);
        } else {
            String type = user.getUserType();
            modelAndView.addObject(PARAMETER_USER, user);

            switch (type) {
                case PARAMETER_EMPLOYEE: {
                    return employeeController.toEmployeePage((Employee) user, modelAndView);
                }
                case PARAMETER_EMPLOYER: {
                    return employerController.toEmployerPage((Employer) user, modelAndView);
                }
                case PARAMETER_ADMIN: {
                    return adminController.toAdminPage((Admin) user, modelAndView);
                }
                default: {
                    modelAndView.addObject(LOGIN_ERROR, LOGIN_ERROR);
                    modelAndView.setViewName(LOGIN_PAGE);
                }
            }
        }
        return modelAndView;
    }
}
