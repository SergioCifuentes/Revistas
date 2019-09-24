<%@page import="Usuarios.Persona"%>
<% Persona persona = (Persona) request.getSession().getAttribute("Usuario");%>
<form action="LogOut">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">Revistas IPC2</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">


            </ul>
            <form>
                <a class="navbar-brand" href="#"><%=persona.getUserName()%></a>

                <input  type="submit"class="btn btn-light" value="LogOut" >

            </form>
        </div>
    </nav>
</form>   
