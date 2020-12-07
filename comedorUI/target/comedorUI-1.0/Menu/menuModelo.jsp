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
    String dataFechaMenu = (String) session.getAttribute("dataFechaMenu");

    Gson gson = new Gson();
    JSONObject resultJSON = new JSONObject();
    JSONObject requestJSON = new JSONObject();

    Menu menu = new Menu();
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
                session.setAttribute("respuestalista", resAll);
                resultJSON.put("message", "Listado Correcto");
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
                } else if (accion.equals("edicion")) {
                    menu.setBlnestado(true);
                    menuWs.edit(menu, menu.getIntidmenu().toString());

                    fechas = gson.fromJson(dataFechaMenu, Planificacionmenu.class);
                    fechas.setIntidmenu(menu);
                    fechasMenuWS.edit(fechas, fechas.getIntid().toString());

                    resultJSON.put("message", "Modificación correcta");
                    messageError = "Error en la modificacion";

                } else if (accion.equals("eliminarLogico")) {
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
                } else if (accion.equals("formularioedicion") || accion.equals("formularioActivarMenu")) {

                    resAll = tipoMenuWS.findAll(String.class);
                    session.setAttribute("listadotiposmenu", "{ \"tipoMenus\" : " + resAll + " }");

                    resAll = menuWs.find(String.class, menu.getIntidmenu().toString());
                    session.setAttribute("respuestalista", resAll);

                    resultJSON.put("success", "Correcto");
                    resultJSON.put("message", "Búsqueda del id: " + menu.getIntidmenu().toString() + " correcta");
                } else if (accion.equals("menusActivosFechas")) {
                    resAll = fechasMenuWS.listMenusByFecha(String.class);
                    resultJSON.put("success", "Correcto");
                    resultJSON.put("fechasMenusActivas", "{ \"planificionMenus\" : " + resAll + " }");
                    resAll = menuWs.findByBlnestado(String.class);
                    resultJSON.put("menusActivos", "{ \"menus\" : " + resAll + " }");
                    resultJSON.put("message", "Búsqueda de menus activos correcta");
                    messageError = "Error en busqueda de menus activos";
                } else if (accion.equals("activarMenu")) {
                    fechas = gson.fromJson(dataFechaMenu, Planificacionmenu.class);
                    fechas.setIntidmenu(menu);

                    fechasMenuWS.create(fechas);

                    resultJSON.put("message", "Ingreso correcto");
                    messageError = "Error en el ingreso de las fechas del menú";
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
