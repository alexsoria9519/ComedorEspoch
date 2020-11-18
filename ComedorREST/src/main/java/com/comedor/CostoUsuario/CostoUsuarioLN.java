/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.CostoUsuario;

import com.comedor.entities.Costousuario;
import com.comedor.entities.Tipousuario;
import com.comedor.servicios.CostoUsuarioWS;
import com.comedor.servicios.CostoWS;
import com.comedor.servicios.TipoUsuarioWS;
import com.comedor.utilidades.CedulaIdentidad;
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
public class CostoUsuarioLN {

    CostoUsuarioWS costoUsuarioWs = new CostoUsuarioWS();
    JSONObject resJson = new JSONObject();

    public CostoUsuarioLN() {
    }

    public String insertCostoUsuario(String jsonData) throws Exception {
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();
        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataCostoUsuario = utilidades.getDataJson(resquestJson, "costoUsuario");
            Costousuario costoUsuario = gson.fromJson(dataCostoUsuario, Costousuario.class);
            if (validarDatosIngreso(costoUsuario)) {
                costoUsuarioWs.create(costoUsuario);
                resJson.put("success", "ok");
                resJson.put("data", "Ingreso Correcto");
            }
        } catch (JsonSyntaxException | ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
        }
        return resJson.toString();
    }

    public Boolean validarDatosIngreso(Costousuario costoUsuario) throws Exception {
        String resCedula;
        CedulaIdentidad cedula = new CedulaIdentidad();
        try {
            if (costoUsuario.getStrcedula() == null || "".equals(costoUsuario.getStrcedula())) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar la cedula del usuario");
                return false;
            } else if (costoUsuario.getIntidtipo().getIntidtipo() == null || costoUsuario.getIntidtipo().getIntidtipo() <= 0) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el tipo de usuario");
                return false;
            } else if (costoUsuario.getIntidcosto().getIntidcosto() == null || costoUsuario.getIntidcosto().getIntidcosto() <= 0) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el costo para el usuario");
                return false;
            } else if (!validarTipoUsuario(costoUsuario.getIntidtipo().getIntidtipo())) {
                resJson.put("success", "validacion");
                resJson.put("data", "No existe registro del tipo de usuario ingresado");
                return false;
            } else if (!validarCosto(costoUsuario.getIntidcosto().getIntidcosto())) {
                resJson.put("success", "validacion");
                resJson.put("data", "No existe registro del costo ingresado");
                return false;
            } else {
                resCedula = cedula.validarCedula(costoUsuario.getStrcedula());
                JSONObject jsonResCed = new JSONObject(resCedula);
                if (!jsonResCed.getBoolean("valido")) {
                    resJson.put("success", "validacion");
                    resJson.put("data", jsonResCed.getString("data"));
                    return false;
                }

            }
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
        return true;
    }

    private Boolean validarTipoUsuario(Integer idTipoUsuario) {
        String resAll;
        try {
            TipoUsuarioWS tipoUsuarioWs = new TipoUsuarioWS();
            resAll = tipoUsuarioWs.find(String.class, idTipoUsuario.toString());
            return !"{}".equals(resAll);
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    private Boolean validarCosto(Integer idCosto) {
        String resAll;
        try {
            CostoWS costoWs = new CostoWS();
            resAll = costoWs.find(String.class, idCosto.toString());
            return !"{}".equals(resAll);
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    public String getCostoUsuario(Integer idTipo) {
        String resAll = "";
        try {
            resAll = costoUsuarioWs.find(String.class, idTipo.toString());
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

    public String deleteCostoUsuario(Integer idTipo) {
        String resAll = "";
        try {
            resAll = costoUsuarioWs.find(String.class, idTipo.toString());
            if ("".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No se puede eliminar porque no existen datos del codigo proporcionado");
            } else {
                costoUsuarioWs.remove(idTipo.toString());
                resJson.put("success", "ok");
                resJson.put("data", "Eliminacion Correcta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la eliminacion");
        }
        return resJson.toString();
    }

    

}
