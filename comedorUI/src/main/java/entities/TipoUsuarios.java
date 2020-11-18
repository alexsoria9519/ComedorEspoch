/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author corebitsas
 */
public class TipoUsuarios {
    private List<Tipousuario> tipoUsuarios = new ArrayList<Tipousuario>();

    public TipoUsuarios() {
    }

    public List<Tipousuario> getTipoUsuarios() {
        return tipoUsuarios;
    }

    public void setTipoUsuarios(List<Tipousuario> tipoUsuarios) {
        this.tipoUsuarios = tipoUsuarios;
    }

    
    
}
