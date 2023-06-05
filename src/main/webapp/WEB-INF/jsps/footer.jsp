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
<footer>

<div class="foot-txt">
		<a href="/toAboutHappyNews">About Happy News</a>
		<a href="/goToFAQ">FAQ</a>
		<a href="/toContact">Contact Us</a>
		<a href="/toPrivacyPolicy">Privacy Policy</a>
		<a href="/toTermsAndConditions">Terms & Conditions</a>
		</div>

</footer>

</body>
</html>