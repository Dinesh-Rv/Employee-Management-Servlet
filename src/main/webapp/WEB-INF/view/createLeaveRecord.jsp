<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/10/2022
  Time: 1:19 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Leave Records</title>
</head>
<body>
<form action="CreateLeaveRecord" method="get">
    <table>

        <tr>
            <th></th>
            <th>Enter the values here</th>
        </tr>
        <tr>
            <td>Employee Id: </td>
            <td><input type="text" name="employeeId" value="${employee.employeeId}" readonly></td>
        </tr>
        <tr>
            <td>From Date</td>
            <td><input type="date" name="fromDate" required ></td>
        </tr>
        <tr>
            <td>To Date</td>
            <td><input type="date" name="toDate" required ></td>
        </tr>
        <tr>
            <td>Leave type</td>
            <td>
                <input id="sick" name="leaveType" type="radio" value="sick"/>
                <label for="sick"> Sick Leave </label>
                <input id="casual" name="leaveType" type="radio" value="casual"/>
                <label for="casual"> Casual Leave </label>
            </td>
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
