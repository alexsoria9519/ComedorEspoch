/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedorui;

import EspochWS.Persona;
import Utilities.Utilidades;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import entities.Costo;
import entities.CostosUsuarios;
import entities.Costousuario;
import entities.Menus;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class VentaUI {

    private String html;
    private Gson gson = new Gson();

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String formulario(String respuestaJSON) {

        Date fechaActual = new Date();
        Utilidades utilidades = new Utilidades();

        JsonParser parser = new JsonParser();
        JsonElement elementoJson = parser.parse(respuestaJSON);
        JsonObject objJson = elementoJson.getAsJsonObject();

        Persona persona = new Persona();
        CostosUsuarios costosUsuarios = new CostosUsuarios();

        System.out.println(objJson.get("tipoUsuario").getAsString());

        persona = gson.fromJson(objJson.get("datosPersona").getAsString(), Persona.class);
        //costoUsuario = gson.fromJson(datosCostoUsuario, Costousuario.class);

        String form = "<h3> Registrar una venta </h3>"
                + "<p> Usted puede registrar los datos de una nueva venta </p>"
                + "                            <div class=\"col-lg-12\"> "
                + "                                <div class=\"col-lg-4 \"> "
                + "                                    <h4> Cédula: </h4>\n"
                + "                                    <p id=\"cedula\">" + objJson.get("cedula").getAsString() + "</p>\n"
                + "                                </div>\n"
                + "\n"
                + "                                <div class=\"col-lg-4\"> "
                + "                                    <h4> Nombres: </h4>\n"
                + "                                    <p> " + persona.getPer_nombres() + " </p>\n"
                + "                                </div>\n"
                + "                                <div class=\"col-lg-4\"> "
                + "                                    <h4> Apellidos: </h4>\n"
                + "                                    <p> " + persona.getPer_primerApellido() + " " + persona.getPer_segundoApellido() + " </p>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                            <div class=\"col-lg-12 \"> "
                + "                                <div class=\"form-group col-lg-4 \"> "
                + "                                    <h4> Tipo: </h4>\n"
                + "                                     <p id=\"tipoUsuario\"> " + objJson.get("tipoUsuario").getAsString() + " </p>\n"
                + "                                    "
                + "                                </div>\n"
                + "\n"
                + "                                <div class=\" col-lg-4\"> "
                + "                                    <h4> Fecha: </h4>\n"
                + "                                    <p> " + utilidades.fecha(fechaActual) + " </p>\n"
                + "                                </div>\n"
                + "                                <div class=\" col-lg-4\"> "
                + "                                    <h4> Cantidad: </h4>\n"
                + "                                    <p>  1  </p>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "</br>"
                + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                + "                            "
                + "\n"
                + "                            <div class=\"col-lg-12 form-inline \"> "
                + "                                <div class=\"form-group col-lg-6 \"> "
                + "                                    <label for=\"menu\">Menú: </label>\n";
        form += selectListadoMenus(objJson.get("listadoMenus").getAsString(), objJson);

        form += "                                </div>\n"
                + "                                <div class=\"form-group col-lg-6 \"> "
                + "                                    <label for=\"fecha\">Costo: </label>\n"
                + "                                    <input type=\"text\" readonly class=\"form-control\" id=\"costoUsuario\" name=\"costoUsuario\" value=\"" + datosCosto(objJson.get("datosCosto").getAsString()).toString() + "\" placeholder=\"2.35\"/>\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "\n"
                + "                            <div class=\"col-lg-12 form-inline \"> "
                + "                                <div class=\"form-group col-lg-6 \"> "
                + "                                    <button type=\"button\" class=\"btn  btn-success\" onclick=\"registrarVenta(event,'" + objJson.get("cedula").getAsString() + "','" + objJson.get("tipoUsuario").getAsString() + "'," + objJson.get("existeCostoUsuario").getAsBoolean() + ")\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> "
                + "                                </div>\n"
                + "\n"
                + "                                <div class=\"form-group col-lg-6\"> "
                + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> "
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </form>";
        return form;
        //return "<h3> Hola </h3>";
    }

    public String busquedaDatosCedula() {
        String form = "<h3> Registrar una venta </h3>"
                + "<p> Usted puede registrar los datos de una nueva venta </p>";

        form += "</br>"
                + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                + "                            "
                + "\n"
                + "                                <div class=\"form-group col-lg-12 \"> "
                + "                                    <label for=\"cedula\">Cédula:</label>\n"
                + "                                    <input  type=\"text\" class=\"form-control busquedaDatos\" id=\"cedula\" name=\"cedula\" placeholder=\"1234567890\" autofocus />\n"
                + "                                    "
                + "                                </div>\n"
                + "\n"
                + "                                <div class=\"form-group col-lg-12 \"> "
                + "                                    <button type=\"submit\" class=\"btn  btn-success\" onclick=\"formularioVenta(event)\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> "
                + "                                </div>\n"
                + "                        </form>";
        return form;
    }

    public String selectListadoMenus(String listadoMenus, JsonObject respuestaJSON) {

        try {
            String strlistadosMenus = "<select onchange=\"costos('" + respuestaJSON.get("tipoUsuario").getAsString() + "')\" id=\"listadosMenus\">\n";

            Menus menus = new Menus();

            menus = gson.fromJson(listadoMenus, Menus.class);

            for (int i = 0; i < menus.getMenus().size(); i++) {
                strlistadosMenus += "<option value=\"" + menus.getMenus().get(i).getIntidmenu().toString() + "\">" + menus.getMenus().get(i).getStrcaracteristicas() + "</option>\n";
            }

            return strlistadosMenus += "</select>\n";

        } catch (JsonSyntaxException | NullPointerException ex) {
            System.err.println("com.comedorui.VentaUI.selectListadoMenus()");
            System.err.println(ex);
        }
        return "<select> </select>";
    }

    private String TiposUsuarios(JsonObject respuestaJSON) {
        String tipoUsuario = "Funcionario";

        if (isJsonNotNull(respuestaJSON, "datosEstudiante")) {
            tipoUsuario = "Estudiante";
        } else if (isJsonNotNull(respuestaJSON, "datosDocente")) {
            tipoUsuario = "Docente";
        }
        return tipoUsuario;
    }

    private Boolean isJsonNotNull(JsonObject respuestaJSON, String key) {
        String datosJsonTiposUsuarios;
        try {
            datosJsonTiposUsuarios = respuestaJSON.get(key).getAsString();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Double datosCosto(String JSONCosto) {
        Costo costo = new Costo();
        try {
            costo = gson.fromJson(JSONCosto, Costo.class);
            return costo.getMnvalor();
        } catch (JsonSyntaxException ex) {
            System.err.println("com.comedorui.VentaUI.datosCosto()");
            System.err.println(ex);
        }
        return 0.0;
    }

}
