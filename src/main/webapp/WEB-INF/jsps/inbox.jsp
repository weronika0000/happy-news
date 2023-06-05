<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inbox</title>
<c:url value="/css/style2.css" var="jstlCss2" />
<link href="${jstlCss2}" rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" /><br>
	
	<h2>Inbox</h2>
	
	
	
	
	<c:choose>
		<c:when test="${role == true}">
		
	
	<br>
	<h3>Requests</h3>
	<table class="table-in-center">
		<tr>
			<th>From</th>
			<th>Message</th>
			<th>Time</th>
		</tr>
		<c:forEach items="${allRequests}" var="request">
			<tr>
				<%-- <td>${request.sender.username}</td> --%>
				<td>${request.messageText}</td>
				<td>${request.sendDate}</td>
				<td class="last-column">
					<form action="/confirmRequest">
						<input type="hidden" name="requestId" value="${request.requestId}"/> 
						<input type="submit" class="submit-button-messages" value="Confirm Request"/>
					</form>
				</td>
				<td class="last-column">
					<form action="/rejectRequest">
						<%-- <input type="hidden" name="username" value="${request.sender.username}"/> --%>
						<input type="hidden" name="requestId" value="${request.requestId}"/>
						<input type="submit" class="submit-button-messages" value="Reject Request"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
		</c:when>
		<c:otherwise>
					<tr>
			
			<th>Message</th>
			<th>Time</th>
			<br><br>
		</tr>
		<c:forEach items="${allReceivedMessages}" var="receivedMessage">
			<tr>
				<%-- <td>${receivedMessage.sender.username}</td> --%>
				<td>${receivedMessage.messageText}</td>
				
				<td>${receivedMessage.sendDate}</td>
				<td class="last-column">
					<form action="/deleteMessage">
						<input type="hidden" name="messageId" value="${receivedMessage.messageId}"/> 
						<input type="submit" class="submit-button-messages" value="Delete Message"/>
					</form>
				
				<br><br>
			</tr>
		</c:forEach>
					</c:otherwise>
				</c:choose>
	
	
	<br><br>	
	<br>
	<div class="links-in-messages">
		
		<a class="colo center" href="/">Go to Homepage</a>
	</div>
	<br>
	</div>
	
	<jsp:include page="footer.jsp" />
</body>
</html>