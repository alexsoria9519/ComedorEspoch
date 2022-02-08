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
public class FacultadesCarreras {

    private List<FacultadCarrera> facultadesCarreras = new ArrayList<FacultadCarrera>();

    public FacultadesCarreras() {
    }

    public List<FacultadCarrera> getFacultadesCarreras() {
        return facultadesCarreras;
    }

    public void setFacultadesCarreras(List<FacultadCarrera> facultadesCarreras) {
        this.facultadesCarreras = facultadesCarreras;
    }

}
