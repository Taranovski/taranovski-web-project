/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.UserSkill;
import java.util.List;

/**
 *
 * @author Oleksandr_Taranovsky
 */
public interface EmployeeRepository extends TemplateRepository<Employee> {

    Employee getByCredentials(String firstName, String lastName, String patronymic);

    boolean addSkill(Employee employee, UserSkill skill);

    boolean removeSkill(Employee employee, UserSkill skill);

    boolean clearSkills(Employee employee);

    List<UserSkill> getSkills(Employee employee);

    CheckDocument getCheckDocument(Employee employee);
}
