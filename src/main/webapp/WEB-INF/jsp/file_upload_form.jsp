<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:useBean id="list" type="java.util.List" scope="request" />
<html>
<head>
	<title>Spring MVC Multiple File Upload</title>
<style>
	body {font-family: "Trebuchet MS";}
	h1 {font-size: 1.5em;}
</style>


</head>
<body>
<h1>Spring Multiple File Upload example</h1>

<form method="post" action="save"
		modelAttribute="uploadForm" enctype="multipart/form-data">

			<td><input name="file" type="file" /></td>
	<input type="submit" value="Upload" />


</form>
<ol>
    <% for (Object name : list) { %>
    <li> <%= name %> </li>
    <% } %>
</ol>
</body>
</html>
