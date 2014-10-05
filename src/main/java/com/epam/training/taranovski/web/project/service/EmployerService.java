/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.Vacancy;

/**
 *
 * @author Alyx
 */
public interface EmployerService {

    public boolean save(Employer employer);
    
    public boolean updateVacancyInformation(Vacancy vacancy);

    public boolean updateVacancySkill(int skillId, int exp);
    
    public boolean deleteVacancySkill(int skillId);

    public boolean deleteAllVacancySkills(Vacancy vacancy);

    public boolean addSkill(Vacancy vacancy, int skill, int exp);

    public boolean deleteVacancy(Vacancy vacancy);

    public boolean createVacancy(Vacancy vacancy);
    
}
