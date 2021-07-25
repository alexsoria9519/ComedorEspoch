<%-- 
    Document   : usuarioVista
    Created on : Jul 24, 2021, 8:31:55 PM
    Author     : alex4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");

    if (accion != null) {
        if (accion.equals("registrarUsuario")) {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(respuestaJSON);
        }
    }

    session.removeAttribute("accion");
    session.removeAttribute("respuesta");
    session.removeAttribute("respuestalista");

%>
