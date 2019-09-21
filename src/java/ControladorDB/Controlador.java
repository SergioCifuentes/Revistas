/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import java.sql.Connection;
import java.sql.DriverManager;
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
        }finally{
            try {
                if(getConeccion()!= null) getConeccion().close();
                if (declaracionPreparada!=null) declaracionPreparada.close();
                if(resultado2!=null) resultado2.close();
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
