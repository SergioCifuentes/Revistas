/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sergio
 */
public class Persona {
    private String userName;
    private String password;
        public Persona (HttpServletRequest request) {
        userName = request.getParameter("cui");
        password = request.getParameter("nombre");
    }
}
