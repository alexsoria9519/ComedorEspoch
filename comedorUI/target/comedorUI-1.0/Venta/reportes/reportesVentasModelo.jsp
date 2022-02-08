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
                resAll = comedorWs.listadoOperativos();
                resultJSON.put("operativos", resAll);
                resultJSON.put("fechaVenta", fecha);
                messageError = "Existe un error al obtener los datos";
            } else if (accion.equals("reporteIntervaloFechas")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.listadoVentasIntervaloFechas(req.getString("fechaInicio"), req.getString("fechaFin"));
                resultJSON.put("dataReporte", resAll);
                resAll = comedorWs.listadoOperativos();
                resultJSON.put("operativos", resAll);
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
                resAll = comedorWs.listadoOperativos();
                resultJSON.put("operativos", resAll);
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
                resAll = comedorWs.listadoOperativos();
                resultJSON.put("operativos", resAll);
                resAll = comedorWs.getTipoUsuario(idTipo.toString());
                resultJSON.put("dataTipoUsuario", resAll);
                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
                resultJSON.put("fechaFin", req.getString("fechaFin"));
                resultJSON.put("idTipoUsuario", idTipo);
                messageError = "Existe un error al obtener los datos";
            } else if (accion.equals("reporteDatausuarioFechas")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.listadoVentasUsuarioIntervaloFechas(req.getString("fechaInicio"), req.getString("cedula"), req.getString("fechaFin"));
                resultJSON.put("dataReporte", resAll);
                resAll = comedorWs.listadoOperativos();
                resultJSON.put("operativos", resAll);
                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
                resultJSON.put("fechaFin", req.getString("fechaFin"));
                resultJSON.put("cedula", req.getString("cedula"));
            } else if (accion.equals("formularioReporteFacultadCarrera")) {
                resAll = comedorWs.listadoFacultades();
                JSONObject dataListado = new JSONObject(resAll);
                if (dataListado.getString("success").equals("ok")) {
                    resultJSON.put("listadoFacultades", dataListado.getString("detalleVenta"));
                    resultJSON.put("cantidadFacultades", dataListado.getInt("cantidadFacultades"));
                } else {
                    resultJSON.put("listadoFacultades", "{\"facultadesCarreras\":[]}");
                    resultJSON.put("cantidadFacultades", 0);
                }
            } else if (accion.equals("selectCarreras")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.listadoCarreras(req.getString("facultad"));
                JSONObject dataListado = new JSONObject(resAll);
                if (dataListado.getString("success").equals("ok")) {
                    resultJSON.put("listadoCarreras", dataListado.getString("detalleVenta"));
                    resultJSON.put("cantidadCarreras", dataListado.getInt("cantidadFacultades"));
                } else {
                    resultJSON.put("listadoCarreras", "{\"facultadesCarreras\":[]}");
                    resultJSON.put("cantidadCarreras", 0);
                }
            } else if (accion.equals("reporteFacultad")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.listadoUsuariosFacultad(req.getString("fechaInicio"), req.getString("fechaFin"), req.getString("facultad"));
                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
                resultJSON.put("fechaFin", req.getString("fechaFin"));
                resultJSON.put("facultad", req.getString("descripcionFacultad"));

                JSONObject dataListado = new JSONObject(resAll);

                if (dataListado.getString("success").equals("ok")) {
                    resultJSON.put("listadoUsuarios", dataListado.getString("detalleVenta"));
                    resultJSON.put("totalVentas", dataListado.getInt("totalVentas"));
                    resultJSON.put("totalUsuarios", dataListado.getInt("totalUsuarios"));
                } else {
                    resultJSON.put("listadoUsuarioFacultad", "{\"facultadesCarreras\":[]}");
                    resultJSON.put("totalVentas", 0);
                    resultJSON.put("totalUsuarios", 0);
                    resultJSON.put("data", dataListado.getString("data"));
                }
                resAll = comedorWs.listadoOperativos();
                JSONObject dataListadoOperativos = new JSONObject(resAll);

                if (dataListadoOperativos.getString("success").equals("ok")) {
                    resultJSON.put("listadoOperativos", "{ \"operativos\" : " + dataListadoOperativos.getString("operativos") + " }");
                } else {
                    resultJSON.put("listadoOperativos", "{ \"operativos\" : [] }");
                }

                resultJSON.put("success", dataListado.getString("success"));
            } else if (accion.equals("reporteCarrera")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.listadoUsuariosCarrera(req.getString("fechaInicio"), req.getString("carrera"), req.getString("fechaFin"));
                resultJSON.put("fechaInicio", req.getString("fechaInicio"));
                resultJSON.put("fechaFin", req.getString("fechaFin"));
                resultJSON.put("facultad", req.getString("descripcionFacultad"));
                resultJSON.put("carrera", req.getString("descripcionCarrera"));
                JSONObject dataListado = new JSONObject(resAll);

                if (dataListado.getString("success").equals("ok")) {
                    resultJSON.put("listadoUsuarios", dataListado.getString("detalleVenta"));
                    resultJSON.put("totalVentas", dataListado.getInt("totalVentas"));
                    resultJSON.put("totalUsuarios", dataListado.getInt("totalUsuarios"));
                } else {
                    resultJSON.put("listadoUsuarioFacultad", "{\"facultadesCarreras\":[]}");
                    resultJSON.put("totalVentas", 0);
                    resultJSON.put("totalUsuarios", 0);
                    resultJSON.put("data", dataListado.getString("data"));
                }

                resAll = comedorWs.listadoOperativos();
                JSONObject dataListadoOperativos = new JSONObject(resAll);

                if (dataListadoOperativos.getString("success").equals("ok")) {
                    resultJSON.put("listadoOperativos", "{ \"operativos\" : " + dataListadoOperativos.getString("operativos") + " }");
                } else {
                    resultJSON.put("listadoOperativos", "{ \"operativos\" : [] }");
                }
                resultJSON.put("success", dataListado.getString("success"));
            }

        }
    } catch (Exception ex) {
        System.err.println(ex);
        resultJSON.put("error", "Error de " + ex);
        resultJSON.put("message", messageError);
    }
    session.setAttribute("respuesta", resultJSON.toString());
    session.setAttribute("respuestalista", respuestaListado);

    if (!accion.equals("pdfReporteVentas") && !accion.equals("imprimirReporteVentas") && !accion.equals("pdfReporteFacultadesCarrera")) {
        session.removeAttribute("data");
    }

    response.sendRedirect("reportesVentasControlador.jsp");


%>
