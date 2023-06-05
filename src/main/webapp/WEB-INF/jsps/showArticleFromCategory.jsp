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
<c:url value="/pictures/pic1.jpg" var="pic1" />
</head>
<body>

<jsp:include page="header.jsp" />

<h1 >${category}</h1>
<div class="center" >
<!-- ========Latest Articles================== -->
 	
 	
 	<div class="articles-view">
 	
 	
  <c:forEach var="article" items="${listOfArticles}" varStatus="status">
    <div class="max" id="col${status.index % 3 + 1}">
      
      <img src="${pic1}" class="img-article" />
     <b><a  class="products1 colo" href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
      <small>
      <b> Date: </b>${article.publicationDate}<br>
      <b>Location:</b> ${article.location}<br>
     <b> Author:</b> <a href="/articles/${article.author.username}">${article.author.username}</a><br>

    
   </small>
   
    </div>
  </c:forEach>		
</div>
</div>



<jsp:include page="footer.jsp" />


</body>
</html>