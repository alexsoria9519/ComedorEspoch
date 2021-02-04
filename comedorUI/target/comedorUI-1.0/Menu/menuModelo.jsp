<%-- 
    Document   : menuModelo.jsp
    Created on : May 7, 2019, 2:30:12 PM
    Author     : Alex
--%>





<%@page import="servicios.ComedorWS"%>
<%@page import="entities.Planificacionmenu"%>
<%@page import="servicios.PlanificacionMenuWS"%>
<%@page import="servicios.TipoMenuWS"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.google.gson.Gson"%>

<%@page import="entities.Menu"%>
<%@page import="servicios.MenuWS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");
    String dataPlanificacionMenu = (String) session.getAttribute("dataPlanificacionMenu");

    Gson gson = new Gson();
    JSONObject resultJSON = new JSONObject();
    JSONObject requestJSON = new JSONObject();

    Menu menu = new Menu();
    Planificacionmenu planificacionmenu = new Planificacionmenu();

    MenuWS menuWs = new MenuWS();
    TipoMenuWS tipoMenuWS = new TipoMenuWS();
    PlanificacionMenuWS fechasMenuWS = new PlanificacionMenuWS();
    Planificacionmenu fechas = new Planificacionmenu();

    ComedorWS comedorWs = new ComedorWS();

    String resAll;
    String messageError = "Error";

    if (accion != null) {

        try {
            if (accion.equals("listado")) {
//                resAll = comedorWs.findAll(String.class);
//                session.setAttribute("respuestalista", "{ \"menus\" : " + resAll + " }");
//                resultJSON.put("message", "Listado Correcto");

                resAll = comedorWs.getListadoMenus();
                JSONObject respJsonListaMenu = new JSONObject(resAll);

                if (respJsonListaMenu.getString("success").equals("ok")) {
                    session.setAttribute("respuestalista", respJsonListaMenu.getString("menus"));
                } else {
                    session.setAttribute("respuestalista", "[]");
                    resultJSON.put("message", respJsonListaMenu.getString("data"));
                }

                resultJSON.put("success", respJsonListaMenu.getString("success"));
                resultJSON.put("cantidad", respJsonListaMenu.getInt("cantidad"));
            } else if (accion.equals("formulario")) {

                resAll = comedorWs.getListadoTiposMenus();
                resultJSON.put("listadotiposmenu", resAll);
                resultJSON.put("message", "Listado de tipos de Menus Correcto");
                //resAll = tipoMenuWS.findAll(String.class);
                //session.setAttribute("listadotiposmenu", "{ \"tipoMenus\" : " + resAll + " }");

            } else {

                menu = gson.fromJson(data, Menu.class);

                if (accion.equals("ingreso")) {
                    messageError = "Error en el ingreso del menu";
                    menu.setBlnestado(true);
                    requestJSON.put("menu", gson.toJson(menu));
                    resAll = comedorWs.insertMenu(requestJSON.toString());
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    resultJSON.put("success", respJson.getString("success"));
                    requestJSON.remove("menu");
                } else if (accion.equals("edicion")) {
                    menu.setBlnestado(true);

                    requestJSON.put("menu", gson.toJson(menu));
                    resAll = comedorWs.editarMenu(requestJSON.toString());
                    JSONObject respJsonMenu = new JSONObject(resAll);
                    resultJSON.put("success", respJsonMenu.getString("success"));
                    resultJSON.put("message", respJsonMenu.getString("data"));
                    messageError = "Error en la modificacion";
                } else if (accion.equals("eliminar")) {

                    menu.setBlnestado(false);

                    menuWs.editEstado(menu, menu.getIntidmenu().toString());

                    resultJSON.put("message", "Eliminación correcta");
                    messageError = "Error en la eliminación menu";

                } else if (accion.equals("buscarid")) {

                    resAll = menuWs.find(String.class, menu.getIntidmenu().toString());
                    resultJSON.put("success", "Correcto");
                    resultJSON.put("message", "Modificación correcta");
                    session.setAttribute("respuestalista", resAll);
                    resultJSON.put("message", "Búsqueda del id: " + menu.getIntidmenu().toString() + " correcta");
                } else if (accion.equals("formularioActivarMenu")) {
                    messageError = "Error al obtener los datos del menú";
                    resAll = comedorWs.getMenu(menu.getIntidmenu().toString());
                    JSONObject respJsonMenu = new JSONObject(resAll);

                    if (respJsonMenu.getString("success").equals("ok")) {
                        resultJSON.put("menu", respJsonMenu.getString("menu"));
                        resultJSON.put("success", respJsonMenu.getString("success"));
                    } else {
                        resultJSON.put("message", respJsonMenu.getString("data"));
                        resultJSON.put("success", "error");
                    }

                    resAll = comedorWs.getPlanificacionMenusByIdMenu(menu.getIntidmenu().toString());
                    JSONObject respJsonPlanificacionMenu = new JSONObject(resAll);

                    if (respJsonPlanificacionMenu.getString("success").equals("ok")) {
                        resultJSON.put("planificacionesMenu", respJsonPlanificacionMenu.getString("planificacionesMenu"));
                        resultJSON.put("success", respJsonPlanificacionMenu.getString("success"));
                        resultJSON.put("cantidadPlanificacionesMenu", respJsonPlanificacionMenu.getInt("cantidad"));
                    } else {
                        resultJSON.put("planificacionesMenu", "[]");
                        resultJSON.put("message", respJsonPlanificacionMenu.getString("data"));
                        resultJSON.put("success", "error");
                        resultJSON.put("cantidadPlanificacionesMenu", 0);
                    }

                } else if (accion.equals("formularioedicion")) {

                    messageError = "Error al obtener los datos del menú";
                    resAll = comedorWs.getMenu(menu.getIntidmenu().toString());
                    JSONObject respJSONMenu = new JSONObject(resAll);

                    if (respJSONMenu.getString("success").equals("ok")) {
                        resultJSON.put("menu", respJSONMenu.getString("menu"));
                    } else {
                        resultJSON.put("message", respJSONMenu.getString("data"));
                        resultJSON.put("error", "Error al obtener los datos del menu");
                    }
                    resultJSON.put("success", respJSONMenu.getString("success"));
                    resAll = comedorWs.getListadoTiposMenus();
                    JSONObject respJsonListaMenu = new JSONObject(resAll);
                    resultJSON.put("tiposMenus", resAll);
                    resultJSON.put("success", respJsonListaMenu.getString("success"));
                } else if (accion.equals("menusActivosFechas")) {
                    messageError = "Error en busqueda de menus activos";
                    resAll = comedorWs.getPlanificacionMenusFechaActual();

                    JSONObject respJsonListaMenu = new JSONObject(resAll);

                    if (respJsonListaMenu.getString("success").equals("ok")) {
                        session.setAttribute("respuestalista", respJsonListaMenu.getString("planificacionesMenu"));
                    } else {
                        session.setAttribute("respuestalista", "[]");
                        resultJSON.put("message", respJsonListaMenu.getString("data"));
                    }

                    resultJSON.put("success", respJsonListaMenu.getString("success"));
                    resultJSON.put("cantidad", respJsonListaMenu.getInt("cantidad"));

                } else if (accion.equals("cambiarEstadoMenu")) {
                    requestJSON.put("menu", gson.toJson(menu));
                    resAll = comedorWs.cambiarEstadoMenu(requestJSON.toString());

                    JSONObject respJSONMenu = new JSONObject(resAll);
                    resultJSON.put("success", respJSONMenu.getString("success"));
                    resultJSON.put("message", respJSONMenu.getString("data"));
                    messageError = "Error en el ingreso de las fechas del menú";
                } else if (accion.equals("desactivarPlanificacionMenu")) {

                    planificacionmenu = gson.fromJson(dataPlanificacionMenu, Planificacionmenu.class);

                    messageError = "Error en la desactivacion del Menú";
                    requestJSON.put("menu", gson.toJson(menu));
                    resAll = comedorWs.desactivarPlanificacionMenu(planificacionmenu.getIntid().toString());
                    JSONObject respJson = new JSONObject(resAll);
                    resultJSON.put("message", respJson.getString("data"));
                    resultJSON.put("success", respJson.getString("success"));
                } else if (accion.equals("crearPlanificacionMenu")) {
                    planificacionmenu = gson.fromJson(dataPlanificacionMenu, Planificacionmenu.class);
                    requestJSON.put("planificacionMenu", gson.toJson(planificacionmenu));
                    resAll = comedorWs.insertPlanificacionMenu(requestJSON.toString());

                    JSONObject respJsonPlanificacion = new JSONObject(resAll);

                    resultJSON.put("success", respJsonPlanificacion.getString("success"));
                    resultJSON.put("message", respJsonPlanificacion.getString("data"));
                } else if (accion.equals("listadoPlanificacionMenu")) {

                    resAll = comedorWs.getPlanificacionMenusByIdMenu(menu.getIntidmenu().toString());
                    JSONObject respJsonPlanificacionMenu = new JSONObject(resAll);

                    if (respJsonPlanificacionMenu.getString("success").equals("ok")) {
                        resultJSON.put("planificacionesMenu", respJsonPlanificacionMenu.getString("planificacionesMenu"));
                        resultJSON.put("success", respJsonPlanificacionMenu.getString("success"));
                        resultJSON.put("cantidadPlanificacionesMenu", respJsonPlanificacionMenu.getInt("cantidad"));
                    } else {
                        resultJSON.put("planificacionesMenu", "[]");
                        resultJSON.put("message", respJsonPlanificacionMenu.getString("data"));
                        resultJSON.put("success", "error");
                        resultJSON.put("cantidadPlanificacionesMenu", 0);
                    }
                }
            }

        } catch (Exception e) {
            resultJSON.put("error", "Error de " + e);
            resultJSON.put("message", messageError);
        }
        session.setAttribute("respuesta", resultJSON.toString());
        response.sendRedirect("menuControlador.jsp");

    }


%>
