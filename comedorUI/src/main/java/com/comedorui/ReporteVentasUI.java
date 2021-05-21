/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedorui;

import Utilities.Utilidades;
import com.google.gson.Gson;
import entities.TipoMenus;
import entities.Tipomenu;
import entities.VentaProcedure;
import entities.VentasProcedure;
import java.util.Date;
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

    public String formularioReporteTipoMenu(String JSONTipoMenus) {
        String HTML = "";
        HTML += headerModalFormVentasTipoMenu();
        HTML += formModalVentasIntervaloMenu(JSONTipoMenus);
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
        HTML = "            <div class='col-md-12'> \n"
                + "               <div class='col-md-12'> \n"
                + "                   <p>  ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO   </p>"
                + "                   <p>  RUC # 066001250001   </p>"
                + "                   <p>  PANAMERICANA SUR KM 1/2   </p>"
                + "                   <p> A. SRI 1121901577 </p>"
                + "               </div>\n"
                + "               <div class='col-md-12'> \n"
                + "                   <h5>  REPORTE DIARIO   </h5>"
                + "               </div>\n";
        return HTML;
    }

    public String toHtmlVentasDiarias(Date fecha, String JSONReporte) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {

            JSONObject dataReporte = new JSONObject(JSONReporte);

            HTML += encabezadoReportes();

            if (dataReporte.getString("success").equals("ok")) {
                HTML += "               <div class='col-md-3'>\n"
                        + "                     <p> Fecha:    </p>"
                        + "               </div>\n"
                        + "               <div class='col-md-3'>\n"
                        + "                     <p> " + utilidades.fechaFormatoHtml(fecha) + " </p>"
                        + "               </div>\n"
                        + "               <div class='col-md-3'>\n"
                        + "                     <p> Número de Ventas del día:    </p>"
                        + "               </div>\n"
                        + "               <div class='col-md-3'>\n"
                        + "                     <p> " + dataReporte.getInt("cantidadVentas") + " Tickets </p>"
                        + "               </div>\n";
            }
            HTML += detalleListadoVenta(JSONReporte);

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

            HTML = "            <div class='col-md-12 table-responsive-md'> \n";

            HTML += "   <table class='table'>";
            HTML += "       <thead class='thead-dark'>\n"
                    + "     <tr>\n"
                    + "             <th>Detalle</th>\n"
                    + "             <th>Cantidad</th>\n"
                    + "             <th>Precio Unitario</th>\n"
                    + "             <th>Total</th>\n"
                    + "         </tr>\n"
                    + "     </thead>";
            HTML += "<tbody>";
            HTML += toHTMLListVentas(JSONReporte);
            HTML += toHTMLResultListVenta(JSONReporte);
            HTML += "</tbody>";
            HTML += "               </table>"
                    + "               </div>\n";

        } catch (Exception ex) {

        }

        return HTML;
    }

    private String toHTMLListVentas(String JSONReporte) {
        String HTML = "";
        try {
            JSONObject dataReporte = new JSONObject(JSONReporte);

            if (dataReporte.getInt("cantidadVentas") > 0) {

                VentasProcedure ventasDiarias = gson.fromJson("{ \"ventasProcedure\" : " + dataReporte.getString("ventasDiarias") + " }", VentasProcedure.class);

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
                    Tipomenu tipoMenu = gson.fromJson(dataTipoMenu.getString("tipoMenu"), Tipomenu.class);
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
}
