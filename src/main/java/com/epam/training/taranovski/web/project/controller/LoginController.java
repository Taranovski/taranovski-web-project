/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

    /**
     *
     * @return
     */
//    @Autowired
    @RequestMapping("/toLoginPage")
    public ModelAndView toLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.jsp");
        return modelAndView;
    }

    /**
     *
     * @param userName
     * @param password
     * @return
     */
//    @Autowired
    @RequestMapping("/loginSystem")
    public ModelAndView login(
            @RequestParam(value = "username") String userName,
            @RequestParam(value = "password") String password
    ) {

        ModelAndView modelAndView = new ModelAndView();

        boolean error = true;

        modelAndView.addObject(LOGIN_ERROR, LOGIN_ERROR);
        modelAndView.setViewName("login.jsp");

        return modelAndView;
    }

}
