/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.TipoMenu;

import com.comedor.entities.TipoMenus;
import com.comedor.servicios.TipoMenuWS;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class TiposMenusLN {

    TipoMenuWS tipoMenuWs = new TipoMenuWS();
    JSONObject resJson = new JSONObject();

    public TiposMenusLN() {
    }

    public String getTiposMenus() {
        String resAll = "";
        Gson gson = new Gson();
        try {
            resAll = tipoMenuWs.findAll(String.class);
            TipoMenus tipoMenus = gson.fromJson("{ \"tipoMenus\" : " + resAll + " }", TipoMenus.class);
            resJson.put("tiposMenus", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", tipoMenus.getTipoMenus().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }
}
