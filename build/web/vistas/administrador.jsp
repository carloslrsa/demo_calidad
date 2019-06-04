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
            <h2>Administrador: <c:out value="${administrador.nombres}"/> <c:out value="${administrador.apellidos}"/></h2>
        </div>
        <hr>
        <h2>SOLICITUDES DE REINGRESO</h2>
        <form action="./administrador" align="center" method="post">
            <table border="1px">
            <c:forEach var="solicitud" items="${solicitudes}">
                <tr>
                    <td>${solicitud.docente.nombres}</td>
                    <td>${solicitud.docente.apellidos}</td>
                    <td>${solicitud.motivo}</td>
                    <td>
                        <input type="submit" name="atender" value="Atender">
                        <input type="hidden" name="solicitudAtendida" value="${solicitud.idSolicitud}">
                    </td>
                </tr>
            </c:forEach>
            </table>
        </form>
    </div>
    <script type="text/javascript">
        <c:if test="${message != null}">
            alert('${message}');
        </c:if>
    </script>
</body>
</html>