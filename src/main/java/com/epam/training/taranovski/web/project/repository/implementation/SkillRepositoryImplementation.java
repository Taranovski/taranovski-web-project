/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.repository.SkillRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alyx
 */
@Repository
public class SkillRepositoryImplementation implements SkillRepository {

    @Override
    public UserSkill getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserSkill> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(UserSkill admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserSkill admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(UserSkill admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
