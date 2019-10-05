<%-- 
    Document   : Home
    Created on : Sep 22, 2019, 10:33:30 AM
    Author     : sergio
--%>
<%@page import="Ingresos.VerificacionDePago"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Revista.Revista"%>
<%@page import="ControladorDB.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
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
            <%@include file="ComponentesSuscriptor/MenuMisRevistas.jsp"%>
        </div>
        <br>        
        <div class="container" style="width: 75%">
            <% Controlador co = new Controlador();
                ArrayList<Revista> rev = co.obtnerRevistasDeSuscriptor(persona.getUserName());
                VerificacionDePago ver = new VerificacionDePago();
                ver.obtenerAtrazos(persona.getUserName());
                if (rev.size() == 0) {
            %> <h2 style="text-align: center">No tienes niguna Suscripcion

                <br>(Busca Revistas en "Buscar Revistas")
            </h2><%
            } else {
                for (int i = 0; i < rev.size(); i++) {
            %>

            <div class="card">
                <div class="card-header" style="background-color: #ef643d;font: bold">
                    <%=rev.get(i).getNombre()%>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Autor:  <a href="/Revistas/BuscarAutor?autor=<%=rev.get(i).getAutor().getUserName()%> "><%=rev.get(i).getAutor().getUserName()%> </a></h5>
                    <p class="card-text"><%=rev.get(i).getDescripcion()%></p>
                    <p>
                    <form method="post">
                        <input type="submit" class="btn btn-secondary" id="likes" data-toggle="collapse" href="#collapseComentarios<%=i%>" role="button" aria-expanded="false" aria-controls="collapseExample"
                               value="<%=rev.get(i).getLikes()%> Likes"
                               >
                        <a class="btn btn-secondary" data-toggle="collapse" href="#collapseComentarios<%=i%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Comentarios
                        </a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-dark" data-toggle="collapse" href="#collapseExample<%=i%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Ediciones
                        </a>   
                    </form>



                    </p>
                </div>
                <div class="collapse" id="collapseExample<%=i%>">
                    <div class="card card-body">
                        <%for (int j = 0; j < rev.get(i).getEdiciones().size(); j++) {%>
                        <form action="/Revistas/pdf">
                            <p style="font-size: 15px">#<%=rev.get(i).getEdiciones().get(j).getNumeroEd()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <a style="width: 10%"><%=rev.get(i).getEdiciones().get(j).getNombre()%> </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                Publicado: <%=rev.get(i).getEdiciones().get(j).getFecha()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input  onmouseenter="verificar(<%=i%>,<%=j%>)" id="boton<%=i + "-" + j%>" type="submit" class="btn btn-success"style="display: inline-block; width:7%" target="_blank" title="pdf" value="Ver"
                                        <%if (!ver.verificarEdicionPagado(persona.getUserName(), rev.get(i).getCodigo(), rev.get(i).getEdiciones().get(j))) {
                                        %>disabled="true"<%}%>>
                            </p>

                            <input hidden="true" name="codigo" value="<%=rev.get(i).getCodigo()%>"><input hidden="true" name="ed" value="<%=rev.get(i).getEdiciones().get(j).getNumeroEd()%>">

                        </form>
                        <%
                            }%>
                        <br>
                    </div>
                </div>
                <div class="collapse" id="collapseComentarios<%=i%>">
                    <div class="card card-body">
                        <%for (int j = 0; j < rev.get(i).getComentario().size(); j++) {%>
                        <p style="font-size: 15px;"> <a style="font-size: 17px;color: #ef643d"><%=rev.get(i).getComentario().get(j).getUserName()%> :</a>&nbsp;&nbsp;&nbsp;
                            <%=rev.get(i).getComentario().get(j).getComentario()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </p>
                        <%
                            }%>
                        
                        <form method="post" onsubmit=";">
                            <input type="text" placeholder="Que piensas sobre la Revista?" style="width: 30%" id="comentario" name="comentario">
                            <input type="button" onclick="return guardarComentario()" name="Comentar" class="btn btn-primary" value="Comentar">
                        </form>
                        <br>
                    </div>
                </div>
                <%}
                    }

                %>

                <script type="text/javascript" src="/Revistas/AreaSuscriptor/verificarEdiciones.js"></script>
                <script type="text/javascript" src="/Revistas/AreaSuscriptor/Comentar.js"></script>
                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

                </body>
                </html>
