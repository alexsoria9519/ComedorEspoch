/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$('#nivel2').text('Administración de Ventas');
var venta = new Object();

function formularioVenta(event) {
    event.preventDefault();
    persona = new Object();
    persona.strcedula = $('#strCedula').val();
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioVenta',
            'datos': JSON.stringify(persona)},
        success: function (resultado) {
            console.log(resultado);
            $('#contenidoDinamico').html(resultado);
            $('#nivel2').text('Nueva Venta');

        },
        error: function (error) {

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
            console.log(resultado);
            $('#costoUsuario').val(resultado);
        },
        error: function (error) {

        }
    });
}

function registrarVenta(event, cedula, tipoUsuario, existeUsuario) {
    event.preventDefault();
    var menu = new Object();
    var costoUsuario = new Object();

    venta.dtfecha = new Date();
    venta.intcantidad = parseInt(1);
    venta.blnestado = new Boolean(true);
    menu.intidmenu = $('#listadosMenus').val();

    venta.intidmenu = menu;
    venta.intidcostousuario = costoUsuario;

    venta.cedula = cedula;
    venta.tipousuario = tipoUsuario;
    venta.existeusuario = existeUsuario;

    console.log(venta.existeusuario);

    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'registrarVenta',
            'datos': JSON.stringify(venta)},
        success: function (resultado) {
            console.log(resultado);
            //$('#costoUsuario').val(resultado);
        },
        error: function (error) {

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
            console.log(resultado);
            //$('#costoUsuario').val(resultado);
        },
        error: function (error) {

        }
    });
}
