<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<c:url value="/css/addArticle.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
</head>
<body>

	<jsp:include page="header.jsp" />
	<h1>Send Article</h1>

	<form action="/addArticle" method="post" >
		Title:<input type="text" name="title" placeholder="Title" required /><br>
		Text:<input type="text" name="articleText" placeholder="Text" required /><br>
		Location:<select class="select-class" name="location">
					<%-- <option value="${filters.location }" selected="selected">${filters.location }</option> --%>

					<option value="world">world</option>
					<option value="poland">poland</option>
					<option value="ukraine">ukraine</option>
					</select>
		
		Category:<select name="category" required><br> 	
				<option value="people">people</option>
				<option value="culture">culture</option>
				<option value="environment">environment</option>
				<option value="science">science</option>
				<option value="economics">economics</option>
				<option value="lifestyle">lifestyle</option>
				</select>
			<input type="hidden" name="author" value="${user.username}"><br>
			
			<input type="submit" value="Add new article" />
			
			
	</form>
<br><br>


	<jsp:include page="footer.jsp" />


</body>
</html>