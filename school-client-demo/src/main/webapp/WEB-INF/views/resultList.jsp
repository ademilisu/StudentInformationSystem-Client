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
		<h3>Results of ${lectureName}</h3>
	</div>

	<div id="right">
			<c:if test="${type == 'admin'}">
				<input type="button" value="Delete All"
				   onclick="window.location.href='deleteAllResults?lectureId=${lectureId}'; return false;"
				   class="add-button"
				/>
			</c:if><br>
	
			
			<table>
					<tr>
						<th><label>First Name</label></th>
						<th><label>Last Name</label></th>
						<th><label>1.Exam</label></th>
						<th><label>2.Exam</label></th>
						<th><label>Final Exam</label></th>
						<c:if test="${type == 'admin'}">
						<th><label>Action</label></th>
						</c:if>
					</tr>
				
					<c:forEach var="tempResult" items="${results}">	
					<c:if test="${type == 'admin'}">
						<c:url var="deleteLink" value="/deleteResult">
							<c:param name="resultId" value="${tempResult.id}"></c:param>
						</c:url>
					</c:if>
					<tr>
						<td>${students.get(tempResult.studentId).firstName}</td>
						<td>${students.get(tempResult.studentId).lastName}</td>
						<td>${tempResult.examOne}</td>
						<td>${tempResult.examTwo}</td>
						<td>${tempResult.finalExam}</td>
						
						<c:if test="${type == 'admin'}">
						<td><a href="${deleteLink}">Delete</a></td>
						</c:if>	
											
					</tr>
					</c:forEach>
			</table>
	</div>
</body>

</html>










