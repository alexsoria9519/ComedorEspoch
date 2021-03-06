/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedorui;

import Utilities.Utilidades;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import entities.Costo;
import entities.Costos;
import entities.TipoMenus;
import entities.TipoUsuarios;
import org.json.JSONObject;

/**
 *
 * @author Alex
 */
public class CostoUI {

    private String html;
    private Gson gson = new Gson();

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void listaCostos() {

    }

    private String opcionesBotones(String accion, Integer id) {
        String boton = "";

        if (accion.equals("editar")) {
            boton = "<button type='button' class='btn btn-warning' data-id='" + id + "' onclick='edicion(event," + id + " )'> <i class='fa fa-edit'></i> Editar </button>";
        } else if (accion.equals("eliminar")) {
            boton = "<button type='button' class='btn btn-danger' data-id='" + id + "' onclick='eliminar(event," + id + ")'> <i class='fa fa-trash'></i> Eliminar</button>";
        } else if (accion.equals("activar")) {
            boton = "<button type='button' class='btn btn-success' data-id='" + id + "' onclick='activar(event," + id + ")'> <i class='far fa-check-circle'></i> Activar</button>";
        } else if (accion.equals("desactivar")) {
            boton = "<button type='button' class='btn btn-primary' data-id='" + id + "' onclick='desactivar(event," + id + ")'> <i class='fas fa-ban'></i> Desactivar</button>";
        }
        return boton;
    }

    private String ingresoBoton() {
        String button = "<div class=\"col-12\">\n"
                + "                            <button class=\"btn btn-primary\" onclick=\"formulario(event)\"> Ingresar Datos  </button>\n"
                + "                        </div>\n <br>";
        return button;
    }

    public String formulario(String respuestaJSON) {

        String form = "<h3> Registrar un Costo </h3>"
                + "<p> Usted puede registrar los datos de un nuevo costo </p>";

        try {

            JSONObject respJson = new JSONObject(respuestaJSON);

            form += "</br>"
                    + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                    + "                            \n"
                    + "\n"
                    + "                            <div class=\"col-lg-12 form-inline \"> \n"
                    + "                                <div class=\"form-group col-lg-6 \"> \n"
                    + "                                    <label for=\"detalle\">Detalle:</label>\n"
                    + "                                    <textarea id=\"detalle\"  name=\"detalle\" onkeyup=\"mensajeDetalle()\" placeholder=\"Costo de un almuerzo para estudiante\"></textarea>\n"
                    + "                                    \n"
                    + "                                <div class=\"validation\" id=\"detallemensaje\"> </div>\n"
                    + "                                </div>\n"
                    + "\n"
                    + "                                <div class=\"form-group col-lg-6\"> \n"
                    + "                                    <label for=\"valor\">Valor </label>\n"
                    + "                                    <input type=\"text\" data-validation=\"required\" class=\"form-control\" id=\"valor\" name=\"valor\" onkeyup=\"mensajeValor()\" placeholder=\"1.60\"/ >\n"
                    + "                                <div class=\"validation\" id=\"valormensaje\"> </div> \n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "\n"
                    + "                            <div class=\"col-lg-12 form-inline \"> \n"
                    + "                                <div class=\"form-group col-lg-6 \"> \n"
                    + "                                    <label for=\"fecha\">Fecha: </label>\n"
                    + "                                    <input type=\"text\"  class=\"form-control datepicker\" id=\"fecha\" name=\"fecha\" onkeyup=\"mensajeFecha()\"  placeholder=\"02/05/2019\"/>\n"
                    + "                                <div class=\"validation\" id=\"fechamensaje\"> </div>\n"
                    + "                                </div>\n"
                    + "\n";
            /*
                + "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <label for=\"estado\">Estado </label>\n"
                + "                                    <select  id=\"estado\">\n"
                + "                                        <option value=\"true\">Activo</option>     \n"
                + "                                        <option value=\"false\">Inactivo</option>     \n"
                + "                                    </select>\n"
                + "                                </div>\n"
             */

            if (respJson.getString("success").equals("ok")) {
                form += selectTipoMenus(respJson.getString("dataTipoMenus"), -1);

                form += "                            <div class=\"col-lg-12 form-inline \"> \n";
                form += selectTipoUsuarios(respJson.getString("dataTipoUsuarios"), -1);
                form += "                                </div>\n";
            }

            form += "                            </div>\n"
                    + "\n"
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
        } catch (Exception ex) {
            System.err.println("com.comedorui.CostoUI.formulario() " + ex);
        }

        return form;
    }

