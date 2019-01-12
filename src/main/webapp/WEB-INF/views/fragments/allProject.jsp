<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<table border="5"a align="center">

    <td><b>User Id</b></td>
    <td><b>Id</b></td>
    <td><b>name project</b></td>



    </tr>
    <c:forEach items="${projects}" var="project">
        <tr>
            <td>${user.id}</td>
            <td>${project.id}</td>
            <td>${project.nameProject}</td>

            <td><a href="delete/${project.id}">Usuń</a><br>
                <a href="edit/${project.id}">Edytuj</a></td>
        </tr>
    </c:forEach>
</table>
