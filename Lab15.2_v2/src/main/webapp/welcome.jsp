<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 04.06.2021
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
</html>
