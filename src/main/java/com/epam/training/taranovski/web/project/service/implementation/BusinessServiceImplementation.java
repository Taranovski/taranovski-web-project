/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
import com.epam.training.taranovski.web.project.repository.VacancyRepository;
import com.epam.training.taranovski.web.project.service.BusinessService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alyx
 */
@Service
public class BusinessServiceImplementation implements BusinessService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Override
    public List<Vacancy> getAvailableVacancies(Employee employee) {
        List<UserSkill> userSkills = employeeRepository.getSkills(employee);
        List<Vacancy> vacancys = vacancyRepository.getAll();
        List<Vacancy> result = new LinkedList<>();

        List<VacancySkill> vacancySkills = null;

        for (Vacancy vacancy : vacancys) {
            vacancySkills = vacancyRepository.getSkills(vacancy);
            if (skillsMatch(vacancySkills, userSkills)) {
                result.add(vacancy);
            }
        }
        return result;
    }

    @Override
    public List<Employee> getAvailableEmployees(Vacancy vacancy) {
        List<VacancySkill> vacancySkills = vacancyRepository.getSkills(vacancy);
        List<Employee> employees = employeeRepository.getAll();

        List<Employee> result = new LinkedList<>();
        List<UserSkill> userSkills = null;

        for (Employee employee : employees) {
            userSkills = employeeRepository.getSkills(employee);
            if (skillsMatch(vacancySkills, userSkills)) {
                result.add(employee);
            }
        }
        return result;
    }

    private boolean skillsMatch(List<VacancySkill> vacancySkills, List<UserSkill> userSkills) {
        boolean success = true;

        for (VacancySkill vacancySkill : vacancySkills) {
            success = success & skillIsPresentAndHigher(vacancySkill, userSkills);
            if (!success) {
                break;
            }
        }
        return success;
    }

    private boolean skillIsPresentAndHigher(VacancySkill vacancySkill, List<UserSkill> userSkills) {
        boolean success = false;
        for (UserSkill userSkill : userSkills) {
            if (vacancySkill.getSkill().equals(userSkill.getSkill())) {
                if (vacancySkill.getExperience() <= userSkill.getExperience()) {
                    success = true;
                    break;
                }
            }
        }
        return success;
    }
}
