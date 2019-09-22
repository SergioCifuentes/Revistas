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
public class Administrador extends Persona{
    
    public Administrador(HttpServletRequest request) {
        super(request);
    }
     public Administrador(String userName, String password) {
        super(userName, password);
    }
}
