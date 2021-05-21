/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class Utilidades {

    public Utilidades() {
    }

    public Boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public Boolean validarFecha(String pattern, String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat(pattern);
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
        
//        DateFormat formatter = new SimpleDateFormat(pattern);
//        Date fecha = (Date) formatter.parse(strFecha);
//        return fecha;
    }

    private Boolean isStringObject(JSONObject json, String key) {
        try {
            String data = json.getString(key);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    private Boolean isJsonObject(JSONObject json, String key) {
        try {
            json.getJSONObject(key);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    public String getDataJson(JSONObject json, String key) {
        String res = "";
//        System.out.println("key: "+ key);
//        System.out.println("json: "+ json.toString());
//        System.out.println("Object :"+isStringObject(json, key));
//        System.out.println("String :"+isStringObject(json, key));
        if (isStringObject(json, key)) {
            res = json.getString(key);
        } else if (isJsonObject(json, key)) {
            res = json.getJSONObject(key).toString();
        }
//        System.out.println("Respuesta"+ res);
        return res;
    }

}
