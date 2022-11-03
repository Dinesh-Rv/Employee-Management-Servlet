package com.ideas2it.service;

import com.ideas2it.service.EmployeeService;
import com.ideas2it.model.Employee;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.dao.EmployeeDaoImpl;
import com.ideas2it.service.LeaveRecordsService;
import com.ideas2it.service.LeaveRecordsServiceImpl;
import com.ideas2it.service.EmployeeProjectsServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
    //LeaveRecordsService leaveRecordServiceImpl = new LeaveRecordsServiceImpl();


    @Override
    public String addEmployee(Employee employee) {
        String processDetail;
        boolean isPhoneNumberValid = isPhoneNumberValid(employee.getEmployeePhoneNumber());
        boolean isEmailValid = isEmailValid(employee.getEmployeeEmail());
        if(isPhoneNumberValid && isEmailValid) {
            employee.setEmployeeId(getNewEmployeeId());
            employee.setCreatedAt(LocalDateTime.now().toString());
            employee.setModifiedAt(LocalDateTime.now().toString());
            employee.setEmployeeId(getNewEmployeeId());
            processDetail = "Employee added successfully id= " + employeeDao.insertEmployee(employee);
        } else if(!isPhoneNumberValid) {
           processDetail = "Phone number already exist";
        } else {
            processDetail = "An Error Occurred";
        }
        return processDetail;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    @Override
     public String updateEmployee(Employee employee) {
            employee.setModifiedAt(LocalDateTime.now().toString());
            return employeeDao.updateEmployee(employee);
    }

    @Override
    public String removeEmployee(Employee employee) {
         //leaveRecordServiceImpl.removeEmployeeLeaveRecords(employee);
         //employeeProjectsImpl.removeAllEmployeeProjects(employee);
        if(employeeDao.removeEmployee(employee)!=0) {
            return employee.getEmployeeId() + " has deleted succesfully";
        }
        return employee.getEmployeeId() + " not deleted";
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public boolean isPhoneNumberValid(String userPhoneNumber) {
        String phoneNumber = employeeDao.getEmployeePhoneNumber(userPhoneNumber);
        if(phoneNumber == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmailValid(String userEmail) {
        String emailId = employeeDao.getEmployeeEmail(userEmail);
        if(emailId == null) {
            return true;
        }
        return false;
    }

    @Override
    public String getNewEmployeeId() {
        int idNumber = 1;
        String lastId = employeeDao.getLastEmployeeId();
        try {
            String[] splitId = lastId.split("T");
            idNumber = Integer.parseInt(splitId[1]) + 1;
        } catch (NullPointerException ignored) {

        }
        return "I2IT" + idNumber;
    }
}