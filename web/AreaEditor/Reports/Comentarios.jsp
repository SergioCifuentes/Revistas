<%@page import="Revista.Revista"%>
<%@page import="ControladorDB.Controlador"%>
<%@page import="ControladorDB.Controlador2"%>
<%@page import="Usuarios.Usuario"%>
<%@page import="Usuarios.Persona"%>
<%@page import="ControladorDB.ControlReportes"%>
<%@page import="java.util.ArrayList"%>
<h1 style="text-align: center;color: tomato">Comentarios</h1>
<br>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">

    <form action="/Revistas/FiltracionEd" method="post">
        Elije una Revista:     <select name="RevistaVieja"class="custom-select" style="width: 20%">
            <div style="text-align: left">
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 80px;text-align: left">
                    <option class="dropdown-item" href="#">Ninguno</option>
                    <%Controlador control = new Controlador();
                        ArrayList<Revista> revistasM = control.obtnerRevistasPorAutor(((Usuario) request.getSession().getAttribute("Usuario")).getUserName());
                        for (int idx = 0; idx < revistasM.size(); idx++) {
                            if (revistasM.get(idx).getEstado() == 2) {        %>
                    <option class="dropdown-item" href="#"><%=revistasM.get(idx).getCodigo()%></option>
                    <%}
                        }%>
                </div> 

            </div>
            </div>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioC")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Final <input name="fin" value="<%=request.getAttribute("finC")%>"type="date">  &nbsp;&nbsp;<input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarC">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="RecetearC">
    </form>

</div>
<br>
<br>

<%    ArrayList<String[]> comen;
    if (request.getAttribute("RevistasAMostrarC") != null) {
        comen = (ArrayList) request.getAttribute("RevistasAMostrarC");
    } else {
        ControlReportes com = new ControlReportes();
        comen = com.obtenerComentarios(null, null, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), null);
    }
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Usuario</th>
            <th scope="col">Comentario</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < comen.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=comen.get(idx)[0]%></td>
            <td><%=comen.get(idx)[1]%></td>
            <td><%=comen.get(idx)[3]%></td>
            <td><%=comen.get(idx)[2]%></td>
        </tr>
        <%
            }%>


    </tbody>
</table>
