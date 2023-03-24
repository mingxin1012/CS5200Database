<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>PlayListTrack</title>
</head>
<body>
<h1>${messages.title}</h1>
<table border="1">
  <tr>
    <th>TrackId</th>

    <th>Track</th>
  </tr>
  <c:forEach items="${trackList}" var="trackList" >
    <tr>
      <td><c:out value="${trackList.getPlayListId()}" /></td>
      <td><c:out value="${trackPost.getTrackName()}"/></td>
    </tr>
  </c:forEach>
</table>
<br/>
<div id="UserPlaylistDeleteTrack"><a href="UserPlaylistDeleteTrack">Delete Track</a></div>
<br/><br/>

</body>
</html>
