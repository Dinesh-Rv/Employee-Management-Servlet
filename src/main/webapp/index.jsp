<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>The Office</title>
</head>
<body>
<h1><%= "Employee Management" %></h1>
<br/>
<p>Current Date: <%= java.time.LocalDate.now() %> </p>
<br/>


<a href= "employee.jsp">EMPLOYEE CRUD OPERATION</a>
</body>
</html>