<%-- 
    Document   : Home
    Created on : Sep 22, 2019, 10:33:30 AM
    Author     : sergio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>

        <%@include file="/Componentes/cabeceraUsuario.jsp"%>       
        <br>
        <br>
        <br>
        <br>
        <div class="container" style="width: 75%;text-align: center">
            <%@include file="ComponentesSuscriptor/MenuMisRevistas.html"%>
        </div>
        <br>
        
    </body>
</html>
