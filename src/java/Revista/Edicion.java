/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author sergio
 */
public class Edicion {
    private String nombre;
    private int numeroEd;
    private InputStream archivo;
    private Revista revista;
    private LocalDate fecha;
    private byte[] archivopdf;

    public Edicion(String nombre, int numeroEd, InputStream archivo, Revista revista, LocalDate fecha) {
        this.nombre = nombre;
        this.numeroEd = numeroEd;
        this.archivo = archivo;
        this.revista = revista;
        this.fecha = fecha;
    }

    public byte[] getArchivopdf() {
        return archivopdf;
    }

    public void setArchivopdf(byte[] archivopdf) {
        this.archivopdf = archivopdf;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroEd() {
        return numeroEd;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public Revista getRevista() {
        return revista;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
}
