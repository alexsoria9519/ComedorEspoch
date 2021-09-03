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
        <link href="assets/css/my-custom-styles.css" rel="stylesheet" type="text/css" />


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


                                        <a target="_blank" class="btn btn-link dropdown-toggle" style="margin-top: 5px;" href="https://drive.google.com/file/d/1ulgd5m8-FWz0YtXbkjug1QCnz4Vbi9EZ/view?usp=sharing">
                                            <i class="fas fa-file-pdf"></i>
                                            <span class='name'>Manual de Usuario</span>
                                        </a>

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
                            <li style='cursor: pointer' title="Reportes">
                                <a onclick="" href="Venta/reportes/reportes.jsp">
                                    <i class="fab fa-delicious"></i><span class="text">Reportes</span>
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



            <!-- Content Row Indicadores-->
            <div class="row dashboard-info">
                <!--Valores de datos de ventas dia-->
                <div class="col-md-3 col-lg-3 col-3 col-sm-6">
                    <div class="panel panel-default border-left-primary shadow h-100 py-2">
                        <div class="panel-heading">
                            <div id="titleTotalVentas" class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Ventas
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row no-gutters align-items-center">

                                <div class="col-9 col-md-9"> 
                                    <h5 id="totalVentasDiario"><strong> Total: </strong>  $0.00</h5>
                                    <h5 id="totalIvaDiario"><strong> IVA: </strong>  $0.00</h5>
                                </div>

                                <div class="col-3 col-md-3"> 
                                    <i class="fas fa-dollar-sign fa-3x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                        <!--<div class="panel-footer"></div>-->
                    </div>
                </div>
                <!--Cantidad de datos de ventas dia-->
                <div class="col-md-3 col-lg-3 col-3 col-sm-6">
                    <div class="panel panel-default border-left-primary shadow h-100 py-2">
                        <div class="panel-heading">
                            <div id="titleCantidadVentas" class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Ventas
                            </div>
                        </div>
                        <div class="panel-body">
                            <div id="cantidadDetalleVenta" class="row no-gutters align-items-center">

                                <!--                                <div class="col-9 col-md-9" >
                                                                    <h5 id="totalVentasDiario1"><strong> Desayuno: </strong>  0</h5>
                                                                    <h5 id="totalIvaDiario1"><strong> Almuerzo </strong>  0</h5>
                                                                </div>
                                
                                
                                                                <div class="col-3 col-md-3"> 
                                                                    <i class="fas fa-calendar fa-3x text-gray-300"></i>
                                                                </div>-->
                            </div>
                        </div>
                        <!--<div class="panel-footer"></div>-->
                    </div>
                </div>

                <div class="col-md-3 col-lg-3 col-3 col-sm-6">
                    <div class="panel panel-default border-left-primary shadow h-100 py-2">
                        <div class="panel-heading">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Earnings (Monthly)
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row no-gutters align-items-center">

                                <div class="col-9 col-md-9"> 
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                                </div>

                                <div class="col-3 col-md-3"> 
                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                        <!--<div class="panel-footer"></div>-->
                    </div>
                </div>

                <div class="col-md-3 col-lg-3 col-3 col-sm-6">
                    <div class="panel panel-default border-left-primary shadow h-100 py-2">
                        <div class="panel-heading">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Earnings (Monthly)
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row no-gutters align-items-center">

                                <div class="col-9 col-md-9"> 
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                                </div>

                                <div class="col-3 col-md-3"> 
                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                        <!--<div class="panel-footer"></div>-->
                    </div>
                </div>
            </div>

            <!-- Content Row Graficos -->

            <div class="row dashboard-graphics">

                <!-- Area Chart -->
                <div class="col-xl-8 col-lg-7">
                    <div class="panel panel-info">
                        <div class="panel-heading">Earnings Overview</div>
                        <div class="panel-body">
                            <div class="chart-area">
                                <canvas id="myAreaChart"></canvas>
                            </div>
                        </div>
                        <div class="panel-footer"> <h6>Datos del comedor ESPOCH  Matriz</h6></div>
                    </div>
                </div>

                <!-- Donut Chart -->
                <div class="col-xl-4 col-lg-5">
                    <div class="panel panel-info">
                        <div class="panel-heading">Donut Chart</div>
                        <div class="panel-body">
                            <div class="chart-area">
                                <canvas id="myPieChart"></canvas>
                            </div>
                        </div>
                        <div class="panel-footer"> <h6>Datos del comedor ESPOCH Matriz </h6></div>
                    </div>
                </div>

                <!-- Donut Chart -->
                <div class="col-xl-12 col-lg-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">Bar Chart</div>
                        <div class="panel-body">
                            <div class="chart-area">
                                <canvas id="myBarChart"></canvas>
                            </div>
                        </div>
                        <div class="panel-footer"> <h6>Datos del comedor ESPOCH Matriz </h6></div>
                    </div>
                </div>
            </div>
            <footer class="footer">
                <div> <a href="http://dtic.espoch.edu.ec/" target="_blank" style="color:#FFF;"> <img width="45" height="15" src="assets/img/dtic.png" > Escuela Superior Politécnica de Chimborazo 2018</a></div>
            </footer>


        </div>






        <script src="assets/js/jquery/jquery-2.1.0.min.js"></script>
        <script src="assets/js/bootstrap/bootstrap.js"></script>
        <script src="assets/js/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <!--<script src="assets/js/king-common.js"></script>-->
        <script src="assets/js/chart.js/Chart.min.js"></script>
        <script src="assets/js/master.js"></script>
        <script src="Dashboard/dashboard.js"></script>

    </body>

</html>