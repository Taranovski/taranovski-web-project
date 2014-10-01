/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.CheckDocumentRepository;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckDocument> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(CheckDocument admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(CheckDocument admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(CheckDocument admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            System.out.println(e);
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
            System.out.println(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return checkDocument;
    }

}
