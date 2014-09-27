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
@Table(name = "\"Vacancy\"")
@NamedQueries({
    @NamedQuery(name = "Vacancy.findAll", query = "SELECT v FROM Vacancy v"),
    @NamedQuery(name = "Vacancy.findByPosition", query = "SELECT v FROM Vacancy v WHERE v.position = :position"),
    @NamedQuery(name = "Vacancy.findBySalary", query = "SELECT v FROM Vacancy v WHERE v.salary = :salary"),
    @NamedQuery(name = "Vacancy.findByDescription", query = "SELECT v FROM Vacancy v WHERE v.description = :description"),
    @NamedQuery(name = "Vacancy.findByVacancyId", query = "SELECT v FROM Vacancy v WHERE v.vacancyId = :vacancyId"),
    @NamedQuery(name = "Vacancy.findByEmployer", query = "SELECT v FROM Vacancy v WHERE v.employer = :employer")
})
public class Vacancy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(name = "\"position\"")
    private String position;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Column(name = "\"salary\"")
    private BigDecimal salary;

    @Size(max = 100)
    @Column(name = "\"description\"")
    private String description;

    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "vacancySequence", sequenceName = "\"VacancySequence\"", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacancySequence")

    @Column(name = "\"vacancyId\"")
    private Integer vacancyId;

    @JoinColumn(name = "\"employerId\"", referencedColumnName = "\"employerUserId\"")
    @ManyToOne
    private Employer employer;

//    @OneToOne
//    @JoinColumn(referencedColumnName = "\"checkDocumentId\"")
//    private CheckDocument checkDocumentId;
//    @OneToMany
//    @Basic(fetch = FetchType.LAZY)
//    @JoinColumn(referencedColumnName = "\"skillId\"")
//    private Collection<UserSkill> skillCollection;

    public Vacancy() {
    }

    public Vacancy(Integer vacancyId) {
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

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

//    public CheckDocument getCheckDocumentId() {
//        return checkDocumentId;
//    }
//
//    public void setCheckDocumentId(CheckDocument checkDocumentId) {
//        this.checkDocumentId = checkDocumentId;
//    }
//    public Collection<UserSkill> getSkillCollection() {
//        return skillCollection;
//    }
//
//    public void setSkillCollection(Collection<UserSkill> skillCollection) {
//        this.skillCollection = skillCollection;
//    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.position);
        hash = 89 * hash + Objects.hashCode(this.salary);
        hash = 89 * hash + Objects.hashCode(this.description);
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
        final Vacancy other = (Vacancy) obj;
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        if (!Objects.equals(this.salary, other.salary)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vacancy{" + "position=" + position + ", salary=" + salary + ", description=" + description + '}';
    }

}
