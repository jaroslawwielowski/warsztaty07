<%--
  Created by IntelliJ IDEA.
  User: jaroslawwielowski
  Date: 19/12/2018
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>register</title>
</head>
<body>

<%--@elvariable id="user" type="pl.coderslab.starter.edtityes.User"--%>
<form:form method="post"
           modelAttribute="user" action="/register">
    <form:input path="login" placeholder="Login"/><br>
    <form:input path="email" placeholder="Email"/><br>
    <form:password path="password" placeholder="Hasło" /><br>

    <input type="submit" value="Rejestracja"><br>
</form:form>



</body>
</html>
