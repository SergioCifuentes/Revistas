/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Redireccionar;

import ControladorDB.Controlador;
import Usuarios.Perfil;
import Usuarios.Suscriptor;
import Usuarios.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio
 */
public class ActualizarPerfil extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActualizarPerfil</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarPerfil at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("pppp"+request.getParameter("Cancelar"));
        if ("Cancelar".equals(request.getParameter("Cancelar"))) {
            getServletContext().getRequestDispatcher("/AreaEditor/PerfilEditador.jsp").forward(request, response);
            }
        if ("Guardar".equals(request.getParameter("Guardar"))) {
            Perfil nuevoPerfil = new Perfil(request);
        Controlador co = new Controlador();
        HttpSession misession= (HttpSession) request.getSession();
        Usuario nueva=(Usuario)misession.getAttribute("Usuario");
        nueva.getPerfil().AcualizarInfo(request);
        co.actualizarPerfil(nueva.getUserName(),nuevoPerfil);
            getServletContext().getRequestDispatcher("/AreaEditor/PerfilEditador.jsp").forward(request, response);
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
