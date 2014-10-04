/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import java.util.List;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface VacancyRepository extends TemplateRepository<Vacancy> {

    boolean addSkill(Vacancy vacancy, VacancySkill skill);

    boolean removeSkill(Vacancy vacancy, VacancySkill skill);

    boolean clearSkills(Vacancy vacancy);

    List<VacancySkill> getSkills(Vacancy vacancy);
    
    List<Employee> getAppropriateAvailableEmployees(Vacancy vacancy);
}
