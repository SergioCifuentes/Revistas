/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import Ingresos.Suscripcion;
import Revista.Comentario;
import Revista.Reaccion;
import Revista.Revista;
import Usuarios.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class ControlReportes extends Coneccion {

    private final static String ST_SUSCRIPCION = " SELECT CodigoRevista,Nombre,s.UserName,s.Codigo AS CodigoSuscripcion,s.Fecha "
            + "FROM Suscripcion s JOIN Revista r ON s.CodigoRevista=r.Codigo ";
    private final static String ST_PORCENTAJE_SISTEMA = "SELECT PorcentajeSistema FROM PreciosAdmin";
    private final static String ST_COMENTARIO = " SELECT CodigoRevista,Nombre,Comentario,s.UserName  From Reaccion s Join Revista r On s.CodigoRevista=r.Codigo ";
    private final static String ST_LIKES = " SELECT CodigoRevista,Nombre,Likes,s.UserName  From Reaccion s Join Revista r On s.CodigoRevista=r.Codigo ";
    private final static String ST_COMENTARIOS_EDITOR = " r.UserName=? ";

    private final static String ST_PAGOS = " SELECT CodigoRevista,Nombre,Comentario,s.UserName  From Reaccion s Join Revista r On s.CodigoRevista=r.Codigo ";
    private final static String ST_NOT_NULL = " Comentario != 'Null'";
    private final static String ST_REVISTA_EDITOR = " CodigoRevista= ? ";
    private final static String ST_WHERE = " WHERE ";
    private final static String ST_FECHA_INICIO = " s.Fecha> ? ";
    private final static String ST_AND = " AND ";
    private final static String ST_FECHA_FINAL = " s.Fecha< ? ";

    public ArrayList<Revista> obtenerMasLikes(LocalDate inicio, LocalDate fin, String userName , String codigoRevista) {
        ArrayList<Comentario> likes = new ArrayList<>();
        ArrayList<Revista> revistas = new ArrayList<>();
        String re = "";
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            if (codigoRevista != null) {
            re = ST_AND + ST_REVISTA_EDITOR;
            re = re.replace("?", "'" + codigoRevista + "'");
        }
            ResultSet resultado2 = null;
            if (inicio == null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_LIKES + ST_WHERE + ST_COMENTARIOS_EDITOR + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio != null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_LIKES + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(inicio));
                declaracionPreparada.setString(3, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }

            if (inicio != null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_LIKES + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_INICIO + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(inicio));
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio == null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_LIKES + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_FINAL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }
            Controlador co = new Controlador();
            while (resultado2.next()) {
                boolean aux = true;
                if (resultado2.getBoolean("Likes")) {
                    for (int i = 0; i < revistas.size(); i++) {
                        if (revistas.get(i).getCodigo().equals(resultado2.getString("CodigoRevista"))) {
                            revistas.get(i).addLike(resultado2.getString("UserName"));
                            aux = false;
                        }
                    }
                    if (aux) {
                        Revista rev = new Revista(resultado2.getString("CodigoRevista"),resultado2.getString("Nombre"));
                        rev.addLike(resultado2.getString("UserName"));
                        revistas.add(rev);
                    }
                }
            }

        } catch (NumberFormatException | SQLException e) {
        }
        return ordenar(revistas, 3);
    }

    public ArrayList<String[]> obtenerComentarios(LocalDate inicio, LocalDate fin, String userName, String codigoRevista) {
        ArrayList<String[]> comentarios = new ArrayList<>();
        String re = "";
        if (codigoRevista != null) {
            re = ST_AND + ST_REVISTA_EDITOR;
            re = re.replace("?", "'" + codigoRevista + "'");
        }
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            PreparedStatement declaracionPreparada = null;
            if (inicio == null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_NOT_NULL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
            }
            if (inicio != null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL + ST_AND + ST_NOT_NULL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(inicio));
                declaracionPreparada.setString(3, String.valueOf(fin));
            }

            if (inicio != null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_INICIO + ST_AND + ST_NOT_NULL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(inicio));
            }
            if (inicio == null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_FINAL + ST_AND + ST_NOT_NULL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(fin));
                

            }
            resultado2 = declaracionPreparada.executeQuery();
            Controlador co = new Controlador();
            while (resultado2.next()) {
                String[] s = new String[4];
                s[0] = resultado2.getString("CodigoRevista");
                s[1] = resultado2.getString("Nombre");
                s[2] = resultado2.getString("Comentario");
                s[3] = resultado2.getString("UserName");
                comentarios.add(s);
            }

        } catch (NumberFormatException | SQLException e) {
        }
        return comentarios;
    }

    public ArrayList<String[]> obtenerSuscripcion(LocalDate inicio, LocalDate fin, String userName, String codigoRevista) {
        ArrayList<String[]> sucripciones = new ArrayList<>();
        String re = "";
        if (codigoRevista != null) {
            re = ST_AND + ST_REVISTA_EDITOR;
            re = re.replace("?", "'" + codigoRevista + "'");
        }
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            PreparedStatement declaracionPreparada = null;
            if (inicio == null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_COMENTARIOS_EDITOR + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
            }
            if (inicio != null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(inicio));
                declaracionPreparada.setString(3, String.valueOf(fin));
            }

            if (inicio != null && fin == null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_INICIO + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(inicio));
            }
            if (inicio == null && fin != null) {
                declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_COMENTARIOS_EDITOR + ST_AND + ST_FECHA_FINAL + re);
                declaracionPreparada.setString(1, String.valueOf(userName));
                declaracionPreparada.setString(2, String.valueOf(fin));

            }
            resultado2 = declaracionPreparada.executeQuery();
            while (resultado2.next()) {
                String[] s = new String[4];
                s[0] = resultado2.getString("CodigoRevista");
                s[1] = resultado2.getString("Nombre");
                s[2] = resultado2.getString("UserName");
                sucripciones.add(s);
            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Error al Obtener Suscripciones");
        }
        return sucripciones;
    }

    public ArrayList<Revista> obtenerGanancias(LocalDate inicio, LocalDate fin, String CodigoRevista) {
        ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
        ArrayList<Revista> revistas = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            if (inicio == null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION);
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio != null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                declaracionPreparada.setString(2, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }

            if (inicio != null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_FECHA_INICIO);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio == null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }
            Controlador co = new Controlador();
            while (resultado2.next()) {
                Suscripcion su = new Suscripcion(co.obtnerPagosPorCodigo(resultado2.getString("CodigoSuscripcion")), resultado2.getObject("Fecha", LocalDate.class),
                        (Usuario) co.obtenerUsuario(resultado2.getString("UserName")));
                su.setCodigoRevista(resultado2.getString("CodigoRevista"));
                su.setNombreRevista(resultado2.getString("Nombre"));
                suscripciones.add(su);
            }
            for (int j = 0; j < suscripciones.size(); j++) {
                boolean aux = true;
                for (int k = 0; k < revistas.size(); k++) {
                    if (revistas.get(k).getCodigo().equals(suscripciones.get(j).getCodigoRevista())) {
                        revistas.get(k).addSuscripcions(suscripciones.get(j));
                        aux = false;
                        break;
                    }

                }
                if (aux) {
                    Revista rev = new Revista(suscripciones.get(j).getCodigoRevista(), suscripciones.get(j).getNombreRevista());
                    rev.addSuscripcion(suscripciones.get(j));
                    revistas.add(rev);
                }

            }

        } catch (NumberFormatException | SQLException e) {
        }
        return revistas;
    }

    public ArrayList<Revista> obtenerMasSuscritos(LocalDate inicio, LocalDate fin) {
        ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
        ArrayList<Revista> revistas = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            if (inicio == null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION);
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio != null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                declaracionPreparada.setString(2, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }

            if (inicio != null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_FECHA_INICIO);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio == null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_SUSCRIPCION + ST_WHERE + ST_FECHA_FINAL);
                declaracionPreparada.setString(1, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }
            Controlador co = new Controlador();
            while (resultado2.next()) {
                Suscripcion su = new Suscripcion(co.obtnerPagosPorCodigo(resultado2.getString("CodigoSuscripcion")), resultado2.getObject("Fecha", LocalDate.class),
                        (Usuario) co.obtenerUsuario(resultado2.getString("UserName")));
                su.setCodigoRevista(resultado2.getString("CodigoRevista"));
                su.setNombreRevista(resultado2.getString("Nombre"));
                suscripciones.add(su);
            }
            for (int j = 0; j < suscripciones.size(); j++) {
                boolean aux = true;
                for (int k = 0; k < revistas.size(); k++) {
                    if (revistas.get(k).getCodigo().equals(suscripciones.get(j).getCodigoRevista())) {
                        revistas.get(k).addSuscripcions(suscripciones.get(j));
                        aux = false;
                        break;
                    }

                }
                if (aux) {
                    Revista rev = new Revista(suscripciones.get(j).getCodigoRevista(), suscripciones.get(j).getNombreRevista());
                    rev.addSuscripcion(suscripciones.get(j));
                    revistas.add(rev);
                }

            }

        } catch (NumberFormatException | SQLException e) {
        }

        return ordenar(revistas, 1);
    }

    public ArrayList<Revista> obtenerMasComentadas(LocalDate inicio, LocalDate fin) {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        ArrayList<Revista> revistas = new ArrayList<>();
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            if (inicio == null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_NOT_NULL);
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio != null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL + ST_AND + ST_NOT_NULL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                declaracionPreparada.setString(2, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }

            if (inicio != null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_FECHA_INICIO + ST_AND + ST_NOT_NULL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio == null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_FECHA_FINAL + ST_AND + ST_NOT_NULL);
                declaracionPreparada.setString(1, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }
            Controlador co = new Controlador();
            while (resultado2.next()) {
                Comentario com = new Comentario(co.obtenerUsuario(resultado2.getString("UserName")).getUserName(), resultado2.getString("Comentario"),
                        null);
                com.setCodigoRevista(resultado2.getString("CodigoRevista"));
                com.setNombreRevista(resultado2.getString("Nombre"));
                comentarios.add(com);
            }
            for (int j = 0; j < comentarios.size(); j++) {
                boolean aux = true;
                for (int k = 0; k < revistas.size(); k++) {
                    if (revistas.get(k).getCodigo().equals(comentarios.get(j).getCodigoRevista())) {
                        revistas.get(k).addComentario(comentarios.get(j));
                        aux = false;
                        break;
                    }

                }
                if (aux) {
                    Revista rev = new Revista(comentarios.get(j).getCodigoRevista(), comentarios.get(j).getNombreRevista());
                    rev.addComentario(comentarios.get(j));
                    revistas.add(rev);
                }
            }

        } catch (NumberFormatException | SQLException e) {
        }
        return ordenar(revistas, 2);
    }

    private ArrayList<Revista> ordenar(ArrayList<Revista> revistasOg, int tipo) {
        ArrayList<Revista> revOrdenadas = new ArrayList<Revista>();
        if (revistasOg.size() > 1) {
            Revista menor;
            Revista aux;
            int pos;
            for (int i = 0; i < revistasOg.size() - 1; i++) { // tomamos como menor el primero
                menor = revistasOg.get(i); // de los elementos que quedan por ordenar
                pos = i; // y guardamos su posición
                for (int j = i + 1; j < revistasOg.size(); j++) { // buscamos en el resto
                    switch (tipo) {
                        case 1:
                            if (revistasOg.get(j).getSuscripcions().size() > menor.getSuscripcions().size()) { // del array algún elemento
                                menor = revistasOg.get(j); // menor que el actual
                                pos = j;
                            }   break;
                        case 2:
                            if (revistasOg.get(j).getComentarios().size() > menor.getComentarios().size()) { // del array algún elemento
                                menor = revistasOg.get(j); // menor que el actual
                                pos = j;
                            }   break;
                        default:
                            if (revistasOg.get(j).getLike().size() > menor.getLike().size()) { // del array algún elemento
                                menor = revistasOg.get(j); // menor que el actual
                                pos = j; 
                            }   break;
                    }

                }
                if (pos != i) { // si hay alguno menor se intercambia
                    aux = revistasOg.get(i);
                    revistasOg.set(i, revistasOg.get(pos));
                    revistasOg.set(pos, aux);
                }
            }
        }

        for (int i = 0; (i < 5 && i < revistasOg.size()); i++) {
            if (revistasOg.get(i) != null) {

                revOrdenadas.add(revistasOg.get(i));
            }

        }
        return revOrdenadas;
    }

    public float obtenerPorcentajeDelSistema() {
        float porcentaje = 0;
        try {
            if (getConeccion() == null) {
                setConeccion();
            }
            ResultSet resultado2 = null;
            PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_PORCENTAJE_SISTEMA);
            resultado2 = declaracionPreparada.executeQuery();
            if (resultado2.next()) {
                porcentaje = resultado2.getFloat("PorcentajeSistema");
            }
        } catch (SQLException e) {
        }
        return porcentaje;
    }

}
