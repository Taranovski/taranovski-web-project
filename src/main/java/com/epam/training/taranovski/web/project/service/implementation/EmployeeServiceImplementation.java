/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.BasicSkill;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.repository.BasicSkillRepository;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
import com.epam.training.taranovski.web.project.repository.UserSkillRepository;
import com.epam.training.taranovski.web.project.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alyx
 */
@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private UserSkillRepository userSkillRepository;
    
    @Autowired
    private BasicSkillRepository basicSkillRepository;

    @Override
    public boolean save(Employee employee) {
        return employeeRepository.update(employee);
    }

    @Override
    public List<UserSkill> getSkillList(Employee employee) {
        return employeeRepository.getSkills(employee);
    }

    @Override
    public boolean updateSkill(int skillId, int exp) {
        UserSkill userSkill = userSkillRepository.getById(skillId);
        userSkill.setExperience(exp);
        return userSkillRepository.update(userSkill);
    }

    @Override
    public boolean deleteSkill(int skillId) {
        UserSkill userSkill = userSkillRepository.getById(skillId);
        return userSkillRepository.delete(userSkill);
    }

    @Override
    public boolean deleteAllSkills(Employee employee) {
        return employeeRepository.clearSkills(employee);
    }

    @Override
    public List<BasicSkill> getSkillsToAddList(Employee employee) {
        return basicSkillRepository.getSkillsNotInEmployee(employee);
    }

    @Override
    public boolean addSkill(Employee employee, int skill, int exp) {
        BasicSkill basicSkill = basicSkillRepository.getById(skill);
        UserSkill userSkill = new UserSkill();
        userSkill.setEmployee(employee);
        userSkill.setSkill(basicSkill);
        userSkill.setExperience(exp);
        return userSkillRepository.create(userSkill);
    }

}
