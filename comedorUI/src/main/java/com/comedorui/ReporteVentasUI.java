/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedorui;

import EspochWS.Persona;
import Utilities.PrintUtilidades;
import Utilities.Utilidades;
import com.google.gson.Gson;
import entities.TipoMenus;
import entities.TipoUsuarios;
import entities.Tipomenu;
import entities.Tipousuario;
import entities.Venta;
import entities.VentaProcedure;
import entities.VentasProcedure;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

/**
 *
 * @author corebitsas
 */
public class ReporteVentasUI {

    private Gson gson = new Gson();

    public ReporteVentasUI() {
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String formularioVentasDiarias() {
        String HTML = "";
        HTML += headerModalFormVentasDiarias();
        HTML += formModalVentasDiarias();
        return HTML;
    }

    public String headerModalFormVentasDiarias() {
        String HTML = "<div class='modal-header'>\n"
                + "                                <h4 id='modalReportesTitle' class='modal-title'> Reporte de Ventas diarias </h4>\n"
                + "                                <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n"
                + "                                    <span aria-hidden='true'>&times;</span>\n"
                + "                                </button>\n"
                + "                            </div>\n";

        return HTML;
    }

    public String headerModalFormVentasUsuarioFechas() {
        String HTML = "<div class='modal-header'>\n"
                + "                                <h4 id='modalReportesTitle' class='modal-title'> Reporte de Ventas de un usuario </h4>\n"
                + "                                <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n"
                + "                                    <span aria-hidden='true'>&times;</span>\n"
                + "                                </button>\n"
                + "                            </div>\n";

        return HTML;
    }

    public String formModalVentasUsuarioFechas() {
        String form = "";
        form += "                            <div class='modal-body'>\n"
                + "\n"
                + "                                <div id='dataModalFechas' class='row'>\n"
                + "                                     <div class='col-md-12'> En esta sección se obtiene la información de los ventas del dia, mediante un número de cédula y un intervalo de fechas </div> \n"
                //                + "                                    <div class='col-md-6'><strong>Tipo: </strong>  " + menu.getIntidtipomenu().getStrtipo() + "  </div> \n"
                + "                                </div>\n"
                + "</br>"
                + "                                <form id='frmVentasDiarias' onsubmit='return reporteVentasUsuarioIntervaloFechas();'>\n"
                + "                                    <div class='input-daterange' id='datepicker'>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='cedula'>Cédula</label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control' id='cedula'  placeholder='Cédula'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaInicio'>Fecha de Inicio</label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaInicio' onkeypress='mensajeFechaInicio()' onchange='mensajeFechaInicio()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaFin'>Fecha de Fin</label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaFin' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='validation' id='fechasmensaje'> \n"
                + "                                            </div>    \n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                            </div>\n"
                + "                            <div class='modal-footer'>\n"
                + "                                <button type='submit' class='btn btn-primary' form='frmVentasDiarias' >Buscar</button>\n"
                + "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>Cerrar</button>\n"
                + "                            </div>\n"
                + "                            </form>";
        return form;
    }

    public String formModalVentasDiarias() {
        String form = "";
        form += "                            <div class='modal-body'>\n"
                + "\n"
                + "                                <div id='dataModalFechas' class='row'>\n"
                + "                                     <div class='col-md-12'> En esta sección se obtiene la información de los ventas del dia, mediante una fecha especificada </div> \n"
                //                + "                                    <div class='col-md-6'><strong>Tipo: </strong>  " + menu.getIntidtipomenu().getStrtipo() + "  </div> \n"
                + "                                </div>\n"
                + "</br>"
                + "                                <form id='frmVentasDiarias' onsubmit='return reporteVentasDiarias();'>\n"
                + "                                    <div class='input-daterange' id='datepicker'>\n"
                + "\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaInicio'>Fecha de Inicio</label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaVenta' onkeypress='mensajeFechaInicio()' onchange='mensajeFechaInicio()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='validation' id='fechasmensaje'> \n"
                + "                                            </div>    \n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                            </div>\n"
                + "                            <div class='modal-footer'>\n"
                + "                                <button type='submit' class='btn btn-primary' form='frmVentasDiarias' >Buscar</button>\n"
                + "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>Cerrar</button>\n"
                + "                            </div>\n"
                + "                            </form>";
        return form;

    }

    public String formularioVentasIntervalo() {
        String HTML = "";
        HTML += headerModalFormVentasIntervalo();
        HTML += formModalVentasIntervalo();
        return HTML;
    }

    private String headerModalFormVentasIntervalo() {
        String HTML = "<div class='modal-header'>\n"
                + "                                <h4 id='modalReportesTitle' class='modal-title'> Reporte de Ventas en un rango de fechas </h4>\n"
                + "                                <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n"
                + "                                    <span aria-hidden='true'>&times;</span>\n"
                + "                                </button>\n"
                + "                            </div>\n";

        return HTML;
    }

    private String formModalVentasIntervalo() {
        String form = "";
        form += "                            <div class='modal-body'>\n"
                + "\n"
                + "                                <div id='dataModalFechas' class='row'>\n"
                + "                                     <div class='col-md-12'> En esta sección se obtiene la información de los ventas que existen un intervalo de fechas, "
                + "  donde se debe especificar la fecha de inicio y la fecha de fin.</div> \n"
                //                + "                                    <div class='col-md-6'><strong>Tipo: </strong>  " + menu.getIntidtipomenu().getStrtipo() + "  </div> \n"
                + "                                </div>\n"
                + "</br>"
                + "                                <form id='frmVentasIntervalo' onsubmit='return reporteVentasIntervalo();'>\n"
                + "                                    <div class='input-daterange' id='datepicker'>\n"
                + "\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaInicio'><strong>Fecha de Inicio</strong></label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaInicio' onkeypress='mensajeFechaInicio()' onchange='mensajeFechaInicio()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaFin'><strong>Fecha de Fin</strong></label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaFin' onkeypress='mensajeFechaFin()' onchange='mensajeFechaFin()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='validation' id='fechasmensaje'> \n"
                + "                                            </div>    \n"
                + "                                        </div>\n"
                + "                                    </div>\n"
                + "                            </div>\n"
                + "                            <div class='modal-footer'>\n"
                + "                                <button type='submit' class='btn btn-primary' form='frmVentasIntervalo' >Buscar</button>\n"
                + "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>Cerrar</button>\n"
                + "                            </div>\n"
                + "                            </form>";
        return form;
    }

    private String headerModalFormVentasTipoMenu() {
        String HTML = "<div class='modal-header'>\n"
                + "                                <h4 id='modalReportesTitle' class='modal-title'> Reporte de Ventas por tipo de menú </h4>\n"
                + "                                <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n"
                + "                                    <span aria-hidden='true'>&times;</span>\n"
                + "                                </button>\n"
                + "                            </div>\n";

        return HTML;
    }

    private String headerModalFormVentasTipoUsuario() {
        String HTML = "<div class='modal-header'>\n"
                + "                                <h4 id='modalReportesTitle' class='modal-title'> Reporte de Ventas por tipo de usuario </h4>\n"
                + "                                <button type='button' class='close' data-dismiss='modal' aria-label='Close'>\n"
                + "                                    <span aria-hidden='true'>&times;</span>\n"
                + "                                </button>\n"
                + "                            </div>\n";

        return HTML;
    }

    private String formModalVentasIntervaloMenu(String JSONTipoMenus) {
        String form = "";
        form += "                            <div class='modal-body'>\n"
                + "\n"
                + "                                <div id='dataModalFechasMenu' class='row'>\n"
                + "                                     <div class='col-md-12'> En esta sección se obtiene la información de las ventas que existen un intervalo de fechas"
                + "de acuerdo al tipo de menú, se debe seleccionar el tipo de menú e ingresar la fecha de inicio y fecha de fin."
                + "  donde se debe especificar la fecha de inicio y la fecha de fin.</div> \n"
                + "                                </div>\n"
                + "</br>"
                + "                                <form id='frmVentasIntervaloMenu' onsubmit='return reporteVentasIntervaloMenu();'>\n"
                + "                                    <div class='input-daterange' id='datepicker'>\n"
                + "\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaInicio'><strong>Fecha de Inicio</strong></label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaInicio' onkeypress='mensajeFechaInicio()' onchange='mensajeFechaInicio()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaFin'><strong>Fecha de Fin</strong></label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaFin' onkeypress='mensajeFechaFin()' onchange='mensajeFechaFin()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='validation' id='fechasmensaje'> \n"
                + "                                            </div>    \n"
                + "                                        </div>\n"
                + "                                        </br>";

        form += "                                        <div class='row'>\n"
                + "                                               <div class='form-group'>\n"
                + "                                                 <div class='col-md-4'> <label for='tipoMenu'><strong> Selecione el tipo de menú: </strong></label></div>\n"
                + "                                                 <div class='col-md-6'> ";
        form += selectTipoMenus(JSONTipoMenus);
        form += "                                                   </div>\n"
                + "                                               </div>"
                + "                                      </div>\n";

        form += "                                    </div>\n"
                + "                            </div>\n"
                + "                            <div class='modal-footer'>\n"
                + "                                <button type='submit' class='btn btn-primary' form='frmVentasIntervaloMenu' >Buscar</button>\n"
                + "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>Cerrar</button>\n"
                + "                            </div>\n"
                + "                            </form>";
        return form;
    }

    private String formModalVentasIntervaloUsuario(String JSONTipoUsuarios) {
        String form = "";
        form += "                            <div class='modal-body'>\n"
                + "\n"
                + "                                <div id='dataModalFechasUsuario' class='row'>\n"
                + "                                     <div class='col-md-12'> En esta sección se obtiene la información de las ventas que existen un intervalo de fechas "
                + "de acuerdo al tipo de usuario, se debe seleccionar el usuario e ingresar la fecha de inicio y fecha de fin."
                + "  donde se debe especificar la fecha de inicio y la fecha de fin.</div> \n"
                + "                                </div>\n"
                + "</br>"
                + "                                <form id='frmVentasIntervaloUsuario' onsubmit='return reporteVentasIntervaloFechasUsuario();'>\n"
                + "                                    <div class='input-daterange' id='datepicker'>\n"
                + "\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaInicio'><strong>Fecha de Inicio</strong></label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaInicio' onkeypress='mensajeFechaInicio()' onchange='mensajeFechaInicio()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='form-group'>\n"
                + "                                                <div class='col-md-4'> <label for='fechaFin'><strong>Fecha de Fin</strong></label> </div>\n"
                + "                                                <div class='col-md-6'> \n"
                + "                                                    <input type='text' class='form-control datepicker' id='fechaFin' onkeypress='mensajeFechaFin()' onchange='mensajeFechaFin()' aria-describedby='emailHelp' placeholder='2020-10-15'> \n"
                + "                                                </div>\n"
                + "                                                <div class='col-md-2'> </div>\n"
                + "                                            </div>\n"
                + "                                        </div>\n"
                + "                                        <div class='row'>\n"
                + "                                            <div class='validation' id='fechasmensaje'> \n"
                + "                                            </div>    \n"
                + "                                        </div>\n"
                + "                                        </br>";

        form += "                                        <div class='row'>\n"
                + "                                               <div class='form-group'>\n"
                + "                                                 <div class='col-md-4'> <label for='tipoUsuario'><strong> Selecione el usuario: </strong></label></div>\n"
                + "                                                 <div class='col-md-6'> ";
        form += selectTipoUsuarios(JSONTipoUsuarios);
        form += "                                                   </div>\n"
                + "                                               </div>"
                + "                                      </div>\n";

        form += "                                    </div>\n"
                + "                            </div>\n"
                + "                            <div class='modal-footer'>\n"
                + "                                <button type='submit' class='btn btn-primary' form='frmVentasIntervaloUsuario' >Buscar</button>\n"
                + "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>Cerrar</button>\n"
                + "                            </div>\n"
                + "                            </form>";
        return form;
    }

    private String selectTipoMenus(String JSONTipoMenus) {
        String HTML = "";
        try {

            JSONObject dataTipos = new JSONObject(JSONTipoMenus);

            if (dataTipos.getString("success").equals("ok") && dataTipos.getInt("cantidad") > 0) {

                TipoMenus tipoMenus = gson.fromJson("{ \"tipoMenus\" : " + dataTipos.getString("tiposMenus") + " }", TipoMenus.class);
                HTML = " <select class='form-control' id='tipoMenu'>\n";

                for (Tipomenu tipoMenu : tipoMenus.getTipoMenus()) {
                    HTML += "      <option value='" + tipoMenu.getIntidtipo() + "'>" + tipoMenu.getStrtipo() + "</option>\n";
                }

                HTML += "    </select>";
            }

        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.selectTipoMenus() " + ex);
        }
        return HTML;
    }

    private String selectTipoUsuarios(String JSONTipoUsuarios) {
        String HTML = "";
        try {

            JSONObject dataTipos = new JSONObject(JSONTipoUsuarios);

            if (dataTipos.getString("success").equals("ok") && dataTipos.getInt("cantidad") > 0) {
                TipoUsuarios tipoUsuarios = gson.fromJson("{ \"tipoUsuarios\" : " + dataTipos.getString("tiposUsuarios") + " }", TipoUsuarios.class);
                HTML = " <select class='form-control' id='tipoUsuario'>\n";

                for (Tipousuario tipoUsuario : tipoUsuarios.getTipoUsuarios()) {
                    HTML += "      <option value='" + tipoUsuario.getIntidtipo() + "'>" + tipoUsuario.getStrtipo() + "</option>\n";
                }

                HTML += "    </select>";
            }

        } catch (Exception ex) {
            System.out.println("com.comedorui.ReporteVentasUI.selectTipoUsuarios() " + ex);
        }
        return HTML;
    }

    public String formularioReporteTipoMenu(String JSONTipoMenus) {
        String HTML = "";
        HTML += headerModalFormVentasTipoMenu();
        HTML += formModalVentasIntervaloMenu(JSONTipoMenus);
        return HTML;
    }

    public String formularioReporteTipoUsuario(String JSONTipoUsuarios) {
        String HTML = "";
        HTML += headerModalFormVentasTipoUsuario();
        HTML += formModalVentasIntervaloUsuario(JSONTipoUsuarios);
        return HTML;
    }

    public String formularioReporteVentasUsuarioFechas() {
        String HTML = "";
        HTML += headerModalFormVentasUsuarioFechas();
        HTML += formModalVentasUsuarioFechas();
        return HTML;
    }

    public String reporteVentasUsuarioFechas(String JSONData) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONData);
            if (utilidades.validarError(JSONData)) {
                Date fechaInicio = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaInicio"));
                Date fechaFin = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaFin"));
                HTML += toHTMLVentasUsuarioFechas(data.getString("dataReporte"), fechaInicio, fechaFin);
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.reporteVentasUsuarioFechas() " + ex);
        }
        return HTML;
    }

    public String toHTMLVentasUsuarioFechas(String JSONReporte, Date fechaInicio, Date fechaFin) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {

            JSONObject dataReporte = new JSONObject(JSONReporte);

            HTML += encabezadoReportes();

            if (dataReporte.getString("success").equals("ok")) {
                HTML += "               <div class='col-md-12'>\n"
                        + "                 <div class='row'>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Inicio:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaInicio) + "</p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Fin:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaFin) + "</p>"
                        + "                     </div>\n"
                        + "                 </div>\n"
                        + "               </div>\n"
                        + "               <div class='col-md-12'>\n"
                        + "                 <div class='row'>\n";

                Persona persona = gson.fromJson(dataReporte.getString("datosPersona"), Persona.class);

                HTML += "                     <div class='col-md-3'>\n"
                        + "                         <p> Nombres:    </p>"
                        + "                     </div>\n";

                HTML += "                     <div class='col-md-3'>\n"
                        + "                         <p> " + persona.getPer_nombres() + " " + persona.getPer_primerApellido() + " " + persona.getPer_segundoApellido() + " </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Número de Ventas:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                        + "                     </div>\n";

                HTML += "                     <div class='col-md-6'>\n"
                        + "                         <p> Total de ventas:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-6'>\n"
                        + "                         <p> " + dataReporte.getInt("totalVentas") + " $ </p>"
                        + "                     </div>\n";
            }

            HTML += "                   </div>\n"
                    + "                </div>\n";

            HTML += detalleListadoVenta(JSONReporte);
            HTML += "         </div>\n";

        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.toHTMLVentasUsuarioFechas() " + ex);
        }
        return HTML;
    }

    public String reporteVentasDiarias(String JSONData) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONData);

            if (utilidades.validarError(JSONData)) {
                Date fecha = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaVenta"));
                HTML += toHtmlVentasDiarias(fecha, data.getString("dataReporte"));
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.reporteVentasDiarias() " + ex);

        }
        return HTML;
    }

    private String encabezadoReportes() {
        String HTML = "";
        HTML = "               <div class='row encabezado'> \n"
                + "                   <p>  ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO   </p>"
                + "                   <p>  RUC # 066001250001   </p>"
                + "                   <p>  PANAMERICANA SUR KM 1/2   </p>"
                + "                   <p> A. SRI 1121901577 </p>"
                + "               </div>\n";

        return HTML;
    }

    public String toHtmlVentasDiarias(Date fecha, String JSONReporte) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {

            JSONObject dataReporte = new JSONObject(JSONReporte);

            HTML += encabezadoReportes();

            HTML += "         <div class='card'> \n";
            HTML += "               <div class='card-header'> \n"
                    + "                   <h5 class='card-title'>  REPORTE DIARIO   </h5>";
            HTML += "                   <div class='row'>";
            if (dataReporte.getString("success").equals("ok")) {
                HTML += "                   <div class='col-md-3'>\n"
                        + "                     <p> Fecha:    </p>"
                        + "                 </div>\n"
                        + "                 <div class='col-md-3'>\n"
                        + "                     <p> " + utilidades.fechaFormatoHtml(fecha) + " </p>"
                        + "                 </div>\n"
                        + "                 <div class='col-md-3'>\n"
                        + "                     <p> Número de Ventas del día:    </p>"
                        + "                 </div>\n"
                        + "                 <div class='col-md-3'>\n"
                        + "                     <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                        + "                 </div>\n";
            }
            HTML += "                   </div>\n";
            HTML += "               </div>\n";
            HTML += "               <div clas='card-body'>";
            HTML += detalleListadoVenta(JSONReporte);
            HTML += "               </div>";
            HTML += "         </div>\n"
                    + "\n";
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.toHtmlVentasDiarias() " + ex);
        }
        return HTML;
    }

    private String detalleListadoVenta(String JSONReporte) {
        String HTML = "";
        try {
            HTML += "   <table class='table table-responsive-md' >";
            HTML += "       <thead class='thead-dark'>\n"
                    + "     <tr>\n"
                    + "             <th>Detalle</th>\n"
                    + "             <th>Cantidad</th>\n"
                    + "             <th>Precio Unitario</th>\n"
                    + "             <th>Total</th>\n"
                    + "         </tr>\n"
                    + "     </thead>";
            HTML += "       <tbody>";
            HTML += toHTMLListVentas(JSONReporte);
            HTML += toHTMLResultListVenta(JSONReporte);
            HTML += "       </tbody>";
            HTML += "   </table>";

        } catch (Exception ex) {

        }

        return HTML;
    }

    private String toHTMLListVentas(String JSONReporte) {
        String HTML = "";
        try {
            JSONObject dataReporte = new JSONObject(JSONReporte);

            if (dataReporte.getInt("cantidadVentas") > 0) {

                VentasProcedure ventasDiarias = gson.fromJson("{ \"ventasProcedure\" : " + dataReporte.getString("ventasDiarias") + " }", VentasProcedure.class
                );

                for (VentaProcedure ventaProcedure : ventasDiarias.getVentasProcedure()) {
                    HTML += "<tr>\n"
                            + "         <td>" + ventaProcedure.getNombrecostousuario() + "</td>\n"
                            + "         <td>" + ventaProcedure.getCantidadvendidos() + "</td>\n"
                            + "         <td> $ " + ventaProcedure.getCostounitario() + "</td>\n"
                            + "         <td> $ " + ventaProcedure.getTotal() + "</td>\n"
                            + "    </tr>";
                }
            }

        } catch (Exception ex) {
            System.out.println("com.comedorui.ReporteVentasUI.toHTMLListVentas() " + ex);
        }
        return HTML;
    }

    private String toHTMLResultListVenta(String JSONReporte) {
        String HTML = "";

        try {
            JSONObject dataReporte = new JSONObject(JSONReporte);
            Double iva = 0.12;

            if (dataReporte.getInt("cantidadVentas") > 0) {
                HTML += "<tr>\n"
                        + "         <td colspan='3'> <strong> Subtotal: </strong></td>\n"
                        + "         <td> $ " + Math.round((dataReporte.getDouble("totalVentas") - (dataReporte.getDouble("totalVentas") * iva)) * 100.0) / 100.0 + " </td>\n"
                        + "    </tr>";
                HTML += "<tr>\n"
                        + "         <td colspan='3'> <strong> Iva: </strong></td>\n"
                        + "         <td> $ " + Math.round((dataReporte.getDouble("totalVentas") * iva) * 100.0) / 100.0 + " </td>\n"
                        + "    </tr>";
                HTML += "<tr>\n"
                        + "         <td colspan='3'> <strong> Total Ventas: </strong></td>\n"
                        + "         <td> $ " + Math.round(dataReporte.getDouble("totalVentas") * 100.0) / 100.0 + " </td>\n"
                        + "    </tr>";
            } else {
                HTML += "<tr>\n"
                        + "         <td colspan='3'> <strong> Subtotal: </strong></td>\n"
                        + "         <td> $ 0.00 </td>\n"
                        + "    </tr>";
                HTML += "<tr>\n"
                        + "         <td colspan='3'> <strong> Iva: </strong></td>\n"
                        + "         <td> $ 0.00 </td>\n"
                        + "    </tr>";
                HTML += "<tr>\n"
                        + "         <td colspan='3'> <strong> Total Ventas: </strong></td>\n"
                        + "         <td> $ 0.00 </td>\n"
                        + "    </tr>";
            }

        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.toHTMLResultListVenta() " + ex);
        }
        return HTML;
    }

    public String reporteVentasIntervalos(String JSONData) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONData);

            if (utilidades.validarError(JSONData)) {
                Date fechaInicio = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaInicio"));
                Date fechaFin = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaFin"));
                HTML += toHTMLVentasIntervalo(fechaInicio, fechaFin, data.getString("dataReporte"));

            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.reporteVentasIntervalos() " + ex);

        }
        return HTML;
    }

    private String toHTMLVentasIntervalo(Date fechaInicio, Date fechaFin, String JSONReporte) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {

            JSONObject dataReporte = new JSONObject(JSONReporte);

            HTML += encabezadoReportes();

            if (dataReporte.getString("success").equals("ok")) {
                HTML += "               <div class='col-md-9'>\n"
                        + "                 <div class='row'>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Inicio:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaInicio) + "</p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Fin:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaFin) + "</p>"
                        + "                     </div>\n"
                        + "                 </div>\n"
                        + "               </div>\n"
                        + "               <div class='col-md-3'>\n"
                        + "                 <div class='row'>\n"
                        + "                     <div class='col-md-6'>\n"
                        + "                         <p> Número de Ventas del día:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-6'>\n"
                        + "                         <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                        + "                     </div>\n"
                        + "                 </div>\n"
                        + "               </div>\n";
            }
            HTML += detalleListadoVenta(JSONReporte);
            HTML += "         </div>\n";
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.toHTMLVentasIntervalo() " + ex);
        }
        return HTML;
    }

    public String reporteVentasIntervalosMenu(String JSONData) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONData);

            if (utilidades.validarError(JSONData)) {
                Date fechaInicio = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaInicio"));
                Date fechaFin = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaFin"));
                HTML += toHTMLVentasIntervaloMenu(fechaInicio, fechaFin, data.getString("dataReporte"), data.getString("dataTipoMenu"));
            }
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.reporteVentasIntervalosMenu() " + ex);

        }
        return HTML;
    }

    public String reporteVentasIntervaloUsuario(String JSONData) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONData);

            if (utilidades.validarError(JSONData)) {
                Date fechaInicio = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaInicio"));
                Date fechaFin = utilidades.stringToDate("yyyy-MM-dd", data.getString("fechaFin"));
                HTML += toHTMLVentasIntervaloUsuario(fechaInicio, fechaFin, data.getString("dataReporte"), data.getString("dataTipoUsuario"));
            }
        } catch (Exception ex) {
            System.out.println("com.comedorui.ReporteVentasUI.reporteVentasIntervaloUsuario() " + ex);

        }
        return HTML;
    }

    private String toHTMLVentasIntervaloMenu(Date fechaInicio, Date fechaFin, String JSONReporte, String JSONTipoMenu) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {

            JSONObject dataReporte = new JSONObject(JSONReporte);

            HTML += encabezadoReportes();

            if (dataReporte.getString("success").equals("ok")) {
                HTML += "               <div class='col-md-12'>\n"
                        + "                 <div class='row'>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Inicio:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaInicio) + "</p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Fin:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaFin) + "</p>"
                        + "                     </div>\n"
                        + "                 </div>\n"
                        + "               </div>\n"
                        + "               <div class='col-md-12'>\n"
                        + "                 <div class='row'>\n";

                JSONObject dataTipoMenu = new JSONObject(JSONTipoMenu);
                if (dataTipoMenu.getString("success").equals("ok")) {
                    HTML += "                     <div class='col-md-3'>\n"
                            + "                         <p> Tipo de Menú:    </p>"
                            + "                     </div>\n";
                    Tipomenu tipoMenu = gson.fromJson(dataTipoMenu.getString("tipoMenu"), Tipomenu.class
                    );
                    HTML += "                     <div class='col-md-3'>\n"
                            + "                         <p> " + tipoMenu.getStrtipo() + " </p>"
                            + "                     </div>\n"
                            + "                     <div class='col-md-3'>\n"
                            + "                         <p> Número de Ventas del día:    </p>"
                            + "                     </div>\n"
                            + "                     <div class='col-md-3'>\n"
                            + "                         <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                            + "                     </div>\n";
                } else {
                    HTML += "                     <div class='col-md-6'>\n"
                            + "                         <p> Número de Ventas del día:    </p>"
                            + "                     </div>\n"
                            + "                     <div class='col-md-6'>\n"
                            + "                         <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                            + "                     </div>\n";
                }

                HTML += "                   </div>\n"
                        + "                </div>\n";
            }
            HTML += detalleListadoVenta(JSONReporte);
            HTML += "         </div>\n";
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.toHTMLVentasIntervalo() " + ex);
        }
        return HTML;
    }

    private String toHTMLVentasIntervaloUsuario(Date fechaInicio, Date fechaFin, String JSONReporte, String JSONTipoUsuario) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {

            JSONObject dataReporte = new JSONObject(JSONReporte);

            HTML += encabezadoReportes();

            if (dataReporte.getString("success").equals("ok")) {
                HTML += "               <div class='col-md-12'>\n"
                        + "                 <div class='row'>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Inicio:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaInicio) + "</p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> Fecha Fin:    </p>"
                        + "                     </div>\n"
                        + "                     <div class='col-md-3'>\n"
                        + "                         <p> " + utilidades.fecha(fechaFin) + "</p>"
                        + "                     </div>\n"
                        + "                 </div>\n"
                        + "               </div>\n"
                        + "               <div class='col-md-12'>\n"
                        + "                 <div class='row'>\n";

                JSONObject dataTipoUsuario = new JSONObject(JSONTipoUsuario);
                if (dataTipoUsuario.getString("success").equals("ok")) {
                    HTML += "                     <div class='col-md-3'>\n"
                            + "                         <p> Tipo de Menú:    </p>"
                            + "                     </div>\n";
                    Tipousuario tipoUsuario = gson.fromJson(dataTipoUsuario.getString("tipoUsuario"), Tipousuario.class
                    );
                    HTML += "                     <div class='col-md-3'>\n"
                            + "                         <p> " + tipoUsuario.getStrtipo() + " </p>"
                            + "                     </div>\n"
                            + "                     <div class='col-md-3'>\n"
                            + "                         <p> Número de Ventas del día:    </p>"
                            + "                     </div>\n"
                            + "                     <div class='col-md-3'>\n"
                            + "                         <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                            + "                     </div>\n";
                } else {
                    HTML += "                     <div class='col-md-6'>\n"
                            + "                         <p> Número de Ventas del día:    </p>"
                            + "                     </div>\n"
                            + "                     <div class='col-md-6'>\n"
                            + "                         <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                            + "                     </div>\n";
                }

                HTML += "                   </div>\n"
                        + "                </div>\n";
            }
            HTML += detalleListadoVenta(JSONReporte);
            HTML += "         </div>\n";
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.toHTMLVentasIntervalo() " + ex);
        }
        return HTML;
    }

    public String getImageEspoch(String filePath) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    }

    private String encabezadoImagenEspoch() throws IOException {
        String HTML = "";
        try {
            HTML += "            <div class='row'>\n";
            HTML += "                <div class='col-md-12'>\n";
            HTML += "<img class='center' src='data:image/png;base64, " + getImageEspoch("C:\\Users\\alex4\\Documents\\NetBeansProjects\\testBarcode\\src\\main\\webapp\\escudo_espoch.png") + "' alt='' width='100' height='100'>";
            HTML += "                    <p class='text-center'>  ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO   </p>\n";
            HTML += "                    <p class='text-center'>  RUC # 066001250001   </p>\n";
            HTML += "                    <p class='text-center'>  PANAMERICANA SUR KM 1/2   </p>\n";
            HTML += "                    <p class='text-center'> A. SRI 1121901577 </p>\n";
            HTML += "                </div>\n";
            HTML += "            </div>\n";
            return HTML;
        } catch (Exception ex) {

        }
        return HTML;
    }

    private String tableDataVenta(String JSONRegistroVenta) {

        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONRegistroVenta);

            Venta venta = gson.fromJson(data.getString("dataVenta"), Venta.class
            );
            Persona persona = gson.fromJson(data.getString("datosUsuario"), Persona.class
            );

            HTML += "                <table class='table'>\n";
            HTML += "                    <tbody>\n";
            HTML += "                        <tr>\n";
            HTML += "                            <th>Cédula</th>\n";
            HTML += "                            <td>" + venta.getIntidcostousuario().getStrcedula() + "</td>\n";
            HTML += "                            <th>   Nombre</th>\n";
            HTML += "                            <td>" + persona.getPer_nombres() + " " + persona.getPer_primerApellido() + " " + persona.getPer_segundoApellido() + "\n";
            HTML += "                        </tr>\n";
            HTML += "                        <tr>\n";
            HTML += "                            <th>Correo</th>\n";
            HTML += "                            <td>" + persona.getPer_email() + "</td>\n";
            HTML += "                            <th>   Fecha</th>\n";
            HTML += "                            <td>" + utilidades.fecha(venta.getDtfecha()) + "</td>\n";
            HTML += "                        </tr>\n";
            HTML += "                        <tr>\n";
            HTML += "                            <th>Tipo Usuario</th>\n";
            HTML += "                            <td>" + venta.getIntidcostousuario().getIntidcosto().getIntidtipousuario().getStrtipo() + "</td>\n";
            HTML += "                            <td></td>\n";
            HTML += "                            <td></td>\n";
            HTML += "                        </tr>\n";
            HTML += "                </table>\n";
            HTML += "                </br>\n";

        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.tableDataVenta() " + ex);
        }
        return HTML;
    }

    private String tableDetalleVenta(String JSONRegistroVenta) {
        String HTML = "";
        try {
            JSONObject data = new JSONObject(JSONRegistroVenta);
            Venta venta = gson.fromJson(data.getString("dataVenta"), Venta.class
            );
            Double iva = 0.12;
            HTML += "<table class='table'>\n"
                    + "  <thead>\n"
                    + "    <tr>\n"
                    + "      <th>Cantidad</th>\n"
                    + "      <th colspan='2'>Detalle</th>\n"
                    + "      <th>Precio Unitario</th>\n"
                    + "      <th>Total</th>\n"
                    + "    </tr>\n"
                    + "  </thead>\n"
                    + "  <tbody>\n"
                    + "    <tr>\n"
                    + "      <td>" + venta.getIntcantidad() + "</td>\n"
                    + "      <td colspan='2'> " + venta.getIntidcostousuario().getIntidcosto().getStrdetalle() + "</td>\n";

            Double precioUnitario = Math.round(venta.getIntidcostousuario().getIntidcosto().getMnvalor()) * 100.0 / 100.0;
            precioUnitario -= (precioUnitario * iva);
            Double total = (Math.round(precioUnitario * venta.getIntcantidad()) * 100.0 / 100.0);

            HTML += "      <td>" + precioUnitario.toString() + "</td>\n"
                    + "      <td>" + total + "</td>\n"
                    + "    </tr>\n";
            HTML += "<tr>\n"
                    + "         <td colspan='4'> <strong> Subtotal: </strong></td>\n"
                    + "         <td> $ " + Math.round((total - (total * iva)) * 100.0) / 100.0 + " </td>\n"
                    + "    </tr>";
            HTML += "<tr>\n"
                    + "         <td colspan='4'> <strong> Iva: </strong></td>\n"
                    + "         <td> $ " + Math.round((total * iva) * 100.0) / 100.0 + " </td>\n"
                    + "    </tr>";
            HTML += "<tr>\n"
                    + "         <td colspan='4'> <strong> Total Ventas: </strong></td>\n"
                    + "         <td> $ " + Math.round(total * 100.0) / 100.0 + " </td>\n"
                    + "    </tr>"
                    + "  </tbody>\n"
                    + "</table>";
            if (!data.getString("qrImage").equals("no image")) {
                HTML += "<div class='row'>"
                        + "<div class='col-md-12'>"
                        + " <img class='rounded center' src='" + data.getString("qrImage") + "' alt='Venta " + venta.getIntidcostousuario().getStrcedula() + " QR Code' width='200' height='200'>"
                        + "<p class='center text-center'>" + new Date().toString() + "</p>"
                        + "</div>"
                        + "</div>";
            }
        } catch (Exception ex) {

        }
        return HTML;
    }

    public String getHTMLPDFVentaRegistroVenta(String JSONRegistroVenta) {
        String HTML = "";
        try {
            HTML += "<html>\n"
                    + "    <head>\n"
                    + "        <title>TODO supply a title</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" >\n"
                    + "\n"
                    + "        <style>\n"
                    + "            .center {\n"
                    + "                display: block;\n"
                    + "                margin-left: auto;\n"
                    + "                margin-right: auto;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>";
            HTML += "    <body>\n";
            HTML += "\n";
            HTML += "        <div class='container'>\n";
            HTML += "\n";

            //Encabezado
            HTML += encabezadoImagenEspoch();

            HTML += "\n";
            HTML += "            <div class='row'>\n";
            HTML += tableDataVenta(JSONRegistroVenta);
            HTML += tableDetalleVenta(JSONRegistroVenta);
            HTML += "            </div>\n";
            HTML += "    </body>\n";
            HTML += "</html>";

        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.getHTMLPDFVentaRegistroVenta() " + ex);
        }
        return HTML;
    }

    private String getHTMLHeaderImpVenta(Integer porcentaje) {
        return " <!DOCTYPE html> \n"
                + " <html lang=\"es\"> \n"
                + " <head> \n"
                + " <meta charset=\"UTF-8\" /> \n"
                + " <meta http-equiv=Content-Type content=text/html; charset=utf-8 /> \n"
                + " <title>Datos Compra</title> \n"
                + " \n"
                + " <style> \n"
                + "     .body { \n"
                + "         margin: auto; \n"
                + "         width: " + porcentaje + "%; \n"
                + "         border: 1px solid #cfcfcf; \n"
                + "         border-radius: 5px; \n"
                + "         padding: 10px; \n"
                + "     } \n"
                + " \n"
                + "    .center img { \n"
                + "        display: block; \n"
                + "        margin-left: auto; \n"
                + "        margin-right: auto; \n"
                + "        width: 150px; \n"
                + "        height: 150px; \n"
                + "    } \n"
                + " \n"
                + "    .text-center{ \n"
                + "        text-align: center; \n"
                + "    } \n"
                + " \n"
                + "    .tablaDatos .datos { \n"
                + "        margin-top: 30px; \n"
                + "        margin-left: 20px; \n"
                + "        margin-bottom: 10px; \n"
                + "    } \n"
                + " \n"
                + "    .tablaDatos .datos th{ \n"
                + "        text-align: left; \n"
                + "        padding-right: 20px; \n"
                + "        padding-bottom: 10px; \n"
                + "    } \n"
                + " \n   "
                + "    .tablaDatos .datos td{ \n"
                + "        text-align: justify; \n"
                + "        padding-right: 20px; \n"
                + "        padding-bottom: 10px; \n"
                + "    } \n"
                + " \n"
                + "    .tablaDatos .venta { \n"
                + "        margin-top: 35px; \n"
                + "        margin-left: 20px; \n"
                + "        margin-bottom: 20px; \n"
                + "        width: 90%; \n"
                + "        border-collapse: collapse; \n"
                + "    } \n"
                + " \n"
                + "    .tablaDatos .venta td, .tablaDatos .venta th { \n"
                + "        border: 1px solid #dddddd; \n"
                + "        text-align: left; \n"
                + "        padding: 8px; \n"
                + "    } \n"
                + " \n"
                + "    .tablaDatos .venta tr:nth-child(even) { \n"
                + "        background-color: #dddddd; \n"
                + "    } \n"
                + " \n"
                + "</style> \n"
                + " \n"
                + "</head> \n";
    }

    private String getHTMLBodyImpVenta(String JSONRegistroVenta, Boolean logo) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONRegistroVenta);
            Double iva = 0.12;
            Venta venta = gson.fromJson(data.getString("dataVenta"), Venta.class
            );
            Persona persona = gson.fromJson(data.getString("datosUsuario"), Persona.class
            );

            HTML += " <body class=\"body\">\n"
                    + " \n "
                    + " <div class=\"center\">\n";
            if (logo) {
                HTML += "<img class='center' src='data:image/png;base64, " + getImageEspoch("C:\\Users\\alex4\\Documents\\NetBeansProjects\\testBarcode\\src\\main\\webapp\\escudo_espoch.png") + "' alt='' width='100' height='100'>";
            }
            HTML += "     <p class=\"text-center\">  ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO   </p>\n"
                    + "     <p class=\"text-center\">  RUC # 066001250001   </p>\n"
                    + "     <p class=\"text-center\">  PANAMERICANA SUR KM 1/2   </p>\n"
                    + "     <p class=\"text-center\"> A. SRI 1121901577 </p>\n"
                    + " </div>\n"
                    + " <div class=\"tablaDatos\">\n"
                    + "  \n"
                    + "     <table class=\"datos\">\n"
                    + "         <tr>\n"
                    + "             <th colspan=\"2\"> Cédula </th>\n"
                    + "             <td colspan=\"4\">" + venta.getIntidcostousuario().getStrcedula() + "</td>\n"
                    + "             <th colspan=\"2\" > Nombre </th>\n"
                    + "             <td colspan=\"4\"> " + persona.getPer_nombres() + " " + persona.getPer_primerApellido() + " " + persona.getPer_segundoApellido() + " </td>\n"
                    + "         </tr>\n"
                    + "         <tr>\n"
                    + "             <th colspan=\"2\" > Correo </th>\n"
                    + "             <td colspan=\"4\"> " + persona.getPer_email() + " </td>\n"
                    + "             <th colspan=\"2\" > Fecha </th>\n"
                    + "             <td colspan=\"4\"> " + utilidades.fecha(venta.getDtfecha()) + " </td>\n"
                    + "         </tr>\n"
                    + "         <tr>\n"
                    + "             <th colspan=\"2\"> Tipo de Usuario </th>\n"
                    + "             <td colspan=\"4\"> " + venta.getIntidcostousuario().getIntidcosto().getIntidtipousuario().getStrtipo() + " </td>\n"
                    + "         </tr>\n"
                    + "     </table>\n"
                    + " \n"
                    + "     <table class=\"venta\">\n"
                    + "         <thead>\n"
                    + "             <tr>\n"
                    + "                 <th>Cantidad</th>\n"
                    + "                 <th colspan=\"2\">Detalle</th>\n"
                    + "                 <th>Precio Unitario</th>\n"
                    + "                 <th>Total</th>\n"
                    + "             </tr>\n"
                    + "         </thead>\n"
                    + "         <tbody>\n"
                    + "             <tr>\n"
                    + "                 <td>" + venta.getIntcantidad() + "</td>\n"
                    + "      <td colspan='2'> " + venta.getIntidcostousuario().getIntidcosto().getStrdetalle() + "</td>\n";

            Double precioUnitario = Math.round(venta.getIntidcostousuario().getIntidcosto().getMnvalor()) * 100.0 / 100.0;
            precioUnitario -= (precioUnitario * iva);
            Double total = (Math.round(precioUnitario * venta.getIntcantidad()) * 100.0 / 100.0);
            HTML += "      <td>" + precioUnitario.toString() + "</td>\n"
                    + "      <td>" + total + "</td>\n"
                    + "    </tr>\n";
            HTML += "<tr>\n"
                    + "         <td colspan='4'> <strong> Subtotal: </strong></td>\n"
                    + "         <td> $ " + Math.round((total - (total * iva)) * 100.0) / 100.0 + " </td>\n"
                    + "    </tr>";
            HTML += "<tr>\n"
                    + "         <td colspan='4'> <strong> Iva: </strong></td>\n"
                    + "         <td> $ " + Math.round((total * iva) * 100.0) / 100.0 + " </td>\n"
                    + "    </tr>";
            HTML += "<tr>\n"
                    + "         <td colspan='4'> <strong> Total Ventas: </strong></td>\n"
                    + "         <td> $ " + Math.round(total * 100.0) / 100.0 + " </td>\n"
                    + "    </tr>"
                    + "  </tbody>\n"
                    + "</table>";
            HTML += "  \n"
                    + "     <div class=\"center\">\n"
                    + " <img class='rounded center' src='" + data.getString("qrImage") + "' alt='Venta " + venta.getIntidcostousuario().getStrcedula() + " QR Code' width='200' height='200'>"
                    + "<p class='center text-center'>" + new Date().toString() + "</p>"
                    + "     </div>\n"
                    + " \n"
                    + " \n"
                    + " </body>\n"
                    + " </html>\n";
        } catch (Exception ex) {
            System.out.println("com.comedorui.ReporteVentasUI.getHTMLBodyImpVenta() " + ex);
        }
        return HTML;
    }

    private String getHTMLBodyReporte(String identificador, String JSONReporte, Boolean logo) {
        String HTML = "";
        try {

            HTML += " <body class=\"body\">\n"
                    + " \n "
                    + " <div class=\"center\">\n";
            if (logo) {
                HTML += "<img class='center' src='data:image/png;base64, " + getImageEspoch("C:\\Users\\alex4\\Documents\\NetBeansProjects\\testBarcode\\src\\main\\webapp\\escudo_espoch.png") + "' alt='' width='100' height='100'>";
            }
            HTML += "     <p class=\"text-center\">  ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO   </p>\n"
                    + "     <p class=\"text-center\">  RUC # 066001250001   </p>\n"
                    + "     <p class=\"text-center\">  PANAMERICANA SUR KM 1/2   </p>\n"
                    + "     <p class=\"text-center\"> A. SRI 1121901577 </p>\n"
                    + " </div>\n"
                    + " <div class=\"tablaDatos\">\n"
                    + "  \n";

            HTML += getHTMLInfoReporte(identificador, JSONReporte)
                    + " \n";

            HTML += getHTMLDetalleReporte(JSONReporte);

            HTML += "  \n"
                    + " </body>\n"
                    + " </html>\n";
        } catch (Exception ex) {
            System.out.println("com.comedorui.ReporteVentasUI.getHTMLBodyImpVenta() " + ex);
        }
        return HTML;
    }

    private String getHTMLInfoReporte(String identificador, String JSONReporte) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject dataReporte = new JSONObject(JSONReporte);
            HTML = "     <table class=\"datos\">\n";
            JSONObject reporte = new JSONObject(dataReporte.getString("dataReporte"));
            if (identificador.equals("ventasDiario")) {

                Date fechaVenta = utilidades.stringToDate("yyyy-MM-dd", dataReporte.getString("fechaVenta"));
                HTML += "         <tr>\n"
                        + "             <th colspan=\"10\"> Ventas del día </th>\n"
                        + "         </tr>\n";

                HTML += "         <tr>\n"
                        + "             <th colspan=\"2\" > Fecha </th>\n"
                        + "             <td colspan=\"3\"> " + utilidades.fecha(fechaVenta) + " </td>\n"
                        + "             <th colspan=\"3\" > Número de Ventas del día </th>\n"
                        + "             <td colspan=\"2\"> " + reporte.getInt("cantidadVentas") + " Tickets </td>\n"
                        + "         </tr>\n";
            } else if (identificador.equals("intervaloFechas")) {
                Date fechaInicio = utilidades.stringToDate("yyyy-MM-dd", dataReporte.getString("fechaInicio"));
                Date fechaFin = utilidades.stringToDate("yyyy-MM-dd", dataReporte.getString("fechaFin"));
                HTML += "         <tr>\n"
                        + "             <th colspan=\"15\"> Ventas en un rango de fechas </th>\n"
                        + "         </tr>\n";
                HTML += "         <tr>\n"
                        + "             <th colspan=\"2\" > Fecha Inicio </th>\n"
                        + "             <td colspan=\"3\"> " + utilidades.fecha(fechaInicio) + " </td>\n"
                        + "             <th colspan=\"2\" > Fecha Fin </th>\n"
                        + "             <td colspan=\"3\"> " + utilidades.fecha(fechaFin) + " </td>\n"
                        + "             <th colspan=\"3\" > Número de Ventas del día </th>\n"
                        + "             <td colspan=\"2\"> " + reporte.getInt("cantidadVentas") + " Tickets </td>\n"
                        + "         </tr>\n";
            } else if (identificador.equals("reporteIntervaloFechasMenu")) {

                JSONObject dataTipoMenu = new JSONObject(dataReporte.getString("dataTipoMenu"));
                Tipomenu tipoMenu = gson.fromJson(dataTipoMenu.getString("tipoMenu"), Tipomenu.class
                );

                Date fechaInicio = utilidades.stringToDate("yyyy-MM-dd", dataReporte.getString("fechaInicio"));
                Date fechaFin = utilidades.stringToDate("yyyy-MM-dd", dataReporte.getString("fechaFin"));
                HTML += "         <tr>\n"
                        + "             <th colspan=\"10\"> Ventas por tipo de menú </th>\n"
                        + "         </tr>\n";
                HTML += "         <tr>\n"
                        + "             <th colspan=\"2\" > Fecha Inicio </th>\n"
                        + "             <td colspan=\"3\"> " + utilidades.fecha(fechaInicio) + " </td>\n"
                        + "             <th colspan=\"2\" > Fecha Fin </th>\n"
                        + "             <td colspan=\"3\"> " + utilidades.fecha(fechaFin) + " </td>\n"
                        + "         </tr>\n";
                HTML += "         <tr>\n"
                        + "             <th colspan=\"3\" > Tipo de menú </th>\n"
                        + "             <td colspan=\"2\"> " + tipoMenu.getStrtipo() + " </td>\n"
                        + "             <th colspan=\"3\" > Número de Ventas del día </th>\n"
                        + "             <td colspan=\"2\"> " + reporte.getInt("cantidadVentas") + " Tickets </td>\n"
                        + "         </tr>\n";

            } else if (identificador.equals("reporteDatausuarioFechas")) {
                Date fechaInicio = utilidades.stringToDate("yyyy-MM-dd", dataReporte.getString("fechaInicio"));
                Date fechaFin = utilidades.stringToDate("yyyy-MM-dd", dataReporte.getString("fechaFin"));
                HTML += "         <tr>\n"
                        + "             <th colspan=\"10\"> Ventas del Usuario por fechas </th>\n"
                        + "         </tr>\n";
                HTML += "         <tr>\n"
                        + "             <th colspan=\"2\" > Fecha Inicio </th>\n"
                        + "             <td colspan=\"3\"> " + utilidades.fecha(fechaInicio) + " </td>\n"
                        + "             <th colspan=\"2\" > Fecha Fin </th>\n"
                        + "             <td colspan=\"3\"> " + utilidades.fecha(fechaFin) + " </td>\n"
                        + "         </tr>\n";

                Persona persona = gson.fromJson(reporte.getString("datosPersona"), Persona.class);

                HTML += "         <tr>\n"
                        + "             <th colspan=\"3\" > Nombres </th>\n"
                        + "             <td colspan=\"2\"> " + persona.getPer_nombres() + " " + persona.getPer_primerApellido() + " " + persona.getPer_segundoApellido() + " </td>\n"
                        + "             <th colspan=\"3\" > Número de Ventas: </th>\n"
                        + "             <td colspan=\"2\"> " + reporte.getInt("cantidadVentas") + " Tickets </td>\n";
                HTML += "         </tr>\n"
                        + "         <tr>\n"
                        + "             <th colspan=\"3\" > Total de ventas: </th>\n"
                        + "             <td colspan=\"2\"> " + reporte.getInt("totalVentas") + " $  </td>\n";
                HTML += "         </tr>\n";

            }
            HTML += "     </table>\n";
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.getHTMLInfoReporte() " + ex);
        }
        return HTML;
    }

    private String getHTMLDetalleReporte(String JSONReporte) {
        String HTML = "";
        try {
            JSONObject dataReporte = new JSONObject(JSONReporte);
            HTML += "   <table class=\"venta\">\n"
                    + "         <thead>\n"
                    + "     <tr>\n"
                    + "             <th>Detalle</th>\n"
                    + "             <th>Cantidad</th>\n"
                    + "             <th>Precio Unitario</th>\n"
                    + "             <th>Total</th>\n"
                    + "         </tr>\n"
                    + "     </thead>";
            HTML += "       <tbody>";
            HTML += toHTMLListVentas(dataReporte.getString("dataReporte"));
            HTML += toHTMLResultListVenta(dataReporte.getString("dataReporte"));
            HTML += "       </tbody>";
            HTML += "    </table>";
        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.getHTMLDetalleReporte() " + ex);
        }
        return HTML;
    }

    public String HTMLImpVenta(String JSONRegistroVenta, Integer porcentaje, Boolean logo) {
        return getHTMLHeaderImpVenta(porcentaje) + getHTMLBodyImpVenta(JSONRegistroVenta, logo);
    }

    public String getPdfReporte(String identificador, String JSONReporte, Boolean logo) {
        String FILE = "No existe";
        PrintUtilidades printUtilidades = new PrintUtilidades();
        try {
//            String html = getHTMLHeaderImpVenta(100) + getHTMLBodyReporte(identificador, JSONReporte, logo);
            String html = getPrintHTMLReporte(identificador, JSONReporte, logo);
            FILE = printUtilidades.transformHTMltoPDF(html);

        } catch (Exception ex) {
            System.err.println("com.comedorui.ReporteVentasUI.getPdfReporte() " + ex);
        }
        return FILE;
    }

    public String getPrintHTMLReporte(String identificador, String JSONReporte, Boolean logo) {
        return getHTMLHeaderImpVenta(100) + getHTMLBodyReporte(identificador, JSONReporte, logo);
    }

}
