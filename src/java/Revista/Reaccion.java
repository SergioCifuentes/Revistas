/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import Usuarios.Suscriptor;
import java.time.LocalDate;

/**
 *
 * @author sergio
 */
public class Reaccion {
    private Suscriptor suscriptor;
    private boolean like;
    private String comentario;
    private LocalDate fecha;

    public Reaccion(Suscriptor suscriptor, boolean like, String comentario, LocalDate fecha) {
        this.suscriptor = suscriptor;
        this.like = like;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Suscriptor getSuscriptor() {
        return suscriptor;
    }

    public boolean isLike() {
        return like;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
}
