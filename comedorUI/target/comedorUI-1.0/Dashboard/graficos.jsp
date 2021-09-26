<%-- 
    Document   : graficos
    Created on : Sep 7, 2021, 2:02:03 PM
    Author     : alex4
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="servicios.ComedorWS"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");

    Gson gson = new Gson();
    ComedorWS comedorWs = new ComedorWS();
    JSONObject resultJSON = new JSONObject();
    String resAll;
    String messageError = "Error";
    try {
        if (accion != null) {
            if (accion.equals("graphicsSection")) {
                JSONObject req = new JSONObject(data);
                resAll = comedorWs.historicoDatosGenero();

                JSONObject responseHistoricoDatosGenero = new JSONObject(resAll);
                JSONObject valoresHistoricoDatosGenero = new JSONObject();

                if (responseHistoricoDatosGenero.get("success").equals("ok")) {
                    valoresHistoricoDatosGenero.put("success", "ok");
                } else {
                    valoresHistoricoDatosGenero.put("success", "error");
                    valoresHistoricoDatosGenero.put("data", valoresHistoricoDatosGenero.getString("data"));
                }

                valoresHistoricoDatosGenero.put("totalVentas", responseHistoricoDatosGenero.getBigInteger("totalVentas"));
                valoresHistoricoDatosGenero.put("detalleVentas", responseHistoricoDatosGenero.getString("detalleVenta"));
                resultJSON.put("graficoPastelGenero", valoresHistoricoDatosGenero);

                resAll = comedorWs.historicoDatosMeses();

                JSONObject responseHistoricoDatosMeses = new JSONObject(resAll);
                JSONObject valoresHistoricoDatosMeses = new JSONObject();

                if (responseHistoricoDatosMeses.get("success").equals("ok")) {
                    valoresHistoricoDatosMeses.put("success", "ok");
                } else {
                    valoresHistoricoDatosGenero.put("success", "error");
                    valoresHistoricoDatosMeses.put("data", valoresHistoricoDatosGenero.getString("data"));
                }

                valoresHistoricoDatosMeses.put("totalDatos", responseHistoricoDatosMeses.getBigInteger("totalDatos"));
                valoresHistoricoDatosMeses.put("detalleResumen", responseHistoricoDatosMeses.getString("detalleResumen"));
                resultJSON.put("graficoBarrasMeses", valoresHistoricoDatosMeses);

//                resAll = comedorWs.porcentajeDatosTiposUsuarios();
//
//                JSONObject responsePastelTiposUsuarios = new JSONObject(resAll);
//                JSONObject valoresPastelTiposUsuarios = new JSONObject();
//
//                if (responsePastelTiposUsuarios.get("success").equals("ok")) {
//                    valoresPastelTiposUsuarios.put("success", "ok");
//                } else {
//                    valoresHistoricoDatosGenero.put("success", "error");
//                    valoresPastelTiposUsuarios.put("data", valoresHistoricoDatosGenero.getString("data"));
//                }
//
//                valoresPastelTiposUsuarios.put("totalCantidad", responsePastelTiposUsuarios.getBigInteger("totalCantidad"));
//                valoresPastelTiposUsuarios.put("detalleVentas", responsePastelTiposUsuarios.getString("detalleVenta"));
//                resultJSON.put("graficoPastelTiposUsuarios", valoresPastelTiposUsuarios);
                System.err.println("graphicsSection1 " + resultJSON.toString());

            }
        }
    } catch (Exception ex) {
        System.err.println(ex);
        resultJSON.put("error", "Error de " + ex);
        resultJSON.put("message", messageError);
    }

    response.setStatus(200);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(resultJSON.toString());

%>
