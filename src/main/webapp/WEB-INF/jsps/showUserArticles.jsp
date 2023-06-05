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

<h1 >${username} articles </h1>
<div class="center" >
<!-- ========Latest Articles================== -->
 	
 	<br>
 	<div >
 	
 	
  <c:forEach var="article" items="${listOfUserArticles}" >
    <div  >
      
      <img src="${pic1}" class="img-article" />
     <b><a  class="products1 colo" href="/goToArticlePage/${article.articleId}">${article.title}</a></b><br>
      <small>
      <b>Date:</b> ${article.publicationDate}, 
      <b>Location:</b>${article.location}, 
      <b>Category:</b> <a  href="/${article.category}">${article.category}</a><br>
      
      
      
      
   </small>
   
    </div>
  </c:forEach>		
</div>
</div>




<br>
<br>
<jsp:include page="footer.jsp" />


</body>
</html>