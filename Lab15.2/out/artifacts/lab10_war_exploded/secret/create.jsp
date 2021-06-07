<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>New newspapers</h3>
<label>Newspaper number</label><br>
<p style="color: red">${message}</p>
<form method="POST" action="create">
    <input name="newspapernumber" type="text"/><br>
    <input type="submit" value="Ok" />
</form>
</body>
</html>
