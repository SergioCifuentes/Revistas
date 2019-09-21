/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.Controlador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class InicioSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");

        String password = request.getParameter("password");
        Controlador control = new Controlador();
        if (control.verificarUserName(userName)) {
            response.sendRedirect("/Revistas/Inicio/SignUp.jsp");
        } else {
            response.sendRedirect("/Revistas/Inicio/Login.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     */
}
