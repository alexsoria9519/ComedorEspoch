<%-- 
    Document   : ventaModelo
    Created on : May 6, 2019, 12:19:18 PM
    Author     : Alex
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="EspochWS.Persona"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="servicios.CostoUsuarioWS"%>
<%

    String accion = (String) session.getAttribute("accion");
    String cedula =(String) session.getAttribute("cedula");
    String data = (String) session.getAttribute("data");
    String resAll;

    //Costos costos = new Costos();
    Persona persona = new Persona();
    CostoUsuarioWS costoUsuarioWs = new CostoUsuarioWS();
    Gson gson = new Gson();
    

    if (accion != null) {
        if (accion.equals("listado")) {
            //String resAll = costoWs.findAll(String.class);
            //costos = gson.fromJson("{ \"costos\" : " + resAll + " }", Costos.class);
            session.setAttribute("accion", "listado");
            //session.setAttribute("respuesta", "{ \"costos\" : " + resAll + " }");
            response.sendRedirect("ventaControlador.jsp");
        } else if (accion.equals("buscardatos")) {
            resAll = costoUsuarioWs.findExterno(cedula);
            session.setAttribute("accion", "buscardatos");
            session.setAttribute("cedula", cedula);
            session.setAttribute("respuesta", resAll);
            response.sendRedirect("ventaControlador.jsp");
        }
    }


%>
