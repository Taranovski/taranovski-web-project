/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Admin;
import com.epam.training.taranovski.web.project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alyx
 */
@Controller
@SessionAttributes(value = "user")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @RequestMapping("/toAdminPage")
    public ModelAndView toAdminPage(
            @ModelAttribute(value = "user") Admin admin,
            ModelAndView modelAndView) {

        
        
        modelAndView.setViewName("admin.jsp");
        return modelAndView;
    }

}
