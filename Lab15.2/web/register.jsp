<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorize</title>
    <style>
        body
        {
            overflow: hidden;
            max-width: 250px;
            margin: 0 auto;
            text-align: center;
            font-size: 20px;
        }
        fieldset {
            text-align: center;
            background: whitesmoke;
            width: 200px;
            font-size: 20px;
            margin: 3%;
            padding: 10px;
        }
        input{
            margin: 3%;
        }
    </style>

</head>
<body>
<%@include file="Header.jsp"%><br>
<p style="color: red">${message}</p>
<fieldset>
    <legend>Registration</legend>
    <form method="POST" action="register">
        <input name="name" type="text" placeholder="name"/>
        <input name="login" type="text" placeholder="login"/>
        <input name="password" type="password" placeholder="password"/>
        <input type="submit" value="Sign up"/>
    </form>
</fieldset>
<form method="POST" action="GoToLogin">
    <input type="submit" value="Sign in"/>
</form>
</body>
<%@include file="Footer.jsp"%><br>
</html>
