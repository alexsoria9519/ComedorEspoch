<%-- 
    Document   : dashboardModelo
    Created on : Aug 23, 2021, 12:39:33 PM
    Author     : alex4
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
            if (accion.equals("datosPanelCard")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.ventasDiariasGraficos(req.getString("fecha"));
                JSONObject responseDataVenta = new JSONObject(resAll);
                JSONObject valoresDataVenta = new JSONObject();

                if (responseDataVenta.get("success").equals("ok")) {
                    valoresDataVenta.put("success", "ok");
                } else {
                    valoresDataVenta.put("success", "error");
                    valoresDataVenta.put("data", responseDataVenta.getString("data"));
                }

                valoresDataVenta.put("valorVentas", responseDataVenta.getString("valorVentas"));
                valoresDataVenta.put("IVA", responseDataVenta.getString("IVA"));

                resAll = comedorWs.cantidadVentasDetalladosDia(req.getString("fecha"));
                JSONObject responseDataCantidad = new JSONObject(resAll);
                JSONObject cantidadDataVenta = new JSONObject();
                if (responseDataCantidad.get("success").equals("ok")) {
                    cantidadDataVenta.put("success", "ok");
                } else {
                    cantidadDataVenta.put("success", "error");
                    cantidadDataVenta.put("data", responseDataCantidad.getString("data"));
                }

                cantidadDataVenta.put("totalVentas", responseDataCantidad.getBigInteger("totalVentas"));
                cantidadDataVenta.put("detalleVentas", responseDataCantidad.getString("detalleVenta"));
                resultJSON.put("valoresDataVenta", valoresDataVenta);
                resultJSON.put("cantidadDataVenta", cantidadDataVenta);

            } else if (accion.equals("cantidadDetalleDia")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.cantidadVentasDetalladosDia(req.getString("fecha"));
                JSONObject responseData = new JSONObject(resAll);
                if (responseData.get("success").equals("ok")) {
                    resultJSON.put("success", "ok");
                } else {
                    resultJSON.put("success", "error");
                    resultJSON.put("data", responseData.getString("data"));
                }

                resultJSON.put("totalVentas", responseData.getBigInteger("totalVentas"));
                resultJSON.put("detalleVentas", responseData.getString("detalleVenta"));
            }

//            if (accion.equals("reporteVentasDia")) {
//                String fecha = data;
//                resAll = comedorWs.listadoVentasDiarias(fecha);
//                resultJSON.put("dataReporte", resAll);
//                resultJSON.put("fechaVenta", fecha);
//                messageError = "Existe un error al obtener los datos";
//            } else if (accion.equals("reporteIntervaloFechas")) {
//                JSONObject req = new JSONObject(data);
//                resAll = comedorWs.listadoVentasIntervaloFechas(req.getString("fechaInicio"), req.getString("fechaFin"));
//                resultJSON.put("dataReporte", resAll);
//                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
//                resultJSON.put("fechaFin", req.getString("fechaFin"));
//                messageError = "Existe un error al obtener los datos";
//            } else if (accion.equals("formularioReporteTipoMenu")) {
//                respuestaListado = comedorWs.getListadoTiposMenus();
//                messageError = "Existe un error al obtener los datos";
//            } else if (accion.equals("reporteIntervaloFechasMenu")) {
//                JSONObject req = new JSONObject(data);
//                Integer idTipo = req.getInt("idTipo");
//                resAll = comedorWs.listadoVentasIntervaloFechasMenu(req.getString("fechaInicio"), idTipo.toString(), req.getString("fechaFin"));
//                resultJSON.put("dataReporte", resAll);
//                resAll = comedorWs.getTipoMenu(idTipo.toString());
//                resultJSON.put("dataTipoMenu", resAll);
//                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
//                resultJSON.put("fechaFin", req.getString("fechaFin"));
//                resultJSON.put("idTipoMenu", idTipo);
//                messageError = "Existe un error al obtener los datos";
//            } else if (accion.equals("formularioReporteTipoUsuario")) {
//                respuestaListado = comedorWs.getListadoTiposUsuarios();
//                messageError = "Existe un error al obtener los datos";
//            } else if (accion.equals("reporteIntervaloFechasUsuario")) {
//                JSONObject req = new JSONObject(data);
//                Integer idTipo = req.getInt("idTipo");
//                resAll = comedorWs.listadoVentasIntervaloFechasUsuario(idTipo.toString(), req.getString("fechaInicio"), req.getString("fechaFin"));
//                resultJSON.put("dataReporte", resAll);
//                resAll = comedorWs.getTipoUsuario(idTipo.toString());
//                resultJSON.put("dataTipoUsuario", resAll);
//                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
//                resultJSON.put("fechaFin", req.getString("fechaFin"));
//                resultJSON.put("idTipoUsuario", idTipo);
//                messageError = "Existe un error al obtener los datos";
//            } else if (accion.equals("reporteDatausuarioFechas")) {
//                JSONObject req = new JSONObject(data);
//                resAll = comedorWs.listadoVentasUsuarioIntervaloFechas(req.getString("fechaInicio"), req.getString("cedula"), req.getString("fechaFin"));
//                resultJSON.put("dataReporte", resAll);
//                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
//                resultJSON.put("fechaFin", req.getString("fechaFin"));
//                resultJSON.put("cedula", req.getString("cedula"));
//            }
        }
    } catch (Exception ex) {
        System.err.println(ex);
        resultJSON.put("error", "Error de " + ex);
        resultJSON.put("message", messageError);
    }
    session.setAttribute("respuesta", resultJSON.toString());
    response.sendRedirect("dashboardControlador.jsp");


%>
