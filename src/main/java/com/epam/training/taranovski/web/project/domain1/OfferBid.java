/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain1;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "OfferBid")
@NamedQueries({
    @NamedQuery(name = "OfferBid.findAll", query = "SELECT o FROM OfferBid o"),
    @NamedQuery(name = "OfferBid.findByOfferBidId", query = "SELECT o FROM OfferBid o WHERE o.offerBidId = :offerBidId"),
    @NamedQuery(name = "OfferBid.findByEmployeeSigned", query = "SELECT o FROM OfferBid o WHERE o.employeeSigned = :employeeSigned"),
    @NamedQuery(name = "OfferBid.findByEmployerSigned", query = "SELECT o FROM OfferBid o WHERE o.employerSigned = :employerSigned")})
public class OfferBid implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "offerBidId")
    private BigDecimal offerBidId;
    @Size(max = 20)
    @Column(name = "employeeSigned")
    private String employeeSigned;
    @Size(max = 20)
    @Column(name = "employerSigned")
    private String employerSigned;
    @JoinColumn(name = "vacancyId", referencedColumnName = "vacancyId")
    @ManyToOne
    private Vacancy vacancyId;
    @JoinColumn(name = "offerEmployerId", referencedColumnName = "employerUserId")
    @ManyToOne
    private Employer offerEmployerId;
    @JoinColumn(name = "bidEmployeeId", referencedColumnName = "employeeUserId")
    @ManyToOne
    private Employee bidEmployeeId;

    public OfferBid() {
    }

    public OfferBid(BigDecimal offerBidId) {
        this.offerBidId = offerBidId;
    }

    public BigDecimal getOfferBidId() {
        return offerBidId;
    }

    public void setOfferBidId(BigDecimal offerBidId) {
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

    public Vacancy getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Vacancy vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Employer getOfferEmployerId() {
        return offerEmployerId;
    }

    public void setOfferEmployerId(Employer offerEmployerId) {
        this.offerEmployerId = offerEmployerId;
    }

    public Employee getBidEmployeeId() {
        return bidEmployeeId;
    }

    public void setBidEmployeeId(Employee bidEmployeeId) {
        this.bidEmployeeId = bidEmployeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (offerBidId != null ? offerBidId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfferBid)) {
            return false;
        }
        OfferBid other = (OfferBid) object;
        if ((this.offerBidId == null && other.offerBidId != null) || (this.offerBidId != null && !this.offerBidId.equals(other.offerBidId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.training.taranovski.web.project.domain1.OfferBid[ offerBidId=" + offerBidId + " ]";
    }
    
}
