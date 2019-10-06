<%@page import="java.time.LocalDate"%>
<%@page import="ControladorDB.ControlReportes"%>
<%@page import="Revista.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ControladorDB.Controlador2"%>
<h1 style="text-align: center;color: tomato">Ganancias</h1>
<br>
<div class="p-3 mb-2 bg-dark text-white" style="text-align: right">

    <form action="/Revistas/Filtracion" method="post">
        Elije una Revista:     <select name="RevistaVieja"class="custom-select" style="width: 20%">
            <div style="text-align: left">
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" style="width: 80px;text-align: left">
                    <option class="dropdown-item" href="#">Ninguno</option>
                    <%Controlador2 control = new Controlador2();
                        ControlReportes control2 = new ControlReportes();
                        ArrayList<Revista> revistasM = control.obtnerRevistasPorEstado(2);
                        for (int idx = 0; idx < revistasM.size(); idx++) {%>
                    <option class="dropdown-item" href="#"><%=revistasM.get(idx).getCodigo()%></option>
                    <%
                        }%>
                </div> 

            </div>
            </div>
        </select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        Inicio: <input name="inicio"type="date" value="<%=request.getAttribute("inicioG")%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
        Final <input name="fin" value="<%=request.getAttribute("finG")%>"type="date">  &nbsp;&nbsp;<input class="btn btn-light" type="submit" value="Filtrar" name="FiltrarG">
        &nbsp;<input class="btn btn-light" type="submit" value="Resetear" name="RecetearG">
        <br>
    </form>

    <%
        if (request.getAttribute("RevistasAMostrarG") != null) {
            revistasM = (ArrayList) request.getAttribute("RevistasAMostrarG");
        }
    %>

</div>
<%float gananciaTotal = 0;%>
<table class="table">
    <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Suscripcion</th>
            <th scope="col">Ingreso</th>
            <th scope="col">Perdidas</th>
            <th scope="col">Ganancias</th>
        </tr>
    </thead>
    <tbody>
        <%for (int idx = 0; idx < revistasM.size(); idx++) {%>
        <tr>
            <th scope="row"><%=idx + 1%></th>
            <td><%=revistasM.get(idx).getCodigo()%></td>
            <td><%=revistasM.get(idx).getNombre()%></td>
            <td><div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Suscripcion
                    </button>
                    <div class="dropdown-menu">
                            <%for (int j = 0; j < revistasM.get(idx).getSuscripcions().size(); j++) {%>
                            <a class="dropdown-item"><%= revistasM.get(idx).getSuscripcions().get(j).getUsuario().getUserName() + " Ingr:" + revistasM.get(idx).getSuscripcions().get(j).getIngresos()
                                    + " Gan:" + (revistasM.get(idx).getSuscripcions().get(j).getIngresos() - (revistasM.get(idx).getSuscripcions().get(j).getIngresos() * (1 / control2.obtenerPorcentajeDelSistema())))%></a>
                            <%}%>

                    </div>
                </div></td>
            <td><%=revistasM.get(idx).getIngresos((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))%></td>
            <td><%=revistasM.get(idx).getPerdida((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))%></td>
            <% gananciaTotal=gananciaTotal+revistasM.get(idx).getIngresos((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))-revistasM.get(idx).getPerdida((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"));%>
            <td><%=revistasM.get(idx).getIngresos((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))-revistasM.get(idx).getPerdida((LocalDate) request.getAttribute("inicioG"), (LocalDate) request.getAttribute("finG"))%></td>
        </tr>
        <%
            }%>
    

    </tbody>

</table>
    <br>
            <h2>Ganancia Total : <%=gananciaTotal%></h2>
    