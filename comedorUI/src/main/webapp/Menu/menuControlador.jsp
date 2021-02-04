<%-- 
    Document   : costoControlador
    Created on : May 7, 2019, 2:42:04 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");
    String dataPlanificacionMenu = request.getParameter("datosPlanificacion");

    String accionSession = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");

    //String listadoTiposMenu = (String) session.getAttribute("listadotiposmenu");
    if (accion != null) {
        if (accion.equals("formulario")) {
            session.setAttribute("accion", "formulario");
            response.sendRedirect("menuModelo.jsp");
        } else {
            if (accion.equals("listado")) {
                session.setAttribute("accion", "listado");
            } else if (accion.equals("ingreso")) {
                session.setAttribute("accion", "ingreso");
            } else if (accion.equals("edicion")) {
                session.setAttribute("accion", "edicion");
            } else if (accion.equals("eliminar")) {
                session.setAttribute("accion", "eliminar");
            } else if (accion.equals("formularioedicion")) {
                session.setAttribute("accion", "formularioedicion");
            } else if (accion.equals("validarMenuExistente")) {
                session.setAttribute("accion", "validarMenuExistente");
            } else if (accion.equals("menusActivosFechas")) {
                session.setAttribute("accion", "menusActivosFechas");
            } else if (accion.equals("formularioActivarMenu")) {
                session.setAttribute("accion", "formularioActivarMenu");
            } else if (accion.equals("cambiarEstadoMenu")) {
                session.setAttribute("accion", "cambiarEstadoMenu");
            } else if (accion.equals("desactivarPlanificacionMenu")) {
                session.setAttribute("accion", "desactivarPlanificacionMenu");
            } else if (accion.equals("crearPlanificacionMenu")) {
                session.setAttribute("accion", "crearPlanificacionMenu");
            } else if (accion.equals("listadoPlanificacionMenu")) {
                session.setAttribute("accion", "listadoPlanificacionMenu");
            }
            session.setAttribute("data", data);
            session.setAttribute("dataPlanificacionMenu", dataPlanificacionMenu);
            response.sendRedirect("menuModelo.jsp");
        }

    }

    if (accionSession != null) {
        if (accionSession.equals("listado")) {
            session.setAttribute("respuestalista", respuestaListado);
        } else if (accionSession.equals("formularioedicion")) {
            session.setAttribute("respuestalista", respuestaListado);
        }
        session.setAttribute("respuesta", respuestaJSON);
        response.sendRedirect("menuVista.jsp");
    }
%>

