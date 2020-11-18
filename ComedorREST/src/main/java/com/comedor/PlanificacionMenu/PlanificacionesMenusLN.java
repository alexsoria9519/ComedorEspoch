/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.PlanificacionMenu;

import com.comedor.entities.Menu;
import com.comedor.entities.PlanificacionMenus;
import com.comedor.entities.Planificacionmenu;
import com.comedor.servicios.PlanificacionMenuWS;
import com.google.gson.Gson;
import javax.ws.rs.ClientErrorException;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class PlanificacionesMenusLN {

    PlanificacionMenuWS planificacionWs = new PlanificacionMenuWS();
    JSONObject resJson = new JSONObject();

    public PlanificacionesMenusLN() {
    }

    public String getPlanificacionesMenus() {
        String resAll = "";
        Gson gson = new Gson();
        try {
            resAll = planificacionWs.findAll(String.class);
            PlanificacionMenus planificionMenus = gson.fromJson("{ \"costos\" : " + resAll + " }", PlanificacionMenus.class);
            resJson.put("tiposMenus", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", planificionMenus.getPlanificionMenus().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }

    public Integer insertPlanificacionesMenu(Menu menu) {
        Integer numeroIngresos = 0;
        System.err.println("Menu Heredado " + menu.getIntidmenu());
        try {
            if (menu.getPlanificacionmenuCollection() != null) {
                if (menu.getPlanificacionmenuCollection().size() > 0) {
                    for (Planificacionmenu planificacionmenu : menu.getPlanificacionmenuCollection()) {
                        planificacionmenu.setIntidmenu(new Menu());
                        planificacionmenu.getIntidmenu().setIntidmenu(menu.getIntidmenu());
                        System.err.println("menu " + planificacionmenu.getIntidmenu().getIntidmenu());
                        Integer idPlanificacionMenu = Integer.parseInt(planificacionWs.ingreso(planificacionmenu));
                        //Integer idPlanificacionMenu = planificacionWs.ingreso(planificacionmenu, Integer.class);
                        if (idPlanificacionMenu > 0) {
                            numeroIngresos++;
                        } 
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Error " + ex);
        }
        return numeroIngresos;
    }

}
