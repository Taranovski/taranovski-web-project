/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
import com.epam.training.taranovski.web.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alyx
 */
@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean save(Employee employee) {
        return employeeRepository.update(employee);
    }

}
