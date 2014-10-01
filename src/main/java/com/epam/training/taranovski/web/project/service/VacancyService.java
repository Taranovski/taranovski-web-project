/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

import com.epam.training.taranovski.web.project.domain.BasicSkill;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import java.util.List;

/**
 *
 * @author Alyx
 */
public interface VacancyService {

    public List<Vacancy> getVacancyList(Employer employer);

    public Vacancy getVacancyById(int id);

    public List<VacancySkill> getVacancySkills(Vacancy vacancy);

    public List<BasicSkill> getVacancySkillsToAdd(Vacancy vacancy);

}
