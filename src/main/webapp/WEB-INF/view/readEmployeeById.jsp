<%--
  Created by IntelliJ IDEA.
  User: Dinesh
  Date: 10/14/2022
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read Employee By Id</title>
</head>
<body>

<form action = "ReadEmployeeById" method = "get" >
    <div align="center">
    <table>
        <tr>
            <td>Enter Employee ID</td>
            <td><input type="text" name="EmployeeId"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Go"></td>
        </tr>
    </table>
        <p>${employee}</p>
    </div>
<%-- <input type="text" name="EmployeeId" id="id">

    <label for="id">Employee Id</label>
    <a href="${pageContext.request.contextPath}/EmployeeServlet">read using id</a>--%>
</form>
</body>
</html>
