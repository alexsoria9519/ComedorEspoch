/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

ocultarDivCarga();
cargaCompleta();

$('#nivel2').text('Administración de Ventas');
var venta = new Object();

function formularioVenta(event) {
    event.preventDefault();
    llamadoCarga();
    var cedula = $('#strCedula').val();
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioVenta',
            'datos': cedula},
        success: function (resultado) {
            $('#contenidoDinamico').html(resultado);
            $('#nivel2').text('Nueva Venta');
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}


function  costos(tipoUsuario) {
    var menu = new Object();

    menu.intidmenu = $('#listadosMenus').val();
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'costoVenta',
            'tipoUsuario': tipoUsuario,
            'datos': JSON.stringify(menu)},
        success: function (resultado) {
            $('#costoUsuario').val(resultado);
        },
        error: function (error) {

        }
    });
}

function registrarVenta(event) {
    event.preventDefault();
    llamadoCarga();
    var costoUsuario = new Object();

    venta.dtfecha = new Date();
    venta.intcantidad = parseInt(1);
    venta.blnestado = new Boolean(true);
    costoUsuario.intidcostousuario = parseInt($("#selectCostoUsuario").val());
    venta.intidcostousuario = costoUsuario;

    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'registrarVenta',
            'datos': JSON.stringify(venta)},
        success: function (resultado) {
            var datosIngreso = JSON.parse(resultado);
            if (datosIngreso.success === "ok") {
                $("#modal-header-venta").html(datosIngreso.headerModal);
                $("#modal-body-venta").html(datosIngreso.bodyModal);
//                $("#modal-footer-venta").html(datosIngreso.modalFooter);
                $("#modalVenta").modal();
            }
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function reportePorFecha(event) {
    venta.dtfecha = new Date();
    event.preventDefault();
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'reporteFecha',
            'datos': JSON.stringify(venta)},
        success: function (resultado) {
            //$('#costoUsuario').val(resultado);
        },
        error: function (error) {

        }
    });
}

function getCostoUsuario() {
    event.preventDefault();
    var idCostoUsuario = $("#selectCostoUsuario").val();
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'getCostoUsuario',
            'datos': idCostoUsuario},
        success: function (resultado) {
            var data = JSON.parse(resultado);
            if (data.success === "ok") {
                var costo = JSON.parse(data.Costo);
                $('#costoUsuario').val(costo.intidcosto.mnvalor);
            }
        },
        error: function (error) {

        }
    });
}

function pdfRegistroVenta() {
    event.preventDefault();
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'pdfRegistroVenta'},
        success: function (resultado) {
            downloadBase64File('application/pdf', resultado, "venta");
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function imprimirRegistroVenta() {
    event.preventDefault();
    event.preventDefault();
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'printHTML'},
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






