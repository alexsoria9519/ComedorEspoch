/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

ocultarDivCarga();
cargaCompleta();
function ventasDiarias() {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "reportesVentasControlador.jsp",
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
        url: "reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteVentasDia',
            'datos': fecha},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"reportes.jsp\">Reportes</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Reporte por día </em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
//                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('ventasDiario')\"><i class=\"fas fa-print\"></i></button> "
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
        url: "reportesVentasControlador.jsp",
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
        url: "reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteIntervaloFechas',
            'datos': JSON.stringify(dataFechas)},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"reportes.jsp\">Reportes</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Reportes en un rango de fechas </em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
//                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('intervaloFechas')\"><i class=\"fas fa-print\"></i></button> "
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
        url: "reportesVentasControlador.jsp",
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
        url: "reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteIntervaloFechasMenu',
            'datos': JSON.stringify(dataFechas)},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"reportes.jsp\">Reportes</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Ventas rango de fechas de un tipo de menú</em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
//                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('reporteIntervaloFechasMenu')\"><i class=\"fas fa-print\"></i></button> "
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
        url: "reportesVentasControlador.jsp",
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
        url: "reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'reporteIntervaloFechasUsuario',
            'datos': JSON.stringify(dataFechas)},
        success: function (resultado) {
            var HTML = "<div class=\"main-header col-md-6\" >"
                    + "<h2><a href=\"reportes.jsp\">Reportes</a></h2>"
                    + "<em id=\"nivel2\"> Reportes </em>"
                    + "<em id=\"nivel3\">  Reportes rango de fechas de un tipo de usuario</em>"
                    + "</div>"
                    + "<div class=\"col-md-6 reporteButtons\">"
//                    + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('reporteIntervaloFechasUsuario')\"><i class=\"fas fa-print\"></i></button> "
                    + "  <button type='button' class='btn  btn-danger' onclick=\"pdfReporteVentas('reporteIntervaloFechasUsuario')\"><i class=\"fas fa-file-pdf\"></i></button>"
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
        url: "reportesVentasControlador.jsp",
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
        url: "reportesVentasControlador.jsp",
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

function ventasUsuarioFechas() {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': 'modalVentasUsuariosFechas'},
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

function reporteVentasUsuarioIntervaloFechas() {
    event.preventDefault();
    var dataFechas = new Object();
    dataFechas.fechaInicio = $("#fechaInicio").val();
    dataFechas.fechaFin = $("#fechaFin").val();
    dataFechas.cedula = $("#cedula").val();

    if (validarFechaMayor() && validarFechaMayor()) {
        $('#modalReportesVentas').modal('toggle');
        llamadoCarga();
        $.ajax({
            url: "reportesVentasControlador.jsp",
            type: "GET",
            data: {'accion': 'reporteDatausuarioFechas',
                'datos': JSON.stringify(dataFechas)},
            success: function (resultado) {
                var HTML = "<div class=\"main-header col-md-6\" >"
                        + "<h2><a href=\"reportes.jsp\">Reportes</a></h2>"
                        + "<em id=\"nivel2\"> Reportes </em>"
                        + "<em id=\"nivel3\">  Reporte de un usuario </em>"
                        + "</div>"
                        + "<div class=\"col-md-6 reporteButtons\">"
//                        + "  <button type='button' class='btn  btn-success' onclick=\"imprimirReportesVentas('reporteDatausuarioFechas')\"><i class=\"fas fa-print\"></i></button> "
                        + "  <button type='button' class='btn  btn-danger' onclick=\"pdfReporteVentas('reporteDatausuarioFechas')\"><i class=\"fas fa-file-pdf\"></i></button>"
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
    } else {
        cargaCompleta();
    }
}

function ventasFacultadCarrera() {

    event.preventDefault();
    llamadoCarga();
    var dataFechas = new Object();
    dataFechas.fechaInicio = $("#fechaInicio").val();
    dataFechas.fechaFin = $("#fechaFin").val();
    dataFechas.cedula = $("#cedula").val();

    var promesaFacultades = loadFacultades();


    promesaFacultades.then((data) => {

        $("#content-modal-reporte").html(data);
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

        loadCarreras();


    }).catch((err) => {
        console.log('Error ', err);
    });


}



function loadFacultades() {
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "reportesVentasControlador.jsp",
            type: "GET",
            data: {'accion': 'formularioReporteFacultadCarrera'},
            success: function (resultado) {
                resolve(resultado);
            },
            complete: function () {
                cargaCompleta();
            },
            error: function (error) {
                cargaCompleta();
                reject(error);
            }
        });
    });
}

function loadCarreras() {


    event.preventDefault();

    var facultad = $("#facultadListado").val();

    if (facultad) {
//            llamadoCarga();
        var dataFacultad = new Object();
        dataFacultad.facultad = facultad;
        $('#facultadListado').prop('disabled', 'disabled');
        $.ajax({
            url: "reportesVentasControlador.jsp",
            type: "GET",
            data: {'accion': 'selectCarreras',
                'datos': JSON.stringify(dataFacultad)},
            success: function (resultado) {
                var checkbox = $('#chkCarrera')[0].checked;
                $("#loadSelectCarreras").html(resultado);
                console.log('loadCarreras ', checkbox);
                if (checkbox) {
                    $("#loadSelectCarreras").show();
                } else {
                    $("#loadSelectCarreras").hide();
                }
                console.log("Resultado carreras ", resultado);
                $('#facultadListado').prop('disabled', false);
            },
            complete: function () {
//                    cargaCompleta();
            },
            error: function (error) {
//                    cargaCompleta();
            }
        });
    }




    console.log("Cambio", facultad);
}


function onCarreras() {
    var checkbox = $('#chkCarrera')[0].checked;

    if (checkbox) {
        $("#loadSelectCarreras").show();
    } else {
        $("#loadSelectCarreras").hide();
    }

}




function reporteUsuarioFacultadesCarreras() {
    var checkbox = $('#chkCarrera')[0].checked;

    event.preventDefault();

    var dataReporte = new Object();
    dataReporte.facultad = $("#facultadListado").val();
    dataReporte.descripcionFacultad = $("#facultadListado option:selected").text();
    dataReporte.fechaInicio = $("#fechaInicio").val();
    dataReporte.fechaFin = $("#fechaFin").val();


    console.log('Select Carreras ', $("#selectCarreras"));

    if ($("#selectCarreras").val() && checkbox) {
        dataReporte.carrera = $("#selectCarreras").val();
        dataReporte.descripcionCarrera = $("#selectCarreras option:selected").text();
        sessionStorage.setItem("descripcionCarrera", dataReporte.descripcionCarrera);
    } else {
        dataReporte.carrera = 'Ninguna';
        dataReporte.descripcionCarrera = $("#selectCarreras option:selected").text();
        sessionStorage.setItem("descripcionCarrera", "Todas");
    }


    if (validarIntervaloFecha()) {
        console.log('Data reporteUsuarioFacultadesCarreras ', dataReporte);
    }

    sessionStorage.setItem("requestReporteUsuarios", JSON.stringify(dataReporte));
    sessionStorage.setItem("descripcionFacultad", dataReporte.descripcionFacultad);

    window.location.href = 'reporteUsuarios.jsp';


}



