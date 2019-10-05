<%-- 
    Document   : PerfilAutor
    Created on : Oct 4, 2019, 6:49:15 PM
    Author     : sergio
--%>

<%@page import="Usuarios.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%Usuario user = (Usuario) request.getAttribute("Autor");%>
        <title><%=user.getUserName()%></title>
    </head>
    <body>
        <%@include file="/Componentes/cabeceraUsuario.jsp"%>       
        <br>
        <br>
        <br>
        <br>
        <div class="container" style="width: 75%;text-align: center">
            <%@include file="ComponentesSuscriptor/MenuMisRevistas.jsp"%>
        </div>
        <br> 

        <br>
        <a style="font-size: 50px;color: tomato;font: fantasy"><%=user.getUserName()%></a>
        <br>
        <div style="width:30%" class="container">
            <img src="ControladorImagen?userName=<%=user.getUserName()%>" width="250" height="300" class="rounded-circle">
            <br>
            <br>

        </div>
        <div class="container" style=" border: 5px ridge  #424040;">
            <b style="color: tomato">Descripcion:</b>
            <br>
            <%if (!user.getPerfil().getDescripcion().equals("")) {%>
            <a style="color: black"><%=user.getPerfil().getDescripcion()%></a>
            <%} else {%>
            <a style="color: gray">'Editar Informacion' para agregar campo</a>
            <%}%>
        </div>
        <br>
        <div class="container" style=" border: 5px ridge  #424040;">
            <b style="color: tomato">Hobbies:</b>
            <br>
            <%if (!user.getPerfil().getHobbies().equals("")) {%>
            <a style="color: black"><%=user.getPerfil().getHobbies()%></a>
            <%} else {%>
            <a style="color: gray">'Editar Informacion' para agregar campo</a>
            <%}%>
        </div>
        <br>
        <div class="container" style=" border: 5px ridge  #424040;">
            <b style="color: tomato">Temas De Interes:</b>
            <br>
            <%if (!user.getPerfil().getTemasDeInteres().equals("")) {%>
            <a style="color: black"><%=user.getPerfil().getTemasDeInteres()%></a>
            <%} else {%>
            <a style="color: gray">'Editar Informacion' para agregar campo</a>
            <%}%>
        </div>
        <br>
        <div class="container" style=" border: 5px ridge  #424040;">
            <b style="color: tomato">Gustos:</b>
            <br>
            <%System.out.println("sdawdws" + user.getPerfil().getGustos() + "xxx");
        if (!user.getPerfil().getGustos().equals("")) {%>
            <a style="color: black"><%=user.getPerfil().getGustos()%></a>
            <%} else {%>

            <a style="color: gray">'Editar Informacion' para agregar campo</a>
            <%}%>
        </div>
        <br>
    </body>
</html>
