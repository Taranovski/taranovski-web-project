/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.OfferBid;
import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.repository.CheckDocumentRepository;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
import com.epam.training.taranovski.web.project.repository.OfferBidRepository;
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

    @Autowired
    private CheckDocumentRepository checkDocumentRepository;

    @Autowired
    private OfferBidRepository offerBidRepository;

    @Override
    public List<Vacancy> getAvailableVacancies(Employee employee) {
        return employeeRepository.getAvailableVacancies(employee);

//        List<UserSkill> userSkills = employeeRepository.getSkills(employee);
//        List<Vacancy> vacancys = vacancyRepository.getAll();
//        List<Vacancy> result = new LinkedList<>();
//
//        List<VacancySkill> vacancySkills = null;
//
//        for (Vacancy vacancy : vacancys) {
//            vacancySkills = vacancyRepository.getSkills(vacancy);
//            if (skillsMatch(vacancySkills, userSkills)) {
//                result.add(vacancy);
//            }
//        }
//        return result;
    }

    @Override
    public List<Employee> getAvailableEmployees(Vacancy vacancy) {
        return vacancyRepository.getAppropriateEmployees(vacancy);

//        List<VacancySkill> vacancySkills = vacancyRepository.getSkills(vacancy);
//        List<Employee> employees = employeeRepository.getAllFreeEmployees();
//
//        List<Employee> result = new LinkedList<>();
//        List<UserSkill> userSkills = null;
//
//        for (Employee employee : employees) {
//            userSkills = employeeRepository.getSkills(employee);
//            if (skillsMatch(vacancySkills, userSkills)) {
//                result.add(employee);
//            }
//        }
//        return result;
    }

//    private boolean skillsMatch(List<VacancySkill> vacancySkills, List<UserSkill> userSkills) {
//        boolean success = true;
//
//        for (VacancySkill vacancySkill : vacancySkills) {
//            success = success & skillIsPresentAndHigher(vacancySkill, userSkills);
//            if (!success) {
//                break;
//            }
//        }
//        return success;
//    }
//
//    private boolean skillIsPresentAndHigher(VacancySkill vacancySkill, List<UserSkill> userSkills) {
//        boolean success = false;
//        for (UserSkill userSkill : userSkills) {
//            if (vacancySkill.getSkill().equals(userSkill.getSkill())) {
//                if (vacancySkill.getExperience() <= userSkill.getExperience()) {
//                    success = true;
//                    break;
//                }
//            }
//        }
//        return success;
//    }

    @Override
    public CheckDocument getJobCheckDocument(Employee employee) {
        return checkDocumentRepository.findByEmployee(employee);
    }

    @Override
    public boolean bidForVacancy(Employee employee, Vacancy vacancy) {
        OfferBid offerBid = new OfferBid();
        offerBid.setEmployee(employee);
        offerBid.setVacancy(vacancy);
        offerBid.setEmployer(vacancy.getEmployer());
        offerBid.setEmployeeSigned("signed");

        return offerBidRepository.create(offerBid);
    }

    @Override
    public boolean offerVacancy(Employee employee, Vacancy vacancy) {
        OfferBid offerBid = new OfferBid();
        offerBid.setEmployee(employee);
        offerBid.setVacancy(vacancy);
        offerBid.setEmployer(vacancy.getEmployer());
        offerBid.setEmployerSigned("signed");

        return offerBidRepository.create(offerBid);
    }

    @Override
    public List<OfferBid> getOffers(Employee employee) {
        return offerBidRepository.getOffersForEmployee(employee);
    }

    @Override
    public List<OfferBid> getBids(Vacancy vacancy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean acceptBid(OfferBid offerBid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean acceptOffer(OfferBid offerBid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
