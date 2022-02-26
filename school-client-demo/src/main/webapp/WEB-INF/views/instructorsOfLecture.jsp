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
				onclick="window.location.href='lectureList'; return false"
				class=add-button
				/>
		</div>
	</div>
	
	<div id="header">
		<h3>Lecture List</h3>
	</div>
	
	
	<div id="right">	
		
		<div id="content">
		
			<h3>Instructors Of Lecture</h3>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempIOfLecture" items="${instructorsOfLecture}">
					<c:if test="${type == 'admin'}">
						<c:url var="removeLink" value="/removeInstructorFromLecture?">
							<c:param name="instructorId" value="${tempIOfLecture.id}"></c:param>
							<c:param name="lectureId" value="${lectureId}"></c:param>
						</c:url>
					</c:if>
				
					<tr>
						<td>${tempIOfLecture.firstName}</td>
						<td>${tempIOfLecture.lastName}</td>
						<td>
							<c:if test="${type == 'admin'}">
							 | <a href="${removeLink}">Remove Instructor</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<h3>Instructors</h3>
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempInstructor" items="${instructors}">
					<c:url var="detailLink" value="/instructorDetails">
						<c:param name="instructorId" value="${tempInstructor.id}"></c:param>
					</c:url>
					<c:if test="${type == 'admin'}">
						<c:url var="addLink" value="/addInstructorToLecture?lectureId=${lectureId}">
							<c:param name="instructorId" value="${tempInstructor.id}"></c:param>
							<c:param name="lectureId" value="${lectureId}"></c:param>
						</c:url>
					</c:if>
				
					<tr>
						<td>${tempInstructor.firstName}</td>
						<td>${tempInstructor.lastName}</td>
						<td>
							<a href="${detailLink}">Detail</a>
							<c:if test="${type == 'admin'}">
							 | <a href="${addLink}">Add Instructor</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	

</body>

</html>









