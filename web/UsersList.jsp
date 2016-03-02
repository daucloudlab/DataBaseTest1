<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sql:query var="listUsers" dataSource="jdbc/UsersDB">
    select username, email from users ;
</sql:query>

<html>
<head>
    <title>Users List</title>
</head>
<body>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of users</h2></caption>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach var="user" items="${listUsers.rows}">
            <tr>
                <td><c:out value="${user.username}" /></td>
                <td><c:out value="${user.email}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
