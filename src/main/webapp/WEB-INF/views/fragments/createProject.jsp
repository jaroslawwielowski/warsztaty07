<%--
  Created by IntelliJ IDEA.
  User: jaroslawwielowski
  Date: 03/01/2019
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %><html>
<head>
    <title>Create Project</title>
</head>
<body>
<%--@elvariable id="project" type="pl.coderslab.starter.edtityes.Project"--%>
<table>
    <form:form method="post"
               modelAttribute="project" action="/project/create">
        <form:input path="nameProject" placeholder="Nazwa Projektu"/><br>
        <input type="submit" value="dodaj projekt"><br>
    </form:form>
</table>
</body>
</html>
