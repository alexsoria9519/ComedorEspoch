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
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import entities.Costo;
import entities.CostosUsuarios;
import entities.Costousuario;
import entities.Menus;
import entities.Venta;
import entities.Ventas;
import java.util.Date;
import org.json.JSONObject;
import servicios.ComedorWS;

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
        String form = "";

        try {
            JSONObject resp = new JSONObject(respuestaJSON);

            if (resp.getInt("cantidadReservas") > 0) {
                form = formularioVentaReserva(respuestaJSON);
            } else {
                form = formularioVenta(resp.getString("formulario"));
            }

        } catch (Exception ex) {

        }
        return form;
    }

    public String formularioVentaReserva(String respuestaJSON) {
        String form = "<h3> Registrar una venta  -- Reserva </h3>";
        try {
            JSONObject resp = new JSONObject(respuestaJSON);
            form += datosPrincipalesVenta(resp.getString("formulario"));

            form += "<form class='lead col-lg-10' id='formulario' method='post'>\n"
                    + "                            "
                    + "\n"
                    + "                            <div class='col-lg-12 form-inline'> "
                    + "                                <div class='form-group col-lg-6 '> "
                    + "                                    <label for='menu'>Menú: </label>\n";
//        form += selectListadoMenus(objJson.get("listadoMenus").getAsString(), objJson);

            form += "                                </div>\n";
            form += "                              </div>\n";

            Ventas ventas = gson.fromJson("{ \"ventas\" : " + resp.getString("reservas") + " }", Ventas.class);
            Integer idCUReserva = ventas.getVentas().get(0).getIntidcostousuario().getIntidcostousuario();
            form += selectCostosUsuarios(resp.getString("formulario"), idCUReserva);

            form += "                            </div>\n"
                    + "\n"
                    + "                            <div class='col-lg-12 form-inline '> "
                    + "                                <div class='form-group col-lg-6 '> "
                    //                    + "                                    <button type='button' class='btn  btn-success' onclick='registrarVenta(event,'" + objJson.get("cedula").getAsString() + "','" + objJson.get("tipoUsuario").getAsString() + "'," + objJson.get("existeCostoUsuario").getAsBoolean() + ")'>Guardar <i class='fa fa-check' aria-hidden='true'></i></button> "
                    + "                                    <button type='button' class='btn  btn-success' onclick='registrarVenta(event)'>Guardar <i class='fa fa-check' aria-hidden='true'></i></button> "
                    + "                                </div>\n"
                    + "\n"
                    + "                                <div class='form-group col-lg-6'> "
                    + "                                    <button type='' class='btn   btn-danger'>Cancelar <i class='fa fa-times' aria-hidden='true'></i></button> "
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </form>";
        } catch (Exception ex) {

        }
        return form;
    }

    public String formularioVenta(String respuestaJSON) {
        String form = "<h3> Registrar una venta </h3>";
        try {
            form += datosPrincipalesVenta(respuestaJSON);

            form += "<form class='lead col-lg-10' id='formulario' method='post'>\n"
                    + "                            "
                    + "\n"
                    + "                            <div class='col-lg-12 form-inline'> "
                    + "                                <div class='form-group col-lg-6 '> "
                    + "                                    <label for='menu'>Menú: </label>\n";
//        form += selectListadoMenus(objJson.get("listadoMenus").getAsString(), objJson);

            form += "                                </div>\n";
            form += "                              </div>\n";
            form += selectCostosUsuarios(respuestaJSON, 0);

            form += "                            </div>\n"
                    + "\n"
                    + "                            <div class='col-lg-12 form-inline '> "
                    + "                                <div class='form-group col-lg-6 '> "
                    //                    + "                                    <button type='button' class='btn  btn-success' onclick='registrarVenta(event,'" + objJson.get("cedula").getAsString() + "','" + objJson.get("tipoUsuario").getAsString() + "'," + objJson.get("existeCostoUsuario").getAsBoolean() + ")'>Guardar <i class='fa fa-check' aria-hidden='true'></i></button> "
                    + "                                    <button type='button' class='btn  btn-success' onclick='registrarVenta(event)'>Guardar <i class='fa fa-check' aria-hidden='true'></i></button> "
                    + "                                </div>\n"
                    + "\n"
                    + "                                <div class='form-group col-lg-6'> "
                    + "                                    <button type='' class='btn   btn-danger'>Cancelar <i class='fa fa-times' aria-hidden='true'></i></button> "
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                        </form>";
        } catch (Exception ex) {

        }
        return form;
    }

    private String datosPrincipalesVenta(String respuestaJSON) {
        Date fechaActual = new Date();
        Utilidades utilidades = new Utilidades();
        Gson gson = new Gson();
        String HTML = "<p> Usted puede registrar los datos de una nueva venta </p>";

        try {
            JSONObject dataJSON = new JSONObject(respuestaJSON);
            Persona persona = gson.fromJson(dataJSON.getString("datosUsuario"), Persona.class);
            HTML = "                            <div class='col-lg-12'> "
                    + "                                <div class='col-lg-4 '> "
                    + "                                    <h4> Cédula: </h4>\n"
                    + "                                    <p id='cedula'>" + dataJSON.getString("cedula") + "</p>\n"
                    + "                                </div>\n"
                    + "\n"
                    + "                                <div class='col-lg-4'> "
                    + "                                    <h4> Nombres: </h4>\n"
                    + "                                    <p> " + persona.getPer_nombres() + " </p>\n"
                    + "                                </div>\n"
                    + "                                <div class='col-lg-4'> "
                    + "                                    <h4> Apellidos: </h4>\n"
                    + "                                    <p> " + persona.getPer_primerApellido() + " " + persona.getPer_segundoApellido() + " </p>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                            <div class='col-lg-12 '> "
                    + "                                <div class='form-group col-lg-4 '> "
                    + "                                    <h4> Tipo: </h4>\n"
                    + "                                     <p id='tipoUsuario'> " + dataJSON.getString("tipoUsuario") + " </p>\n"
                    + "                                    "
                    + "                                </div>\n"
                    + "\n"
                    + "                                <div class=' col-lg-4'> "
                    + "                                    <h4> Fecha: </h4>\n"
                    + "                                    <p> " + utilidades.fecha(fechaActual) + " </p>\n"
                    + "                                </div>\n"
                    + "                                <div class=' col-lg-4'> "
                    + "                                    <h4> Cantidad: </h4>\n"
                    + "                                    <p>  1  </p>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "</br>";
        } catch (Exception ex) {

        }
        return HTML;
    }

    private String selectCostosUsuarios(String respuestaJSON, Integer idCostosUsuarioReserva) {
        String SELECT = "<div class='col-lg-12 form-inline'>"
                + "<div class='form-group col-lg-6 '> ";
        try {
            JSONObject resquestJson = new JSONObject(respuestaJSON);
            CostosUsuarios costosUsuarios = gson.fromJson(resquestJson.getString("costosUsuario"), CostosUsuarios.class);

            if (costosUsuarios.getCostosUsuarios().size() > 0) {
                SELECT += "                                    <label for='fecha'>Costo: </label>\n"
                        + "                                    <input type='text' readonly class='form-control' id='costoUsuario' name='costoUsuario' value='" + costosUsuarios.getCostosUsuarios().get(0).getIntidcosto().getMnvalor() + "' placeholder='Ingrese el costo del servicio'/>\n"
                        + "                                </div>\n";

                SELECT += "                                <div class='form-group col-lg-6'> \n"
                        + "                                    <label for='selectCostoUsuario'>Tipo de Usuario </label>\n"
                        + "                                    <select onchange='getCostoUsuario()' id='selectCostoUsuario'>\n";
                for (Costousuario costosUsuario : costosUsuarios.getCostosUsuarios()) {
                    String tipoMenu = costosUsuario.getIntidcosto().getIntidtipomenu().getStrtipo();
                    String tipoUsuario = costosUsuario.getIntidcosto().getIntidtipousuario().getStrtipo();
                    if (idCostosUsuarioReserva == costosUsuario.getIntidcostousuario()) {
                        SELECT += "<option selected value='" + costosUsuario.getIntidcostousuario() + "' > " + tipoMenu + " - " + tipoUsuario + " </option>     \n";
                    } else {
                        SELECT += "<option  value='" + costosUsuario.getIntidcostousuario() + "' > " + tipoMenu + " - " + tipoUsuario + " </option>     \n";
                    }

                }
                SELECT += "                                     </select>"
                        + "                               </div>";
            }
        } catch (Exception ex) {

        }
        return SELECT + "</div>\n";
    }

    public String busquedaDatosCedula() {
        String form = "<h3> Registrar una venta </h3>"
                + "<p> Usted puede registrar los datos de una nueva venta </p>";

        form += "</br>"
                + "<form class='lead col-lg-10' id='formulario' method='post'>\n"
                + "                            "
                + "\n"
                + "                                <div class='form-group col-lg-12 '> "
                + "                                    <label for='cedula'>Cédula:</label>\n"
                + "                                    <input  type='text' class='form-control busquedaDatos' id='cedula' name='cedula' placeholder='1234567890' autofocus />\n"
                + "                                    "
                + "                                </div>\n"
                + "\n"
                + "                                <div class='form-group col-lg-12 '> "
                + "                                    <button type='submit' class='btn  btn-success' onclick='formularioVenta(event)'>Guardar <i class='fa fa-check' aria-hidden='true'></i></button> "
                + "                                </div>\n"
                + "                        </form>";
        return form;
    }

    public String selectListadoMenus(String listadoMenus, JsonObject respuestaJSON) {

        try {
            String strlistadosMenus = "<select onchange='costos('" + respuestaJSON.get("tipoUsuario").getAsString() + "')' id='listadosMenus'>\n";

            Menus menus = new Menus();

            menus = gson.fromJson(listadoMenus, Menus.class);

            for (int i = 0; i < menus.getMenus().size(); i++) {
                strlistadosMenus += "<option value='" + menus.getMenus().get(i).getIntidmenu().toString() + "'>" + menus.getMenus().get(i).getStrcaracteristicas() + "</option>\n";
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

    public String resultRegistrosVenta(String JSONRegistroVenta) {
        String HTML = "";
        JSONObject resp = new JSONObject();
        try {

            HTML = toHTMLHeaderRespVenta();
            resp.put("success", "ok");
            resp.put("headerModal", HTML);
            resp.put("bodyModal", toHTMLBodyRespVenta(JSONRegistroVenta));
            resp.put("modalFooter", toHTMLFooterVenta());
            sendMail(JSONRegistroVenta); // Enviar un correo al usuario con el registro de la venta
        } catch (Exception ex) {
            resp.put("success", "error");
            resp.put("data", "Error al registrar la venta");
        }
        return resp.toString();
    }

    private String toHTMLHeaderRespVenta() {
        String HTML = "";
        HTML += "<h5 class='modal-title'>Venta Registrada</h5>"
                //                + "<button type='button' class='btn btn-info' onclick='pdfRegistroVenta(event)'>PDF</button>"
                + "  <button type='button' class='btn  btn-info' onclick='imprimirRegistroVenta()'>Imprimir <i class='fa fa-check' aria-hidden='true'></i></button> "
                + "  <button type='button' class='btn  btn-success' onclick='pdfRegistroVenta()'>PDF <i class='fa fa-check' aria-hidden='true'></i></button> "
                + "<button type='button' class='close' data-dismiss='modal' aria-label='Close'>"
                + "    <span aria-hidden='true'>&times;</span>"
                + "</button>";
        return HTML;
    }

    private String toHTMLBodyRespVenta(String JSONRegistroVenta) {
        String HTML = "";
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject data = new JSONObject(JSONRegistroVenta);

            Venta venta = gson.fromJson(data.getString("dataVenta"), Venta.class);
            Persona persona = gson.fromJson(data.getString("datosUsuario"), Persona.class);
            Double iva = 0.12;
            HTML = "<div class='row'>"
                    + "<div class='col-md-4'>"
                    + "    <h5> Cédula </h5>"
                    + "</div>"
                    + "<div class='col-md-8'>"
                    + "<p>" + venta.getIntidcostousuario().getStrcedula() + "</p>"
                    + "</div>"
                    + "<div class='col-md-4'>"
                    + "    <h5> Nombre </h5>"
                    + "</div>"
                    + "<div class='col-md-8'>"
                    + "<p>" + persona.getPer_nombres() + persona.getPer_primerApellido() + persona.getPer_segundoApellido() + "</p>"
                    + "</div>"
                    + "<div class='col-md-4'>"
                    + "    <h5> Correo </h5>"
                    + "</div>"
                    + "<div class='col-md-8'>"
                    + "<p>" + persona.getPer_email() + "</p>"
                    + "</div>"
                    + "<div class='col-md-4'>"
                    + "    <h5> Fecha </h5>"
                    + "</div>"
                    + "<div class='col-md-8'>"
                    + "    <p>" + utilidades.fecha(venta.getDtfecha()) + "</p>"
                    + "</div>"
                    + "<div class='col-md-2'>"
                    + "    <h5> Tipo de usuario </h5>"
                    + "</div>"
                    + "<div class='col-md-4'>"
                    //                    + "    <p>" + datosVenta.intidcostousuario.intidcosto.intidtipousuario.strtipo + "</p>"
                    + "    <p>" + venta.getIntidcostousuario().getIntidcosto().getIntidtipousuario().getStrtipo() + "</p>"
                    + "</div>";

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
                    + "      <td>" + (total - iva) + "</td>\n"
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
            HTML += "</div>";
            if (!data.getString("qrImage").equals("no image")) {
                HTML += "<div class='row'>"
                        + "<div class='col-md-12'>"
                        + " <img class='rounded center' src='" + data.getString("qrImage") + "' alt='Venta " + venta.getIntidcostousuario().getStrcedula() + " QR Code' width='200' height='200'>"
                        + "<p class='center text-center'>" + new Date().toString() + "</p>"
                        + "</div>"
                        + "</div>";
            }

        } catch (Exception ex) {
            HTML = "<div class='row'>"
                    + "<table class='table'>\n"
                    + "  <thead class='thead-dark'>\n"
                    + "    <tr>\n"
                    + "    </tr>\n"
                    + "  </thead>\n"
                    + "  <tbody>\n"
                    + "    <tr>\n"
                    + "    </tr>\n"
                    + "  </tbody>\n"
                    + "</table>"
                    + "</div>";
        }
        return HTML;
    }

    private String toHTMLFooterVenta() {
        String HTML = "";
        HTML = "      <div class='modal-footer'>\n"
                //                + "        <button type=\"button\" class=\"btn btn-primary\">Save changes</button>\n"
                + "        <button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>\n"
                + "      </div>";
        return HTML;
    }

    public String pdfRegistroVenta(String JSONRegistroVenta) {
        String FILE = "No existe";
        PrintUtilidades printUtilidades = new PrintUtilidades();
        ReporteVentasUI reportes = new ReporteVentasUI();
        try {
            String html = reportes.getHTMLPDFVentaRegistroVenta(JSONRegistroVenta);
            FILE = printUtilidades.transformHTMltoPDF(html);
        } catch (Exception ex) {
            System.err.println("com.comedorui.VentaUI.pdfRegistroVenta() " + ex);
        }
        return FILE;
    }

    public void sendMail(String JSONRegistroVenta) {
        ReporteVentasUI reportes = new ReporteVentasUI();
        ComedorWS comedorWs = new ComedorWS();
        try {
            JSONObject jsonMail = new JSONObject();
            JSONObject data = new JSONObject(JSONRegistroVenta);
            Persona persona = gson.fromJson(data.getString("datosUsuario"), Persona.class);
//            String html = reportes.getHTMLPDFVentaRegistroVenta(JSONRegistroVenta);
            String html = reportes.HTMLImpVenta(JSONRegistroVenta, 50, true);
            jsonMail.put("destinatario", persona.getPer_email());
            jsonMail.put("asunto", "Compra Comedor Espoch");
            jsonMail.put("cuerpo", html);
            comedorWs.enviarEmail(jsonMail.toString());
        } catch (Exception ex) {
            System.err.println("com.comedorui.VentaUI.sendMail()");
        }
    }

}
