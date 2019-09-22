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
public class Editador extends Usuario{
    
    public Editador(HttpServletRequest request) {
        super(request);
    }
    public Editador(String userName, String password) {
        super(userName, password);
    }
}
