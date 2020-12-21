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

    menu.strcaracteristicas = $('#caracteristicas').val();
    tipoMenu.intidtipo = parseInt($('#estado').val());
    menu.intidtipomenu = tipoMenu;

    var fechaInicioMenu = new Date($('#fechaInicio').val());
    var fechaFinMenu = new Date($('#fechaFin').val());

    menu.planificacionmenuCollection = [
        {
            "dtfechainicio": new Date($('#fechaInicio').val()),
            "dtfechafin": new Date($('#fechaFin').val())
        }
    ];

    if (validarFormularioMenu()) {
        llamadoCarga();
        $.ajax({
            url: "menuControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'ingreso',
                'datos': JSON.stringify(menu)},
//                'datosFechaMenu': JSON.stringify(fechasMenu)},
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

function editar(event, idMenu, idFechas) {
    var tipoMenu = new Object();
    event.preventDefault();

    menu.intidmenu = idMenu;
    menu.strcaracteristicas = $('#caracteristicas').val();
    tipoMenu.intidtipo = parseInt($('#estado').val());
    menu.intidtipo = tipoMenu;

    fechasMenu.intid = idFechas;
    fechasMenu.dtfechainicio = new Date($('#fechaInicio').val());
    fechasMenu.dtfechafin = new Date($('#fechaFin').val());

    if (validarFormularioMenu()) {
        llamadoCarga();
        $.ajax({
            url: "menuControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'edicion',
                'datos': JSON.stringify(menu),
                'datosFechaMenu': JSON.stringify(fechasMenu)},
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
    fechasMenu.intid = idFechas;

    console.log(fechasMenu.intid);
    console.log(menu);

    sweetAlert.then((result) => {
        if (result.value) {
            $.ajax({
                url: "menuControlador.jsp",
                type: "GET",
                dataType: "text",
                data: {'accion': 'eliminarLogico',
                    'datos': JSON.stringify(menu),
                    'datosFechaMenu': JSON.stringify(fechasMenu)},
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
    ];

    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioActivarMenu',
            'datos': JSON.stringify(menu)},
        success: function (resultado) {
            resultado = JSON.parse(resultado);
            console.log('resultado ', resultado);

            if (resultado.success === 'ok') {
                var menu = JSON.parse(resultado.menu);
                $('#modalFechasTitle').text('Activar menu ');
                var html = "<div class='col-md-6'><strong>Características: </strong> " + menu.strcaracteristicas + " </div>\n\
                            <div class='col-md-6'><strong>Tipo: </strong> " + menu.intidtipomenu.strtipo + " </div>";

                datatableListados('#planificacionMenuInfo', resultado.listadoPlanificacion, columnas);

                $('#dataModalFechas').html(html);
            }
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

function activarMenu(event, idMenu, ) {
    event.preventDefault();

    menu.intidmenu = idMenu;

    fechasMenu.dtfechainicio = new Date($('#fechaInicio').val());
    fechasMenu.dtfechafin = new Date($('#fechaFin').val());

    if (validarFormularioActivacion()) {
        $.ajax({
            url: "menuControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'activarMenu',
                'datos': JSON.stringify(menu),
                'datosFechaMenu': JSON.stringify(fechasMenu)},
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
}

function cargarListados() {
    $('#nivel2').text('Listado de Menus');
    llamadoCarga();
    let listadosMenus = new Promise((resolve, reject) => {

        var columnas = [
            {title: "Caracter&#237;sticas"},
            {title: "Tipo de Men&#250;"},
            {title: "Activar"},
            {title: "Opciones", "orderable": false}
        ];

        $.ajax({
            url: "menuControlador.jsp",
            //async: false,
            type: "GET",
            dataType: "text",
            data: {'accion': 'listado'},
            success: function (resultado) {
                let data = JSON.parse(resultado);
                console.log('resultado ', data);
                datatableListados('#example', data.listado, columnas);
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
                console.log('resultado del listado de fechas', resultado);
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
            'datosFechaMenu': JSON.stringify(fechasMenu)},
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
                    'datosFechaMenu': JSON.stringify(planificacionMenu)},
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
        