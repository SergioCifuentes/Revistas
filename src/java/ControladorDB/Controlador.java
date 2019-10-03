/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import Ingresos.Pago;
import Ingresos.Suscripcion;
import Revista.Categoria;
import Revista.Edicion;
import Revista.Reaccion;
import Revista.Revista;
import Usuarios.Administrador;
import Usuarios.Editador;
import Usuarios.Perfil;
import Usuarios.Persona;
import Usuarios.Suscriptor;
import Usuarios.Usuario;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sergio
 */
public class Controlador extends Coneccion {

    private final static String ST_USERNAME = "SELECT * FROM Persona WHERE Username = ?";
    private final static String ST_PERFIL = "SELECT * FROM Perfil WHERE Username = ?";
    private final static String ST_OBTENER_USUARIO = "SELECT * FROM Persona WHERE UserName = ? AND AES_DECRYPT(Contrasena,'llave')= ?";
    private final static String ST_OBTENER_USUARIO2 = "SELECT * FROM Persona WHERE UserName = ?";
    private final static String ST_CREAR_USUARIO = "INSERT INTO Persona VALUES(?,?,AES_ENCRYPT(?,'llave'))";
    private final static String ST_CREAR_PERFIL = "INSERT INTO Perfil VALUES(?,?,?,?,?,?)";
    private final static String ST_CREAR_REVISTA = "INSERT INTO Revista VALUES(?,?,?,?,?,?,?,?,?)";
    private final static String ST_CREAR_EDICION = "INSERT INTO Edicion VALUES(?,?,?,?,?)";
    private final static String ST_ACTUALIZAR_FOTO = "UPDATE Perfil SET Foto = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_DESCRIPCION = "UPDATE Perfil SET Descripcion = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_HOBBIES = "UPDATE Perfil SET Hobbies = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_TEMAS = "UPDATE Perfil SET TemasDeInteres = ? WHERE UserName=?";
    private final static String ST_ACTUALIZAR_GUSTOS = "UPDATE Perfil SET Gustos = ? WHERE UserName=?";
    private final static String ST_OBTENER_REVISTAS = "SELECT * FROM Revista";
    private final static String ST_OBTENER_SUSCRIPCION = "SELECT Codigo FROM Suscripcion";
    private final static String ST_OBTENER_PAGO = "SELECT Codigo FROM Pago";
    private final static String ST_OBTENER_REVISTAS_POR_AUTOR = "SELECT * FROM Revista WHERE UserName=?";
    private final static String ST_OBTENER_REVISTAS_POR_CODIGO = "SELECT * FROM Revista WHERE Codigo=?";
    private final static String ST_OBTENER_SUSCRIPCION_POR_CODIGO = "SELECT * FROM Suscripcion WHERE CodigoRevista=?";
    private final static String ST_OBTENER_PAGO_POR_CODIGO = "SELECT * FROM Pago WHERE CodigoSuscripcion=?";
    private final static String ST_OBTENER_SUSCRIPCIONES_POR_USER = "SELECT * FROM Suscripcion WHERE UserName=?";
    private final static String ST_OBTENER_PDF_POR_CODIGO = "SELECT Archivo FROM Edicion WHERE CodigoRevista = ? AND NumeroEd = ?";
    private final static String ST_OBTENER_EDICIONES = "SELECT * FROM Edicion WHERE CodigoRevista = ?";
    private final static String ST_OBTENER_REACCIONES = "SELECT * FROM Reaccion WHERE CodigoRevista = ?";

