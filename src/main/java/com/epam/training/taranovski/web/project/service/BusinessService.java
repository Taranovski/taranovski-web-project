/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import java.util.List;

/**
 *
 * @author Alyx
 */
public interface BusinessService {
    List<Vacancy> getAvailableVacancies(Employee employee);
    List<Employee> getAvailableEmployees(Vacancy vacancy);
    
    
}
