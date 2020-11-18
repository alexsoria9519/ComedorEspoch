<%-- 
    Document   : tipoUsuarioControlador
    Created on : 16/05/2019, 15:08:05
    Author     : corebitsas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");
    String accionSession = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");

    if (accion != null) {
        if (accion.equals("formulario")) {
            session.setAttribute("accion", "formulario");
            response.sendRedirect("tipoUsuarioVista.jsp");
        } else {
            if (accion.equals("listado")) {
                session.setAttribute("accion", "listado");
            } else if (accion.equals("ingreso")) {
                session.setAttribute("accion", "ingreso");
            } else if (accion.equals("edicion")) {
                session.setAttribute("accion", "edicion");
            } else if (accion.equals("eliminar")) {
                session.setAttribute("accion", "eliminar");
            } else if(accion.equals("eliminarForzoso")){
                session.setAttribute("accion", "eliminarForzoso");
            }else if (accion.equals("formularioedicion")) {
                session.setAttribute("accion", "formularioedicion");
            } else if(accion.equals("validarTipo")){
                session.setAttribute("accion", "validarTipo");
            }
            session.setAttribute("data", data);
            response.sendRedirect("tipoUsuarioModelo.jsp");
        }

    }

    if (accionSession != null) {
        if (accionSession.equals("listado")) {
            session.setAttribute("respuestalista", respuestaListado);
        } else if (accionSession.equals("formularioedicion")) {
            session.setAttribute("respuestalista", respuestaListado);
        }
        session.setAttribute("respuesta", respuestaJSON);
        response.sendRedirect("tipoUsuarioVista.jsp");
    }

%>
