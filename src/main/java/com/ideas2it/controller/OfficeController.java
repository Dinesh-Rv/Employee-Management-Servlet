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
 * @modified 13.10.2022
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
        String validName = getValidName(employeeName);
        //String validRole = getValidRole(employeeRole);
        String validDepartment = getValidDept(employeeDepartment);
        String validPhoneNumber = getValidPhoneNumber(employeePhoneNumber);
        String validDateOfBirth = getValidDateOfBirth(employeeDateOfBirth);
        //String validGender = getValidGender(employeeGender);
        String validEmail = getValidEmail(employeeEmail);

        String errors= isAllInputValid(validName, validDepartment, validPhoneNumber,
                                             validDateOfBirth, validEmail);

        if (errors == null) {
            Employee newEmployeeDetails = new Employee(validName,
                                                       employeeRole,
                                                       validDepartment,
                                                       validPhoneNumber,
                                                       validDateOfBirth,
                                                       employeeGender,
                                                       validEmail,
                                                       employeeId,
                                                       createdAt,
                                                       modifiedAt);
            if (employeeServiceImpl.addEmployee(newEmployeeDetails) != null) {
                return "Success";
            }
        }
        return errors;
    }

    public String isAllInputValid(String validName,
                                        String validDepartment,
                                        String validPhoneNumber,
                                        String validDateOfBirth,
                                        String validEmail) {
        List<String> errors = new ArrayList<String>();
        int sumOfValidInputs = 0;

        if(validName == null) {
            sumOfValidInputs = sumOfValidInputs + 1;
            errors.add("Invalid name: Entered name isn't valid (correct way: Dinesh Kumar R)");
        }
        if(validDepartment == null) {
            sumOfValidInputs = sumOfValidInputs + 1;
            errors.add("Invalid Depatment: Entered department isn't valid (correct way: Fresher Training)");
        }
        if(validPhoneNumber == null) {
            sumOfValidInputs = sumOfValidInputs + 1;
            errors.add("Invalid Phone Number: Entered Phone Number isn't valid (correct way: 9999999999)");
        }
        if(validDateOfBirth == null) {
            sumOfValidInputs = sumOfValidInputs + 1;
            errors.add("Invalid Dob: Entered Date isn't valid (correct way: dd/MM/yyyy)");
        }
        if(validEmail == null) {
            sumOfValidInputs = sumOfValidInputs + 1;
            errors.add("Invalid Email Id: Entered email isn't valid (correct way: xyz@abc.com)");
        }
        if(sumOfValidInputs == 5) {
            return null;
        }
        return phraseErrors(errors);
    }

    public String phraseErrors(List<String> errorsList){
        StringBuilder errorMessage = new StringBuilder();
        for(String error : errorsList) {
            errorMessage.append(error);
            errorMessage.append(",\n");
        }
        return errorMessage.toString();
    }

    /**
     * <p>
     * To Check is the input for the name is valid
     * </p>
     *
     * @param userName
     *        Contains the name input of the user
     * @return the name if the entered name is valid
     */
    public String getValidName(String userName) {
        ValidationUtils validationUtils = new ValidationUtils();
        if(validationUtils.nameValidation(userName)) {
                return userName;
        }
        return null;
    }

    /**
     *<p>
     * Gets the input for the employeeRole and checks if its valid
     *</p>
     *
     * @return role of the employee
     */
    /*private String getValidRole(String userChoice) {
        String Role;
        if (userChoice <= 2 && userChoice != 0) {
            EmployeeRole employeeRole = EmployeeRole.getEmployeeRole(userChoice);
            if (employeeRole != null) {
                role = employeeRole;
                break;
                } else {
                    System.out.println("Please enter a valid choice for Role");
                }
            }
        }
        return role;
    }*/

    /**
     * <p>
     * To validate user name input
     * </p>
     *
     * @param userDepartment
     *        Contains the input for department from the user
     * @return the department if the entered department is valid
     *         null if its invalid
     */
    public String getValidDept(String userDepartment) {
        ValidationUtils validationUtils = new ValidationUtils();
        if(validationUtils.nameValidation(userDepartment)) {
                return userDepartment;
        }
        return null;
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
        ValidationUtils validationUtils = new ValidationUtils();
        if(validationUtils.phoneNumberValidation(userPhoneNumber)) {
            return userPhoneNumber;
        }
        return null;
    }

    /**
     * <p>
     * To Check the date of birth input from the user is valid
     * </p>
     * @param userDateOfBirth
     *        contains the date of birth from the user
     * @return the valid date of birth
     *         null if invalid
     */
    public String getValidDateOfBirth(String userDateOfBirth) {
        ValidationUtils validationUtils = new ValidationUtils();
        if(validationUtils.dateValidation(userDateOfBirth)) {
            int age = dateDifference(userDateOfBirth);
            if(age != 0 && age>=18) {
                return userDateOfBirth;
            }
        }
        return null;
    }

    /**
     * <p>
     * finds the difference between date of birth and current date,
     * used for date of birth validation
     * </p>
     *
     * @param userDate
     *        contains the date the user inputs
     *
     * @return the difference between current year and the year the user enters
     */
    public int dateDifference(String userDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date currentDate = new Date();
            Date date = dateFormat.parse(userDate);
            Calendar currentCalendar = Calendar.getInstance();

            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(date);

            if(dateCalendar.get(Calendar.YEAR) < (currentCalendar.get(Calendar.YEAR))) {
                return  currentCalendar.get(Calendar.YEAR) - dateCalendar.get(Calendar.YEAR);

            }
        } catch (ParseException e) {
            System.out.println(e);
        }
        return 0;
    }

    /**
     *<p>
     * gets the input for gender and checks the gender is valid
     *</p>
     *
     * @return gender of the employee
     */
    /*private EmployeeGender getValidGender() {
        EmployeeGender gender;
        for(;;) {
            StringBuilder genderChoice = new StringBuilder();
            genderChoice.append("+===+==Enter Employee Gender==+"
                    + "\n| 1 |          Male           |"
                    + "\n+===+=========================+"
                    + "\n| 2 |         Female          |"
                    + "\n+===+=========================+"
                    + "\n| 3 |       Undefined         |"
                    + "\n==============================+");
            System.out.println(genderChoice);
            int userChoice = Integer.parseInt("2");
            if (userChoice <= 3 && userChoice != 0) {
                EmployeeGender employeeGender = EmployeeGender.getEmployeeGender(userChoice);
                if (employeeGender != null) {
                    gender = employeeGender;
                    break;
                } else {
                    System.out.println("Please enter a valid Choice for Gender");
                }
            }
        }
        return gender;
    }*/

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
     * creates an leave record for employee
     * </p>
     *
     */ 
    public void createLeaveRecords(Employee employee) {

        System.out.println("Leave alloted From: ");
        String fromDate = getValidLeaveDate();

        System.out.println("Leave period ends on: ");
        String toDate = getValidLeaveDate();

        String leaveType = getValidLeaveType().toString();
        String createdAt = currentDateTime();
        String modifiedAt = currentDateTime();
        LeaveRecords record = new LeaveRecords(employee, fromDate, toDate, leaveType, createdAt, modifiedAt);

        int newLeaveId = leaveRecordsServiceImpl.addLeaveRecord(record);
        if(newLeaveId != 0) {
            System.out.println("Absense for " + leaveType + " Recorded succesfully reference leaveId: " + newLeaveId);
        } else {
            System.out.println("Leave record unsuccesfull");
        }
    }

    /**
     * <p>
     * creates an project record for employee
     * </p>
     *
     */ 
    public void createEmployeeProject(Employee employee) {
        
        System.out.println("Project Name: ");
        String projectName = scanner.nextLine();

        System.out.println("Project manager: ");
        String projectManager = scanner.nextLine();

        System.out.println("client Name: ");
        String clientName = scanner.nextLine();

        System.out.println("Start Date: ");
        String startDate = scanner.nextLine();
    
        String createdAt = currentDateTime();
        String modifiedAt = currentDateTime();

        EmployeeProjects record = new EmployeeProjects(projectName, projectManager, clientName,
                                                       startDate, createdAt, modifiedAt);

        if(employeeProjectsServiceImpl.addEmployeeProject(record, employee)) {
            System.out.println("Project record  Successfull");
        } else {
            System.out.println("Project record Unsuccessfull");
        }
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
            System.out.println("First Entry");
        }
	return "I2IT" + idNumber;
    }







    
    /**
     * <p>
     * Update each details of a employee
     * </p>
     *
     */ 
    /*public void updateEmployee(Employee employee) {
    for(;;) {			
                System.out.println("UPDATE PORTAL FOR EMPLOYEE ID: " + employee.getEmployeeId());
                System.out.println(employee);
		updateStatement();
                String updateElement = scanner.nextLine();
				
	        if (updateElement.equals(Constants.EDIT_NAME)) {	
                    employee.setEmployeeName(editName());
                    employee.setModifiedAt(currentDateTime());

		} else if (updateElement.equals(Constants.EDIT_PHONE_NUMBER))  {
                    employee.setEmployeePhoneNumber(editPhoneNumber()); 
                    employee.setModifiedAt(currentDateTime());

                } else if (updateElement.equals(Constants.EDIT_DEPARTMENT)) {	
                    employee.setEmployeeDepartment(editDepartment());
                    employee.setModifiedAt(currentDateTime());

                } else if (updateElement.equals(Constants.EDIT_DOB)) {	
                    employee.setEmployeeDateOfBirth(editDateOfBirth());
                    employee.setModifiedAt(currentDateTime());

                } else if (updateElement.equals(Constants.EDIT_EMAIL)) {
                    employee.setEmployeeEmail(editEmail());
                    employee.setModifiedAt(currentDateTime());

                } else if (updateElement.equals(Constants.EDIT_ROLE)) {
                    employee.setEmployeeRole(getValidRole().toString());
                    employee.setModifiedAt(currentDateTime());

                } else if (updateElement.equals(Constants.EDIT_GENDER)) {
                    employee.setEmployeeGender(getValidGender().toString());
                    employee.setModifiedAt(currentDateTime());

                } else if (updateElement.equals(Constants.FINISH_UPDATING)) {
                    employeeServiceImpl.updateEmployee(employee);
                    break;     

                } else {
                    System.out.println("Exited without saving");
                    break;
                }
            }  
    }*/



    /**
     * <p>
     * Updates leave record for employee
     * </p>
     *
     */ 
    /*public void updateLeaveRecords(Employee employee) {
        System.out.println(employee.getLeaveRecords());
        int userLeaveRecordId = Integer.parseInt(scanner.nextLine()); 
        LeaveRecords leaveRecord = leaveRecordsServiceImpl.getLeaveRecordById(userLeaveRecordId);

        if(leaveRecord != null) {
            for(;;) {			
                System.out.println(leaveRecord);
                leaveRecordsUpdateStatement();
                int updateElement = Integer.parseInt(scanner.nextLine());
				
	        if (updateElement == 1) {	
                    leaveRecord.setFromDate(scanner.nextLine());
                    leaveRecord.setModifiedAt(currentDateTime());
                
                } else if (updateElement == 2)  {
                    leaveRecord.setToDate(getValidLeaveDate()); 
                    leaveRecord.setModifiedAt(currentDateTime());

                } else if (updateElement == 3) {	
                    leaveRecord.setLeaveType(getValidLeaveType().toString());
                    leaveRecord.setModifiedAt(currentDateTime());
                    
                } else  if (updateElement == 4) {
                    if (leaveRecordsServiceImpl.updateLeaveRecords(leaveRecord)) {
                        System.out.println("Update is successfull");
                    }
                    break;
                } else if (updateElement == 5) {
                    break;
                }
            }
        }
    }*/

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
     * <p>
     * deletes an employee
     * </p>
     *
     */
    public void deleteEmployee(Employee employee) {
       /* StringBuilder updateChoice = new StringBuilder   ("+===+====YOU ARE DELETING AN EMPLOYE======+"
                                                      + "\n+===+==========Id: " + employee.getEmployeeId() + "==================+"
                                                      + "\n+===+========Are you sure?================+"
                                                      + "\n| 1 |      YES (Data will be lost)        |"
                                                      + "\n+===+=====================================+"
                                                      + "\n|any|           NO (Retract)              |"
                                                      + "\n+===+=====================================+");
        System.out.println(updateChoice);
        switch(getValidChoice()) {
        case Constants.YES:
            if (employeeServiceImpl.removeEmployee(employee) > 0) {
                System.out.println("============Employee Deleted==================");
            } else {
                System.out.println("An Error Occured: While Deleting Employee");
            }
        default:
            System.out.println("Backing");
        }*/
    }

    /**
     * <p> 
     * checks the employee is present in the
employee List
     * </p>
     *
     *
     *        id of the employee received from the user for checking
     *
     * @return boolen value
     *         true if the employee is present
     */
    /*public boolean isEmployeeValid(String employeeId) {
        boolean isValid = false;

        Employee employee = employeeServiceImpl.getEmployeeById(employeeId); 
	if(employee != null) {
            isValid = true;
        } 
        return isValid;
    }*/

    public Employee getValidEmployee() {
        System.out.println("Enter Employee Id");
        String employeeId = scanner.nextLine();
 
        Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
        if(employee != null) {
            return employee;
        }
        return null;
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
     *<p>
     * output statement for employee projects update
     *</p>
     *
     */
    public void employeeProjectsUpdateStatement() {
        StringBuilder updateOptions = new StringBuilder();
	updateOptions.append("====================UPDATE ELEMENTS========================="
                           + "\n1.To Update Project Name"
                           + "\n2.To Update Project Manager"
                           + "\n3.To Update Client Name"
                           + "\n4.To Update Start Date"
                           + "\n=========================================================");
        System.out.println(updateOptions);
    }



    /**
     * <p>
     * To validate date input from the user, can be used for
both date of birth and date of joining input
     * </p>
     *
     * @return the date 
     */ 
    public String getValidLeaveDate() { 
    ValidationUtils validationUtils = new ValidationUtils();
    String userDate;
        for(;;) {
            userDate = scanner.nextLine();
            if(validationUtils.dateValidation(userDate)) {
                if(daysDifference(userDate)!= 0) {
                    break;
                } 
            }
            System.out.println("Enter a valid date:");
        }
        return userDate;
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