<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>$Title$</title>

</head>
<%@include file="Header.jsp"%><br>
<body>

<form method="GET" action="Task1">
  <input type="submit" value="CURRENT DATE"/>
</form>

<form method="POST" action="GoToLogin">
  <input type="submit" value="Sign in"/>
</form>
<form method="POST" action="GoToRegister">
  <input type="submit" value="Registration"/>
</form>
<form method="GET" action="Servlet2">
  <input type="submit" value="Task7"/>
</form>
</body>
<%@include file="Footer.jsp"%><br>
</html>
