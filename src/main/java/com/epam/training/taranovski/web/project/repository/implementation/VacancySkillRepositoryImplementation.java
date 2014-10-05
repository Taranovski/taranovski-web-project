/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.repository.VacancySkillRepository;
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
public class VacancySkillRepositoryImplementation implements VacancySkillRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Override
    public VacancySkill getById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        VacancySkill vacancySkill;
        try {
            em.getTransaction().begin();
            vacancySkill = em.find(VacancySkill.class, id);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return vacancySkill;
    }

    @Override
    public List<VacancySkill> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(VacancySkill vacancySkill) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();
            em.persist(vacancySkill);
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
    public boolean update(VacancySkill vacancySkill) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();
            em.merge(vacancySkill);
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
    public boolean delete(VacancySkill vacancySkill) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        VacancySkill managedVacancySkill = null;
        try {
            em.getTransaction().begin();
            managedVacancySkill = em.merge(vacancySkill);
            em.remove(managedVacancySkill);
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
