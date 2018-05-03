<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<t:StandardHeader>Hello World</t:StandardHeader>
<body>
  <center>
  
    <h2>Hello World</h2>
  
    <h2>${message}, ${name}!</h2>
  
    <img src="<c:url value="resources/images/helloworld.gif"/>" height=150/>
    <br/>
    <ul>
    <li><a href="<c:url value="/"/>">Back to Home</a></li>
    </ul>
  
  </center>
</body>
</html>