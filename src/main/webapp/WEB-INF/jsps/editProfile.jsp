<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>

<jsp:include page="header.jsp" />
<br>
<h1>Edit Profile</h1>


<form action="/editUserProfile" method="post">
<input type="hidden" name="username" value="${user.username}"/>
<input type="hidden" name="password" value="${user.password}"/>
<b>Username:</b> ${user.username}<br>
Email:<br> <input type="text" name="email" value="${user.email}" required /><br><br>
Pet name:<br> <input type="text" name="petName" value="${user.petName}" required /><br><br>	
	<input type="submit" value="Save changes"/>


</form>
<br>
<jsp:include page="footer.jsp" />


</body>
</html>