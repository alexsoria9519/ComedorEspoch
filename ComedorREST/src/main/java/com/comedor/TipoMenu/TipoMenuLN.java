/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.TipoMenu;

import com.comedor.Costos.CostoLN;
import com.comedor.Menu.MenuLN;
import com.comedor.entities.Tipomenu;
//import com.comedor.servicios.CostoWS;
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
public class TipoMenuLN {

    TipoMenuWS tipoMenuWs = new TipoMenuWS();
    JSONObject resJson = new JSONObject();

    public TipoMenuLN() {
    }

    public String insertTipoMenu(String jsonData) {
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();
        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataUsuario = utilidades.getDataJson(resquestJson, "tipoMenu");
            Tipomenu tipoMenu = gson.fromJson(dataUsuario, Tipomenu.class);

            if (validarDatosIngreso(tipoMenu) && validarTipoRepetido(tipoMenu)) {
                tipoMenuWs.create(tipoMenu);
                resJson.put("success", "ok");
                resJson.put("data", "Ingreso Correcto");
            }
        } catch (JsonSyntaxException | ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.insertTipoMenu() " + ex);
        }
        return resJson.toString();
    }

    private Boolean validarDatosIngreso(Tipomenu tipoMenu) {
        try {
            if (tipoMenu.getStrtipo() == null || "".equals(tipoMenu.getStrtipo())) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el nombre del tipo");
                return false;
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.validarDatosIngreso() " + ex);
            return false;
        }
        return true;
    }

    private Boolean validarTipoRepetido(Tipomenu tipoMenu) {

        try {
            String resAll = tipoMenuWs.findByStrTipo(String.class, tipoMenu.getStrtipo());

            if (!"{}".equals(resAll)) {
                resJson.put("success", "validacion");
                resJson.put("data", "Ya existe registro del tipo " + tipoMenu.getStrtipo());
                return false;
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.validarTipoRepetido() " + ex);
            return false;
        }
        return true;
    }

    private Boolean validarEliminacion(Integer idTipo) {
        Tipomenu tipoMenu = new Tipomenu();
        CostoLN costoLn = new CostoLN();
        MenuLN menuLn = new MenuLN();
        try {
            tipoMenu.setIntidtipo(idTipo);

            if (menuLn.tiposDeMenuUtilizadosMenus(idTipo) > 0) {
                resJson.put("success", "menusAsociados");
                resJson.put("data", "Este tipo de menú esta asociado a varios menús. ¿Desea eliminar de todas formas?");
                return false;
            } else if (costoLn.validarTipoMenuUtilizado(idTipo)) {
                resJson.put("success", "costosAsociados");
                resJson.put("data", "Este tipo de menú esta asociado a varios costos. ¿Desea eliminar de todas formas?");
                return false;
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.validarEliminacion() " + ex);
            return false;
        }
        return true;
    }

//    private Integer tiposDeMenuUtilizadosMenus(Tipomenu tipoMenu) {
//        MenuWS menuWs = new MenuWS();
//        Menu menu = new Menu();
//        Menus menus = new Menus();
//        Gson gson = new Gson();
//        String resAll;
//        try {
//            menu.se(tipoMenu);
//            resAll = menuWs.findByTipoMenu(menu, String.class);
//            menus = gson.fromJson("{ \"menus\" : " + resAll + " }", Menus.class);
//            return menus.getMenus().size();
//
//        } catch (JsonSyntaxException | ClientErrorException | NullPointerException ex) {
//            System.err.println("com.comedor.TipoMenu.TipoMenuLN.tiposDeMenuUtilizados()");
//            System.err.println(ex);
//        }
//        return -1;
//    }
    public String getTipoMenu(Integer idTipo) {
        String resAll = "";
        try {
            resAll = tipoMenuWs.find(String.class, idTipo.toString());
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No existen datos de ese codigo");
            } else {
                resJson.put("success", "ok");
                resJson.put("tipoMenu", resAll);
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la busqueda");
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.getTipoMenu() " + ex);
        }
        return resJson.toString();
    }

    public String getTipoMenuByTipo(String tipo) {
        String resAll = "";

        try {
            resAll = tipoMenuWs.findByStrTipo(String.class, tipo);
            System.err.println("resAll Comedor WS " + resAll);
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No se encontro ningun registro del tipo " + tipo);
            } else {
                resJson.put("success", "ok");
                resJson.put("tipoMenu", resAll);
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la busqueda");
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.getTipoMenuByTipo() " + ex);
        }
        return resJson.toString();
    }

    public String updateTipoMenu(String jsonData) {
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();

        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataUsuario = utilidades.getDataJson(resquestJson, "tipoMenu");
            Tipomenu tipoMenu = gson.fromJson(dataUsuario, Tipomenu.class);

            if (validarTipoRepetido(tipoMenu)) {
                tipoMenuWs.edit(tipoMenu, tipoMenu.getIntidtipo().toString());
                resJson.put("success", "ok");
                resJson.put("data", "Modificacion Correcta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la modificacion");
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.updateTipoMenu() " + ex);
        }
        return resJson.toString();
    }

    public String deleteTipoMenu(Integer idTipo, String tipo) {
        String resAll = "";
        try {
            resAll = tipoMenuWs.find(String.class, idTipo.toString());
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No existe el tipo de menu a eliminar");
            } else {
                eliminacionPorTipo(idTipo, tipo);
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la eliminacion");
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.deleteTipoMenu() " + ex);
        }
        return resJson.toString();
    }

    private void eliminacionPorTipo(Integer idTipo, String tipo) {
        try {
            if (tipo.equals("Normal")) {
                if (validarEliminacion(idTipo)) {
                    tipoMenuWs.remove(idTipo.toString());
                    resJson.put("success", "ok");
                    resJson.put("data", "Eliminacion Correcta");
                }
            } else if (tipo.equals("forzada")) {
                tipoMenuWs.remove(idTipo.toString());
                resJson.put("success", "ok");
                resJson.put("data", "Eliminacion Correcta");
            } else {
                resJson.put("success", "error");
                resJson.put("data", "No existe el tipo de eliminacion");
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.TipoMenu.TipoMenuLN.eliminacionPorTipo() " + ex);
        }

    }
}
