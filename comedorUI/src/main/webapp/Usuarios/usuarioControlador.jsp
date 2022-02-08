<%-- 
    Document   : usuarioControlador
    Created on : Jul 24, 2021, 8:31:46 PM
    Author     : alex4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");
    String accionSession = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");

    if (accion != null) {

        if (accion.equals("registrarUsuario")) {
            session.setAttribute("accion", "registrarUsuario");
        }

        session.setAttribute("data", data);
        response.sendRedirect("usuarioModelo.jsp");
    }

    if (accionSession != null) {
        session.setAttribute("respuesta", respuestaJSON);
        response.sendRedirect("usuarioVista.jsp");
    }


%>
