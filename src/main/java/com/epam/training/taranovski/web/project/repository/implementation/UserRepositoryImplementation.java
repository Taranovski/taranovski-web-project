/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.Admin;
import com.epam.training.taranovski.web.project.domain.User;
import com.epam.training.taranovski.web.project.repository.UserRepository;
import com.epam.training.taranovski.web.project.service.EncryptionService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alyx
 */
@Repository
public class UserRepositoryImplementation implements UserRepository {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public String getTypeOf(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(User admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(User admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getByNameAndPassword(String name, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        User user = null;
        boolean exists = false;

        try {
            em.getTransaction().begin();

            user = em.find(Admin.class, 1);
            
//            if (user instanceof Admin) {
//                user = (Admin) user;
//            }
            
//            TypedQuery<User> query = em.createNamedQuery("User.findByUserLoginAndPassword", User.class);
//            query.setParameter("name", name);
//            query.setParameter("password", encryptionService.encrypt(password));
//
//            user = query.getSingleResult();

            em.getTransaction().commit();
            exists = true;
        } catch (NoResultException ex) {
            exists = false;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        if (exists) {
            return user;
        } else {
            return null;
        }

    }

    @Override
    public boolean nameExistsInDB(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
