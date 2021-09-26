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
 * @author alex4
 */
public class DataUsuarios {

    private List<DataUsuario> dataUsuarios = new ArrayList<DataUsuario>();

    public DataUsuarios() {
    }

    public List<DataUsuario> getDataUsuarios() {
        return dataUsuarios;
    }

    public void setDataUsuarios(List<DataUsuario> dataUsuarios) {
        this.dataUsuarios = dataUsuarios;
    }

}
