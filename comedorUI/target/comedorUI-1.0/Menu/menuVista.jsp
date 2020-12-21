<%-- 
    Document   : menuVista
    Created on : May 7, 2019, 2:42:32 PM
    Author     : Alex
--%>


<%@page import="com.comedorui.MenuUI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    //String accion = (String) session.getAttribute("accion");
    String accion = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");
    String listadoTiposMenu = (String) session.getAttribute("listadotiposmenu");
    MenuUI menu = new MenuUI();
    
    if (accion != null) {
        if (accion.equals("formulario")) {
            response.setStatus(200);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(menu.formulario(respuestaJSON));
        } else {
            if (accion.equals("listado")) {
                
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(menu.listadoMenus(respuestaListado, respuestaJSON));
            } else if (accion.equals("ingreso")) {
                response.setStatus(201);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("edicion")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("formularioedicion")) {
                response.setStatus(200);
                response.setContentType("text/plain");
                response.getWriter().write(menu.formularioEdicion(respuestaListado, listadoTiposMenu));
            } else if (accion.equals("eliminarLogico")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("validarMenuExistente")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("menusActivosFechas")) {
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.getWriter().write(menu.listadoMenusFechasActivos(respuestaJSON));
            } else if (accion.equals("formularioActivarMenu")) {
                response.setStatus(200);
//                response.setContentType("text/plain");
                response.setContentType("application/json");
                response.getWriter().write(menu.formularioActivarMenu(respuestaJSON));

//                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("activarMenu")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            } else if (accion.equals("desactivarPlanificacionMenu")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            }
        }
    }
    session.removeAttribute("accion");
    session.removeAttribute("respuesta");
    session.removeAttribute("respuestalista");
    session.removeAttribute("listadotiposmenu");
%>
