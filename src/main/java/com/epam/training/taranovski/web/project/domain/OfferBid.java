/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "\"OfferBid\"")
@NamedQueries({
    @NamedQuery(name = "OfferBid.findAll", query = "SELECT o FROM OfferBid o"),
    @NamedQuery(name = "OfferBid.findByOfferBidId", query = "SELECT o FROM OfferBid o WHERE o.offerBidId = :offerBidId"),
    @NamedQuery(name = "OfferBid.findByEmployeeSigned", query = "SELECT o FROM OfferBid o WHERE o.employeeSigned = :employeeSigned"),
    @NamedQuery(name = "OfferBid.findByEmployerSigned", query = "SELECT o FROM OfferBid o WHERE o.employerSigned = :employerSigned"),
    @NamedQuery(name = "OfferBid.findVacancyOffersForEmployee", query = "SELECT o.vacancy FROM OfferBid o WHERE o.employee = :employee and o.employerSigned = 'signed'"),
    @NamedQuery(name = "OfferBid.findVacancyBidsForEmployee", query = "SELECT o.vacancy FROM OfferBid o WHERE o.employee = :employee and o.employeeSigned = 'signed'"),
    @NamedQuery(name = "OfferBid.findByVacancy", query = "SELECT o FROM OfferBid o WHERE o.vacancy = :vacancy"),
    @NamedQuery(name = "OfferBid.findOfferByEmployeeAndVacancy", query = "SELECT o FROM OfferBid o WHERE o.employee = :employee and o.vacancy = :vacancy and o.employerSigned = 'signed'"),
    @NamedQuery(name = "OfferBid.findBidByEmployeeAndVacancy", query = "SELECT o FROM OfferBid o WHERE o.employee = :employee and o.vacancy = :vacancy and o.employeeSigned = 'signed'"),
    @NamedQuery(name = "OfferBid.findEmployeeOffersForVacancy", query = "SELECT o.employee FROM OfferBid o WHERE o.vacancy = :vacancy and o.employerSigned = 'signed'"),
    @NamedQuery(name = "OfferBid.findEmployeeBidsForVacancy", query = "SELECT o.employee FROM OfferBid o WHERE o.vacancy = :vacancy and o.employeeSigned = 'signed'"),
    @NamedQuery(name = "OfferBid.deleteByVacancy", query = "delete FROM OfferBid o WHERE o.vacancy = :vacancy"),
    @NamedQuery(name = "OfferBid.deleteByEmployee", query = "delete FROM OfferBid o WHERE o.employee = :employee")
})
public class OfferBid implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "offerBidSequence", sequenceName = "\"OfferBidSequence\"", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerBidSequence")

    @Column(name = "\"offerBidId\"")
    private Integer offerBidId;
    @Size(max = 20)
    @Column(name = "\"employeeSigned\"")
    private String employeeSigned;
    @Size(max = 20)
    @Column(name = "\"employerSigned\"")
    private String employerSigned;
    @JoinColumn(name = "\"vacancyId\"", referencedColumnName = "\"vacancyId\"")
    @ManyToOne
    private Vacancy vacancy;
    @JoinColumn(name = "\"offerEmployerId\"", referencedColumnName = "\"employerUserId\"")
    @ManyToOne
    private Employer employer;
    @JoinColumn(name = "\"bidEmployeeId\"", referencedColumnName = "\"employeeUserId\"")
    @ManyToOne
    private Employee employee;

    public OfferBid() {
    }

    public OfferBid(Integer offerBidId) {
        this.offerBidId = offerBidId;
    }

    public Integer getOfferBidId() {
        return offerBidId;
    }

    public void setOfferBidId(Integer offerBidId) {
        this.offerBidId = offerBidId;
    }

    public String getEmployeeSigned() {
        return employeeSigned;
    }

    public void setEmployeeSigned(String employeeSigned) {
        this.employeeSigned = employeeSigned;
    }

    public String getEmployerSigned() {
        return employerSigned;
    }

    public void setEmployerSigned(String employerSigned) {
        this.employerSigned = employerSigned;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.vacancy);
        hash = 71 * hash + Objects.hashCode(this.employer);
        hash = 71 * hash + Objects.hashCode(this.employee);
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
        if (!Objects.equals(this.vacancy, other.vacancy)) {
            return false;
        }
        if (!Objects.equals(this.employer, other.employer)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OfferBid{" + "employeeSigned=" + employeeSigned + ", employerSigned=" + employerSigned + ", vacancy=" + vacancy + ", employer=" + employer + ", employee=" + employee + '}';
    }

}
