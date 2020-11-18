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
    var tipoMenu = new Object();
    tipoMenu.strtipo = $('#tipo').val();
    var auxiliar;
    $.ajax({
        url: "tipoMenuControlador.jsp",
        type: "GET",
        async: false,
        dataType: "text",
        data: {'accion': 'validarTipo',
            'datos': JSON.stringify(tipoMenu)},
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


function validarFormularioTipoMenu() {

    return (camposObligatorios() && longitudInput());
}

function validarTipoMenuExistente() {
    if(tipoExistente()){
        $('#tipomensaje').show();
        $('#tipomensaje').text("El tipo ya se encuentra registrado, por favor ingrese otro");
        return false;
    }
    return true;
}


function mensajeTipo() {
    if (valCampoVacio($('#tipo'))) {
        $('#tipomensaje').hide();
    }
}