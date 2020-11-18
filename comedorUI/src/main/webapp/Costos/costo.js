const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
    },
    buttonsStyling: false,
});



var costo = new Object();

console.log("Ready");
ocultarDivCarga();
listado();


function ingreso(event) {
    event.preventDefault();
    var tipoMenu = new Object();
    var tipoUsuario = new Object();
    costo.strdetalle = $('#detalle').val();
    costo.mnvalor = parseFloat($('#valor').val());
    costo.dtfecha = new Date($('#fecha').val());
    costo.blnestado = $('#estado').val() === 'true' ? true : false;

    tipoMenu.intidtipo = $('#tipoMenu').val();
    tipoUsuario.intidtipo = $('#tipoUsuario').val();
    
    costo.intidtipomenu = tipoMenu;
    costo.intidtipousuario = tipoUsuario;


    console.log(new Date(costo.dtfecha));

    if (validarFormularioCosto()) {
        $.ajax({
            url: "costoControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'ingreso',
                'datos': JSON.stringify(costo)},
            success: function (resultado) {
                console.log('Resultado ', resultado)
                recargarDatatable();
                mensajeCorrecto('success', resultado);
            },
            error: function (error) {

            }
        });
    }

}

function edicion(event, idCosto) {
    event.preventDefault();

    costo.intidcosto = idCosto;
    $.ajax({
        url: "costoControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioedicion',
            'datos': JSON.stringify(costo)},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
            $('.datepicker').datepicker({
                format: 'mm/dd/yyyy',
                //format: 'DD dde MM del yyyy',   
                autoclose: true,
                startDate: '0d',
                endDate: '+30d',
                numberOfMonths: 1,
                closeText: 'Cerrar',
                language: 'es',
                daysOfWeekDisabled: "0,6",
                todayHighlight: true,
                clearBtn: true
            });
            $('#nivel2').text('Editar Precio');
        },
        error: function (error) {
        }
    });
}

function editar(event, idCosto) {
    event.preventDefault();

    estado = new Boolean($('#estado').val());

    costo.intidcosto = idCosto;
    costo.strdetalle = $('#detalle').val();
    costo.mnvalor = parseFloat($('#valor').val());
    costo.dtfecha = new Date($('#fecha').val());
    costo.blnestado = $('#estado').val() === 'true' ? true : false;


    if (validarFormularioCosto()) {
        $.ajax({
            url: "costoControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'edicion',
                'datos': JSON.stringify(costo)},
            success: function (resultado) {
                recargarDatatable();
                mensajeCorrecto('success', resultado);
            },
            error: function (error) {
            }
        });
    }

}



function formulario(event) {
    event.preventDefault();

    $.ajax({
        url: "costoControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formulario'},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
            $('.datepicker').datepicker({
                //format: 'DD d-MM-yyyy',
                format: 'mm/dd/yyyy',
                //format: 'DD dde MM del yyyy',   
                autoclose: true,
                startDate: '0d',
                endDate: '+30d',
                numberOfMonths: 1,
                closeText: 'Cerrar',
                language: 'es',
                daysOfWeekDisabled: "0,6",
                todayHighlight: true,
                clearBtn: true
            });
            $('#nivel2').text('Nuevo Precio');


        },
        error: function (error) {
            console.log(error);
        }
    });

}

function listado() {
    $('#nivel2').text('Listado de Precios');
    llamadoCarga();
    $.ajax({
        url: "costoControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'listado'},
        success: function (resultado) {


            let data = JSON.parse(resultado);
            console.log('Data', data);

            if (data.error) {
                mensajeCorrecto('error', resultado, 2500);
            } else {
                $('#example').DataTable({
                    data: JSON.parse(data.listado),
                    columns: [
                        {title: "Detalle"},
                        {title: "Valor"},
                        {title: "Fecha"},
                        {title: "Tipo de Men&#250"},
                        {title: "Tipo de Usuario"},
                        //{title: "Estado"},
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
    $("#contenidoInferior").html("<div class=\"col-12\"><button class=\"btn btn-primary\" onclick=\"formulario(event)\"> Ingresar Datos  </button></div> <br><table id=\"example\" class=\"display\" style=\"width:100%;\"></table>");
    listado();
}

function eliminar(event, idCosto) {
    event.preventDefault();
    var sweetAlert;

    sweetAlert = swalWithBootstrapButtons.fire({
        title: '&iquest;Estas seguro que de deseas eliminar el costo ?',
        text: "Si elimina el costo el proceso no se puede revertir",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    eliminarCosto(sweetAlert, idCosto);

}

function eliminarCosto(sweetAlert, idCosto) {

    var costo = new Object();
    costo.intidcosto = idCosto;

    sweetAlert.then((result) => {
        if (result.value) {

            $.ajax({
                url: "costoControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {//'accion': 'eliminar',
                    'accion': 'eliminarLogico',
                    'datos': JSON.stringify(costo)},
                success: function (resultado) {
                    console.log(resultado);
                    recargarDatatable();
                    mensajeCorrecto('success', resultado);
                },
                error: function (error) {
                    console.log(error);
                }
            });

        }
    });
}


function validar() {
    $.validate({
        lang: 'es',
        form: '#formulario'
    });
}
