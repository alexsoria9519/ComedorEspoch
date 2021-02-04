const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
    },
    buttonsStyling: false,
});

var menu = new Object();
var planificacionmenuCollection = new Object();


ocultarDivCarga();
cargarListados();


function ingreso(event) {
    event.preventDefault();

    var tipoMenu = new Object();
    var checkPlanificacion = $('#planificacionCheck').prop('checked');
    menu.strcaracteristicas = $('#caracteristicas').val();
    tipoMenu.intidtipo = parseInt($('#tipoMenu').val());
    menu.intidtipomenu = tipoMenu;

    var fechaInicioMenu = new Date($('#fechaInicio').val());
    var fechaFinMenu = new Date($('#fechaFin').val());

    if (checkPlanificacion) {
        if (validarFormularioMenu()) {
            menu.planificacionmenuCollection = [
                {
                    "dtfechainicio": fechaInicioMenu,
                    "dtfechafin": fechaFinMenu
                }
            ];
            llamadoCarga();
            ingresarMenu(menu)
        }
    } else {
        if (validarFormularioMenuSinPlanificacion()) {
            llamadoCarga();
            ingresarMenu(menu)
        }
    }
}

function ingresarMenu(menu) {
    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'ingreso',
            'datos': JSON.stringify(menu)},
