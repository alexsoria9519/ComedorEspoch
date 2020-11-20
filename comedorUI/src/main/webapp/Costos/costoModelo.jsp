<%-- 
    Document   : costoModelo.jsp
    Created on : May 7, 2019, 2:30:12 PM
    Author     : Alex
--%>




<%@page import="servicios.ComedorWS"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.google.gson.Gson"%>

<%@page import="entities.Costo"%>
<%@page import="servicios.CostoWS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");

    Gson gson = new Gson();
    JSONObject resultJSON = new JSONObject();
    JSONObject requestJSON = new JSONObject();

    Costo costo = new Costo();
    CostoWS costoWs = new CostoWS();
    ComedorWS comedorWs = new ComedorWS();

    String resAll;
    String messageError = "Error";

    if (accion != null) {

        try {
            if (accion.equals("listado")) {
                messageError = "Lo sentimos, se presentó un error al momento de obtener los datos, por favor intente mas tarde";
                //resAll = costoWs.findAll(String.class);

                resAll = comedorWs.getListadoCostos();
                session.setAttribute("respuestalista", resAll);
                resultJSON.put("message", "Listado Correcto");
            } else if (accion.equals("formulario")) {

                resAll = comedorWs.getListadoTiposMenus();
                resultJSON.put("success", "ok");
                resultJSON.put("dataTipoMenus", resAll);
                resAll = comedorWs.getListadoTiposUsuarios();
                resultJSON.put("dataTipoUsuarios", resAll);
                resultJSON.put("message", "Listados de tipos correctos");

            } else {

                costo = gson.fromJson(data, Costo.class);

                if (accion.equals("ingreso")) {
                    costo.setBlnestado(true);

                    requestJSON.put("costo", gson.toJson(costo));

                    resAll = comedorWs.insertCosto(requestJSON.toString());

                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    messageError = "Error en el ingreso del costo";

                } else if (accion.equals("edicion")) {
                    //costoWs.edit(costo, costo.getIntidcosto().toString());

                    requestJSON.put("costo", gson.toJson(costo));

                    resAll = comedorWs.editarCosto(requestJSON.toString());

                    JSONObject respJson = new JSONObject(resAll);

                    resultJSON.put("message", respJson.getString("data"));

                    //resultJSON.put("message", "Modificación correcta");
                    messageError = "Error en la modificacion";

                } else if (accion.equals("eliminar")) {

                    //costoWs.remove(costo.getIntidcosto().toString());
                    resAll = comedorWs.eliminarCosto(costo.getIntidcosto().toString());
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    resultJSON.put("success", respJson.getString("success"));
                    messageError = "Error en la eliminación costo";

                } else if (accion.equals("activar") || accion.equals("desactivar")) {

                    requestJSON.put("costo", gson.toJson(costo));
                    resAll = comedorWs.activarDesactivarCosto(requestJSON.toString(), costo.getIntidcosto().toString(), accion);

                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("success", respJson.getString("success"));

                    if (accion.equals("activar")) {
                        resultJSON.put("message", "Activación Correcta");
                        messageError = "Error en la activación del costo";;
                    } else if (accion.equals("desactivar")) {
                        resultJSON.put("message", "Desactivación Correcta");
                        messageError = "Error al desactivar  el costo";;
                    }
                } else if (accion.equals("validarDetalle")) {
                    resAll = comedorWs.getCostoByDetalle(costo.getStrdetalle());
                    JSONObject respJson = new JSONObject(resAll);
                    if (respJson.get("success").equals("ok")) {
                        resultJSON.put("message", "Ya existe un registro con ese nombre");
                    } else if (respJson.get("success").equals("no existe")) {
                        resultJSON.put("message", "No existe registro");
                    }
                } else if (accion.equals("buscarid") || accion.equals("formularioedicion")) {

                    resAll = comedorWs.getCosto(costo.getIntidcosto().toString());
                    resultJSON.put("dataCosto", resAll);

                    resAll = comedorWs.getListadoTiposMenus();
                    resultJSON.put("dataTipoMenus", resAll);

                    resAll = comedorWs.getListadoTiposUsuarios();
                    resultJSON.put("dataTipoUsuarios", resAll);

                    resultJSON.put("success", "ok");
                    resultJSON.put("message", "Búsqueda del formulario: " + costo.getIntidcosto().toString() + " correcta");
                }
            }

        } catch (Exception e) {
            resultJSON.put("error", "Error de " + e);
            resultJSON.put("message", messageError);
        }
        session.setAttribute("respuesta", resultJSON.toString());
        response.sendRedirect("costoControlador.jsp");

    }


%>
