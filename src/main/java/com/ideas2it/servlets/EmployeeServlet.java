package com.ideas2it.servlets;

import com.google.gson.Gson;
import com.ideas2it.controller.OfficeController;
import com.ideas2it.model.Employee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.time.LocalDateTime;
import java.io.IOException;
import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Contains servlet operation for Employee crud operations
 * </p>
 *
 * @author Dinesh Kumar R
 * @modified 13/10/2022
 */
public class EmployeeServlet extends HttpServlet {

    private OfficeController officeController = new OfficeController();

    public void init(ServletConfig servletConfig) {

    }

    /**
     * <p>
     *     Contains post operation for employee that is
     *     for adding a new employee
     * </p>
     *
     * @param request
     *        contains http request from the jsp page
     * @param response
     *        contains http response to be sent to jsp page
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String employeeRole = request.getParameter("role");
        String employeeName = request.getParameter("name");
        String employeeGender = request.getParameter("gender");
        String employeeDepartment = request.getParameter("department");
        String employeePhoneNumber = request.getParameter("phoneNumber");
        String employeeDateOfBirth = request.getParameter("dateOfBirth");
        String employeeEmail = request.getParameter("email");
        String employeeId = officeController.createId();
        String createdAt = String.valueOf(LocalDateTime.now());
        String modifiedAt = String.valueOf(LocalDateTime.now());

        String processDetails = officeController.createEmployee(employeeName,
                                                                employeeRole,
                                                                employeeDepartment,
                                                                employeePhoneNumber,
                                                                employeeDateOfBirth,
                                                                employeeGender,
                                                                employeeEmail,
                                                                employeeId,
                                                                createdAt,
                                                                modifiedAt);

        request.setAttribute("message", processDetails);
        RequestDispatcher rd = request.getRequestDispatcher("createEmployee.jsp");
        rd.include(request, response);
    }

    /**
     * <p>
     *     Contains Get operation for employee such as
     *     for getting all employees or getting an single employee
     * </p>
     *
     * @param request
     *        contains http request from the jsp page
     * @param response
     *        contains http response to be sent to jsp page
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userRequest = request.getServletPath();

        switch (userRequest) {
            case "/ReadAllEmployees" -> getAllEmployees(request, response);
            case "/ReadEmployeeById" -> getEmployeeById(request, response);
            case "/ModifyEmployee" -> modifyEmployee(request, response);
            case "/UpdateEmployee" -> updateEmployee(request, response);
        }
    }

    public void getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        //Gson gson = new Gson();
        List<Employee> employees = officeController.getAllEmployees();
        //String displayEmployees = gson.toJson(employees);
        writer.println(employees);
        //request.setAttribute("employees", employees);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("readAllEmployees.jsp");
        //dispatcher.forward(request, response);
    }

    public void getEmployeeById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Gson gson = new Gson();
        String userId = request.getParameter("EmployeeId");
        Employee employee = officeController.getEmployeeById(userId);
        if(employee != null) {
            employee.setLeaveRecords(null);
            employee.setEmployeeProjects(null);
            String displayEmployee = gson.toJson(employee);
            request.setAttribute("employee", displayEmployee);
            RequestDispatcher rd = request.getRequestDispatcher("readEmployeeById.jsp");
            rd.include(request, response);
        } else {
            request.setAttribute("employee", "Employee not found");
            RequestDispatcher rd = request.getRequestDispatcher("readEmployeeById.jsp");
            rd.include(request, response);
        }
    }

    public void modifyEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter("EmployeeId");
        String userChoice = request.getParameter("modify");
        Employee employee = officeController.getEmployeeById(userId);
        if (employee != null) {
            request.setAttribute("employee", employee);
            switch (userChoice) {
                case "update" -> {
                    RequestDispatcher rd = request.getRequestDispatcher("updateEmployee.jsp");
                    rd.forward(request, response);
                }
                case "delete" -> removeEmployee(request, response, employee);

                case "leaveRecords" -> {
                    RequestDispatcher rd = request.getRequestDispatcher("leaveRecords.jsp");
                    rd.forward(request, response);
                }

                case "employeeProjects" -> {
                    RequestDispatcher rd = request.getRequestDispatcher("employeeProjects.jsp");
                    rd.forward(request, response);
                }
            }
        } else {
            request.setAttribute("message", "Employee not found");
            RequestDispatcher rd = request.getRequestDispatcher("modifyEmployee.jsp");
            rd.include(request, response);
        }
    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String employeeId = request.getParameter("employeeId");
        Employee employee = officeController.getEmployeeById(employeeId);
        employee.setEmployeeRole(request.getParameter("updateRole"));
        employee.setEmployeeName(request.getParameter("updateName"));
        employee.setEmployeeGender(request.getParameter("updateGender"));
        employee.setEmployeeDepartment(request.getParameter("updateDepartment"));
        employee.setEmployeePhoneNumber(request.getParameter("updatePhoneNumber"));
        employee.setEmployeeDateOfBirth(request.getParameter("updateDob"));
        employee.setEmployeeEmail(request.getParameter("updateEmail"));

        String processDetails = officeController.updateEmployee(employee);

        request.setAttribute("message", processDetails);
        RequestDispatcher rd = request.getRequestDispatcher("modifyEmployee.jsp");
        rd.forward(request, response);
    }

    public void removeEmployee(HttpServletRequest request, HttpServletResponse response,Employee employee) throws IOException, ServletException {
        String employeeId = employee.getEmployeeId();
        String processDetails = officeController.removeEmployee(employee);

        request.setAttribute("message", processDetails);
        RequestDispatcher rd = request.getRequestDispatcher("modifyEmployee.jsp");
        rd.include(request, response);
    }

        public void destroy() {
    }
}
