<%-- 
    Document   : index
    Created on : May 2, 2019, 6:25:00 PM
    Author     : Alex
--%>
<!DOCTYPE html>
<html>

    <head>

        <title>Comedor ESPOCH</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">


        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
              integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

        <link rel="stylesheet" href="assets/css/main.css">
        <link href="assets/css/skins/darkblue.css" rel="stylesheet" type="text/css" />


    </head>

    <body class="sidebar-fixed topnav-fixed dashboard">

        <div id="wrapper" class="wrapper">
            <!-- TOP BAR -->
            <div class="top-bar navbar-fixed-top">
                <div class="container">
                    <div class="clearfix">
                        <!-- logo -->
                        <div class="pull-left left logo">
                            <h4 style="color:#FFF">Comedor ESPOCH</h4>
                            <!--<a href="index.jsp"><img src="assets/img/logoDTIC.png"  /></a>-->
                        </div>

                        <!-- end logo -->
                        <div class="pull-right right">
                            <!-- top-bar-right -->
                            <div class="top-bar-right">
                                <!-- logged user and the menu -->
                                <div class="logged-user">
                                    <div id="btnUser" class="btn-group">
                                        <a class="btn btn-link dropdown-toggle" data-toggle="dropdown">
                                            <img src="assets/img/user-avatar.png" alt="User Avatar" />
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
                                <a onclick="" href="TipoMenu/tipoMenu.jsp">
                                    <i class="fab fa-delicious"></i><span class="text">Tipo de Menu</span>
                                </a>
                            </li>
                            <li style='cursor: pointer' title="TipoUsuario">
                                <a onclick="" href="TipoUsuario/tipoUsuario.jsp">
                                    <i class="fab fa-delicious"></i><span class="text">Tipo de Usuario</span>
                                </a>
                            </li>
                            <li style='cursor: pointer' title="Venta">
                                <a onclick="" href="Venta/venta.jsp">
                                    <i class="fab fa-delicious"></i><span class="text">Venta</span>
                                </a>
                            </li>
                            <li style='cursor: pointer' title="Precios">
                                <a onclick="" href="Costos/costo.jsp">
                                    <i class="fab fa-delicious"></i><span class="text">Precios</span>
                                </a>
                            </li>
                            <li style='cursor: pointer' title="Menu">
                                <a onclick="" href="Menu/menu.jsp">
                                    <i class="fab fa-delicious"></i><span class="text">Menú</span>
                                </a>
                            </li>
                            <li style='cursor: pointer' title="Usuario">
                                <a onclick="" href="./Usuarios/usuario.jsp">
                                    <i class="fab fa-delicious"></i><span class="text">Usuarios</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>

            </div>
            <!-- END LEFT SIDEBAR -->

        </div>




        <div id="main-content-wrapper" class="content-wrapper">
            <div class="row">
                <div class="col-lg-12 ">
                    <ul id='divSeguimiento' class="breadcrumb">
                        <li><i class="fa fa-home"></i><a href="index.jsp">Inicio</a></li>
                    </ul>
                </div>
            </div>

            <div class="main-header">
                <h2>Inicio</h2>
                <em>Panel de administración</em>
            </div>

            <div id='contenidoDinamico' class="content">

            </div>



            <footer class="footer">
                <div> <a href="http://dtic.espoch.edu.ec/" target="_blank" style="color:#FFF;"> <img width="45" height="15" src="assets/img/dtic.png" > Escuela Superior Politécnica de Chimborazo 2018</a></div>
            </footer>


        </div>






        <script src="assets/js/jquery/jquery-3.3.1.js"></script>
        <script src="assets/js/plugins/datatable/jquery.dataTables.min.js"></script>
        <script src="assets/js/plugins/datatable/dataTables.bootstrap.min.js"></script>
        <script src="assets/js/king-common.js"></script>
        <script src="assets/js/master.js"></script>
    </body>

</html>