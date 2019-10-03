/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import Ingresos.Pago;
import Ingresos.Suscripcion;
import Revista.Edicion;
import Revista.GeneradorDeCodigos;
import Revista.Revista;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Controlador2 extends Coneccion {

    private final static String ST_CREAR_SUSCRIPCION = "INSERT INTO Suscripcion VALUES(?,?,?,?)";
    private final static String ST_CREAR_PAGO = "INSERT INTO Pago VALUES(?,?,?,?,?)";

    public void guardarSuscripcion(Suscripcion suscripcion, String codigoRevista) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_SUSCRIPCION);
            String codigo = GeneradorDeCodigos.generarCodigoSuscripcion();
            declaracionPreparada2.setString(1, codigo);
            declaracionPreparada2.setString(2, codigoRevista);
            declaracionPreparada2.setString(3, suscripcion.getUsuario().getUserName());
            declaracionPreparada2.setString(4, String.valueOf(suscripcion.getFecha()));
            declaracionPreparada2.executeUpdate();
            for (int i = 0; i < suscripcion.getPagos().size(); i++) {
                guardarPago(codigo,suscripcion.getPagos().get(i));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPago(String codigoSuscripcion, Pago pago) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_PAGO);

            declaracionPreparada2.setString(1, GeneradorDeCodigos.generarCodigoPago());
            declaracionPreparada2.setString(2, String.valueOf(pago.getFecha()));
            declaracionPreparada2.setString(3, codigoSuscripcion);
            declaracionPreparada2.setString(4, String.valueOf(pago.getTarjeta()));
            declaracionPreparada2.setString(5, String.valueOf(pago.getCantidad()));
            declaracionPreparada2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
