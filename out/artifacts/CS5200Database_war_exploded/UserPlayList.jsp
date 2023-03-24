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
        <th>track url</th>
        <th>album name</th>
        <th>artist name</th>
        <th>artist url</th>
        <th>duration ms</th>
    </tr>
    <c:forEach items="${tracks}" var="track" >
        <tr>
            <td><c:out value="${track.getTrack_uri()}" /></td>
            <td><c:out value="${track.getAlbum().getAlbumName()}" /></td>
            <td><c:out value="${track.getArtistName()}" /></td>
            <td><c:out value="${track.getArtist_uri()}" /></td>
            <td><c:out value="${track.getDuration_ms()}" /></td>
        </tr>
    </c:forEach>
</table>

<h1>Add track</h1>
<form action="userPlayList" method="post">
    <p>
        <label for="trackURL">track URL</label>
        <input id="trackURL" name="trackURL" value="">
    </p>
    <p>
        <label for="albumName">Album Name</label>
        <input id="albumName" name="albumName" value="">
    </p>
    <p>
        <label for="artistName">Artist Name</label>
        <input id="artistName" name="artistName" value="">
    </p>
    <p>
        <label for="artistURL">Artist URL</label>
        <input id="artistURL" name="artistURL" value="">
    </p>
    <p>
        <label for="durationMS">Duration in millseconds</label>
        <input id="durationMS" name="durationMS" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="success"><b>${messages.success}</b></span>
</p>
</body>
</html>
