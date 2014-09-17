/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
//import java.math.BigDecimal;
import java.util.Collection;
//import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "Employee")
@PrimaryKeyJoinColumn(name = "employeeId", referencedColumnName = "userId")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.findBySurname", query = "SELECT e FROM Employee e WHERE e.surname = :surname"),
    @NamedQuery(name = "Employee.findByPatronymic", query = "SELECT e FROM Employee e WHERE e.patronymic = :patronymic"),
    @NamedQuery(name = "Employee.findByQualification", query = "SELECT e FROM Employee e WHERE e.qualification = :qualification"),
    @NamedQuery(name = "Employee.findByOccupation", query = "SELECT e FROM Employee e WHERE e.occupation = :occupation"),
    @NamedQuery(name = "Employee.findByEmployeeId", query = "SELECT e FROM Employee e WHERE e.employeeId = :employeeId")})
public class Employee extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "surname")
    private String surname;
    @Size(max = 50)
    @Column(name = "patronymic")
    private String patronymic;
    @Size(max = 50)
    @Column(name = "qualification")
    private String qualification;
    @Size(max = 50)
    @Column(name = "occupation")
    private String occupation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Id
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "employeeId")
//    private BigDecimal employeeId;
    @OneToMany(mappedBy = "employeeId")
    private Collection<Skill> skillCollection;
//    @JoinColumn(name = "userId", referencedColumnName = "USER_ID")
//    @ManyToOne(optional = false)
//    private User userId;
    @JoinColumn(name = "checkDocumentId", referencedColumnName = "checkDocumentId")
    @ManyToOne
    private CheckDocument checkDocumentId;

    public Employee() {
    }

//    public Employee(BigDecimal employeeId) {
//        this.employeeId = employeeId;
//    }
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

//    public BigDecimal getEmployeeId() {
//        return employeeId;
//    }
//
//    public void setEmployeeId(BigDecimal employeeId) {
//        this.employeeId = employeeId;
//    }
    public Collection<Skill> getSkillCollection() {
        return skillCollection;
    }

    public void setSkillCollection(Collection<Skill> skillCollection) {
        this.skillCollection = skillCollection;
    }

//    public User getUserId() {
//        return userId;
//    }
//    public void setUserId(User userId) {
//        this.userId = userId;
//    }

    public CheckDocument getCheckDocumentId() {
        return checkDocumentId;
    }

    public void setCheckDocumentId(CheckDocument checkDocumentId) {
        this.checkDocumentId = checkDocumentId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        return "Employee{" + "name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", qualification=" + qualification + ", occupation=" + occupation + '}';
    }

}
