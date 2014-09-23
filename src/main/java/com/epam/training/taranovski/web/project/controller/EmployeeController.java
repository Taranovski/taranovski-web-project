/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.controller;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
import com.epam.training.taranovski.web.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Alyx
 */
@Controller
@SessionAttributes(value = "user")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
     
    @RequestMapping("/editEmployeePersonalInfo")
    public ModelAndView editEmployeePersonalInfo(ModelAndView modelAndView) {
        modelAndView.setViewName("employeeEditInformation.jsp");
        return modelAndView;
    }
    
    @RequestMapping("/dontSaveEmployeePersonalInfo")
    public ModelAndView dontSaveEmployeePersonalInfo(ModelAndView modelAndView) {
        modelAndView.setViewName("employee.jsp");
        return modelAndView;
    }

    @RequestMapping("/saveEmployeePersonalInfo")
    public ModelAndView saveEmployeePersonalInfo(
            @ModelAttribute(value = "user") Employee employee,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "patronymic") String patronymic,
            @RequestParam(value = "qualification") String qualification,
            @RequestParam(value = "occupation") String occupation,
            ModelAndView modelAndView) {

        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setQualification(qualification);
        employee.setOccupation(occupation);
        
        employeeService.save(employee);
        
        modelAndView.addObject("user", employee);
        modelAndView.setViewName("employee.jsp");

        return modelAndView;
    }
}
