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

    @Autowired
    private RegisterService registerService;

    /**
     *
     * @param modelAndView
     * @return
     */
    @RequestMapping("/toRegister")
    public ModelAndView toRegister(ModelAndView modelAndView) {
        modelAndView.setViewName("register.jsp");
        return modelAndView;
    }

    /**
     *
     * @param userName
     * @param password
     * @param userType
     * @param modelAndView
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(
            @RequestParam(value = "username") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "userType") String userType,
            ModelAndView modelAndView) {

        boolean error = false;

        if (registerService.loginAllowed(userName)) {
            if (registerService.passwordAllowed(password)) {
                if (registerService.register(userName, password, userType)) {
                    modelAndView.addObject(REGISTER_SUCCESS, REGISTER_SUCCESS);
                } else {
                    modelAndView.addObject(REGISTER_ERROR, REGISTER_ERROR);
                }
            } else {
                modelAndView.addObject(PASSWORD_ERROR, PASSWORD_ERROR);
            }
        } else {
            modelAndView.addObject(NAME_ERROR, NAME_ERROR);
        }

        modelAndView.setViewName("register.jsp");
        return modelAndView;
    }
}
