<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sistema Disponibilidad Docente</title>
    <link rel='stylesheet' href='http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <script src="js/jquery-3.4.1.js"></script>
</head>
<body>
    <div>
        <div align="center">
            <div class="div-contenedor">
                <label>ADMINISTRADOR: </label><input class="text-rounded" type="text" value="${administrador.nombres} ${administrador.apellidos}" disabled="true"/>
            </div>
        </div>
        <hr>
        <div align="center">
            <div class="div-contenedor" style="padding: 5px">
                <h3>SOLICITUDES DE REINGRESO</h3>
            </div>
        </div>
        <div align="center">
            <form action="./administrador"method="post">
                <table>
                    <thead>
                        <tr class="table100-head">
                            <th>NOMBRES</th>
                            <th>APELLIDOS</th>
                            <th>MOTIVO</th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach var="solicitud" items="${solicitudes}">
                        <tr>
                            <td>${solicitud.docente.nombres}</td>
                            <td>${solicitud.docente.apellidos}</td>
                            <td>${solicitud.motivo}</td>
                            <td>
                                <input type="submit" class="btn btn-primary button-rounded-short"  name="atender" value="Atender">
                                <input type="hidden" name="solicitudAtendida" value="${solicitud.idSolicitud}">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </div>
    </div>
    <div style="height: 50px;">

        </div>
    <script type="text/javascript">
        <c:if test="${message != null}">
            alert('${message}');
        </c:if>
    </script>
</body>
</html>