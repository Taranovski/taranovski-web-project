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
@Table(name = "\"Admin\"")
@DiscriminatorValue("admin")
@PrimaryKeyJoinColumn(name = "\"adminUserId\"", referencedColumnName = "\"userId\"")
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findByAdminName", query = "SELECT a FROM Admin a WHERE a.adminName = :adminName")})
public class Admin extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "\"name\"")
    private String adminName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Id
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "\"adminUserId\"")
    private Integer adminUserId;
//    @JoinColumn(name = "ADMIN_USER_ID", referencedColumnName = "USER_ID")
//    @ManyToOne(optional = false)
//    private User adminUserId;

    public Admin() {
    }

//    public Admin(Integer adminUserId) {
//        this.adminUserId = adminUserId;
//    }
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }
//    public User getAdminUserId() {
//        return adminUserId;
//    }
//
//    public void setAdminUserId(User adminUserId) {
//        this.adminUserId = adminUserId;
//    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + (this.adminName != null ? this.adminName.hashCode() : 0);
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
        if (!super.equals(obj)) {
            return false;
        }
        final Admin other = (Admin) obj;
        if ((this.adminName == null) ? (other.adminName != null) : !this.adminName.equals(other.adminName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Admin{" + "adminName=" + adminName + '}';
    }

}
