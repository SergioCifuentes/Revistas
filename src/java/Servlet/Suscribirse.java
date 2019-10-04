/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.Controlador;
import ControladorDB.Controlador2;
import ControladorDB.Filtracion;
import Ingresos.Pago;
import Ingresos.Suscripcion;
import Usuarios.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class Suscribirse extends HttpServlet {

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
            out.println("<title>Servlet Suscribirse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Suscribirse at " + request.getContextPath() + "</h1>");
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
//        for (int i = 0; i < Filtracion.obtenerRevNoSuscritas(((Usuario)request.getSession().getAttribute("Usuario")).getUserName()).size(); i++) {
//             
//            
//        }
       if (request.getParameter("Aceptar") != null) {
            LocalDate date = LocalDate.parse(request.getParameter("fecha"));
            ArrayList<Pago> pagos = new ArrayList<>();
            for (int i = 0; i < Integer.valueOf(request.getParameter("Meses")); i++) {
                Pago pa = new Pago(Float.parseFloat(request.getParameter("cuota")), date.plusMonths(i),Integer.valueOf(request.getParameter("Tarjeta")));
                pagos.add(pa);
            }

            Suscripcion su = new Suscripcion(pagos, date, (Usuario) request.getSession().getAttribute("Usuario"));
            Controlador2 co = new Controlador2();
            
            co.guardarSuscripcion(su, request.getParameter("Revista"));
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
