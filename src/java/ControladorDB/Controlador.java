/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import Usuarios.Administrador;
import Usuarios.Editador;
import Usuarios.Persona;
import Usuarios.Suscriptor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Controlador extends Coneccion{

    private final static String ST_USERNAME = "SELECT * FROM Persona WHERE Username = ?";
    private final static String ST_OBTENER_USUARIO = "SELECT * FROM Persona WHERE UserName = ? AND AES_DECRYPT(Contrasena,'llave')= ?";
private final static String ST_CREAR_USUARIO = "INSERT INTO Persona VALUES(?,?,AES_ENCRYPT(?,'llave'))";

    public boolean verificarUserName(String userName){
        PreparedStatement declaracionPreparada=null;
        ResultSet resultado2=null;
        try {            
            if (getConeccion()==null) {
                setConeccion();
            }
                declaracionPreparada = getConeccion().prepareStatement(ST_USERNAME);
            declaracionPreparada.setString(1,userName);
             resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                  return true;
            }     
        } catch (SQLException ex) {           
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
        public void crearUsuario(String userName,String password){
        try {
            if (getConeccion()==null) {
                setConeccion();
            }
            System.out.println(getConeccion()+"sssssssss");
            PreparedStatement  declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_USUARIO);
            declaracionPreparada2.setString(1,userName);
            declaracionPreparada2.setString(2,String.valueOf(3));
            declaracionPreparada2.setString(3,password);
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public Persona obtenerUsuario(String userName,String password){
        try {            
            if (getConeccion()==null) {
                setConeccion();
                
            }
               PreparedStatement  declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_USUARIO);
            declaracionPreparada.setString(1,userName);
            declaracionPreparada.setString(2,password);
             ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                System.out.println("Tipo" +resultado2.getInt("Tipo"));
                  switch (resultado2.getInt("Tipo")) {
                    case 1:
                        return new Administrador(userName,password);                        
                    case 2:
                        return new Editador(userName,password);                       
                    case 3:
                        return new Suscriptor(userName,password);                          
                }
            }     
        } catch (SQLException ex) {           
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }        

}
