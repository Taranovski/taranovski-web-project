/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.taranovski.web.project.repository.implementation;

import com.epam.training.taranovski.web.project.domain.Employee;
import com.epam.training.taranovski.web.project.domain.UserSkill;
import com.epam.training.taranovski.web.project.domain.Vacancy;
import com.epam.training.taranovski.web.project.repository.EmployeeRepository;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
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
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addSkill(Employee employee, UserSkill skill) {
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeSkill(Employee employee, UserSkill skill) {
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean clearSkills(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        boolean success = true;
        try {
            em.getTransaction().begin();

           Query query = em.createNamedQuery("UserSkill.clearSkillsForEmployee");
            query.setParameter("employee", employee);
            
            em.getTransaction().commit();
            success = true;
        } catch (RuntimeException e) {
            Logger.getLogger(EmployeeRepositoryImplementation.class.getName()).info(e);
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
            Logger.getLogger(EmployeeRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return list;
    }

    @Override
    public Employee getById(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Employee employee = null;
        try {
            em.getTransaction().begin();
            employee = em.find(Employee.class, id);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            Logger.getLogger(EmployeeRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return employee;
    }

    @Override
    public List<Employee> getAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> list = new LinkedList<>();
        try {
            em.getTransaction().begin();

            TypedQuery<Employee> query = em.createNamedQuery("Employee.findAll", Employee.class);
            list = query.getResultList();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            Logger.getLogger(EmployeeRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return list;
    }

    @Override
    public boolean create(Employee admin) {
        throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
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
        } catch (RuntimeException e) {
            Logger.getLogger(EmployeeRepositoryImplementation.class.getName()).info(e);
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
        throw new UnsupportedOperationException("Not supported yet."); 
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Employee> getAllFreeEmployees() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Employee> list = new LinkedList<>();
        try {
            em.getTransaction().begin();

            TypedQuery<Employee> query = em.createNamedQuery("Employee.findAllFreeEmployees", Employee.class);
            list = query.getResultList();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            Logger.getLogger(EmployeeRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return list;
    }

    @Override
    public List<Vacancy> getAvailableActiveVacancies(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Integer> list = null;
        List<Vacancy> list1 = null;

        try {
            em.getTransaction().begin();

            Query query = em.createNativeQuery("select \"ide\" from (select \"UserSkill\".\"employeeId\", \"UserSkill\".\"experience\", \"VacancySkill\".\"vacancyId\" as \"ide\", \"VacancySkill\".\"experience\",  \"UserSkill\".\"allSkillsId\" from \"UserSkill\" join \"VacancySkill\" on \"UserSkill\".\"allSkillsId\" = \"VacancySkill\".\"allSkillsId\" where \"UserSkill\".\"employeeId\" = ? and \"UserSkill\".\"experience\" >= \"VacancySkill\".\"experience\") group by \"employeeId\", \"ide\" having count(\"employeeId\") >= (select count(*) from \"VacancySkill\" where \"VacancySkill\".\"vacancyId\" = \"ide\")");
            query.setParameter(1, employee.getEmployeeUserId());
            list = query.getResultList();
            if (list.isEmpty()) {
                list.add(0);
            }

            TypedQuery<Vacancy> query1 = em.createNamedQuery("Vacancy.findActiveVacanciesByIds", Vacancy.class);
            query1.setParameter("vacancyIdList", list);
            list1 = query1.getResultList();

            em.getTransaction().commit();
        } catch (RuntimeException e) {
            Logger.getLogger(EmployeeRepositoryImplementation.class.getName()).info(e);
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        return list1;
    }

}
