package com.ideas2it.servlets;

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

    OfficeController officeController = new OfficeController();

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
        List<Employee> employees = officeController.getAllEmployees();
        PrintWriter writer = response.getWriter();
        writer.println(employees);
    }


    public void destroy() {
    }
}
