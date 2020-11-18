<%-- 
    Document   : tipoMenuModelo
    Created on : 16/05/2019, 12:39:46
    Author     : corebitsas
--%>


<%@page import="servicios.ComedorWS"%>
<%@page import="servicios.TipoMenuWS"%>
<%@page import="entities.Tipomenu"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");

    Gson gson = new Gson();
    JSONObject resultJSON = new JSONObject();
    JSONObject requestJSON = new JSONObject();

    Tipomenu tipoMenu = new Tipomenu();
    TipoMenuWS tipoMenuWS = new TipoMenuWS();
    ComedorWS comedorWs = new ComedorWS();
    String resAll;
    String messageError = "Error";

    if (accion != null) {

        try {

            /* -----------------------------------------------------------------
               ---------------  Listado de Tipos de Menu -------------------
               ----------------------------------------------------------------- 
             */
            if (accion.equals("listado")) {
                messageError = "Lo sentimos, se presentó un error al momento de obtener los datos, por favor intente mas tarde";
                resAll = comedorWs.getListadoTiposMenus();
                session.setAttribute("respuestalista", resAll);
                resultJSON.put("message", "Listado Correcto");
            } else {

                tipoMenu = gson.fromJson(data, Tipomenu.class);
                /* -----------------------------------------------------------------
                   ---------------  Ingreso de Tipos de Menu -------------------
                   ----------------------------------------------------------------- 
                 */
                if (accion.equals("ingreso")) {
                    messageError = "Lo sentimos, se presentó un error al momento de ingresar los datos, por favor intente mas tarde";
                    requestJSON.put("tipoMenu", gson.toJson(tipoMenu));
                    resAll = comedorWs.insertTipoMenu(requestJSON.toString());
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                } else if (accion.equals("edicion")) {
                    /* -----------------------------------------------------------------
                   ---------------  Editar de Tipos de Menu -------------------
                   ----------------------------------------------------------------- 
                     */
                    requestJSON.put("tipoMenu", gson.toJson(tipoMenu));
                    resAll = comedorWs.editarTipoMenu(requestJSON.toString());
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    messageError = "Error en la modificacion";

                } else if (accion.equals("eliminar")) {
                    /* -----------------------------------------------------------------
                   ---------------  Eeliminar de Tipos de Menu -------------------
                   ----------------------------------------------------------------- 
                     */
                    resAll = comedorWs.eliminarTipoMenu(tipoMenu.getIntidtipo().toString(), "Normal");
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    resultJSON.put("success", respJson.getString("success"));
                    messageError = "Error en la eliminación tipoMenu";

                } else if (accion.equals("buscarid") || accion.equals("formularioedicion")) {

                    /* -----------------------------------------------------------------
                   ---------------  buscarid formularioedicion de Tipos de Menu -------------------
                   ----------------------------------------------------------------- 
                     */
                    resAll = comedorWs.getTipoMenu(tipoMenu.getIntidtipo().toString());
                    resultJSON.put("success", "Correcto");
                    session.setAttribute("respuestalista", resAll);
                    resultJSON.put("message", "Búsqueda del id: " + tipoMenu.getIntidtipo().toString() + " correcta");
                } else if (accion.equals("validarTipo")) {

                    /* -----------------------------------------------------------------
                   ---------------  validarTipo de Tipos de Menu -------------------
                   ----------------------------------------------------------------- 
                     */
                    resAll = comedorWs.getTipoMenuByTipo(tipoMenu.getStrtipo());
                    JSONObject respJson = new JSONObject(resAll);
                    if (respJson.get("success").equals("ok")) {
                        resultJSON.put("message", "Ya existe un registro con ese nombre");
                    } else if (respJson.get("success").equals("no existe")) {
                        resultJSON.put("message", "No existe registro");
                    }
                    messageError = "Error en la busqueda del tipo";
                } else if (accion.equals("eliminarForzoso")) {

                    //tipoMenuWS.remove(tipoMenu.getIntidtipo().toString());
                    resAll = comedorWs.eliminarTipoMenu(tipoMenu.getIntidtipo().toString(), "forzada");
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    resultJSON.put("success", respJson.getString("success"));
                    messageError = "Error en la eliminación tipoMenu";
                }
            }

        } catch (Exception e) {
            resultJSON.put("error", "Error de " + e);
            resultJSON.put("message", messageError);
        }
        session.setAttribute("respuesta", resultJSON.toString());
        response.sendRedirect("tipoMenuControlador.jsp");

    }


%>
