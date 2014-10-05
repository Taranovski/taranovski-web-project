/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.service.RegisterService;
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
public class RegisterController {

    private static final String NAME_ERROR = "name_error";
    private static final String PASSWORD_ERROR = "password_error";
    private static final String REGISTER_ERROR = "register_error";
    private static final String REGISTER_SUCCESS = "register_success";
    
    private static final String REGISTER_PAGE = "register.jsp";
    private static final String PARAMETER_USERNAME = "username";
    private static final String PARAMETER_PASSWORD = "password";
    private static final String PARAMETER_USERTYPE = "usertype";

    @Autowired
    private RegisterService registerService;

    /**
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toRegister")
    public ModelAndView toRegister(ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTER_PAGE);
        return modelAndView;
    }

    /**
     *
     * @param userName
     * @param password
     * @param usertype
     * @param modelAndView
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(
            @RequestParam(value = PARAMETER_USERNAME) String userName,
            @RequestParam(value = PARAMETER_PASSWORD) String password,
            @RequestParam(value = PARAMETER_USERTYPE) String usertype,
            ModelAndView modelAndView) {

        boolean error = false;

        if (!registerService.loginAllowed(userName)) {
            error = true;
            modelAndView.addObject(NAME_ERROR, NAME_ERROR);
        }

        if (!registerService.passwordAllowed(password)) {
            error = true;
            modelAndView.addObject(PASSWORD_ERROR, PASSWORD_ERROR);
        }

        if (!error) {
            boolean registered = registerService.register(userName, password, usertype);
            if (registered) {
                modelAndView.addObject(REGISTER_SUCCESS, REGISTER_SUCCESS);
            } else {
                modelAndView.addObject(REGISTER_ERROR, REGISTER_ERROR);
            }
        }

        modelAndView.setViewName(REGISTER_PAGE);
        return modelAndView;
    }
}
