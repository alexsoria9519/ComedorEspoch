/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedorui;

import Utilities.Utilidades;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entities.Planificacionmenu;
import entities.Menu;
import entities.Menus;
import entities.TipoMenus;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import entities.PlanificacionMenus;
import org.json.JSONObject;

/**
 *
 * @author Alex
 */
public class MenuUI {

    private Gson gson = new Gson();
    private String listado;

    public MenuUI() {
    }

    private String opcionesBotones(String accion, Integer id, Integer idFechas) {
        String boton = "";

        if (accion.equals("editar")) {
            boton = "<button type='button' class='btn btn-warning' data-id='" + id + "' onclick='edicion(event," + id + " )'> <i class='fa fa-edit'></i> Editar </button>";
        } else if (accion.equals("eliminar")) {
            boton = "<button type='button' class='btn btn-danger' data-id='" + id + "' onclick='eliminar(event," + id + "," + idFechas + ")'> <i class='fa fa-trash'></i> Eliminar</button>";
        } else if (accion.equals("crearPlanificacion")) {
            boton = "<button type='button' class='btn btn-success' data-id='" + id + "' onclick='formularioActivarMenu(event," + id + ")'> <i class='fa fa-calendar'></i> Planificación</button>";
        } else if (accion.equals("desactivarMenu")) {
            boton = "<button type='button' class='btn btn-danger' data-id='" + id + "' onclick='cambiarEstadoMenu(event," + id + ", false)'> <i class='fa fa-times'></i> Desactivar</button>";
        } else if (accion.equals("desactivarPlanificacion")) {
            boton = "<button type='button' class='btn btn-danger' data-id='" + id + "' onclick='desactivarPlanificacion(event," + idFechas + ")'> <i class='fa fa-times'></i> Desactivar</button>";
        } else if (accion.equals("desactivarPlanificacionInfoMenu")) {
            boton = "<button type='button' class='btn btn-danger' data-id='" + id + "' onclick='desactivarPlanificacionInfo(event," + idFechas + ", " + id + ")'> <i class='fa fa-times'></i> Desactivar</button>";
        } else if (accion.equals("activarMenu")) {
            boton = "<button type='button' class='btn btn-success' data-id='" + id + "' onclick='cambiarEstadoMenu(event," + id + ", true)'> <i class='fa fa-check-circle'></i> Activar</button>";
        }
        return boton;
    }

    public String formulario(String respuestaJSON) {

        String form = "<h3> Registrar un Men&#250; </h3>"
                + "<p> Usted puede registrar los datos de un nuevo men&#250; </p>";
        try {
            JSONObject respJson = new JSONObject(respuestaJSON);

            form += "</br>"
                    + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                    + "                            \n"
                    + "\n"
                    + "                            <div class=\"col-lg-12 form-inline \"> \n"
                    + "                                <div class=\"form-group col-lg-6 \"> \n"
                    + "                                    <label for=\"caracteristicas\">Características   :</label>\n"
                    + "                                    <textarea id=\"caracteristicas\"  name=\"caracteristicas\" onkeyup=\"mensajeCaracteristicas()\" placeholder=\"Sopa de pollo, seco de carne, jugo de naranja y fruta\"></textarea>\n"
                    + "                                    \n"
                    + "                                <div class=\"validation\" id=\"caracteristicasmensaje\"> </div>\n"
                    + "                                </div>\n"
                    + "\n";

            form += selectTiposMenu(respJson.getString("listadotiposmenu"), -1);

            form += "                            </div>\n"
                    + "                                 <div class='form-check'>\n"
                    + "                                     <label class='form-check-label' for='planificacionCheck'>Ingresar Planificación</label>\n"
                    + "                                     <input type='checkbox' class='form-check-input' id='planificacionCheck' onchange='toggleCheckboxPlanificacion(this)'>\n"
                    + "                                 </div>"
                    + "                            <div id='inputPlanificacion' class=\"col-lg-12 form-inline \"> \n <br/>"
                    + "<div class=\"col-lg-6\">\n         <label>Fechas disponibles del menú:</label>\n  </div>"
                    + "<div class=\"form-group col-lg-6 input-daterange input-group\" id=\"datepicker\">\n"
                    + "    <input type=\"text\" class=\"input-sm form-control\" id=\"fechaInicio\" onkeyup=\"mensajeFechaInicio()\"  onchange=\"mensajeFechaInicio()\" name=\"from\" placeholder=\"Desde\"/>\n"
                    + "    <span class=\"input-group-addon\">a</span>\n"
                    + "    <input type=\"text\" class=\"input-sm form-control\" id=\"fechaFin\" name=\"fechaFin\" onkeyup=\"mensajeFechaFin()\" onchange=\"mensajeFechaFin()\" placeholder=\"Hasta\"/>\n"
                    + "</div>"
                    + "                                <div class=\"validation\" id=\"fechasmensaje\"> </div>\n"
                    + "                                </div>\n"
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

        }

        return form;

    }

