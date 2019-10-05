<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Usuarios.Usuario"%>

<% Usuario user = (Usuario) request.getSession().getAttribute("Usuario");%>
<br>
<a style="font-size: 50px;color: tomato;font: fantasy"><%=user.getUserName()%></a>
<br>
<div style="width:30%" class="container">
    <img src="ControladorImagen?userName=<%=user.getUserName()%>" width="250" height="300" class="rounded-circle">
    <br>
    <form action="CambiarFoto" method="POST" enctype="multipart/form-data">
        <div class="container" style="width: 90%">
            <div class="custom-file">
                <input  type="file" class="custom-file-input" name="cambiar"id="cambiar"accept=".jpg,.png" aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">Cambiar Foto</label> 
                <input name="Cambiar"type="submit" class="btn btn-success" value="Cambiar">
            </div>
        </div>
    </form>
    <br>

</div>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Descripcion:</b>
    <br>
    <%if (!user.getPerfil().getDescripcion().equals("")) {%>
    <a style="color: black"><%=user.getPerfil().getDescripcion()%></a>
    <%} else {%>
    <a style="color: gray">'Editar Informacion' para agregar campo</a>
    <%}%>
</div>
<br>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Hobbies:</b>
    <br>
    <%if (!user.getPerfil().getHobbies().equals("")) {%>
    <a style="color: black"><%=user.getPerfil().getHobbies()%></a>
    <%} else {%>
    <a style="color: gray">'Editar Informacion' para agregar campo</a>
    <%}%>
</div>
<br>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Temas De Interes:</b>
    <br>
    <%if (!user.getPerfil().getTemasDeInteres().equals("")) {%>
    <a style="color: black"><%=user.getPerfil().getTemasDeInteres()%></a>
    <%} else {%>
    <a style="color: gray">'Editar Informacion' para agregar campo</a>
    <%}%>
</div>
<br>
<div class="container" style=" border: 5px ridge  #424040;">
    <b style="color: tomato">Gustos:</b>
    <br>
    <%System.out.println("sdawdws" + user.getPerfil().getGustos() + "xxx");
        if (!user.getPerfil().getGustos().equals("")) {%>
    <a style="color: black"><%=user.getPerfil().getGustos()%></a>
    <%} else {%>

    <a style="color: gray">'Editar Informacion' para agregar campo</a>
    <%}%>
</div>
<br>
