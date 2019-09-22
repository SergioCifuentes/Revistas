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
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String userName = request.getParameter("userName");

        String password = request.getParameter("pass");
        Controlador control = new Controlador();
        if (control.verificarUserName(userName)) {
            Persona usuario = control.obtenerUsuario(userName, password);
            if (usuario!=null) {
                request.setAttribute("Usuario",usuario);
                try {
                    Administrador admin = (Administrador)usuario;
                    getServletContext().getRequestDispatcher("/Administracion/HomeAdmin.jsp").forward(request, response);
                } catch (Exception e) {
                    try {
                        Editador editador= (Editador)usuario;
                        getServletContext().getRequestDispatcher("/AreaEditor/HomeEditador.jsp").forward(request, response);
                    } catch (IOException | ServletException ex) {
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
    public static void main(String[] args) {
        Controlador co = new Controlador();
        co.crearUsuario("sergio","pass");
        
    }
    /**
     * Returns a short description of the servlet.
     *
     */
}
