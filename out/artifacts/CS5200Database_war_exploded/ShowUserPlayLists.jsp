<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>PlayList</title>
</head>
<body>
    <h1>${messages.title}</h1>
    <table border="1">
        <tr>
            <th>PlayListId</th>

            <th>Created</th>
            <th>Delete PlayList</th>
        </tr>
        <c:forEach items="${playList}" var="playList" >
            <tr>
                <td><c:out value="${playList.getPlayListId()}" /></td>
                <td><fmt:formatDate value="${blogPost.getCreated()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                <td><a href="deleteplaylist?playListid=<c:out value="${playlist.getPlayListId()}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
