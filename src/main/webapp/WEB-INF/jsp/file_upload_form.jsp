<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:useBean id="list" type="java.util.List" scope="request"/>

<html>
<head>
    <title>Spring MVC Multiple File Upload</title>


</head>
<body>
<h1>File Upload</h1>

<form method="post" action="save"
      modelAttribute="uploadForm" enctype="multipart/form-data">

    <td><input name="file" type="file"/></td>
    <input type="submit" value="Upload"/>

</form>
<table>
    <% for (Object name : list) { %>
    <tr>
        <td><%= name %>
        </td>

        <form method="post" action="play/<%= name %>" target="_blank">

            <td><input type="submit" value="Play"/></td>
        </form>

        <form method="post" action="delete/<%= name %>">

            <td><input type="submit" value="Delete"/></td>
        </form>

    </tr>

    <% } %>

</table>

</body>
</html>
