/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Operativos;

import com.comedor.entities.Operativo;
import com.comedor.entities.Operativos;
import com.comedor.servicios.OperativosWS;
import com.google.gson.Gson;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
public class OperativosLN {

    OperativosWS operativosWS = new OperativosWS();
    JSONObject resJson = new JSONObject();

    public OperativosLN() {
    }

    public String listadoOperativos() {
        String resAll = "";
        Gson gson = new Gson();
        try {
            resAll = operativosWS.findAll(String.class);

            Operativos operativos = gson.fromJson("{ \"operativos\" : " + resAll + " }", Operativos.class);
            resJson.put("operativos", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", operativos.getOperativos().size());
            operativos.findDataIdentificador("IVA", operativos.getOperativos());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }

}
