/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ControladorDB.Controlador;
import Usuarios.Administrador;
import Usuarios.Editador;
import Usuarios.Persona;
import Usuarios.Suscriptor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sergio
 */
public class InicioSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String userName = request.getParameter("userName");

        String password = request.getParameter("pass");
        Controlador control = new Controlador();
        if (control.verificarUserName(userName)) {
            Persona usuario = control.obtenerUsuario(userName, password);
            HttpSession nuevoSession = request.getSession(true);
                nuevoSession.setAttribute("Usuario", usuario);
            if (usuario!=null) {
                request.setAttribute("Usuario",usuario);
               try {
                     Administrador admin = (Administrador)usuario;
                    request.setAttribute("Ganancias",true);
                    request.setAttribute("Suscritos",false);
                    request.setAttribute("Pendiente",false);
                    request.setAttribute("Comentarios",false);
                    getServletContext().getRequestDispatcher("/Administracion/HomeAdmin.jsp").forward(request, response);
                
                } catch (Exception e) {
                    try {
                        Editador editador= (Editador)usuario;
                        
                        getServletContext().getRequestDispatcher("/AreaEditor/HomeEditador.jsp").forward(request, response);
                    } catch (Exception ex) {
                        Suscriptor suscriptor= (Suscriptor)usuario;
                        getServletContext().getRequestDispatcher("/AreaSuscriptor/Home.jsp").forward(request, response);
                    }
                }
 
            
            
                
            }else{
                request.setAttribute("errorPassword", userName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio/Login.jsp");
            dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("errorUserName", userName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Inicio/Login.jsp");
            dispatcher.forward(request, response);
            
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     */
}
