/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.VacancyRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alyx
 */
@Repository
public class VacancyRepositoryImplementation implements VacancyRepository {

    @Override
    public boolean addSkill(Vacancy vacancy, UserSkill skill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeSkill(Vacancy vacancy, UserSkill skill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean clearSkills(Vacancy vacancy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserSkill> getSkills(Vacancy vacancy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vacancy getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vacancy> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Vacancy admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Vacancy admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Vacancy admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
