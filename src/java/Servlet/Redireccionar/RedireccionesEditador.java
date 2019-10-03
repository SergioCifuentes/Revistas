/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Redireccionar;

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
public class RedireccionesEditador extends HttpServlet {

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
            out.println("<title>Servlet RedireccionesEditador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RedireccionesEditador at " + request.getContextPath() + "</h1>");
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
        
        if ("Perfil".equals(request.getParameter("Perfil"))) {
            getServletContext().getRequestDispatcher("/AreaEditor/PerfilEditador.jsp").forward(request, response);
            }
        if ("Home".equals(request.getParameter("Home"))) {
            getServletContext().getRequestDispatcher("/AreaEditor/HomeEditador.jsp").forward(request, response);
            }  
        if ("Editar Informacion".equals(request.getParameter("EditarInfo"))) {
            getServletContext().getRequestDispatcher("/AreaEditor/EditarInfo.jsp").forward(request, response);
            }
        if ("Publicar".equals(request.getParameter("Publicar"))) {
            getServletContext().getRequestDispatcher("/AreaEditor/Publicacion.jsp").forward(request, response);
            }     
        if ("Revistas".equals(request.getParameter("Revistas"))) {
            getServletContext().getRequestDispatcher("/AreaEditor/RevistasEd.jsp").forward(request, response);
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
