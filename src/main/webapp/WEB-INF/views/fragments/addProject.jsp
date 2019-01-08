<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add Project</title>
</head>
<body>
<%--@elvariable id="project" type="pl.coderslab.starter.edtityes.Project"--%>
<div>
    Dodaj projekt : <br>
    <form:form method="post"
                     modelAttribute="project" action="/project/add">
        <form:input path="nameProject" placeholder="Nazwa Projektu"/><br>
        <%--<form: path="email" placeholder="Email"/><br>--%>
        <input type="submit" value="Stówrz Projekt"><br>
    </form:form>

    ${message}
</div>
</body>
</html>
