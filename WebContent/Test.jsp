<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="display:flex; justify-content:center">
	<div>
	<form action="Login" method="post">
		<input type="text" name="username"
			   placeholder="Enter Username" required><br>
		<input type="password" name="psw"
			   placeholder="Enter Password" required>
		<input type="submit">Login
	</form>
	</div>
	<div>
	<form action="Registrazione" method="post">
		<input type="text" name="username" placeholder="Enter Username" required><br>
		<input type="text" name="password" placeholder="Enter Password" required><br>
		<input type="email" name="email" placeholder="Enter E-mail" required><br>
		<input type="text" name="nick" placeholder="Enter Nickname" required><br>
		<input type="submit">Registrati
	</form>
	</div>
</div>
</body>
</html>