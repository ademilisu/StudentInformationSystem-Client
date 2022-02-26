<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>

<head>
	<title>Info</title>


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
		<h3>Account Information</h3>
	</div>
	
	
	<div id="right">
	
		<div id="content">
				
			<table>
				<tr>
					<th>Email</th>
					<th>First Name</th>
					<th>Last Name</th>		
				</tr>

				<tr>
					<td>${account.email}</td>
					<td>${account.firstName}</td>
					<td>${account.lastName}</td>
					
					
				</tr>
			</table><br>
			<c:if test="${type == 'admin'}">
				<table>
				<tr>
					<th>Phone Number</th>
				</tr>

				<tr>
					<td>${detail.phoneNumber}</td>
				</tr>
				</table><br>
				
				<h3>Address Information</h3>
				<table>
				<tr>
					<th>Province</th>
					<th>District</th>
					<th>Neighborhood</th>
					<th>Street</th>
					<th>Building No</th>
					<th>Apartment No</th>
				</tr>

				<tr>
					<td>${detail.address.province}</td>
					<td>${detail.address.district}</td>
					<td>${detail.address.neighborhood}</td>
					<td>${detail.address.street}</td>
					<td>${detail.address.buildingNo}</td>
					<td>${detail.address.apartmentNo}</td>
				</tr>
				</table>
			</c:if>
			
		</div>
	</div>
	

</body>

</html>









