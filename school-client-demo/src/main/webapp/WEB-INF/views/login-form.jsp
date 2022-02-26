<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

<body>
	
	<div id="wrapper">
		<div id="mainheader">
			<h2>STUDENT INFORMATION SYSTEM</h2>
		</div>
	</div>
	
	<div id="rightBar">
		
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
		<h3>Login</h3>
	</div>

	<div id="right">
		<h3>${error}</h3>
		<form:form action="login" modelAttribute="user" method="POST">
					
			<table>
				<tbody>
				
					
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
						<td><input type="submit" value="login" class="save" /></td>
					</tr>

				
				</tbody>
			</table>
		
		
		</form:form>
	
	
	</div>

</body>

</html>










