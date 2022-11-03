package com.ideas2it.service;

import com.ideas2it.model.LeaveRecords;
import com.ideas2it.model.Employee;

import com.ideas2it.dao.LeaveRecordsDao;
import com.ideas2it.dao.LeaveRecordsDaoImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveRecordsServiceImpl implements LeaveRecordsService {

    private final LeaveRecordsDao leaveRecordsDao;

    public LeaveRecordsServiceImpl(LeaveRecordsDao leaveRecordsDao) {
        this.leaveRecordsDao = leaveRecordsDao;
    }

    @Override
    public int addLeaveRecord(LeaveRecords record, Employee employee) {
        record.setEmployee(employee);
        record.setCreatedAt(LocalDateTime.now().toString());
        record.setModifiedAt(LocalDateTime.now().toString());
        return leaveRecordsDao.addLeaveRecord(record);
    }

    @Override
    public List<LeaveRecords> getLeaveRecords(Employee employee) {
        return leaveRecordsDao.getLeaveRecords(employee);
    }

    @Override
    public String updateLeaveRecords(LeaveRecords leaveRecord) {
        String processDetails;
        if(leaveRecordsDao.updateLeaveRecords(leaveRecord) != 0) {
            processDetails = "LeaveRecord has updated Successfully";
        } else {
            processDetails = "An error occurred";
        }
        return processDetails;
    }

    @Override
    public LeaveRecords getLeaveRecordById(String userLeaveRecordId) {
        LeaveRecords leaveRecord = null;
        int leaveRecordId = 0;
        try {
            leaveRecordId = Integer.parseInt(userLeaveRecordId);
            leaveRecord = leaveRecordsDao.getLeaveRecordById(leaveRecordId);
        } catch (NumberFormatException ignored) {

        }
        return leaveRecord;
    }
   
    @Override
    public void removeEmployeeLeaveRecords(Employee employee) {
        System.out.println("inside removing all leav rec");
        leaveRecordsDao.removeEmployeeLeaveRecords(employee);
    }
}