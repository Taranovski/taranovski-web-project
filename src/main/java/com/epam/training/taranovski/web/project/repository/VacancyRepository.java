/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository;

import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import java.util.List;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface VacancyRepository extends TemplateRepository<Vacancy> {

    boolean addSkill(Vacancy vacancy, UserSkill skill);

    boolean removeSkill(Vacancy vacancy, UserSkill skill);

    boolean clearSkills(Vacancy vacancy);

    List<UserSkill> getSkills(Vacancy vacancy);
}
