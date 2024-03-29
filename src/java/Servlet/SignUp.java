/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.Controlador;
import Usuarios.Suscriptor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio
 */
@WebServlet(name = "SignUp", urlPatterns = {"/CrearUsuario"})
public class SignUp extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Suscriptor nuevousuario = new Suscriptor(request);
        Controlador co = new Controlador();
        if (!co.verificarUserName(request.getParameter("userName"))) {
            if (request.getParameter("pass").length()<8 || !request.getParameter("pass").equals(request.getParameter("confirmacion")) ) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Revistas/Inicio/SignUp.jsp");
            dispatcher.forward(request, response);
            }else{
                co.crearUsuario(nuevousuario.getUserName(),nuevousuario.getPassword());
                HttpSession nuevoSession = request.getSession(true);
                nuevoSession.setAttribute("Usuario", nuevousuario);
            request.setAttribute("usuarioCreado", nuevousuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio/IngresoInfo.jsp");
            dispatcher.forward(request, response);
            }
        }else{
            request.setAttribute("errorUserExistente", nuevousuario);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio/SignUp.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
