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
public interface EmployerService {

    public List<Vacancy> getVacancyList(Employer employer);

    public boolean save(Employer employer);
    
    public Vacancy getVacancyById(int id);
    
    public List<VacancySkill> getVacancySkills(Vacancy vacancy);

    public boolean updateVacancyInformation(Vacancy vacancy);

    public List<BasicSkill> getVacancySkillsToAdd(Vacancy vacancy);

    public boolean updateVacancySkill(int skillId, int exp);
    
    public boolean deleteVacancySkill(int skillId);

    public boolean deleteAllVacancySkills(Vacancy vacancy);

    public List<BasicSkill> getSkillsToAddList(Vacancy vacancy);

    public boolean addSkill(Vacancy vacancy, int skill, int exp);
    
}
