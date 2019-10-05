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

    private final static String ST_COMENTARIO = " SELECT CodigoRevista,Nombre,Comentario,s.UserName  From Reaccion s Join Revista r On s.CodigoRevista=r.Codigo ";
    private final static String ST_NOT_NULL = " Comentario != 'Null'";
    private final static String ST_WHERE = " WHERE ";
    private final static String ST_FECHA_INICIO = " s.Fecha> ? ";
    private final static String ST_AND = " AND ";
    private final static String ST_FECHA_FINAL = " s.Fecha< ? ";

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
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO+ST_AND+ST_NOT_NULL);
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio != null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_FECHA_INICIO + ST_AND + ST_FECHA_FINAL+ST_AND+ST_NOT_NULL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                declaracionPreparada.setString(2, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }

            if (inicio != null && fin == null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_FECHA_INICIO+ST_AND+ST_NOT_NULL);
                declaracionPreparada.setString(1, String.valueOf(inicio));
                resultado2 = declaracionPreparada.executeQuery();
            }
            if (inicio == null && fin != null) {
                PreparedStatement declaracionPreparada = getConeccion().prepareStatement(ST_COMENTARIO + ST_WHERE + ST_FECHA_FINAL+ST_AND+ST_NOT_NULL);
                declaracionPreparada.setString(1, String.valueOf(fin));
                resultado2 = declaracionPreparada.executeQuery();
            }
            Controlador co = new Controlador();
            while (resultado2.next()) {
                Comentario com = new Comentario(co.obtenerUsuario(resultado2.getString("UserName")).getUserName(), resultado2.getString("Comentario"),
                         null);
                com.setCodigoRevista(resultado2.getString("CodigoRevista"));
                com.setNombreRevista(resultado2.getString("Nombre"));
                System.out.println("come");
                comentarios.add(com);
            }
            for (int j = 0; j < comentarios.size(); j++) {
                System.out.println(comentarios.size());
                boolean aux = true;
                System.out.println(revistas.size());
                for (int k = 0; k < revistas.size(); k++) {
                    if (revistas.get(k).getCodigo().equals(comentarios.get(j).getCodigoRevista())) {
                        revistas.get(k).addComentario(comentarios.get(j));
                        System.out.println("rep");
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
                    if (tipo == 1) {
                        if (revistasOg.get(j).getSuscripcions().size() > menor.getSuscripcions().size()) { // del array algún elemento
                            menor = revistasOg.get(j); // menor que el actual
                            pos = j;
                        }
                    } else {
                        if (revistasOg.get(j).getComentario().size() > menor.getComentario().size()) { // del array algún elemento
                            menor = revistasOg.get(j); // menor que el actual
                            pos = j;
                        }
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
                System.out.println("s");
            }

        }
        return revOrdenadas;
    }
}
