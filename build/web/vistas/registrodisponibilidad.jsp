<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sistema Disponibilidad Docente</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div align="center">
        <div>
            <h2>Docente: <c:out value="${docente.nombres}"/> <c:out value="${docente.apellidos}"/></h2>
            <h2>Tipo Docente: T. <c:out value="${docente.categoria.tipo}"/></h2>
        </div>
        <hr>
        <h2>DISPONIBILIDAD DE HORARIO</h2>
        <form action="./registrardisponibilidad" align="center" method="post">
            <table border="1px">
                <tr>
                    <th>HORAS</th>
                    <th width="15%">Lunes</th>
                    <th width="15%">Martes</th>
                    <th width="15%">Miércoles</th>
                    <th width="15%">Jueves</th>
                    <th width="15%">Viernes</th>
                    <th width="15%">Sábado</th>
                </tr>
                <c:forEach var="r" items="${horario}">
                    <tr>
                        <td><c:out value="${r.hora}"/></td>
                        <td><input type="checkbox" disabled="true" <c:if test="${r.lunes == '1'}">checked="checked"</c:if> name="lunes"/></td>
                        <td><input type="checkbox" disabled="true" <c:if test="${r.martes == '1'}">checked="checked"</c:if> name="martes"/></td>
                        <td><input type="checkbox" disabled="true" <c:if test="${r.miercoles == '1'}">checked="checked"</c:if> name="miercoles"/></td>
                        <td><input type="checkbox" disabled="true" <c:if test="${r.jueves == '1'}">checked="checked"</c:if> name="jueves"/></td>
                        <td><input type="checkbox" disabled="true" <c:if test="${r.viernes == '1'}">checked="checked"</c:if> name="viernes"/></td>
                        <td><input type="checkbox" disabled="true" <c:if test="${r.sabado == '1'}">checked="checked"</c:if> name="sabado"/></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <hr>
            <h2>CURSOS A DICTAR</h2>
            <table border="1px">

            <c:forEach var="escuela" items="${cursos}">
                <tr>
                    <th align="left">${escuela.key}</th>
                    <c:forEach var="cporescuela" items="${escuela.value}">
                        <td>
                            ${cporescuela.nombre}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </table>
            <br>
            <input type="submit" name="registrar" value="Registrar"/>
        </form>
    </div>	
</body>
</html>