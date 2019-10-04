/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorDB;

import Revista.Revista;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class Filtracion {

    public static ArrayList<Revista> obtenerRevNoSuscritas(String userName) {
        ArrayList<Revista> revistas = new ArrayList<Revista>();
        Controlador co = new Controlador();
        Controlador2 co2 = new Controlador2();
        ArrayList<Revista> todas = co2.obtnerRevistasPorEstado(2);
        ArrayList<Revista> suscritas = co.obtnerRevistasDeSuscriptor(userName);
        for (int i = 0; i < todas.size(); i++) {
            boolean aux = true;
            for (int j = 0; j < suscritas.size(); j++) {
                if (todas.get(i).getCodigo().equals(suscritas.get(j).getCodigo())) {
                    aux = false;
                }
            }
            if (aux) {
                revistas.add(todas.get(i));
            }

        }
        return revistas;
    }
}
