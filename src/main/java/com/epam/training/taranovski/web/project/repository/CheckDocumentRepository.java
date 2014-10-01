/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Vacancy;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface CheckDocumentRepository extends TemplateRepository<CheckDocument> {

    public CheckDocument findByEmployee(Employee employee);

    public CheckDocument findByVacancy(Vacancy vacancy);
}
