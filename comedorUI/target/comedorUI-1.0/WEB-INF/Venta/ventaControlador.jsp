<%-- 
    Document   : ventaControlador
    Created on : May 6, 2019, 12:19:07 PM
    Author     : Alex
--%>

<%

    String accion = request.getParameter("accion");
    String cedula = request.getParameter("cedula");
    String accionSession = (String) session.getAttribute("accion");
    String respuestaJSON = (String) session.getAttribute("respuesta");
    //VentaUI venta = new VentaUI();
    //MenuWS menu = new MenuWS();

    if (accion != null) {
        if (accion.equals("buscardatos")) {
            session.setAttribute("accion", "buscardatos");
            session.setAttribute("cedula", cedula);
            response.sendRedirect("ventaModelo.jsp");
        } else if (accion.equals("listado")) {
            session.setAttribute("accion", "listado");
            response.sendRedirect("costoModelo.jsp");
        }

    }

    if (accionSession != null) {
        if (accionSession.equals("listado")) {
            session.setAttribute("accion", "listado");
            session.setAttribute("respuesta", respuestaJSON);
            response.sendRedirect("ventaVista.jsp");
        }else if(accionSession.equals("buscardatos")){
            session.setAttribute("accion", "buscardatos");
            session.setAttribute("respuesta", respuestaJSON);
            response.sendRedirect("ventaVista.jsp");
        }
    }

%>