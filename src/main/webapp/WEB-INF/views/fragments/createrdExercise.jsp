<%--
  Created by IntelliJ IDEA.
  User: jaroslawwielowski
  Date: 10/01/2019
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %><html>
<html>
<head>
    <title>creater exercise</title>
</head>
<body>

<table>
    <%--@elvariable id="exercise" type="pl.coderslab.starter.edtityes.Exercise"--%>
    <form:form method="post"
               modelAttribute="exercise" action="/exercise/add">
        <form:input path="nameExercise" placeholder="Nazwa Zadania"/><br>
        <input type="submit" value="dodaj projekt"><br>
    </form:form>
</table>

</body>
</html>
