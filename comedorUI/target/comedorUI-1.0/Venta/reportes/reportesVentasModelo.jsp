<%-- 
    Document   : reporteVentasModelo
    Created on : May 12, 2021, 3:44:14 PM
    Author     : corebitsas
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="servicios.ComedorWS"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");

    Gson gson = new Gson();
    ComedorWS comedorWs = new ComedorWS();
    JSONObject resultJSON = new JSONObject();
    String resAll;
    String respuestaListado = "";
    String messageError = "Error";
    try {
        if (accion != null) {

            if (accion.equals("reporteVentasDia")) {
                String fecha = data;
                resAll = comedorWs.listadoVentasDiarias(fecha);
                resultJSON.put("dataReporte", resAll);
                resultJSON.put("fechaVenta", fecha);
                messageError = "Existe un error al obtener los datos";
            } else if (accion.equals("reporteIntervaloFechas")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.listadoVentasIntervaloFechas(req.getString("fechaInicio"), req.getString("fechaFin"));
                resultJSON.put("dataReporte", resAll);
                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
                resultJSON.put("fechaFin", req.getString("fechaFin"));
                messageError = "Existe un error al obtener los datos";
            } else if (accion.equals("formularioReporteTipoMenu")) {
                respuestaListado = comedorWs.getListadoTiposMenus();
                messageError = "Existe un error al obtener los datos";
            } else if (accion.equals("reporteIntervaloFechasMenu")) {
                JSONObject req = new JSONObject(data);
                Integer idTipo = req.getInt("idTipo");
                resAll = comedorWs.listadoVentasIntervaloFechasMenu(req.getString("fechaInicio"), idTipo.toString(), req.getString("fechaFin"));
                resultJSON.put("dataReporte", resAll);
                resAll = comedorWs.getTipoMenu(idTipo.toString());
                resultJSON.put("dataTipoMenu", resAll);
                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
                resultJSON.put("fechaFin", req.getString("fechaFin"));
                resultJSON.put("idTipoMenu", idTipo);
                messageError = "Existe un error al obtener los datos";
            } else if (accion.equals("formularioReporteTipoUsuario")) {
                respuestaListado = comedorWs.getListadoTiposUsuarios();
                messageError = "Existe un error al obtener los datos";
            } else if (accion.equals("reporteIntervaloFechasUsuario")) {
                JSONObject req = new JSONObject(data);
                Integer idTipo = req.getInt("idTipo");
                resAll = comedorWs.listadoVentasIntervaloFechasUsuario(idTipo.toString(), req.getString("fechaInicio"), req.getString("fechaFin"));
                resultJSON.put("dataReporte", resAll);
                resAll = comedorWs.getTipoUsuario(idTipo.toString());
                resultJSON.put("dataTipoUsuario", resAll);
                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
                resultJSON.put("fechaFin", req.getString("fechaFin"));
                resultJSON.put("idTipoUsuario", idTipo);
                messageError = "Existe un error al obtener los datos";
            }

        }
    } catch (Exception ex) {
        System.err.println(ex);
        resultJSON.put("error", "Error de " + ex);
        resultJSON.put("message", messageError);
    }
    session.setAttribute("respuesta", resultJSON.toString());
    session.setAttribute("respuestalista", respuestaListado);

    if (!accion.equals("pdfReporteVentas") && !accion.equals("imprimirReporteVentas")) {
        session.removeAttribute("data");
    }

    response.sendRedirect("reportesVentasControlador.jsp");


%>
