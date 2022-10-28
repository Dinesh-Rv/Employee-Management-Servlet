package com.ideas2it.servlets;

import com.ideas2it.controller.OfficeController;
import com.ideas2it.model.Employee;
import com.ideas2it.model.LeaveRecords;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class EmployeeProjectsServlet extends HttpServlet {

    private OfficeController officeController = new OfficeController();
    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("employeeId");
        String crudChoice = request.getParameter("crudValue");
        Employee employee = officeController.getEmployeeById(employeeId);

        switch (crudChoice) {
            case "create" -> {
                request.setAttribute("employee", employee);
                RequestDispatcher rd = request.getRequestDispatcher("createEmployeeProjects.jsp");
                rd.forward(request, response);
            }
            /*case "read" -> {
                List<LeaveRecords> leaveRecords = officeController.getAllLeaveRecords(employee);
                request.setAttribute("leaveRecords", leaveRecords);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("modifyEmployee.jsp");
                requestDispatcher.forward(request, response);
            }

            case "update" -> {
                List<LeaveRecords> leaveRecords = officeController.getAllLeaveRecords(employee);
                iterateLeaveRecords(request, response, leaveRecords);

            }*/
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userRequest = request.getServletPath();
        switch(userRequest) {
            case "/CreateEmployeeProjects" -> createEmployeeProject(request,response);
            /*case "/SelectLeaveRecord" -> {
                String leaveId = request.getParameter("recordChoice");
                if(Objects.equals(leaveId, "")) {
                    request.setAttribute("message", "An Error Occurred");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("modifyEmployee.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    LeaveRecords record = officeController.getLeaveRecordById(Integer.parseInt(leaveId));
                    request.setAttribute("record", record);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("updateLeaveRecord.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
            case "/UpdateLeaveRecord" -> updateLeaveRecord(request, response);*/
        }
    }

    private void createEmployeeProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getParameter("projectName");
        String projectManager = request.getParameter("projectManager");
        String clientName = request.getParameter("clientName");
        String startDate = request.getParameter("startDate");
        String createdAt = String.valueOf(LocalDateTime.now());
        String modifiedAt = String.valueOf(LocalDateTime.now());

        String processDetails = officeController.createEmployeeProject(projectName, projectManager, clientName,
                                                                     startDate, createdAt, modifiedAt);
        request.setAttribute("message", processDetails);
        RequestDispatcher rd = request.getRequestDispatcher("modifyEmployee.jsp");
        rd.forward(request, response);
    }
}
