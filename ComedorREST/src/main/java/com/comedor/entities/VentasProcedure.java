/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author corebitsas
 */
public class VentasProcedure {
    private List<VentaProcedure> ventasProcedure = new ArrayList<VentaProcedure>();

    public VentasProcedure() {
    }

    public List<VentaProcedure> getVentasProcedure() {
        return ventasProcedure;
    }

    public void setVentasProcedure(List<VentaProcedure> ventasProcedure) {
        this.ventasProcedure = ventasProcedure;
    }
    
}
