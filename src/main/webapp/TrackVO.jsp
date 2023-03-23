<%@ page import="melodic.model.TrackVO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Track List</title>
</head>
<body>
<h1>Track List</h1>
<table>
    <thead>
    <tr>
        <th>Album ID</th>
        <th>Album Name</th>
        <th>Total Time</th>
    </tr>
    </thead>
    <tbody>
    <% for (TrackVO track : (List<TrackVO>) request.getAttribute("trackVOList")) { %>
    <tr>
        <td><%= track.getAlbumId() %></td>
        <td><%= track.getAlbumName() %></td>
        <td><%= track.getTotalTime() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>