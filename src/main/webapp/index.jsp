<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - HelloWorld Index Page</title>
<link href="<c:url value="resources/css/main.css"/>" rel="stylesheet" />
</head>
<body>
  <center>
  
    <h2>"Hello World MVC" - Index Page</h2>
  
    <h3>
      <a href="hello?name=Alex">Hello World</a>
    </h3>
  
    <h3>
      <a href="employees">Employee List</a>
    </h3>
  
    <h3>
      <a href="showEmployeeForm">Add Employee</a>
    </h3>
  
    <img src="<c:url value="resources/images/index.jpg"/>" height=200/>
  
  </center>
</body>
</html>