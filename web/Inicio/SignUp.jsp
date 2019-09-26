<%-- 
    Document   : SignUp
    Created on : Sep 20, 2019, 5:42:47 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./bootstap.css" type="text/css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <title>Sign Up</title>
    </head>
    <body>
        <%@include file="cabeza.html"  %>

        <div style="background-color: white;  width:30%;align-self: center;  " class="container">
            <h2 style="text-align: center;color: #424040;width:70%">Sign Up</h2>
            <form action="/Revistas/CrearUsuario" method="post" >
                UserName: <br>
                <div id="userSpace">
                    <input type="text" name="userName" id="user"placeholder="Ingrese UserName"
                           required  
                           <c:if test="${requestScope['errorPasswordSize'] != null }">   
                               value=${requestScope['errorPassword']}
                           </c:if> />

                </div>
                <c:if test="${requestScope['errorUserExistente'] != null}">   
                    <small style="color: red" class="form-text text-muted">UserName Ya Existente</small>
                </c:if>
                <br>
                Password: <br>
                <input  onkeyup="confirmarLongitud()" type="password" name="pass" id="pass" placeholder="Ingrese Password" required/><br>
                <div style="font-size: 10px;" id="errorPassword">
                </div>
                <br>
                Confirmar Password: <br>
                <input onkeyup="confirmarPassword()" id="confirmacion" type="password" name="confirmacion" placeholder="Confirmar Password" required/><br>
                <div style="font-size: 10px;" id="errorConfirmacion">
                </div>
                <br>
                <input type="submit" class="btn btn-dark" value="Sign Up" ><a style="color: white">....  </a>
            </form >
            <br>
            <div style="text-align: center;width:65%">

            </div>

        </div>
        <div style="width: 80%;"  class="container">
            <div class="progress" >
                <div class="progress-bar" role="progressbar" style="width: 1%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
        </div>
        <br>
        <br>
        <footer> 
            Ya tienes cuenta... <a href="Login.jsp"> LogIn</a>
        </footer>
        <script type="text/javascript" src="/Revistas/Inicio/confirmarPass.js"></script>
    </body>
</html>
