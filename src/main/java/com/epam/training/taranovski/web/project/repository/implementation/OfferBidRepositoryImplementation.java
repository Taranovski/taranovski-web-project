/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.OfferBid;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.OfferBidRepository;
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
public class OfferBidRepositoryImplementation implements OfferBidRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public OfferBid getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OfferBid> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(OfferBid something) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = false;

        try {
            em.getTransaction().begin();
            em.persist(something);
            em.getTransaction().commit();
            success = true;
        } catch (RuntimeException e) {
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
    public boolean update(OfferBid something) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = false;

        try {
            em.getTransaction().begin();
            em.merge(something);
            em.getTransaction().commit();
            success = true;
        } catch (RuntimeException e) {
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
    public boolean delete(OfferBid something) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        OfferBid managedManagedOfferBid = null;
        try {
            em.getTransaction().begin();
            managedManagedOfferBid = em.merge(something);
            em.remove(managedManagedOfferBid);
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
    public List<Vacancy> getOffersForEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Vacancy> list = new LinkedList<>();
        try {
            em.getTransaction().begin();

            TypedQuery<Vacancy> query = em.createNamedQuery("OfferBid.findVacancyOffersForEmployee", Vacancy.class);
            query.setParameter("employee", employee);
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
    public OfferBid getOfferByEmployeeAndVacancy(Employee employee, Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        OfferBid offer = null;
        try {
            em.getTransaction().begin();

            TypedQuery<OfferBid> query = em.createNamedQuery("OfferBid.findOfferByEmployeeAndVacancy", OfferBid.class);
            query.setParameter("employee", employee);
            query.setParameter("vacancy", vacancy);
            offer = query.getSingleResult();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            offer = null;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return offer;
    }

    @Override
    public List<Vacancy> getBidsForEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Vacancy> list = new LinkedList<>();
        try {
            em.getTransaction().begin();

            TypedQuery<Vacancy> query = em.createNamedQuery("OfferBid.findVacancyBidsForEmployee", Vacancy.class);
            query.setParameter("employee", employee);
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
    public OfferBid getBidByEmployeeAndVacancy(Employee employee, Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        OfferBid offer = null;
        try {
            em.getTransaction().begin();

            TypedQuery<OfferBid> query = em.createNamedQuery("OfferBid.findBidByEmployeeAndVacancy", OfferBid.class);
            query.setParameter("employee", employee);
            query.setParameter("vacancy", vacancy);
            offer = query.getSingleResult();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            offer = null;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return offer;
    }

}
