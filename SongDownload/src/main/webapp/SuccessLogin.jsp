<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sd" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP</title>
</head>
<body>
	<h1 style="align-content: center; color: fuchsia;">Project
		${projectName}</h1>
	<h1 style="color: green">
		<sd:choose>
			<sd:when test="${user.firstName!=null}">Welcome ${user.firstName}</sd:when>
			<sd:otherwise>Login Failed</sd:otherwise>
		</sd:choose>
	</h1>
</body>
</html>