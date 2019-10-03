/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ingresos;

import java.time.LocalDate;

/**
 *
 * @author sergio
 */
public class Pago {
    private float cantidad;
    private LocalDate fecha;
    private int tarjeta;

    public Pago(float cantidad, LocalDate fecha, int tarjeta) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tarjeta = tarjeta;
    }

    public float getCantidad() {
        return cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getTarjeta() {
        return tarjeta;
    }
    
}
