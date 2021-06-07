<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>Edit newspaper</h3>
<form method="post">
    <input type="hidden" value="${newspaper.id}" name="newspaperId" />
    <input type="hidden" value="${newspaper.userid}" name="userid" />
    <label>Number</label><br>
    <input name="newspapernumber" value="${newspaper.number}" /><br><br>
    <input type="submit" value="Send" />
</form>
</body>
</html>
