/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Oleksandr_Taranovsky
 */
@Controller
public class RegisterController {

    /**
     *
     * @param modelAndView
     * @return
     */
    @Autowired
    @RequestMapping("/toRegister")
    public ModelAndView toRegister(ModelAndView modelAndView) {
        modelAndView.setViewName("register.jsp");
        return modelAndView;
    }

    /**
     *
     * @param modelAndView
     * @return
     */
    @Autowired
    @RequestMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {

        modelAndView.setViewName("login.jsp");
        return modelAndView;
    }
}
