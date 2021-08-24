<%-- 
    Document   : dashboardControlador
    Created on : Aug 23, 2021, 12:39:48 PM
    Author     : alex4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");

    String accionSession = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");

    if (accion != null) {

        if (accion.endsWith("valorVentasDia")) {
            session.setAttribute("accion", "valorVentasDia");
        }

//        if (accion.equals("formularioReporteVentasDia")) {
//            session.setAttribute("accion", "formularioReporteVentasDia");
//            response.sendRedirect("reportesVentasVista.jsp");
//        } else if (accion.equals("formularioReporteIntervaloFechas")) {
//            session.setAttribute("accion", "formularioReporteIntervaloFechas");
//            response.sendRedirect("reportesVentasVista.jsp");
//        } else if (accion.equals("modalVentasUsuariosFechas")) {
//            session.setAttribute("accion", "modalVentasUsuariosFechas");
//            response.sendRedirect("reportesVentasVista.jsp");
//        } else {
//            if (accion.equals("reporteVentasDia")) {
//                session.setAttribute("accion", "reporteVentasDia");
//            } else if (accion.equals("reporteIntervaloFechas")) {
//                session.setAttribute("accion", "reporteIntervaloFechas");
//            } else if (accion.equals("formularioReporteTipoMenu")) {
//                session.setAttribute("accion", "formularioReporteTipoMenu");
//            } else if (accion.equals("reporteIntervaloFechasMenu")) {
//                session.setAttribute("accion", "reporteIntervaloFechasMenu");
//            } else if (accion.equals("pdfReporteVentas")) {
//                session.setAttribute("accion", "pdfReporteVentas");
//            } else if (accion.equals("imprimirReporteVentas")) {
//                session.setAttribute("accion", "imprimirReporteVentas");
//            } else if (accion.equals("formularioReporteTipoUsuario")) {
//                session.setAttribute("accion", "formularioReporteTipoUsuario");
//            } else if (accion.equals("reporteIntervaloFechasUsuario")) {
//                session.setAttribute("accion", "reporteIntervaloFechasUsuario");
//            } else if (accion.equals("reporteDatausuarioFechas")) {
//                session.setAttribute("accion", "reporteDatausuarioFechas");
//            }
        session.setAttribute("data", data);
        response.sendRedirect("dashboardModelo.jsp");
    }

//
    if (accionSession != null) {
        response.sendRedirect("dashboardVista.jsp");
    }
//
//


%>