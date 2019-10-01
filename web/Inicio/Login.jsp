<%-- 
    Document   : Login
    Created on : Sep 20, 2019, 3:22:54 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="./bootstap.css" type="text/css">
        <title>LogIn</title>
    </head>
    <body>
        <%@include file="cabeza.html"  %>
        <br>
        <div style="background-color:white " >
            <div style="background-color: white;  width:30%;  " class="container">
                <h2 style="text-align: center;color: #424040;width:60%">Iniciar Sesion</h2>
                <form action="/Revistas/Iniciar" method="post" >
                    UserName: <br>
                    <input type="text" name="userName" id="user"placeholder="Ingrese UserName"
                           required  
                           <c:if test="${requestScope['errorPassword'] != null}">   
                               value=${requestScope['errorPassword']}
                           </c:if> /><br><br>
                    <c:if test="${requestScope['errorUserName'] != null}">   
                        <small style="color: red" class="form-text text-muted">Usuario No Existente</small>
                    </c:if>
                        
                    Password: <br>
                    <input type="password" name="pass" placeholder="Ingrese Password" required/><br>
                    <c:if test="${requestScope['errorPassword'] != null}">   

                        <small class="form-text text-muted" style="color:red">Password Incorecto </small>
                    </c:if>
                    <br>
                    <input type="submit" class="btn btn-dark" value="Iniciar Sesion" ><a style="color: white">....  </a> <a href="/Revistas/Inicio/SignUp.jsp">    SignUp</a>
                </form >
                <br>
                <div style="text-align: center;width:65%">
                    
                </div>

            </div>
        </div>

    </body>
</html>