    private String selectTiposMenu(String jsonTiposMenus, Integer idTipoMenu) {
//        Gson gson = new Gson();
        String selected;
        TipoMenus tipoMenus = new TipoMenus();
        String select = "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <label for=\"tipoMenu\">Tipo de Menú </label>\n"
                + "                                    <select  id=\"tipoMenu\">\n";
        try {

            JSONObject respJson = new JSONObject(jsonTiposMenus);
            if (respJson.getString("success").equals("ok")) {

                tipoMenus = gson.fromJson("{ \"tipoMenus\" : " + respJson.getString("tiposMenus") + " }", TipoMenus.class);
                for (int i = 0; i < tipoMenus.getTipoMenus().size(); i++) {

                    if (Objects.equals(idTipoMenu, tipoMenus.getTipoMenus().get(i).getIntidtipo())) {
                        selected = "selected";
                    } else {
                        selected = "";
                    }
                    select += "<option value=\"" + tipoMenus.getTipoMenus().get(i).getIntidtipo() + "\"  " + selected + " > " + tipoMenus.getTipoMenus().get(i).getStrtipo() + " </option>     \n";
                }
            }
        } catch (JsonSyntaxException | NullPointerException ex) {
            System.err.println("com.comedorui.MenuUI.selectTiposMenu()");
            System.err.println(ex);
        }
        return select += "                                    </select>\n"
                + "                                </div>\n";
    }

//    public String listadoMenus(String listadoJSON) {
//
//        Menus menus = new Menus();
//
//        try {
//            listado = "[\n";
//
//            menus = gson.fromJson(listadoJSON, Menus.class);
//            for (int i = 0; i < menus.getMenus().size(); i++) {
//                if (menus.getMenus().get(i).getBlnestado()) {
//                    listado += "    [ \" " + menus.getMenus().get(i).getStrcaracteristicas() + "\", "
//                            + "\"" + menus.getMenus().get(i).getIntidtipo().getStrtipo() + "\", "
//                            + "\"" + opcionesBotones("activar", menus.getMenus().get(i).getIntidmenu(), 0) + "\", "
//                            + "\"" + opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0)
//                            + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i)))
//                            + "\"],";
//                }
//            }
//            listado = listado.substring(0, listado.length() - 1);
//        } catch (JsonSyntaxException | NullPointerException ex) {
//            System.out.println("com.comedorui.MenuUI.listadoMenus()");
//            System.err.println(ex);
//        }
//        return listado += "]";
//
//    }
    public String listadoMenus(String listadoJSON, String respuestaJSON) {

        Utilidades utilidades = new Utilidades();
        String listado = "[\n";
        try {

            Menus menus = new Menus();

            if (utilidades.validarError(respuestaJSON)) {
                menus = gson.fromJson("{ \"menus\" : " + listadoJSON + " }", Menus.class);
                for (int i = 0; i < menus.getMenus().size(); i++) {
                    listado += "    [ \" " + menus.getMenus().get(i).getStrcaracteristicas() + "\", "
                            + "\"" + menus.getMenus().get(i).getIntidtipomenu().getStrtipo() + "\", ";

                    if (menus.getMenus().get(i).getBlnestado()) {
                        listado += "\"" + opcionesBotones("crearPlanificacion", menus.getMenus().get(i).getIntidmenu(), 0) + "\", ";
                    } else {
                        listado += "\" \",";
                    }
                    listado += htmlBotones(menus, i);
                }

                return listado += "]";
            } else {
                return respuestaJSON;
            }
        } catch (JsonSyntaxException | NullPointerException ex) {
            System.out.println("com.comedorui.MenuUI.listadoMenus() " + ex);
        }
        return listado += "]";
    }

