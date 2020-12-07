<%-- 
    Document   : index
    Created on : May 2, 2019, 6:25:00 PM
    Author     : Alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <title>Comedor ESPOCH</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">


        <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="../assets/css/dataTables.bootstrap.min.css">
        <script src="../assets/js/plugins/font-awesome/fontawesome.min.js"></script>
        <script src="../assets/js/plugins/font-awesome/all.min.js"></script>
        <link rel="shortcut icon" href="../assets/ico/favicon.ico">
        <link rel="stylesheet" href="../assets/css/sweetalert2.css" />



        <link rel="stylesheet" href="../assets/css/main.css">
        <link href="../assets/css/skins/darkblue.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="../assets/css/datepicker.css"/>
        <link href="../assets/css/my-custom-styles.css" rel="stylesheet" type="text/css" />


    </head>

    <body class="sidebar-fixed topnav-fixed dashboard">


        <header>
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
                                            <a class="btn btn-link dropdown-toggle" data-toggle="dropdown">
                                                <img src="../assets/img/user-avatar.png" alt="User Avatar" />
                                                <span class='name'>Usuario logueado</span><span class='caret'></span>


                                            </a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li>
                                                    <a onclick="fncLnkAdmin();" style='cursor: pointer'>
                                                        <i class="fas fa-user"></i>
                                                        <span class="text">Menu 1</span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a onclick="Matar();" style='cursor: pointer'>
                                                        <i class="fas fa-power-off"></i>
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
                        <i class="fas fa-exchange-alt"></i>
                    </div>
                    <div class="sidebar-scroll">
                        <nav class="main-nav">
                            <ul class="main-menu">
                                <li style='cursor: pointer' title="TipoMenu">
                                    <a onclick="" href="../TipoMenu/tipoMenu.jsp">
                                        <i class="fab fa-delicious"></i><span class="text">Tipo de Menu</span>
                                    </a>
                                </li>
                                <li style='cursor: pointer' title="TipoUsuario">
                                    <a onclick="" href="../TipoUsuario/tipoUsuario.jsp">
                                        <i class="fab fa-delicious"></i><span class="text">Tipo de Usuario</span>
                                    </a>
                                </li>
                                <li style='cursor: pointer' title="Venta">
                                    <a onclick="" href="../Venta/venta.jsp">
                                        <i class="fab fa-delicious"></i><span class="text">Venta</span>
                                    </a>
                                </li>
                                <li style='cursor: pointer' title="Precios">
                                    <a onclick="" href="../Costos/costo.jsp">
                                        <i class="fab fa-delicious"></i><span class="text">Precios</span>
                                    </a>
                                </li>
                                <li style='cursor: pointer' title="Menu">
                                    <a onclick="" href="menu.jsp">
                                        <i class="fab fa-delicious"></i><span class="text">Menú</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>

                </div>
                <!-- END LEFT SIDEBAR -->

            </div>
        </header>



        <div id="main-content-wrapper" class="content-wrapper">
            <div class="row">
                <div class="col-lg-12 ">
                    <ul id='divSeguimiento' class="breadcrumb">
                        <li><i class="fa fa-home"></i><a href="../index.jsp" style="padding-left: 10px;">Inicio</a></li>
                    </ul>
                </div>
            </div>

            <div class="row"> 
                <div class="main-header col-md-6">
                    <h2><a href="menu.jsp">Menus</a></h2>
                    <em id="nivel2"> Listado de Menus </em>
                </div>
                <div class="col-md-6">
                    <button class="btn btn-primary" onclick="formulario(event)"> Ingresar Datos  </button>
                </div>
            </div>  

            <div class="row">
                <div id='contenidoDinamico' class="content" style=" margin-bottom:  25  px;">



                    <div id="contenidoInferior" class="main-content">

                        <table id="example" class="table table-sorting table-hover  datatable">

                        </table>
                    </div>
                </div>
            </div>


            <div class="row">

                <h3>Menus Activos</h3>

                <p> En esta sección se muestran los menus activos según la fecha</p>

                <div id="menusactivos" class="col-12">
                    <table id="menusact" class="table table-sorting table-hover  datatable">

                    </table>
                </div>
            </div>
            <br>
            <br>

            <footer class="footer">
                <div> <a href="http://dtic.espoch.edu.ec/" target="_blank" style="color:#FFF;"> <img width="45" height="15" src="../assets/img/dtic.png" > Escuela Superior Politécnica de Chimborazo 2019</a></div>
            </footer>


        </div>






        <!--<script src="../assets/js/jquery/jquery-3.3.1.js"></script>-->
        <script src="../assets/js/jquery/jquery-2.1.0.min.js"></script>
        <script src="../assets/js/bootstrap/bootstrap.js"></script>
        <script src="../assets/js/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="../assets/js/plugins/font-awesome/fontawesome.min.js"></script>
        <script src="../assets/js/plugins/font-awesome/all.min.js"></script>
        <script src="../assets/js/plugins/datatable/jquery.dataTables.min.js"></script>
        <script src="../assets/js/plugins/datatable/dataTables.bootstrap.min.js"></script>
        <script src="../assets/js/plugins/sweet-alert/sweetalert2.all.min.js"></script>
        <script src="../assets/js/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
        <script src="../assets/js/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" charset="UTF-8"></script>
        <script src="../assets/js/king-common.js"></script>
        <script src="../assets/js/king-chart-stat.js"></script>
        <script src="../assets/js/king-table.js"></script>
        <script src="../assets/js/king-components.js"></script>
        <script src="../js/validacion.js"></script>
        <script src="../js/generales.js"></script>
        <script src="validacionMenu.js"></script>
        <script src="menu.js"></script>

    </body>

</html>