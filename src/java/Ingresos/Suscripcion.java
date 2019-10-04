/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ingresos;

import Revista.Revista;
import Usuarios.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Suscripcion {
    private ArrayList<Pago> pagos;
    private LocalDate fecha;
    private Usuario usuario;
    private Revista revista;
    public Suscripcion(ArrayList<Pago> pagos, LocalDate fecha, Usuario usuario) {
        this.pagos = pagos;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
}
