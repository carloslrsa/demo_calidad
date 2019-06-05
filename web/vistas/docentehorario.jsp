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
    <div align="center">
        <div>
            <h2>Docente: <c:out value="${docente.nombres}"/> <c:out value="${docente.apellidos}"/></h2>
            <h2>Tipo Docente: T. <c:out value="${docente.categoria.tipo}"/></h2>
        </div>
        <form action="./seleccionarhorario" align="center" method="post">
            <table id="interactable_table">
                <thead>
                    <tr class="table100-head">
                        <th>HORAS</th>
                        <th class="column2">Lunes</th>
                        <th class="column2">Martes</th>
                        <th class="column2">Miércoles</th>
                        <th class="column2">Jueves</th>
                        <th class="column2">Viernes</th>
                        <th class="column2">Sábado</th>
                    </tr>
                </thead>
                <c:forEach var="r" items="${horario}">
                    <tr>
                        <td id="hora"><label><c:out value="${r.hora}"/></label></td>
                        <td><input type="checkbox" value="${r.hora}" name="lunes" style="opacity:0; position:absolute; left:9999px;"/></td>
                        <td><input type="checkbox" value="${r.hora}" name="martes" style="opacity:0; position:absolute; left:9999px;"/></td>
                        <td><input type="checkbox" value="${r.hora}" name="miercoles" style="opacity:0; position:absolute; left:9999px;"/></td>
                        <td><input type="checkbox" value="${r.hora}" name="jueves" style="opacity:0; position:absolute; left:9999px;"/></td>
                        <td><input type="checkbox" value="${r.hora}" name="viernes" style="opacity:0; position:absolute; left:9999px;"/></td>
                        <td><input type="checkbox" value="${r.hora}" name="sabado" style="opacity:0; position:absolute; left:9999px;"/></td>
                    </tr>
                </c:forEach>
            </table>
            
            <input type="submit" class="button-next  btn btn-primary" value="Seleccionar cursos a dictar"/>
            
            <script type="text/javascript">
                <c:if test="${errorMessage != null}">
                    alert('${errorMessage}');
                </c:if>
            </script>
            <script>
            
            $(function () {
            var isMouseDown = false,
                isHighlighted;
            $("#interactable_table td")
                .mousedown(function () {

                var isHour = $(this).attr("id");
                if(isHour != "hora"){
                    isMouseDown = true;
                    $(this).toggleClass("selected");
                    isHighlighted = $(this).hasClass("selected");
                    if(isHighlighted)
                        ($(this).children()).attr("checked","checked");
                    else
                        ($(this).children()).removeAttr("checked");
                }
                
                return false; // prevent text selection
                })
                .mouseover(function () {
                if (isMouseDown) {
                    var isHour = $(this).attr("id");
                    if(isHour != "hora"){
                        $(this).toggleClass("selected", isHighlighted);
                        if(isHighlighted)
                            ($(this).children()).attr("checked","checked");
                        else
                            ($(this).children()).removeAttr("checked");
                    }
                }
                })
                .bind("selectstart", function () {
                return false;
                })

            $(document)
                .mouseup(function () {
                isMouseDown = false;
                });
            });
            </script>
        </form>
    </div>	
</body>
</html>