    public boolean verificarUserName(String userName) {
        PreparedStatement declaracionPreparada = null;
        ResultSet resultado2 = null;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            declaracionPreparada = getConeccion().prepareStatement(ST_USERNAME);
            declaracionPreparada.setString(1, userName);
            resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void crearUsuario(String userName, String password) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            System.out.println(getConeccion() + "sssssssss");
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_USUARIO);
            declaracionPreparada2.setString(1, userName);
            declaracionPreparada2.setString(2, String.valueOf(3));
            declaracionPreparada2.setString(3, password);
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearPerfil(String userName, Perfil perfil) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_PERFIL);
            declaracionPreparada2.setString(1, userName);
            if (perfil.getFotoPerfil() != null) {
                declaracionPreparada2.setBlob(2, perfil.getFotoPerfil());
            } else {
                declaracionPreparada2.setString(2, null);
            }
            declaracionPreparada2.setString(3, perfil.getDescripcion());
            declaracionPreparada2.setString(4, perfil.getTemasDeInteres());
            declaracionPreparada2.setString(5, perfil.getHobbies());
            declaracionPreparada2.setString(6, perfil.getGustos());
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Persona obtenerUsuario(String userName, String password) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_USUARIO);
            declaracionPreparada.setString(1, userName);
            declaracionPreparada.setString(2, password);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                System.out.println("Tipo" + resultado2.getInt("Tipo"));
                switch (resultado2.getInt("Tipo")) {
                    case 1:
                        return new Administrador(userName, password);
                    case 2:
                        Editador nuevo = new Editador(userName, password);
                        nuevo.setPerfil(obtnerPerfil(userName));

                        return nuevo;
                    case 3:
                        Suscriptor nuevoS = new Suscriptor(userName, password);
                        nuevoS.setPerfil(obtnerPerfil(userName));
                        return nuevoS;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Persona obtenerUsuario(String userName) {
        try {
            if (getConeccion() == null) {
                setConeccion();

            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_USUARIO2);
            declaracionPreparada.setString(1, userName);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.absolute(1)) {
                System.out.println("Tipo" + resultado2.getInt("Tipo"));
                switch (resultado2.getInt("Tipo")) {
                    case 1:
                        return new Administrador(userName, resultado2.getString("Contrasena"));
                    case 2:
                        Editador nuevo = new Editador(userName, resultado2.getString("Contrasena"));
                        nuevo.setPerfil(obtnerPerfil(userName));

                        return nuevo;
                    case 3:
                        Suscriptor nuevoS = new Suscriptor(userName, resultado2.getString("Contrasena"));
                        nuevoS.setPerfil(obtnerPerfil(userName));
                        return nuevoS;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Perfil obtnerPerfil(String userName) {
        Perfil nuevo = null;
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_PERFIL);
            declaracionPreparada.setString(1, userName);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {

                nuevo = new Perfil(null, resultado2.getString(5), resultado2.getString(4),
                        resultado2.getString(3), resultado2.getString(6));
            }
        } catch (Exception e) {
        }
        return nuevo;
    }

    public Perfil obtnerPerfil(String userName, HttpServletResponse response) {
        response.setContentType("image/*");
        Perfil nuevo = null;
        OutputStream out = null;
        BufferedInputStream buffer;
        BufferedOutputStream bufferOut;
        InputStream input = null;
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            out = response.getOutputStream();
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_PERFIL);
            declaracionPreparada.setString(1, userName);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                input = resultado2.getBinaryStream(2);
                nuevo = new Perfil(input, resultado2.getString(5), resultado2.getString(4),
                        resultado2.getString(3), resultado2.getString(6));
            }
            buffer = new BufferedInputStream(input);
            bufferOut = new BufferedOutputStream(out);
            int i = 0;
            while ((i = buffer.read()) != -1) {
                bufferOut.write(i);
            }

        } catch (Exception e) {
        }
        return nuevo;
    }

    public void actualizarFoto(String userName, InputStream foto) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_FOTO);
            declaracionPreparada2.setBlob(1, foto);
            declaracionPreparada2.setString(2, userName);
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarPerfil(String userName, Perfil perfil) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_DESCRIPCION);
            declaracionPreparada2.setString(1, perfil.getDescripcion());
            declaracionPreparada2.setString(2, userName);
            declaracionPreparada2.executeUpdate();
            declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_HOBBIES);
            declaracionPreparada2.setString(1, perfil.getHobbies());
            declaracionPreparada2.setString(2, userName);
            declaracionPreparada2.executeUpdate();
            declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_TEMAS);
            declaracionPreparada2.setString(1, perfil.getTemasDeInteres());
            declaracionPreparada2.setString(2, userName);
            declaracionPreparada2.executeUpdate();
            declaracionPreparada2 = getConeccion().prepareStatement(ST_ACTUALIZAR_GUSTOS);
            declaracionPreparada2.setString(1, perfil.getGustos());
            declaracionPreparada2.setString(2, userName);
            declaracionPreparada2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList obtenerCodigoDeRevistas() {
        ArrayList codigos = new ArrayList();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_REVISTAS);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                codigos.add(resultado2.getString("Codigo"));
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
        return codigos;
    }

    public ArrayList obtenerCodigoDePagos() {
        ArrayList codigos = new ArrayList();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_PAGO);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                codigos.add(resultado2.getString("Codigo"));
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
        return codigos;
    }

    public ArrayList obtenerCodigoDeSuscripcion() {
        ArrayList codigos = new ArrayList();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_SUSCRIPCION);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                codigos.add(resultado2.getString("Codigo"));
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
        return codigos;
    }

    public void publicarNuevaRevista(Revista revista, Edicion edicion) {
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_REVISTA);
            declaracionPreparada2.setString(1, revista.getCodigo());
            declaracionPreparada2.setString(2, String.valueOf(revista.getCostoPorSuscripcion()));
            declaracionPreparada2.setString(3, revista.getAutor().getUserName());
            if (revista.getCategoria() == null) {
                declaracionPreparada2.setString(4, null);
            } else {
                declaracionPreparada2.setString(4, revista.getCategoria().getNombre());
            }
            declaracionPreparada2.setString(5, String.valueOf(1));
            declaracionPreparada2.setString(6, revista.getNombre());
            declaracionPreparada2.setString(7, String.valueOf(0));
            declaracionPreparada2.setString(8, String.valueOf(0));
            declaracionPreparada2.setString(9, revista.getDescripcion());
            declaracionPreparada2.executeUpdate();
            //Edicion
            declaracionPreparada2 = getConeccion().prepareStatement(ST_CREAR_EDICION);

            declaracionPreparada2.setString(1, String.valueOf(1));
            declaracionPreparada2.setString(2, revista.getCodigo());
            declaracionPreparada2.setBlob(3, edicion.getArchivo());
            declaracionPreparada2.setString(4, String.valueOf(edicion.getFecha()));
            declaracionPreparada2.setString(5, edicion.getNombre());
            declaracionPreparada2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Revista> obtnerRevistas() {
        ArrayList<Revista> revistas = new ArrayList<Revista>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_REVISTAS);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                revistas.add(obtnerRevistaPorCodigo(resultado2.getString("Codigo")));
            }

        } catch (Exception e) {
        }
        return revistas;
    }

    public ArrayList<Revista> obtnerRevistasPorAutor(String autor) {
        ArrayList<Revista> revistas = new ArrayList<Revista>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_REVISTAS_POR_AUTOR);
            declaracionPreparada.setString(1, autor);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                revistas.add(obtnerRevistaPorCodigo(resultado2.getString("Codigo")));
            }

        } catch (Exception e) {
        }
        return revistas;
    }

    public ArrayList<Edicion> obtnerEdiciones(Revista revista) {
        ArrayList<Edicion> ediciones = new ArrayList<>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_EDICIONES);
            declaracionPreparada.setString(1, revista.getCodigo());
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                Edicion ed = new Edicion(resultado2.getString("Nombre"), resultado2.getInt("NumeroEd"),
                        resultado2.getBinaryStream("Archivo"), revista, resultado2.getObject("Fecha", LocalDateTime.class));
                ed.setArchivopdf(resultado2.getBytes("Archivo"));
                ediciones.add(ed);
            }

        } catch (Exception e) {
        }
        return ediciones;
    }

    public ArrayList<Reaccion> obtnerReacciones(String codigoRevista) {
        ArrayList<Reaccion> reaccions = new ArrayList<>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_REACCIONES);
            declaracionPreparada.setString(1, codigoRevista);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                reaccions.add(new Reaccion((Suscriptor) obtenerUsuario(resultado2.getString("UserName")),
                        resultado2.getBoolean("Likes"), resultado2.getString("Comentario"), resultado2.getObject("Fecha", LocalDate.class)));
            }

        } catch (Exception e) {
        }
        return reaccions;
    }

    public void pdf(String codigoRevista, String numeroEd, HttpServletResponse response) {
        byte[] b = null;
        try {
            System.out.println("codigo: " + codigoRevista);
            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_PDF_POR_CODIGO);
            declaracionPreparada.setString(1, codigoRevista);
            declaracionPreparada.setString(2, numeroEd);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                b = resultado2.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);
            int sizeInput = bos.available();
            byte[] datosPdf = new byte[sizeInput];
            bos.read(datosPdf, 0, sizeInput);
            response.getOutputStream().write(datosPdf);
            bos.close();

        } catch (Exception e) {
        }
    }

    public ArrayList<Revista> obtnerRevistasDeSuscriptor(String suscriptor) {
        ArrayList<Revista> revistas = new ArrayList<Revista>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_SUSCRIPCIONES_POR_USER);
            declaracionPreparada.setString(1, suscriptor);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                revistas.add(obtnerRevistaPorCodigo(resultado2.getString("CodigoRevista")));
            }

        } catch (Exception e) {
        }
        return revistas;
    }

    public Revista obtnerRevistaPorCodigo(String codigo) {
        Revista revistas = null;
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_REVISTAS_POR_CODIGO);
            declaracionPreparada.setString(1, codigo);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                Revista nuevaRevista = (new Revista(codigo, resultado2.getString("Nombre"), resultado2.getFloat("CostoPorSuscripcion"),
                        new Categoria(resultado2.getString("NombreCategoria")), (Editador) obtenerUsuario(resultado2.getString("UserName")),
                        resultado2.getInt("Estado"), resultado2.getBoolean("Comentarios"), resultado2.getBoolean("Suscripciones"), resultado2.getString("Descripcion")));
                nuevaRevista.setEdiciones(obtnerEdiciones(nuevaRevista));
                nuevaRevista.setReaccions(obtnerReacciones(codigo));
                nuevaRevista.setSuscripcions(obtnerSuscripcionPorCodigo(codigo));
                revistas = nuevaRevista;
            }

        } catch (Exception e) {
        }
        return revistas;
    }

    public ArrayList<Suscripcion> obtnerSuscripcionPorCodigo(String codigoRevista) {
        ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_SUSCRIPCION_POR_CODIGO);
            declaracionPreparada.setString(1, codigoRevista);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                suscripciones.add(new Suscripcion(obtnerPagosPorCodigo(resultado2.getString("Codigo")), resultado2.getObject("Fecha", LocalDate.class),
                         (Usuario) obtenerUsuario(resultado2.getString("UserName"))));
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return suscripciones;
    }

    public ArrayList<Pago> obtnerPagosPorCodigo(String codigoSuscripcion) {
        ArrayList<Pago> pagos = new ArrayList<Pago>();
        try {

            if (getConeccion() == null) {
                setConeccion();
            }
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_OBTENER_PAGO_POR_CODIGO);
            declaracionPreparada.setString(1, codigoSuscripcion);
            ResultSet resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                pagos.add(new Pago(Float.valueOf(resultado2.getString("Cantidad")), resultado2.getObject("Fecha", LocalDate.class), Integer.parseInt(resultado2.getString("Tarjeta"))));
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return pagos;
    }

}
