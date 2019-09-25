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
        <title>JSP Page</title>
    </head>
    <body>

        <%@include file="/Componentes/cabeceraUsuario.jsp"%>

        <br>
        <br>
        <div class="container" style="width: 90%;text-align: center">
            <form method="POST" action="/Revistas/RedireccionesEditador">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <ul class="navbar-nav">
                            <li class="nav-item ">
                                <input name="Home" class="nav-link" type="submit"style="font-size: 25px;width: 190px" value="Home">
                            </li>
                            <li class="nav-item active">
                                <input name="Perfil" value="Perfil" class="nav-link"type="submit" id="Perfil" style="font-size: 25px;width: 190px"> <span class="sr-only">(current)</span>
                            </li>
                            <li class="nav-item">
                                <input name="Publicar"value="Publicar" class="nav-link"type="submit" id="Publicar" style="font-size: 25px;width: 190px">
                            </li>
                            <li class="nav-item">
                                <input name="Revistas" value="Revistas" class="nav-link"type="submit" id="Revistas" style="font-size: 25px;width: 190px">
                            </li>
                            <li class="nav-item">
                                <input name="Ganancias" value="Ganancias" class="nav-link"type="submit" id="Ganancias" style="font-size: 25px;width: 190px">
                            </li>
                        </ul>
                    </div>
                </nav>
                <form>
                    </div>
                    <%@include file="/AreaEditor/MoldePerfil.jsp"%>
                    <br>
                    
                    <form>
                        <div class="container" style="width: 18%">
                            <input type="submit" class="btn btn-dark" value="Editar Informacion">
                        </div>
                    </form>
                    </body>
                    </html>
