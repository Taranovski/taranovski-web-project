/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "\"UserSkill\"")
@NamedQueries({
    @NamedQuery(name = "Skill.findAll", query = "SELECT s FROM Skill s"),
    @NamedQuery(name = "Skill.findBySkillName", query = "SELECT s FROM Skill s WHERE s.skillName = :skillName"),
    @NamedQuery(name = "Skill.findByDescription", query = "SELECT s FROM Skill s WHERE s.description = :description"),
    @NamedQuery(name = "Skill.findByExperience", query = "SELECT s FROM Skill s WHERE s.experience = :experience"),
    @NamedQuery(name = "Skill.findBySkillId", query = "SELECT s FROM Skill s WHERE s.skillId = :skillId")})
public class UserSkill implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"skillId\"")
    private Integer skillId;

    @OneToOne
    @JoinColumn(name = "\"allSkillsId\"", referencedColumnName = "\"allSkillsId\"")
    private Skill skill;

    @Column(name = "\"experience\"")
    @Basic
    private Integer experience;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    public UserSkill() {
    }

    public UserSkill(Integer skillId) {
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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

}