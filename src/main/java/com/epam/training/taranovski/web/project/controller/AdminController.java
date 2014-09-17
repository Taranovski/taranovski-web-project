/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alyx
 */
@Controller
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @RequestMapping("/admin")
    public ModelAndView getAdminById(ModelAndView modelAndView) {
        modelAndView.addObject("admin", adminRepository.getById(1));
        modelAndView.setViewName("admin.jsp");
        return modelAndView;
    }

    @RequestMapping("/newjsp")
    public ModelAndView getAdminById1(ModelAndView modelAndView) {
        modelAndView.addObject("admin", adminRepository.getById(2));
        modelAndView.setViewName("newjsp.jsp");
        return modelAndView;
    }
}
