/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.util.Objects;

/**
 *
 * @author Alyx
 */
public class OfferBid {

    private Integer offerBidId;

    private Boolean employeeSigned;
    private Boolean employerSigned;
    private Employee employee;
    private Vacancy vacancy;

    public Integer getOfferBidId() {
        return offerBidId;
    }

    public void setOfferBidId(Integer offerBidId) {
        this.offerBidId = offerBidId;
    }

    public Boolean getEmployeeSigned() {
        return employeeSigned;
    }

    public void setEmployeeSigned(Boolean employeeSigned) {
        this.employeeSigned = employeeSigned;
    }

    public Boolean getEmployerSigned() {
        return employerSigned;
    }

    public void setEmployerSigned(Boolean employerSigned) {
        this.employerSigned = employerSigned;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.employeeSigned);
        hash = 53 * hash + Objects.hashCode(this.employerSigned);
        hash = 53 * hash + Objects.hashCode(this.employee);
        hash = 53 * hash + Objects.hashCode(this.vacancy);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OfferBid other = (OfferBid) obj;
        if (!Objects.equals(this.employeeSigned, other.employeeSigned)) {
            return false;
        }
        if (!Objects.equals(this.employerSigned, other.employerSigned)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        if (!Objects.equals(this.vacancy, other.vacancy)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OfferBid{" + "employeeSigned=" + employeeSigned + ", employerSigned=" + employerSigned + ", employee=" + employee + ", vacancy=" + vacancy + '}';
    }

}
