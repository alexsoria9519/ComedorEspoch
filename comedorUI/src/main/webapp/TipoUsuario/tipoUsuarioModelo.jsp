<%-- 
    Document   : tipoUsuarioModelo
    Created on : 16/05/2019, 15:08:14
    Author     : corebitsas
--%>

<%@page import="servicios.ComedorWS"%>
<%@page import="servicios.TipoUsuarioWS"%>
<%@page import="entities.Tipousuario"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");

    Gson gson = new Gson();
    JSONObject resultJSON = new JSONObject();
    JSONObject requestJSON = new JSONObject();

    Tipousuario tipoUsuario = new Tipousuario();
    TipoUsuarioWS tipoUsuarioWS = new TipoUsuarioWS();
    ComedorWS comedorWs = new ComedorWS();

    String resAll;
    String resService;
    String messageError = "Error";

    if (accion != null) {

        try {
            /* -----------------------------------------------------------------
               ---------------  Listado de Tipos de Usuarios -------------------
               ----------------------------------------------------------------- 
             */
            if (accion.equals("listado")) {
                //resAll = tipoUsuarioWS.findAll(String.class);
                messageError = "Lo sentimos, se presentó un error al momento de obtener los datos, por favor intente mas tarde";
                resAll = comedorWs.getListadoTiposUsuarios();
                JSONObject response1 = new JSONObject(resAll);
                resultJSON.put("respuestalista", "{ \"tipoUsuarios\" : " + response1.getString("tiposUsuarios") + " }");
                resultJSON.put("cantidadUsuarios", response1.get("cantidad"));
                resultJSON.put("success", response1.get("success"));
                resultJSON.put("message", "Listado Correcto");
                session.setAttribute("respuestalista", "{ \"tipoUsuarios\" : " + response1.getString("tiposUsuarios") + " }");
                session.setAttribute("cantidadUsuarios", response1.get("cantidad"));
                session.setAttribute("success", response1.get("success"));
            } else {

                tipoUsuario = gson.fromJson(data, Tipousuario.class);
                requestJSON.put("tipoUsuario", gson.toJson(tipoUsuario));
                /* -----------------------------------------------------------------
                   ---------------  Ingreso de Tipos de Usuarios -------------------
                   ----------------------------------------------------------------- 
                 */
                if (accion.equals("ingreso")) {
                    messageError = "Lo sentimos, se presentó un error al momento de ingresar los datos, por favor intente mas tarde";
                    requestJSON.put("tipoUsuario", gson.toJson(tipoUsuario));
                    resAll = comedorWs.insertTipoUsuario(requestJSON.toString());
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    requestJSON.put("tipoUsuario", data);
                    /* -----------------------------------------------------------------
                    ---------------  Editar de Tipos de Usuarios --------------------
                    ----------------------------------------------------------------- 
                     */
                } else if (accion.equals("edicion")) {
                    requestJSON.put("tipoUsuario", gson.toJson(tipoUsuario));
                    
                    resAll = comedorWs.editarTipoUsuario(requestJSON.toString());
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    resultJSON.put("success", respJson.getString("success"));
                    // resultJSON.put("message", "Modificación correcta");
                    /* -----------------------------------------------------------------
                    ---------------  Eliminar de Tipos de Usuarios ------------------
                    ----------------------------------------------------------------- 
                     */
                } else if (accion.equals("eliminar")) {
                    messageError = "Lo sentimos, se presentó un error al momento de eliminar los datos, por favor intente mas tarde";
                    resAll = comedorWs.eliminarTipoUsuario(tipoUsuario.getIntidtipo().toString(),"normal");
                    JSONObject response1 = new JSONObject(resAll);
                    if (response1.get("success").equals("ok")) {
                        resultJSON.put("message", "Eliminación Correcta");
                        resultJSON.put("success", "ok");
                    } else if (response1.get("success").equals("no existe")) {
                        resultJSON.put("message", "Lo sentimos, no se puede eliminar el tipo de usario, no existen datos del tipo");
                        resultJSON.put("success", "validacion");
                    } else if (response1.get("success").equals("error")) {
                        resultJSON.put("message", "Lo sentimos, existió un error en la eliminación");
                        resultJSON.put("success", "error");
                    } else if(response1.get("success").equals("info")){
                        resultJSON.put("message", response1.getString("data") +", ¿Desea eliminar de todas formas este tipo de usuario?. Los registros de usuario asociados a este tipo serán eliminados.");
                        resultJSON.put("success", "info");
                    }
                    /* -----------------------------------------------------------------
                    ---------------  Eliminar Forzoso Tipos de Usuarios ------------------
                    ----------------------------------------------------------------- 
                     */
                } else if(accion.equals("eliminarForzoso")){
                    messageError = "Lo sentimos, se presentó un error al momento de eliminar los datos, por favor intente mas tarde";
                    resAll = comedorWs.eliminarTipoUsuario(tipoUsuario.getIntidtipo().toString(),"normal");
                    JSONObject response1 = new JSONObject(resAll);
                    if (response1.get("success").equals("ok")) {
                        resultJSON.put("message", "Eliminación Correcta");
                        resultJSON.put("success", "ok");
                    } else if (response1.get("success").equals("no existe")) {
                        resultJSON.put("message", "Lo sentimos, no se puede eliminar el tipo de usario, no existen datos del tipo");
                        resultJSON.put("success", "validacion");
                    } else if (response1.get("success").equals("error")) {
                        resultJSON.put("message", "Lo sentimos, existió un error en la eliminación");
                        resultJSON.put("success", "error");
                    }
                    /*  -----------------------------------------------------------------
                        -----------  Buscar Formulario Edicion Tipos de Usuarios --------
                        ----------------------------------------------------------------- 
                     */
                }else if (accion.equals("buscarid") || accion.equals("formularioedicion")) {
                    messageError = "Lo sentimos, se presentó un error al momento de buscar los datos, por favor intente mas tarde";
                    //resAll = tipoUsuarioWS.find(String.class, tipoUsuario.getIntidtipo().toString());
                    resAll = comedorWs.getTipoUsuario(tipoUsuario.getIntidtipo().toString());
                    resultJSON.put("success", "Correcto");
                    session.setAttribute("respuestalista", resAll);
                    resultJSON.put("message", "Búsqueda del id: " + tipoUsuario.getIntidtipo().toString() + " correcta");
                    /* -----------------------------------------------------------------
                   ---------------  Validar de Tipos de Usuarios -------------------
                   ----------------------------------------------------------------- 
                     */
                } else if (accion.equals("validarTipo")) {
                    resAll = comedorWs.getTipoUsuarioByTipo(tipoUsuario.getStrtipo());
                    messageError = "Lo sentimos, se presentó un error al momento de buscar los datos, por favor intente mas tarde";
                    JSONObject respJson = new JSONObject(resAll);
                    if (respJson.get("success").equals("ok")) {
                        resultJSON.put("message", "Ya existe un registro con ese nombre");
                    } else if (respJson.get("success").equals("no existe")) {
                        resultJSON.put("message", "No existe registro");
                    }
                }
            }
        } catch (Exception e) {
            resultJSON.put("error", "Error de " + e);
            resultJSON.put("message", messageError);
        }
        session.setAttribute("respuesta", resultJSON.toString());
        response.sendRedirect("tipoUsuarioControlador.jsp");

    }


%>
