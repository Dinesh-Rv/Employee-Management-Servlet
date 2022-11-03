package com.ideas2it.dao;

import com.ideas2it.model.LeaveRecords;
import com.ideas2it.enums.LeaveType;
import com.ideas2it.connection.ConnectorInfo;
import com.ideas2it.model.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction; 
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Repository
public class LeaveRecordsDaoImpl implements LeaveRecordsDao {
    
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public int addLeaveRecord(LeaveRecords record) {
        int leaveId = 0;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            leaveId = (Integer)session.save(record);
            transact.commit();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return leaveId;
    }

    @Override
    public List<LeaveRecords> getLeaveRecords(Employee employee) {
        List<LeaveRecords> leaveRecords = new ArrayList<LeaveRecords>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            Query query = session.createQuery("FROM LeaveRecords WHERE deleted = 0 AND employee = :employee");
            query.setParameter("employee", employee);
            leaveRecords = query.list();
            transact.commit();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }          
        }
        return leaveRecords;
    }

    @Override
    public int updateLeaveRecords(LeaveRecords leaveRecord) {
        Session session = null;
        int noOfRowAffected = 0;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            Query query = session.createQuery("UPDATE LeaveRecords SET fromDate = :fromDate, toDate = :toDate," +
                    "leaveType = :leaveType, modifiedAt = :modifiedAt WHERE leaveRecordId = :leaveRecordId");
            query.setParameter("fromDate", leaveRecord.getFromDate());
            query.setParameter("toDate", leaveRecord.getToDate());
            query.setParameter("leaveType", leaveRecord.getLeaveType());
            query.setParameter("modifiedAt", LocalDateTime.now().toString());
            query.setParameter("leaveRecordId", leaveRecord.getLeaveRecordId());
            noOfRowAffected = query.executeUpdate();
            transact.commit();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }          
        }
        return noOfRowAffected;
    } 

    @Override
    public LeaveRecords getLeaveRecordById(int userLeaveRecordId) {
        LeaveRecords leaveRecord = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            leaveRecord = (LeaveRecords) session.get(LeaveRecords.class, userLeaveRecordId);
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }          
        }
        return leaveRecord;
    }

    public void removeEmployeeLeaveRecords(Employee employee) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transact = session.beginTransaction();
            Query query = session.createQuery("UPDATE LeaveRecords set deleted= 1 WHERE employee = :employee");
            query.setParameter("employee", employee.getEmployeeId());
            transact.commit();
        } catch (HibernateException h) {
            System.out.println(h);
        } finally {
            if(session != null) {
                session.close();
            }          
        }
    }
}