    private String selectTipoMenus(String jsonTiposMenus, Integer idSeleccionado) {

        TipoMenus tipoMenus = new TipoMenus();
        String selected;
        String select = "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <label for=\"tipoMenu\">Tipo de Men?? </label>\n"
                + "                                    <select  id=\"tipoMenu\">\n";

        try {

            JSONObject respJson = new JSONObject(jsonTiposMenus);
            if (respJson.getString("success").equals("ok")) {

                tipoMenus = gson.fromJson("{ \"tipoMenus\" : " + respJson.getString("tiposMenus") + " }", TipoMenus.class);
                for (int i = 0; i < tipoMenus.getTipoMenus().size(); i++) {

                    if (idSeleccionado > -1 && idSeleccionado == tipoMenus.getTipoMenus().get(i).getIntidtipo()) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }
                    select += "<option value=\"" + tipoMenus.getTipoMenus().get(i).getIntidtipo() + "\"  " + selected + " > " + tipoMenus.getTipoMenus().get(i).getStrtipo() + " </option>     \n";
                }

            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.CostoUI.selectTipoMenus() " + ex);
        }

        return select += "                                    </select>\n"
                + "                                </div>\n";
    }

    private String selectTipoUsuarios(String jsonTiposUsuarios, Integer idSeleccionado) {

        TipoUsuarios tipoUsuarios = new TipoUsuarios();
        String selected;
        String select = "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <label for=\"tipoUsuario\">Tipo de Usuario </label>\n"
                + "                                    <select  id=\"tipoUsuario\">\n";

        try {
            JSONObject respJson = new JSONObject(jsonTiposUsuarios);

            if (respJson.get("success").equals("ok")) {
                tipoUsuarios = gson.fromJson("{ \"tipoUsuarios\" : " + respJson.getString("tiposUsuarios") + " }", TipoUsuarios.class);

                for (int i = 0; i < tipoUsuarios.getTipoUsuarios().size(); i++) {

                    if (idSeleccionado > -1 && idSeleccionado == tipoUsuarios.getTipoUsuarios().get(i).getIntidtipo()) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }
                    select += "<option value=\"" + tipoUsuarios.getTipoUsuarios().get(i).getIntidtipo() + "\"  " + selected + " > " + tipoUsuarios.getTipoUsuarios().get(i).getStrtipo() + " </option>     \n";
                }
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.CostoUI.selectTipoUsuarios() " + ex);
        }

        return select += "                                    </select>\n"
                + "                                </div>\n";
    }

    public String listadoCostos(String listadoJSON, String respuestaJSON) {

        String listado = "[\n";

        Utilidades utilidades = new Utilidades();

        try {

            if (utilidades.validarError(respuestaJSON)) {

                Costos costos = new Costos();

                JSONObject respJson = new JSONObject(listadoJSON);

                costos = gson.fromJson("{ \"costos\" : " + respJson.getString("costos") + " }", Costos.class);

                for (int i = 0; i < costos.getCostos().size(); i++) {

                    listado += "    [ \" " + costos.getCostos().get(i).getStrdetalle() + "\", "
                            + "\" $" + costos.getCostos().get(i).getMnvalor() + "\", "
                            + "\" " + utilidades.fecha(costos.getCostos().get(i).getDtfecha()) + "\", "
                            + "\" " + costos.getCostos().get(i).getIntidtipomenu().getStrtipo() + "\", "
                            + "\" " + costos.getCostos().get(i).getIntidtipousuario().getStrtipo() + "\", ";
                    listado += htmlBotones(costos, i);

                }
                respJson.put("listado", listado += "]");

                return respJson.toString();
            } else {
                return respuestaJSON;
            }

        } catch (JsonSyntaxException | NullPointerException ex) {
            System.err.println("com.comedorui.CostoUI.listadoCostos() " + ex);
        }
        return listado += "]";

    }

