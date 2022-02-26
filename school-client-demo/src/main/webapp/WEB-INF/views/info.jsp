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
			<c:if test="${type == 'admin'}">
				<input type="button" value="Add Account"
				 onclick="window.location.href='showNewAccountForm'; return false;"
				 class="add-button"
				/>
				<input type="button" value="Students"
				 onclick="window.location.href='showStudentList'; return false;"
				 class="add-button"
				/>
				<input type="button" value="Instructors"
				 onclick="window.location.href='showInstructorList'; return false;"
				 class="add-button"
				/>
			</c:if>
			<c:if test="${type == 'student' || type == 'instructor'}">
				<input type="button" value="Account"
				   onclick="window.location.href='accountInformation'; return false;"
				   class="add-button"
				/>
				<input type="button" value="My Lectures"
				   onclick="window.location.href='myLectures'; return false;"
				   class="add-button"
				/>
			</c:if>
			<c:if test="${type != null}">
				<input type="button" value="All Lectures"
				   onclick="window.location.href='lectureList'; return false;"
				   class="add-button"
				/>
				
				<input type="button" value="Result"
				   onclick="window.location.href='showResultsPage'; return false;"
				   class="add-button"
				/>
			</c:if>
			<c:if test="${type == 'student'}">
				<input type="button" value="Transcript"
				   onclick="window.location.href='showMyTranscript'; return false;"
				   class="add-button"
				/>
			</c:if>
			<input type="button" value="Info"
				   onclick="window.location.href='info'; return false;"
				   class="add-button"
				/>
		</div>
	</div>
	
	
	
	<div id="header">
		<h3>Info</h3>
	</div>
	
	
	<div id="right">
		<div id="content">
				<c:if test="${account == 'new'}">
					Change your password and update your account 
					<input type="button" value="Change Password"
					onclick="window.location.href='showChangePasswordForm'; return false;"
					class="add-button"
					/>
					<input type="button" value="Update Account"
					onclick="window.location.href='showUpdateAccountForm'; return false;"
					class="add-button"
					/>
					<br>
				</c:if>		
		</div>
	</div>
</body>

</html>









