<%@page import="java.time.LocalDate"%>
<%@page import="ControladorDB.ControlReportes"%>
<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Usuarios.Usuario"%>
<%@page import="Usuarios.Usuario"%>
<%@page import="ControladorDB.Controlador"%>
<h1 style="text-align: center;color: tomato">Ganancias</h1>
<br>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">

    <form action="/Revistas/FiltracionEd" method="post">
        Elije una Revista:     <select name="RevistaViejaG"class="custom-select" style="width: 20%">
            <div style="text-align: left">
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 80px;text-align: left">
                    <option class="dropdown-item" href="#">Ninguno</option>
                    <%Controlador controlG = new Controlador();
                        ArrayList<Revista> ganancias = controlG.obtnerRevistasPorAutor(((Usuario) request.getSession().getAttribute("Usuario")).getUserName());
                        for (int idx = 0; idx < ganancias.size(); idx++) {
                            if (ganancias.get(idx).getEstado() == 2) {%>
                    <option class="dropdown-item" href="#"><%=ganancias.get(idx).getCodigo()%></option>
                    <%}
                        }%>
                </div> 

            </div>
            </div>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioG")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  Final <input name="fin" value="<%=request.getAttribute("finG")%>"type="date">  &nbsp;&nbsp;<input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarG">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="RecetearG">
    </form>
</div>

<br>
<br>

<% 
    if (request.getAttribute("RevistasAMostrarG") != null) {
        ganancias = (ArrayList) request.getAttribute("RevistasAMostrarG");
    }
%>

<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Suscripciones</th>
            <th scope="col">Ganancias</th>
        </tr>
    </thead>
    <tbody>
        <%float gananciaTotal=0;
            ControlReportes controlG2 = new ControlReportes();
            for (int idx = 0; idx < ganancias.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=ganancias.get(idx).getCodigo()%></td>
            <td><%=ganancias.get(idx).getNombre()%></td>
            <td><div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Suscripcion
                    </button>
                    <div class="dropdown-menu">
                            <%for (int j = 0; j < ganancias.get(idx).getSuscripcions().size(); j++) {%>
                            <a class="dropdown-item"><%= ganancias.get(idx).getSuscripcions().get(j).getUsuario().getUserName()+ " Gan:" + (ganancias.get(idx).getSuscripcions().get(j).getIngresos() - (ganancias.get(idx).getSuscripcions().get(j).getIngresos() * (1 / controlG2.obtenerPorcentajeDelSistema())))%></a>
                            <%}%>

                    </div>
                </div></td>
             <% gananciaTotal=gananciaTotal+ganancias.get(idx).getIngresos((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))-ganancias.get(idx).getPerdida((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"));%>
            <td><%=ganancias.get(idx).getIngresos((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))-ganancias.get(idx).getPerdida((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))%></td>
        
        </tr>
        <%
            }%>


    </tbody>
</table>

