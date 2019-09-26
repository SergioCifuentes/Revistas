/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import Usuarios.Administrador;
import Usuarios.Editador;
import Usuarios.Perfil;
import Usuarios.Persona;
import Usuarios.Suscriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class Controlador extends Coneccion {

    private final static String ST_USERNAME = "SELECT * FROM Persona WHERE Username = ?";
    private final static String ST_PERFIL = "SELECT * FROM Perfil WHERE Username = ?";
    private final static String ST_OBTENER_USUARIO = "SELECT * FROM Persona WHERE UserName = ? AND AES_DECRYPT(Contrasena,'llave')= ?";
    private final static String ST_CREAR_USUARIO = "INSERT INTO Persona VALUES(?,?,AES_ENCRYPT(?,'llave'))";
    private final static String ST_CREAR_PERFIL = "INSERT INTO Perfil VALUES(?,?,?,?,?,?)";
    private final static String ST_ACTUALIZAR_FOTO = "UPDATE Perfil SET Foto = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_DESCRIPCION = "UPDATE Perfil SET Descripcion = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_HOBBIES = "UPDATE Perfil SET Hobbies = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_TEMAS = "UPDATE Perfil SET TemasDeInteres = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_GUSTOS = "UPDATE Perfil SET Gustos = ? WHERE UserName=?";

    public boolean verificarUserName(String userName) {
        PreparedStatement declaracionPreparada = null;
        ResultSet resultado2 = null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            declaracionPreparada = getConeccion().prepareStatement(ST_USERNAME);
            declaracionPreparada.setString(1, userName);
            resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void crearUsuario(String userName, String password) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            System.out.println(getConeccion() + "sssssssss");
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_USUARIO);
            declaracionPreparada2.setString(1, userName);
            declaracionPreparada2.setString(2, String.valueOf(3));
            declaracionPreparada2.setString(3, password);
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearPerfil(String userName,Perfil perfil) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_PERFIL);
            declaracionPreparada2.setString(1, userName);
            if (perfil.getFotoPerfil()!=null) {
                declaracionPreparada2.setBlob(2,perfil.getFotoPerfil());
            }else{
                declaracionPreparada2.setString(2, null);
            }
            declaracionPreparada2.setString(3, perfil.getDescripcion());
            declaracionPreparada2.setString(4, perfil.getTemasDeInteres());
            declaracionPreparada2.setString(5, perfil.getHobbies());
            declaracionPreparada2.setString(6, perfil.getGustos());
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Persona obtenerUsuario(String userName, String password) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_USUARIO);
            declaracionPreparada.setString(1, userName);
            declaracionPreparada.setString(2, password);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                System.out.println("Tipo" + resultado2.getInt("Tipo"));
                switch (resultado2.getInt("Tipo")) {
                    case 1:
                        return new Administrador(userName, password);
                    case 2:
                        Editador nuevo = new Editador(userName, password);
                        nuevo.setPerfil(obtnerPerfil(userName));
                        
                        return nuevo ;
                    case 3:
                        Suscriptor nuevoS = new  Suscriptor(userName, password);
                        nuevoS.setPerfil(obtnerPerfil(userName));
                        return nuevoS;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Perfil obtnerPerfil(String userName){
        Perfil nuevo=null;
        try {
           
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_PERFIL);
            declaracionPreparada.setString(1, userName);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while(resultado2.next()) {
                
                nuevo= new Perfil(null,resultado2.getString(5),resultado2.getString(4),
                        resultado2.getString(3),resultado2.getString(6));
            }            
        } catch (Exception e) {
        }
        return nuevo;
    }
    public Perfil obtnerPerfil(String userName,HttpServletResponse response){
        response.setContentType("image/*");
        Perfil nuevo=null;
        OutputStream out=null;
        BufferedInputStream buffer;
        BufferedOutputStream bufferOut;
        InputStream input=null;
        try {
           
            if (getConeccion() == null) {
                setConeccion();
            }
            out= response.getOutputStream();
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_PERFIL);
            declaracionPreparada.setString(1, userName);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while(resultado2.next()) {
                input=resultado2.getBinaryStream(2);
                nuevo= new Perfil(input,resultado2.getString(5),resultado2.getString(4),
                        resultado2.getString(3),resultado2.getString(6));
            }
            buffer= new BufferedInputStream(input);
            bufferOut= new BufferedOutputStream(out);
            int i=0;
            while ((i= buffer.read())!=-1) {                
                bufferOut.write(i);
            }
            
        } catch (Exception e) {
        }
        return nuevo;
    }
public void actualizarFoto(String userName,InputStream foto) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_FOTO);
            declaracionPreparada2.setBlob(1, foto);
            declaracionPreparada2.setString(2, userName);            
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void actualizarPerfil(String userName,Perfil perfil) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_DESCRIPCION);
            declaracionPreparada2.setString(1, perfil.getDescripcion());
            declaracionPreparada2.setString(2, userName);            
            declaracionPreparada2.executeUpdate();
            declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_HOBBIES);
            declaracionPreparada2.setString(1, perfil.getHobbies());
            declaracionPreparada2.setString(2, userName);            
            declaracionPreparada2.executeUpdate();
            declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_TEMAS);
            declaracionPreparada2.setString(1, perfil.getTemasDeInteres());
            declaracionPreparada2.setString(2, userName);            
            declaracionPreparada2.executeUpdate();
            declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_GUSTOS);
            declaracionPreparada2.setString(1, perfil.getGustos());
            declaracionPreparada2.setString(2, userName);            
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
