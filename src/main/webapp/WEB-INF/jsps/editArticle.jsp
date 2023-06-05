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
<h2>Edit article</h2>



<form action="/editArticle" method="post">
<input type="hidden" name=articleId value="${article.articleId}"/>
<input type="hidden" name="author" value="${user.username}">

Title:<input type="text" name="title" value="${article.title}" /> <Br>
Text: <input type="text" name="articleText" value="${article.articleText}" /><Br>
Location: <input type="text" name="location" value="${article.location}" /><Br>

Category:<select name="category" value="${article.category}"> 	
				<option value="${article.category}">${article.category}</option>
				<option value="people">people</option>
				<option value="culture">culture</option>
				<option value="environment">environment</option>
				<option value="science">science</option>
				<option value="science">economics</option>
				<option value="lifestyle">lifestyle</option>
				</select>
<input type="submit" value="Save Changes" />
</form>

<form action="/deleteArticle" method="get">
<input type="hidden" name=articleId value="${article.articleId}"/>
<input type="submit" value="Delete" />
</form>


<br><br>
<jsp:include page="footer.jsp" />


</body>
</html>