    private String htmlBotones(Menus menus, Integer i) {
        String botones = "";
        if (menus.getMenus().get(i).getBlnestado()) {
            botones += "\"" + opcionesBotones("desactivarMenu", menus.getMenus().get(i).getIntidmenu(), i);
        } else {
            botones += "\"" + opcionesBotones("activarMenu", menus.getMenus().get(i).getIntidmenu(), i);
        }

        if (i != menus.getMenus().size() - 1) {
            botones += opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0) + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"], ";
        } else {
            botones += opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0) + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"] ";
        }
        return botones;
    }

    public String listadoMenusFechasActivos(String listadoFechas, String respuestaJSON) {
        Utilidades utilidades = new Utilidades();
        String listado = "[\n";
        try {

            if (utilidades.validarError(respuestaJSON)) {

                PlanificacionMenus planificacionMenus = gson.fromJson("{ \"planificacionMenus\" : " + listadoFechas + " }", PlanificacionMenus.class);
                System.err.println("Lista de Menus Activos " + listadoFechas);
                System.err.println("Size lista de Menus Activos " + planificacionMenus.getPlanificacionMenus().size());
                for (int i = 0; i < planificacionMenus.getPlanificacionMenus().size(); i++) {
                    listado += "    [ \" " + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getStrcaracteristicas() + "\", "
                            + "\"" + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidtipomenu().getStrtipo() + "\", "
                            + "\"" + fechasMenu(planificacionMenus.getPlanificacionMenus().get(i)) + "\", "
                            + "\"" + diasUnaFecha(planificacionMenus.getPlanificacionMenus().get(i)) + "\", "
                            //+ "\"" + opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0) + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"],";
                            + htmlBotonesPlanificacion(planificacionMenus, i);
                }

                return listado += "]";
            } else {
                return respuestaJSON;
            }
        } catch (JsonSyntaxException | NullPointerException ex) {
            System.out.println("com.comedorui.MenuUI.listadoMenus() " + ex);
        }
        return listado += "]";
    }

