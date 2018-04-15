<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - Employee List</title>
<link href="<c:url value="resources/css/main.css"/>" rel="stylesheet" />
</head>
<body>
  <center>
 
    <h2>Employee List</h2>
 
    <table>
    <tr><th>ID</th><th>Name</th></tr>
    <c:forEach items="${employeeList}" var="employee">
      <tr><td>${employee.id}</td><td>${employee.getFullName()}</td></tr>
    </c:forEach>
    </table>
    
     <h3>
      <a href="showEmployeeForm">Add Employee</a>
     </h3>
     
     <h4>
      <a href="<c:url value="/"/>">Back to Home</a>
     </h4>
 
  </center>
</body>
</html>