/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.TipoUsuario;

import com.comedor.CostoUsuario.CostoUsuarioLN;
import com.comedor.Costos.CostoLN;
import com.comedor.entities.Tipousuario;
import com.comedor.servicios.TipoUsuarioWS;
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
public class TipoUsuarioLN {

    TipoUsuarioWS tipoUsuarioWS = new TipoUsuarioWS();
    JSONObject resJson = new JSONObject();

    public TipoUsuarioLN() {
    }

    public String insertTipoUsuario(String jsonData) {
        Gson gson = new Gson();
        Utilidades utilidades = new Utilidades();

        try {
            JSONObject resquestJson = new JSONObject(jsonData);

            // String dataUsuario = resquestJson.getJSONObject("tipoUsuario").toString();
            String dataUsuario = utilidades.getDataJson(resquestJson, "tipoUsuario");
            // Tipousuario tipoUsuario = gson.fromJson(jsonData, Tipousuario.class);
            Tipousuario tipoUsuario = gson.fromJson(dataUsuario, Tipousuario.class);
            if (validarDatosIngreso(tipoUsuario) && ValidarTipoRepetido(tipoUsuario)) {
                tipoUsuarioWS.create(tipoUsuario);
                resJson.put("success", "ok");
                resJson.put("data", "Ingreso Correcto");
            }
        } catch (JsonSyntaxException | ClientErrorException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
            System.err.println("com.comedor.TipoUsuario.TipoUsuarioLN.insertTipoUsuario() " + ex);
        }
        return resJson.toString();
    }

    private Boolean validarDatosIngreso(Tipousuario tipousuario) {
        try {
            if (tipousuario.getStrtipo() == null || "".equals(tipousuario.getStrtipo())) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el nombre del tipo");
                return false;
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.TipoUsuario.TipoUsuarioLN.validarDatosIngreso() " + ex);
            return false;
        }
        return true;
    }

    public Boolean ValidarTipoRepetido(Tipousuario tipousuario) {

        try {
            String resAll = tipoUsuarioWS.findByStrTipo(String.class, tipousuario.getStrtipo());

            if (!"{}".equals(resAll)) {
                resJson.put("success", "validacion");
                resJson.put("data", "Ya existe registro del tipo " + tipousuario.getStrtipo());
                return false;
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.TipoUsuario.TipoUsuarioLN.ValidarTipoRepetido() " + ex);
            return false;
        }
        return true;
    }

    public String getTipoUsuario(Integer idTipo) {
        String resAll = "";
        try {
            resAll = tipoUsuarioWS.find(String.class, idTipo.toString());
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No existen datos de ese codigo");
            } else {
                resJson.put("success", "ok");
                resJson.put("tipoUsuario", resAll);
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la busqueda");
            System.err.println("com.comedor.TipoUsuario.TipoUsuarioLN.getTipoUsuario() " + ex);
        }
        return resJson.toString();
    }

    public String updateTipoUsuario(String jsonData) {

        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();

        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataUsuario = utilidades.getDataJson(resquestJson, "tipoUsuario");
            // Tipousuario tipoUsuario = gson.fromJson(jsonData, Tipousuario.class);
            Tipousuario tipoUsuario = gson.fromJson(dataUsuario, Tipousuario.class);

            if (ValidarTipoRepetido(tipoUsuario)) {
                tipoUsuarioWS.edit(tipoUsuario, tipoUsuario.getIntidtipo().toString());
                resJson.put("success", "ok");
                resJson.put("data", "Modificacion Correcta");
            }
        } catch (JsonSyntaxException | ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la modificacion");
            System.err.println("com.comedor.TipoUsuario.TipoUsuarioLN.updateTipoUsuario() " +ex);
        }
        return resJson.toString();
    }

    public String deleteTipoUsuario(Integer idTipo, String tipoEliminacion) {
        String resAll = "";
        try {
            resAll = tipoUsuarioWS.find(String.class, idTipo.toString());
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No se puede eliminar porque no existen datos del codigo proporcionado");
            } else {
                if (tipoEliminacion.equals("normal")) {
                    CostoLN costoLn = new CostoLN();
                    if (costoLn.validarTipoUsuarioUtilizado(idTipo)) {
                        resJson.put("success", "info");
                        resJson.put("data", "Este tipo tiene asociado varios registros de usuarios");
                    } else {
                        tipoUsuarioWS.remove(idTipo.toString());
                        resJson.put("success", "ok");
                        resJson.put("data", "Eliminacion Correcta");
                    }
                } else {
                    tipoUsuarioWS.remove(idTipo.toString());
                    resJson.put("success", "ok");
                    resJson.put("data", "Eliminacion Correcta");
                }
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la eliminacion");
            System.err.println("com.comedor.TipoUsuario.TipoUsuarioLN.deleteTipoUsuario() " +ex);
        }
        return resJson.toString();
    }

    public String BuscarPorTipo(String tipo) {
        try {

            if (tipo != null || !tipo.equals("")) {
                String resAll = tipoUsuarioWS.findByStrTipo(String.class, tipo);

                if (!"{}".equals(resAll)) {
                    resJson.put("success", "ok");
                    resJson.put("data", resAll);
                } else {
                    resJson.put("success", "notFound");
                    resJson.put("data", "No se encontro ningun registro del tipo " + tipo);
                }
            } else {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar un tipo de usuario");
            }

            return resJson.toString();
        } catch (ClientErrorException | JSONException ex) {
            System.err.println("com.comedor.TipoUsuario.TipoUsuarioLN.BuscarPorTipo() " + ex);
            resJson.put("success", "error");
            resJson.put("message", ex.toString());
            return resJson.toString();
        }
    }
}
