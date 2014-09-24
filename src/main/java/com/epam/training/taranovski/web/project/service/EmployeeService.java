/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

import com.epam.training.taranovski.web.project.domain.BasicSkill;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.UserSkill;
import java.util.List;

/**
 *
 * @author Alyx
 */
public interface EmployeeService {

    boolean save(Employee employee);

    public List<UserSkill> getSkillList(Employee employee);

    public boolean updateSkill(int skillId, int exp);

    public boolean deleteSkill(int skillId);

    public boolean deleteAllSkills(Employee employe);

    public List<BasicSkill> getSkillsToAddList(Employee employee);

    public boolean addSkill(Employee employee, int skill, int exp);
}
