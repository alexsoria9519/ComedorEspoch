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
 * @author alex4
 */
public class Operativos {

    private List<Operativo> operativos = new ArrayList<Operativo>();

    public List<Operativo> getOperativos() {
        return operativos;
    }

    public void setOperativos(List<Operativo> operativos) {
        this.operativos = operativos;
    }
    
    public String findDataIdentificador(String key, List<Operativo> operativos) {
        for (Operativo operativo : operativos) {
            if (operativo.getStridentificador().equals(key)) {
                System.err.println("testFilter " + operativo.getStrdetalle());
                return operativo.getStrdetalle();
            }
        }
        return null;
    }

}
