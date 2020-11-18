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
        } else if (accion.equals("activar")) {
            boton = "<button type='button' class='btn btn-success' data-id='" + id + "' onclick='formularioActivarMenu(event," + id + ")'> <i class='fa fa-check-circle'></i> Activar</button>";
        } else if (accion.equals("desactivar")) {
            boton = "<button type='button' class='btn btn-danger' data-id='" + id + "' onclick='desactivarMenu(event," + id + "," + idFechas + ")'> <i class='fa fa-times'></i> Desactivar</button>";
        }
        return boton;
    }

    public String formulario(String listadoTiposMenu) {

        String form = "<h3> Registrar un Men&#250; </h3>"
                + "<p> Usted puede registrar los datos de un nuevo men&#250; </p>";

        form += "</br>"
                + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                + "                            \n"
                + "\n"
                + "                            <div class=\"col-lg-12 form-inline \"> \n"
                + "                                <div class=\"form-group col-lg-6 \"> \n"
                + "                                    <label for=\"caracteristicas\">Características   :</label>\n"
                + "                                    <textarea id=\"caracteristicas\"  name=\"caracteristicas\" onkeyup=\"mensajeCaracteristicas()\" placeholder=\"Costo de un almuerzo para estudiante\"></textarea>\n"
                + "                                    \n"
                + "                                <div class=\"validation\" id=\"caracteristicasmensaje\"> </div>\n"
                + "                                </div>\n"
                + "\n";
        form += selectTiposMenu(listadoTiposMenu, -1)
                + "                            </div>\n"
                + "                            <div class=\"col-lg-12 form-inline \"> \n <br/>"
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
        return form;
    }

    private String selectTiposMenu(String listadoTiposMenu, Integer idTipoMenu) {
//        Gson gson = new Gson();
        String selected;
        TipoMenus tipoMenus = new TipoMenus();
        String select = "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <label for=\"estado\">Estado </label>\n"
                + "                                    <select  id=\"estado\">\n";
        try {
            tipoMenus = gson.fromJson(listadoTiposMenu, TipoMenus.class);
            for (int i = 0; i < tipoMenus.getTipoMenus().size(); i++) {

                if (Objects.equals(idTipoMenu, tipoMenus.getTipoMenus().get(i).getIntidtipo())) {
                    selected = "selected";
                } else {
                    selected = "";
                }

                select += "<option value=\"" + tipoMenus.getTipoMenus().get(i).getIntidtipo() + "\"  " + selected + " > " + tipoMenus.getTipoMenus().get(i).getStrtipo() + " </option>     \n";
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
    public String listadoMenus(String listadoJSON) {
        Gson gson = new Gson();

        Menus menus = new Menus();
        String listado;
        listado = "[\n";

        try {

            JSONObject respJson = new JSONObject(listadoJSON);
            menus = gson.fromJson("{ \"menus\" : " + respJson.getString("menus") + " }", Menus.class);
            for (int i = 0; i < menus.getMenus().size(); i++) {
                if (menus.getMenus().get(i).getBlnestado()) {
                    listado += "    [ \" " + menus.getMenus().get(i).getStrcaracteristicas() + "\", "
                            + "\"" + menus.getMenus().get(i).getIntidtipo().getStrtipo() + "\", "
                            + "\"" + opcionesBotones("activar", menus.getMenus().get(i).getIntidmenu(), 0) + "\", ";
                    if (i != menus.getMenus().size() - 1) {
                        listado += "\"" + opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0) + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"], ";
                    } else {
                        listado += "\"" + opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0) + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"] ";
                    }
                }
            }
            respJson.put("listado", listado += "]");
            return respJson.toString();
        } catch (JsonSyntaxException | NullPointerException ex) {
            System.out.println("com.comedorui.MenuUI.listadoMenus()");
            System.err.println(ex);
        }
        return listado += "]";
    }

    public String listadoMenusFechasActivos(String FechasJSON) {

        Menus menus = new Menus();
        PlanificacionMenus planificacionMenus = new PlanificacionMenus();
        JsonParser parser = new JsonParser();

        try {
            JsonElement elementoJson = parser.parse(FechasJSON);
            JsonObject objJson = elementoJson.getAsJsonObject();

            menus = gson.fromJson(objJson.get("menusActivos").getAsString(), Menus.class);
            planificacionMenus = gson.fromJson(objJson.get("fechasMenusActivas").getAsString(), PlanificacionMenus.class);

            return menusActivos(menus, planificacionMenus);
        } catch (JsonSyntaxException | NullPointerException ex) {
            System.err.println("com.comedorui.MenuUI.listadoMenusFechasActivos()");
            System.err.println(ex);
        }
        return "";
    }

    private String menusActivos(Menus menus, PlanificacionMenus planificacionMenus) {
        listado = "[\n";
        try {
            for (int i = 0; i < menus.getMenus().size(); i++) {

                if (menuRangoFechas(menus.getMenus().get(i), planificacionMenus)) {
                    listado += "    [ \" " + menus.getMenus().get(i).getStrcaracteristicas() + "\", "
                            + "\"" + menus.getMenus().get(i).getIntidtipo().getStrtipo() + "\", "
                            + "\"" + fechasMenu(menus.getMenus().get(i)) + "\", "
                            + "\"" + diasUnaFecha(menus.getMenus().get(i)) + "\", "
                            //+ "\"" + opcionesBotones("editar", menus.getMenus().get(i).getIntidmenu(), 0) + opcionesBotones("eliminar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"],";
                            + "\"" + opcionesBotones("desactivar", menus.getMenus().get(i).getIntidmenu(), getIdFechasMenu(menus.getMenus().get(i))) + "\"],";
                }
            }
            listado = listado.substring(0, listado.length() - 1);
        } catch (Exception ex) {
            System.err.println("com.comedorui.MenuUI.menusActivos()");
            System.err.println(ex);
        }

        return listado += "]";
    }

    private Boolean menuRangoFechas(Menu menu, PlanificacionMenus planificacionMenusActivos) {
        Boolean fechaActiva = false;

        List<Planificacionmenu> listaFechasMenu = new ArrayList<>(menu.getPlanificacionmenuCollection());
        try {
            for (int i = 0; i < listaFechasMenu.size(); i++) { // Lista de fechas del menu
                for (int j = 0; j < planificacionMenusActivos.getPlanificionMenus().size(); j++) { // lista de fechas activas
                    if (Objects.equals(planificacionMenusActivos.getPlanificionMenus().get(j).getIntid(), listaFechasMenu.get(i).getIntid())) {
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

    private String fechasMenu(Menu menu) {
        String fechasString = "";
        Utilidades utilidades = new Utilidades();
        List<Planificacionmenu> listaFechas = new ArrayList<>(menu.getPlanificacionmenuCollection());

        if (listaFechas.isEmpty()) {
            fechasString = "No tiene Fechas";
        } else {
            fechasString = utilidades.fecha(listaFechas.get(listaFechas.size() - 1).getDtfechainicio()) + " - " + utilidades.fecha(listaFechas.get(listaFechas.size() - 1).getDtfechafin());
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

    private String diasUnaFecha(Menu menu) {
        String fechasString = "";

        try {
            List<Planificacionmenu> listaFechas = new ArrayList<>(menu.getPlanificacionmenuCollection());

            if (listaFechas.isEmpty()) {
                fechasString = "0 Días";
            } else {
                fechasString = Dias(listaFechas.get(listaFechas.size() - 1).getDtfechainicio(), listaFechas.get(listaFechas.size() - 1).getDtfechafin()).toString()
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

    public String formularioEdicion(String menuString, String listadoTiposMenu) throws ParseException {

        Menu menu = new Menu();
        Utilidades utilidades = new Utilidades();
        String form = "<h3> Modificar un Men&#250; </h3>"
                + "<p> Usted puede modificar los datos de un nuevo men&#250; </p>";

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

        try {
            menu = gson.fromJson(menuString, Menu.class);
            List<Planificacionmenu> listaFechas = new ArrayList<>(menu.getPlanificacionmenuCollection());

            form += selectTiposMenu(listadoTiposMenu, menu.getIntidtipo().getIntidtipo())
                    + "                            </div>\n"
                    + "                            <div class=\"col-lg-12 form-inline \"> \n <br/>"
                    + "<div class=\"col-lg-6\">\n         <label>Fechas disponibles del menú:</label>\n  </div>"
                    + "<div class=\"form-group col-lg-6 input-daterange input-group\" id=\"datepicker\">\n"
                    + "    <input type=\"text\" class=\"input-sm form-control\" id=\"fechaInicio\" onkeyup=\"mensajeFechaInicio()\"  onchange=\"mensajeFechaInicio()\" name=\"from\" placeholder=\"Desde\"/ value=\"" + utilidades.fechaFormularioEdicion(getFechasInicioFin(menu, "Inicio")) + "\"   >\n"
                    + "    <span class=\"input-group-addon\">a</span>\n"
                    + "    <input type=\"text\" class=\"input-sm form-control\" id=\"fechaFin\" onkeyup=\"mensajeFechaFin()\" onchange=\"mensajeFechaFin()\" name=\"to\" placeholder=\"Hasta\"/ value=\"" + utilidades.fechaFormularioEdicion(getFechasInicioFin(menu, "Fin")) + "\" >\n"
                    + "</div>"
                    + "                                <div class=\"validation\" id=\"fechasmensaje\"> </div>\n"
                    + "                                </div>\n"
                    + "                                                       \n"
                    + "                            <div class=\"col-lg-12 form-inline \"> \n"
                    + "                                <div class=\"form-group col-lg-6 \"> \n";
            if (!listaFechas.isEmpty()) {
                form += "                                    <button type=\"submit\" class=\"btn  btn-success\" onclick=\"editar(event," + menu.getIntidmenu() + ", " + listaFechas.get(listaFechas.size() - 1).getIntid() + " )\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> \n";
            }
        } catch (JsonSyntaxException | NullPointerException ex) {
            System.err.println("com.comedorui.MenuUI.formularioEdicion()");
            System.err.println(ex);
        }

        form += "                                </div>\n"
                + "\n"
                + "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> \n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </form>";
        return form;

    }

    public String formularioActivarMenu(String menuString) throws ParseException {

        Menu menu = new Menu();
        Utilidades utilidades = new Utilidades();

        menu = gson.fromJson(menuString, Menu.class);

        String form = "<h3> Activar un Men&#250; </h3>"
                + "<p> Usted establecer las fechas en las que se encuentre activo este men&#250; </p>";

        try {
            form += " <div class=\"col-lg-12 form-inline \"> \n"
                    + "     <div class=\"col-lg-6 \"> \n"
                    + "     <h4>Características </h4>\n"
                    + "     <p> " + menu.getStrcaracteristicas() + "</p>\n"
                    + "     </div>\n"
                    + "     <div class=\"col-lg-6 \"> \n"
                    + "     <h4> Tipo de Menú</h4>\n"
                    + "     <p> " + menu.getIntidtipo().getStrtipo() + "</p>\n"
                    + "     </div>\n";
        } catch (NullPointerException ex) {
            System.err.println("com.comedorui.MenuUI.formularioActivarMenu()");
            System.err.println(ex);
        }

        form += "</br>"
                + "<form class=\"lead col-lg-10\" id=\"formulario\" method=\"post\">\n"
                + "                            \n"
                + "\n"
                + "<div class=\"col-lg-12 form-inline \"> \n <br/>"
                + "<div class=\"col-lg-6\">\n         <label>Fechas disponibles del menú:</label>\n  </div>"
                + "<div class=\"form-group col-lg-6 input-daterange input-group\" id=\"datepicker\">\n"
                + "    <input type=\"text\" class=\"input-sm form-control\" id=\"fechaInicio\" onkeyup=\"mensajeFechaInicio()\"  onchange=\"mensajeFechaInicio()\" name=\"from\" placeholder=\"Desde\"/    >\n"
                + "    <span class=\"input-group-addon\">a</span>\n"
                + "    <input type=\"text\" class=\"input-sm form-control\" id=\"fechaFin\" onkeyup=\"mensajeFechaFin()\" onchange=\"mensajeFechaFin()\" name=\"to\" placeholder=\"Hasta\"/  >\n"
                + "</div>"
                + "                                <div class=\"validation\" id=\"fechasmensaje\"> </div>\n"
                + "                                </div>\n"
                + "                                                       \n"
                + "                            <div class=\"col-lg-12 form-inline \"> \n"
                + "                                <div class=\"form-group col-lg-6 \"> \n"
                + "                                    <button type=\"submit\" class=\"btn  btn-success\" onclick=\"activarMenu(event," + menu.getIntidmenu() + ")\">Guardar <i class=\"fa fa-check\" aria-hidden=\"true\"></i></button> \n"
                + "                                </div>\n"
                + "\n"
                + "                                <div class=\"form-group col-lg-6\"> \n"
                + "                                    <button type=\"\" class=\"btn   btn-danger\">Cancelar <i class=\"fa fa-times\" aria-hidden=\"true\"></i></button> \n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                        </form>";
        return form;

    }
}
