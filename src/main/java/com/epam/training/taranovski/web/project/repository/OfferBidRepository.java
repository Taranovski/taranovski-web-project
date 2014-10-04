/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.OfferBid;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import java.util.List;

/**
 *
 * @author Alyx
 */
public interface OfferBidRepository extends TemplateRepository<OfferBid> {

    List<Vacancy> getOffersForEmployee(Employee employee);

    List<Vacancy> getBidsForEmployee(Employee employee);

    OfferBid getOfferByEmployeeAndVacancy(Employee employee, Vacancy vacancy);

    OfferBid getBidByEmployeeAndVacancy(Employee employee, Vacancy vacancy);

    List<Employee> getBidsForVacancy(Vacancy vacancy);

    List<Employee> getOffersForVacancy(Vacancy vacancy);

    boolean deleteAllBidsForVacancy(Vacancy vacancy);

    boolean deleteAllOffersForEmployee(Employee employee);

}
