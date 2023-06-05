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
<h1>REGISTER</h1>
<span style="color: red">${errorMessage}</span>


<br>
	<form action="/registration" method="post">
		Username: <input type="text" name="username" required /><br>
		Password: <input type="password" name="password" required /><br>
		Email: <input type="email" name="email" required />
		<br> Question for reseting password: <br> What is your
		favourite pet name? <br> <input type="text" name="petName"
			required /><br> <input type="submit" value="Submit">
	</form>

<br>
	<br>

<jsp:include page="footer.jsp" />


</body>
</html>