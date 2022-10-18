package com.ideas2it.dao;

import java.util.ArrayList;
import java.util.List;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProjects;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class EmployeeProjectsDaoImpl implements EmployeeProjectsDao {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    @Override
    public boolean addEmployeeProject(EmployeeProjects employeeProject, Employee employee) {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        employeeProject.setEmployee(employees);
        Session session = null;
        int projectId = 0;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            projectId = (Integer) session.save(employeeProject);
            transact.commit();
            return true;
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public List<EmployeeProjects> getEmployeeProject(String employeeId) {
        List<EmployeeProjects> employeeProject = new ArrayList<EmployeeProjects>();
        Session session = null;
        int recordNumber = 0;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM EmployeeProjects p LEFT JOIN p.employee e where e.employeeId = :employeeId");
            query.setParameter("employeeId", employeeId);
            employeeProject = query.list();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return employeeProject;
    }

    @Override
    public List<EmployeeProjects> getEmployeeProjectById(String employeeId) {
        List<EmployeeProjects> employeeProject = new ArrayList<EmployeeProjects>();
        Session session = null;
        int recordNumber = 0;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM EmployeeProjects p WHERE p.projectId ="
                                             + "(SELECT p.id FROM p.employee e WHERE e.employeeId = :employeeId)");
            query.setParameter("employeeId", employeeId);
            employeeProject = query.list();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return employeeProject;
    }

    @Override
    public boolean updateEmployeeProjects(EmployeeProjects projects) {

        return true;
    }
}
