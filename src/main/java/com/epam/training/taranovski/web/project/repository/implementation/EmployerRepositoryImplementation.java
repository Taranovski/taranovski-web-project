/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employer;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.EmployerRepository;
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
public class EmployerRepositoryImplementation implements EmployerRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean addVacancy(Employer employer, Vacancy vacancy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeVacancy(Employer employer, Vacancy vacancy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean clearVacancies(Employer employer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vacancy> getVacancys(Employer employer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Vacancy> list = new LinkedList<>();
        try {
            em.getTransaction().begin();
            
            TypedQuery<Vacancy> query = em.createNamedQuery("Vacancy.findByEmployer", Vacancy.class);
            query.setParameter("employer", employer);
            list = query.getResultList();
            
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return list;
    }

    @Override
    public boolean addCheckDocument(Employer employer, CheckDocument checkDocument) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeCheckDocument(Employer employer, CheckDocument checkDocument) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean clearCheckDocuments(Employer employer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CheckDocument> getCheckDocuments(Employer employer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employer getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employer> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Employer admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Employer employer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();

            em.merge(employer);
            em.flush();

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
    public boolean delete(Employer admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
