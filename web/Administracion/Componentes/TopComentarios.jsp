<%@page import="ControladorDB.ControlReportes"%>
<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<h1 style="text-align: center;color: tomato">Top 5 Revistas Mas Comentadas</h1>
<br>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">

    <form action="/Revistas/Filtracion" method="post">
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioC")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Final <input name="fin" value="<%=request.getAttribute("finC")%>"type="date">  &nbsp;&nbsp;<input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarC">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="RecetearC">
    </form>

</div>
<br>
<br>

<%    ArrayList<Revista> revsAMostrar;
    if (request.getAttribute("RevistasAMostrarC") != null) {
        revsAMostrar = (ArrayList) request.getAttribute("RevistasAMostrarC");
    } else {
        ControlReportes com = new ControlReportes();
        revsAMostrar = com.obtenerMasComentadas(null, null);
    }
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Comentarios</th>
            <th scope="col">Comentores</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < revsAMostrar.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=revsAMostrar.get(idx).getCodigo()%></td>
            <td><%=revsAMostrar.get(idx).getNombre()%></td>
            <td><%=revsAMostrar.get(idx).getComentarios().size()%></td>
            <td><div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Comentarios
                    </button>
                    <div class="dropdown-menu">
                        <%for (int j = 0; j < revsAMostrar.get(idx).getComentarios().size(); j++) {%>
                        <a class="dropdown-item"><%= revsAMostrar.get(idx).getComentarios().get(j).getUserName() +": "+revsAMostrar.get(idx).getComentarios().get(j).getComentario() %></a>
                        <%}%>

                    </div>
                </div></td>
        </tr>
        <%
            }%>


    </tbody>
</table>