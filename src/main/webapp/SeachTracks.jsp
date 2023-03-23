<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Tracks</title>
</head>
<body>
	<form action="searchtracks" method="get">
		<h1>Search for a Tracks by Track Name</h1>
		<p>
			<label for="trackName">TrackName</label>
			<input id="trackName" name="trackName" value="${fn:escapeXml(param.trackName)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>

	<h1>Tracks Information</h1>
        <table border="1">
            <tr>
                <th>Track Name</th>
        		<th>Album Name</th>
        		<th>Artist Name</th>
            </tr>
            <c:forEach items="${trackList}" var="trackList" >
                <tr>
                    <td><c:out value="${trackList.getTrackName()}" /></td>
                    <td><c:out value="${trackList.getAlbum_name()}" /></td>
                    <td><c:out value="${trackList.getArtistName()}" /></td>
          
   
                </tr>
            </c:forEach>
       </table>
</body>
</html>