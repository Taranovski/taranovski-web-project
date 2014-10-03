/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service.implementation;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.OfferBid;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.CheckDocumentRepository;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
import com.epam.training.taranovski.web.project.repository.OfferBidRepository;
import com.epam.training.taranovski.web.project.repository.VacancyRepository;
import com.epam.training.taranovski.web.project.service.BusinessService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alyx
 */
@Service
public class BusinessServiceImplementation implements BusinessService {

    private static final BigDecimal COMMISSION_RATE = new BigDecimal(0.5);

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
    }

    @Override
    public List<Employee> getAvailableEmployees(Vacancy vacancy) {
        return vacancyRepository.getAppropriateEmployees(vacancy);
    }

    @Override
    public CheckDocument getJobCheckDocument(Employee employee) {
        return checkDocumentRepository.findByEmployee(employee);
    }

    @Override
    public boolean bidForVacancy(Employee employee, Vacancy vacancy) {

        boolean offerBidDoNotExistsInDB = offerBidRepository.getBidByEmployeeAndVacancy(employee, vacancy) != null;

        if (!offerBidDoNotExistsInDB) {
            OfferBid offerBid = new OfferBid();
            offerBid.setEmployee(employee);
            offerBid.setVacancy(vacancy);
            offerBid.setEmployer(vacancy.getEmployer());
            offerBid.setEmployeeSigned("signed");

            return offerBidRepository.create(offerBid);
        } else {
            return false;
        }
    }

    @Override
    public boolean offerVacancy(Employee employee, Vacancy vacancy) {
        boolean offerBidDoNotExistsInDB = offerBidRepository.getOfferByEmployeeAndVacancy(employee, vacancy) != null;

        if (!offerBidDoNotExistsInDB) {
            OfferBid offerBid = new OfferBid();
            offerBid.setEmployee(employee);
            offerBid.setVacancy(vacancy);
            offerBid.setEmployer(vacancy.getEmployer());
            offerBid.setEmployerSigned("signed");

            return offerBidRepository.create(offerBid);
        } else {
            return false;
        }
    }

    @Override
    public boolean acceptBid(OfferBid offerBid) {
        boolean success = false;
        if (offerBid == null) {
            return false;
        }
        offerBid.setEmployerSigned("signed");
        if ("signed".equals(offerBid.getEmployeeSigned()) && "signed".equals(offerBid.getEmployerSigned())) {
            Employee employee = offerBid.getEmployee();
            employee.setStatus("hired");
            Vacancy vacancy = offerBid.getVacancy();
            vacancy.setStatus("closed");

            CheckDocument checkDocument = new CheckDocument();
            checkDocument.setEmployee(employee);
            checkDocument.setVacancy(vacancy);
            checkDocument.setCommissions(vacancy.getSalary().multiply(COMMISSION_RATE));

            boolean error = !employeeRepository.update(employee);
            error = error & !offerBidRepository.update(offerBid);
            error = error & !vacancyRepository.update(vacancy);
            error = error & !checkDocumentRepository.create(checkDocument);

            if (!error) {
                success = true;
            }
        }
        return success;
    }

    @Override
    public boolean acceptOffer(OfferBid offerBid) {
        boolean success = false;
        if (offerBid == null) {
            return false;
        }
        offerBid.setEmployeeSigned("signed");
        if ("signed".equals(offerBid.getEmployeeSigned()) && "signed".equals(offerBid.getEmployerSigned())) {
            Employee employee = offerBid.getEmployee();
            employee.setStatus("hired");
            Vacancy vacancy = offerBid.getVacancy();
            vacancy.setStatus("closed");

            CheckDocument checkDocument = new CheckDocument();
            checkDocument.setEmployee(employee);
            checkDocument.setVacancy(vacancy);
            checkDocument.setCommissions(vacancy.getSalary().multiply(COMMISSION_RATE));

            boolean error = !employeeRepository.update(employee);
            error = error & !offerBidRepository.update(offerBid);
            error = error & !vacancyRepository.update(vacancy);
            error = error & !checkDocumentRepository.create(checkDocument);

            if (!error) {
                success = true;
            }
        }
        return success;
    }

    @Override
    public List<Vacancy> getOffersForEmployee(Employee employee) {
        return offerBidRepository.getOffersForEmployee(employee);
    }

    @Override
    public List<Vacancy> getBidsForEmployee(Employee employee) {
        return offerBidRepository.getBidsForEmployee(employee);
    }

    @Override
    public List<Employee> getBidsForVacancy(Vacancy vacancy) {
        return offerBidRepository.getBidsForVacancy(vacancy);
    }

    @Override
    public List<Employee> getOffersForVacancy(Vacancy vacancy) {
        return offerBidRepository.getOffersForVacancy(vacancy);
    }

    @Override
    public OfferBid getOffer(Employee employee, Vacancy vacancy) {
        return offerBidRepository.getOfferByEmployeeAndVacancy(employee, vacancy);
    }

    @Override
    public OfferBid getBid(Employee employee, Vacancy vacancy) {
        return offerBidRepository.getBidByEmployeeAndVacancy(employee, vacancy);
    }

    @Override
    public boolean deleteOfferBid(OfferBid offerBid) {
        return offerBidRepository.delete(offerBid);
    }
}
