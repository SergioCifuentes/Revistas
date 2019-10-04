<%@page import="Usuarios.Usuario"%>
<% Usuario persona = (Usuario) request.getSession().getAttribute("Usuario");%>
<form action="./LogOut">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <h2 style="color: tomato;">Revistas IPC2</h2>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">


            </ul>
            <form>
                <img src="ControladorImagen?userName=<%=persona.getUserName()%>" width="40" height="40" class="rounded-circle">&nbsp;
                <a class="navbar-brand" href="#"><%=persona.getUserName()%></a>

                <input  type="submit"class="btn btn-light" value="LogOut" >

            </form>
        </div>
    </nav>
</form>   
