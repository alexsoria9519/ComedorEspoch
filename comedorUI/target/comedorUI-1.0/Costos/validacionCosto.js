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
            return true;
        } else{
            $('#valormensaje').show();
            $('#valormensaje').text("Se puede ingresar cantidades de hasta 2 cifras");
        }
            
    } else {
        $('#valormensaje').show();
        $('#valormensaje').html("Solo se pueden ingresar n&#250;meros");
    }
}


function validarFecha(){
    
    
    var isDate = validarCampoFecha($('#fecha').val());
    
    if(!isDate){
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
