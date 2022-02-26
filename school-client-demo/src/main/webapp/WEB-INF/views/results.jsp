<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<title>Home</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/css/style.css" />
		 

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
	
	<div id ="left">
			<div id="content">
				<input type="button" value="Back"
				onclick="window.location.href='home'; return false"
				class=add-button
				/>
		</div>
	</div>
	
	<div id="header">
		<c:choose>
			<c:when test="${type == 'admin'}">
			<h3>Lecture Results</h3>
			</c:when>
			<c:otherwise>
			<h3>My Lecture Results</h3>
			</c:otherwise>
		</c:choose>
		
	</div>
	
	
	<div id="right">	
		<div id="content">
						
			<table>
				<tr>
					<th>Lecture Name</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempLecture" items="${lectures}">
					<c:url var="detailLink" value="/lecturedetail">
						<c:param name="lectureName" value="${tempLecture.lectureName}"></c:param>
						<c:param name="lectureId" value="${tempLecture.id}"/>
					</c:url>
					<c:url var="showResultLink" value="/showResults">
						<c:param name="lectureName" value="${tempLecture.lectureName}"></c:param>
						<c:param name="lectureId" value="${tempLecture.id}"/>
					</c:url>
					<c:if test="${type != 'student'}">
						<c:url var="updateResultLink" value="/showUpdateResultsForm">
							<c:param name="lectureName" value="${tempLecture.lectureName}"></c:param>
							<c:param name="lectureId" value="${tempLecture.id}"></c:param>
						</c:url>
						
					</c:if>
				
					<tr>
						<td><a href="${detailLink}">${tempLecture.lectureName}</a></td>
						<td>
							<a href="${showResultLink}">Show</a>
							<c:if test="${type != 'student'}">
							 | <a href="${updateResultLink}">Update</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	

</body>

</html>









