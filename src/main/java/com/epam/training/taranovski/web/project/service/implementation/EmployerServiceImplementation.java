/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.BasicSkill;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.repository.BasicSkillRepository;
import com.epam.training.taranovski.web.project.repository.EmployerRepository;
import com.epam.training.taranovski.web.project.repository.VacancyRepository;
import com.epam.training.taranovski.web.project.repository.VacancySkillRepository;
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

    @Autowired
    private VacancyRepository vacancyRepository;
    
    @Autowired
    private BasicSkillRepository basicSkillRepository;
    
    @Autowired
    private VacancySkillRepository vacancySkillRepository;

    @Override
    public List<Vacancy> getVacancyList(Employer employer) {
        return employerRepository.getVacancys(employer);
    }

    @Override
    public boolean save(Employer employer) {
        return employerRepository.update(employer);
    }

    @Override
    public Vacancy getVacancyById(int id) {
        return vacancyRepository.getById(id);
    }

    @Override
    public List<VacancySkill> getVacancySkills(Vacancy vacancy) {
        return vacancyRepository.getSkills(vacancy);
    }

    @Override
    public boolean updateVacancyInformation(Vacancy vacancy) {
        return vacancyRepository.update(vacancy);
    }

    @Override
    public List<BasicSkill> getVacancySkillsToAdd(Vacancy vacancy) {
        return basicSkillRepository.getSkillsNotInVacancy(vacancy);
    }

    @Override
    public boolean updateVacancySkill(int skillId, int exp) {
        VacancySkill vacancySkill = vacancySkillRepository.getById(skillId);
        vacancySkill.setExperience(exp);
        return vacancySkillRepository.update(vacancySkill);
    }

    @Override
    public boolean deleteVacancySkill(int skillId) {
        VacancySkill vacancySkill = vacancySkillRepository.getById(skillId);
        return vacancySkillRepository.delete(vacancySkill);
    }

    @Override
    public boolean deleteAllVacancySkills(Vacancy vacancy) {
        return vacancyRepository.clearSkills(vacancy);
    }

    @Override
    public List<BasicSkill> getSkillsToAddList(Vacancy vacancy) {
        return basicSkillRepository.getSkillsNotInVacancy(vacancy);
    }

    @Override
    public boolean addSkill(Vacancy vacancy, int skill, int exp) {
        BasicSkill basicSkill = basicSkillRepository.getById(skill);
        VacancySkill vacancySkill = new VacancySkill();
        vacancySkill.setVacancy(vacancy);
        vacancySkill.setSkill(basicSkill);
        vacancySkill.setExperience(exp);
        return vacancySkillRepository.create(vacancySkill);
    }

}
