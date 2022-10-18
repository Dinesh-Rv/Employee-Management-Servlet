package com.ideas2it.controller;

import com.ideas2it.model.EmployeeProjects;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.LeaveRecordsService;
import com.ideas2it.service.EmployeeProjectsService;
import com.ideas2it.service.EmployeeServiceImpl;
import com.ideas2it.service.LeaveRecordsServiceImpl;
import com.ideas2it.service.EmployeeProjectsServiceImpl;

import com.ideas2it.constants.Constants;

import com.ideas2it.utils.ValidationUtils;

import com.ideas2it.model.Employee;
import com.ideas2it.model.LeaveRecords;

import com.ideas2it.enums.EmployeeRole;
import com.ideas2it.enums.EmployeeGender;
import com.ideas2it.enums.LeaveType;

import com.ideas2it.exceptions.MoreCharacterException;

import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Implementing Controller for Employee Operations in an office
 * </p>
 *
 * @author Dinesh Kumar R
 * 
 * @modified 14.10.2022
 *
 */
public class OfficeController {							  

    private EmployeeService employeeServiceImpl = new EmployeeServiceImpl();
    private LeaveRecordsService leaveRecordsServiceImpl = new LeaveRecordsServiceImpl();
    private EmployeeProjectsService employeeProjectsServiceImpl = new EmployeeProjectsServiceImpl();
    private Scanner scanner = new Scanner(System.in);


    /**
     *<p>
     * gets all the employee details to display
     *</p>
     *
     * @return all employee details in a List
     *       if user enters 1 it returns crudChoice as 1 ,to create employee details
     */
    public List<Employee> getAllEmployees() {
        return employeeServiceImpl.getEmployees();
    }

    /**
     *<p>
     * gets an employee using id
     *</p>
     *
     * @param employeeId
     *        contains the id of an employee
     * @return an Employee object null if not found
     */
    public Employee getEmployeeById(String employeeId) {
        return employeeServiceImpl.getEmployeeById(employeeId);
    }

    /**
     * <p>
     * Creates an employee
     * </p>
     *
     */
    public String createEmployee(String employeeName,
                                 String employeeRole,
                                 String employeeDepartment,
                                 String employeePhoneNumber,
                                 String employeeDateOfBirth,
                                 String employeeGender,
                                 String employeeEmail,
                                 String employeeId,
                                 String createdAt,
                                 String modifiedAt) {

        String validPhoneNumber = getValidPhoneNumber(employeePhoneNumber);
        String validEmail = getValidEmail(employeeEmail);

        String errors = isAllInputValid(validPhoneNumber);
        if (errors == null) {
            Employee newEmployeeDetails = new Employee(employeeName,
                                                       employeeRole,
                                                       employeeDepartment,
                                                       validPhoneNumber,
                                                       employeeDateOfBirth,
                                                       employeeGender,
                                                       validEmail,
                                                       employeeId,
                                                       createdAt,
                                                       modifiedAt);
            if (employeeServiceImpl.addEmployee(newEmployeeDetails) != null) {
                return "New entry for employee is added successfully";
            }
        }
        return errors;
    }

    /**
     * <p>
     * To validate user phone Number input
     * </p>
     *
     * @param userPhoneNumber
     *        contains the input for phone Number of the employee
     * @return the phone number if the entered phone number is valid
     *         null if invalid
     */
    public String getValidPhoneNumber(String userPhoneNumber) {
        if(employeeServiceImpl.isPhoneNumberValid(userPhoneNumber)) {
            return userPhoneNumber;
        }
        return null;
    }

    /**
     * <p>
     *     checks if phone number and email id is actually valid
     *     by comparing each validation return value
     * </p>
     * @param validPhoneNumber
     * @return the value if it doesnt contains any null content
     *         if it does passes an error statement
     */
    public String isAllInputValid(String validPhoneNumber) {

        int validInputs = 2;

        if(validPhoneNumber == null) {
            validInputs = validInputs - 1;
            return "Invalid Phone Number: Phone Number Already Exist";
        }

        //if(validEmail == null) {
            //validInputs = validInputs - 1;
            //return "Invalid Email Id: Email Already exist";
        //}

        return null;
    }

    /**
     * <p>
     * creates an leave record for employee
     * </p>
     *
     */
    public String createLeaveRecords(String fromDate,
                                     String toDate,
                                     String leaveType,
                                     Employee employee,
                                     String createdAt,
                                     String modifiedAt) {
        LeaveRecords record = new LeaveRecords(employee, fromDate, toDate, leaveType, createdAt, modifiedAt);

        int newLeaveId = leaveRecordsServiceImpl.addLeaveRecord(record);
        if(newLeaveId != 0) {
            return "Absense for " + leaveType + " Recorded succesfully reference leaveId: " + newLeaveId;
        } else {
            return "Leave record unsuccesfull";
        }
    }

    /**
     * <p>
     * To validate user email input
     * </p>
     *
     * @param userEmail
     *        contains the email input from the user
     * @return the phone number if the entered phone number is valid
     */
    public String getValidEmail(String userEmail) {
        ValidationUtils validationUtils = new ValidationUtils();
            if(validationUtils.emailValidation(userEmail)) {
                return userEmail;
            }
        return null;
    }



