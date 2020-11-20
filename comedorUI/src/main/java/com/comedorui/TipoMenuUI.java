/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedorui;

import Utilities.Utilidades;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entities.TipoMenus;
import entities.Tipomenu;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author corebitsas
 */
public class TipoMenuUI {

    private String opcionesBotones(String accion, Integer id) {
        String boton = "";

        if (accion.equals("editar")) {
            boton = "<button type='button' class='btn btn-warning' data-id='" + id + "' onclick='edicion(event," + id + " )'> <i class='fa fa-edit'></i> Editar </button>";
        } else {
            boton = "<button type='button' class='btn btn-danger' data-id='" + id + "' onclick='eliminar(event," + id + ")'> <i class='fa fa-trash'></i> Eliminar</button>";
        }
        return boton;
    }

    private String ingresoBoton() {
        String button = "<div class=\"col-12\">\n"
                + "                            <button class=\"btn btn-primary\" onclick=\"formulario(event)\"> Ingresar Datos  </button>\n"
                + "                        </div>\n <br>";
        return button;
    }

    public String formulario() {

        String form = "<h3> Registrar un Tipo de Menu </h3>"
                + "<p> Usted puede registrar los datos de un nuevo tipo de menu </p>";

        form += "</br>"
                + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                + "                            \n"
                + "\n"
                + "                            <div class=\"col-lg-12 form-inline \"> \n"
                + "                                    <label for=\"tipo\">Tipo:</label>\n"
                + "                                    <textarea id=\"tipo\" name=\"tipo\" onkeyup=\"mensajeTipo()\" placeholder=\"Almuerzo\"></textarea>\n"
                + "                                <div class=\"validation\" id=\"tipomensaje\"> </div>\n"
                + "                            </div>\n"
                + "                                                       \n"
                + "                            <div class=\"col-lg-12 form-inline \"> \n"
                + "                                <div class=\"form-group col-lg-6 \"> \n"
                + "                                    <button type=\"submit\" class=\"btn  btn-success\" onclick=\"ingreso(event)\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> \n"
                + "                                </div>\n"
                + "\n"
                + "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> \n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </form>";
        return form;
    }

    public String listadoTiposMenus(String listadoJSON, String respuestaJSON) {

        String listado = "[\n";
        Gson gson = new Gson();

        Utilidades utilidades = new Utilidades();

        try {

            if (utilidades.validarError(respuestaJSON)) {
                JSONObject respJson = new JSONObject(listadoJSON);

                TipoMenus tipoMenus = new TipoMenus();
                tipoMenus = gson.fromJson("{ \"tipoMenus\" : " + respJson.getString("tiposMenus") + " }", TipoMenus.class);

                for (int i = 0; i < tipoMenus.getTipoMenus().size(); i++) {
                    listado += "    [\"" + tipoMenus.getTipoMenus().get(i).getStrtipo() + "\",";
                    if (i != tipoMenus.getTipoMenus().size() - 1) {
                        listado += "\"" + opcionesBotones("editar", tipoMenus.getTipoMenus().get(i).getIntidtipo()) + opcionesBotones("eliminar", tipoMenus.getTipoMenus().get(i).getIntidtipo()) + "\"], ";
                    } else {
                        listado += "\"" + opcionesBotones("editar", tipoMenus.getTipoMenus().get(i).getIntidtipo()) + opcionesBotones("eliminar", tipoMenus.getTipoMenus().get(i).getIntidtipo()) + "\"] ";
                    }
                }
                respJson.put("listado", listado += "]");
                return respJson.toString();
            } else {
                return respuestaJSON;
            }

        } catch (JsonSyntaxException | JSONException | NullPointerException ex) {
            System.err.println("com.comedorui.TipoMenuUI.listadoCostos() " + ex);
        }

        return listado += "]";

    }

    public String formularioEdicion(String tipoMenuJSON) {

        Gson gson = new Gson();
        Tipomenu tipoMenu = new Tipomenu();

        String form = "<h3> Modificar datos del tipo de menu</h3>"
                + "<p> Usted puede modificar los datos del tipo de menu </p>";

        form += "</br>"
                + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\" >\n";

        try {

            JSONObject respJson = new JSONObject(tipoMenuJSON);

            if (respJson.getString("success").equals("ok")) {

                tipoMenu = gson.fromJson(respJson.getString("tipoMenu"), Tipomenu.class);
                form += "                            \n"
                        + "\n"
                        + "                            <div class=\"col-lg-12 form-inline \"> \n"
                        + "                                    <label for=\"tipo\">Detalle:</label>\n"
                        + "                                    <textarea id=\"tipo\" name=\"tipo\" onkeyup=\"mensajeTipo()\" placeholder=\"Almuerzo\">" + tipoMenu.getStrtipo() + "</textarea>"
                        + "\n"
                        + "                                <div class=\"validation\" id=\"tipomensaje\"> </div>\n"
                        + "                            </div>\n"
                        + "                            <div class=\"col-lg-12 form-inline \"> \n"
                        + "                                <div class=\"form-group col-lg-6 \"> \n"
                        + "                                    <button type=\"submit\" class=\"btn  btn-success\" onclick=\"editar(event, " + tipoMenu.getIntidtipo() + ")\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> \n"
                        + "                                </div>\n"
                        + "\n"
                        + "                                <div class=\"form-group col-lg-6\"> \n"
                        + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> \n"
                        + "                                </div>\n"
                        + "                            </div>\n";
            } else {
                form += "                            \n"
                        + "\n"
                        + "\n"
                        + "                                <div class=\"form-group col-lg-6\"> \n"
                        + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> \n"
                        + "                                </div>\n"
                        + "                            </div>\n";
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.TipoMenuUI.formularioEdicion() " + ex);
        }

        return form;
    }
}
