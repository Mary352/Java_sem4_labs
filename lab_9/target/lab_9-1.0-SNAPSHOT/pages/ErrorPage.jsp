<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.05.2021
  Time: 0:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name: ${}
<br/>pageContext.errorData.servletName
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
<br/>
Message from exception: ${pageContext.exception.message}
</body>
</html>
