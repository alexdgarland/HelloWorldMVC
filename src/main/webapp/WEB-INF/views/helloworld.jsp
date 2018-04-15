<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC - HelloWorld</title>
<link rel="stylesheet" media="screen" href="https://fontlibrary.org/face/gidole-regular" type="text/css"/>
<link href="<c:url value="resources/css/main.css"/>" rel="stylesheet" />
</head>
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