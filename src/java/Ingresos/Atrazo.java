/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ingresos;

import Revista.Revista;
import java.time.LocalDate;

/**
 *
 * @author sergio
 */
public class Atrazo {
    private Revista revista;
    private LocalDate ultimoDiaPagado;
    private int diasAtrazado;
    private int diasFaltantes;

    public Atrazo(Revista revista, LocalDate ultimoDiaPagado, int diasAtrazado, int diasFaltantes) {
        this.revista = revista;
        this.ultimoDiaPagado = ultimoDiaPagado;
        this.diasAtrazado = diasAtrazado;
        this.diasFaltantes = diasFaltantes;
    }

    
    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public LocalDate getUltimoDiaPagado() {
        return ultimoDiaPagado;
    }

    public void setUltimoDiaPagado(LocalDate ultimoDiaPagado) {
        this.ultimoDiaPagado = ultimoDiaPagado;
    }

    public int getDiasAtrazado() {
        return diasAtrazado;
    }

    public void setDiasAtrazado(int diasAtrazado) {
        this.diasAtrazado = diasAtrazado;
    }

    public int getDiasFaltantes() {
        return diasFaltantes;
    }

    public void setDiasFaltantes(int diasFaltantes) {
        this.diasFaltantes = diasFaltantes;
    }
    
    
}
