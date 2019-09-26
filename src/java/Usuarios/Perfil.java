/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuarios;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sergio
 */
public class Perfil {
    
    private InputStream fotoPerfil;
    private InputStream fotoPredeterminado;
    private String hobbies;
    private String temasDeInteres;
    private String descripcion;
    private String gustos;
public Perfil (HttpServletRequest request) throws FileNotFoundException {
        this.fotoPredeterminado = new FileInputStream("/home/sergio/IPC2Proyectos/Revistas/Imagenes/abstract-user-flat-4.png");
        fotoPerfil=fotoPredeterminado;
        hobbies = request.getParameter("Hobbies");
        temasDeInteres = request.getParameter("Temas");
        descripcion = request.getParameter("Descripcion");
        gustos = request.getParameter("Gustos");
    }
public void AcualizarInfo(HttpServletRequest request) {
        hobbies = request.getParameter("Hobbies");
        temasDeInteres = request.getParameter("Temas");
        descripcion = request.getParameter("Descripcion");
        gustos = request.getParameter("Gustos");
    }
    public void setFotoPerfil(InputStream fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public InputStream getFotoPerfil() {
        return fotoPerfil;
    }


    public InputStream getFotoPredeterminado() {
        return fotoPredeterminado;
    }

    public void setFotoPredeterminado(InputStream fotoPredeterminado) {
        this.fotoPredeterminado = fotoPredeterminado;
    }


    public String getHobbies() {
        return hobbies;
    }

    public String getTemasDeInteres() {
        return temasDeInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGustos() {
        return gustos;
    }

    public Perfil(InputStream fotoPerfil, String hobbies, String temasDeInteres, String descripcion, String gustos) {
        this.fotoPerfil = fotoPerfil;
        this.hobbies = hobbies;
        this.temasDeInteres = temasDeInteres;
        this.descripcion = descripcion;
        this.gustos = gustos;
    }
    
}
