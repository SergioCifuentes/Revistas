/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import Ingresos.Suscripcion;
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
    private String descripcion;
    private boolean  comentarioBloqueados;
    private boolean  suscripcionesBloqueados;
    private ArrayList<Etiqueta> etiquetas;
    private ArrayList<Edicion> ediciones;
    private ArrayList<Costos> costos;
    private ArrayList<Reaccion> reaccions;
    private ArrayList<Suscripcion> suscripcions;

    public ArrayList<Suscripcion> getSuscripcions() {
        return suscripcions;
    }

    public void setSuscripcions(ArrayList<Suscripcion> suscripcions) {
        this.suscripcions = suscripcions;
    }
    public void addSuscripcions(Suscripcion suscripcions) {
        this.suscripcions.add(suscripcions);
    }
    public Revista(String codigo, String nombre, float costoPorSuscripcion, Categoria categoria, Editador autor, int estado, boolean comentarioBloqueados, boolean suscripcionesBloqueados,String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costoPorSuscripcion = costoPorSuscripcion;
        this.categoria = categoria;
        this.autor = autor;
        this.estado = estado;
        this.comentarioBloqueados = comentarioBloqueados;
        this.suscripcionesBloqueados = suscripcionesBloqueados;
        this.descripcion=descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEstado() {
        return estado;
    }

    public ArrayList<Reaccion> getReaccions() {
        return reaccions;
    }

    public void setReaccions(ArrayList<Reaccion> reaccions) {
        this.reaccions = reaccions;
    }

    public boolean isComentarioBloqueados() {
        return comentarioBloqueados;
    }

    public boolean isSuscripcionesBloqueados() {
        return suscripcionesBloqueados;
    }

    public void setEdiciones(ArrayList<Edicion> ediciones) {
        this.ediciones = ediciones;
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
    
    public ArrayList<Comentario> getComentario(){
        
        ArrayList<Comentario> comentarios = new ArrayList<>();
        for (int i = 0; i < reaccions.size(); i++) {
            if (reaccions.get(i).getComentario()!=null&&!"".equals(reaccions.get(i).getComentario())) {
                comentarios.add(new Comentario(reaccions.get(i).getSuscriptor().getUserName(),reaccions.get(i).getComentario()
                        , reaccions.get(i).getFecha()));
            }
            
        }
        return comentarios;
    }
        public int getLikes(){
        
       int likes =0;
        for (int i = 0; i < reaccions.size(); i++) {
            if (reaccions.get(i).isLike()) {
                likes++;
            }
            
        }
        return likes;
    }
    
}
