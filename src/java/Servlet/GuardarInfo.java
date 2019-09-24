/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.Controlador;
import Usuarios.Perfil;
import Usuarios.Persona;
import Usuarios.Suscriptor;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "GuardarInfo", urlPatterns = {"/CrearPerfil"})
public class GuardarInfo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Perfil nuevoPerfil = new Perfil(request);
        Controlador co = new Controlador();
        HttpSession misession= (HttpSession) request.getSession();
        Suscriptor nueva=(Suscriptor)misession.getAttribute("Usuario");
        nueva.setPerfil(nuevoPerfil);
        co.crearPerfil(nueva.getUserName(), nuevoPerfil);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio/IngresoFoto.jsp");
            dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */


}
