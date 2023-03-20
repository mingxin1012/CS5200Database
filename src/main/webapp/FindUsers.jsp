<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findusers" method="post">
		<h1>Search for a MelodicMindsUser by UserId</h1>
		<p>
			<label for="userid">UserId</label>
			<input id="userid" name="userid" value="${fn:escapeXml(param.userid)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="userCreate"><a href="usercreate">Create MelodicMindsUser</a></div>
	<br/>
	<h1>Matching MelodicMindsUser</h1>
        <table border="1">
            <tr>
                <th>UserId</th>
                <th>UserName</th>
                <th>Email</th>
                <th>DoB</th>
                <th>Description</th>
                <th>IsRegistered</th>
                <th>Address</th>
                <th>PlayList</th>
                <th>Delete BlogUser</th>
                <th>Update BlogUser</th>
            </tr>
            <c:forEach items="${melodicMindsUser}" var="melodicMindsUser" >
                <tr>
                    <td><c:out value="${melodicMindsUser.getUserId()}" /></td>
                    <td><c:out value="${melodicMindsUser.getUserName()}" /></td>
                    <td><c:out value="${melodicMindsUser.getEmail()}" /></td>
                    <td><fmt:formatDate value="${melodicMindsUser.getDob()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${melodicMindsUser.getDescription()}" /></td>
                    <td><c:out value="${melodicMindsUser.isRegistered()}" /></td>
                    <td><c:out value="${melodicMindsUser.getAddress()}" /></td>
                    <td><a href="userplaylist?userid=<c:out value="${melodicMindsUser.getUserName()}"/>">PlayList</a></td>
                    <td><a href="userdelete?userid=<c:out value="${melodicMindsUser.getUserId()}"/>">Delete</a></td>
                    <td><a href="userupdate?userid=<c:out value="${melodicMindsUser.getUserId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
