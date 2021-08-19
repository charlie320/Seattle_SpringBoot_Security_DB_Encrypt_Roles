<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<h1>Registration Page</h1>
		<form:form action="registerNewUser" method="post" modelAttribute="newUser">
		<div>
			<label>Username:</label>
			<form:input type="text" path="username" name="username"/>
			<form:errors path="username" cssClass="error" />
		</div>
		<div>
			<label>Password:</label>
			<form:input type="text" path="password" name="password" />
			<form:errors path="password" cssClass="error" />
		</div>
		<div>
			<input type="submit" value="Submit" />
		</div>
	</form:form>
	<a href="./">Cancel</a>
</body>
</html>