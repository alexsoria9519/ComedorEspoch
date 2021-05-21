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
            console.log('Res Formulario ', resultado);
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