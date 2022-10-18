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

<%-- Displays Employee using id  --%>
    <a href="readEmployeeById.jsp">Read Employee By Id</a>



</body>
</html>
