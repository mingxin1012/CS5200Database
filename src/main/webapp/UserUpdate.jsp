<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a User</title>
</head>
<body>
	<h1>Update BlogUser</h1>
	<form action="userupdate" method="post">
		<p>
			<label for="userid">UserId: ${fn:escapeXml(param.userid)}</label>
			<input type="hidden" id="userid" name="userid" value="${fn:escapeXml(param.userid)}">
		</p>
		
		<p>
			<label for="username">New UserName</label>
			<input id="username" name="username" value="${melodicMindsUser.getUserName()}">
		</p>
		
		<p>
			<label for="email">New email</label>
			<input id="email" name="email" value="${melodicMindsUser.getEmail()}">
		</p>
		
		<p>
			<label for="dob">New DoB (yyyy-mm-dd)</label>
			<input id="dob" name="dob" value="<fmt:formatDate value="${melodicMindsUser.getDob()}" pattern="yyyy-MM-dd" />">
		</p>
		
		<p>
			<label for="description">New Description</label>
			<input id="description" name="description" value="${melodicMindsUser.getDescription()}">
		</p>
		
		<p>
			<label for="address">New Address</label>
			<input id="address" name="address" value="${melodicMindsUser.getAddress()}">
		</p>
		
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>