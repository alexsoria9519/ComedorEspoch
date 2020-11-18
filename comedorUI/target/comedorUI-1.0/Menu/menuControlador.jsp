<%-- 
    Document   : costoControlador
    Created on : May 7, 2019, 2:42:04 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");
    String dataFechaMenu = request.getParameter("datosFechaMenu");
    
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
            } else if (accion.equals("eliminarLogico")) {
                session.setAttribute("accion", "eliminarLogico");
            } else if (accion.equals("formularioedicion")) {
                session.setAttribute("accion", "formularioedicion");
            } else if (accion.equals("validarMenuExistente")){
                session.setAttribute("accion", "validarMenuExistente");
            } else if(accion.equals("menusActivosFechas")){
                session.setAttribute("accion", "menusActivosFechas");
            } else if(accion.equals("formularioActivarMenu")){
                session.setAttribute("accion", "formularioActivarMenu");
            } else if(accion.equals("activarMenu")){
                session.setAttribute("accion", "activarMenu");
            }
            session.setAttribute("data", data);
            session.setAttribute("dataFechaMenu", dataFechaMenu);
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

