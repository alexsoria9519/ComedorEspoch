/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
    },
    buttonsStyling: false,
});

ocultarDivCarga();
cargaCompleta();
//
//$('#nivel2').text('');
//
//
//var venta = new Object();


function registrarUsuario() {

    var data = new Object();

    data.cedula = $("#cedula").val();
    data.tipoUsuario = $("#tiposUsuario").val();

    console.log("Data ", data);


    llamadoCarga();
    $.ajax({
        url: "usuarioControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'registrarUsuario',
            'datos': JSON.stringify(data)},
        success: function (resultado) {
            var data = JSON.parse(resultado)
            console.log(data);
            if (data.success == "ok") {
                mensajeCorrecto('success', data.data);
            } else if (data.success == "validacion") {
                mensajeCorrecto('warning', data.data);
            } else if (data.success = "error") {
                mensajeCorrecto('error', data.data);
            }
            $('#nivel2').text('Registrar Usuario');
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });


}

function mensajeCorrecto(tipo, mensaje, tiempo) {
    Swal.fire({
        type: tipo,
        title: mensaje,
        showConfirmButton: false,
        timer: tiempo | 1500
    })
}