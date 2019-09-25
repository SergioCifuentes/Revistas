<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Usuarios.Usuario"%>

<% Usuario user = (Usuario) request.getSession().getAttribute("Usuario");%>
<br>
<a style="font-size: 50px;color: tomato;font: fantasy"><%=user.getUserName()%></a>
<br>
<div style="width:30%" class="container">
    <img src="ControladorImagen?userName=<%=user.getUserName()%>" width="250" height="300" class="rounded-circle">
    <br>
    <form>
        <div class="container" style="width: 65%">
            <input type="submit" class="btn btn-dark" value="Editar Foto">
        </div>
    </form>
    <br>

</div>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Descripcion:</b>
    <br>
    <%if (user.getPerfil().getDescripcion() != "") {%>
    <a style="color: black"><%=user.getPerfil().getDescripcion()%></a>
    <%} else {%>
    <a style="color: black">------</a>
    <%}%>
</div>
<br>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Hobbies:</b>
    <br>
    <%if (user.getPerfil().getHobbies() != "") {%>
    <a style="color: black"><%=user.getPerfil().getHobbies()%></a>
    <%} else {%>
    <a style="color: black">------</a>
    <%}%>
</div>
<br>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Temas De Interes:</b>
    <br>
    <%if (user.getPerfil().getTemasDeInteres() != "") {%>
    <a style="color: black"><%=user.getPerfil().getTemasDeInteres()%></a>
    <%} else {%>
    <a style="color: black">------</a>
    <%}%>
</div>
<br>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Gustos:</b>
    <br>
    <%System.out.println("sdawdws" + user.getPerfil().getGustos() + "xxx");
        if (user.getPerfil().getGustos() != "") {%>
    <a style="color: black"><%=user.getPerfil().getGustos()%></a>
    <%} else {%>
    sdws
    <a style="color: black">------</a>
    <%}%>
</div>
<br>
