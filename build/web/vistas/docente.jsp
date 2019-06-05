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
                <label>NOMBRES: </label><input class="text-rounded" type="text" value="${docente.nombres} ${docente.apellidos}" disabled="true"/>
                <label>TIPO:    </label><input class="text-rounded" type="text" value="T. ${docente.categoria.tipo}" disabled="true"/>
            </div>
        </div>
        <hr>
        <div align="center">
            <div class="div-contenedor" style="padding: 5px">
                <h3>DISPONIBILIDAD DE HORARIO</h3>
            </div>
        </div>
        <div align="center">
            <form action="./docente" align="center" method="post">
                <table>
                    <thead>
                        <tr class="table100-head">
                            <th width="10%">HORAS</th>
                            <th width="15%" class="column2">Lunes</th>
                            <th width="15%" class="column2">Martes</th>
                            <th width="15%" class="column2">Mi&eacute;rcoles</th>
                            <th width="15%" class="column2">Jueves</th>
                            <th width="15%" class="column2">Viernes</th>
                            <th width="15%" class="column2">S&aacute;bado</th>
                        </tr>
                    </thead>
                    <c:forEach var="r" items="${horario}">
                        <tr>
                            <td><c:out value="${r.hora}"/></td>
                            <td <c:if test="${r.lunes == '1'}">class="selected"</c:if>></td>
                            <td <c:if test="${r.martes == '1'}">class="selected"</c:if>></td>
                            <td <c:if test="${r.miercoles == '1'}">class="selected"</c:if>></td>
                            <td <c:if test="${r.jueves == '1'}">class="selected"</c:if>></td>
                            <td <c:if test="${r.viernes == '1'}">class="selected"</c:if>></td>
                            <td <c:if test="${r.sabado == '1'}">class="selected"</c:if>></td>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <hr>
                <div align="center">
                    <div class="div-contenedor" style="padding: 5px">
                        <h3>CURSOS A DICTAR</h3>
                    </div>
                </div>
                <table>
                    <thead>
                        <tr class="table100-head">
                            <th colspan="1">ESCUELA SELECCIONADA</th>
                            <th colspan="100%">CURSOS SELECCIONADOS</th>
                        </tr>
                    </thead>
                    <c:forEach var="escuela" items="${cursos}">
                        <tr>
                            <td align="left">${escuela.key}</td>
                            <c:forEach var="cporescuela" items="${escuela.value}">
                                <td>
                                    ${cporescuela.nombre}
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
                <br>
                <hr>
                <br>
                <div align="center">
                    <div class="div-contenedor" style="padding: 5px">
                        <label>Ingrese motivo de reingreso de notas:</label>
                        <input type="text" class="text-rounded" style="width: 80%;" name="motivo">
                    </div>
                </div>
                <div align="right" style="margin-right: 15%;">
                    <input type="submit" class="btn btn-primary button-rounded" name="solicitar" value="Solicitar reingreso de disponibilidad"/>
                </div>
                <br>
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