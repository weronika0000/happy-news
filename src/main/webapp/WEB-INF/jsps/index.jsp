<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<c:url value="/css/style.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br>
	<jsp:include page="searchbar.jsp" />

	<br></br>
	<hr>
	<div>

		<span style="color: red">${errorMessage}</span>

<!-- ========Top Articles================== -->
		<h1>Top Articles</h1>
		<div class="center">
			<div class="articles-view">
				
				<c:forEach var="article" items="${topArticles}" varStatus="status">
					<div class="max" id="col${status.index % 3 + 1}">

						<img src="pictures/pic1.jpg" class="img-article" /> <b><a
							class="products1 colo"
							href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
						<small> <b> Date:</b> ${article.publicationDate}<br>
							<b> Location:</b> ${article.location}<br> 
							<b> Author:</b> <a href="/articles/${article.author.username}">${article.author.username}</a><br>
							<b>Category:</b> <a href="/${article.category}">${article.category}</a><br></small>
					</div>
				</c:forEach>
				
			</div>
		</div>

<!-- ========Latest Articles================== -->
		<h1>Latest Articles</h1>
		<div class="center">
			<div class="articles-view">

				<c:forEach var="article" items="${latestArticles}">
					<div class="max" id="col${status.index % 3 + 1}">
						 <img src="pictures/pic1.jpg" class="img-article" />
							<b><a class="products1 colo " href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
						<small>
							<b>Date: </b>${article.publicationDate}<br> 
							<b>Location:</b>${article.location}<br>
							<b>Author:</b> <a  href="/articles/${article.author.username}">${article.author.username}</a><br>
							<b>Category:</b> <a href="/${article.category}">${article.category}</a><br>
						</small>

					</div>
				</c:forEach>
			</div>
		</div>
		
		<!-- ========RECOMMENDED ARTICLES================== -->

		<c:choose>
			<c:when test="${loggedIn == true}">
				<c:choose>
					<c:when test="${articlesByUserCategories.size() != 0 }">
						<h1>Recommended Articles</h1>


						<div class="center">
							<div class="articles-view">
								<c:forEach var="article" items="${articlesByUserCategories}">
									<div class="max" id="col${status.index % 3 + 1}">
										<small> <img src="pictures/pic1.jpg"
											class="img-article" /> <b>
											<a class="products1 colo" href="goToArticlePage/${article.articleId}">${article.title}</a></b><br>
											<b>Date: </b>${article.publicationDate}<br> 
											<b>Location:</b>${article.location}<br>
											<b>Author:</b> <a  href="/articles/${article.author.username}">${article.author.username}</a><br> 
											<b>Category:</b> <a href="/${article.category}">${article.category}</a><br>
										</small>

									</div>
								</c:forEach>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						 <div class="sub in-center">
 <p class=colo> Stay positive and stay informed with <b>Happy News! </b>
  <br>Subscribe now to get daily updates on the good news happening around the world. <br>
  Your daily dose of happiness awaits you. <br> 

<br><a class="big-font-button" href="/subscription"> Subscribe Happy News</a><br>
 <small class=colo>and start spreading positivity.</small><br></p>
</div>
  
					</c:otherwise>
				</c:choose>
			</c:when>
		</c:choose>

</div>
		<!-- ========end of content================== -->
		<p><p>
		
	<a class="colo center" href="/">Go to Homepage</a>
	<br>
	<br>

	<jsp:include page="footer.jsp" />
</body>
</html>