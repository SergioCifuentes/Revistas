/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.Controlador;
import ControladorDB.Controlador2;
import Revista.Comentario;
import Revista.Reaccion;
import Usuarios.Suscriptor;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class Comentar extends HttpServlet {

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
            out.println("<title>Servlet Comentar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Comentar at " + request.getContextPath() + "</h1>");
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
        Controlador2 co = new Controlador2();
        Controlador co2 = new Controlador();
        if (request.getParameter("Like") != null) {
            Reaccion reaccion=null;
            if ("DisLike".equals(request.getParameter("DisLike"))) {
                 reaccion = new Reaccion((Suscriptor) request.getSession().getAttribute("Usuario"),true, null, LocalDate.now());
            } else if ("Like".equals(request.getParameter("DisLike"))) {
                 reaccion = new Reaccion((Suscriptor) request.getSession().getAttribute("Usuario"),false, null, LocalDate.now());
            }
            co.guardarLike(reaccion, request.getParameter("Revis"));
            getServletContext().getRequestDispatcher("/AreaSuscriptor/Home.jsp").forward(request, response);
        }
        
        
        if (request.getParameter("Comentar") != null) {
            Comentario comentario = new Comentario(((Suscriptor) request.getSession().getAttribute("Usuario")).getUserName()
                    ,String.valueOf(request.getParameter("comentario")), LocalDate.now());
            co.guardarComentario(comentario, request.getParameter("Revis"));
            getServletContext().getRequestDispatcher("/AreaSuscriptor/Home.jsp").forward(request, response);
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
