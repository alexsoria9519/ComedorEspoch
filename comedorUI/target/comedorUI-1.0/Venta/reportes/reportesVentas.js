/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ventasDiarias() {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'formularioReporteVentasDia'},
        success: function (resultado) {
            $("#content-modal-reporte").html(resultado);
            $('#modalReportesVentas').modal();
            $('.datepicker').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                numberOfMonths: 12,
                closeText: 'Cerrar',
                language: 'es',
                daysOfWeekDisabled: "0,6",
                todayHighlight: true,
                clearBtn: true
            });
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function reporteVentasDiarias() {
    event.preventDefault();
    llamadoCarga();
    $('#modalReportesVentas').modal('toggle');
    var fecha = $("#fechaVenta").val();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteVentasDia',
            'datos': fecha},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"venta.jsp\">Ventas</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Ventas del día </em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('ventasDiario')\"><i class=\"fas fa-print\"></i></button> "
                    + "  <button type='button' class='btn  btn-danger' onclick=\"pdfReporteVentas('ventasDiario')\"><i class=\"fas fa-file-pdf\"></i></button>"
                    + "</div>";

            $("#ruta").html(HTML);
            $("#contenidoDinamico").addClass("reporte");
            $("#contenidoDinamico").html(resultado);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function ventasIntervaloFechas() {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'formularioReporteIntervaloFechas'},
        success: function (resultado) {
            $("#content-modal-reporte").html(resultado);
            $('#modalReportesVentas').modal();
            $('.datepicker').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                numberOfMonths: 12,
                closeText: 'Cerrar',
                language: 'es',
                daysOfWeekDisabled: "0,6",
                todayHighlight: true,
                clearBtn: true
            });
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function reporteVentasIntervalo() {
    event.preventDefault();
    llamadoCarga();
    $('#modalReportesVentas').modal('toggle');
    var dataFechas = new Object();
    dataFechas.fechaInicio = $("#fechaInicio").val();
    dataFechas.fechaFin = $("#fechaFin").val();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteIntervaloFechas',
            'datos': JSON.stringify(dataFechas)},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"venta.jsp\">Ventas</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Ventas rango de fechas </em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('intervaloFechas')\"><i class=\"fas fa-print\"></i></button> "
                    + "  <button type='button' class='btn  btn-danger' onclick=\"pdfReporteVentas('intervaloFechas')\"><i class=\"fas fa-file-pdf\"></i></button>"
                    + "</div>";

            $("#ruta").html(HTML);
            $("#contenidoDinamico").addClass("reporte");
            $("#contenidoDinamico").html(resultado);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function ventasTipoMenu() {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'formularioReporteTipoMenu'},
        success: function (resultado) {
            $("#content-modal-reporte").html(resultado);
            $('#modalReportesVentas').modal();
            $('.datepicker').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                numberOfMonths: 12,
                closeText: 'Cerrar',
                language: 'es',
                daysOfWeekDisabled: "0,6",
                todayHighlight: true,
                clearBtn: true
            });
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function reporteVentasIntervaloMenu() {
    event.preventDefault();
    llamadoCarga();
    $('#modalReportesVentas').modal('toggle');
    var dataFechas = new Object();
    dataFechas.fechaInicio = $("#fechaInicio").val();
    dataFechas.fechaFin = $("#fechaFin").val();
    dataFechas.idTipo = $("#tipoMenu").val();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteIntervaloFechasMenu',
            'datos': JSON.stringify(dataFechas)},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"venta.jsp\">Ventas</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Ventas rango de fechas </em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('reporteIntervaloFechasMenu')\"><i class=\"fas fa-print\"></i></button> "
                    + "  <button type='button' class='btn  btn-danger' onclick=\"pdfReporteVentas('reporteIntervaloFechasMenu')\"><i class=\"fas fa-file-pdf\"></i></button>"
                    + "</div>";

            $("#ruta").html(HTML);
            $("#contenidoDinamico").addClass("reporte");
            $("#contenidoDinamico").html(resultado);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}


function ventasTipoUsuario() {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'formularioReporteTipoUsuario'},
        success: function (resultado) {
            $("#content-modal-reporte").html(resultado);
            $('#modalReportesVentas').modal();
            $('.datepicker').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                numberOfMonths: 12,
                closeText: 'Cerrar',
                language: 'es',
                daysOfWeekDisabled: "0,6",
                todayHighlight: true,
                clearBtn: true
            });
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}


function reporteVentasIntervaloFechasUsuario() {
    event.preventDefault();
    llamadoCarga();
    $('#modalReportesVentas').modal('toggle');
    var dataFechas = new Object();
    dataFechas.fechaInicio = $("#fechaInicio").val();
    dataFechas.fechaFin = $("#fechaFin").val();
    dataFechas.idTipo = $("#tipoUsuario").val();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteIntervaloFechasUsuario',
            'datos': JSON.stringify(dataFechas)},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"venta.jsp\">Ventas</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Ventas rango de fechas </em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('reporteIntervaloFechasMenu')\"><i class=\"fas fa-print\"></i></button> "
                    + "  <button type='button' class='btn  btn-danger' onclick=\"pdfReporteVentas('reporteIntervaloFechasMenu')\"><i class=\"fas fa-file-pdf\"></i></button>"
                    + "</div>";

            $("#ruta").html(HTML);
            $("#contenidoDinamico").addClass("reporte");
            $("#contenidoDinamico").html(resultado);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function pdfReporteVentas(identificador) {
    event.preventDefault();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'pdfReporteVentas',
            'datos': identificador
        },
        success: function (resultado) {
            downloadBase64File('application/pdf', resultado, "reporte-" + identificador);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function imprimirReportesVentas(identificador) {
    event.preventDefault();
    $.ajax({
        url: "reportes/reportesVentasControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'imprimirReporteVentas',
            'datos': identificador
        },
        success: function (resultado) {
            printPage(resultado);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}