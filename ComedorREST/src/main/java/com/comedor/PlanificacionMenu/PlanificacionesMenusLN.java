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
            PlanificacionMenus planificacionMenus = gson.fromJson("{ \"planificacionMenus\" : " + resAll + " }", PlanificacionMenus.class);
            resJson.put("planificacionesMenu", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", planificacionMenus.getPlanificionMenus().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }

    public Integer insertPlanificacionesMenu(Menu menu) {
        Integer numeroIngresos = 0;
        try {
            if (menu.getPlanificacionmenuCollection() != null) {
                if (menu.getPlanificacionmenuCollection().size() > 0) {
                    for (Planificacionmenu planificacionmenu : menu.getPlanificacionmenuCollection()) {
                        planificacionmenu.setIntidmenu(menu);
                        String resAll = planificacionWs.create(planificacionmenu);
                        JSONObject response = new JSONObject(resAll);
                        if (response.getBoolean("ok")) {
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

    public String getFechasPlanificacionesByFechaActual() {
        Gson gson = new Gson();
        try {
            String resAll = planificacionWs.listMenusByFecha(String.class);

            if (resAll != null) {
                PlanificacionMenus planificacionMenus = gson.fromJson("{ \"planificacionMenus\" : " + resAll + " }", PlanificacionMenus.class);
                resJson.put("planificacionesMenu", resAll);
                resJson.put("success", "ok");
                resJson.put("cantidad", planificacionMenus.getPlanificionMenus().size());
            } else {
                resJson.put("success", "validacion");
                resJson.put("data", "No se encontraron registros");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }

    public String getFechasPlanificacionesByIdMenu(Integer idMenu) {
        Gson gson = new Gson();
        try {
            String resAll = planificacionWs.listPlanificacionMenuByIdMenu(String.class, idMenu.toString());
            PlanificacionMenus planificacionMenus = gson.fromJson("{ \"planificacionMenus\" : " + resAll + " }", PlanificacionMenus.class);
            if (resAll != null) {
                resJson.put("planificacionesMenu", resAll);
                resJson.put("success", "ok");
                resJson.put("cantidad", planificacionMenus.getPlanificionMenus().size());
            } else {
                resJson.put("success", "validacion");
                resJson.put("data", "No se encontraron registros");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }

    public Integer getNumeroPlanificacionesMenu(Integer idMenu) {
        String resAll = "";
        Gson gson = new Gson();
        try {
            resAll = planificacionWs.listPlanificacionMenuByIdMenu(String.class, idMenu.toString());
            PlanificacionMenus planificacionMenus = gson.fromJson("{ \"planificacionMenus\" : " + resAll + " }", PlanificacionMenus.class);
            return planificacionMenus.getPlanificionMenus().size();
        } catch (Exception ex) {
            return -1;
        }
    }

    public Integer getNumeroPlanificacionesActivasMenu(Integer idMenu) {
        Integer menusActivos = 0;
        try {
            Gson gson = new Gson();
            String resAll = planificacionWs.listMenusByFecha(String.class);

            if (resAll != null) {
                PlanificacionMenus planificacionesMenu = gson.fromJson("{ \"planificacionMenus\" : " + resAll + " }", PlanificacionMenus.class);
                for (Planificacionmenu planificacionMenu : planificacionesMenu.getPlanificionMenus()) {
                    if (planificacionMenu.getIntidmenu().getIntidmenu() == idMenu) {
                        menusActivos++;
                    }
                }
            }
        } catch (Exception ex) {
            return -1;
        }
        return menusActivos;
    }

}
