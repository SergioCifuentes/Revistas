<%@page import="ControladorDB.ControlReportes"%>
<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Usuarios.Usuario"%>
<%@page import="ControladorDB.Controlador"%>
<h1 style="text-align: center;color: tomato">Suscripciones</h1>
<br>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">

    <form action="/Revistas/FiltracionEd" method="post">
        Elije una Revista:     <select name="RevistaVieja"class="custom-select" style="width: 20%">
            <div style="text-align: left">
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 80px;text-align: left">
                    <option class="dropdown-item" href="#">Ninguno</option>
                    <%Controlador controlS = new Controlador();
                        ArrayList<Revista> revistasMS = controlS.obtnerRevistasPorAutor(((Usuario) request.getSession().getAttribute("Usuario")).getUserName());
                        for (int idx = 0; idx < revistasMS.size(); idx++) {
                            if (revistasMS.get(idx).getEstado() == 2) {        %>
                    <option class="dropdown-item" href="#"><%=revistasMS.get(idx).getCodigo()%></option>
                    <%}
                        }%>
                </div> 

            </div>
            </div>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioS")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Final <input name="fin" value="<%=request.getAttribute("finS")%>"type="date">  &nbsp;&nbsp;<input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarS">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="RecetearS">
    </form>
        </div>
<br>
<br>

<%    ArrayList<String[]> suscrip;
    if (request.getAttribute("RevistasAMostrarS") != null) {
        suscrip = (ArrayList) request.getAttribute("RevistasAMostrarS");
    } else {
        ControlReportes com2 = new ControlReportes();
    
        suscrip = com2.obtenerSuscripcion(null, null, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), null);
    }
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Usuario</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < suscrip.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=suscrip.get(idx)[0]%></td>
            <td><%=suscrip.get(idx)[1]%></td>
            <td><%=suscrip.get(idx)[2]%></td>
        </tr>
        <%
            }%>


    </tbody>
</table>
