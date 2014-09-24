/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "\"Employee\"")
@DiscriminatorValue("employee")
@PrimaryKeyJoinColumn(name = "\"employeeUserId\"", referencedColumnName = "\"userId\"")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.findBySurname", query = "SELECT e FROM Employee e WHERE e.surname = :surname"),
    @NamedQuery(name = "Employee.findByPatronymic", query = "SELECT e FROM Employee e WHERE e.patronymic = :patronymic"),
    @NamedQuery(name = "Employee.findByQualification", query = "SELECT e FROM Employee e WHERE e.qualification = :qualification"),
    @NamedQuery(name = "Employee.findByOccupation", query = "SELECT e FROM Employee e WHERE e.occupation = :occupation")})
public class Employee extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(name = "\"name\"")
    private String name;

    @Size(max = 50)
    @Column(name = "\"surname\"")
    private String surname;

    @Size(max = 50)
    @Column(name = "\"patronymic\"")
    private String patronymic;

    @Size(max = 50)
    @Column(name = "\"qualification\"")
    private String qualification;

    @Size(max = 50)
    @Column(name = "\"occupation\"")
    private String occupation;

    @Column(name = "\"employeeUserId\"")
    private Integer employeeUserId;

//    @OneToMany//(mappedBy = "\"employeeId\"")
//    @JoinColumn (name = "\"EMPLOYEEID\"")
//    //@Basic(fetch = FetchType.LAZY)
//    private Collection<UserSkill> skillCollection;

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Integer getEmployeeUserId() {
        return employeeUserId;
    }

    public void setEmployeeUserId(Integer employeeUserId) {
        this.employeeUserId = employeeUserId;
    }

//    public Collection<UserSkill> getSkillCollection() {
//        return skillCollection;
//    }
//
//    public void setSkillCollection(Collection<UserSkill> skillCollection) {
//        this.skillCollection = skillCollection;
//    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 83 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 83 * hash + (this.surname != null ? this.surname.hashCode() : 0);
        hash = 83 * hash + (this.patronymic != null ? this.patronymic.hashCode() : 0);
        hash = 83 * hash + (this.qualification != null ? this.qualification.hashCode() : 0);
        hash = 83 * hash + (this.occupation != null ? this.occupation.hashCode() : 0);
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
        final Employee other = (Employee) obj;
        if (!super.equals((User) obj)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.surname == null) ? (other.surname != null) : !this.surname.equals(other.surname)) {
            return false;
        }
        if ((this.patronymic == null) ? (other.patronymic != null) : !this.patronymic.equals(other.patronymic)) {
            return false;
        }
        if ((this.qualification == null) ? (other.qualification != null) : !this.qualification.equals(other.qualification)) {
            return false;
        }
        if ((this.occupation == null) ? (other.occupation != null) : !this.occupation.equals(other.occupation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Employee{" + "name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", qualification=" + qualification + ", occupation=" + occupation + '}';
    }

}
