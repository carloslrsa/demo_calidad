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
</head>
<body>
    <div class="wrapper">
        <form class="form-signin" action=".\iniciosesion" method="post">
            <img src="FISI_LOGO.png" class="center" width="150" height="150" />
            <h2 class="form-signin-heading" align="center">DISPONIBILIDAD DOCENTE</h2>
            <input type="text" class="form-control" name="dni" placeholder="DNI"/>
            <input type="password" class="form-control" name="pass" placeholder="Password" required=""/>
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Ingresar"/>
        </form>
    </div>
    <script type="text/javascript">
        <c:if test="${errorMessage != null}">
            alert('${errorMessage}');
        </c:if>
    </script>
</body>
</html>