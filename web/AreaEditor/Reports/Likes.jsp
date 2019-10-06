<%@page import="ControladorDB.ControlReportes"%>
<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Usuarios.Usuario"%>
<%@page import="Usuarios.Usuario"%>
<%@page import="ControladorDB.Controlador"%>
<%@page import="ControladorDB.Controlador"%>
<h1 style="text-align: center;color: tomato">Likes</h1>
<br>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">

    <form action="/Revistas/FiltracionEd" method="post">
        Elije una Revista:     <select name="RevistaViejaL"class="custom-select" style="width: 20%">
            <div style="text-align: left">
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 80px;text-align: left">
                    <option class="dropdown-item" href="#">Ninguno</option>
                    <%Controlador controlL = new Controlador();
                        ArrayList<Revista> revistasL = controlL.obtnerRevistasPorAutor(((Usuario) request.getSession().getAttribute("Usuario")).getUserName());
                        for (int idx = 0; idx < revistasL.size(); idx++) {
                            if (revistasL.get(idx).getEstado() == 2) {        %>
                    <option class="dropdown-item" href="#"><%=revistasL.get(idx).getCodigo()%></option>
                    <%}
                        }%>
                </div> 

            </div>
            </div>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioL")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Final <input name="fin" value="<%=request.getAttribute("finL")%>"type="date">  &nbsp;&nbsp;<input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarL">
        &nbsp;<input class="btn btn-light" type="submit" value="Recetear" name="RecetearL">
    </form>
</div>

<br>
<br>

<%    
    if (request.getAttribute("RevistasAMostrarL") != null) {

        revistasL = (ArrayList) request.getAttribute("RevistasAMostrarL");
    } else {
        ControlReportes comL = new ControlReportes();
        revistasL = comL.obtenerMasLikes(null,null, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(),null);
    }
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Likes</th>
            <th scope="col">Usuarios</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < revistasL.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=revistasL.get(idx).getCodigo()%></td>
            <td><%=revistasL.get(idx).getNombre()%></td>
            <td><%=revistasL.get(idx).getLike().size()%></td>
            <td><div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Users
                    </button>
                    <div class="dropdown-menu">
                        <%for (int j = 0; j < revistasL.get(idx).getLike().size(); j++) {%>
                        <a class="dropdown-item"><%= revistasL.get(idx).getLike().get(j)%></a>
                        <%}%>

                    </div>
                </div></td>
        </tr>
        <%
            }%>


    </tbody>
</table>