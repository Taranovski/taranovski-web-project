/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.BasicSkill;
import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.BasicSkillRepository;
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
public class BasicSkillRepositoryImplementation implements BasicSkillRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Override
    public BasicSkill getById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        BasicSkill skill = null;
        try {
            em.getTransaction().begin();
            skill = em.find(BasicSkill.class, id);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return skill;
    }

    @Override
    public List<BasicSkill> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(BasicSkill something) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(BasicSkill something) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(BasicSkill something) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BasicSkill> getSkillsNotInEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<BasicSkill> list = new LinkedList<>();
        try {
            em.getTransaction().begin();
            
            TypedQuery<BasicSkill> query = em.createNamedQuery("BasicSkill.findSkillsNotInEmployee", BasicSkill.class);
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
    public List<BasicSkill> getSkillsNotInVacancy(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<BasicSkill> list = new LinkedList<>();
        try {
            em.getTransaction().begin();
            
            TypedQuery<BasicSkill> query = em.createNamedQuery("BasicSkill.findSkillsNotInVacancy", BasicSkill.class);
            query.setParameter("vacancy", vacancy);
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

}
