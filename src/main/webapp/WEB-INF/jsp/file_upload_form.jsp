<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:useBean id="set" type="java.util.Set" scope="request"/>

<html>
<head>
    <title>Spring MVC Multiple File Upload</title>
    <style>
        body {
            font-family: "Trebuchet MS";
        }

        h1 {
            font-size: 1.5em;
        }
    </style>


</head>
<body>
<h1>File Upload</h1>

<form method="post" action="save"
      modelAttribute="uploadForm" enctype="multipart/form-data">

    <td><input name="file" type="file"/></td>
    <input type="submit" value="Upload"/>

</form>
<table>
    <% for (Object name : set) { %>
    <tr>
        <td><%= name %>
        </td>
        <form method="post" action="play/<%= name %>">

            <td><input type="submit" value="Play"/></td>
        </form>

        <form method="post" action="play/<%= name %>">

            <td><input type="submit" value="Delete"/></td>
        </form>

    </tr>

    <% } %>

</table>

</body>
</html>
