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
<br>



	
	<div class="center">
	<span style="color: red">${errorDate}</span><br>
	<span style="color: red">${errorNoDate}</span><br>
	<span style="color: red">${errorNothingToSearch}</span><br><br>
	</div>
	${test}
	<div class="center">
		<form action="/goToSearchingPage" method="get">
			<input class="search-input" type="text" name="articleTitle" placeholder="Search an article" value = "${searchedPhrase}">
			<input class=button type="submit" value="Search">
		</form>
	</div>

	<br>



</body>
</html>