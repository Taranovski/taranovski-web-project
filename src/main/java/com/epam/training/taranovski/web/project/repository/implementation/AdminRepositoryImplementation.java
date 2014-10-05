/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.Admin;
import com.epam.training.taranovski.web.project.repository.AdminRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alyx
 */
@Repository
public class AdminRepositoryImplementation implements AdminRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public AdminRepositoryImplementation() {
    }

    @Autowired
    public AdminRepositoryImplementation(EntityManagerFactory emf) {
        this.entityManagerFactory = emf;
    }

    @Override
    public Admin getByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin getById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Admin admin;
        try {
            em.getTransaction().begin();
            admin = em.find(Admin.class, id);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return admin;
    }

    @Override
    public List<Admin> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Admin admin) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

}
