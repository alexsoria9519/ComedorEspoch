/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function camposObligatorios() {

    if (valCampoVacio($('#detalle'))) {
        if (valCampoVacio($('#valor'))) {

            if (valCampoVacio($('#fecha'))) {
                return true;
            } else {

                $('#fechamensaje').show();
                $('#fechamensaje').text("El campo fecha es obligatorio");
            }

        } else {
            $('#valormensaje').show();
            $('#valormensaje').text("El campo valor es obligatorio");
        }

    } else {
        $('#detallemensaje').show();
        $('#detallemensaje').text("El campo detalle es obligatorio");
    }

    return false;

}

function longitudInput() {
    if ($('#detalle').val().length < 500) {

        return true;

    } else {
        $('#detallemensaje').show();
        $('#detallemensaje').text("El campo detalle no debe ser mayor a 500 caracteres");
    }
    return false;
}


function validarValor() {

    if (!isNaN($('#valor').val())) {
        if (validarDecimal($('#valor').val())) {
            var valor = Number($('#valor').val());
            if ( valor > 0 && valor < 1000) {
                return true;
            } else {
                $('#valormensaje').show();
                $('#valormensaje').text("Se puede ingresar cantidades de hasta 3 cifras y mayores a 0");
            }
        } else {
            $('#valormensaje').show();
            $('#valormensaje').text("Se puede ingresar cantidades de hasta 2 decimales");
        }

    } else {
        $('#valormensaje').show();
        $('#valormensaje').html("Solo se pueden ingresar n&#250;meros");
    }
    return false;
}


function validarFecha() {


    var isDate = validarCampoFecha($('#fecha').val());

    if (!isDate) {
        $('#fechamensaje').show();
        $('#fechamensaje').text("Se debe ingresar una fecha valida");
        return false;
    }
    return true;
}


function validarFormularioCosto() {
    return (camposObligatorios() && longitudInput() && validarValor() && validarFecha());
}



function mensajeDetalle() {
    if (valCampoVacio($('#detalle'))) {
        $('#detallemensaje').hide();
    }
}

function mensajeValor() {
    if (valCampoVacio($('#valor'))) {
        $('#valormensaje').hide();
    }
}

function mensajeFecha() {
    if (valCampoVacio($('#fecha'))) {
        $('#fechamensaje').hide();
    }
}

function detalleExistente() {
    var existeTipo = false;
    var costo = new Object();
    costo.strdetalle = $('#detalle').val();
    var auxiliar;
    $.ajax({
        url: "costoControlador.jsp",
        type: "GET",
        async: false,
        dataType: "text",
        data: {'accion': 'validarDetalle',
            'datos': JSON.stringify(costo)},
        success: function (resultado) {
            auxiliar = JSON.parse(resultado);
            console.log(auxiliar);
            if (auxiliar.message === "Ya existe un registro con ese nombre") {
                existeTipo = true;
            }
            console.log(existeTipo);
        },
        error: function (error) {
        }
    });
    return existeTipo;
}


function validarDetalleExistente() {
    if (detalleExistente()) {
        $('#detallemensaje').show();
        $('#detallemensaje').text("El detalle ya se encuentra registrado, por favor ingrese otro");
        return false;
    }
    return true;
}
