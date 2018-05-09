<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<t:StandardHeader>Employee List</t:StandardHeader>
<body>
  <center>
 
    <h2>Employee List</h2>
 
    <table>
    <tr><th>ID</th><th>Name</th></tr>
    <c:forEach items="${employeeList}" var="employee">
      <tr>
        <td>${employee.id}</td>
        <td>${employee.getFullName()}</td>
        <td>
        <c:url var="deleteUrl" value="/deleteEmployee"/>    
        <form action="${deleteUrl}" method="POST">
          <input id="employee" name="employeeId" type="hidden" value="${employee.id}"/>
          <input type="submit" value="delete" onClick="return confirm('Are you sure you want to delete employee with ID ${employee.id}?')"/>
        </form>
        </td>
        <td>
        <c:url var="updateUrl" value="/showUpdateEmployeeForm"/>    
        <form action="${updateUrl}" method="POST">
          <input id="employee" name="employeeId" type="hidden" value="${employee.id}"/>
          <input type="submit" value="update"/>
        </form>
        </td>
      </tr>
    </c:forEach>
    </table>
    
    <ul>
      <li><a href="showAddEmployeeForm">Add Employee</a></li>
      <li><a href="<c:url value="/"/>">Back to Home</a></li>
    </ul>
      
  </center>
</body>
</html>