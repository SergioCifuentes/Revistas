<%-- 
    Document   : Login
    Created on : Sep 20, 2019, 3:22:54 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="./bootstap.css" type="text/css">
        <title>LogIn</title>
    </head>
    <body>
        <h1 style="text-align:center;background-color: aquamarine">Iniciar Sesion </h1>
        <div style="text-align:center;background-color:blueviolet " class="container">
            <form action="/Revistas/Iniciar" method="post" >
            UserName: <br>
            <input type="text" name="userName" placeholder="Ingrese UserName" required/><br>
            Password: <br>
            <input type="password" name="password" placeholder="Ingrese Password" required/><br>
            <input type="submit" class="btn btn-primary" value="Iniciar Sesion" >
        </form>
            <a href="SignUp.jsp"> SignUp</a>
        </div>
    </body>
</html>
