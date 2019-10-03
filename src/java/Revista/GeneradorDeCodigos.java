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

    private static final String ID_REVISTA = "R";
    private static final String ID_SUSCRIPCION = "S";
    private static final String ID_PAGO = "R";

    public static String generarCodigoRevista() {
        String codigo = "000";
        Controlador co = new Controlador();
        if (co.obtenerCodigoDeRevistas() != null) {
            codigo = String.valueOf(Integer.valueOf(codigo) + co.obtenerCodigoDeRevistas().size());
        }

        while (co.obtnerRevistaPorCodigo(codigo) != null) {
            codigo = String.valueOf(Integer.valueOf(codigo) + 1);
        }

        return ID_REVISTA + codigo;
    }

    public static String generarCodigoSuscripcion() {
        String codigo = "000";
        Controlador co = new Controlador();
        if (co.obtenerCodigoDeSuscripcion()!= null) {
            codigo = String.valueOf(Integer.valueOf(codigo) + co.obtenerCodigoDeSuscripcion().size());
        }


        return ID_SUSCRIPCION + codigo;
    }

    public static String generarCodigoPago() {
        String codigo = "000";
        Controlador co = new Controlador();
        if (co.obtenerCodigoDePagos() != null) {
            codigo = String.valueOf(Integer.valueOf(codigo) + co.obtenerCodigoDePagos().size());
        }
//
//        while (co.obtnerRevistaPorCodigo(codigo) != null) {
//            codigo = String.valueOf(Integer.valueOf(codigo) + 1);
//        }

        return ID_PAGO + codigo;
    }
}
