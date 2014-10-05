/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.repository.UserSkillRepository;
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
public class UserSkillRepositoryImplementation implements UserSkillRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public UserSkill getById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        UserSkill userSkill;
        try {
            em.getTransaction().begin();
            userSkill = em.find(UserSkill.class, id);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return userSkill;
    }

    @Override
    public List<UserSkill> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(UserSkill userSkill) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();
            em.persist(userSkill);
            em.getTransaction().commit();
            success = true;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                success = false;
            }
            em.close();
        }

        return success;
    }

    @Override
    public boolean update(UserSkill userSkill) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();
            em.merge(userSkill);
            em.getTransaction().commit();
            success = true;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                success = false;
            }
            em.close();
        }

        return success;
    }

    @Override
    public boolean delete(UserSkill userSkill) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        UserSkill managedUserSkill = null;
        try {
            em.getTransaction().begin();
            managedUserSkill = em.merge(userSkill);
            em.remove(managedUserSkill);
            em.getTransaction().commit();

            success = true;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                success = false;
            }
            em.close();
        }

        return success;
    }

}
