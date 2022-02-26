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
			<c:when test="${detail.id != 0}">
				<h3>Update Lecture</h3>
			</c:when>
			<c:otherwise>
				<h3>Add Lecture</h3>
			</c:otherwise>
		</c:choose>
		
	</div>

	<div id="right">
	
		<form:form action="updateLectureDetail?lectureId=${lectureId}" modelAttribute="detail" method="POST">
			<form:hidden path="id"/>
			<table>
				<tbody>
					<tr>
						<td><label>Description:</label></td>
						<td><form:input path="description" /></td>
					</tr>
				
					<tr>
						<td><label>Credit:</label></td>
						<td><form:input path="credit" /></td>
					</tr>					
					
					<tr>
						<td><label>Semester:</label></td>
						<td><form:input path="semester" /></td>
					</tr>
				
					<tr>
						<td><label>Grade:</label></td>
						<td><form:input path="grade" /></td>
					</tr>
					
					<tr>
						<td><label>Type:</label></td>
						<td>
						Compulsory<form:radiobutton path="type" value="COMPULSORY" checked="checked"/>
						Optional<form:radiobutton path="type" value="OPTIONAL"/>
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










