/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function camposObligatorios() {

    if (valCampoVacio($('#caracteristicas'))) {
        if (valCampoVacio($('#fechaInicio'))) {

            if (valCampoVacio($('#fechaFin'))) {
                return true;
            } else {

                $('#fechasmensaje').show();
                $('#fechasmensaje').text("El campo fecha de fin es obligatorio");
            }

        } else {
            $('#fechasmensaje').show();
            $('#fechasmensaje').text("El campo fecha de inicio es obligatorio es obligatorio");
        }

    } else {
        $('#caracteristicasmensaje').show();
        $('#caracteristicasmensaje').text("El campo caracter\u00EDsticas es obligatorio");
    }

    return false;

}

function longitudInput() {
    if ($('#caracteristicas').val().length < 500) {

        return true;

    } else {
        $('#caracteristicasmensaje').show();
        $('#caracteristicasmensaje').text("El campo caracter\u00EDsticas no debe ser mayor a 500 caract\u00E9res");
    }
    return false;
}

function validarFecha() {

    var isDateFechaInicio = validarCampoFecha($('#fechaInicio').val());
    var isDateFechaFin = validarCampoFecha($('#fechaFin').val());

    if (!isDateFechaInicio || !isDateFechaFin) {
        $('#fechasmensaje').show();
        $('#fechasmensaje').text("Se debe ingresar una fecha v\u00E1lida");
        return false;
    }
    return true;
}


//function validarMenuExistente(data) {
//
//    if (JSON.parse(data).message === "Validacion Menu existente"){
//        $('#caracteristicasmensaje').show();
//        //$('#caracteristicasmensaje').text("El campo caracter\u00EDsticas no debe ser mayor a 500 caract\u00E9res");
//        $('#caracteristicasmensaje').text("Ya existe un menu de esas caracter\u00EDsticas y el mismo tipo");
//        return false;   
//    }
//    return true;
//}


function validarFechaMayor() {
    var fechaInicio = new Date($('#fechaInicio').val());
    var fechaFin = new Date($('#fechaFin').val());

    if (fechaInicio > fechaFin) {
        $('#fechasmensaje').show();
        $('#fechasmensaje').text("La fecha de inicio debe ser mayor o igual a la fecha de fin");
        return false;
    }
    return true;
}


function validarFormularioMenu() {
    return (camposObligatorios() && longitudInput() && validarFecha() && validarFechaMayor());
}



function  validarFormularioActivacion(){
    
        if (valCampoVacio($('#fechaInicio'))) {
            if (valCampoVacio($('#fechaFin'))) {
                if(validarFecha() && validarFechaMayor()){
                    return true;
                }
            } else {

                $('#fechasmensaje').show();
                $('#fechasmensaje').text("El campo fecha de fin es obligatorio");
            }
        } else {
            $('#fechasmensaje').show();
            $('#fechasmensaje').text("El campo fecha de inicio es obligatorio es obligatorio");
        }
    
    return false;
}

function mensajeCaracteristicas() {
    if (valCampoVacio($('#caracteristicas'))) {
        $('#caracteristicasmensaje').hide();
    }
}

function mensajeFechaInicio() {
    if (valCampoVacio($('#fechaInicio'))) {
        $('#fechasmensaje').hide();
    }
}

function mensajeFechaFin() {
    if (valCampoVacio($('#fechaFin'))) {
        $('#fechasmensaje').hide();
    }
}
