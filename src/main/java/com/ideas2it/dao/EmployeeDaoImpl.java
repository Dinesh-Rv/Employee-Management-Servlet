package com.ideas2it.dao;

import com.ideas2it.model.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction; 
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;
import java.util.ArrayList;

/**
 * EmployeeDaoImpl.java
 * <p>
 * contains methods for data access for the Employee Crud operations
 * </p>
 * @author Dinesh Ravikumar
 * @modified 21-09-2022
 */
public class EmployeeDaoImpl implements EmployeeDao {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public String insertEmployee(Employee employee) {
        Session session = null;
        String employeeId = null;
        try { 
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            employeeId = (String)session.save(employee);
            transact.commit();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }          
        }
        return employeeId;
    }

    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            employees = session.createQuery("FROM Employee WHERE deleted = 0").list();
            transact.commit();
        } catch (HibernateException ignored) {

        } finally {
            if(session != null) {
                session.close();
            }
        }
        return employees;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            session.update(employee);
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
    public int removeEmployee(Employee employee) {
        Session session = null;
        String employeePhoneNumber;
        int recordNumber = 0;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            Query query = session.createQuery("UPDATE Employee set deleted = 1 WHERE employeeId = :employeeId");
            query.setParameter("employeeId", employee.getEmployeeId());
            recordNumber = query.executeUpdate();
            transact.commit();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }          
        }
        return recordNumber;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Session session = null;
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, employeeId);
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }         
        } 
        return employee;
    } 
    
    @Override
    public String getEmployeeId() {
        Session session = null;
        String id = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("select max(employeeId) from Employee");
            id = (String)query.getSingleResult();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }          
        }     
        return id;
    }

    @Override
    public String getEmployeePhoneNumber(String userPhoneNumber){
        Session session = null;
        String phoneNumber = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT employeePhoneNumber from Employee WHERE employeePhoneNumber = :phoneNumber");
            query.setParameter("phoneNumber", userPhoneNumber);
            phoneNumber = (String)query.getSingleResultOrNull();

        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return phoneNumber;
    }

    @Override
    public String getEmployeeEmail(String userEmail){
        Session session = null;
        String emailId = null;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("SELECT employeeEmail from Employee WHERE employeeEmail = :emailId");
            query.setParameter("emailId", userEmail);
            emailId = (String)query.getSingleResultOrNull();

        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return emailId;
    }
}