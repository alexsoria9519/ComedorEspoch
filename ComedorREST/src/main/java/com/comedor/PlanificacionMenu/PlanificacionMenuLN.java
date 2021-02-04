/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.PlanificacionMenu;

import com.comedor.entities.Planificacionmenu;
import com.comedor.servicios.MenuWS;
import com.comedor.servicios.PlanificacionMenuWS;
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
public class PlanificacionMenuLN {

    PlanificacionMenuWS planificacionWs = new PlanificacionMenuWS();
    JSONObject resJson = new JSONObject();

    public PlanificacionMenuLN() {
    }

    public String insertPlanificacion(String jsonData) {
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();
        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataPlanificacionMenu = utilidades.getDataJson(resquestJson, "planificacionMenu");
            Planificacionmenu planificacionMenu = gson.fromJson(dataPlanificacionMenu, Planificacionmenu.class);
            if (validarDatosIngreso(planificacionMenu)) {
                planificacionWs.create(planificacionMenu);
                resJson.put("success", "ok");
                resJson.put("data", "Ingreso Correcto");
            }
        } catch (JsonSyntaxException | ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
        }
        return resJson.toString();
    }

    private Boolean validarDatosIngreso(Planificacionmenu planificacion) {
        try {
            if (planificacion.getIntidmenu().getIntidmenu() == null || planificacion.getIntidmenu().getIntidmenu() <= 0) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el menu para planificar las fechas");
                return false;
            } else if (planificacion.getDtfechainicio() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar la fecha de inicio");
                return false;
            } else if (planificacion.getDtfechafin() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar la fecha de fin");
                return false;
            } else if (!validarMenu(planificacion.getIntidmenu().getIntidmenu())) {

                Boolean validarMenu = validarMenu(planificacion.getIntidmenu().getIntidmenu());
                System.err.println("validarMenu " + validarMenu);
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar un tipo de menu válido");
                return false;
            } else if (!validarIntervalosFechas(planificacion)) {
                resJson.put("success", "validacion");
                resJson.put("data", "Ya se encuentra activo el menú para el rango de fechas seleccionado");
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }

        return true;
    }

    private Boolean validarMenu(Integer idMenu) {
        MenuWS menuWs = new MenuWS();
        try {
            String resAll = menuWs.find(String.class, idMenu.toString());
            return !"{}".equals(resAll);
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    private Boolean validarIntervalosFechas(Planificacionmenu planificacion) {
        try {

            String validate = planificacionWs.listMenusFechasIntervalo(planificacion, String.class);
            return (validate.equals("{}"));
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    public String desactivarPlanificacionMenu(Integer idPlanificacionMenu) {
        String resAll = "";
        try {

            resAll = planificacionWs.find(String.class, idPlanificacionMenu.toString());
            System.err.println("Find Planificacion Menu " + resAll);
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No se puede eliminar porque no existen datos del codigo proporcionado");
            } else {
                resAll = planificacionWs.remove(idPlanificacionMenu.toString());
                System.err.println("Eliminacion retorno " + resAll);
                resJson.put("success", "ok");
                resJson.put("data", "Eliminacion Correcta");
            }

        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la eliminacion");
            System.err.println("com.comedor.PlanificacionMenu.PlanificacionMenuLN.desactivarPlanificacionMenu() " + ex);
        }
        return resJson.toString();
    }
}
