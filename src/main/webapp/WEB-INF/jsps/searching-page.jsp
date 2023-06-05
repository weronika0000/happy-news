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

<br>
	<br>
	<jsp:include page="searchbar.jsp" />
	
	<div>
		<h2>More filters:</h2>

		<div class="filters-container">
			<form class="form-product-filter" action="/filtered" method="post">
	<input type="hidden" name="searchedPhrase" value="${searchedPhrase }"> 
				<div class="filter-item">
				<span class="text-filters">Category:</span>
				<select class="select-class" name="category">
					<option value="${filters.category }" selected="selected">${filters.category }</option>

					<option value="people">people</option>
					<option value="culture">culture</option>
					<option value="environment">environment</option>
					<option value="science">science</option>
					<option value="economics">economics</option>
					<option value="lifestyle">lifestyle</option>
					<option value="No filter">No filter</option>
					</select>
				 <input class="submit-filters" type="submit" value="Filter" />
				</div>
				<div class="filter-item">
				<span class="text-filters">Location:</span>
				<select class="select-class" name="location">
					<option value="${filters.location }" selected="selected">${filters.location }</option>

					<option value="world">world</option>
					<option value="poland">poland</option>
					<option value="ukraine">ukraine</option>
					<option value="No filter">No filter</option>
					
					</select>
				 <input class="submit-filters" type="submit" value="Filter" />
				
				<span style="color: red">${errorAuthor}</span><br>
				<span class="text-filters">Author:</span>
				<input class="search-input" type="text" name="author" placeholder="Search an article" >
			<input class="submit-button" type="submit" value="Filter">
				
				<%-- <div class="filter-item">
				<span class="text-filters">Author:</span>
				<select class="select-class" name="author">
					<option value="${filters.author }" selected="selected">${filters.author }</option>

					<option value="weronika">weronika</option>
					<option value="vlad">vlad</option>
					<option value="ewa">ewa</option>
					<option value="No filter">No filter</option>
					
					</select>
				 <input class="submit-filters" type="submit" value="Filter" />
				</div> --%>
				
				
				</form>
		</div>
	</div>
	<br>
	<br>
	<div class="products">

		<c:forEach items="${foundArticles}" var="article" varStatus="status">

			<div id="col${status.index % 3 + 1}">
				<a class=products1 href="/goToArticlePage/${article.articleId}">
					
				<br> <b>${article.title}</b> <br>
				
						
				<br> <a class=violetButton
					href="/goToArticlePage/${article.articleId}">See Details</a> <br>
				<br>
				</a>
			</div>

		</c:forEach>
	</div>okay


<jsp:include page="footer.jsp" />


</body>
</html>