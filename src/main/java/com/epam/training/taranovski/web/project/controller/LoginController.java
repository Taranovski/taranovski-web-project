/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.service.EmployeeService;
import com.epam.training.taranovski.web.project.service.EmployerService;
import com.epam.training.taranovski.web.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
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
    private EmployeeService employeeService;

    @Autowired
    private EmployerService employerService;

    /**
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toLoginPage")
    public ModelAndView toLoginPage(ModelAndView modelAndView) {
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
            @RequestParam(value = "username") String userName,
            @RequestParam(value = "password") String password,
            ModelAndView modelAndView
    ) {

        boolean error = false;
        User user = null;
        if (userName.matches("\\s*") || password.matches("\\s*")) {
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
                    modelAndView.addObject("skills", employeeService.getSkillList((Employee) user));
                    modelAndView.setViewName("employee.jsp");
                    break;
                }
                case "employer": {
//                    modelAndView.addObject("skills", employerService.getVacancyList(user));
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
