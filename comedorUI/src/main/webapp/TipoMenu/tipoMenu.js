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
console.log("Ready");
ocultarDivCarga();
listado();


function ingreso(event) {
    event.preventDefault();

    var tipoMenu = new Object();
    tipoMenu.strtipo = $('#tipo').val();
    
    tipoMenu.strtipo = eliminarCaracteresEspeciales(tipoMenu.strtipo);
    //tipoExistente();

    if (validarFormularioTipoMenu()) {

        if (validarTipoMenuExistente()) {
            llamadoCarga();
            $.ajax({
                url: "tipoMenuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'ingreso',
                    'datos': JSON.stringify(tipoMenu)},
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

function edicion(event, idTipoMenu) {
    event.preventDefault();
    var tipoMenu = new Object();
    tipoMenu.intidtipo = idTipoMenu;
    llamadoCarga();
    $.ajax({
        url: "tipoMenuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioedicion',
            'datos': JSON.stringify(tipoMenu)},
        //data: {'accionPermiso': 'nuevo'},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
            $('#nivel2').text('Editar Tipo de Menú');
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });


    //alert(JSON.stringify(tipoMenu));

}

function editar(event, idTipoMenu) {
    event.preventDefault();

    var tipoMenu = new Object();


    tipoMenu.intidtipo = idTipoMenu;
    tipoMenu.strtipo = $('#tipo').val();
    
    tipoMenu.strtipo = eliminarCaracteresEspeciales(tipoMenu.strtipo);
    
    
    if (validarFormularioTipoMenu()) {
        if (validarTipoMenuExistente()) {
            llamadoCarga();
            $.ajax({
                url: "tipoMenuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'edicion',
                    'datos': JSON.stringify(tipoMenu)},
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



function formulario(event) {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "tipoMenuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formulario'},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
            $('#nivel2').text('Nuevo Tipo de Menú');
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
    $('#nivel2').text('Listado de Tipos de Menú');
    llamadoCarga();
    $.ajax({
        url: "tipoMenuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'listado'},
        success: function (resultado) {
            let data = JSON.parse(resultado);
            console.log('Data',  data);

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
                $('#numeroRegistros').text(data.cantidad + ' Registros');
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

function mensajeCorrecto(tipo, data) {
    Swal.fire({
        type: tipo,
        title: JSON.parse(data).message,
        showConfirmButton: false,
        timer: 1500
    })
}

function recargarDatatable() {
    $("#contenidoInferior").html("<br><table id=\"example\" class=\"display\" style=\"width:100%;\"></table>");
    listado();
}

function eliminar(event, idTipoMenu) {
    event.preventDefault();
    var sweetAlert;

    sweetAlert = swalWithBootstrapButtons.fire({
        title: '&iquest;Estas seguro que de deseas eliminar el tipo de men&#250; ?',
        text: "Si elimina el tipo de menú el proceso no se puede revertir",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    eliminarTipoMenu(sweetAlert, idTipoMenu);

}

function eliminarTipoMenu(sweetAlert, idTipoMenu) {

    var tipoMenu = new Object();
    tipoMenu.intidtipo = idTipoMenu;

    sweetAlert.then((result) => {
        if (result.value) {
            llamadoCarga();
            //Promesa a la espera del resultado de la eliminacion
            var promesaEliminar;
            promesaEliminar = new Promise((resolve, reject) => {
                $.ajax({
                    url: "tipoMenuControlador.jsp",
                    type: "GET",
                    dataType: "text",
                    data: {'accion': 'eliminar',
                        'datos': JSON.stringify(tipoMenu)},
                    success: function (resultado) {
                        console.log(resultado);
                        recargarDatatable();
                        resolve(resultado);
                        //mensajeCorrecto('success', resultado);

                    },
                    complete: function () {
                        cargaCompleta();
                    },
                    error: function (error) {
                        cargaCompleta();
                        console.log(error);
                        reject({error: error});
                    }
                });
            });

            promesaEliminar.then((data) => {
                var datosEliminacion = JSON.parse(data);
                if (datosEliminacion.success) { // Si el proceso es correcto
                    if (datosEliminacion.success === "menusAsociados") { // En caso de encontrador datos asociados
                        elminacionForzada(tipoMenu, datosEliminacion);
                    } else { // Se realizo la eliminacion
                        mensajeCorrecto('success', JSON.stringify(datosEliminacion));
                    }
                } else { // Error controlado de la eliminacion
                    mensajeCorrecto('error', JSON.stringify({message: 'Error en la eliminación'}));
                }
            }).catch((err) => { // Fallo de la Eliminacion
                console.log(err);
                mensajeCorrecto('error', JSON.stringify({message: 'Error en la eliminación'}));
            });
        }
    });
}



function elminacionForzada(tipoMenu, datosEliminacion) {
    let sweetAlert;
    sweetAlert = swalWithBootstrapButtons.fire({
        //title: '&iquest;Estas seguro que de deseas eliminar el tipo de men&#250; ?',
        title: datosEliminacion.message,
        text: "Si elimina el tipo de menú el proceso no se puede revertir",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    sweetAlert.then(result => {
        if (result.value) {
            $.ajax({
                url: "tipoMenuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'eliminarForzoso',
                    'datos': JSON.stringify(tipoMenu)},
                success: function (resultado) {
                    console.log(resultado);
                    recargarDatatable();
                    mensajeCorrecto('success', resultado);

                },
                error: function (error) {
                    console.log(error);
                    mensajeCorrecto('error', JSON.stringify({message: 'Error en la eliminación'}));
                }
            });
        }
    });


}



