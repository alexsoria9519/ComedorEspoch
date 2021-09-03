<%-- 
    Document   : dashboardVista
    Created on : Aug 23, 2021, 12:39:20 PM
    Author     : alex4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    //String accion = (String) session.getAttribute("accion");
    String accion = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String data = (String) session.getAttribute("data");

//    JsonParser parser = new JsonParser();
//    JsonElement elementoJson = parser.parse(respuestaJSON);
//    JsonObject objJson = elementoJson.getAsJsonObject();
//
//    VentaUI ventaUI = new VentaUI();
//    ReporteVentasUI reporteVentas = new ReporteVentasUI();
    if (accion != null) {

        if (accion.equals("datosPanelCard")) {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(respuestaJSON);
        } else if (accion.equals("cantidadDetalleDia")) {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(respuestaJSON);
        }

//        if (accion.equals("formulario")) {
//            response.setStatus(200);
//            response.setContentType("text/plain");
//            response.setCharacterEncoding("UTF-8");
//
//        } else if (accion.equals("formularioReporteVentasDia")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.removeAttribute("dataResReporte");
//            response.getWriter().write(reporteVentas.formularioVentasDiarias());
//        } else if (accion.equals("reporteVentasDia")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.setAttribute("dataResReporte", respuestaJSON);
//            response.getWriter().write(reporteVentas.reporteVentasDiarias(respuestaJSON));
//        } else if (accion.equals("formularioReporteIntervaloFechas")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.removeAttribute("dataResReporte");
//            response.getWriter().write(reporteVentas.formularioVentasIntervalo());
//        } else if (accion.equals("reporteIntervaloFechas")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.setAttribute("dataResReporte", respuestaJSON);
//            response.getWriter().write(reporteVentas.reporteVentasIntervalos(respuestaJSON));
//        } else if (accion.equals("formularioReporteTipoMenu")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.removeAttribute("dataResReporte");
//            response.getWriter().write(reporteVentas.formularioReporteTipoMenu(respuestaListado));
//        } else if (accion.equals("reporteIntervaloFechasMenu")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.setAttribute("dataResReporte", respuestaJSON);
//            response.getWriter().write(reporteVentas.reporteVentasIntervalosMenu(respuestaJSON));
//        } else if (accion.equals("pdfReporteVentas")) {
//            String dataResReporte = (String) session.getAttribute("dataResReporte");
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            response.getWriter().write(reporteVentas.getPdfReporte(data, dataResReporte, true));
//            session.removeAttribute("data");
//        } else if (accion.equals("imprimirReporteVentas")) {
//            String dataResReporte = (String) session.getAttribute("dataResReporte");
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            response.getWriter().write(reporteVentas.getPrintHTMLReporte(data, dataResReporte, true));
//            session.removeAttribute("data");
//        } else if (accion.equals("formularioReporteTipoUsuario")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.removeAttribute("dataResReporte");
//            response.getWriter().write(reporteVentas.formularioReporteTipoUsuario(respuestaListado));
//        } else if (accion.equals("reporteIntervaloFechasUsuario")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.setAttribute("dataResReporte", respuestaJSON);
//            System.err.println("Vista Response JSON " + respuestaJSON);
//            response.getWriter().write(reporteVentas.reporteVentasIntervaloUsuario(respuestaJSON));
//        } else if (accion.equals("modalVentasUsuariosFechas")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            response.getWriter().write(reporteVentas.formularioReporteVentasUsuarioFechas());
//        } else if (accion.equals("reporteDatausuarioFechas")) {
//            response.setStatus(200);
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("text/plain");
//            session.setAttribute("dataResReporte", respuestaJSON);
//            response.getWriter().write(reporteVentas.reporteVentasUsuarioFechas(respuestaJSON));
//        }
    }
    session.removeAttribute("accion");
    session.removeAttribute("respuesta");
%>

