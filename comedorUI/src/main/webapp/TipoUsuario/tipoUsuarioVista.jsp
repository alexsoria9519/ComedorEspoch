<%-- 
    Document   : tipoUsuarioVista
    Created on : 16/05/2019, 15:08:22
    Author     : corebitsas
--%>

<%@page import="com.comedorui.TipoUsuarioUI"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    //String accion = (String) session.getAttribute("accion");
    String accion = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");
    TipoUsuarioUI tipousuario = new TipoUsuarioUI();

    if (accion != null) {
        if (accion.equals("formulario")) {
            response.setStatus(200);
            response.setContentType("text/plain");
            response.getWriter().write(tipousuario.formulario());
        } else {
            if (accion.equals("listado")) {

                response.setStatus(200);
                response.setContentType("text/plain");

                String res = tipousuario.listadoTipoUsuario(respuestaListado, respuestaJSON);
                System.err.println("Respuesta "+ res);
//                response.getWriter().write(tipousuario.listadoTipoUsuario(respuestaListado,respuestaJSON));
                response.getWriter().write(res);
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
                response.getWriter().write(tipousuario.formularioEdicion(respuestaListado));
            } else if (accion.equals("eliminar") || accion.equals("validarTipo") || accion.equals("eliminarForzoso")) {
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
