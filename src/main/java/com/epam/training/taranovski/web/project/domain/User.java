/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.training.taranovski.web.project.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alyx
 */
@Entity
@Table(name = "\"User\"")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserLogin", query = "SELECT u FROM User u WHERE u.userLogin = :userLogin"),
    @NamedQuery(name = "User.findByUserPassword", query = "SELECT u FROM User u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "User.findByUserType", query = "SELECT u FROM User u WHERE u.userType = :userType"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserTypeId", query = "SELECT u FROM User u WHERE u.userTypeId = :userTypeId")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 25)
    @Column(name = "USER_LOGIN")
    private String userLogin;
    @Size(max = 25)
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Size(max = 25)
    @Column(name = "USER_TYPE")
    private String userType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "USER_TYPE_ID")
    private Integer userTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Employer> employerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Employee> employeeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminUserId")
    private Collection<Admin> adminCollection;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTyprId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Collection<Employer> getEmployerCollection() {
        return employerCollection;
    }

    public void setEmployerCollection(Collection<Employer> employerCollection) {
        this.employerCollection = employerCollection;
    }

    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    public Collection<Admin> getAdminCollection() {
        return adminCollection;
    }

    public void setAdminCollection(Collection<Admin> adminCollection) {
        this.adminCollection = adminCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epam.training.taranovski.spring.domain1.User[ userId=" + userId + " ]";
    }
    
}