//                'datosPlanificacion': JSON.stringify(fechasMenu)},
        success: function (resultado) {
            var data = JSON.parse(resultado);

            if (data.success === 'ok') {
//                    if (validarMenuExistente(resultado)) {
                mensajeCorrecto('success', resultado);
//                    }
            } else if (data.success === 'validacion') {
                mensajeCorrecto('warning', resultado);
            } else {
                mensajeCorrecto('error', resultado);
            }

            recargarDatatable();
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


function edicion(event, idMenu) {
    event.preventDefault();
    llamadoCarga();
    menu.intidmenu = idMenu;
    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioedicion',
            'datos': JSON.stringify(menu)},
        success: function (resultado) {

            var dataResultado = JSON.parse(resultado);

            console.log('dataResultado ', dataResultado);

            if (dataResultado.success === 'ok') {
                $("#contenidoInferior").html(dataResultado.formulario);
            } else if (dataResultado.success === 'error') {
                console.log('Resultado Error ', resultado);
                mensajeCorrecto('error', resultado);
            }
//            $('.input-daterange').datepicker({
//                format: 'mm/dd/yyyy',
//                //format: 'DD dd-MM-yyyy',
//                //format: 'DD dde MM del yyyy',   
//                autoclose: true,
//                startDate: '0d',
//                endDate: '+30d',
//                numberOfMonths: 1,
//                closeText: 'Cerrar',
//                language: 'es',
//                daysOfWeekDisabled: "0,6",
//                todayHighlight: true,
//                clearBtn: true
//            });
            $('#nivel2').text('Editar Menú');
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

function editar(event, idMenu) {
    var tipoMenu = new Object();
    event.preventDefault();

    menu.intidmenu = idMenu;
    menu.strcaracteristicas = $('#caracteristicas').val();
    tipoMenu.intidtipo = parseInt($('#tipoMenu').val());
    menu.intidtipomenu = tipoMenu;

    console.log('Data menu ', menu);

//    fechasMenu.intid = idFechas;
//    fechasMenu.dtfechainicio = new Date($('#fechaInicio').val());
//    fechasMenu.dtfechafin = new Date($('#fechaFin').val());

    if (validarFormularioMenuSinPlanificacion()) {
        llamadoCarga();
        $.ajax({
            url: "menuControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'edicion',
                'datos': JSON.stringify(menu)},
//                'datosPlanificacion': JSON.stringify(fechasMenu)},
            success: function (resultado) {
                console.log('Resultado de la edicion ', resultado);
                recargarDatatable();
                mensajeCorrecto('success', resultado);
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

}

function formulario(event) {
    event.preventDefault();
    llamadoCarga();
    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formulario'},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
            $('.input-daterange').datepicker({
                format: 'mm/dd/yyyy',
                //format: 'DD dd-MM-yyyy',
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
            $('#nivel2').text('Nuevo Menú');
            $("#inputPlanificacion").hide();
            $("#seccionMenusActivos").hide();
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
        timer: 2500
    })
}

function recargarDatatable() {
    $("#contenidoInferior").html("<br><table id=\"example\" class=\"display\" style=\"width:100%;\"></table>");
    cargarListados();
}

function eliminar(event, idMenu, idFechas) {
    event.preventDefault();
    var sweetAlert;

    sweetAlert = swalWithBootstrapButtons.fire({
        title: '&iquest;Estas seguro que de deseas eliminar el menu ?',
        text: "Si elimina el menu el proceso no se puede revertir",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    eliminarMenu(sweetAlert, idMenu, idFechas);

}

function eliminarMenu(sweetAlert, idMenu, idFechas) {
    menu.intidmenu = idMenu;
//    fechasMenu.intid = idFechas;
//
//    console.log(fechasMenu.intid);
    console.log(menu);

    sweetAlert.then((result) => {
        if (result.value) {
            $.ajax({
                url: "menuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'eliminar',
                    'datos': JSON.stringify(menu)},
                success: function (resultado) {
                    console.log(resultado);
                    recargarDatatable();
                    mensajeCorrecto('success', resultado);
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
    });
}

function datatableListados(identificador, resultado, columnas) {
    console.log('Listado hecho JSON ', JSON.parse(resultado))
    $(identificador).DataTable({
        destroy: true,
        data: JSON.parse(resultado),
        columns: columnas,
        language: {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning&#250;n dato disponible en esta tabla",
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
}

function formularioActivarMenu(event, idMenu) {
    event.preventDefault();

    $('#modalFechas').modal();
    menu.intidmenu = idMenu;

    var columnas = [
        {title: "Caracter&#237;sticas"},
        {title: "Tipo de Men&#250;"},
        {title: "Fechas"},
        {title: "D&#237;as", "orderable": false},
        {title: "Opciones", "orderable": false},
    ];

    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioActivarMenu',
            'datos': JSON.stringify(menu)},
        success: function (resultado) {


            var dataResultado = JSON.parse(resultado);


            if (dataResultado.success === 'ok') {
                var htmlModal = dataResultado.infoMenuModal;
//                $('#modalFechasTitle').text('Activar menu ');
                $('#modalPlanificacion').html(htmlModal);
                datatableListados('#planificacionMenuInfo', dataResultado.listadoPlanificacion, columnas);


                $('.input-daterange').datepicker({
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
            } else if (dataResultado.success === 'error') {
                console.log('Resultado Error ', resultado);
                mensajeCorrecto('error', resultado);
                $('#modalFechas').modal('toggle');
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

function cambiarEstadoMenu(event, idMenu, estado) {
    event.preventDefault();

    var sweetAlert;
    var titleSweet = "";
    var confirmButton = "";

    if (estado) {
        titleSweet = "¿Estas seguro que de deseas activar el estadodel menú ?";
        confirmButton = "Activar";
    } else {
        titleSweet = "¿Estas seguro que de deseas desactivar el estadodel menú ?";
        confirmButton = "Desactivar";
    }

    sweetAlert = swalWithBootstrapButtons.fire({
        title: 'Modificar estado del menú',
        text: titleSweet,
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: confirmButton,
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    modificarEstadoMenu(sweetAlert, idMenu, estado);
}


function modificarEstadoMenu(sweetAlert, idMenu, estado) {
    menu.intidmenu = idMenu;
    menu.blnestado = estado;


    sweetAlert.then((result) => {
        if (result.value) {
            $.ajax({
                url: "menuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'cambiarEstadoMenu',
                    'datos': JSON.stringify(menu)},
                success: function (resultado) {
                    var data = JSON.parse(resultado);
                    if (data.success === "validacion") {
                        mensajeCorrecto('warning', resultado);
                    } else {
                        mensajeCorrecto('success', resultado);
                    }
                    cargaCompleta();
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
    });



}


function cargarListados() {
    $('#nivel2').text('Listado de Menus');
    llamadoCarga();
    let listadosMenus = new Promise((resolve, reject) => {

        var columnas = [
            {title: "Caracter&#237;sticas"},
            {title: "Tipo de Men&#250;"},
            {title: "Planificaci&#245;n"},
            {title: "Opciones", "orderable": false}
        ];

        $.ajax({
            url: "menuControlador.jsp",
            //async: false,
            type: "GET",
            dataType: "text",
            data: {'accion': 'listado'},
            success: function (resultado) {
                datatableListados('#example', resultado, columnas);
                resolve('Exito');
            },
            error: function (error) {
                mensajeCorrecto('error', error);
                cargaCompleta();
            },
            complete: function () {
                cargaCompleta();
            }
        });
    });

    listadosMenus.then((response) => {
        console.log('Response: ', response);
        var columnas = [
            {title: "Caracter&#237;sticas"},
            {title: "Tipo de Men&#250;"},
            {title: "Fechas"},
            {title: "D&#237;as"},
            {title: "Opciones", "orderable": false}
        ];

        $.ajax({
            url: "menuControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'menusActivosFechas'},
            success: function (resultado) {
                $("#seccionMenusActivos").show();
                datatableListados('#menusact', resultado, columnas);
            },
            error: function (error) {
                mensajeCorrecto('error', error);
                cargaCompleta();
            },
            complete: function () {
                cargaCompleta();
            }
        });

    }).catch((error) => {
        console.log('Response: ', error);
    });
}

function desactivarMenu(idMenu, idFechas) {
    event.preventDefault();

    menu.intidmenu = idMenu;
    fechasMenu.intid = idFechas;

    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'desactivarMenu',
            'datos': JSON.stringify(menu),
            'datosPlanificacion': JSON.stringify(fechasMenu)},
        success: function (resultado) {
            recargarDatatable();
            mensajeCorrecto('success', resultado);
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

function desactivarPlanificacion(event, idPlanificacion) {


    event.preventDefault();

    var sweetAlert;

    sweetAlert = swalWithBootstrapButtons.fire({
        title: '&iquest;Estas seguro que de deseas eliminar la planificación del menú en las fechas establecidas?',
        text: "Este proceso de eliminar la planificación del menu no se puede revertir",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });

    desactivarPlanificacionMenu(sweetAlert, idPlanificacion);

}

function desactivarPlanificacionMenu(sweetAlert, idPlanificacion) {

    var planificacionMenu = new Object();
    planificacionMenu.intid = idPlanificacion;

    sweetAlert.then((result) => {
        if (result.value) {
            $.ajax({
                url: "menuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'desactivarPlanificacionMenu',
                    'datosPlanificacion': JSON.stringify(planificacionMenu)},
                success: function (resultado) {
                    recargarDatatable();
                    mensajeCorrecto('success', resultado);
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
    });
}







function crearPlanificacionMenu(idMenu) {
    event.preventDefault();
    console.log('idMenu ', idMenu);

    if (validarFormularioPlanificacionMenu()) {

        var planificacionMenu = new Object();

        menu.intidmenu = idMenu;

        planificacionMenu.dtfechainicio = new Date($('#fechaInicio').val());
        planificacionMenu.dtfechafin = new Date($('#fechaFin').val());
        planificacionMenu.intidmenu = menu;

        console.log("Data " + JSON.stringify(planificacionMenu));

        var ingresoDatos = new Promise((resolve, reject) => {
            $.ajax({
                url: "menuControlador.jsp",
                type: "POST",
                dataType: "text",
                data: {'accion': 'crearPlanificacionMenu',
                    'datosPlanificacion': JSON.stringify(planificacionMenu)},
//                'datosPlanificacion': JSON.stringify(fechasMenu)},
                success: function (resultado) {
                    var data = JSON.parse(resultado);

                    if (data.success === 'ok') {
//                    if (validarMenuExistente(resultado)) {
                        mensajeCorrecto('success', resultado);
//                    }
                    } else if (data.success === 'validacion') {
                        mensajeCorrecto('warning', resultado);
                    } else {
                        mensajeCorrecto('error', resultado);
                    }
                    resolve('ok');
                },
                error: function (error) {
                    mensajeCorrecto('error', error);
                    cargaCompleta();
                    reject('error');
                },
                complete: function () {
                    cargaCompleta();
                }
            });
        });

        ingresoDatos.then(() => {
            listadoPlanificacionMenuId(idMenu);
        }).catch((error) => {
            console.log('Response: ', error);
        });
    }



}

function listadoPlanificacionMenuId(idMenu) {
    menu.intidmenu = idMenu;

    var columnas = [
        {title: "Caracter&#237;sticas"},
        {title: "Tipo de Men&#250;"},
        {title: "Fechas"},
        {title: "D&#237;as", "orderable": false},
        {title: "Opciones", "orderable": false},
    ];

    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'listadoPlanificacionMenu',
            'datos': JSON.stringify(menu)},
        success: function (resultado) {
            var dataResultado = JSON.parse(resultado);
            if (dataResultado.success === 'ok') {
                datatableListados('#planificacionMenuInfo', dataResultado.listadoPlanificacion, columnas);
            } else if (dataResultado.success === 'error') {
                mensajeCorrecto('error', resultado);
                $('#modalFechas').modal('toggle');
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


function desactivarPlanificacionInfo(event, idPlanificacion, idMenu) {


    event.preventDefault();

    var sweetAlert;

    sweetAlert = swalWithBootstrapButtons.fire({
        title: '&iquest;Estas seguro que de deseas eliminar la planificación del menú en las fechas establecidas?',
        text: "Este proceso de eliminar la planificación del menu no se puede revertir",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Eliminar',
        cancelButtonText: 'Cancelar',
        reverseButtons: true
    });
//
    desactivarPlanificacionMenuInfo(sweetAlert, idPlanificacion, idMenu);

}


function desactivarPlanificacionMenuInfo(sweetAlert, idPlanificacion, idMenu) {

    var planificacionMenu = new Object();
    planificacionMenu.intid = idPlanificacion;

    sweetAlert.then((result) => {
        if (result.value) {
            $.ajax({
                url: "menuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'desactivarPlanificacionMenu',
                    'datosPlanificacion': JSON.stringify(planificacionMenu)},
                success: function (resultado) {
                    mensajeCorrecto('success', resultado);
                    listadoPlanificacionMenuId(idMenu);
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
    });
}

function toggleCheckboxPlanificacion() {
    var check = $('#planificacionCheck').prop('checked');
    console.log('Check Value ', check);

    if (check) {
        $("#inputPlanificacion").show();
    } else {
        $("#inputPlanificacion").hide();
    }

}