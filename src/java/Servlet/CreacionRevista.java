/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.Controlador;
import Revista.GeneradorDeCodigos;
import Revista.Revista;
import Usuarios.Editador;
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
public class CreacionRevista extends HttpServlet {

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
            out.println("<title>Servlet CreacionRevista</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreacionRevista at " + request.getContextPath() + "</h1>");
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
        
    
        if (request.getParameter("NuevaR")!=null) {
            float cuota;
            
            if ("".equals(request.getParameter("Cuota"))) {
                cuota=0;
            }else{
                cuota=Float.valueOf(request.getParameter("Cuota"));
            }
            Revista nuevaRevista=new Revista(GeneradorDeCodigos.generarCodigoRevista(),
                    String.valueOf(request.getParameter("nombre")),cuota, null, (Editador)request.getSession().getAttribute("Usuario"),1,
                    true,true,String.valueOf(request.getParameter("Descripcion")));
            request.setAttribute("Revista",nuevaRevista);
            getServletContext().getRequestDispatcher("/AreaEditor/EleccionArchivo.jsp").forward(request, response);
        }else if(request.getParameter("NuevaE")!=null){
            Controlador co = new Controlador();
            request.setAttribute("Revista",co.obtnerRevistaPorCodigo(request.getParameter("RevistaVieja")));
            getServletContext().getRequestDispatcher("/AreaEditor/EleccionArchivo2.jsp").forward(request, response);
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
