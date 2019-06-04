<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sistema Disponibilidad Docente</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/jquery-3.4.1.js"></script>

</head>
<body>
    <div align="center">
        <div>
            <h2>Docente: <c:out value="${docente.nombres}"/> <c:out value="${docente.apellidos}"/></h2>
            <h2>Tipo Docente: T. <c:out value="${docente.categoria.tipo}"/></h2>
        </div>
        <form action="./seleccionarcursos" align="center" method="post">
            <table>
                <c:forEach var = "i" begin = "1" end = "${docente.categoria.cursosCan}">
                    <tr>
                        <td>
                            Seleccione escuela:
                        </td>
                        <td>
                            <select name="escuela${i}" id="escuela${i}">
                                <c:forEach var="e" items="${cursosporescuela}">
                                    <option>${e.key}</option>
                                </c:forEach>
                            </select>    
                        </td>
                        <td>
                            Seleccione curso:
                        </td>
                        <td>
                            <select name="cursoseleccionado${i}" id="cursoseleccionado${i}">
                                <c:forEach var="cursoescuela" items="${cursosporescuela[primerescuela]}">
                                    <option value="${cursoescuela.curso}">
                                        ${cursoescuela.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>  
                    </tr>                           
                </c:forEach>
            </table>
            <input type="submit" name="siguiente" value="Siguiente"/>
        </form>
    </div>
        
    <script type="text/javascript">
        $(function() {
            <c:forEach var = "i" begin = "1" end = "${docente.categoria.cursosCan}">
            $("#escuela${i}").change(function() {
                var item=$(this);
                $("#cursoseleccionado${i}").children('option').remove();
                <c:forEach var="cursoescuela" items="${cursosporescuela}">
                var key = '${cursoescuela.key}';                
                if(key === item.val()){
                    <c:forEach var="curso" items="${cursoescuela.value}">
                        var _cod = '${curso.curso}';
                        var _nom = '${curso.nombre}';
                        $("#cursoseleccionado${i}").append( $("<option>")
                            .val(_cod)
                            .html(_nom)
                        );
                    </c:forEach>
                }
                </c:forEach>
            });
            </c:forEach>
        });
    </script>
        
    <script type="text/javascript">
        <c:if test="${errorMessage != null}">
            alert('${errorMessage}');
        </c:if>
    </script>
</body>
</html>