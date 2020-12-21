/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Menu;

import com.comedor.PlanificacionMenu.PlanificacionesMenusLN;
import com.comedor.entities.Menu;
import com.comedor.entities.Menus;
import com.comedor.entities.Planificacionmenu;
import com.comedor.entities.Tipomenu;
import com.comedor.servicios.MenuWS;
import com.comedor.servicios.PlanificacionMenuWS;
import com.comedor.servicios.TipoMenuWS;
import com.comedor.utilidades.Utilidades;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.ClientErrorException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class MenuLN {

    MenuWS menuWs = new MenuWS();
    PlanificacionMenuWS planificacionMenuWs = new PlanificacionMenuWS();
    PlanificacionesMenusLN planificacionesMenus = new PlanificacionesMenusLN();
    JSONObject resJson = new JSONObject();

    public MenuLN() {
    }

    public String insertMenu(String jsonData) {
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();

        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataMenu = utilidades.getDataJson(resquestJson, "menu");
            Menu menu = gson.fromJson(dataMenu, Menu.class);
            if (validarDatosIngreso(menu) && validarMenuRepetido(menu)) {

                String resAll = menuWs.ingreso(menu);
                JSONObject response = new JSONObject(resAll);
                Integer idMenu = response.getInt("data");
                if (idMenu > 0) {
                    if (menu.getPlanificacionmenuCollection() != null) {
                        if (menu.getPlanificacionmenuCollection().size() > 0) {
                            menu.setIntidmenu(idMenu);
                            ingresarPlanificacionesMenu(menu);
                        } else {
                            resJson.put("success", "ok");
                            resJson.put("data", "Ingreso Correcto");
                        }
                    } else {
                        resJson.put("success", "ok");
                        resJson.put("data", "Ingreso Correcto");
                    }
                } else {
                    resJson.put("success", "error");
                    resJson.put("data", "Error en el ingreso");
                }
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
        }
        return resJson.toString();
    }

    public void ingresarPlanificacionesMenu(Menu menu) {
        try {
            Integer numeroIngresos = planificacionesMenus.insertPlanificacionesMenu(menu);
            if (numeroIngresos == menu.getPlanificacionmenuCollection().size()) {
                resJson.put("success", "ok");
                resJson.put("data", "Ingreso Correcto");
                resJson.put("datosIngresados", numeroIngresos);
            } else {
                resJson.put("success", "error");
                resJson.put("data", "No se ingreso el total de las planificaciones del menu");
                resJson.put("datosIngresados", numeroIngresos);
            }
        } catch (Exception e) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
        }
    }

    public JSONObject insertPlanificacionMenu(Planificacionmenu planificacion) {
        JSONObject respuestaIngreso = new JSONObject();
        try {
            planificacionMenuWs.create(planificacion);
            respuestaIngreso.put("success", "ok");
            respuestaIngreso.put("data", "Ingreso Correcto");
        } catch (Exception ex) {
            respuestaIngreso.put("success", "error");
            respuestaIngreso.put("data", "Error en el ingreso de planificar menus");
        }
        return respuestaIngreso;
    }

    public Boolean validarDatosIngreso(Menu menu) {

        try {
            if (menu.getIntidtipomenu().getIntidtipo() == null || menu.getIntidtipomenu().getIntidtipo() <= 0) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el tipo de menu");
                return false;
            } else if (menu.getStrcaracteristicas() == null || "".equals(menu.getStrcaracteristicas())) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar las características del menu");
                return false;
            } else if (menu.getBlnestado() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar un estado del menu");
                return false;
            } else if (!validarTipoMenu(menu.getIntidtipomenu().getIntidtipo())) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar un tipo de menu válido");
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }

        return true;
    }

    public Boolean validarMenuRepetido(Menu menu) {
        try {
            String resAll = menuWs.findByStrCaracteristicas(String.class, menu.getStrcaracteristicas());
            if (!"{}".equals(resAll)) {
                resJson.put("success", "validacion");
                resJson.put("data", "Ya existe registro con las características " + menu.getStrcaracteristicas());
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
        return true;
    }

    public Boolean validarTipoMenu(Integer idTipo) {
        TipoMenuWS tipoMenuWS = new TipoMenuWS();
        try {
            String resAll = tipoMenuWS.find(String.class, idTipo.toString());
            System.err.println(!"{}".equals(resAll));
            return !"{}".equals(resAll);
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    public String getMenu(Integer idTipo) {
        String resAll = "";
        try {
            resAll = menuWs.find(String.class, idTipo.toString());
            if ("".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No existen datos de ese codigo");
            } else {
                resJson.put("success", "ok");
                resJson.put("menu", resAll);
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la busqueda");
        }
        return resJson.toString();
    }

    public String updateMenu(String jsonData) {
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();
        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataMenu = utilidades.getDataJson(resquestJson, "menu");
            System.out.println(dataMenu);
            Menu menu = gson.fromJson(dataMenu, Menu.class);

            if (validarMenuRepetido(menu)) {
                menuWs.edit(menu, menu.getIntidmenu().toString());
                resJson.put("success", "ok");
                resJson.put("data", "Modificacion Correcta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la modificacion");
        }
        return resJson.toString();
    }

    public Integer tiposDeMenuUtilizadosMenus(Integer idTipoMenu) {
        Menu menu = new Menu();
        Menus menus = new Menus();
        Tipomenu tipoMenu = new Tipomenu();
        Gson gson = new Gson();
        String resAll;
        try {
            tipoMenu.setIntidtipo(idTipoMenu);
            menu.setIntidtipomenu(tipoMenu);
            resAll = menuWs.findByTipoMenu(menu, String.class);
            menus = gson.fromJson("{ \"menus\" : " + resAll + " }", Menus.class);
            return menus.getMenus().size();

        } catch (JsonSyntaxException | ClientErrorException | NullPointerException ex) {
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.tiposDeMenuUtilizados()");
            System.err.println(ex);
        }
        return -1;
    }
}
