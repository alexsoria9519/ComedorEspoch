<%-- 
    Document   : costoVista
    Created on : May 7, 2019, 2:42:32 PM
    Author     : Alex
--%>


<%@page import="com.comedorui.CostoUI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    //String accion = (String) session.getAttribute("accion");
    String accion = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");
    CostoUI costo = new CostoUI();

    if (accion != null) {
        if (accion.equals("formulario")) {
            response.setStatus(200);
            response.setContentType("text/plain");
            response.getWriter().write(costo.formulario(respuestaJSON));
        } else {
            if (accion.equals("listado")) {

                response.setStatus(200);
                response.setContentType("text/plain");
                response.getWriter().write(costo.listadoCostos(respuestaListado, respuestaJSON));
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
                response.getWriter().write(costo.formularioEdicion(respuestaListado));
            } else if (accion.equals("eliminar")) {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(respuestaJSON);
            }else if (accion.equals("eliminarLogico")) {
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
%>
