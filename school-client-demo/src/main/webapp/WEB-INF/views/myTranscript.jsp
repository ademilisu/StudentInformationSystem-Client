<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>UNIVERSITY SERVICE </title> 
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/css/style.css">
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/form-style.css">
</head>
<%	
	String info = null;
	String type = null;
	Cookie[] cookie = request.getCookies();
	if(cookie != null){
		for(Cookie temp : cookie){
			if(temp.getName().equals("type")){
				type = temp.getValue();
			}
			if(temp.getName().equals("info")){
				info = temp.getValue();
			}
		}
	}
	pageContext.setAttribute("type", type);	
	pageContext.setAttribute("info", info);
%>
<body>
	
	<div id="wrapper">
		<div id="mainheader">
			<h2>STUDENT INFORMATION SYSTEM</h2>
		</div>
	</div>
	
	<div id="rightBar">
		<c:if test="${type != null}">
			 ${info}
		</c:if>
		<c:choose>
			<c:when test="${type != null}">
				<input type="button" value="Logout"
				   onclick="window.location.href='logout'; return false;"
				   class="add-button"
				/>
			</c:when>
			<c:otherwise>
				<input type="button" value="Login"
				   onclick="window.location.href='showLoginForm'; return false;"
				   class="add-button"
				/>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div id="left">
		<div id="content">
			<input type="button" value="Back"
				onclick="window.location='<%=request.getHeader("referer")%>'; return false"
				class=add-button
				/>
		</div>
	</div>
	
	
	
	<div id="header">
		<h3>My Transcript</h3>
	</div>

	<div id="right">		
			<table>
					<tr>
						<th><label>Lecture Name</label></th>
						<th><label>Score</label></th>
					</tr>
				
					<c:forEach var="tempTranscript" items="${transcripts}">	
					<tr>
						<td>${lectures.get(tempTranscript.lectureId).lectureName}</td>
						<td>${tempTranscript.score}</td>			
					</tr>
					</c:forEach>
			</table>
	</div>
</body>

</html>










