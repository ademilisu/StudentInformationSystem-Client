<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>UNIVERSITY SERVICE </title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/css/save-form.css">

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
		<c:choose>
			<c:when test="${lecture.id != 0}">
				<h3>Update Lecture</h3>
			</c:when>
			<c:otherwise>
				<h3>Add Lecture</h3>
			</c:otherwise>
		</c:choose>
	
		
	</div>

	<div id="right">
		<form:form action="addLecture" modelAttribute="lecture" method="POST">
			<form:hidden path="id"/>		
			<table>
				<tbody>
					<tr>
						<td><label>Lecture Name:</label></td>
						<td><form:input path="lectureName" /></td>
					</tr>
				
					<tr>
						<td><label>Language:</label></td>
						<td><form:input path="language" /></td>
					</tr>					
					
					<tr>
						<td><label>Selection Status:</label></td>
						<td>
						Active<form:radiobutton path="lectureSelection" value="ACTIVE" checked="checked"/>
						Passive<form:radiobutton path="lectureSelection" value="PASSIVE"/>
						</td>
					</tr>	
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		</form:form>

	</div>

</body>

</html>










