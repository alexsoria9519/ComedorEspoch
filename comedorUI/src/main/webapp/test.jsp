<%-- 
    Document   : test
    Created on : 13/08/2019, 21:02:53
    Author     : corebitsas
--%>

<%@page import="java.util.Date"%>
<%@page import="entities.Venta"%>
<%@page import="entities.Menu"%>
<%@page import="servicios.VentaWS"%>
<%@page import="entities.Costousuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            int idcostousuario; ;
            int idmenu;

            Costousuario costousuario = new Costousuario();
            Menu menu = new Menu();
            Venta venta = new Venta();
            VentaWS ventaWs = new VentaWS();

            venta.setIntcantidad(1);
            venta.setDtfecha(new Date());
            venta.setBlnestado(true);

            for (int i = 1; i <= 800; i++) {
                idcostousuario = (int) (Math.random() * 7 + 1);
                idmenu = (int) (Math.random() * 2 + 2);
                costousuario.setIntidcostousuario(idcostousuario);
                menu.setIntidmenu(idmenu);
                venta.setIntidcostousuario(costousuario);
                venta.setIntidmenu(menu);
                ventaWs.create(venta);
            }

        %>   

        <h1>Hello World!</h1>
    </body>
</html>
