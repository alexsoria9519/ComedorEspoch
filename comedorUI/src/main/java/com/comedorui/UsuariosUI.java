/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedorui;

import com.google.gson.Gson;
import entities.TipoUsuarios;
import entities.Tipousuario;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
public class UsuariosUI {

    private Gson gson = new Gson();

    public UsuariosUI() {
    }

    public String selectTiposMenus(String dataTipoMenus) {
        String HTML = "<select class='form-control' id='tiposUsuario'>";
        try {
            JSONObject resp = new JSONObject(dataTipoMenus);
            if (resp.getString("success").equals("ok")) {
                TipoUsuarios tiposUsuarios = gson.fromJson("{ \"tipoUsuarios\" : " + resp.getString("tiposUsuarios") + " }", TipoUsuarios.class);
                for (Tipousuario tipoUsuario : tiposUsuarios.getTipoUsuarios()) {
                    HTML += "<option value='" + tipoUsuario.getIntidtipo() + "'>" + tipoUsuario.getStrtipo() + "</option>";
                }
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.UsuariosUI.selectTiposMenus() " + ex);
        }

        return HTML + "</select>";
    }

}
