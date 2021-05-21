<%-- 
    Document   : reporteVentasVista
    Created on : May 12, 2021, 3:44:26 PM
    Author     : corebitsas
--%>

<%@page import="com.comedorui.ReporteVentasUI"%>
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

//    JsonParser parser = new JsonParser();
//    JsonElement elementoJson = parser.parse(respuestaJSON);
//    JsonObject objJson = elementoJson.getAsJsonObject();
//
//    VentaUI ventaUI = new VentaUI();
    ReporteVentasUI reporteVentas = new ReporteVentasUI();

    if (accion != null) {
        if (accion.equals("formulario")) {
            response.setStatus(200);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

        } else if (accion.equals("formularioReporteVentasDia")) {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write(reporteVentas.formularioVentasDiarias());
        } else if (accion.equals("reporteVentasDia")) {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write(reporteVentas.reporteVentasDiarias(respuestaJSON));
        } else if (accion.equals("formularioReporteIntervaloFechas")) {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write(reporteVentas.formularioVentasIntervalo());
        } else if (accion.equals("reporteIntervaloFechas")) {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write(reporteVentas.reporteVentasIntervalos(respuestaJSON));
        } else if (accion.equals("formularioReporteTipoMenu")) {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write(reporteVentas.formularioReporteTipoMenu(respuestaListado));
        } else if (accion.equals("reporteIntervaloFechasMenu")) {
            response.setStatus(200);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().write(reporteVentas.reporteVentasIntervalosMenu(respuestaJSON));
        }
//            if (accion.equals("costoVenta")) {
//
//                response.setStatus(200);
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("text/plain");
//                System.err.println(objJson.get("datosCosto").getAsString());
//                response.getWriter().write(ventaUI.datosCosto(objJson.get("datosCosto").getAsString()).toString());
//            } else if (accion.equals("registrarVenta")) {
//                response.setStatus(201);
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(respuestaJSON);
//            } else if (accion.equals("reporteFecha")) {
//                response.setStatus(200);
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(respuestaJSON);
//            } else if (accion.equals("formularioedicion")) {
//                response.setStatus(200);
//                response.setContentType("text/plain");
//                //response.getWriter().write(menu.formularioEdicion(respuestaListado, listadoTiposMenu));
//            } else if (accion.equals("eliminarLogico")) {
//                response.setStatus(200);
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(respuestaJSON);
//            } else if (accion.equals("formularioVenta")) {
//                response.setStatus(200);
//                response.setContentType("text/plain");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(ventaUI.formulario(respuestaJSON));
//            } else if(accion.equals("getCostoUsuario")){
//                response.setStatus(200);
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(respuestaListado);
//            }

    }
    session.removeAttribute("accion");
    session.removeAttribute("respuesta");
    session.removeAttribute("respuestalista");
    session.removeAttribute("listadotiposmenu");
%>
