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
public class TipoMenus {
    private List<Tipomenu> tipoMenus = new ArrayList<Tipomenu>();

    public TipoMenus() {
    }

    public List<Tipomenu> getTipoMenus() {
        return tipoMenus;
    }

    public void setTipoMenus(List<Tipomenu> tipoMenus) {
        this.tipoMenus = tipoMenus;
    }

    
}
