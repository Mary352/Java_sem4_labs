<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="../Header.jsp"%><br>
<body>
Welcome ${name}! <br>
Last login is  ${last_login}!<br>
Login number ${login_number}!<br>
User role ${user_role}!<br>
<h2>Newspapers List</h2>
<p><a href='<c:url value="/create" />'>Create new</a></p>
<table>
    <tr><th>Number</th></tr>
    <c:forEach var="newspaper" items="${newspapers}">
        <tr><td>${newspaper.number}</td>
            <td>
                <a href='<c:url value="/edit?id=${newspaper.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="/delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${newspaper.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<%@include file="../Footer.jsp"%><br>
</html>
