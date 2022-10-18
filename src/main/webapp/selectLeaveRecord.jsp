<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 17/10/2022
  Time: 4:47 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Select Leave Record</title>
</head>
<body>
<h2> Leave Slots</h2>
<form action = "SelectLeaveRecord" method="get">
<table>
    <tr>
        <td>
            <input type="submit" value="Update">
        </td>
    </tr>
    <tr>
        <td>
            <input type="radio" name="recordChoice" value="${leaveRecord0.leaveRecordId}">
        </td>
        <td>
            ${leaveRecord0}
        </td>
    </tr>
    <tr>
        <td>
            <input type="radio" name="recordChoice" value="${leaveRecord1.leaveRecordId}">
        </td>
        <td>
            ${leaveRecord1}
        </td>
    </tr>
    <tr>
        <td>
            <input type="radio" name="recordChoice" value="${leaveRecord2.leaveRecordId}">
        </td>
        <td>
            ${leaveRecord2}
        </td>
    </tr>
    <tr>
        <td>
            <input type="radio" name="recordChoice" value="${leaveRecord3.leaveRecordId}">
        </td>
        <td>
            ${leaveRecord3}
        </td>
    </tr>
</table>
</form>
</body>
</html>
