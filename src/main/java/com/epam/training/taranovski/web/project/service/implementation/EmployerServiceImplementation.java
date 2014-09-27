/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.EmployerRepository;
import com.epam.training.taranovski.web.project.service.EmployerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alyx
 */
@Service
public class EmployerServiceImplementation implements EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    @Override
    public List<Vacancy> getVacancyList(Employer employer) {
        return employerRepository.getVacancys(employer);
    }

    @Override
    public boolean save(Employer employer) {
        return employerRepository.update(employer);
    }

}
