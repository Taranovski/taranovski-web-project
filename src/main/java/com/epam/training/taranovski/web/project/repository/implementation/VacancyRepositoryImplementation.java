/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.VacancySkill;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.VacancyRepository;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alyx
 */
@Repository
public class VacancyRepositoryImplementation implements VacancyRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Override
    public boolean addSkill(Vacancy vacancy, VacancySkill skill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeSkill(Vacancy vacancy, VacancySkill skill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean clearSkills(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<VacancySkill> list = new LinkedList<>();
        boolean success = true;
        try {
            em.getTransaction().begin();
            
            TypedQuery<VacancySkill> query = em.createNamedQuery("VacancySkill.findByVacancy", VacancySkill.class);
            query.setParameter("vacancy", vacancy);
            list = query.getResultList();
            
            for (VacancySkill vacancySkill : list) {
                em.remove(vacancySkill);
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
    public List<VacancySkill> getSkills(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<VacancySkill> list = new LinkedList<>();
        try {
            em.getTransaction().begin();
            
            TypedQuery<VacancySkill> query = em.createNamedQuery("VacancySkill.findByVacancy", VacancySkill.class);
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

    @Override
    public Vacancy getById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Vacancy vacancy;
        try {
            em.getTransaction().begin();
            vacancy = em.find(Vacancy.class, id);
            em.getTransaction().commit();
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return vacancy;
    }

    @Override
    public List<Vacancy> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Vacancy> list = new LinkedList<>();
        try {
            em.getTransaction().begin();
            
            TypedQuery<Vacancy> query = em.createNamedQuery("Vacancy.findAll", Vacancy.class);
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
    public boolean create(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();
            em.persist(vacancy);
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
    public boolean update(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();
            em.merge(vacancy);
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
    public boolean delete(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        Vacancy managedVacancy = null;
        try {
            em.getTransaction().begin();
            managedVacancy = em.merge(vacancy);
            em.remove(managedVacancy);
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
    public List<Employee> getAppropriateAvailableEmployees(Vacancy vacancy) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Integer> list = new LinkedList<>();
        List<Employee> list1 = new LinkedList<>();

        try {
            em.getTransaction().begin();

            Query query = em.createNativeQuery("select \"employeeId\" from (select \"employeeId\", \"vacancyId\" as \"ide\", count(*) as cou from (select \"UserSkill\".\"employeeId\", \"VacancySkill\".\"vacancyId\", \"UserSkill\".\"experience\", \"VacancySkill\".\"experience\" from \"UserSkill\" join \"VacancySkill\" on \"UserSkill\".\"allSkillsId\" = \"VacancySkill\".\"allSkillsId\" where \"UserSkill\".\"experience\" >= \"VacancySkill\".\"experience\" and \"VacancySkill\".\"vacancyId\" = ?) group by \"employeeId\", \"vacancyId\") where cou = (select count(*) from \"VacancySkill\" where \"VacancySkill\".\"vacancyId\" = \"ide\")");
            query.setParameter(1, vacancy.getVacancyId());
            list = query.getResultList();
            if (list.isEmpty()) {
                list.add(0);
            }
            
            TypedQuery<Employee> query1 = em.createNamedQuery("Employee.findAmongIdsFreeEmployees", Employee.class);
            query1.setParameter("employeeIdList", list);
            list1 = query1.getResultList();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            System.out.println(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return list1;
    }

}
