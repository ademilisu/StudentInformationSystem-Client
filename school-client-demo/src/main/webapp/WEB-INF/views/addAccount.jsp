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
				onclick="window.location.href='home'; return false"
				class=add-button
				/>

		</div>
	</div>
	
	
	
	<div id="header">
		<h3>Add Account</h3>
	</div>

	<div id="right">
		
		<c:if test="${username != null}">
		<h4 style="color:#090073;">The user: ${username} has been added</h4>
		</c:if>
		<br>
		
		<form:form action="signUp" modelAttribute="userData" method="POST">
					
			<table>
				<tbody>
				
					<tr>
						<td><label>Type:</label></td>
						<td>
						Student<form:radiobutton path="type" value="student" checked="checked"/>
						Instructor<form:radiobutton path="type" value="instructor"/>
						Admin<form:radiobutton path="type" value="admin"/>
						</td>
					</tr>
					
					<tr>
						<td><label>Username:</label></td>
						<td><form:input path="username" /></td>
					</tr>
				
					<tr>
						<td><label>Password:</label></td>
						<td><form:input path="password" /></td>
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










