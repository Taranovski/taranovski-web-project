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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "Vacancy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacancy.findAll", query = "SELECT v FROM Vacancy v"),
    @NamedQuery(name = "Vacancy.findByPosition", query = "SELECT v FROM Vacancy v WHERE v.position = :position"),
    @NamedQuery(name = "Vacancy.findBySalary", query = "SELECT v FROM Vacancy v WHERE v.salary = :salary"),
    @NamedQuery(name = "Vacancy.findByDescription", query = "SELECT v FROM Vacancy v WHERE v.description = :description"),
    @NamedQuery(name = "Vacancy.findByVacancyId", query = "SELECT v FROM Vacancy v WHERE v.vacancyId = :vacancyId")})
public class Vacancy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "position")
    private String position;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salary")
    private BigDecimal salary;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "vacancyId")
    private BigDecimal vacancyId;
    @JoinColumn(name = "employerId", referencedColumnName = "employerUserId")
    @ManyToOne
    private Employer employerId;
    @OneToMany(mappedBy = "vacancyId")
    private Collection<OfferBid> offerBidCollection;

    public Vacancy() {
    }

    public Vacancy(BigDecimal vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(BigDecimal vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Employer getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Employer employerId) {
        this.employerId = employerId;
    }

    @XmlTransient
    public Collection<OfferBid> getOfferBidCollection() {
        return offerBidCollection;
    }

    public void setOfferBidCollection(Collection<OfferBid> offerBidCollection) {
        this.offerBidCollection = offerBidCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacancyId != null ? vacancyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacancy)) {
            return false;
        }
        Vacancy other = (Vacancy) object;
        if ((this.vacancyId == null && other.vacancyId != null) || (this.vacancyId != null && !this.vacancyId.equals(other.vacancyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.training.taranovski.web.project.domain1.Vacancy[ vacancyId=" + vacancyId + " ]";
    }
    
}
