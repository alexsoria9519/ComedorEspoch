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
listado();


function ingreso(event) {

    event.preventDefault();

    var tipoUsuario = new Object();

    tipoUsuario.strtipo = $('#tipo').val();
    tipoUsuario.strtipo = eliminarCaracteresEspeciales(tipoUsuario.strtipo);
    if (validarFormularioTipoUsuario()) {
        if (validarTipoUsuarioExistente()) {
            llamadoCarga();
            $.ajax({
                url: "tipoUsuarioControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'ingreso',
                    'datos': JSON.stringify(tipoUsuario)},
                //data: {'accionPermiso': 'nuevo'},
                success: function (resultado) {
                    recargarDatatable();
                    mensajeCorrecto('success', resultado);
                },
                complete: function () {
                    cargaCompleta();
                },
                error: function (error) {
                    cargaCompleta();
                }
            });
        }
    }
}

function edicion(event, idTipoUsuario) {
    event.preventDefault();
    var tipoUsuario = new Object();
    tipoUsuario.intidtipo = idTipoUsuario;
    llamadoCarga();
    $.ajax({
        url: "tipoUsuarioControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioedicion',
            'datos': JSON.stringify(tipoUsuario)},
        //data: {'accionPermiso': 'nuevo'},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
            $('#nivel2').text('Editar tipo de usuario');
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}

function editar(event, idTipoUsuario) {
    event.preventDefault();

    var tipoUsuario = new Object();
    
    llamadoCarga();
    tipoUsuario.intidtipo = idTipoUsuario;
    
    var tipo = $('#tipo').val();
       
    tipoUsuario.strtipo = eliminarCaracteresEspeciales(tipo);
    
    if (validarFormularioTipoUsuario()) {
        $.ajax({
            url: "tipoUsuarioControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'edicion',
                'datos': JSON.stringify(tipoUsuario)},
            success: function (resultado) {
                var result = JSON.parse(resultado);
                recargarDatatable();
                if (result.success === 'validacion') {
                    mensajeCorrecto('warning', resultado);
                } else {
                    mensajeCorrecto('success', resultado);
                }
            },
            complete: function () {
                cargaCompleta();
            },
            error: function (error) {
                cargaCompleta();
            }
        });
    } else {
        cargaCompleta();
    }

}



function formulario(event) {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "tipoUsuarioControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formulario'},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
            $('#nivel2').text('Nuevo Tipo de Usuario');
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });

}

function listado() {
    $('#nivel2').text('Listado de Tipos de Usuarios');
    llamadoCarga();
    $.ajax({
        url: "tipoUsuarioControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'listado'},
        success: function (resultado) {
            let data = JSON.parse(resultado);
            if (data.error) {
                mensajeCorrecto('error', resultado, 2500);
            } else {
                $('#example').DataTable({
                    data: JSON.parse(data.listado),
                    columns: [
                        {title: "Tipo"},
                        {title: "Opciones", "orderable": false}
                    ],
                    language: {
                        "sProcessing": "Procesando...",
                        "sLengthMenu": "Mostrar _MENU_ registros",
                        "sZeroRecords": "No se encontraron resultados",
                        "sEmptyTable": "Ningún dato disponible en esta tabla",
                        "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                        "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                        "sInfoPostFix": "",
                        "sSearch": "Buscar:",
                        "sUrl": "",
                        "sInfoThousands": ",",
                        "sLoadingRecords": "Cargando...",
                        "oPaginate": {
                            "sFirst": "Primero",
                            "sLast": "Último",
                            "sNext": "Siguiente",
                            "sPrevious": "Anterior"
                        },
                        "oAria": {
                            "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                        }
                    }
                });
                $('#numeroRegistros').text(data.cantidadUsuarios + ' Registros');
            }
        },
        error: function (error) {
            mensajeCorrecto('error', error);
            cargaCompleta();
        },
        complete: function () {
            cargaCompleta();
        }
    });
}

function mensajeCorrecto(tipo, data, tiempo) {
    Swal.fire({
        type: tipo,
        title: JSON.parse(data).message,
        showConfirmButton: false,
        timer: tiempo | 1500
    })
}

function recargarDatatable() {
    $("#contenidoInferior").html("<br><table id=\"example\" class=\"display\" style=\"width:100%;\"></table>");
    listado();
}

function eliminar(event, idTipoUsuario) {
    event.preventDefault();
    var sweetAlert;

    sweetAlert = swalWithBootstrapButtons.fire({
        title: '&iquest;Estas seguro que de deseas eliminar el tipo de usuario ?',
        text: "Si elimina el tipo de usuario el proceso no se puede revertir",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    eliminarTipoMenu(sweetAlert, idTipoUsuario);
    //eliminarTipoMenu(sweetAlert, 100);

}

function eliminarTipoMenu(sweetAlert, idTipoUsuario) {

    var tipoUsuario = new Object();
    tipoUsuario.intidtipo = idTipoUsuario;

    sweetAlert.then((result) => {
        if (result.value) {
            llamadoCarga();
            $.ajax({
                url: "tipoUsuarioControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'eliminar',
                    'datos': JSON.stringify(tipoUsuario)},
                success: function (resultado) {
                    var data = JSON.parse(resultado);
                    if (data.error) {
                        mensajeCorrecto('error', resultado, 2500);
                    } else {
                        recargarDatatable();
                        if (data.success === 'ok') {
                            mensajeCorrecto('success', resultado);
                        } else if (data.success === 'validacion') {
                            mensajeCorrecto('warning', resultado);
                        } else if (data.success === 'error') {
                            mensajeCorrecto('error', resultado, 2500);
                        } else if (data.success === 'info') {
                            confirmEliminarForzoso('info', idTipoUsuario, data.message);
                        }
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
    });
}

function confirmEliminarForzoso(tipo, idTipoUsuario, mensaje) {
    var tipoUsuario1 = new Object();
    tipoUsuario1.intidtipo = idTipoUsuario;
    sweetAlert = swalWithBootstrapButtons.fire({
        //title: '&iquest;Estas seguro que de deseas eliminar el tipo de usuario ?',
        title: 'Se encontraron registros asociados al tipo de usuario',
        text: mensaje,
        type: tipo,
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    sweetAlert.then((result) => {
        if (result.value) {
            llamadoCarga();
            $.ajax({
                url: "tipoUsuarioControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'eliminarForzoso',
                    'datos': JSON.stringify(tipoUsuario1)},
                success: function (resultado) {
                    var data = JSON.parse(resultado);
                    recargarDatatable();
                    if (data.success === 'ok') {
                        mensajeCorrecto('success', resultado);
                    } else if (data.success === 'validacion') {
                        mensajeCorrecto('warning', resultado);
                    } else if (data.success === 'error') {
                        mensajeCorrecto('error', resultado);
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
    });
}
