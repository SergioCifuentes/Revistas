<%-- 
    Document   : PerfilEditador
    Created on : Sep 24, 2019, 6:50:27 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=((Usuario) request.getSession().getAttribute("Usuario")).getUserName()%></title>
    </head>
    <body>

        <%@include file="/Componentes/cabeceraUsuario.jsp"%>

        <br>
        <br>
        <br>
        <div class="container" style="width: 90%;text-align: center">
            <%@include file="ComponentesMenu/MenuPerfil.html"%>
        </div>
        <%@include file="/Componentes/MoldePerfil.jsp"%>
        <br>

        <form method="POST" action="/Revistas/RedireccionesEditador">
            <div class="container" style="width: 18%">
                <input type="submit" name="EditarInfo" id="EditarInfo" class="btn btn-dark" value="Editar Informacion">
            </div>
            <br>
            <br>
        </form>
    </body>
</html>
