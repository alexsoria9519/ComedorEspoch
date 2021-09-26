/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Graficos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex4
 */
public class DataUsuario {

    private String nombres;
    private String apellidos;
    private Integer cantidadventas;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getCantidadventas() {
        return cantidadventas;
    }

    public void setCantidadventas(Integer cantidadventas) {
        this.cantidadventas = cantidadventas;
    }

    public List<DataUsuario> convertirListaConteo(List<Object[]> dataList) {
        List<DataUsuario> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                DataUsuario data = new DataUsuario();

                String nombres = (String) object[0];
                String apellidos = (String) object[1];
                Integer cantidadVentas = (Integer) object[2];

                data.setNombres(nombres);
                data.setApellidos(apellidos);
                data.setCantidadventas(cantidadVentas);
                result.add(data);
            }
            return result;
        } catch (Exception ex) {
            System.err.println("com.Graficos.DataUsuario.convertirListaConteo() " + ex);
            return null;
        }
    }

}
