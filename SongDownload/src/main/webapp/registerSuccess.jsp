<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="sd" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Success!</title>
</head>
<body>

	<h1 style="color: green">
		<sd:choose>
			<sd:when test="${user.firstName!=''}">
				<h1>Registered Successfully</h1>
				<br />
			Welcome ${user.firstName}</sd:when>
			<sd:otherwise> Already Registered
		<br />
				<a href="index.html">Login here</a>
			</sd:otherwise>
		</sd:choose>
	</h1>
</body>
</html>