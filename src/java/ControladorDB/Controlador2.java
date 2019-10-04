/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import Ingresos.Pago;
import Ingresos.Suscripcion;
import Revista.Costos;
import Revista.Edicion;
import Revista.GeneradorDeCodigos;
import Revista.Revista;
import Usuarios.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class Controlador2 extends Coneccion {

    private final static String ST_CREAR_SUSCRIPCION = "INSERT INTO Suscripcion VALUES(?,?,?,?)";
    private final static String ST_CREAR_PAGO = "INSERT INTO Pago VALUES(?,?,?,?,?)";
    private final static String ST_CREAR_COSTO_REVISTA = "INSERT INTO CostosRevistas VALUES(?,?,?)";
    private final static String ST_UPDATE_ESTADO = "UPDATE Revista SET Estado = ? WHERE Codigo =?";
    private final static String ST_UPDATE_FECHA_EDICION = "UPDATE Edicion SET Fecha = ? WHERE NumeroEd =? AND CodigoRevista=?";
    private final static String ST_OBTENER_SUSCRIPCION_POR_USERNAME = "SELECT * FROM Suscripcion WHERE UserName=?";
    private final static String ST_OBTENER_SUSCRIPCION_POR_USERNAME_REVISTA = "SELECT Codigo FROM Suscripcion WHERE UserName=? AND CodigoRevista=?";
    private final static String ST_OBTENER_REVISTAS_POR_ESTADO = "SELECT Codigo FROM Revista WHERE Estado=?";
    private final static String ST_OBTENER_PRECIOS_ADMIN = "SELECT * FROM PreciosAdmin";

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
                guardarPago(codigo, suscripcion.getPagos().get(i));

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
    public String obtnerCodigoSuscripcion(String userName,String codigoRevista) {
        String suscripcion = null;
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_SUSCRIPCION_POR_USERNAME_REVISTA);
            declaracionPreparada.setString(1, userName);
            declaracionPreparada.setString(2, codigoRevista);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                suscripcion = resultado2.getString("Codigo");

            }
        } catch (NumberFormatException | SQLException e) {
        }
        return suscripcion;
    }
    public ArrayList<Suscripcion> obtnerSuscripcionPorUser(String userName) {
        ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_SUSCRIPCION_POR_USERNAME);
            declaracionPreparada.setString(1, userName);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            Controlador co = new Controlador();
            while (resultado2.next()) {
                Suscripcion su = new Suscripcion(co.obtnerPagosPorCodigo(resultado2.getString("Codigo")), resultado2.getObject("Fecha", LocalDate.class),
                        (Usuario) co.obtenerUsuario(resultado2.getString("UserName")));
                su.setRevista(co.obtnerRevistaPorCodigo(resultado2.getString("CodigoRevista")));
                suscripciones.add(su);

            }
        } catch (NumberFormatException | SQLException e) {
        }
        return suscripciones;
    }

    public ArrayList<Revista> obtnerRevistasPorEstado(int estado) {
        ArrayList<Revista> revistas = new ArrayList<Revista>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_REVISTAS_POR_ESTADO);
            declaracionPreparada.setString(1, String.valueOf(estado));
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            Controlador co = new Controlador();
            while (resultado2.next()) {
                revistas.add(co.obtnerRevistaPorCodigo(resultado2.getString("Codigo")));
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return revistas;

    }

    public void publicarRevitas(String codigoRevista, float cuota, LocalDate fecha) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            coneccion.setAutoCommit(false);
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_UPDATE_ESTADO);

            declaracionPreparada2.setString(1, String.valueOf(2));
            declaracionPreparada2.setString(2, codigoRevista);
            declaracionPreparada2.executeUpdate();
            crearCosto(codigoRevista, new Costos(cuota, fecha));
            declaracionPreparada2 = getConeccion().prepareStatement(ST_UPDATE_FECHA_EDICION);

            declaracionPreparada2.setString(1, String.valueOf(fecha));
            declaracionPreparada2.setString(2, String.valueOf(1));
            declaracionPreparada2.setString(3, codigoRevista);
            declaracionPreparada2.executeUpdate();
            coneccion.commit();
            coneccion.setAutoCommit(true);
        } catch (SQLException ex) {
            System.out.println("Error Al Guardar");
            try {
                coneccion.rollback();
            } catch (SQLException ex1) {
                System.out.println("Error RollBack");
            }
        }
    }

    public void crearCosto(String codigoRevista, Costos costo) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_COSTO_REVISTA);

            declaracionPreparada2.setString(1, String.valueOf(costo.getFecha()));
            declaracionPreparada2.setString(2, codigoRevista);
            declaracionPreparada2.setString(3, String.valueOf(costo.getCosto()));
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Costos obtenerPrecioGlobal(){
        Costos costo=null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_PRECIOS_ADMIN);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                costo=new Costos(resultado2.getFloat("CostoPorDiaGlobal"),resultado2.getObject("Fecha",LocalDate.class));
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return costo;

    }
}
