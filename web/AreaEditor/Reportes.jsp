<%-- 
    Document   : Reportes
    Created on : Oct 3, 2019, 2:22:09 PM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reportes</title>
    </head>
    <body>
        <%@include file="/Componentes/cabeceraUsuario.jsp"%>
        <br>
        <br>
        <br>
        <div class="container" style="width: 90%;text-align: center">
            <%@include file="ComponentesMenu/MenuReportes.html"%>
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope['comentarios'] == true}"> active</c:if>"  id="home-tab" data-toggle="tab" href="#comentarios" role="tab" aria-controls="comentarios" aria-selected="false">Comentarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope['Suscripciones'] == true}"> active</c:if>"  id="profile-tab" data-toggle="tab" href="#Suscripciones" role="tab" aria-controls="Suscripciones" aria-selected="false">Suscripciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope['Likes'] == true}"> active</c:if>"  id="contact-tab" data-toggle="tab" href="#Likes" role="tab" aria-controls="Likes" aria-selected="false">Likes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope['Ganancias'] == true}"> active</c:if>"  id="contact-tab" data-toggle="tab" href="#Ganancias" role="tab" aria-controls="Ganancias" aria-selected="false">Ganancias</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade <c:if test="${requestScope['comentarios'] == true}">show active</c:if>" id="comentarios" role="tabpanel" aria-labelledby="comentarios-tab"><%@include file="Reports/Comentarios.jsp"%></div>
                <div class="tab-pane fade <c:if test="${requestScope['Suscripciones'] == true}">show active</c:if>" id="Suscripciones" role="tabpanel" aria-labelledby="Suscripciones-tab"><%@include file="Reports/Suscripciones.jsp"%></div>
                <div class="tab-pane fade <c:if test="${requestScope['Likes'] == true}">show active</c:if>" id="Likes" role="tabpanel" aria-labelledby="Likes-tab"><%@include file="Reports/Likes.jsp"%></div>
                <div class="tab-pane fade <c:if test="${requestScope['Ganancias'] == true}">show active</c:if>" id="Ganancias" role="tabpanel" aria-labelledby="Ganancias-tab"><%@include file="Reports/Ganancias.jsp"%></div>
            </div>
        </div>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

    </body>
</html>
