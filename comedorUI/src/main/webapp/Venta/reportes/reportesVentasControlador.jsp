<%-- 
    Document   : reporteVentasControlador
    Created on : May 12, 2021, 3:43:57 PM
    Author     : corebitsas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");

    String accionSession = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");

    if (accion != null) {
        if (accion.equals("formularioReporteVentasDia")) {
            session.setAttribute("accion", "formularioReporteVentasDia");
            response.sendRedirect("reportesVentasVista.jsp");
        } else if (accion.equals("formularioReporteIntervaloFechas")) {
            session.setAttribute("accion", "formularioReporteIntervaloFechas");
            response.sendRedirect("reportesVentasVista.jsp");
        } else {
            if (accion.equals("reporteVentasDia")) {
                session.setAttribute("accion", "reporteVentasDia");
            } else if (accion.equals("reporteIntervaloFechas")) {
                session.setAttribute("accion", "reporteIntervaloFechas");
            } else if(accion.equals("formularioReporteTipoMenu")){
                session.setAttribute("accion", "formularioReporteTipoMenu");
            } else if(accion.equals("reporteIntervaloFechasMenu")){
                session.setAttribute("accion", "reporteIntervaloFechasMenu");
            }

//            
//            if (accion.equals("costoVenta")) {
//                session.setAttribute("accion", "costoVenta");
//                session.setAttribute("tipoUsuario", dataTipoUsuario);
//            } else if (accion.equals("registrarVenta")) {
//                session.setAttribute("accion", "registrarVenta");
//            } else if (accion.equals("reporteFecha")) {
//                session.setAttribute("accion", "reporteFecha");
//            } else if (accion.equals("reporteTipoFecha")) {
//                session.setAttribute("accion", "reporteTipoFecha");
//            } else if (accion.equals("formularioedicion")) {
//                session.setAttribute("accion", "formularioedicion");
//            } else if (accion.equals("formularioVenta")) {
//                session.setAttribute("accion", "formularioVenta");
//            } else if(accion.equals("getCostoUsuario")){
//                session.setAttribute("accion", "getCostoUsuario");
//            }
//            
//            
//            
//            else if(accion.equals("formularioReporteVentasDia")){
//                session.setAttribute("accion", "formularioReporteVentasDia");
//                response.sendRedirect("ventaVista.jsp");
//            }
//            
            session.setAttribute("data", data);
            response.sendRedirect("reportesVentasModelo.jsp");
        }

    }
//
    if (accionSession != null) {
        response.sendRedirect("reportesVentasVista.jsp");
    }
//
//


%>
