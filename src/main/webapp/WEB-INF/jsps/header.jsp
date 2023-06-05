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
<c:url value="/pictures/logo.png" var="logo" />
</head>
<body>

	<header>
		<div class="navbar">
			<!-- navigation bar with basic functions -->
			<div>
				<a href="/"><img src="${logo}" class="logo" /></a>
			</div>

			<!----------- menu bar with categories --------------->
			<div class="functions">
				<c:choose>
					<c:when test="${loggedIn == true}">
						<a class="italic">You are logged in as ${user.username}</a>
						<a href="/logout">Logout</a>
						<a href="/showProfile">See Profile</a>
						<a href="/goToInbox">Inbox</a>
						<a href="/goToAddArticle">Add article</a>
					</c:when>
					<c:otherwise>
						<a href="/login">Login</a>
						<a href="/registration">Register</a>
						<!-- <a href="/login">People</a> 
			<a href="/login">Culture</a>
			<a href="/login">Environment</a> 
			<a href="/login">Science</a>
			<a href="/login">Economics</a> 
			<a href="/login">Lifestyle</a> -->

					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="menu-categories ">

			<a href="/People">People</a> 
			<a href="/Culture">Culture</a> 
			<a href="/Environment">Environment</a> 
			<a href="/Science">Science</a>
			<a href="/Economics">Economics</a>
			 <a href="/Lifestyle">Lifestyle</a>
			
		</div>
	</header>



</body>
</html>