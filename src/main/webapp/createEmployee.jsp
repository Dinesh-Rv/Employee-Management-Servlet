<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/12/2022
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CREATE EMPLOEE</title>
</head>
<body>
<h1><%= "Employee Creation" %></h1>
<br>

<form action="EmployeeServlet" method="post">
    <table>

        <tr>
            <th></th>
            <th>Enter the values here</th>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name" required></td>
        </tr>
        <tr>
            <td>Role</td>
            <%--<td><input type="text" name="role" required></td>--%>
            <td>
                <input type="radio" id ="trainer" name="role" value="trainer" />
                    <label for="trainer"> Trainer </label>
                <input type="radio" id ="trainee" name="role" value="trainee" />
                    <label for="trainer"> Trainee </label>
            </td>
        </tr>
        <tr>
            <td>Department</td>
            <td><input type="text" name="department" required></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td><input type="text" name="gender" required></td>
        </tr>
        <tr>
            <td>Phone Number</td>
            <td><input type="text" name="phoneNumber" required></td>
        </tr>
        <tr>
            <td>Date Of Birth</td>
            <td><input type="text" name="dateOfBirth" required></td>
        </tr>
        <tr>
            <td>Email Id</td>
            <td><input type="text" name="email" required></td>

        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
    <p> <td>${message}</td> </p>
</form>
</body>
</html>
