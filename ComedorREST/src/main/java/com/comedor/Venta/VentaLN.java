/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Venta;

import com.comedor.CostoUsuario.CostoUsuarioLN;
import com.comedor.entities.Venta;
import com.comedor.servicios.VentaWS;
import com.comedor.utilidades.BarCode;
import com.comedor.utilidades.Utilidades;
import com.google.gson.Gson;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author corebitsas
 */
public class VentaLN {

    VentaWS ventaWS = new VentaWS();
    JSONObject resJson = new JSONObject();
    Gson gson = new Gson();

    public String getDatosFormularioVenta(String cedula) {
        String resAll = "";
        CostoUsuarioLN costosusuarioLN = new CostoUsuarioLN();
        try {
            resAll = costosusuarioLN.costosUsuarioCedula(cedula);
            JSONObject resquestJson = new JSONObject(resAll);
            if (resquestJson.getString("success").equals("ok")) {
                resJson.put("success", "ok");
                resJson.put("costosUsuario", resquestJson.getString("costosUsuario"));
                resJson.put("tipoUsuario", resquestJson.getString("tipoUsuario"));
//                resJson.put("MostrarCostoBecado", mostrarCostoBecado(cedula, resquestJson.getString("tipoUsuario")));
            } else {
                resJson.put("success", "false");
                resJson.put("data", resquestJson.getString("data"));
                resJson.put("costosUsuario", "[]");
//                resJson.put("MostrarCostoBecado", "NO");
                resJson.put("tipoUsuario", "Desconocido");
            }
            resJson.put("datosUsuario", costosusuarioLN.datosPersona(cedula));
            resJson.put("cedula", cedula);

        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("costosUsuario", "[]");
            resJson.put("tipoUsuario", "");
//            resJson.put("MostrarCostoBecado", "NO");
            resJson.put("cedula", cedula);
        }
        return resJson.toString();
    }

    private String mostrarCostoBecado(String cedula, String tipoUsuario) {
        try {
            if (tipoUsuario.equals("Becado")) {
                Date fechaActual = new Date();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String strFecha = df.format(fechaActual);
                String resAll = ventaWS.cantidadTicketsUsuarioDiaBecado(strFecha, cedula);
                Integer data = Integer.parseInt(resAll);
                return ((data <= 0) ? "SI" : "NO");
            }

        } catch (Exception ex) {
        }
        return "NO";
    }

    public String insertVenta(String JSONVenta) {
        Utilidades utilidades = new Utilidades();

        try {
            JSONObject resquestJson = new JSONObject(JSONVenta);
            String dataVenta = utilidades.getDataJson(resquestJson, "venta");

            Venta venta = gson.fromJson(dataVenta, Venta.class);

            String resAll = ventaWS.insert(venta);

            JSONObject respIngreso = new JSONObject(resAll);
            if (respIngreso.getBoolean("ok")) {
                Integer idVenta = respIngreso.getInt("data");
                resJson.put("success", "ok");
                resJson.put("idVenta", idVenta);
                resJson.put("data", "Ingreso Correcto");
                resAll = ventaWS.find(String.class, idVenta.toString());
                datosVenta(resAll);
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Existe un error en el ingreso");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
        }
        return resJson.toString();
    }

    private void datosVenta(String JSONIngresoVenta) {
        CostoUsuarioLN costosusuarioLN = new CostoUsuarioLN();
        BarCode barCode = new BarCode();
        try {
            resJson.put("dataVenta", JSONIngresoVenta);
            Venta venta = gson.fromJson(JSONIngresoVenta, Venta.class);
            String resAll = costosusuarioLN.datosPersona(venta.getIntidcostousuario().getStrcedula());
            String qrImage = barCode.getQRCode(venta.getIntidventa() + "-" + venta.getIntidcostousuario().getStrcedula());
            resJson.put("datosUsuario", resAll);
            resJson.put("qrImage", qrImage);
        } catch (Exception ex) {
            resJson.put("dataVenta", "{}");
            resJson.put("datosUsuario", "{}");
            resJson.put("qrImage", "no image");
        }
    }

    public String getDatosVenta(Integer idVenta) {
        String resAll = "";
        try {
            resAll = ventaWS.find(String.class, idVenta.toString());
            if (!resAll.equals("{}")) {
                resJson.put("dataVenta", resAll);
                resJson.put("success", "ok");
            } else {
                resJson.put("dataVenta", "{}");
                resJson.put("success", "no existe");
                resJson.put("data", "No existen datos de la venta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Existe un error en el ingreso");
            resJson.put("dataVenta", "{}");

        }
        return resJson.toString();
    }

    public String getQRVenta(Integer idVenta) {
        String resAll = "";
        BarCode barCode = new BarCode();
        try {
            resAll = ventaWS.find(String.class, idVenta.toString());
            if (!resAll.equals("{}")) {

                Venta venta = gson.fromJson(resAll, Venta.class);
                String qrImage = barCode.getOnlyQRCode(venta.getIntidventa() + "-" + venta.getIntidcostousuario().getStrcedula());
                resJson.put("qrImage", qrImage);
                resJson.put("success", "ok");
            } else {
                resJson.put("qrImage", "");
                resJson.put("success", "no existe");
                resJson.put("data", "No existen datos de la venta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Existe un error al obtener el código QR");
            resJson.put("qrImage", "");

        }
        return resJson.toString();
    }

}
