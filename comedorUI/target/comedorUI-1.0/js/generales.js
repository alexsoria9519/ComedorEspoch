/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function ocultarDivCarga() {
    $('.loader').hide();
    $('#contenidoDinamico').hide();
}

function cargaCompleta() {
    $('.loader').hide();
    $('#contenidoDinamico').show();
}

function llamadoCarga(){
    $('.loader').show();
    $('#contenidoDinamico').hide();
}

function eliminarCaracteresEspeciales(texto){
    return eliminarSaltoLinea(texto);
}


function eliminarSaltoLinea(texto){
    return texto.split("\n").join("");
}

