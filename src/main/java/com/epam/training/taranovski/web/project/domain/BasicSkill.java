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
@Table(name = "\"AllSkills\"")
@NamedQueries({
    @NamedQuery(name = "BasicSkill.findSkillsNotInEmployee", 
            query = "select b from BasicSkill b where b not in (SELECT u.skill FROM UserSkill u where u.employee = :employee)")
})
public class BasicSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "allSkillsSequence", sequenceName = "\"AllSkillsSequence\"", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allSkillsSequence")
    
    @Column(name = "\"allSkillsId\"")
    private Integer skillId;

    @Size(max = 50)
    @Column(name = "\"skillName\"")
    private String skillName;
    
    @Size(max = 100)
    @Column(name = "\"description\"")
    private String description;

    public BasicSkill() {
    }

    public BasicSkill(Integer skillId) {
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

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.skillName);
        hash = 47 * hash + Objects.hashCode(this.description);
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
        final BasicSkill other = (BasicSkill) obj;
        if (!Objects.equals(this.skillName, other.skillName)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Skill{" + "skillName=" + skillName + ", description=" + description + '}';
    }

}
