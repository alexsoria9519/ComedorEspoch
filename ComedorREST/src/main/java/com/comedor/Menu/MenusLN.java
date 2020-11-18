/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Menu;

import com.comedor.entities.Menus;
import com.comedor.servicios.MenuWS;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class MenusLN {

    MenuWS menuWs = new MenuWS();
    JSONObject resJson = new JSONObject();
    
    public MenusLN() {
    }
    
    public String getMenus() {
        String resAll = "";
        Gson gson = new Gson();
        try {
            resAll = menuWs.findAll(String.class);
            Menus menus = gson.fromJson("{ \"menus\" : " + resAll + " }", Menus.class);
            resJson.put("menus", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", menus.getMenus().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }
    
    
}
