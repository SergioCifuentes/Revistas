<%@page import="Usuarios.Usuario"%>
<%@page import="Ingresos.VerificacionDePago"%>
<%VerificacionDePago verPago = new VerificacionDePago();
    int atrazos = verPago.obtenerAtrazos(((Usuario) request.getSession().getAttribute("Usuario")).getUserName()).size();%>
<form method="POST" action="/Revistas/RedireccionesSuscriptor">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <input name="Mis Revistas" class="btn btn-dark" type="submit" style="font-size: 25px;width: 240px" value="Mis Revistas"> <span class="sr-only">(current)</span>
                </li>
                <li class="nav-item">
                    <input name="Buscar Revistas" value="Buscar Revistas" class="btn btn-light" type="submit" style="font-size: 25px;width: 240px">
                </li>
                <li class="nav-item">
                    <input name="Mi Perfil" value="Mi Perfil" class="btn btn-light" type="submit" style="font-size: 25px;width: 240px">
                </li>
                <li class="nav-item">
                    <button name="Pagos" value="Pagos" class="btn btn-light" type="submit" style="font-size: 25px;width: 240px"> Pagos<% if (atrazos> 0) {%>
                        <span class="badge badge-danger"><%=atrazos%></span></button>  <span class="sr-only">unread messages</span>
                    <%}%></button>
                </li>
            </ul>
        </div>
    </nav>

</form>