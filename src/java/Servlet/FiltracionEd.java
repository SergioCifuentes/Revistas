/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.ControlReportes;
import ControladorDB.Controlador;
import Revista.Revista;
import Usuarios.Usuario;
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
public class FiltracionEd extends HttpServlet {

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
            out.println("<title>Servlet FiltracionEd</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FiltracionEd at " + request.getContextPath() + "</h1>");
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
            if ("Ninguno".equals(request.getParameter("RevistaVieja"))) {
                request.setAttribute("RevistasAMostrarC", co.obtenerComentarios(inicio, fin, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), null));
            } else {
                request.setAttribute("RevistasAMostrarC", co.obtenerComentarios(inicio, fin, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), request.getParameter("RevistaVieja")));

            }
            request.setAttribute("comentarios", true);
            request.setAttribute("Ganancias", false);
            request.setAttribute("inicioC", inicio);
            request.setAttribute("finC", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");
            dispatcher.forward(request, response);
        }
        if ("Recetear".equals(request.getParameter("RecetearC"))) {
            request.setAttribute("comentarios", true);
            request.setAttribute("Ganancias", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");

            dispatcher.forward(request, response);
        }

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
            if ("Ninguno".equals(request.getParameter("RevistaVieja"))) {
                request.setAttribute("RevistasAMostrarS", co.obtenerSuscripcion(inicio, fin, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), null));
            } else {
                request.setAttribute("RevistasAMostrarS", co.obtenerSuscripcion(inicio, fin, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), request.getParameter("RevistaVieja")));

            }
            request.setAttribute("Suscripciones", true);
            request.setAttribute("comentarios", false);
            request.setAttribute("Ganancias", false);
            request.setAttribute("inicioS", inicio);
            request.setAttribute("finS", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");
            dispatcher.forward(request, response);
        }
        if ("Recetear".equals(request.getParameter("RecetearS"))) {
            request.setAttribute("Suscripciones", true);
            request.setAttribute("Ganancias", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");

            dispatcher.forward(request, response);
        }

        if ("Recetear".equals(request.getParameter("RecetearG"))) {
            request.setAttribute("comentarios", false);
            request.setAttribute("Ganancias", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");
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
            if ("Ninguno".equals(request.getParameter("RevistaViejaG"))) {
            } else {
                ArrayList<Revista> re = new ArrayList<Revista>();
                re.add(co2.obtnerRevistaPorCodigo(request.getParameter("RevistaViejaG")));
                request.setAttribute("RevistasAMostrarG", re);
            }
            request.setAttribute("comentarios", false);
            request.setAttribute("Ganancias", true);
            request.setAttribute("inicioG", inicio);
            request.setAttribute("finG", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");
            dispatcher.forward(request, response);
        }

        
        
        
        
        if ("Recetear".equals(request.getParameter("RecetearL"))) {
            request.setAttribute("comentarios", false);
            request.setAttribute("Ganancias", false);
            request.setAttribute("Likes", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");
            dispatcher.forward(request, response);
        }
        if ("Filtrar".equals(request.getParameter("FiltrarL"))) {
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
            if ("Ninguno".equals(request.getParameter("RevistaViejaL"))) {
                request.setAttribute("RevistasAMostrarL", co.obtenerMasLikes(inicio, fin, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), null));
            } else {
                request.setAttribute("RevistasAMostrarL", co.obtenerMasLikes(inicio, fin, ((Usuario) request.getSession().getAttribute("Usuario")).getUserName(), request.getParameter("RevistaViejaL")));
            }
            request.setAttribute("Ganancias", false);
            request.setAttribute("comentarios", false);
            request.setAttribute("Suscripciones", false);
            request.setAttribute("Likes", true);
            request.setAttribute("inicioL", inicio);
            request.setAttribute("finL", fin);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaEditor/Reportes.jsp");
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
