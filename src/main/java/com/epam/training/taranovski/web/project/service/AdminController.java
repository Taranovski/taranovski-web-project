/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

import com.epam.training.taranovski.web.project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Alyx
 */
@Controller
@RequestMapping(value = "/getAdminById")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping("/getAdminById")
    public String getAdminById(Model model) {
        System.out.println("1");
        model.addAttribute("admin", adminRepository.getById(1));
        return "admin";
    }

}
