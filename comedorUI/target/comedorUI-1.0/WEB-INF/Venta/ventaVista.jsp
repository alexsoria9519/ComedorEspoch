<%-- 
    Document   : ventaVista
    Created on : May 6, 2019, 12:19:29 PM
    Author     : Alex
--%>


<%@page import="com.comedorui.VentaUI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    //String accion = (String) session.getAttribute("accion");
    String accion = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String cedula = (String) session.getAttribute("cedula");
    String prueba = "";
    VentaUI venta = new VentaUI();

    if (accion.equals("buscardatos")) {

        response.setStatus(200);
        response.setContentType("text/plain");
        //prueba = costo.listadoCostos(respuestaJSON);
        response.getWriter().write(venta.formulario(respuestaJSON, cedula));
    } else if (accion.equals("listado")) {
        //String resAll = menu.findAll(String.class);
        response.setStatus(200);
        response.setContentType("text/plain");
        //prueba = costo.listadoCostos(respuestaJSON);
        response.getWriter().write(prueba);
    }

    session.removeAttribute("accion");
    session.removeAttribute("respuesta");
    session.removeAttribute("cedula");
%>