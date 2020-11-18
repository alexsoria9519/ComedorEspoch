/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function camposObligatorios() {
    
    if (valCampoVacio($('#tipo'))) {
        return true;
    } else {
        $('#tipomensaje').show();
        $('#tipomensaje').text("El campo tipo es obligatorio");
    }

    return false;

}

function longitudInput() {
    if ($('#tipo').val().length < 129) {
        return true;
    } else {
        $('#tipomensaje').show();
        $('#tipomensaje').text("El campo tipo no debe ser mayor a 128 caracteres");
    }
    return false;
}

function tipoExistente() {
    var existeTipo = false;
    var tipoUsuario = new Object();
    tipoUsuario.strtipo = $('#tipo').val();
    var auxiliar;
    llamadoCarga();
    $.ajax({
        url: "tipoUsuarioControlador.jsp",
        type: "GET",
        async: false,
        dataType: "text",
        data: {'accion': 'validarTipo',
            'datos': JSON.stringify(tipoUsuario)},
        success: function (resultado) {
            auxiliar = JSON.parse(resultado);
            console.log(auxiliar);
            if (auxiliar.message === "Ya existe un registro con ese nombre") {
                existeTipo = true;
            }
            console.log(existeTipo);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
    return existeTipo;
}


function validarFormularioTipoUsuario() {
    return (camposObligatorios() && longitudInput());
}

function mensajeTipo() {
    if (valCampoVacio($('#tipo'))) {
        $('#tipomensaje').hide();
    }
}

function validarTipoUsuarioExistente() {
    if (tipoExistente()) {
        $('#tipomensaje').show();
        $('#tipomensaje').text("El tipo ya se encuentra registrado, por favor ingrese otro");
        return false;
    }
    return true;
}