<%@page import="ControladorDB.Controlador2"%>
<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<h1 style="text-align: center;color: tomato">Revistas Pendientes</h1>
<br>
<% Controlador2 co = new Controlador2();
    ArrayList<Revista> rev = co.obtnerRevistasPorEstado(1);
    Float costoGlobal = co.obtenerPrecioGlobal().getCosto();
    for (int i = 0; i < rev.size(); i++) {%>
<div class="card">
    <div class="card-header">
        <%=rev.get(i).getNombre()%>                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Cuota De Suscripcion <%=rev.get(i).getCostoPorSuscripcion()%>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </div>
    <div class="card-body">
        <h5 class="card-title">Codigo : <%=rev.get(i).getCodigo()%>      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            <p class="card-text"><%= rev.get(i).getDescripcion()%></p>
            <p>
                <a class="btn btn-dark" data-toggle="collapse" href="#collapseExample<%=i%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                    Ediciones
                </a>                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <form method="post" action="/Revistas/Publicar">
                <input hidden="true"value="<%=rev.get(i).getCodigo()%>" name="Revista">
                Cuota Por Dia: <input onchange="confirmarCambio(<%=i%>)"type="number" id="costo<%=i%>"style="width: 70px"value="<%=costoGlobal%>" name="CuotaDia"min="0" step="0.01" required="true">  
                <a id="label<%=i%>" style="font-size:12px;color: green">Global</a>                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Fecha De Publicacion: <input type="date" name="fechaPublicacion" required="true">                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" class="btn btn-primary" value="Publicar">
            </form> 
            </p>
    </div>
    <div class="collapse" id="collapseExample<%=i%>">
        <div class="card card-body">

            <%for (int j = 0; j < rev.get(i).getEdiciones().size(); j++) {%>
            <form action="/Revistas/pdf">
                <p style="font-size: 15px">#<%=rev.get(i).getEdiciones().get(j).getNumeroEd()%>                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <%=rev.get(i).getEdiciones().get(j).getNombre()%>                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    Publicado: <%=rev.get(i).getEdiciones().get(j).getFecha()%>                                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input  onmouseenter="verificar(<%=i%>,<%=j%>)" id="boton<%=i + "-" + j%>" type="submit" class="btn btn-success"style="display: inline-block; width:7%" target="_blank" title="pdf" value="Ver">
                </p>
                <input hidden="true" name="codigo" value="<%=rev.get(i).getCodigo()%>"><input hidden="true" name="ed" value="<%=rev.get(i).getEdiciones().get(j).getNumeroEd()%>">

            </form>
            <%}
            %>
        </div>
    </div>

</div>  

<%}%>
<input hidden="true" id="costoConstante"value="<%=costoGlobal%>">
<script type="text/javascript" src="/Revistas/Administracion/Componentes/VerificarCambio.js"></script>
