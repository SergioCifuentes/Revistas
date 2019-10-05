<%@page import="ControladorDB.ControlReportes"%>
<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<h1 style="text-align: center;color: tomato">Top 5 Revistas Mas Suscritas</h1>
<br>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">

    <form action="/Revistas/Filtracion" method="post">
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioS")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Final <input name="fin" value="<%=request.getAttribute("finS")%>"type="date">  &nbsp;&nbsp;<input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarS">
        &nbsp;<input class="btn btn-light" type="submit" value="Recetear" name="RecetearS">
    </form>

</div>
<br>
<br>

<%    ArrayList<Revista> revAMostrar;
    if (request.getAttribute("RevistasAMostrar") != null) {
        revAMostrar = (ArrayList) request.getAttribute("RevistasAMostrar");
    } else {
        ControlReportes com = new ControlReportes();
        revAMostrar = com.obtenerMasSuscritos(null, null);
    }
%>


<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Suscripciones</th>
            <th scope="col">Suscriptores</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < revAMostrar.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=revAMostrar.get(idx).getCodigo()%></td>
            <td><%=revAMostrar.get(idx).getNombre()%></td>
            <td><%=revAMostrar.get(idx).getSuscripcions().size()%></td>
            <td><div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Suscriptores
                    </button>
                    <div class="dropdown-menu">
                        <%for (int j = 0; j < revAMostrar.get(idx).getSuscripcions().size(); j++) {%>
                        <a class="dropdown-item"><%= revAMostrar.get(idx).getSuscripcions().get(j).getUsuario().getUserName()%></a>
                        <%}%>

                    </div>
                </div></td>
        </tr>
        <%
            }%>


    </tbody>
</table>
