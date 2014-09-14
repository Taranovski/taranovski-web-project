/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "CheckDocument")
@NamedQueries({
    @NamedQuery(name = "CheckDocument.findAll", query = "SELECT c FROM CheckDocument c"),
    @NamedQuery(name = "CheckDocument.findByCommissions", query = "SELECT c FROM CheckDocument c WHERE c.commissions = :commissions"),
    @NamedQuery(name = "CheckDocument.findByCheckDocumentId", query = "SELECT c FROM CheckDocument c WHERE c.checkDocumentId = :checkDocumentId")})
public class CheckDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "commissions")
    private BigDecimal commissions;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "checkDocumentId")
    private BigDecimal checkDocumentId;
    @OneToMany(mappedBy = "checkDocumentId")
    private Collection<Vacancy> vacancyCollection;
    @OneToMany(mappedBy = "checkDocumentId")
    private Collection<Employee> employeeCollection;
    @JoinColumn(name = "employerId", referencedColumnName = "employerId")
    @ManyToOne(optional = false)
    private Employer employerId;

    public CheckDocument() {
    }

    public CheckDocument(BigDecimal checkDocumentId) {
        this.checkDocumentId = checkDocumentId;
    }

    public BigDecimal getCommissions() {
        return commissions;
    }

    public void setCommissions(BigDecimal commissions) {
        this.commissions = commissions;
    }

    public BigDecimal getCheckDocumentId() {
        return checkDocumentId;
    }

    public void setCheckDocumentId(BigDecimal checkDocumentId) {
        this.checkDocumentId = checkDocumentId;
    }

    public Collection<Vacancy> getVacancyCollection() {
        return vacancyCollection;
    }

    public void setVacancyCollection(Collection<Vacancy> vacancyCollection) {
        this.vacancyCollection = vacancyCollection;
    }

    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    public Employer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Employer employerId) {
        this.employerId = employerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (checkDocumentId != null ? checkDocumentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckDocument)) {
            return false;
        }
        CheckDocument other = (CheckDocument) object;
        if ((this.checkDocumentId == null && other.checkDocumentId != null) || (this.checkDocumentId != null && !this.checkDocumentId.equals(other.checkDocumentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.training.taranovski.spring.domain1.CheckDocument[ checkDocumentId=" + checkDocumentId + " ]";
    }
    
}
