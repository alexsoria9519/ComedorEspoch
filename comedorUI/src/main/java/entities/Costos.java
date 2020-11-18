/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import servicios.CostoWS;

/**
 *
 * @author Alex
 */
public class Costos {
    
    private List<Costo> costos = new ArrayList<Costo>();

    public List<Costo> getCostos() {
        return costos;
    }

    public void setCostos(List<Costo> costos) {
        this.costos = costos;
    }
    
    public String costosTipoUsuarios(String tipoUsuario){
        String resAll;
        CostoWS costoWs = new CostoWS();
        resAll = costoWs.tipoLikeDetalle(String.class, tipoUsuario);
        System.out.println(resAll);
        return  "{ \"costos\" : " + resAll + " }";
    }
    
    
    
}
