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

<c:choose>
		<c:when test="${role == true}">
	<h1>Your article is posted!</h1>
	
		</c:when>
		<c:otherwise>
					<h1>Your article has been sent to admin for approval</h1>
					</c:otherwise>
				</c:choose>



<jsp:include page="footer.jsp" />


</body>
</html>