    /**
     *<p>
     * current date and time for createdAt and modifiedAt feature
     *</p>
     *
     * @return Type of leave alloted for the employee
     */
    public String currentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(dateTime);
    }

    /**
     * <p>
     * creates an project record for employee
     * </p>
     *
     */ 
    public String createEmployeeProject(String projectName, String projectManager, String clientName,
                                        String startDate, Employee employee, String createdAt, String modifiedAt) {

        EmployeeProjects record = new EmployeeProjects(projectName, projectManager, clientName,
                                                       startDate, createdAt, modifiedAt);

        if(employeeProjectsServiceImpl.addEmployeeProject(record, employee)) {
            return "Project record Successfull";
        }
        return "Project record Unsuccessfull";
    }

    /**
     * <p>
     * Method to generate an id when an employee is created
     * </p>
     *
     * @return String employeeId
     */ 
    public String createId() {
        int idNumber = 1;
        String lastId = employeeServiceImpl.getEmployeeId();
        try {
            String[] splitId = lastId.split("T");
            idNumber = Integer.parseInt(splitId[1]) + 1;
        } catch (NullPointerException n) {

        }
	return "I2IT" + idNumber;
    }


    /**
     * <p>
     * Update each details of a employee
     * </p>
     */
    public String updateEmployee(Employee employee) {

        employee.setModifiedAt(currentDateTime());
        if(employeeServiceImpl.updateEmployee(employee)) {
            return "Employee Updated Sucessfully";
        }
        return "Update Failed";
    }

    /**
     * <p>
     * deletes an employee
     * </p>
     *
     * @param employee
     *        contains an employee object
     * @return string type value to inform the process status
     */
    public String removeEmployee(Employee employee) {
        if (employeeServiceImpl.removeEmployee(employee) > 0) {
            return "Employee Deleted";
        }
        return "An Error Occurred: While Deleting Employee";
    }

    /**
     * <p>
     *     Gets all the leave records of the employee
     * </p>
     *
     * @param employee
     *        contains the employee object of the concernec employee
     * @return the leave record list of the employee
     */
    public List<LeaveRecords> getAllLeaveRecords(Employee employee) {
        return leaveRecordsServiceImpl.getLeaveRecords(employee);
    }

    public LeaveRecords getLeaveRecordById(int leaveId) {
        return leaveRecordsServiceImpl.getLeaveRecordById(leaveId);
    }

    /**
     * <p>
     * Updates leave record for employee
     * </p>
     *
     */ 
    public String updateLeaveRecord(LeaveRecords leaveRecord) {
        if(leaveRecordsServiceImpl.updateLeaveRecords(leaveRecord)) {
            return "Update successfully completed";
        }
        return "Update Aborted";
    }

    /**
     * <p>
     * Updates employee project
     * </p>
     *
     */ 
    public void updateEmployeeProjects(Employee employee) {
        /*EmployeeProjects employeeProjects = employeeProjectsServiceImpl.getEmployeeProject(employee); 
    
        for(;;) {			
            System.out.println(employeeProjects);
            leaveRecordsUpdateStatement();
            int updateElement = scanner.nextInt();
				
	    if (updateElement == 1) {	
                employeeProjects.setProjectName(scanner.nextLine());
                employeeProjects.setModifiedAt(currentDateTime());
                employeeProjectsServiceImpl.updateEmployeeProjects(employeeProjects);

	    } else if (updateElement == 2)  {
                employeeProjects.setProjectManager(scanner.nextLine()); 
                employeeProjects.setModifiedAt(currentDateTime());
                employeeProjectsServiceImpl.updateEmployeeProjects(employeeProjects);

            } else if (updateElement == 3) {	
                employeeProjects.setClientName(scanner.nextLine());
                employeeProjects.setModifiedAt(currentDateTime());
                employeeProjectsServiceImpl.updateEmployeeProjects(employeeProjects);

            } else if (updateElement == 4) {	
                employeeProjects.setStartDate(scanner.nextLine());
                employeeProjects.setModifiedAt(currentDateTime());
                employeeProjectsServiceImpl.updateEmployeeProjects(employeeProjects);

            } else {
                System.out.println("Backing");
            }
        }*/ 
    }

    /**
     *<p>
     * gets the input for Leave Type and checks the Leave type is valid
     *</p>
     *
     * @return Type of leave alloted for the employee
     */
    private LeaveType getValidLeaveType() {
        LeaveType leaveType;     
        for(;;) {
            StringBuilder leaveTypeChoice = new StringBuilder();
	    leaveTypeChoice.append("=============Enter Employee Gender================="
                              + "\n1 Sick leave"
                              + "\n2 Casual leave"
                              + "\n==============================");
            System.out.println(leaveTypeChoice);
            int userChoice = Integer.parseInt(scanner.nextLine());
            leaveType = LeaveType.getLeaveType(userChoice);
            if(leaveType != null) {
                leaveType = leaveType; 
                break;
            } else {
                System.out.println("Please enter a valid Choice for Leave Type");
            }
        } 
        return leaveType;
    }

    /**
     * <p>
     * finds the difference between from date and to date for
leave records
     * </p>
     * @param userDate
     *        contains the date the user inputs
     * @return the difference between current year and the year the user enters
     */ 
    public int daysDifference(String userDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        int difference = 0;
        try {
            for(;;) {
                Date currentDate = new Date();
                Date date = dateFormat.parse(userDate);
                Calendar currentCalendar = Calendar.getInstance();

                Calendar dateCalendar = Calendar.getInstance();
                dateCalendar.setTime(date); 

                if(dateCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) {
                    if(dateCalendar.get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) {
                        difference = currentCalendar.get(Calendar.DATE) - dateCalendar.get(Calendar.DATE);
                    }
                    break;
                } else {
                    return 0; 
                }   
            }
        } catch (ParseException e) {
            System.out.println(e);
        }
        return difference;
    }

}       