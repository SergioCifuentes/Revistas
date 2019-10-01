/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import ControladorDB.Controlador;

/**
 *
 * @author sergio
 */
public class GeneradorDeCodigos {
private static final String ID_REVISTA="R";
    public static String generarCodigoRevista() {
        String codigo = "000";
        Controlador co = new Controlador();
        if (co.obtenerCodigoDeRevistas() != null) {
            codigo =String.valueOf( Integer.valueOf(codigo) + co.obtenerCodigoDeRevistas().size());
        }

//        while (Controlador.obtenerRutas(codigo) != null) {
//            codigo++;
//        }
System.out.println(ID_REVISTA+codigo);
        return ID_REVISTA+codigo;
    }
}
