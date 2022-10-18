<%--
  Created by IntelliJ IDEA.
  User: Dinesh Kumar R
  Date: 10/13/2022
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CREATE EMPLOYEE</title>
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
            <td><input type="text" name="name" pattern="^[a-zA-Z]{1}[a-zA-Z\s]+[^\s]$" ></td>
        </tr>
        <tr>
            <td>Role</td>
            <td>
                <input id="trainer" name="role" type="radio" value="trainer"/>
                    <label for="trainer"> Trainer </label>
                <input id="trainee" name="role" type="radio" value="trainee"/>
                    <label for="trainee"> Trainee </label>
            </td>
        </tr>
        <tr>
            <td>Department</td>
            <td><input type="text" name="department" pattern = "^[a-zA-Z]{1}[a-zA-Z\s]+[^\s]$"></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td>
            <select name="gender" >
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="undefined">Undefined</option>
            </select>
            </td>
        </tr>
        <tr>
            <td>Phone Number</td>
            <td><input type="text" name="phoneNumber" maxlength="10" pattern = "[6-9]{1}[\d]{9}"></td>
        </tr>
        <tr>
            <td>Date Of Birth</td>
            <td><input type="date"  name="dateOfBirth"></td>
        </tr>
        <tr>
            <td>Email Id</td>
            <td><input type="text" name="email"></td>

        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
    <a href="employee.jsp">Back to main page</a>
    <p> <td>${message}</td> </p>
</form>
</body>
</html>
