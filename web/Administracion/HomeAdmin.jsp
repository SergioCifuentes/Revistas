<%-- 
    Document   : Home
    Created on : Sep 22, 2019, 2:34:41 AM
    Author     : sergio
--%>
<%@page import="Usuarios.Persona"%>
<%@page import="Servlet.InicioSesion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="./bootstrap.css" type="text/css" >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/Componentes/cabecera.jsp"  %>

        <br>
        <br>
        <br>

        <div class="container-fluid">
            <div class="row">
                <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                    <div class="sidebar-sticky">
                        <ul class="nav flex-column">
                            <li class="nav-item <c:if test="${requestScope['Ganancias'] == true}"> active</c:if>">
                                <a class="nav-link <c:if test="${requestScope['Ganancias'] == true}">active</c:if>" id="ganancias" data-toggle="tab" href="#Ganancia" role="tab" aria-controls="Ganancia" aria-selected="true">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bar-chart-2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>
                                    Ganancias <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item <c:if test="${requestScope['Pendiente'] == true}">active</c:if>">
                                <a class="nav-link <c:if test="${requestScope['Pendiente'] == true}">active</c:if>" id="pendientes" data-toggle="tab" href="#pendiente" role="tab" aria-controls="pendiente" aria-selected="false">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                                    Pendientes <% if ((new Controlador2()).obtnerRevistasPorEstado(1).size() > 0) {%>
                                    <span class="badge badge-danger"><%=(new Controlador2()).obtnerRevistasPorEstado(1).size()%></span></button>  <span class="sr-only">unread messages</span>
                                    <%}%>
                                </a>
                            </li>

                            <li class="nav-item ">
                                <a class="nav-link" href="#">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-users"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                                    Usuarios
                                </a>
                            </li>
                            
                            
                            <li class="nav-item nav-item <c:if test="${requestScope['Suscritos'] == true}">active</c:if>">
                                <a class="nav-link <c:if test="${requestScope['Suscritos'] == true}">active</c:if>" id="topS" data-toggle="tab" href="#TopS" role="tab" aria-controls="TopS" aria-selected="false">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bar-chart-2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>
                                    Top Suscritas
                                </a>
                            </li>
                            
                            
                            <li class="nav-item <c:if test="${requestScope['Comentarios'] == true}"> active</c:if>">
                                <a class="nav-link <c:if test="${requestScope['Comentarios'] == true}"> active</c:if>" id="topC" data-toggle="tab" href="#TopC" role="tab" aria-controls="TopC" aria-selected="false">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-bar-chart-2"><line x1="18" y1="20" x2="18" y2="10"></line><line x1="12" y1="20" x2="12" y2="4"></line><line x1="6" y1="20" x2="6" y2="14"></line></svg>
                                    Top Comentadas
                                </a>
                            </li>
                        </ul>



                    </div>
                </nav>

                <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"><div style="position: absolute; inset: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;" class="chartjs-size-monitor"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
                    <div class="">
                        <div class="tab-content" id="myTabContent">
                            <div class="tab-pane fade  <c:if test="${requestScope['Ganancias'] == true}">show active</c:if>" id="Ganancia" role="tabpanel" aria-labelledby="Ganancia-tab"><%@include file="Componentes/Ganancias.jsp"%></div>
                            <div class="tab-pane fade <c:if test="${requestScope['Pendiente'] == true}">show active</c:if>" id="pendiente" role="tabpanel" aria-labelledby="pendiente-tab"><%@include file="Componentes/RevistasPendientes.jsp"%></div>
                            <div class="tab-pane fade <c:if test="${requestScope['Suscritos'] == true}">show active</c:if>" id="TopS" role="tabpanel" aria-labelledby="TopS-tab"><%@include file="Componentes/TopSuscrito.jsp"%></div>
                            <div class="tab-pane fade <c:if test="${requestScope['Comentarios'] == true}">show active</c:if>" id="TopC" role="tabpanel" aria-labelledby="TopC-tab"><%@include file="Componentes/TopComentarios.jsp"%></div>
                        </div>
                    </div>

                    <canvas class="my-4 w-100 chartjs-render-monitor" id="myChart" width="1041" height="439" style="display: block; width: 1041px; height: 439px;"></canvas>
                </main>

            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

    </body>
</html>
