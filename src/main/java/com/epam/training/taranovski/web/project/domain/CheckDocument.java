/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "\"CheckDocument\"")
@NamedQueries({
    @NamedQuery(name = "CheckDocument.findAll", query = "SELECT c FROM CheckDocument c"),
    @NamedQuery(name = "CheckDocument.findByCommissions", query = "SELECT c FROM CheckDocument c WHERE c.commissions = :commissions"),
    @NamedQuery(name = "CheckDocument.findByEmployee", query = "SELECT c FROM CheckDocument c WHERE c.employee = :employee"),
    @NamedQuery(name = "CheckDocument.findByVacancy", query = "SELECT c FROM CheckDocument c WHERE c.vacancy = :vacancy"),
    @NamedQuery(name = "CheckDocument.findByCheckDocumentId", query = "SELECT c FROM CheckDocument c WHERE c.checkDocumentId = :checkDocumentId")})
public class CheckDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "checkDocumentSequence", sequenceName = "\"CheckDocumentSequence\"", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checkDocumentSequence")

    @Column(name = "\"checkDocumentId\"")
    private Integer checkDocumentId;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "\"commissions\"")
    private BigDecimal commissions;

    @OneToOne
    @JoinColumn(name = "\"vacancyId\"", referencedColumnName = "\"vacancyId\"")
    private Vacancy vacancy;

    @OneToOne
    @JoinColumn(name = "\"employeeId\"", referencedColumnName = "\"employeeUserId\"")
    private Employee employee;

    public CheckDocument() {
    }

    public CheckDocument(Integer checkDocumentId) {
        this.checkDocumentId = checkDocumentId;
    }

    public BigDecimal getCommissions() {
        return commissions;
    }

    public void setCommissions(BigDecimal commissions) {
        this.commissions = commissions;
    }

    public Integer getCheckDocumentId() {
        return checkDocumentId;
    }

    public void setCheckDocumentId(Integer checkDocumentId) {
        this.checkDocumentId = checkDocumentId;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.commissions);
        hash = 29 * hash + Objects.hashCode(this.vacancy);
        hash = 29 * hash + Objects.hashCode(this.employee);
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
        final CheckDocument other = (CheckDocument) obj;
        if (!Objects.equals(this.commissions, other.commissions)) {
            return false;
        }
        if (!Objects.equals(this.vacancy, other.vacancy)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CheckDocument{" + "commissions=" + commissions + ", vacancy=" + vacancy + ", employee=" + employee + '}';
    }

}
