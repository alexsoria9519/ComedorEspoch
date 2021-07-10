<%-- 
    Document   : ventaVista
    Created on : 14/06/2019, 12:24:58
    Author     : corebitsas
--%>

<%@page import="com.comedorui.ReporteVentasUI"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonParser"%>

<%@page import="com.comedorui.VentaUI"%>
<%@page import="com.comedorui.MenuUI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    //String accion = (String) session.getAttribute("accion");
    String accion = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");

    String cedula = (String) session.getAttribute("cedula");
    String listadoMenus = (String) session.getAttribute("listadoMenus");
    String datosPersona = (String) session.getAttribute("datosPersona");
    String datosCostoUsuario = (String) session.getAttribute("datosCostoUsuario");

    JsonParser parser = new JsonParser();
    JsonElement elementoJson = parser.parse(respuestaJSON);
    JsonObject objJson = elementoJson.getAsJsonObject();

    VentaUI ventaUI = new VentaUI();

    if (accion != null) {
        if (accion.equals("formulario")) {
            response.setStatus(200);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

        } else {
            if (accion.equals("costoVenta")) {

                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                System.err.println(objJson.get("datosCosto").getAsString());
                response.getWriter().write(ventaUI.datosCosto(objJson.get("datosCosto").getAsString()).toString());
            } else if (accion.equals("registrarVenta")) {
                response.setStatus(201);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                session.setAttribute("dataResVenta", respuestaJSON);
                System.err.println("registrarVenta " + respuestaJSON);
//                response.getWriter().write(respuestaJSON);
                response.getWriter().write(ventaUI.resultRegistrosVenta(respuestaJSON));
            } else if (accion.equals("reporteFecha")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("formularioedicion")) {
                response.setStatus(200);
                response.setContentType("text/plain");
                //response.getWriter().write(menu.formularioEdicion(respuestaListado, listadoTiposMenu));
            } else if (accion.equals("eliminarLogico")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("formularioVenta")) {
                session.removeAttribute("dataResVenta");
                response.setStatus(200);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(ventaUI.formulario(respuestaJSON));
            } else if (accion.equals("getCostoUsuario")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaListado);
            } else if (accion.equals("formularioReporteVentasDia")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            } else if (accion.equals("pdfRegistroVenta")) {
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                String dataResVenta = (String) session.getAttribute("dataResVenta");
                response.getWriter().write(ventaUI.pdfRegistroVenta(dataResVenta));
            } else if (accion.equals("printHTML")) {
                ReporteVentasUI reportes = new ReporteVentasUI();
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                String dataResVenta = (String) session.getAttribute("dataResVenta");
                response.getWriter().write(reportes.HTMLImpVenta(dataResVenta,90, true));
            }
        }
    }
    session.removeAttribute("accion");
    session.removeAttribute("respuesta");
    session.removeAttribute("respuestalista");
    session.removeAttribute("listadotiposmenu");
%>
