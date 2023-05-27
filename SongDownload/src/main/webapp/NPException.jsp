<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NPE page</title>
</head>
<body>
	<h1>Java Exception Details</h1>
	<h2>{pageContext.exception{"class"}}</h2>
	<h2>{pageContext.exception.message}</h2>
</body>
</html>