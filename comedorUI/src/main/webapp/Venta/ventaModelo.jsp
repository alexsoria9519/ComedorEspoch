<%-- 
    Document   : ventaModelo
    Created on : 14/06/2019, 12:24:48
    Author     : corebitsas
--%>

<%@page import="servicios.ComedorWS"%>
<%@page import="entities.TipoUsuarios"%>
<%@page import="servicios.TipoMenuWS"%>
<%@page import="entities.TipoMenus"%>
<%@page import="entities.Costos"%>
<%@page import="entities.VentaProcedure"%>
<%@page import="java.util.Date"%>
<%@page import="entities.CostoProcedure"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonParser"%>
<%@page import="entities.CostosUsuarios"%>
<%@page import="entities.Menus"%>
<%@page import="EspochWS.Estudiante"%>
<%@page import="servicios.TipoUsuarioWS"%>
<%@page import="servicios.CostoWS"%>
<%@page import="entities.Tipousuario"%>
<%@page import="entities.Costo"%>
<%@page import="EspochWS.Persona"%>
<%@page import="entities.Costousuario"%>
<%@page import="servicios.CostoUsuarioWS"%>
<%@page import="servicios.VentaWS"%>
<%@page import="entities.Venta"%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.google.gson.Gson"%>

<%@page import="entities.Menu"%>
<%@page import="servicios.MenuWS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");
    String dataTipoUsuario = (String) session.getAttribute("tipoUsuario");
    String strTipoUsuario = "Funcionario";

    Gson gson = new Gson();
    JSONObject resultJSON = new JSONObject();
    JSONObject requestJSON = new JSONObject();

    Venta venta = new Venta();
    VentaWS ventaWs = new VentaWS();
    CostoUsuarioWS costoUsuarioWS = new CostoUsuarioWS();
    Costousuario costousuario = new Costousuario();
    Costos costos = new Costos();

    MenuWS menuWs = new MenuWS();
    Menu menu = new Menu();
    CostoWS costows = new CostoWS();

    TipoUsuarioWS tipoUsuarioWS = new TipoUsuarioWS();

    ComedorWS comedorWs = new ComedorWS();
    String respuestaListado = "";

    String resAll;
    String messageError = "Error";

    if (accion != null) {

        try {
            if (accion.equals("costoVenta")) {
                //Menu menu = new Menu();

                menu = gson.fromJson(data, Menu.class);

                menu = menuWs.find(Menu.class, menu.getIntidmenu().toString());

                resAll = "";
//                resAll = costows.findByStrDetalle(String.class, menu.getIntidtipo().getStrtipo() + " " + dataTipoUsuario);
                resultJSON.put("datosCosto", resAll);

                //session.setAttribute("respuestalista", "{ \"ventas\" : " + resAll + " }");
                //resultJSON.put("message", "Listado Correcto");
            } else if (accion.equals("formularioVenta")) {
                String cedula = data;

                resAll = comedorWs.reservasUsuario(cedula);

                JSONObject respReserva = new JSONObject(resAll);

                if (respReserva.getString("success").equals("ok")) {
                    resultJSON.put("reservas", respReserva.getString("ventas"));
                    resultJSON.put("cantidadReservas", respReserva.getInt("cantidad"));
                } else {
                    resultJSON.put("reservas", "[]");
                    resultJSON.put("cantidadReservas", 0);
                }
                resAll = comedorWs.datosFormularioVenta(cedula);
                resultJSON.put("formulario", resAll);

            } else if (accion.equals("getCostoUsuario")) {
                String idCostoUsuario = data;
                respuestaListado = comedorWs.getCostoUsuario(idCostoUsuario);
            } else if (accion.equals("reporteTipoFecha")) {
                String datosReporte = "";
                VentaProcedure ventaProcedure = new VentaProcedure();
                TipoMenus tipoMenus = new TipoMenus();
                TipoMenuWS tipoMenuWS = new TipoMenuWS();
                TipoUsuarios tipoUsuarios = new TipoUsuarios();

                ventaProcedure = gson.fromJson(data, VentaProcedure.class);

                tipoMenus = tipoMenuWS.findAll(TipoMenus.class);
                tipoUsuarios = tipoUsuarioWS.findAll(TipoUsuarios.class);

                for (int i = 0; i < tipoMenus.getTipoMenus().size(); i++) {
                    ventaProcedure.setTipomenu(tipoMenus.getTipoMenus().get(i).getStrtipo());
                    for (int j = 0; j < tipoUsuarios.getTipoUsuarios().size(); j++) {
                        ventaProcedure.setTipousuario(tipoUsuarios.getTipoUsuarios().get(j).getStrtipo());
                        datosReporte += ventaWs.ventasPorFecha(ventaProcedure);
                    }
                }

            } else {

                venta = gson.fromJson(data, Venta.class);

                if (accion.equals("registrarVenta")) {

                    venta = gson.fromJson(data, Venta.class);
                    venta.setBlnreserva(false);
                    requestJSON.put("venta", gson.toJson(venta));

                    resAll = comedorWs.insertVenta(requestJSON.toString());

                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    resultJSON.put("idVenta", respJson.getInt("idVenta"));
                    resultJSON.put("dataVenta", respJson.getString("dataVenta"));
                    resultJSON.put("datosUsuario", respJson.getString("datosUsuario"));
                    messageError = "Error en el ingreso la venta";

                } else if (accion.equals("reporteFecha")) {
                    resAll = ventaWs.findByFecha(venta, String.class);
                    resultJSON.put("datosBusquedaFecha", resAll);
                    resultJSON.put("message", "Modificación correcta");
                    messageError = "Error en la modificacion";

                } else if (accion.equals("eliminarLogico")) {

                } else if (accion.equals("buscarid")) {
                    /*
                    resAll = menuWs.find(String.class, menu.getIntidmenu().toString());
                    resultJSON.put("success", "Correcto");
                    resultJSON.put("message", "Modificación correcta");
                    session.setAttribute("respuestalista", resAll);
                     */
                    //resultJSON.put("message", "Búsqueda del id: " + menu.getIntidmenu().toString() + " correcta");
                } else if (accion.equals("formularioedicion")) {
                    /*
                    resAll = tipoMenuWS.findAll(String.class);
                    session.setAttribute("listadotiposmenu", "{ \"tipoMenus\" : " + resAll + " }");

                    resAll = menuWs.find(String.class, menu.getIntidmenu().toString());
                    session.setAttribute("respuestalista", resAll);
                    
                    resultJSON.put("success", "Correcto");
                    resultJSON.put("message", "Búsqueda del id: " + menu.getIntidmenu().toString() + " correcta");
                     */
                }
            }

        } catch (Exception e) {
            System.err.println(e);
            resultJSON.put("error", "Error de " + e);
            resultJSON.put("message", messageError);
        }
        session.setAttribute("respuesta", resultJSON.toString());
        session.setAttribute("respuestalista", respuestaListado);
        response.sendRedirect("ventaControlador.jsp");

    }


%>
