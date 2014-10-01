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

    public CheckDocument getJobCheckDocument(Employee employee);

    public boolean bidForVacancy(Employee employee, Vacancy vacancy);
    
    public boolean offerVacancy(Employee employee, Vacancy vacancy);
    
    public boolean acceptBid(OfferBid offerBid);
    
    public boolean acceptOffer(OfferBid offerBid);

    public List<OfferBid> getOffers(Employee employee);
    
    public List<OfferBid> getBids(Vacancy vacancy);

}
