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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @NamedQuery(name = "CheckDocument.findByCheckDocumentId", query = "SELECT c FROM CheckDocument c WHERE c.checkDocumentId = :checkDocumentId")})
public class CheckDocument implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "\"commissions\"")
    private BigDecimal commissions;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"checkDocumentId\"")
    private Integer checkDocumentId;
//    @OneToOne//(mappedBy = "vacancyId")
////    @JoinColumn(name = "", referencedColumnName = "vacancyId")
//    private Vacancy vacancy;

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

//    public Vacancy getVacancy() {
//        return vacancy;
//    }
//
//    public void setVacancy(Vacancy vacancy) {
//        this.vacancy = vacancy;
//    }

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
        return "com.epam.training.taranovski.web.project.domain.CheckDocument[ checkDocumentId=" + checkDocumentId + " ]";
    }
    
}
