<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<body>
  
  <h1>Add Employee</h1>
 
  <form:form method="post"  modelAttribute="employeeEntity"  action="addEmployee">
    First Name:<form:input path="firstName"></form:input><br/>
    Last Name: <form:input path="lastName"></form:input><br/>
    Nickname: <form:input path="nickName"></form:input><br/>
    <input type="submit" value="Submit">
  </form:form>
  
  <h4><a href="employee">Back to Employee List</a></h4>

  <h4><a href="<c:url value="/"/>">Back to Home</a></h4>
  
</body>
</html>