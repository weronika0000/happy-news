<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" /><br><br>

	<h2>Change Password</h2>
	<span style="color:red">${errorMessage}</span>
	<form action="/change-password" method="post">
		<%-- <input type="hidden" name="username" value="${user.username}"/>	 --%>
		Current Password: <input type="password" name="currentPassword"/><br>
		New Password: <input type="password" name="newPassword"/><br>
		Confirm New Password: <input type="password" name="confirmNewPassword"/><br>
		<input type="submit"/>
		<br><br><a href="/">Go to Homepage</a>
		
	</form>
	
	<jsp:include page="footer.jsp" />
</body>
</html>