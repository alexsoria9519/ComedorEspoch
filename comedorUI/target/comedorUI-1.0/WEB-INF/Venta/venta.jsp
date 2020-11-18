<%-- 
    Document   : Venta
    Created on : May 6, 2019, 12:19:36 PM
    Author     : Alex
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.google.gson.Gson"%>

<%@page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@page contentType='text/html' pageEncoding='UTF-8'%>
<html lang="es" class="no-js">
    <head>
        <title>Comedor ESPOCH Venta</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">


        <!-- CSS -->
        <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/css/main.css" rel="stylesheet" type="text/css">
        <link href="../assets/css/my-custom-styles.css" rel="stylesheet" type="text/css">


        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/kingadmin-favicon144x144.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/kingadmin-favicon114x114.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/kingadmin-favicon72x72.png">
        <link rel="apple-touch-icon-precomposed" sizes="57x57" href="../assets/ico/kingadmin-favicon57x57.png">
        <link rel="shortcut icon" href="../assets/ico/favicon.ico">


        <link href="../js/css/dtic.css" rel="stylesheet" type="text/css"/>
        <script src="../js/ejemplo.js" type="text/javascript"></script>
    </head>

    <%
        String tipoLogueo = "";
        Boolean estado = false;
        session.setAttribute("tipousuario2", "");
        session.setAttribute("TipoUser", "");
        session.setAttribute("ingreso", "false");
        String codigocas = "";
        String cedulacas = "";
        try {
            // recuperar tickets de proxy para un usuario en particular y atributos.
            //Recupera un ticket de proxy CAS para este principal específico

            if (request.getUserPrincipal() != null) {
                AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
                final Map attributes = principal.getAttributes();
                String[] cadena = attributes.toString().split("=");

                for (String i : cadena) {
                    if (i.contains("cedula")) {
                        session.setAttribute("existe", "true");
                        estado = true;
                    }
                }
                if (attributes != null) {
                    Iterator attributeNames = attributes.keySet().iterator();
                    if (attributeNames.hasNext()) {
                        for (; attributeNames.hasNext();) {
                            String attributeName = (String) attributeNames.next();
                            final Object attributeValue = attributes.get(attributeName);
                            if (attributeValue instanceof List) {
                                final List values = (List) attributeValue;
                            } else {
                                if (attributeName == "perid") {
                                    session.setAttribute("ingreso", "true");
                                    codigocas = attributeValue.toString();
                                    session.setAttribute("tipousuario2", "");
                                }
                                if (attributeName == "cedula") {
                                    cedulacas = attributeValue.toString();
                                }
                                if (attributeName == "clientName") {
                                    session.setAttribute("tipousuario2", attributeValue.toString());
                                }
                            }
                        }
                    } else {
                        out.println("<pre>The attribute map is empty. Review your CAS filter configurations.</pre>");
                    }
                } else {
                    out.println("<pre>The user principal is empty from the request object. Review the wrapper filter configuration.</pre>");
                }
            }
            if (estado == true) {
                if (!codigocas.equals("")) {
                    //tipo de sesion
                    if (session.getAttribute("tipousuario2").toString().isEmpty()) {
                        tipoLogueo = "Oasis";
                    } else {
                        tipoLogueo = "Institucional";
                    }

                    //aqui desarrollo
                }
            }

        } catch (Exception e) {
        } finally {

        }
    %>
    <link href="../assets/css/skins/darkblue.css" rel="stylesheet" type="text/css"/>
    <body class="sidebar-fixed topnav-fixed dashboard">
        <div id="Logeo" name="Logeo" ><div style="display:none;"><% out.println(tipoLogueo);%></div></div>

        <!-- WRAPPER -->
        <div id="wrapper" class="wrapper">
            <!-- TOP BAR -->
            <div class="top-bar navbar-fixed-top">
                <div class="container">
                    <div class="clearfix">
                        <!-- logo -->
                        <div class="pull-left left logo">
                            <h4 style="color:#FFF">Comedor ESPOCH</h4>
                            <!--<a href="index.jsp"><img src="../assets/img/logoDTIC.png"  /></a>-->
                        </div>

                        <!-- end logo -->
                        <div class="pull-right right">
                            <!-- top-bar-right -->
                            <div class="top-bar-right">
                                <!-- logged user and the menu -->
                                <div class="logged-user">
                                    <div id="btnUser" class="btn-group">
                                        <a  class="btn btn-link dropdown-toggle" data-toggle="dropdown" >
                                            <img src="../assets/img/user-avatar.png" alt="User Avatar" />
                                            <span class='name'>Usuario logueado</span><span class='caret'></span>


                                        </a>
                                        <ul class="dropdown-menu" role="menu" >
                                            <li>
                                                <a onclick="fncLnkAdmin();" style='cursor: pointer'>
                                                    <i class="fa fa-user"></i>
                                                    <span class="text">Menu 1</span>
                                                </a>
                                            </li>
                                            <li>
                                                <a onclick="Matar();" style='cursor: pointer' >
                                                    <i class="fa fa-power-off"></i>
                                                    <span class="text">SALIR</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <!-- end logged user and the menu -->
                            </div>
                            <!-- end top-bar-right -->
                        </div>
                    </div>
                </div>
                <!-- /container -->
            </div>
            <!-- END TOP BAR -->
            <!-- LEFT SIDEBAR -->
            <div id="left-sidebar" class="left-sidebar">
                <div class="sidebar-minified js-toggle-minified">
                    <i class="fa fa-exchange"></i>
                </div>
                <div class="sidebar-scroll">
                    <nav class="main-nav">
                        <ul class="main-menu">
                            <li style='cursor: pointer' title="Venta">
                                <a onclick=""  href="venta.jsp">
                                    <i class="fa fa-delicious"></i><span class="text">Venta</span>
                                </a>
                            </li>
                            <li style='cursor: pointer' title="Precios">
                                <a onclick="" href="../Costo/costo.jsp">
                                    <i class="fa fa-delicious"></i><span class="text">Precios</span>
                                </a>
                            </li>
                            <li style='cursor: pointer' title="Menu">
                                <a onclick="" href="Menu/menu.jsp">
                                    <i class="fa fa-delicious"></i><span class="text">Menú</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>

            </div>
            <!-- END LEFT SIDEBAR -->
            <!-- MAIN CONTENT WRAPPER -->
            <div id="main-content-wrapper" class="content-wrapper">
                <div class="row">
                    <div class="col-lg-12 ">
                        <ul id='divSeguimiento' class="breadcrumb">
                            <li><i class="fa fa-home"></i><a href="index.jsp">Inicio</a></li>
                        </ul>
                    </div>
                </div>
                <!-- main -->
                <div id='contenidoDinamico' class="content">
                    <div class="main-header">
                        <h2>Inicio</h2>
                        <em>Panel de administración</em>
                    </div>
                    <div id='contenidoInferior' class="main-content">

                        <form class="lead col-lg-10" id="formulario" method="post">

                            <div class="form-group col-lg-12 ">
                                <label for="cedula">Cédula:</label>
                                <input  type="text" class="form-control busquedaDatos" id="cedula" name="cedula" placeholder="1234567890" autofocus />

                            </div>

                            <div class="form-group col-lg-12 ">
                                <button type="submit" class="btn  btn-success" onclick="datosCedula(event)">Buscar Datos <i class="fa fa-check" aria-hidden="true"></i></button>
                            </div>

                        </form>
                        <!--<form class="lead col-lg-10" id="formulario" method="post">
                            

                            <div class="col-lg-12 form-inline "> 
                                <div class="form-group col-lg-6 "> 
                                    <label for="detalle">Detalle:</label>
                                    <textarea id="detalle" name="detalle" placeholder="Costo de un almuerzo para estudiante"></textarea>
                                    
                                </div>

                                <div class="form-group col-lg-6"> 
                                    <label for="valor">Valor </label>
                                    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="1.60"/>
                                </div>
                            </div>

                            <div class="col-lg-12 form-inline "> 
                                <div class="form-group col-lg-6 "> 
                                    <label for="fecha">Fecha: </label>
                                    <input type="text" class="form-control" id="fecha" name="fecha" placeholder="02/05/2019"/>
                                </div>

                                <div class="form-group col-lg-6"> 
                                    <label for="carrera">Estado </label>
                                    <select>
                                        <option value="true">Activo</option>     
                                        <option value="false">Inactivo</option>     
                                    </select>
                                </div>
                            </div>

                                                       
                            <div class="col-lg-12 form-inline "> 
                                <div class="form-group col-lg-6 "> 
                                    <button type="submit" class="btn  btn-success">Guardar <i class="fa fa-check" aria-hidden="true"></i></button> 
                                </div>

                                <div class="form-group col-lg-6"> 
                                    <button type="" class="btn   btn-danger">Cancelar <i class="fa fa-times" aria-hidden="true"></i></button> 
                                </div>
                            </div>
                        </form>-->


                        </br>
                        <div class="bottom-30px"></div>
                    </div>

                </div>
                <!-- /main -->
                <!-- FOOTER -->
                <footer class="footer">
                    <div> <a href="http://dtic.espoch.edu.ec/" target="_blank" style="color:#FFF;"> <img width="45" height="15" src="../assets/img/dtic.png" > Escuela Superior Politécnica de Chimborazo 2018</a></div>
                </footer>
                <!-- END FOOTER -->
            </div>

            <!-- END CONTENT WRAPPER -->
        </div>
        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script src="../assets/js/jquery/jquery-2.1.0.min.js"></script>
        <script src="../assets/js/bootstrap/bootstrap.js"></script>
        <!--<script src="../assets/js/plugins/modernizr/modernizr.js"></script>-->
        <!--<script src="../assets/js/plugins/bootstrap-tour/bootstrap-tour.custom.js"></script>
        <script src="../assets/js/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>-->
        <script src="../assets/js/king-common.js"></script>
        <!--<script src="../assets/js/plugins/stat/jquery.easypiechart.min.js"></script>
        <script src="../assets/js/plugins/raphael/raphael-2.1.0.min.js"></script>
        <script src="../assets/js/plugins/stat/flot/jquery.flot.min.js"></script>
        <script src="../assets/js/plugins/stat/flot/jquery.flot.resize.min.js"></script>
        <script src="../assets/js/plugins/stat/flot/jquery.flot.time.min.js"></script>
        <script src="../assets/js/plugins/stat/flot/jquery.flot.pie.min.js"></script>
        <script src="../assets/js/plugins/stat/flot/jquery.flot.tooltip.min.js"></script>
        <script src="../assets/js/plugins/jquery-sparkline/jquery.sparkline.min.js"></script>
        <script src="../assets/js/plugins/datatable/jquery.dataTables.min.js"></script>
        <script src="../assets/js/plugins/datatable/dataTables.bootstrap.js"></script>
        <script src="../assets/js/plugins/jquery-mapael/jquery.mapael.js"></script>
        <script src="../assets/js/plugins/raphael/maps/usa_states.js"></script>
        <script src="../assets/js/king-chart-stat.js"></script>
        <script src="../assets/js/king-table.js"></script>-->
        <script src="../assets/js/king-components.js"></script>


        <script src="../js/validacion.js" type="text/javascript"></script>
        <script src="../js/dtic.js" type="text/javascript"></script>
        <script src="venta.js"></script>

        <script src="../assets/js/plugins/jqgrid/jquery.jqGrid.min.js"></script>
        <script src="../assets/js/plugins/jqgrid/i18n/grid.locale-en.js"></script>
        <script src="../assets/js/plugins/jqgrid/jquery.jqGrid.fluid.js"></script>
        <script src="../assets/js/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>

    </body>
</html>
