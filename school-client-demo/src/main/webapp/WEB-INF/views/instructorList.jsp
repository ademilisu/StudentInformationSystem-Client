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
		<h3>List Of Instructors</h3>
	</div>
	
	
	<div id="right">		
		<div id="content">

			<form:form action="searchInstructor" modelAttribute="instructor"  method="GET">
				<form:input path="email"/>
				<input type="submit" value="Search" class="save" />
			</form:form>
			
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempInstructor" items="${instructors}">
					<c:url var="detailLink" value="/instructorDetails">
						<c:param name="email" value="${tempInstructor.email}"></c:param>
						<c:param name="instructorId" value="${tempInstructor.id}"/>
					</c:url>
					
					<c:url var="deleteLink" value="/deleteInstructor">
						<c:param name="instructorId" value="${tempInstructor.id}"/>
					</c:url>
	
					<tr>
						<td><a href="${detailLink}">${tempInstructor.firstName}</a></td>
						<td>${tempInstructor.lastName}</td>
						<td>
						<a href="${deleteLink}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>









