/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Revista;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sergio
 */
public class Costos {
    private float costo;
    private LocalDate fecha;
    public Costos (HttpServletRequest request) {
        costo = Float.valueOf(request.getParameter("costoPorDia"));
        fecha = LocalDate.parse(request.getParameter("fecha"));
    }
}
