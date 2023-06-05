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

<div>
<h2 class="center">Hello ${user.username} </h2>
<p class="center">This is your profile </p><br><br>

	<div class="flex">
		<div class="div1">
			
			<b>Username:</b> ${user.username}<br><br>
			<b>Email:</b> ${user.email}<br><br>
			<b>Role:</b> ${user.role}<br><br>
			<b>Pet name</b> ${user.petName}<br> <br>
			
			
			
			
		</div>
		<div class="div1">
			<a class="button no-deco" href="/goEditProfilePage">Edit Profile</a> <br> <br> <br> 
			<a class="button no-deco" href="/goChangePasswordPage">Change Password</a> <br><br> <br> 
			<a class="button no-deco" href="/articles/${user.username}">Show my articles</a><br><br><br> 
			<a class="button2 no-deco" href="/subscription">My Subscriptions </a><br><br><br> 
		</div>
	</div>
	</div>

	<a href="/" ><small class="colo center">Go to homepage</small></a>


<br><br>
<jsp:include page="footer.jsp" />


</body>
</html>