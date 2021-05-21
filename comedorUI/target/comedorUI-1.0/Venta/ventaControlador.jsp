<%-- 
    Document   : ventaControlador
    Created on : 14/06/2019, 12:24:40
    Author     : corebitsas
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = request.getParameter("accion");
    String data = request.getParameter("datos");
    String dataTipoUsuario = request.getParameter("tipoUsuario");

    String accionSession = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    String respuestaListado = (String) session.getAttribute("respuestalista");

    

    if (accion != null) {
        if (accion.equals("formulario")) {
            session.setAttribute("accion", "formulario");
            response.sendRedirect("ventaModelo.jsp");
        } else {
            if (accion.equals("costoVenta")) {
                session.setAttribute("accion", "costoVenta");
                session.setAttribute("tipoUsuario", dataTipoUsuario);
            } else if (accion.equals("registrarVenta")) {
                session.setAttribute("accion", "registrarVenta");
            } else if (accion.equals("reporteFecha")) {
                session.setAttribute("accion", "reporteFecha");
            } else if (accion.equals("reporteTipoFecha")) {
                session.setAttribute("accion", "reporteTipoFecha");
            } else if (accion.equals("formularioedicion")) {
                session.setAttribute("accion", "formularioedicion");
            } else if (accion.equals("formularioVenta")) {
                session.setAttribute("accion", "formularioVenta");
            } else if(accion.equals("getCostoUsuario")){
                session.setAttribute("accion", "getCostoUsuario");
            }
            
            
            
            else if(accion.equals("formularioReporteVentasDia")){
                session.setAttribute("accion", "formularioReporteVentasDia");
                response.sendRedirect("ventaVista.jsp");
            }
            
            
            
            session.setAttribute("data", data);
            response.sendRedirect("ventaModelo.jsp");
        }

    }

    if (accionSession != null) {
        if (accionSession.equals("listado")) {
            session.setAttribute("respuestalista", respuestaListado);
        } else if (accionSession.equals("formularioedicion")) {
            session.setAttribute("respuestalista", respuestaListado);
        }
        //}else if(accion.equals("formulario"))
        //session.setAttribute("listadotiposmenu", listadoTiposMenu);
        session.setAttribute("respuesta", respuestaJSON);
        response.sendRedirect("ventaVista.jsp");
    }
%>

