<%--
  Created by IntelliJ IDEA.
  User: Dinesh
  Date: 10/12/2022
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EMPLOYEE CRUD</title>
</head>
<body>
<h1><%= "Employee details crud" %></h1>
<table>
<br>
    <tr>
        <td>
            <%-- Link to Employee Creation Portal  --%>
            <a href="createEmployee.jsp">Create An Employee</a>
        </td>
    </tr>
    <tr>
        <td>
            <%-- Link to Employee Display Portal  --%>
            <a href="readEmployee.jsp">Read Employee</a>
        </td>
    </tr>
    <tr>
        <td>
            <%-- Link to Employee Modify Portal  --%>
            <a href="modifyEmployee.jsp">Employee Records</a>
        </td>
    </tr>
</table>
</body>
</html>
