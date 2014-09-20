/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
//import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "\"Skill\"")
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s"),
    @NamedQuery(name = "Skill.findBySkillName", query = "SELECT s FROM Skill s WHERE s.skillName = :skillName"),
    @NamedQuery(name = "Skill.findByDescription", query = "SELECT s FROM Skill s WHERE s.description = :description"),
    @NamedQuery(name = "Skill.findByExperience", query = "SELECT s FROM Skill s WHERE s.experience = :experience"),
    @NamedQuery(name = "Skill.findBySkillId", query = "SELECT s FROM Skill s WHERE s.skillId = :skillId")})
public class Skill implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "\"skillName\"")
    private String skillName;
    @Size(max = 100)
    @Column(name = "\"description\"")
    private String description;
    @Column(name = "\"experience\"")
    private BigInteger experience;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"skillId\"")
    private Integer skillId;
//    @JoinColumn(name = "\"vacancyId\"", referencedColumnName = "\"vacancyId\"")
//    @ManyToOne
//    private Vacancy vacancyId;

    public Skill() {
    }

    public Skill(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getExperience() {
        return experience;
    }

    public void setExperience(BigInteger experience) {
        this.experience = experience;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

//    public Vacancy getVacancyId() {
//        return vacancyId;
//    }
//
//    public void setVacancyId(Vacancy vacancyId) {
//        this.vacancyId = vacancyId;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skillId != null ? skillId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skill)) {
            return false;
        }
        Skill other = (Skill) object;
        if ((this.skillId == null && other.skillId != null) || (this.skillId != null && !this.skillId.equals(other.skillId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.training.taranovski.web.project.domain.Skill[ skillId=" + skillId + " ]";
    }
    
}
