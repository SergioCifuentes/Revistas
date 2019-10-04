<%-- 
    Document   : Pagos
    Created on : Oct 4, 2019, 12:54:38 PM
    Author     : sergio
--%>

<%@page import="Ingresos.Atrazo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagos</title>
    </head>
    <body>
        <%@include file="/Componentes/cabeceraUsuario.jsp"%>       
        <br>
        <br>
        <br>
        <br>
        <div class="container" style="width: 75%;text-align: center">
            <%@include file="ComponentesSuscriptor/MenuPagos.jsp"%>
        </div>

        <br>
        <%ArrayList<Atrazo> atr = verPago.obtenerAtrazos(persona.getUserName());%>
        <div class="container" style="width: 75%">


            <%
                if (atr.size() == 0) {
            %> 
            <br><br>
            <h2 style="text-align: center">No tienes niguna Suscripcion Pendiente De Pago

                <br>
            </h2><%
            } else {
                for (int i = 0; i < atr.size(); i++) {
            %>

            <div class="card">
                <div class="card-header" style="background-color: #d40202;font: bold">
                    <%=atr.get(i).getRevista().getNombre()%>
                </div>
                <div class="card-body">
                    <%if(atr.get(i).getDiasFaltantes()>0){%>
                    <h5> La Suscripcion Vence en <%=atr.get(i).getDiasFaltantes()%> Dia(s)</h5>
                    <%}else if(atr.get(i).getDiasAtrazado()==0){%>
                    <h5> La Suscripcion Vencio Hoy</h5>
                    <%}else if(atr.get(i).getDiasAtrazado()>0){%>
                    <h5> La Suscripcion Vencio Hace <%=atr.get(i).getDiasAtrazado()%> Dia(s)</h5>
                    <%}%>
                    <p>
                        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample<%=i%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                            Pagar
                        </a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a class="btn btn-secondary" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="collapseExample">
                            DesSuscribirse
                        </a>                        
                    </p>
                </div>
                <div class="collapse" id="collapseExample<%=i%>">
                    <div class="card card-body">
                        <form method="post" action="/Revistas/PagarSuscripcion">
                            <input hidden="true"value="<%=atr.get(i).getRevista().getCodigo()%>" name="Revista">
                            Costo/Mes: $<input hidden="true"value="<%=atr.get(i).getRevista().getCostoPorSuscripcion()%>" name="cuota"><output id="cuota<%=i%>" style="width: 13%" ><%=atr.get(i).getRevista().getCostoPorSuscripcion()%></output> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            
                            Meses: <input  onchange="confirmarCambio(<%=i%>)"id="Meses<%=i%>" required="true"type="number" name="Meses" style="width: 7%" width="7%" value="1"min="1" step="1"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;#Tarjeta De Credito 
                            <input type="text" placeholder="Tarjeta" name="Tarjeta" id="Tarjeta"required="true" >     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <br>Total: $<label style="width: 13%" id="Total<%=i%>"><%=atr.get(i).getRevista().getCostoPorSuscripcion()%></label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Fecha: <input type="date" id="fecha" name="fecha" value="<%=(atr.get(i).getUltimoDiaPagado().plusMonths(1))%>"required="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                            <input type="submit" class="btn btn-dark" value="Aceptar" name="Aceptar">
                        </form>
                    </div>
                </div>

                <%}
                    }%>
            </div>
        </div>
            <script type="text/javascript" src="/Revistas/AreaSuscriptor/meses.js"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script> 

    </body>
</html>
