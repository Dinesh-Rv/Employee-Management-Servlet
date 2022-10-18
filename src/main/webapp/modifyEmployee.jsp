<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/15/2022
  Time: 12:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="ModifyEmployee" method="get">
  <table>
    <tr>
      <td>Enter Employee ID</td>
      <td><input type="ModifyEmployee" name="EmployeeId"></td>
      <td>${message}</td>
    </tr>
    <tr>
      <td>
        <input id="update" name="modify" type="radio" value="update"/>
        <label for="update"> Update </label>
        <input id="delete" name="modify" type="radio" value="delete"/>
        <label for="Delete"> Delete </label>
      </td>
    </tr>
    <tr>
      <td>
        <input id="leaveRecords" name="modify" type="radio" value="leaveRecords">
        <label for="leaveRecords">Manage Leave Records</label>
      </td>
    </tr>
    <tr>
      <td>
      <input id="employeeProjects" name="modify" type="radio" value="employeeProjects">
      <label for="employeeProjects">Manage Employee Projects</label>
      </td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" value="Go"></td>
    </tr>
  </table>
  <p>${leaveRecords}</p>
  <p>${employeeProjects}</p>
</form>
<a href="employee.jsp">Back to Main Page</a>
</body>
</html>
