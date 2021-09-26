/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EspochWs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex4
 */
public class FacultadCarrera {
    
    private String codigo;
    private String nombre;
    private String idPadre;
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getIdPadre() {
        return idPadre;
    }
    
    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }
    
    public List<FacultadCarrera> convertirListaConteoFacultad(List<Object[]> dataList) {
        List<FacultadCarrera> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                
                FacultadCarrera facCar = new FacultadCarrera();
                
                String codigo = (String) object[0];
                String nombre = (String) object[1];
                
                facCar.setCodigo(codigo);
                facCar.setNombre(nombre);
                result.add(facCar);
            }
            return result;
        } catch (Exception ex) {
            System.out.println("com.EspochWs.FacultadCarrera.convertirListaConteoFacultad() " + ex);
            return null;
        }
    }
    
    public List<FacultadCarrera> convertirListaConteoCarrera(List<Object[]> dataList) {
        List<FacultadCarrera> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                
                FacultadCarrera facCar = new FacultadCarrera();
                
                String codigo = (String) object[0];
                String nombre = (String) object[1];
                String idPadre = (String) object[2];
                
                facCar.setCodigo(codigo);
                facCar.setNombre(nombre);
                
                if (idPadre != null) {
                    facCar.setIdPadre(idPadre);
                }
                
                result.add(facCar);
            }
            return result;
        } catch (Exception ex) {
            System.out.println("com.EspochWs.FacultadCarrera.convertirListaConteoFacultad() " + ex);
            return null;
        }
    }
    
}
