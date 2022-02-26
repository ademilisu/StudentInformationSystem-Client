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
		<h3>List Of Students</h3>
	</div>
	
	
	<div id="right">		
		<div id="content">

			<form:form action="searchStudent" modelAttribute="student"  method="GET">
				<form:input path="email"/>
				<input type="submit" value="Search" class="save" />
			</form:form>
			
			
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<c:if test="${type == 'admin'}">
						<th>Action</th>
					</c:if>
					
				</tr>
				
				<c:forEach var="tempStudent" items="${students}">
				
					<c:url var="detailLink" value="/studentDetails">
						<c:param name="email" value="${tempStudent.email}"></c:param>
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>
				<c:if test="${type == 'admin'}">	
					<c:url var="deleteLink" value="/deleteStudent">
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>
				</c:if>
					
	
					<tr>
						<td><a 
							<c:if test="${type != 'student'}">
							href="${detailLink}"
							</c:if>
							>
						${tempStudent.firstName}</a>
						</td>
						<td>${tempStudent.lastName}</td>
						<c:if test="${type == 'admin'}">
							<td>
								<a href="${deleteLink}">Delete</a>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			
			
			
			</table>
		</div>
	</div>
	

</body>

</html>









