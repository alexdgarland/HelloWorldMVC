<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<t:StandardHeader>Error Tests</t:StandardHeader>
<body>
  <center>
  
    <h2>Test What Happens When Errors Occur!</h2>  

    <ul>
    <li><a href="<c:url value="invalidPage"/>">404 Page Not Found</a></li>
    <li><a href="<c:url value="generateServerError"/>">500 Internal Server Error</a></li>
    </ul>
  
  </center>
</body>
</html>