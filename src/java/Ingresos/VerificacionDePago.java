/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ingresos;

import ControladorDB.Controlador;
import ControladorDB.Controlador2;
import Revista.Edicion;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author sergio
 */
public class VerificacionDePago {
    
    public ArrayList<Atrazo> obtenerAtrazos(String userName){
        ArrayList<Atrazo> atrazos = new ArrayList<>();
        Controlador2 co = new Controlador2();
        ArrayList<Suscripcion> sus = co.obtnerSuscripcionPorUser(userName);
        
        for (int i = 0; i < sus.size(); i++) {
            Pago ultimoPago=sus.get(i).getPagos().get(0);
            for (int j = 0; j < sus.get(i).getPagos().size(); j++) {
                if (ultimoPago.getFecha().isBefore((sus.get(i).getPagos().get(j).getFecha()))) {
                    ultimoPago=sus.get(i).getPagos().get(j);
                }                
            }
            int dias = 0;
            LocalDate fechaInicio=ultimoPago.getFecha();
            while (fechaInicio.isBefore(LocalDate.now())) {
                fechaInicio= fechaInicio.plusDays(1);
                dias++;
            }
            dias = dias-30;
            if (dias>-2 &&dias<0) {
                atrazos.add(new Atrazo(sus.get(i).getRevista(), ultimoPago.getFecha(), 0, (dias/-1)));
            }else if (dias>=0){
                atrazos.add(new Atrazo(sus.get(i).getRevista(), ultimoPago.getFecha(), dias, 0));
            }
            
        }
        return atrazos;
    }
    public boolean verificarEdicionPagado(String User,String codigoRevista,Edicion Ed){
        Controlador2 co = new Controlador2();
        String codigoSuscripcion=co.obtnerCodigoSuscripcion(User, codigoRevista);
        Controlador co2 = new Controlador();
        ArrayList<Pago> pagos = co2.obtnerPagosPorCodigo(codigoSuscripcion);
        Pago ultimoPago=pagos.get(0);
        for (int j = 0; j < pagos.size(); j++) {
                if (ultimoPago.getFecha().isBefore((pagos.get(j).getFecha()))) {
                    ultimoPago=pagos.get(j);
                }                
            }
        if (Ed.getFecha().isAfter(ultimoPago.getFecha().plusMonths(1))) {
            return false;
        }else{
            return true;
        }

    }
}
