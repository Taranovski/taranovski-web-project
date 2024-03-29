/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.CheckDocumentRepository;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alyx
 */
@Repository
public class CheckDocumentRepositoryImplementation implements CheckDocumentRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public CheckDocument getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckDocument> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(CheckDocument something) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = false;

        try {
            em.getTransaction().begin();
            em.persist(something);
            em.getTransaction().commit();
            success = true;
        } catch (RuntimeException e) {
            Logger.getLogger(BasicSkillRepositoryImplementation.class.getName()).info(e);
            success = false;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return success;
    }

    @Override
    public boolean update(CheckDocument admin) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(CheckDocument admin) {
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CheckDocument findByEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();

        CheckDocument checkDocument = null;
        try {
            em.getTransaction().begin();

            TypedQuery<CheckDocument> query = em.createNamedQuery("CheckDocument.findByEmployee", CheckDocument.class);
            query.setParameter("employee", employee);
            checkDocument = query.getSingleResult();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            Logger.getLogger(BasicSkillRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return checkDocument;
    }

    @Override
    public CheckDocument findByVacancy(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();

        CheckDocument checkDocument = null;
        try {
            em.getTransaction().begin();

            TypedQuery<CheckDocument> query = em.createNamedQuery("CheckDocument.findByVacancy", CheckDocument.class);
            query.setParameter("vacancy", vacancy);
            checkDocument = query.getSingleResult();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            Logger.getLogger(BasicSkillRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return checkDocument;
    }

    @Override
    public List<CheckDocument> findAllForEmployer(Employer employer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<CheckDocument> list = new LinkedList<>();
        try {
            em.getTransaction().begin();

            TypedQuery<CheckDocument> query = em.createNamedQuery("CheckDocument.findAllForEmployer", CheckDocument.class);
            query.setParameter("employer", employer);
            list = query.getResultList();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            Logger.getLogger(BasicSkillRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return list;
    }

}
