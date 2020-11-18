<%-- 
    Document   : ventaModelo
    Created on : 14/06/2019, 12:24:48
    Author     : corebitsas
--%>

<%@page import="entities.TipoUsuarios"%>
<%@page import="servicios.TipoMenuWS"%>
<%@page import="entities.TipoMenus"%>
<%@page import="entities.Costos"%>
<%@page import="entities.VentaProcedure"%>
<%@page import="java.util.Date"%>
<%@page import="entities.CostoProcedure"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.JsonElement"%>
<%@page import="com.google.gson.JsonParser"%>
<%@page import="entities.CostosUsuarios"%>
<%@page import="entities.Menus"%>
<%@page import="EspochWS.Estudiante"%>
<%@page import="servicios.TipoUsuarioWS"%>
<%@page import="servicios.CostoWS"%>
<%@page import="entities.Tipousuario"%>
<%@page import="entities.Costo"%>
<%@page import="EspochWS.Persona"%>
<%@page import="entities.Costousuario"%>
<%@page import="servicios.CostoUsuarioWS"%>
<%@page import="servicios.VentaWS"%>
<%@page import="entities.Venta"%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.google.gson.Gson"%>

<%@page import="entities.Menu"%>
<%@page import="servicios.MenuWS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    String accion = (String) session.getAttribute("accion");
    String data = (String) session.getAttribute("data");
    String dataTipoUsuario = (String) session.getAttribute("tipoUsuario");
    String strTipoUsuario = "Funcionario";

    Gson gson = new Gson();
    JSONObject resultJSON = new JSONObject();

    Venta venta = new Venta();
    VentaWS ventaWs = new VentaWS();
    CostoUsuarioWS costoUsuarioWS = new CostoUsuarioWS();
    Costousuario costousuario = new Costousuario();
    Costos costos = new Costos();

    
    MenuWS menuWs = new MenuWS();
    Menu menu = new Menu();
    CostoWS costows = new CostoWS();

    TipoUsuarioWS tipoUsuarioWS = new TipoUsuarioWS();

    String resAll;
    String messageError = "Error";

    JsonParser parser = new JsonParser();
        JsonElement elementoJson = parser.parse(data);
        JsonObject objJson = elementoJson.getAsJsonObject();
    
    
    if (accion != null) {

        try {
            if (accion.equals("costoVenta")) {
                //Menu menu = new Menu();

                menu = gson.fromJson(data, Menu.class);

                menu = menuWs.find(Menu.class, menu.getIntidmenu().toString());

                resAll = costows.findByStrDetalle(String.class, menu.getIntidtipo().getStrtipo() + " " + dataTipoUsuario);
                resultJSON.put("datosCosto", resAll);

                //session.setAttribute("respuestalista", "{ \"ventas\" : " + resAll + " }");
                //resultJSON.put("message", "Listado Correcto");
            } else if (accion.equals("formularioVenta")) {
                costousuario = gson.fromJson(data, Costousuario.class);
                resultJSON.put("cedula", costousuario.getStrcedula());

                resAll = costoUsuarioWS.findExterno(costousuario.getStrcedula());
                resultJSON.put("datosPersona", resAll);

                resAll = costoUsuarioWS.findByStrCedula(String.class, costousuario.getStrcedula());

                if (resAll.equals("[]")) {
                    resAll = costoUsuarioWS.findSiEsEstudiante(costousuario.getStrcedula());

                    if (!resAll.equals("{\"error\": \"No hay datos\" }")) {
                        resultJSON.put("datosEstudiante", resAll);
                        strTipoUsuario = "Estudiante";
                    } else {
                        resAll = costoUsuarioWS.findSiEsDocente(costousuario.getStrcedula());
                        if (!resAll.equals("{\"error\": \"No hay datos\" }")) {
                            resultJSON.put("datosDocente", resAll);
                            strTipoUsuario = "Docente";
                        }
                    }
                    resultJSON.put("existeCostoUsuario", false);                    
                }else{
                    
                    CostosUsuarios costosUsuarios = new CostosUsuarios();
                    
                    costosUsuarios = gson.fromJson("{ \"costosUsuarios\" : " + resAll + " }", CostosUsuarios.class);
                    strTipoUsuario = costosUsuarios.getCostosUsuarios().get(0).getIntidtipo().getStrtipo();
                    resultJSON.put("datosCostoUsuario", "{ \"costosUsuarios\" : " + resAll + " }");
                    resultJSON.put("existeCostoUsuario", true); 
                }
                
                resultJSON.put("tipoUsuario", strTipoUsuario);

                resAll = menuWs.findAll(String.class);
                resultJSON.put("listadoMenus", "{ \"menus\" : " + resAll + " }");

                Menus menus = new Menus();

                menus = gson.fromJson("{ \"menus\" : " + resAll + " }", Menus.class);

                resAll = costows.findByStrDetalle(String.class, menus.getMenus().get(0).getIntidtipo().getStrtipo() + " " + strTipoUsuario);
                resultJSON.put("datosCosto", resAll);

                resultJSON.put("message", "Formulario Venta Correcto");
                messageError = "Error en el formulario de venta";

            } else if(accion.equals("reporteTipoFecha")){
                String datosReporte="";
                VentaProcedure ventaProcedure = new VentaProcedure();
                TipoMenus tipoMenus = new TipoMenus();
                TipoMenuWS tipoMenuWS = new TipoMenuWS();
                TipoUsuarios tipoUsuarios = new TipoUsuarios();
                
                ventaProcedure = gson.fromJson(data, VentaProcedure.class);
                
                tipoMenus = tipoMenuWS.findAll(TipoMenus.class);
                tipoUsuarios = tipoUsuarioWS.findAll(TipoUsuarios.class);
                
                for(int i=0;i<tipoMenus.getTipoMenus().size();i++){
                    ventaProcedure.setTipomenu(tipoMenus.getTipoMenus().get(i).getStrtipo());
                    for(int j=0; j<tipoUsuarios.getTipoUsuarios().size();j++){
                        ventaProcedure.setTipousuario(tipoUsuarios.getTipoUsuarios().get(j).getStrtipo());
                        datosReporte +=  ventaWs.ventasPorFecha(ventaProcedure);
                    }
                }
                
            }else    
                {

                venta = gson.fromJson(data, Venta.class);

                if (accion.equals("registrarVenta")) {
                    
                    CostoProcedure costoProcedure = new CostoProcedure();
                    
                    if(!objJson.get("existeusuario").getAsBoolean()){
                        costousuario.ingresarCostoUsuario(data);
                    }
                    
                    menu = menuWs.find(Menu.class, objJson.get("intidmenu").getAsJsonObject().get("intidmenu").getAsString());
                    
                    costoProcedure.setTipomenus(menu.getIntidtipo().getStrtipo());
                    costoProcedure.setCedula(objJson.get("cedula").getAsString());
                    costoProcedure.setTipousuario(objJson.get("tipousuario").getAsString());
                    
                    
                    costousuario = costoUsuarioWS.findByAllData(costoProcedure, Costousuario.class);
                    venta.setIntidcostousuario(costousuario);
                    ventaWs.create(venta);

                    resultJSON.put("message", "Ingreso correcto");
                    messageError = "Error en el ingreso del menu";

                } else if (accion.equals("reporteFecha")) {
                    resAll = ventaWs.findByFecha(venta, String.class);
                    resultJSON.put("datosBusquedaFecha", resAll);
                    resultJSON.put("message", "Modificación correcta");
                    messageError = "Error en la modificacion";

                } else if (accion.equals("eliminarLogico")) {

                } else if (accion.equals("buscarid")) {
                    /*
                    resAll = menuWs.find(String.class, menu.getIntidmenu().toString());
                    resultJSON.put("success", "Correcto");
                    resultJSON.put("message", "Modificación correcta");
                    session.setAttribute("respuestalista", resAll);
                     */
                    //resultJSON.put("message", "Búsqueda del id: " + menu.getIntidmenu().toString() + " correcta");
                } else if (accion.equals("formularioedicion")) {
                    /*
                    resAll = tipoMenuWS.findAll(String.class);
                    session.setAttribute("listadotiposmenu", "{ \"tipoMenus\" : " + resAll + " }");

                    resAll = menuWs.find(String.class, menu.getIntidmenu().toString());
                    session.setAttribute("respuestalista", resAll);
                    
                    resultJSON.put("success", "Correcto");
                    resultJSON.put("message", "Búsqueda del id: " + menu.getIntidmenu().toString() + " correcta");
                     */
                }
            }

        } catch (Exception e) {
            System.err  .println(e);
            resultJSON.put("error", "Error de " + e);
            resultJSON.put("message", messageError);
        }
        session.setAttribute("respuesta", resultJSON.toString());
        response.sendRedirect("ventaControlador.jsp");

    }


%>
