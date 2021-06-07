<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a><br/>

<form action="SecondTaskServlet" method="get">
    <input type="submit" value="Execute" >
</form>

<form action="Task7_FirstServlet" method="get">
    <input type="submit" value="backAndForth"/>
</form>

<form action="hello" method="get">
    <input type="submit" value="Task 7"/>
</form>
</body>
</html>