//    private String menusActivos(String JSONPlanificacion) {
//        listado = "[\n";
//        try {
//            JSONObject respJson = new JSONObject(JSONPlanificacion);
//            if (respJson.getString("success").equals("ok")) {
//                PlanificacionMenus planificacionMenus = gson.fromJson("{ \"planificionMenus\" : " + respJson.getString("planificacionesMenu") + " }", PlanificacionMenus.class);
//                if (respJson.getInt("cantidad") > 0) {
//                    for (int i = 0; i < planificacionMenus.getPlanificacionMenus().size(); i++) {
//                        listado += "    [ \" " + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getStrcaracteristicas() + "\", "
//                                + "\"" + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidtipomenu().getStrtipo() + "\", "
//                                + "\"" + fechasMenu(planificacionMenus.getPlanificacionMenus().get(i)) + "\", "
//                                + "\"" + diasUnaFecha(planificacionMenus.getPlanificacionMenus().get(i)) + "\", "
//                                //+ "\"" + opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0) + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"],";
//                                + htmlBotonesPlanificacion(planificacionMenus, i);
//                    }
//                } else {
//                    return "[\n]";
//                }
//            } else {
//                return "error";
//            }
//        } catch (Exception ex) {
//            System.err.println("com.comedorui.MenuUI.menusActivos()");
//            System.err.println(ex);
//        }
//
//        return listado += "]";
//    }
    private String htmlBotonesPlanificacion(PlanificacionMenus planificacionMenus, Integer i) {
        String botones = "";

        if (i != planificacionMenus.getPlanificacionMenus().size() - 1) {
            botones += "\"" + opcionesBotones("desactivarPlanificacion", planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidmenu(), planificacionMenus.getPlanificacionMenus().get(i).getIntid()) + "\"],";
        } else {
            botones += "\"" + opcionesBotones("desactivarPlanificacion", planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidmenu(), planificacionMenus.getPlanificacionMenus().get(i).getIntid()) + "\"]";
        }
        return botones;
    }

    private Boolean menuRangoFechas(Menu menu, PlanificacionMenus planificacionMenusActivos) {
        Boolean fechaActiva = false;

        List<Planificacionmenu> listaFechasMenu = new ArrayList<>(menu.getPlanificacionmenuCollection());
        try {
            for (int i = 0; i < listaFechasMenu.size(); i++) { // Lista de fechas del menu
                for (int j = 0; j < planificacionMenusActivos.getPlanificacionMenus().size(); j++) { // lista de fechas activas
                    if (Objects.equals(planificacionMenusActivos.getPlanificacionMenus().get(j).getIntid(), listaFechasMenu.get(i).getIntid())) {
                        fechaActiva = true;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.MenuUI.menuRangoFechas()");
            System.err.println(ex);
        }
        return fechaActiva;
    }

    private String fechasMenu(Planificacionmenu planificacionMenu) {
        String fechasString = "";
        Utilidades utilidades = new Utilidades();

        if (planificacionMenu.getDtfechafin() == null && planificacionMenu.getDtfechafin() == null) {
            fechasString = "No tiene Fechas";
        } else {
            fechasString = utilidades.fecha(planificacionMenu.getDtfechainicio()) + " - " + utilidades.fecha(planificacionMenu.getDtfechafin());
        }
        return fechasString;
    }

    private Date getFechasInicioFin(Menu menu, String identificador) {
        Date fecha = new Date();

        try {
            List<Planificacionmenu> listaFechas = new ArrayList<>(menu.getPlanificacionmenuCollection());

            if (!listaFechas.isEmpty()) {

                if (identificador.equals("Inicio")) {
                    fecha = listaFechas.get(listaFechas.size() - 1).getDtfechainicio();
                } else {
                    fecha = listaFechas.get(listaFechas.size() - 1).getDtfechafin();
                }
            }
        } catch (Exception ex) {
            System.out.println("com.comedorui.MenuUI.getFechasInicioFin()");
            System.err.println(ex);
        }
        return fecha;
    }

    private Integer getIdFechasMenu(Menu menu) {
        Integer id = 0;

        try {
            List<Planificacionmenu> listaFechas = new ArrayList<>(menu.getPlanificacionmenuCollection());

            if (!listaFechas.isEmpty()) {

                id = listaFechas.get(listaFechas.size() - 1).getIntid();

            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.MenuUI.getIdFechasMenu()");
            System.err.println(ex);
        }
        return id;
    }

    private String diasUnaFecha(Planificacionmenu planificacionMenu) {
        String fechasString = "";

        try {
            if (planificacionMenu == null || (planificacionMenu.getDtfechainicio() == null && planificacionMenu.getDtfechafin() == null)) {
                fechasString = "0 Días";
            } else {
                fechasString = Dias(planificacionMenu.getDtfechainicio(), planificacionMenu.getDtfechafin()).toString()
                        + " Días";
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.MenuUI.diasUnaFecha()");
            System.err.println(ex);
        }
        return fechasString;
    }

    public List<Date> getListaEntreFechas(Date fechaInicio, Date fechaFin) {
        try {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(fechaInicio);
            Calendar c2 = Calendar.getInstance();
            c2.setTime(fechaFin);
            List<Date> listaFechas = new ArrayList<>();
            while (!c1.after(c2)) {
                listaFechas.add(c1.getTime());
                c1.add(Calendar.DAY_OF_MONTH, 1);
            }
            return listaFechas;
        } catch (Exception ex) {
            System.err.println("com.comedorui.MenuUI.getListaEntreFechas()");
            System.err.println(ex);
        }
        return new ArrayList<>();
    }

    public Integer Dias(Date fechaInicio, Date FechaFin) {
        Integer numeroDias = 0;
        Utilidades utilidades = new Utilidades();
        List<Date> listaEntreFechas = new ArrayList<>();
        try {
            listaEntreFechas = getListaEntreFechas(fechaInicio, FechaFin);
            for (Date date : listaEntreFechas) {
                if (utilidades.diaHabil(date)) {
                    numeroDias++;
                }

            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.MenuUI.Dias()");
            System.err.println(ex);
        }
        return numeroDias;
    }

    public String formularioEdicion(String respuestaJSON) throws ParseException {

        Utilidades utilidades = new Utilidades();
        JSONObject respuesta = new JSONObject();

        try {

            if (utilidades.validarError(respuestaJSON)) {
                JSONObject resJSONMenu = new JSONObject(respuestaJSON);
                String form = "<h3> Modificar un Men&#250; </h3>"
                        + "<p> Usted puede modificar los datos de un nuevo men&#250; </p>";

                Menu menu = gson.fromJson(resJSONMenu.getString("menu"), Menu.class);
//            List<Planificacionmenu> listaFechas = new ArrayList<>(menu.getPlanificacionmenuCollection());

                form += "</br>"
                        + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                        + "                            \n"
                        + "\n"
                        + "                            <div class=\"col-lg-12 form-inline \"> \n"
                        + "                                <div class=\"form-group col-lg-6 \"> \n"
                        + "                                    <label for=\"caracteristicas\">Características   :</label>\n"
                        + "                                    <textarea id=\"caracteristicas\"  name=\"caracteristicas\" onkeyup=\"mensajeCaracteristicas()\" placeholder=\"Costo de un almuerzo para estudiante\">" + menu.getStrcaracteristicas() + "</textarea>\n"
                        + "                                    \n"
                        + "                                <div class=\"validation\" id=\"caracteristicasmensaje\"> </div>\n"
                        + "                                </div>\n"
                        + "\n";

                form += selectTiposMenu(resJSONMenu.getString("tiposMenus"), menu.getIntidtipomenu().getIntidtipo());
                form += "                                </div>\n"
                        + "\n"
                        + "                            <div class=\"col-lg-12 form-inline \"> \n"
                        + "                                <div class=\"form-group col-lg-6 \"> \n"
                        + "                                    <button type=\"submit\" class=\"btn  btn-success\" onclick=\"editar(event, " + menu.getIntidmenu() + ")\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> \n"
                        + "                                </div>\n"
                        + "\n"
                        + "                                <div class=\"form-group col-lg-6\"> \n"
                        + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> \n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                            </div>\n";
                respuesta.put("formulario", form + "</form>");
                respuesta.put("success", resJSONMenu.getString("success"));
            } else {
                return respuestaJSON;
            }

        } catch (JsonSyntaxException | NullPointerException ex) {
            System.err.println("com.comedorui.MenuUI.formularioEdicion()");
            System.err.println(ex);
        }

        return respuesta.toString();

    }

    public String formularioActivarMenu(String datosJSON) throws ParseException {
        Utilidades utilidades = new Utilidades();
        JSONObject respuesta = new JSONObject();
        try {

            if (utilidades.validarError(datosJSON)) {

                JSONObject dataMenu = new JSONObject(datosJSON);
                Menu menu = gson.fromJson(dataMenu.getString("menu"), Menu.class);
                respuesta.put("infoMenuModal", htmlInfoModalActivarMenu(menu));

                if (dataMenu.getInt("cantidadPlanificacionesMenu") > 0) {
                    respuesta.put("listadoPlanificacion", listadoMenusActivosInfo(dataMenu.getString("planificacionesMenu")));
                } else {
                    respuesta.put("listadoPlanificacion", "[\n]");
                }
                respuesta.put("success", dataMenu.getString("success"));
                return respuesta.toString();
            } else {
                return datosJSON;
            }

        } catch (Exception ex) {
            respuesta.put("success", "error");
            respuesta.put("message", "Error al cargar el formulario");
        }
        return respuesta.toString();
    }

    private String htmlInfoModalActivarMenu(Menu menu) {
        String HTML = "";

        HTML += "<div class='modal-header'>\n"
                + "                                <h4 id='modalFechasTitle' class='modal-title'> Crear Planificacion Menú </h4>\n"
                + "                                <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n"
                + "                                    <span aria-hidden='true'>&times;</span>\n"
                + "                                </button>\n"
                + "                            </div>\n"
                + "                            <div class='modal-body'>\n"
                + "\n"
                + "                                <div id='dataModalFechas' class='row'>\n"
                + "                                     <div class='col-md-6'><strong>Características: </strong> " + menu.getStrcaracteristicas() + "  </div> \n"
                + "                                    <div class='col-md-6'><strong>Tipo: </strong>  " + menu.getIntidtipomenu().getStrtipo() + "  </div> \n"
                + "                                </div>\n"
                + "                                <form id='frmCrearPlanificacion' onsubmit='return crearPlanificacionMenu(" + menu.getIntidmenu() + ");'>\n"
                + "                                    <div class='input-daterange' id='datepicker'>\n"
                + "\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaInicio'>Fecha de Inicio</label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control' id='fechaInicio' onkeypress='mensajeFechaInicio()' onchange='mensajeFechaInicio()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaFin'>Fecha de Fin</label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control' id='fechaFin' onkeypress='mensajeFechaFin()' onchange='mensajeFechaFin()' aria-describedby='emailHelp' placeholder='2020-10-20'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='validation' id='fechasmensaje'> \n"
                + "                                            </div>    \n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                                    <div class='row planificacionMenu'> \n"
                + "\n"
                + "                                        <div class='row'> \n"
                + "                                            <!--                                            <div class='col-md-2'></div>-->\n"
                + "                                            <div class='col-md-12'>\n"
                + "                                                <h5> Histórico Planificaciones Menú </h5>\n"
                + "                                            </div>\n"
                + "                                            <!--                                            <div class='col-2'></div>-->\n"
                + "                                        </div>\n"
                + "\n"
                + "                                        <div class='row'>\n"
                + "                                            <!--                                            <div class='col-md-2'></div>-->\n"
                + "                                            <div class='col-md-12'>\n"
                + "                                                <table id='planificacionMenuInfo' class='table table-sorting table-hover  datatable'>\n"
                + "\n"
                + "                                                </table>\n"
                + "                                            </div>\n"
                + "                                            <!--                                            <div class='col-md-2'></div>-->\n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                            </div>\n"
                + "                            <div class='modal-footer'>\n"
                + "                                <button type='submit' class='btn btn-primary' form='frmCrearPlanificacion' >Guardar</button>\n"
                + "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>Cerrar</button>\n"
                + "                            </div>\n"
                + "                            </form>";

        return HTML;
    }

    public String listadoPlanificacionMenu(String JSONPlanificacion) {
        Utilidades utilidades = new Utilidades();
        JSONObject respuesta = new JSONObject();
        try {
            if (utilidades.validarError(JSONPlanificacion)) {
                JSONObject dataPlanificacionMenu = new JSONObject(JSONPlanificacion);
                if (dataPlanificacionMenu.getInt("cantidadPlanificacionesMenu") > 0) {
                    respuesta.put("listadoPlanificacion", listadoMenusActivosInfo(dataPlanificacionMenu.getString("planificacionesMenu")));
                } else {
                    respuesta.put("listadoPlanificacion", "[\n]");
                }
                respuesta.put("success", dataPlanificacionMenu.getString("success"));

            } else {
                return JSONPlanificacion;
            }
        } catch (Exception ex) {
            respuesta.put("success", "error");
            respuesta.put("message", "Error al cargar el formulario");
        }
        return respuesta.toString();
    }

    private String listadoMenusActivosInfo(String listadoPlanificacion) {
        listado = "[\n";
        try {
//            JSONObject respJson = new JSONObject(JSONPlanificacion);
//            if (respJson.getString("success").equals("ok")) {
            PlanificacionMenus planificacionMenus = gson.fromJson("{ \"planificacionMenus\" : " + listadoPlanificacion + " }", PlanificacionMenus.class);
//                if (respJson.getInt("cantidad") > 0) {
            for (int i = 0; i < planificacionMenus.getPlanificacionMenus().size(); i++) {

                if (i != planificacionMenus.getPlanificacionMenus().size() - 1) {
                    listado += "    [ \" " + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getStrcaracteristicas() + "\", "
                            + "\"" + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidtipomenu().getStrtipo() + "\", "
                            + "\"" + fechasMenu(planificacionMenus.getPlanificacionMenus().get(i)) + "\", "
                            + "\"" + diasUnaFecha(planificacionMenus.getPlanificacionMenus().get(i)) + "\","
                            + "\"" + opcionesBotones("desactivarPlanificacionInfoMenu", planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidmenu(),
                                    planificacionMenus.getPlanificacionMenus().get(i).getIntid()) + "\"],";
                } else {
                    listado += "    [ \" " + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getStrcaracteristicas() + "\", "
                            + "\"" + planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidtipomenu().getStrtipo() + "\", "
                            + "\"" + fechasMenu(planificacionMenus.getPlanificacionMenus().get(i)) + "\", "
                            + "\"" + diasUnaFecha(planificacionMenus.getPlanificacionMenus().get(i)) + "\","
                            + "\"" + opcionesBotones("desactivarPlanificacionInfoMenu", planificacionMenus.getPlanificacionMenus().get(i).getIntidmenu().getIntidmenu(),
                                    planificacionMenus.getPlanificacionMenus().get(i).getIntid()) + "\"]";
                }
            }
//                } else {
//                    return "[\n]";
//                }
//            } else {
//                return "error";
//            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.MenuUI.menusActivos()");
            System.err.println(ex);
        }
        return listado += "]";
    }
}
