/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
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
    private String telephoneNumber;
    
    
    @Column(name = "\"employerUserId\"")
    private Integer employerUserId;


    public Employer() {
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

    public Integer getEmployerUserId() {
        return employerUserId;
    }

    public void setEmployerUserId(Integer employerUserId) {
        this.employerUserId = employerUserId;
    }

    @Override
    public String toString() {
        return super.toString() + "Employer{" + "companyName=" + companyName + ", field=" + field + ", adress=" + adress + ", telephoneNumber=" + telephoneNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.companyName);
        hash = 79 * hash + Objects.hashCode(this.field);
        hash = 79 * hash + Objects.hashCode(this.adress);
        hash = 79 * hash + Objects.hashCode(this.telephoneNumber);
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
        if (!super.equals(obj)) {
            return false;
        }
        final Employer other = (Employer) obj;
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.field, other.field)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.telephoneNumber, other.telephoneNumber)) {
            return false;
        }
        return true;
    }

    

    

}
