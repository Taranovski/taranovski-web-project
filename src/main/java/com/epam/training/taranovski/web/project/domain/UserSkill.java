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
@Table(name = "\"UserSkill\"")
@NamedQueries({
    @NamedQuery(name = "UserSkill.findAll", query = "SELECT u FROM UserSkill u"),
    @NamedQuery(name = "UserSkill.findByExperience", query = "SELECT u FROM UserSkill u WHERE u.experience = :experience"),
    @NamedQuery(name = "UserSkill.findBySkillId", query = "SELECT u FROM UserSkill u WHERE u.skillId = :skillId"),
    @NamedQuery(name = "UserSkill.findByEmployee", query = "SELECT u FROM UserSkill u WHERE u.employee = :employee")})
public class UserSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "userSkillSequence", sequenceName = "\"UserSkillSequence\"", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSkillSequence")
    
    @Column(name = "\"skillId\"")
    private Integer skillId;

    @Column(name = "\"experience\"")
    private Integer experience;

//    @Column()
    @OneToOne
    @JoinColumn(name = "\"allSkillsId\"", referencedColumnName = "\"allSkillsId\"")
    private BasicSkill skill;

//    @Column
    @JoinColumn(name = "\"employeeId\"", referencedColumnName = "\"employeeUserId\"")
    @ManyToOne
    private Employee employee;

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

    public BasicSkill getSkill() {
        return skill;
    }

    public void setSkill(BasicSkill skill) {
        this.skill = skill;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeid) {
        this.employee = employeeid;
    }

}