    private String htmlBotones(Costos costos, Integer i) {
        String botones = "";

        if (costos.getCostos().get(i).getBlnestado()) {
            if (i != costos.getCostos().size() - 1) {
                botones += "\"" + opcionesBotones("editar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("eliminar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("desactivar", costos.getCostos().get(i).getIntidcosto())
                        + "\"],";
            } else {
                botones += "\"" + opcionesBotones("editar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("eliminar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("desactivar", costos.getCostos().get(i).getIntidcosto())
                        + "\"]";
            }
        } else {
            if (i != costos.getCostos().size() - 1) {
                botones += "\"" + opcionesBotones("editar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("eliminar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("activar", costos.getCostos().get(i).getIntidcosto())
                        + "\"],";
            } else {
                botones += "\"" + opcionesBotones("editar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("eliminar", costos.getCostos().get(i).getIntidcosto())
                        + opcionesBotones("activar", costos.getCostos().get(i).getIntidcosto())
                        + "\"]";
            }
        }
        return botones;
    }

    public String formularioEdicion(String respuestaJSON) {
        Utilidades utilidades = new Utilidades();
        Costo costo = new Costo();

        String form = "<h3> Modificar datos del costo </h3>"
                + "<p> Usted puede modificar los datos del costo </p>";

        try {

            JSONObject respJson = new JSONObject(respuestaJSON);

            if (respJson.getString("success").equals("ok")) {
                JSONObject costoJSON = new JSONObject(respJson.getString("dataCosto"));
                costo = gson.fromJson(costoJSON.getString("Costo"), Costo.class);

                form += "</br>"
                        + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\" data-id=\"" + costo.getIntidcosto() + "\">\n"
                        + "                            \n"
                        + "\n"
                        + "                            <div class=\"col-lg-12 form-inline \"> \n"
                        + "                                <div class=\"form-group col-lg-6 \"> \n"
                        + "                                    <label for=\"detalle\">Detalle:</label>\n"
                        + "                                    <textarea id=\"detalle\" name=\"detalle\" onkeyup=\"mensajeDetalle()\" placeholder=\"Costo de un almuerzo para estudiante\">" + costo.getStrdetalle() + "</textarea>\n"
                        + "                                    \n"
                        + "                                <div class=\"validation\" id=\"detallemensaje\"> </div>\n"
                        + "                                </div>\n"
                        + "\n"
                        + "                                <div class=\"form-group col-lg-6\"> \n"
                        + "                                    <label for=\"valor\">Valor </label>\n"
                        + "                                    <input type=\"text\" class=\"form-control\" id=\"valor\" name=\"valor\" onkeyup=\"mensajeValor()\" placeholder=\"1.60\"/ value=\"" + costo.getMnvalor() + "\">\n"
                        + "                                <div class=\"validation\" id=\"valormensaje\"> </div> \n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "\n"
                        + "                            <div class=\"col-lg-12 form-inline \"> \n"
                        + "                                <div class=\"form-group col-lg-6 \"> \n"
                        + "                                    <label for=\"fecha\">Fecha: </label>\n"
                        + "                                    <input type=\"text\" class=\"form-control datepicker\" id=\"fecha\" onkeyup=\"mensajeFecha()\" name=\"fecha\" placeholder=\"02/05/2019\"/ value=\"" + utilidades.fechaFormularioEdicion(costo.getDtfecha()) + "\">\n"
                        + "                                <div class=\"validation\" id=\"fechamensaje\"> </div>\n"
                        + "                                </div>\n"
                        + "\n"
                        + "                                <div class=\"form-group col-lg-6\"> \n"
                        + "                                    <label for=\"estado\">Estado </label>\n"
                        + "                                    <select id=\"estado\">\n";
                if (costo.getBlnestado()) {
                    form += " <option value=\"true\" selected>Activo</option>     \n";
                    form += " <option value=\"false\">Inactivo</option>     \n";
                } else {
                    form += " <option value=\"true\">Activo</option>     \n";
                    form += " <option value=\"false\" selected>Inactivo</option>     \n";

                }

                form += "                                    </select>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "\n"
                        + "                                                       \n";

                form += "                            <div class=\"col-lg-12 form-inline \"> \n";
                form += selectTipoMenus(respJson.getString("dataTipoMenus"), costo.getIntidtipomenu().getIntidtipo());
                form += selectTipoUsuarios(respJson.getString("dataTipoUsuarios"), costo.getIntidtipousuario().getIntidtipo());
                form += "                                </div>\n";

                form += "                            <div class=\"col-lg-12 form-inline \"> \n"
                        + "                                <div class=\"form-group col-lg-6 \"> \n"
                        + "                                    <button type=\"submit\" class=\"btn  btn-success\" onclick=\"editar(event, " + costo.getIntidcosto() + ")\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> \n"
                        + "                                </div>\n"
                        + "\n"
                        + "                                <div class=\"form-group col-lg-6\"> \n"
                        + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> \n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </form>";

            }
        } catch (JsonSyntaxException | NullPointerException ex) {
            System.err.println("com.comedorui.CostoUI.formularioEdicion() " + ex);
        }
        return form;
    }

}
