<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/10/2022
  Time: 12:46 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leave Records</title>
</head>
<body>
<h2>Leave Records crud for Employee Id:  </h2>

<form action="LeaveRecordServlet" method="post">
    <input type="text" value="${employee.employeeId}" name="employeeId" readonly>
    <br>
    <h2>Name:  ${employee.employeeName}</h2>
<table>
    <br>
    <tr>
        <td>
            <input type="radio" name="crudValue" id="create" value="create">
            <label for="create">Create Leave Record</label>
        </td>
    </tr>
    <br>
    <tr>
        <td>
            <input type="radio" name="crudValue" id="read" value="read">
            <label for="read">Read Leave Records</label>
        </td>
    </tr>
    <tr>
        <td>
            <input type="radio" name="crudValue" id="update" value="update">
            <label for="update">Update Leave Record</label>
        </td>
    </tr>
</table>
    <input type="submit" value="Go">
    <p>${message}</p>
</form>
</body>
</html>
