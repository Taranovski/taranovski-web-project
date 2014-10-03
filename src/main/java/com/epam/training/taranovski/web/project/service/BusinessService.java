/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.service;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.OfferBid;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import java.util.List;

/**
 *
 * @author Alyx
 */
public interface BusinessService {

    List<Vacancy> getAvailableVacancies(Employee employee);

    List<Employee> getAvailableEmployees(Vacancy vacancy);

    CheckDocument getJobCheckDocument(Employee employee);

    boolean bidForVacancy(Employee employee, Vacancy vacancy);

    boolean offerVacancy(Employee employee, Vacancy vacancy);

    boolean acceptBid(OfferBid offerBid);

    boolean acceptOffer(OfferBid offerBid);

    List<Vacancy> getOffersForEmployee(Employee employee);

    List<Vacancy> getBidsForEmployee(Employee employee);

    List<Employee> getBidsForVacancy(Vacancy vacancy);

    List<Employee> getOffersForVacancy(Vacancy vacancy);

    OfferBid getOffer(Employee employee, Vacancy vacancy);

    OfferBid getBid(Employee employee, Vacancy vacancy);

    public boolean deleteBid(OfferBid offerBid);

}
