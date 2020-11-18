/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.CostoUsuario;

import com.comedor.entities.CostosUsuarios;
import com.comedor.servicios.CostoUsuarioWS;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class CostosUsuariosLN {

    CostoUsuarioWS costoUsuarioWs = new CostoUsuarioWS();
    JSONObject resJson = new JSONObject();
    
    public CostosUsuariosLN() {
    }
    
    public String getCostosUsuarios() {
        String resAll = "";
        Gson gson = new Gson();
        try {
            resAll = costoUsuarioWs.findAll(String.class);
            CostosUsuarios costosUsuarios = gson.fromJson("{ \"costosUsuarios\" : " + resAll + " }", CostosUsuarios.class);
            resJson.put("CostosUsuarios", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", costosUsuarios.getCostosUsuarios().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }
    
}
