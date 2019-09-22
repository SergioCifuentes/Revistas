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
public class Suscriptor extends Usuario{
    
    public Suscriptor(HttpServletRequest request) {
        super(request);
    }
    public Suscriptor(String userName, String password) {
        super(userName, password);
    }
}
