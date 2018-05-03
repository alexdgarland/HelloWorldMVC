<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<t:StandardHeader>404 - Page Not Found</t:StandardHeader>
<body>
  <center>
  
    <h2>That's Not A Thing</h2>

    <iframe width="560" height="315" src="https://www.youtube.com/embed/NcA_j23HuDU?autoplay=1" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
    <br/>

    <ul>
    <li><a href="<c:url value="/errorTest"/>">Back to Error Tests</a></li>
    <li><a href="<c:url value="/"/>">Back to Home</a></li>
    </ul>
  
  </center>
</body>
</html>
