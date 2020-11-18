/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Costos;

import com.comedor.entities.Costos;
import com.comedor.servicios.CostoWS;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class CostosLN {

    CostoWS costoWs = new CostoWS();
    JSONObject resJson = new JSONObject();
    public CostosLN() {
    }
    
    public String getCostos() {
        String resAll = "";
        Gson gson = new Gson();
        try {
            resAll = costoWs.findAll(String.class);
            Costos costos = gson.fromJson("{ \"costos\" : " + resAll + " }", Costos.class);
            resJson.put("costos", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", costos.getCostos().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }
    
    
}
