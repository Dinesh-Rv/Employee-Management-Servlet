<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18/10/2022
  Time: 6:07 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="CreateEmployeeProjects" method="get">
    <table>

        <tr>
            <th></th>
            <th>Enter the values here</th>
        </tr>
        <tr>
            <td>Manager: </td>
            <td><input type="text" name="projectManager" value="${employee.employeeId}" readonly></td>
        </tr>
        <tr>
            <td>Project Name</td>
            <td><input type="text" name="projectName" required ></td>
        </tr>
        <tr>
            <td>Client Name</td>
            <td><input type="text" name="clientName" required ></td>
        </tr>
        <tr>
            <td>Start Date</td>
            <td><input type="date" name="startDate" required ></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
    <a href="employee.jsp">Back to main page</a>

</form>
</body>
</html>
