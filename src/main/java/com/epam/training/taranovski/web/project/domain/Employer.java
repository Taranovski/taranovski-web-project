/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
//import java.math.Integer;
import java.util.Collection;
//import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "\"Employer\"")
@DiscriminatorValue("employer")
@PrimaryKeyJoinColumn(name = "\"employerUserId\"", referencedColumnName = "\"userId\"")
@NamedQueries({
    @NamedQuery(name = "Employer.findAll", query = "SELECT e FROM Employer e"),
    @NamedQuery(name = "Employer.findByCompanyName", query = "SELECT e FROM Employer e WHERE e.companyName = :companyName"),
    @NamedQuery(name = "Employer.findByField", query = "SELECT e FROM Employer e WHERE e.field = :field"),
    @NamedQuery(name = "Employer.findByAdress", query = "SELECT e FROM Employer e WHERE e.adress = :adress"),
    @NamedQuery(name = "Employer.findByTelephoneNumber", query = "SELECT e FROM Employer e WHERE e.telephoneNumber = :telephoneNumber")})
public class Employer extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "\"companyName\"")
    private String companyName;
    @Size(max = 50)
    @Column(name = "\"field\"")
    private String field;
    @Size(max = 256)
    @Column(name = "\"adress\"")
    private String adress;
    @Column(name = "\"telephoneNumber\"")
    private Long telephoneNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Id
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "\"employerId\"")
    private Integer employerId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "\"vacancyId\"")
    private Collection<Vacancy> vacancyCollection;
//    @JoinColumn(name = "userId", referencedColumnName = "USER_ID")
//    @ManyToOne(optional = false)
//    private User userId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "\"checkDocumentId\"")
    private Collection<CheckDocument> checkDocumentCollection;

    public Employer() {
    }

//    public Employer(Integer employerId) {
//        this.employerId = employerId;
//    }

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

    public Long getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Integer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Integer employerId) {
        this.employerId = employerId;
    }

    public Collection<Vacancy> getVacancyCollection() {
        return vacancyCollection;
    }

    public void setVacancyCollection(Collection<Vacancy> vacancyCollection) {
        this.vacancyCollection = vacancyCollection;
    }

//    public User getUserId() {
//        return userId;
//    }

//    public void setUserId(User userId) {
//        this.userId = userId;
//    }

    public Collection<CheckDocument> getCheckDocumentCollection() {
        return checkDocumentCollection;
    }

    public void setCheckDocumentCollection(Collection<CheckDocument> checkDocumentCollection) {
        this.checkDocumentCollection = checkDocumentCollection;
    }

    @Override
    public String toString() {
        return "Employer{" + "companyName=" + companyName + ", field=" + field + ", adress=" + adress + ", telephoneNumber=" + telephoneNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.companyName != null ? this.companyName.hashCode() : 0);
        hash = 59 * hash + (this.field != null ? this.field.hashCode() : 0);
        hash = 59 * hash + (this.adress != null ? this.adress.hashCode() : 0);
        hash = 59 * hash + (this.telephoneNumber != null ? this.telephoneNumber.hashCode() : 0);
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
        final Employer other = (Employer) obj;
        if ((this.companyName == null) ? (other.companyName != null) : !this.companyName.equals(other.companyName)) {
            return false;
        }
        if ((this.field == null) ? (other.field != null) : !this.field.equals(other.field)) {
            return false;
        }
        if ((this.adress == null) ? (other.adress != null) : !this.adress.equals(other.adress)) {
            return false;
        }
        if (this.telephoneNumber != other.telephoneNumber && (this.telephoneNumber == null || !this.telephoneNumber.equals(other.telephoneNumber))) {
            return false;
        }
        return true;
    }

    
    
}
