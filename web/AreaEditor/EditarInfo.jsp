<%-- 
    Document   : EditarInfo
    Created on : Sep 26, 2019, 3:01:34 AM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EditarInfo</title>
    </head>
    <body>
        <%@include file="/Componentes/cabeceraUsuario.jsp"%>

        <br>
        <br>
        <br>
        <div class="container" style="width: 90%;text-align: center">
            <%@include file="ComponentesMenu/MenuPerfil.html"%>
            <br>
            <% Usuario user = (Usuario) request.getSession().getAttribute("Usuario");%>
            <form method="POST" action="/Revistas/ActualizarPerfil">
                <div class="container" style="text-align: left">
                    <b style="color: tomato">Descripcion:</b>
                    <textarea class="form-control" id="Descripcion"name="Descripcion" rows="1" onKeyDown="valida_longitud_Descripcion()" onKeyUp="valida_longitud_Descripcion(), confirmarCambio()"><%=user.getPerfil().getDescripcion()%></textarea>
                    <br>
                    <b style="color: tomato">Hobbies</b>
                    <textarea class="form-control" id="Hobbies"name="Hobbies" rows="1" onKeyDown="valida_longitud_Hobbies()" onKeyUp="valida_longitud_Hobbies(), confirmarCambio()"><%=user.getPerfil().getHobbies()%></textarea>
                    <br>
                    <b style="color: tomato">Temas De Interes</b>
                    <textarea class="form-control" id="Temas"name="Temas" rows="1" onKeyDown="valida_longitud_Tema()" onKeyUp="valida_longitud_Tema(), confirmarCambio()"><%=user.getPerfil().getTemasDeInteres()%></textarea>
                    <br>
                    <b style="color: tomato">Gustos</b>
                    <textarea class="form-control" id="Gustos"name="Gustos" rows="1" onKeyDown="valida_longitud_Gustos()" onKeyUp="valida_longitud_Gustos(), confirmarCambio()"><%=user.getPerfil().getGustos()%></textarea>
                    <br>
                </div>

                    <input type="submit" class="btn btn-dark"  value="Cancelar" name="Cancelar" id="Cancelar" ><a style="color: white">....  </a> <input id="botton"class="btn btn-success" type="submit"  disabled="Yes" id="Guardar" name="Guardar"value="Guardar">
            </form>

            <script type="text/javascript" src="/Revistas/Inicio/verificarLongitud.js"></script>
            <script type="text/javascript" src="/Revistas/AreaEditor/verificarCambio.js"></script>
    </body>
</html>
