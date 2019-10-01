<%-- 
    Document   : Publicacion
    Created on : Sep 28, 2019, 3:21:29 AM
    Author     : sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Publicacion</title>
    </head>
    <body>
        <%@page import="Revista.GeneradorDeCodigos"%>
        <%@include file="/Componentes/cabeceraUsuario.jsp"%>
        <br>
        <br>
        <br>
        <div class="container" style="width: 90%;text-align: center">
            <%@include file="ComponentesMenu/MenuPublicar.html"%>
        </div>

        <br>
        <div class="container" style="width: 80%">
            <h2> Deseas Publicar una Nueva Revista o Publicar una nueva Edicion?</h2>
        </div>
        <br>
        <br>
        <form action="/Revistas/CreacionRevista" method="POST">
            <div class="container" >
                <div id="NuevaEdicion" style="width: 95%"class="container" >
                    <div class="form-check" style="text-align: left">
                        <input class="form-check-input" onchange ="realizarNuevaEdicion()" type="checkbox" value="Nueva Edicion" id="NuevaE">
                        <label class="form-check-label" for="NuevaE">
                            Nueva Edicion
                        </label>
                    </div>
                    

                    <div id="EspacioEdicion" hidden="true" style="width: 80%" class="container">
                        <div class="dropdown">
                            Elije una Revista     <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Revistas
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                <a class="dropdown-item" href="#">Action</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <br>

                <div id="NuevaRevista"style="width: 95%" class="container">
                    <div class="form-check" style="text-align: left">
                        <input class="form-check-input" onchange ="realizarNuevaRevista()" type="checkbox" value="Nueva Revista" id="NuevaR">
                        <label class="form-check-label" for="NuevaR">
                            Nueva Revista
                        </label>
                    </div>
                    
                    <br>
                    <div class="container"id="EspacioRevista" hidden="true" style="width: 85%">
                        <a id="codigo"> Codigo: <%=GeneradorDeCodigos.generarCodigoRevista()%> </a><br>
                        <a id="nombre"> Autor: <%=persona.getUserName()%> </a>
                        <br><br>
                        Nombre: <input name="nombre" id="nombre"type="text" placeholder="Ingrese Nombre"><br><br>
                        Cuota De Suscripcion <input id="Cuota" type="number" name="Cuota" placeholder="0" width="10px" min="0" step="0.01">

                    </div>
                </div>


                <br>
                <br>
                <br>
            </div>
            <div class="container" style="text-align: center">
                <input type="submit" class="btn btn-success" id="Siguiente" disabled="true" value="Siguiente">
            </div>

        </div>
    </form>
    <script type="text/javascript" src="/Revistas/AreaEditor/manejadorCheckbox.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

</body>
</html>
