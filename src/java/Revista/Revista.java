/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import Usuarios.Editador;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Revista {
    private String codigo;
    private String nombre;
    
    private float costoPorSuscripcion;
    private Categoria categoria;
    private Editador autor;
    private int  estado;
    private boolean  comentarioBloqueados;
    private boolean  suscripcionesBloqueados;
    private ArrayList<Etiqueta> etiquetas;
    private ArrayList<Edicion> ediciones;
    private ArrayList<Costos> costos;

    public Revista(String codigo, String nombre, float costoPorSuscripcion, Categoria categoria, Editador autor, int estado, boolean comentarioBloqueados, boolean suscripcionesBloqueados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costoPorSuscripcion = costoPorSuscripcion;
        this.categoria = categoria;
        this.autor = autor;
        this.estado = estado;
        this.comentarioBloqueados = comentarioBloqueados;
        this.suscripcionesBloqueados = suscripcionesBloqueados;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEstado() {
        return estado;
    }

    public boolean isComentarioBloqueados() {
        return comentarioBloqueados;
    }

    public boolean isSuscripcionesBloqueados() {
        return suscripcionesBloqueados;
    }

    

    public String getCodigo() {
        return codigo;
    }

    public float getCostoPorSuscripcion() {
        return costoPorSuscripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Editador getAutor() {
        return autor;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public ArrayList<Edicion> getEdiciones() {
        return ediciones;
    }

    public ArrayList<Costos> getCostos() {
        return costos;
    }
    
    
    
    
}
