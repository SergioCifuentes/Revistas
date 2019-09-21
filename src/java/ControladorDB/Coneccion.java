/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Coneccion {
    protected  final static String USER = "root";
    protected  final static String PASSWORD = "danielito";
    protected  final static String STRING_CONNECTION = "jdbc:mysql://localhost:3306/paginaRevistas";
    private  Connection coneccion=null;
        public Coneccion() {
        try {
            if (coneccion==null) {
                 coneccion = DriverManager.getConnection(STRING_CONNECTION, USER, PASSWORD);
            }
           
        } catch (SQLException e) {
            System.out.println("Error Al conectar DB");
        }
    }

    public Connection getConeccion() {
        return coneccion;
    }
          public void setConeccion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            coneccion = DriverManager.getConnection(STRING_CONNECTION, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Coneccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
