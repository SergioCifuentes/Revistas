/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import java.time.LocalDate;

/**
 *
 * @author sergio
 */
public class Comentario {
    private String userName;
    private String comentario;
    private LocalDate fecha;

    public String getUserName() {
        return userName;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Comentario(String userName, String comentario, LocalDate fecha) {
        this.userName = userName;
        this.comentario = comentario;
        this.fecha = fecha;
    }
    private String codigoRevista;
    private String nombreRevista;

    public String getCodigoRevista() {
        return codigoRevista;
    }

    public void setCodigoRevista(String codigoRevista) {
        this.codigoRevista = codigoRevista;
    }

    public String getNombreRevista() {
        return nombreRevista;
    }

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }
    
}
