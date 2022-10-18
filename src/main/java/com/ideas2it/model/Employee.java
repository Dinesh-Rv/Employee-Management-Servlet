package com.ideas2it.model;
import com.ideas2it.enums.EmployeeRole;
import com.ideas2it.enums.EmployeeGender;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

/**
 * <p> Contains every attribute common for all employee, doesn't contain
 * any input or output operations
 * </p>
 *
 */
public class Employee {
   
    private String employeeName;
    private String employeeDepartment;
    private String employeePhoneNumber;
    private String employeeDateOfBirth;
    private String employeeEmail;
    private String employeeId;
    private String employeeRole;
    private String employeeGender;
    private String createdAt;
    private String modifiedAt;

    private int deleted;
    private List<LeaveRecords> leaveRecords;

    private List<EmployeeProjects> employeeProjects;

    public Employee () {
    }
  

    public Employee(String employeeName, 
                    String employeeRole,
                    String employeeDepartment, 
                    String employeePhoneNumber,
                    String employeeDateOfBirth,
                    String employeeGender,
                    String employeeEmail,
                    String employeeId,
                    String createdAt,
                    String modifiedAt) {
        this.employeeName = employeeName;
        this.employeeDepartment = employeeDepartment;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
        this.employeeRole = employeeRole;
        this.employeeGender = employeeGender;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Employee(String employeeName, 
                    String employeeRole,
                    String employeeDepartment, 
                    String employeePhoneNumber,
                    String employeeDateOfBirth,
                    String employeeGender,
                    String employeeEmail,
                    String employeeId) {
        this.employeeName = employeeName;
        this.employeeDepartment = employeeDepartment;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeDateOfBirth = employeeDateOfBirth;
        this.employeeEmail = employeeEmail;
        this.employeeId = employeeId;
        this.employeeRole = employeeRole;
        this.employeeGender = employeeGender;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {		
        return employeeName;
    } 

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeDepartment() {		
	return employeeDepartment;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }
	
    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }	

    public String getEmployeeId() {
        return  employeeId;
    }
 
    public void setEmployeeDateOfBirth(String employeeDateOfBirth) {
        this.employeeDateOfBirth = employeeDateOfBirth;
    }

    public String getEmployeeDateOfBirth() {	
        return employeeDateOfBirth;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }  

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeRole() {		
        return employeeRole;
    } 

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeGender() {		
        return employeeGender;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getModifiedAt() {		
        return modifiedAt;
    } 


    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setLeaveRecords(List<LeaveRecords> leaveRecords) {
        this.leaveRecords = leaveRecords;
    }
    public List<LeaveRecords> getLeaveRecords() {
        return leaveRecords;
    }

    public void setEmployeeProjects(List<EmployeeProjects> employeeProjects) {
        this.employeeProjects = employeeProjects;
    }

    public List<EmployeeProjects> getEmployeeProjects() {
        return employeeProjects;
    }
    @Override
    public String toString() {
        String displayEmployeeDetail = "\nEmployee Id : "+ employeeId + "\nEmployee Role : " + employeeRole + 
                                       "\nDepartment :" + employeeDepartment + "\nName : "+employeeName + 
                                       "\nDate of birth :" + employeeDateOfBirth + "\nPhone Number :" + employeePhoneNumber + 
                                       "\nEmail ID :" + employeeEmail + "\nGender : " + employeeGender + "\n";
        return displayEmployeeDetail;
    }	
}