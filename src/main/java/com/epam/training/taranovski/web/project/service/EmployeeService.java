/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

import com.epam.training.taranovski.web.project.domain.Employee;

/**
 *
 * @author Alyx
 */
public interface EmployeeService {

    boolean save(Employee employee);
}