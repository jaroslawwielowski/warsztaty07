<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<table border="5" align="center">
    <tr>
        <td><b>Jesteś zalogowany jako ${user.login}</b></td>
        <td><b><a href="/">Start</a> </b></td>
        <td><b><a href="/project/add">Add Project</a></b></td>
        <td><b><a href="/">All Project(do zrobienia)</a></b></td>
        <td><b><a href="/all">All Users</a> </b></td>
    </tr>
    <tr>
        <td><b><a href="/logout/${user.id}">wyloguj</a> </b></td>
        <td><b></b></td>
        <td><b></b></td>
        <td><b></b></td>
        <td><b> </b></td>
    </tr>
</table>
