<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<t:StandardHeader>HelloWorld Index Page</t:StandardHeader>
<body>
  <center>
  
    <h2>"Hello World MVC" - Index Page</h2>
  
    <img src="<c:url value="resources/images/index.jpg"/>" height=300/>
    <br/>  
    <ul>
      <li><a href="hello?name=Alex">Hello World</a></li>
      <li><a href="employees">Employee List</a></li>
      <li><a href="showEmployeeForm">Add Employee</a></li>
    </ul>
  
  </center>
</body>
</html>