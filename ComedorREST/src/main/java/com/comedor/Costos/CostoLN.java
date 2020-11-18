/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Costos;

import com.comedor.entities.Costo;
import com.comedor.entities.Costousuario;
import com.comedor.entities.Tipomenu;
import com.comedor.entities.Tipousuario;
import com.comedor.servicios.CostoWS;
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
public class CostoLN {

    CostoWS costoWs = new CostoWS();
    JSONObject resJson = new JSONObject();

    public CostoLN() {
    }

    public String insertCosto(String jsonData) {
        System.out.println("Datos Ingreso Rest "+ jsonData);
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();
        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataTipoMenu = utilidades.getDataJson(resquestJson, "costo");
            Costo costo = gson.fromJson(dataTipoMenu, Costo.class);
            if (validarDatosIngreso(costo) && validarCostoRepetido(costo)) {
                costoWs.create(costo);
                resJson.put("success", "ok");
                resJson.put("data", "Ingreso Correcto");
            }
        } catch (JsonSyntaxException | ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
        }
        return resJson.toString();
    }

    public Boolean validarDatosIngreso(Costo costo) {
        // Utilidades utilidades = new Utilidades();
        try {
            if (costo.getStrdetalle() == null || "".equals(costo.getStrdetalle())) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el detalle del costo");
                return false;
            } else if (costo.getMnvalor() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el valor del costo");
                return false;
            } else if (costo.getMnvalor() < 0) {
                resJson.put("success", "validacion");
                resJson.put("data", "El valor del costo debe ser mayor a 0");
                return false;
            } else if (costo.getDtfecha() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el valor de la fecha");
                return false;
//        } else if(!utilidades.validarFecha(costo.getDtfecha().toString())){
//            resJson.put("success", "validacion");
//            resJson.put("data", "Se debe ingresar una fecha válida");
//            return false;
            } else if (costo.getBlnestado() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe proporcionar el estado del costo");
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
        return true;

    }

    public Boolean validarCostoRepetido(Costo costo) {

        try {
            String resAll = costoWs.findByStrDetalle(String.class, costo.getStrdetalle());

            if (!"{}".equals(resAll)) {
                resJson.put("success", "validacion");
                resJson.put("data", "Ya existe registro con el detalle " + costo.getStrdetalle());
                return false;
            }
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
        return true;
    }

    public String getCosto(Integer idTipo) {
        String resAll = "";
        try {
            resAll = costoWs.find(String.class, idTipo.toString());
            if ("".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No existen datos de ese codigo");
            } else {
                resJson.put("success", "ok");
                resJson.put("Costo", resAll);
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la busqueda");
        }
        return resJson.toString();
    }

    public String updateTipoMenu(String jsonData) {
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();

        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataTipoMenu = utilidades.getDataJson(resquestJson, "costo");
            Costo costo = gson.fromJson(dataTipoMenu, Costo.class);

            if (validarCostoRepetido(costo)) {
                costoWs.edit(costo, costo.getIntidcosto().toString());
                resJson.put("success", "ok");
                resJson.put("data", "Modificacion Correcta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la modificacion");
        }
        return resJson.toString();
    }

    public String deleteCosto(Integer idTipo) {
        String resAll = "";
        try {
            
            resAll = costoWs.find(String.class, idTipo.toString());
            
            System.err.println("deleteCosto " + resAll);
            
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No se puede eliminar porque no existen datos del codigo proporcionado");
            } else {
                costoWs.remove(idTipo.toString());
                resJson.put("success", "ok");
                resJson.put("data", "Eliminacion Correcta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la eliminacion");
        }
        return resJson.toString();
    }
    
    public String tiposUsuariosUtilizados(Integer idTipoUsuario) {
        String resAll = "";
        Costo costo = new Costo();
        Tipousuario tipoUsuario = new Tipousuario();
        try {
            tipoUsuario.setIntidtipo(idTipoUsuario);
            costo.setIntidtipousuario(tipoUsuario);
            
            resAll = costoWs.getTiposUsuariosUtilizados(costo, String.class);
            
            if ("[]".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "El codigo ingresado no esta utilizado");
            } else {
                resJson.put("success", "ok");
                resJson.put("data", resAll);
            }
        } catch (Exception ex) {
            System.out.println("com.comedor.CostoUsuario.CostoUsuarioLN.tiposUsuariosUtilizados()" + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al buscar el tipo de usuario utilizado");
        }
        return resJson.toString();
    }
    
    public String tiposMenusUtilizados(Integer idTipoMenu) {
        String resAll = "";
        Costo costo = new Costo();
        Tipomenu tipoMenu = new Tipomenu();
        try {
            tipoMenu.setIntidtipo(idTipoMenu);
            costo.setIntidtipomenu(tipoMenu);
            
            resAll = costoWs.getTiposMenusUtilizados(costo, String.class);
            
            if ("[]".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "El codigo ingresado no esta utilizado");
            } else {
                resJson.put("success", "ok");
                resJson.put("data", resAll);
            }
        } catch (Exception ex) {
            System.out.println("com.comedor.CostoUsuario.CostoUsuarioLN.tiposUsuariosUtilizados()" + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al buscar el tipo de usuario utilizado");
        }
        return resJson.toString();
    }
    
    

    public Boolean validarTipoUsuarioUtilizado(Integer idTipoUsuario) {
        try {
            String resAll = tiposUsuariosUtilizados(idTipoUsuario);
            JSONObject respJson = new JSONObject(resAll);
            return respJson.getString("success").equals("ok");
        } catch (JSONException ex) {
            System.out.println("com.comedor.CostoUsuario.CostoLN.validarTipoUsuarioUtitlizado()" + ex);
            return false;
        }
    }
    
    public Boolean validarTipoMenuUtilizado(Integer idTipoMenu) {
        try {
            String resAll = tiposMenusUtilizados(idTipoMenu);
            JSONObject respJson = new JSONObject(resAll);
            return respJson.getString("success").equals("ok");
        } catch (JSONException ex) {
            System.out.println("com.comedor.CostoUsuario.CostoLN.validarTipoUsuarioUtitlizado()" + ex);
            return false;
        }
    }

}
