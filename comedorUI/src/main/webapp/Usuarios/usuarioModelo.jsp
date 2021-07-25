<%-- 
    Document   : usuarioModelo
    Created on : Jul 24, 2021, 8:31:37 PM
    Author     : alex4
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="servicios.ComedorWS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");
    ComedorWS comedorWs = new ComedorWS();
    JSONObject resultJSON = new JSONObject();
    String resAll;
    String messageError = "Error";

    try {
        if (accion != null) {
            if (accion.equals("registrarUsuario")) {
                JSONObject resJSON = new JSONObject(data);
                Integer idTipousuario = resJSON.getInt("tipoUsuario");
                resAll = comedorWs.insertDataCostoUsuarioUsuario(idTipousuario.toString(), resJSON.getString("cedula"));
                session.setAttribute("respuesta", resAll);
                messageError = "Existe un error al obtener los datos";
            }
        }
    } catch (Exception ex) {
        System.err.println(ex);
        resultJSON.put("error", "Error de " + ex);
        resultJSON.put("message", messageError);
        session.setAttribute("respuesta", resultJSON.toString());
    }

    response.sendRedirect("usuarioControlador.jsp");

%>