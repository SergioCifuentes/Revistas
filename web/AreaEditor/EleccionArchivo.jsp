<%-- 
    Document   : EleccionArchivo
    Created on : Sep 29, 2019, 5:24:16 PM
    Author     : sergio
--%>

<%@page import="Revista.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publicacion</title>
    </head>
    <body>
        <% Revista revista = (Revista) request.getAttribute("Revista");%>
        <%@include file="/Componentes/cabeceraUsuario.jsp"%>
        <br>
        <br>
        <br>
        <div class="container" style="width: 90%;text-align: center">
            <%@include file="ComponentesMenu/MenuPublicar.html"%>
        </div>
        <br>
        <br>
        <div class="container" style="width: 70%">
            <h2> Elija el Archivo(.pdf) y Seleccione Categoria y Etiquetas</h2>
        </div>
        <br>
        <br>
        <div class="container" style="width: 90%">
            <div class="container" style="text-align: center;font-size: 22px"> 
                <a style="text-align: center;font-size: 22px;">&nbsp;&nbsp;&nbsp;&nbsp;<a style="color: tomato;font-size: 22px;">Revista:</a> <%=revista.getCodigo()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a style="color: tomato;font-size: 22px;"> Autor:</a> <%=revista.getAutor().getUserName()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a style="color: tomato;font-size: 22px;">Nombre: </a> <%=revista.getNombre()%></a>
            </div>
            <br>
            <br>

            <form method="post" style="border:4px solid black" action="solicitarPublicacion" enctype="multipart/form-data">
                <%request.getSession().setAttribute("Revista", revista);%>
                <br>
                <div class="container" style="width: 90%;text-align: right">
                    Fecha Publicacion:&nbsp;&nbsp;&nbsp;<input type="date" value="fecha" name="fecha" required="true">
                </div>
                <br>
                <div class="container" style="width: 60%;">                    
                    <div class="input-group mb-3"  >
                        Nombre de la Edicion: &nbsp;&nbsp;&nbsp; <input type="text" name="nombreEd" placeholder="Ingrese el Nombre" required/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <div class="custom-file">

                            <input type="file" class="custom-file-input" name="edicion"id="edicion"accept=".pdf" aria-describedby="inputGroupFileAddon01">
                            <label class="custom-file-label" for="inputGroupFile01" >Archivo(.pdf)</label>
                        </div>
                    </div>
                </div>
                <br>
                <br>
                <div >

                    <div class="dropdown" style="display: inline-block">
                        &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        Agregue Categorias:    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Categorias
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="#">Action</a>
                        </div>
                    </div> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;

                    <div class="dropdown" style="display: inline-block">
                        Agregue Etiquetas:    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Etiquetas
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="#">Action</a>
                        </div>
                    </div>
                </div>
                <br>
        </div>

        <br>
        <br>
        <div class="container" style="text-align: center">
            <input type="submit" class="btn btn-success"name="Siguiente" id="Siguiente" value="Siguiente">
        </div>
        
    </form>
    <br>
    <br>
    <br>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

</body>
</html>
