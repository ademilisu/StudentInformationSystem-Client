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
		  href="${pageContext.request.contextPath}/resources/css/style.css">
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
		<h3>Update ${lectureName} Results</h3>
	</div>

	<div id="right">
			
		<form:form action="updateResults" modelAttribute="resultList" method="POST">
			<table>
				<tbody>
					<tr>
						<th><label>First Name</label></th>
						<th><label>Last Name</label></th>
						<th><label>1.Exam</label></th>
						<th><label>2.Exam</label></th>
						<th><label>Final Exam</label></th>
						
					</tr>
				
					<c:forEach var="tempResult" varStatus="rs" items="${resultList.results}">	
					<tr>
						<td>${students.get(tempResult.studentId).firstName}</td>
						<td>${students.get(tempResult.studentId).lastName}</td>
						<td><form:input path="results[${rs.index}].examOne"/></td>
						<td><form:input path="results[${rs.index}].examTwo"/></td>
						<td><form:input path="results[${rs.index}].finalExam"/></td>	
						<td><form:hidden path="results[${rs.index}].id"/></td>
						<td><form:hidden path="results[${rs.index}].studentId"/></td>
						<td><form:hidden path="results[${rs.index}].lectureId"/></td>
						
					</tr>
					</c:forEach>
					<tr>
					<td>
					<input type="submit" value="Save" class="save" />
					</td>
					</tr>
				</tbody>	
			</table>
		</form:form>
	</div>
</body>

</html>










