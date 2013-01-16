<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:useBean id="list" type="java.util.List" scope="request"/>
<jsp:useBean id="pg" type="java.lang.Integer" scope="request"/>
<jsp:useBean id="num" type="java.lang.Integer" scope="request"/>

<html>
<head>
    <title>Spring MVC Multiple File Upload</title>


</head>
<body>
<h1>File Upload</h1>

<form method="post" action="save?page=<%=num%>"
      modelAttribute="uploadForm" enctype="multipart/form-data">

    <td><input name="file" type="file"/></td>
    <input type="submit" value="Upload"/>

</form>

<table>
    <c:forEach items="${list}" var="name">
        <tr>
            <td>${name}</td>
            <form method="post" action="play?name=${name}" target="_blank">

                <td><input type="submit" value="Play"/></td>
            </form>

            <form method="post" action="delete?name=${name}">

                <td><input type="submit" value="Delete"/></td>
            </form>
        </tr>

    </c:forEach>
</table>

<table>
    <tr>
        <% for (int i = 0; i < num; ++i) { %>
        <td>
            <a href="/video/save?page=<%= i+1%>"/>
            <%=i + 1%>
        </td>

        <% }%>
    </tr>
</table>

</body>
</html>
