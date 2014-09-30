/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain1;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "Employer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employer.findAll", query = "SELECT e FROM Employer e"),
    @NamedQuery(name = "Employer.findByEmployerUserId", query = "SELECT e FROM Employer e WHERE e.employerUserId = :employerUserId"),
    @NamedQuery(name = "Employer.findByCompanyName", query = "SELECT e FROM Employer e WHERE e.companyName = :companyName"),
    @NamedQuery(name = "Employer.findByField", query = "SELECT e FROM Employer e WHERE e.field = :field"),
    @NamedQuery(name = "Employer.findByAdress", query = "SELECT e FROM Employer e WHERE e.adress = :adress"),
    @NamedQuery(name = "Employer.findByTelephoneNumber", query = "SELECT e FROM Employer e WHERE e.telephoneNumber = :telephoneNumber")})
public class Employer implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "employerUserId")
    private BigDecimal employerUserId;
    @Size(max = 50)
    @Column(name = "companyName")
    private String companyName;
    @Size(max = 50)
    @Column(name = "field")
    private String field;
    @Size(max = 256)
    @Column(name = "adress")
    private String adress;
    @Size(max = 12)
    @Column(name = "telephoneNumber")
    private String telephoneNumber;
    @OneToMany(mappedBy = "employerId")
    private Collection<Vacancy> vacancyCollection;
    @OneToMany(mappedBy = "offerEmployerId")
    private Collection<OfferBid> offerBidCollection;
    @JoinColumn(name = "employerUserId", referencedColumnName = "userId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Employer() {
    }

    public Employer(BigDecimal employerUserId) {
        this.employerUserId = employerUserId;
    }

    public BigDecimal getEmployerUserId() {
        return employerUserId;
    }

    public void setEmployerUserId(BigDecimal employerUserId) {
        this.employerUserId = employerUserId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @XmlTransient
    public Collection<Vacancy> getVacancyCollection() {
        return vacancyCollection;
    }

    public void setVacancyCollection(Collection<Vacancy> vacancyCollection) {
        this.vacancyCollection = vacancyCollection;
    }

    @XmlTransient
    public Collection<OfferBid> getOfferBidCollection() {
        return offerBidCollection;
    }

    public void setOfferBidCollection(Collection<OfferBid> offerBidCollection) {
        this.offerBidCollection = offerBidCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employerUserId != null ? employerUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employer)) {
            return false;
        }
        Employer other = (Employer) object;
        if ((this.employerUserId == null && other.employerUserId != null) || (this.employerUserId != null && !this.employerUserId.equals(other.employerUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.training.taranovski.web.project.domain1.Employer[ employerUserId=" + employerUserId + " ]";
    }
    
}
