<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:useBean id="name" type="java.lang.String" scope="request"/>

<html>
<head>
    <title><%=name%></title>

</head>
<body>
<h1><%=name%></h1>

<video width="320" height="240" controls>

    <source src="http://localhost:8080/video/VideoServlet?name=<%=name%>" type="video/mp4">
    <source src="http://localhost:8080/video/VideoServlet?name=<%=name%>" type="video/ogg">
    Your browser does not support the video tag.
</video>

</body>
</html>