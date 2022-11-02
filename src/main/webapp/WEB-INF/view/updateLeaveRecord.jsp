<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/10/2022
  Time: 5:05 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="UpdateLeaveRecord" method="get">
    <table>
        <caption><h2>Update</h2></caption>
        <tr>
            <td> EmployeeID </td>
            <td> = </td>
            <td><input type="text" name="leaveId" value="${record.leaveRecordId}" readonly></td>
        </tr>
        <tr>
            <th>Particulars</th>
            <th>Data</th>
            <th>change to?</th>
        </tr>
        <tr>
            <td>From Date: </td>
            <td>${record.fromDate}</td>
            <td><input type="date" name="fromDate" value="${record.fromDate}" required></td>
        </tr>
        <tr>
            <td>To Date: </td>
            <td>${record.toDate}</td>
            <td><input type="date" name="toDate" value="${record.toDate}" required></td>
        </tr>
        <tr>
            <td>Leave Type:</td>
            <td>${record.leaveType}</td>
            <td><select name="leaveType" required>
                <option value="${record.leaveType}">Don't change</option>
                <option value="sick">Sick</option>
                <option value="casual">casual</option>
            </select></td>
        </tr>
        <tr>
            <td><input type="submit" value="update"></td>
        </tr>
        <tr>
            <td><a href="modifyEmployee.jsp" >Back to employee records</a> </td>
        </tr>
    </table>
</form>
</body>
</html>
