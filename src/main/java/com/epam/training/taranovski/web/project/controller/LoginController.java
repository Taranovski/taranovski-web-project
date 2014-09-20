/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Oleksandr_Taranovsky
 */
@Controller
public class LoginController {

    private static final String LOGIN_ERROR = "login_error";

    @Autowired
    LoginService loginService;

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

        User user = loginService.login(userName, password);

        if (user == null) {
            modelAndView.addObject(LOGIN_ERROR, LOGIN_ERROR);
            modelAndView.setViewName("login.jsp");
        } else {

            // manage session here
            modelAndView.addObject("user", user.toString());

            String type = user.getUserType();
            switch (type) {
                case "employee": {
                    modelAndView.setViewName("employee.jsp");
                    break;
                }
                case "employer": {
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
