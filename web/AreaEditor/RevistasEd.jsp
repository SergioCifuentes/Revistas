<%-- 
    Document   : RevistasEd
    Created on : Oct 1, 2019, 9:41:52 AM
    Author     : sergio
--%>

<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ControladorDB.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Revistas</title>
    </head>
    <body>
        <%@include file="/Componentes/cabeceraUsuario.jsp"%>
        <br>
        <br>
        <br>
        <div class="container" style="width: 90%;text-align: center">
            <%@include file="ComponentesMenu/MenuRevistas.html"%>
        </div>
        <br>
        <div class="container" style="width: 75%">
            <% Controlador co = new Controlador();
                ArrayList<Revista> rev = co.obtnerRevistasPorAutor(persona.getUserName());
                if (rev.size() > 0) {

                    for (int i = 0; i < rev.size(); i++) {
            %>

            <div class="card">
                <div class="card-header">
                    <%=rev.get(i).getNombre()%>
                </div>
                <div class="card-body">
                    <h5 class="card-title">Codigo : <%=rev.get(i).getCodigo()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <% if(rev.get(i).getEstado()==1){%>
                            Estado: Pendiente...
                        <%}else{%>
                        Suscritos: <%=rev.get(i).getSuscripcions().size()%></h5>
                        <%}%>
                    <p class="card-text"><%= rev.get(i).getDescripcion()%></p>
                    <p>
                        <% if(rev.get(i).getEstado()==1){%>
                            
                        <%}else{%>
                        <output class="btn btn-secondary" data-toggle="collapse" role="button"aria-expanded="false" aria-controls="collapseExample"><%=rev.get(i).getLikes()%> Likes</output>
                        
                        <a class="btn btn-secondary" data-toggle="collapse" href="#collapseComentarios<%=i%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Comentarios
                        </a>
                        <%}%>
                        
                        <a class="btn btn-dark" data-toggle="collapse" href="#collapseExample<%=i%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Ediciones
                        </a>                        
                    </p>
                </div>
                <div class="collapse" id="collapseExample<%=i%>">
                    <div class="card card-body">
                        <%for (int j = 0; j < rev.get(i).getEdiciones().size(); j++) {%>
                        <p style="font-size: 15px">#<%=rev.get(i).getEdiciones().get(j).getNumeroEd()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <%=rev.get(i).getEdiciones().get(j).getNombre()%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            Publicado: <%=rev.get(i).getEdiciones().get(j).getFecha()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </p><a class="btn btn-success"style="display: inline-block; width:7%" href="pdf?codigo=<%=rev.get(i).getCodigo()%>&ed=<%=rev.get(i).getEdiciones().get(j).getNumeroEd()%>" target="_blank" title="pdf">VER</a>
                        <%}
                        %>
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
                           
                        <br>
                    </div>
                </div>
            </div>                              






            <br>

            <%
                }
            } else {%>
            <h2>No Cuentas Con Revistas</h2>    
            <%
                }
            %>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

    </body>
</html>
