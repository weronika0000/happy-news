<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="header.jsp" /><br><br>

	<h2>Reset Password</h2>
	<span style="color:red">${errorMessage}</span>
	<form action="/recover-password" method="post">
		Email: <input type="text" name="email"/><br>
		Pet Name: <input type="password" name="petName"/><br>	
		New Password: <input type="password" name="newPassword"/><br>
		Confirm New Password: <input type="password" name="confirmNewPassword"/><br>
		<input type="submit"/>
	</form>
	
	<br>
	<jsp:include page="footer.jsp" />
	<br><br>
</body>
</html>