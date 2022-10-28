<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18/10/2022
  Time: 5:41 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Projects</title>
</head>
<body>
<h2>Employee Projects crud for Employee Id:  </h2>

<form action="EmployeeProjectsServlet" method="post">
    <input type="text" value="${employee.employeeId}" name="employeeId" readonly>
    <br>
    <h2>Name:  ${employee.employeeName}</h2>
    <table>
        <br>
        <tr>
            <td>
                <input type="radio" name="crudValue" id="create" value="create">
                <label for="create">Manage a New Project</label>
            </td>
        </tr>
        <br>
        <tr>
            <td>
                <input type="radio" name="crudValue" id="read" value="read">
                <label for="read">Read Projects</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="radio" name="crudValue" id="update" value="update">
                <label for="update">Update Project</label>
            </td>
        </tr>
    </table>
    <input type="submit" value="Go">
    <p>${message}</p>
</form>
</body>
</html>
