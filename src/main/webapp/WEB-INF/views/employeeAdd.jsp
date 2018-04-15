<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<link href="<c:url value="resources/css/main.css"/>" rel="stylesheet" />
<body>
  
  <h2>Add Employee</h2>
 
  <form:form method="post"  modelAttribute="employeeEntity"  action="addEmployee">
    <table>
    <tr><th>First Name</th><td><form:input path="firstName"></form:input></td></tr>
    <tr><th>Last Name</th><td><form:input path="lastName"></form:input></td></tr>
    <tr><th>Nickname</th><td><form:input path="nickName"></form:input></td></tr>
    </table>
    </br>
    <input type="submit" value="Submit">
  </form:form>
  
  <h4><a href="employees">Back to Employee List</a></h4>

  <h4><a href="<c:url value="/"/>">Back to Home</a></h4>
  
</body>
</html>