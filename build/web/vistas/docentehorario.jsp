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
        <div align="center">
            <form action="./seleccionarhorario" method="post">
                <table id="interactable_table">
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
                <br>
                <div align="left" >
                    <div class="div-contenedor-horas">
                        <label>Cantidad de horas: </label><input id="contador" class="text-rounded-short" value="0"/>
                    </div>

                </div>
                <div align="right" style="margin-right: 15%;">
                    <input type="submit" class="btn btn-primary button-rounded" value="Seleccionar cursos a dictar"/>
                </div>
            </form>
        </div>
    </div>
    <div style="height: 50px;">

    </div>
</body>
<footer>
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
            if(isHighlighted){
                var isChecked = ($(this).children()).attr("checked");
                if (typeof isChecked == typeof undefined || isChecked == false) {
                    ($(this).children()).attr("checked","checked");
                    //Contador
                    var c = parseInt($("#contador").val());
                    c = c + 1;
                    $("#contador").val(c);
                }
            }
            else{
                var isChecked = ($(this).children()).attr("checked");
                if (typeof isChecked !== typeof undefined && isChecked !== false) {  
                    ($(this).children()).removeAttr("checked");
                    //Contador
                    var c = parseInt($("#contador").val());
                    c = c - 1;
                    $("#contador").val(c);
                }
            }
        }
        
        return false; // prevent text selection
        })
        .mouseover(function () {
        if (isMouseDown) {
            var isHour = $(this).attr("id");
            if(isHour != "hora"){
                $(this).toggleClass("selected", isHighlighted);
                if(isHighlighted){
                    var isChecked = ($(this).children()).attr("checked");
                    if (typeof isChecked == typeof undefined || isChecked == false) {
                        ($(this).children()).attr("checked","checked");
                        //Contador
                        var c = parseInt($("#contador").val());
                        c = c + 1;
                        $("#contador").val(c);
                    }
                }
                else{
                    var isChecked = ($(this).children()).attr("checked");
                    if (typeof isChecked !== typeof undefined && isChecked !== false) {  
                        ($(this).children()).removeAttr("checked");
                        //Contador
                        var c = parseInt($("#contador").val());
                        c = c - 1;
                        $("#contador").val(c);
                    }
                }
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
</footer>
</html>