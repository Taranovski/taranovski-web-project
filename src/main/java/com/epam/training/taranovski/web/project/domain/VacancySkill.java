/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "\"VacancySkill\"")
@NamedQueries({
    @NamedQuery(name = "VacancySkill.findAll", query = "SELECT u FROM VacancySkill u"),
    @NamedQuery(name = "VacancySkill.findByExperience", query = "SELECT u FROM VacancySkill u WHERE u.experience = :experience"),
    @NamedQuery(name = "VacancySkill.findBySkillId", query = "SELECT u FROM VacancySkill u WHERE u.skillId = :skillId"),
    @NamedQuery(name = "VacancySkill.findByVacancy", query = "SELECT u FROM VacancySkill u WHERE u.vacancy = :vacancy"),
    @NamedQuery(name = "VacancySkill.clearSkillsForVacancy", query = "delete FROM VacancySkill u WHERE u.vacancy = :vacancy")
})

public class VacancySkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "vacancySkillSequence", sequenceName = "\"VacancySkillSequence\"", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacancySkillSequence")
    @Column(name = "\"skillId\"")
    private Integer skillId;

    @Column(name = "\"experience\"")
    private Integer experience;

//    @Column()
    @OneToOne
    @JoinColumn(name = "\"allSkillsId\"", referencedColumnName = "\"allSkillsId\"")
    private BasicSkill skill;

//    @Column
    @JoinColumn(name = "\"vacancyId\"", referencedColumnName = "\"vacancyId\"")
    @ManyToOne
    private Vacancy vacancy;

    public VacancySkill() {
    }

    public VacancySkill(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public BasicSkill getSkill() {
        return skill;
    }

    public void setSkill(BasicSkill skill) {
        this.skill = skill;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.experience);
        hash = 79 * hash + Objects.hashCode(this.skill);
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
        final VacancySkill other = (VacancySkill) obj;
        if (!Objects.equals(this.experience, other.experience)) {
            return false;
        }
        if (!Objects.equals(this.skill, other.skill)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "VacancySkill{" + "experience=" + experience + ", skill=" + skill + '}';
    }

}
