/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import java.util.Objects;
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
    @NamedQuery(name = "Employee.findAllFreeEmployees", query = "SELECT e FROM Employee e where e.status = 'free'"),
    @NamedQuery(name = "Employee.findByName", query = "SELECT e FROM Employee e WHERE e.name = :name"),
    @NamedQuery(name = "Employee.findBySurname", query = "SELECT e FROM Employee e WHERE e.surname = :surname"),
    @NamedQuery(name = "Employee.findByPatronymic", query = "SELECT e FROM Employee e WHERE e.patronymic = :patronymic"),
    @NamedQuery(name = "Employee.findByQualification", query = "SELECT e FROM Employee e WHERE e.qualification = :qualification"),
    @NamedQuery(name = "Employee.findByOccupation", query = "SELECT e FROM Employee e WHERE e.occupation = :occupation"),
    @NamedQuery(name = "Employee.findAmongIdsFreeEmployees", query = "SELECT e FROM Employee e WHERE e.employeeUserId in :employeeIdList and e.status = 'free'")
})
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

    @Size(max = 20)
    @Column(name = "\"status\"")
    private String status;

    @Column(name = "\"employeeUserId\"")
    private Integer employeeUserId;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.surname);
        hash = 59 * hash + Objects.hashCode(this.patronymic);
        hash = 59 * hash + Objects.hashCode(this.qualification);
        hash = 59 * hash + Objects.hashCode(this.occupation);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.patronymic, other.patronymic)) {
            return false;
        }
        if (!Objects.equals(this.qualification, other.qualification)) {
            return false;
        }
        if (!Objects.equals(this.occupation, other.occupation)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return super.toString() + "Employee{" + "name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", qualification=" + qualification + ", occupation=" + occupation + '}';
    }

}
