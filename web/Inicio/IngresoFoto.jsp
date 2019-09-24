<%-- 
    Document   : IngresoFoto
    Created on : Sep 22, 2019, 6:58:17 PM
    Author     : sergio
--%>

<%@page import="Usuarios.Suscriptor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./bootstap.css" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Foto</title>
    </head>
    <body>
        <%HttpSession misession = (HttpSession) request.getSession();
            Suscriptor nueva = (Suscriptor) misession.getAttribute("Usuario");
        %>
        <%@include file="cabeza.html"%>
        <br>
        
        <div style="background-color: white;color: #424040;  width:95%;align-self: center;  " class="container">
            <div style="width:60%" class="container">
                <h2 style="color: #424040;width:100%; align-self: center;">Ingrese Una Imagen Que Lo Identifique </h2>
            </div>
            <div style="width:30%" class="container">
                <img src="ControladorImagen?userName=${nueva.getUserName()}" width="250" height="330" class="rounded-circle">
                <br>
                <br>

                <div style="width:95%" class="container">
                    <form method="POST" action="ForwardSuscriptor" enctype="multipart/form-data">
                        <input type="file"  class="btn btn-light"name="cambiar"id="cambiar"value="Cambiar Foto" accept=".jpg,.png" ><a style="color: white">..</a> 
                        <br>
                        <div style="width: 50%" class="container">
                        <input  type="submit"class="btn btn-dark"name="Siguiente" value="Siguiente" >
                        </div>
                    </form>
                </div>
            </div>
            <br>
            <br>
            <div class="progress" >
                <div class="progress-bar" role="progressbar" style="width: 85%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
        </div>
    </body>
</html>
