<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<t:StandardHeader>Add Employee</t:StandardHeader>
<body>
  
  <h2>Update Employee</h2>
 
  <form:form method="post"  modelAttribute="employeeEntity"  action="updateEmployee">
    <table>
    <tr><th>ID</th><td>${employeeEntity.id}<form:input path="id" hidden="true"></form:input></td></tr>
    <tr><th>First Name</th><td><form:input path="firstName"></form:input></td></tr>
    <tr><th>Last Name</th><td><form:input path="lastName"></form:input></td></tr>
    <tr><th>Nickname</th><td><form:input path="nickName"></form:input></td></tr>
    </table>
    <br/>
    <input type="submit" value="Submit Updated Employee Details">
  </form:form>
  
  <ul>
    <li><a href="employees">Back to Employee List</a></li>
    <li><a href="<c:url value="/"/>">Back to Home</a></li>
  </ul>
  
</body>
</html>