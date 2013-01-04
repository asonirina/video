<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:useBean id="name" type="java.lang.String" scope="request"/>

<html>
<head>
    <title><%=name%></title>
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
<h1><%=name%></h1>

<video width="320" height="240" controls>

    <source src="http://localhost:8080/video/VideoServlet?name=<%=name%>" type="video/mp4">
    <source src="http://localhost:8080/video/VideoServlet?name=<%=name%>" type="video/ogg">
    Your browser does not support the video tag.
</video>

<br/>
<img src="http://upload.wikimedia.org/wikipedia/ru/a/a9/Example.jpg" alt="image" width="100" height="150">
<br/>

</body>
</html>