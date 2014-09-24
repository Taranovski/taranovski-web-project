/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.CheckDocument;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
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
public class EmployeeRepositoryImplementation implements EmployeeRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Employee getByCredentials(String firstName, String lastName, String patronymic) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addSkill(Employee employee, UserSkill skill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeSkill(Employee employee, UserSkill skill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean clearSkills(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<UserSkill> list = new LinkedList<>();
        boolean success = true;
        try {
            em.getTransaction().begin();
            
            TypedQuery<UserSkill> query = em.createNamedQuery("UserSkill.findByEmployee", UserSkill.class);
            query.setParameter("employee", employee);
            list = query.getResultList();
            
            for (UserSkill userSkill : list) {
                em.remove(userSkill);
            }
            
            em.getTransaction().commit();
            success = true;
        } catch (RuntimeException e) {
            System.out.println(e);
            success = false;
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
    public List<UserSkill> getSkills(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<UserSkill> list = new LinkedList<>();
        try {
            em.getTransaction().begin();
            
            TypedQuery<UserSkill> query = em.createNamedQuery("UserSkill.findByEmployee", UserSkill.class);
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
    public CheckDocument getCheckDocument(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Employee admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();

            em.merge(employee);
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
    public boolean delete(Employee admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
