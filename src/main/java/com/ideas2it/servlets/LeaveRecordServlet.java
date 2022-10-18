package com.ideas2it.servlets;

import com.ideas2it.controller.OfficeController;
import com.ideas2it.model.Employee;
import com.ideas2it.model.LeaveRecords;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *     Contains servlet operation for Leave Records crud operations
 * </p>
 *
 * @author Dinesh Kumar R
 * @modified 16/10/2022
 */
public class LeaveRecordServlet extends HttpServlet {
    private OfficeController officeController = new OfficeController();

    public void init(ServletConfig servletConfig) {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("employeeId");
        String crudChoice = request.getParameter("crudValue");
        Employee employee = officeController.getEmployeeById(employeeId);

        switch (crudChoice) {
            case "create" -> {
                request.setAttribute("employee", employee);
                RequestDispatcher rd = request.getRequestDispatcher("createLeaveRecord.jsp");
                rd.forward(request, response);
            }
            case "read" -> {
                List<LeaveRecords> leaveRecords = officeController.getAllLeaveRecords(employee);
                request.setAttribute("leaveRecords", leaveRecords);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("modifyEmployee.jsp");
                requestDispatcher.forward(request, response);
            }
            
            case "update" -> {
                List<LeaveRecords> leaveRecords = officeController.getAllLeaveRecords(employee);
                iterateLeaveRecords(request, response, leaveRecords);

            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userRequest = request.getServletPath();
        switch(userRequest) {
            case "/CreateLeaveRecord" -> createLeaveRecord(request,response);
            case "/SelectLeaveRecord" -> {
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
            case "/UpdateLeaveRecord" -> updateLeaveRecord(request, response);
        }
    }

    private void updateLeaveRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        int leaveId = Integer.parseInt(request.getParameter("leaveId"));
        writer.println(leaveId);
        LeaveRecords leaveRecord = officeController.getLeaveRecordById(leaveId);
        leaveRecord.setFromDate(request.getParameter("fromDate"));
        leaveRecord.setToDate(request.getParameter("toDate"));
        leaveRecord.setLeaveType(request.getParameter("leaveType"));
        String processDetails = officeController.updateLeaveRecord(leaveRecord);

        request.setAttribute("message", processDetails);
        RequestDispatcher rd = request.getRequestDispatcher("modifyEmployee.jsp");
        rd.forward(request, response);
    }

    private void iterateLeaveRecords(HttpServletRequest request, HttpServletResponse response, List<LeaveRecords> leaveRecords) throws ServletException, IOException {
        Iterator<LeaveRecords> iterator = leaveRecords.iterator();

        while(iterator.hasNext()) {
            for(int i =0; i<leaveRecords.size(); i++) {
                String recordName = "leaveRecord" + i;
                request.setAttribute(recordName, iterator.next());
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("selectLeaveRecord.jsp");
        requestDispatcher.forward(request, response);

    }
    private void createLeaveRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String leaveType = request.getParameter("leaveType");
        String createdAt = String.valueOf(LocalDateTime.now());
        String modifiedAt = String.valueOf(LocalDateTime.now());
        Employee employee = officeController.getEmployeeById(request.getParameter("employeeId"));
        String processDetails = officeController.createLeaveRecords(fromDate, toDate, leaveType,
                                                                    employee, createdAt, modifiedAt);
        request.setAttribute("message", processDetails);
        RequestDispatcher rd = request.getRequestDispatcher("modifyEmployee.jsp");
        rd.forward(request, response);
    }

}
