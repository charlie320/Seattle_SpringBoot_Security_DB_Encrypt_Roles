<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<h1>Login Page</h1>
	<p>${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
	<form action="login" method="post">
		<div>
			<label>Username:</label> <input type="text" name="username">
		</div>
		<div>
			<label>Password:</label> <input type="text" name="password">
		</div>
		<div>
			<input type="submit" value="Submit" />
		</div>
	</form>
	<a href="/register">Register Here</a>
</body>
</html>