/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln;

import java.io.Serializable;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class Response implements Serializable {

    private String ok;
    private String data;
    private String error;

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void getResponse(JSONObject JSONResponse, String identificador, String data) {
        if (identificador.equals("ok")) {
            JSONResponse.remove("error");
            JSONResponse.put("ok", true);
            JSONResponse.put("data", data);
        } else {
            JSONResponse.put("ok", false);
            JSONResponse.remove("data");
            JSONResponse.put("error", data);
        }

    }

}
