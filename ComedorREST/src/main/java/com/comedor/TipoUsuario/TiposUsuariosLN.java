/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.TipoUsuario;

import com.comedor.entities.TipoUsuarios;
import com.comedor.servicios.TipoUsuarioWS;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.ClientErrorException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class TiposUsuariosLN {

    TipoUsuarioWS tipoUsuarioWS = new TipoUsuarioWS();
    JSONObject resJson = new JSONObject();

    public TiposUsuariosLN() {
    }

    public String getTiposUsuarios() {
        String resAll = "";

        Gson gson = new Gson();
        try {
            resAll = tipoUsuarioWS.findAll(String.class);
            TipoUsuarios tiposUsuarios = gson.fromJson("{ \"tipoUsuarios\" : " + resAll + " }", TipoUsuarios.class);
            // resJson.put("tiposUsuario", gson.toJson(tiposUsuarios));
            resJson.put("tiposUsuarios", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", tiposUsuarios.getTipoUsuarios().size());
        } catch (JsonSyntaxException | ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("error", ex.toString());
        }
        return resJson.toString();
    }
}
