<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.05.2021
  Time: 23:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="Header.jsp"/>
<h1 style="text-align: center">Registration</h1>
<form action="/Lab9_war_exploded/registration" method="get" style="text-align: center">
    <h3>Name:</h3>
    <input type="text" name="name"/><br/>
    <h3>Login:</h3>
    <input type="text" name="login"/><br/>
    <h3>Role:</h3>
    <p>
        <input type="radio" name="role" value="user">User<br/>
        <input type="radio" name="role" value="admin">Admin<br/>
    </p>
    <h3>Password:</h3>
    <input type="password" name="password"/><br/>
    <h3>Repeat password:</h3>
    <input type="password" name="repeat_password"/><br/><br/>
    <input type="submit" value="submit"/><br/>
</form>
<form action="login_page" method="get" style="text-align: center">
    <input type="submit" value="Login">
</form>
<jsp:include page="Footer.jsp"/>
</body>
</html>
