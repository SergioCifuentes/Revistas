<%-- 
    Document   : IngresoInfo
    Created on : Sep 22, 2019, 2:42:25 PM
    Author     : sergio
--%>

<%@page import="Usuarios.Suscriptor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="./bootstap.css" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos Personale</title>
    </head>
    <body>
        <% Suscriptor persona = (Suscriptor) request.getAttribute("usuarioCreado");%>
        <%@include file="cabeza.html"%>
        <br>
        <div style="background-color: white;color: #424040;  width:95%;align-self: center;  " class="container">
            <h2 style="color: #424040;width:100%; align-self: center;">Ayudanos A Completar Tu Perfil:</h2>
            <form action="/Revistas/CrearPerfil" method="post" >
                <% request.setAttribute("Usuario",persona);%>
                <label for="exampleFormControlTextarea1">Descripcion:</label>
                <textarea class="form-control"onKeyDown="valida_longitud_Descripcion()" onKeyUp="valida_longitud_Descripcion()" id="Descripcion"name="Descripcion" rows="1"></textarea>
                <br>
                <label for="exampleFormControlTextarea1">Temas De Interes:</label>
                <textarea class="form-control" onKeyDown="valida_longitud_Tema()" onKeyUp="valida_longitud_Tema()"id="Temas"name="Temas" rows="1"></textarea>
                <br>
                <label for="exampleFormControlTextarea1">Hobbies:</label>
                <textarea class="form-control"onKeyDown="valida_longitud_Hobbies()" onKeyUp="valida_longitud_Hobbies()" id="Hobbies" name="Hobbies" rows="1"></textarea>
                <br>
                <label for="exampleFormControlTextarea1">Gustos:</label>
                <textarea class="form-control"onKeyDown="valida_longitud_Gustos()" onKeyUp="valida_longitud_Gustos()" id="Gustos" name="Gustos"rows="1"></textarea>
                <br>
                <input type="submit" class="btn btn-dark" value="Siguiente" >
            </form >
            <br>
            <div style="text-align: center;width:65%">

            </div>

        </div>
        <div style="width: 80%;"  class="container">
            <div class="progress" >
                <div class="progress-bar" role="progressbar" style="width: 45%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
        </div>
        <script type="text/javascript" src="./Inicio/verificarLongitud.js"></script>
    </body>
</html>
