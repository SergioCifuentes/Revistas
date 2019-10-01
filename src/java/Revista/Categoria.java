/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Categoria {
    private String nombre;
    private ArrayList<Etiqueta> etiquetas;

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }
    
}
