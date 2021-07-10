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

function llamadoCarga() {
    $('.loader').show();
    $('#contenidoDinamico').hide();
}

function eliminarCaracteresEspeciales(texto) {
    return eliminarSaltoLinea(texto);
}


function eliminarSaltoLinea(texto) {
    return texto.split("\n").join("");
}


function printPage(HTML) {


//    w = window.open(HTML, "_blank");
//    w.document.write(HTML);
//    w.print();
//    w.close();

    var newWin = window.open();
    newWin.document.write(HTML);
    newWin.document.close();
    newWin.focus();
    newWin.print();
    newWin.close();
    

//    var newWin = window.open();
//    newWin.document.write(HTML);
//    //newWin.location.reload();
//    newWin.focus();
//    newWin.print();
//    newWin.close();

//    var a = window.open('', '', 'height=500, width=500');
//    a.document.write('<html>');
//    a.document.write('<body > <h1>Div contents are <br>');
//    a.document.write(divContents);
//    a.document.write('</body></html>');
//    a.document.close();
//    a.print();

}

function downloadBase64File(contentType, base64Data, fileName) {
    const linkSource = `data:${contentType};base64,${base64Data}`;
    const downloadLink = document.createElement("a");
    downloadLink.href = linkSource;
    downloadLink.download = fileName;
    downloadLink.click();
}
