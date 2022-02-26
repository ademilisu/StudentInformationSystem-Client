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
				onclick="window.location='<%=request.getHeader("referer")%>'; return false"
				class=add-button
				/>
		</div>
	</div>
	
	<div id="header">
		<h3>${detail.lecture.lectureName} Details</h3>
	</div>
	
	<div id="right">
		<div id="content">
			<c:if test="${type == 'admin'}">
			<input id="${lectureId}" type="button" value="Update Detail"
			onclick="window.location.href='showUpdateLectureForm?lectureId=${lectureId}'; return false"
			class=add-button
			/>
			</c:if>
			
			<h3>Details</h3>
			<table>				
				<tr>
					<th>Lecture Name</th>
					<th>Type</th>
					<th>Semester</th> 
					<th>Grade</th>
					<th>Credit</th>
				</tr>
					<tr>
						<td>${detail.lecture.lectureName}</td>
						<td>${detail.type}</td>	
						<td>${detail.semester}</td>
						<td>${detail.grade}</td>
						<td>${detail.credit}</td>
					</tr>
			</table><br>
			
			<table>				
				<tr>
					<th>Lecture Description</th>
				</tr>
					<tr>
						<td>${detail.description}</td>
					</tr>
			</table><br>
			
			<h3>Instructors Of ${detail.lecture.lectureName}</h3>
			
			<c:if test="${type == 'admin'}">
			<input id="${lectureId}" type="button" value="Add Instructor"
			onclick="window.location.href='showAddInstructorList?lectureId=${lectureId}'; return false"
			class=add-button
			/>
			</c:if>
			
			<table>				
				<tr>
					<th>First Name</th>
					<th>Last Name</th> 
				</tr>
				<c:forEach var="tempInstructor" items="${instructors}">
					<c:url var="detailLink" value="/instructorDetails">
						<c:param name="instructorId" value="${tempInstructor.id}"></c:param>
					</c:url>
					
					<tr>
						<td><a	href="${detailLink}">${tempInstructor.firstName}</a></td>
						<td>${tempInstructor.lastName}</td>

					</tr>
			
				</c:forEach>
			</table>
		</div><br> 
	
		<a href="studentsOfLecture?lectureId=${lectureId}">Students Of ${detail.lecture.lectureName}</a>
	</div>
</body>

</html>









