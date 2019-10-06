/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.ControlReportes;
import ControladorDB.Controlador;
import Revista.Revista;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class Filtracion extends HttpServlet {

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
            out.println("<title>Servlet Filtracion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Filtracion at " + request.getContextPath() + "</h1>");
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
        if ("Filtrar".equals(request.getParameter("FiltrarS"))) {
            LocalDate inicio;
            LocalDate fin;
            if (!"".equals(request.getParameter("inicio"))) {
                inicio = LocalDate.parse(request.getParameter("inicio"));
            } else {
                inicio = null;
            }
            if (!"".equals(request.getParameter("fin"))) {
                fin = LocalDate.parse(request.getParameter("fin"));
            } else {
                fin = null;
            }
            ControlReportes co = new ControlReportes();
            request.setAttribute("RevistasAMostrar", co.obtenerMasSuscritos(inicio, fin));
            request.setAttribute("Suscritos", true);
            request.setAttribute("Ganancias", false);
            request.setAttribute("inicioS", inicio);
            request.setAttribute("finS", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Administracion/HomeAdmin.jsp");
            dispatcher.forward(request, response);

//            getServletContext().getRequestDispatcher("/AreaEditor/Reportes.jsp").forward(request, response);
        }
        if ("Recetear".equals(request.getParameter("RecetearS"))) {
            request.setAttribute("Suscritos", true);
            request.setAttribute("Ganancias", false);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Administracion/HomeAdmin.jsp");
            dispatcher.forward(request, response);
        }

        if ("Filtrar".equals(request.getParameter("FiltrarC"))) {
            LocalDate inicio;
            LocalDate fin;
            if (!"".equals(request.getParameter("inicio"))) {
                inicio = LocalDate.parse(request.getParameter("inicio"));
            } else {
                inicio = null;
            }
            if (!"".equals(request.getParameter("fin"))) {
                fin = LocalDate.parse(request.getParameter("fin"));
            } else {
                fin = null;
            }
            ControlReportes co = new ControlReportes();
            request.setAttribute("RevistasAMostrar", co.obtenerMasComentadas(inicio, fin));
            request.setAttribute("Suscritos", false);
            request.setAttribute("Comentarios", true);
            request.setAttribute("Ganancias", false);
            request.setAttribute("inicioC", inicio);
            request.setAttribute("finC", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Administracion/HomeAdmin.jsp");
            dispatcher.forward(request, response);

//            getServletContext().getRequestDispatcher("/AreaEditor/Reportes.jsp").forward(request, response);
        }
        if ("Recetear".equals(request.getParameter("RecetearC"))) {
            request.setAttribute("Suscritos", false);
            request.setAttribute("Comentarios", true);
            request.setAttribute("Ganancias", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Administracion/HomeAdmin.jsp");

            dispatcher.forward(request, response);
        }
        if ("Recetear".equals(request.getParameter("RecetearG"))) {
            request.setAttribute("Suscritos", false);
            request.setAttribute("Comentarios", false);
            request.setAttribute("Ganancias", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Administracion/HomeAdmin.jsp");
            dispatcher.forward(request, response);
        }
        if ("Filtrar".equals(request.getParameter("FiltrarG"))) {
            LocalDate inicio;
            LocalDate fin;
            if (!"".equals(request.getParameter("inicio"))) {
                inicio = LocalDate.parse(request.getParameter("inicio"));
            } else {
                inicio = null;
            }
            if (!"".equals(request.getParameter("fin"))) {
                fin = LocalDate.parse(request.getParameter("fin"));
            } else {
                fin = null;
            }
            ControlReportes co = new ControlReportes();
            Controlador co2 = new Controlador();
            if ("Ninguno".equals(request.getParameter("RevistaVieja"))) {
//                request.setAttribute("RevistasAMostrar", co.obtenerGanancias(inicio, fin,null));
            }else{
                ArrayList<Revista> re=new ArrayList<Revista>();
                re.add(co2.obtnerRevistaPorCodigo(request.getParameter("RevistaVieja")));
                request.setAttribute("RevistasAMostrarG",re);
            }
            
            request.setAttribute("Suscritos", false);
            request.setAttribute("Comentarios", false);
            request.setAttribute("Ganancias", true);
            request.setAttribute("inicioG", inicio);
            request.setAttribute("finG", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Administracion/HomeAdmin.jsp");
            dispatcher.forward(request, response);

            getServletContext().getRequestDispatcher("/AreaEditor/Reportes.jsp").forward(request, response);
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
