<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/15/2022
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateEmployee</title>
</head>
<body>
<form action="UpdateEmployee" method="get">
  <table>
    <caption><h2>Update</h2></caption>
    <tr>
      <td> EmployeeID </td>
      <td> = </td>
      <td><input type="text" name="employeeId" value="${employee.employeeId}" readonly></td>
    </tr>
    <tr>
      <th>Particulars</th>
      <th>Data</th>
      <th>change to?</th>
    </tr>
    <tr>
      <td>Name: </td>
      <td>${employee.employeeName}</td>
      <td><input type="text" name="updateName" value="${employee.employeeName}" required></td>
    </tr>
    <tr>
      <td>Email: </td>
      <td>${employee.employeeEmail}</td>
      <td><input type="text" name="updateEmail" value="${employee.employeeEmail}" required></td>
    </tr>
    <tr>
      <td>Role:</td>
      <td>${employee.employeeRole}</td>
      <td><select name="updateRole" required>
        <option value="${employee.employeeRole}">Don't change</option>
        <option value="trainer">Trainer</option>
        <option value="trainee">Trainee</option>
      </select></td>
    </tr>
    <tr>
      <td>Department:</td>
      <td>${employee.employeeDepartment}</td>
      <td><input type="text" name="updateDepartment" value="${employee.employeeDepartment}"></td>
    </tr>
    <tr>
      <td>PhoneNumber:</td>
      <td>${employee.employeePhoneNumber}</td>
      <td><input type="text" name="updatePhoneNumber" value="${employee.employeePhoneNumber}"></td>
    </tr>
    <tr>
      <td>Date Of Birth:</td>
      <td>${employee.employeeDateOfBirth}</td>
      <td><input type="date" name="updateDob" value = "${employee.employeeDateOfBirth}"></td>
    <tr>
      <td>Gender:</td>
      <td>${employee.employeeGender}</td>
    <td><select name="updateGender">
      <option value="${employee.employeeGender}">Don't Change</option>
      <option value="male">Male</option>
      <option value="female">Female</option>
      <option value="undefined">Undefined</option>
    </select></td>
    </tr>
    <tr>
      <td>Email:</td>
      <td>${employee.employeeEmail}</td>
      <td><input type="text" name="updateEmail" value="${employee.employeeEmail}"></td>
    </tr>
    <tr>
      <td><input type="submit" value="update"></td>
    </tr>
    <tr>
      <td><a href="employee.jsp" >Back to main Page</a> </td>
    </tr>
  </table>
</form>
</body>
</html>
