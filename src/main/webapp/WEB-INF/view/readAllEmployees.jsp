<%--
  Created by IntelliJ IDEA.
  User: Dinesh
  Date: 10/13/2022
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- Displays all the employees  --%>
    <form action = "ReadAllEmployees" method = "get">
        <a href="${pageContext.request.contextPath}/ReadAllEmployees" >Read All Employee Details</a>
    </form>
<br/>
    <a href="${pageContext.servletContext.contextPath}/readEmployeeById">Read Employee By Id</a>
<br/>
<a href="${pageContext.request.contextPath}/employee">back to employee crud</a>
<br/>
<p>${employees}</p>


</body>
</html>
