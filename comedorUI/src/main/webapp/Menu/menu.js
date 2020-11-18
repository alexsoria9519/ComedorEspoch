const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
    },
    buttonsStyling: false,
});

var menu = new Object();
var fechasMenu = new Object();

cargarListados();
$('#nivel2').text('Listado de Menus');

function ingreso(event) {
    event.preventDefault();

    var tipoMenu = new Object();

    menu.strcaracteristicas = $('#caracteristicas').val();
    tipoMenu.intidtipo = parseInt($('#estado').val());
    menu.intidtipo = tipoMenu;

    fechasMenu.dtfechainicio = new Date($('#fechaInicio').val());
    fechasMenu.dtfechafin = new Date($('#fechaFin').val());
    
    console.log('JSON.stringify(menu) ', JSON.stringify(menu));

    if (validarFormularioMenu()) {
        $.ajax({
            url: "menuControlador.jsp",
            type: "GET",
            dataType: "text",
            data: {'accion': 'ingreso',
                'datos': JSON.stringify(menu),
                'datosFechaMenu': JSON.stringify(fechasMenu)},
            success: function (resultado) {
                console.log(resultado);
                if (validarMenuExistente(resultado)) {
                    recargarDatatable();
                    mensajeCorrecto('success', resultado);
                }
            },
            error: function (error) {

            }
        });
    }


}

function edicion(event, idMenu) {
    event.preventDefault();

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
            }
        });
    }

}

function formulario(event) {
    event.preventDefault();

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
            console.log(error);
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
    //listado();
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
                    console.log(error);
                }
            });

        }
    });
}

function datatableListados(identificador, resultado, columnas) {

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
    menu.intidmenu = idMenu;
    $.ajax({
        url: "menuControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'formularioActivarMenu',
            'datos': JSON.stringify(menu)},
        success: function (resultado) {
            $("#contenidoInferior").html(resultado);
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
            }
        });
    }
}

function cargarListados() {
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
                console.log('resultado ', data.listado);
                datatableListados('#example', data.listado, columnas);
                resolve('Exito');
            },
            error: function (error) {
                reject('Fallo');
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
                console.log(resultado);
                datatableListados('#menusact', resultado, columnas);
            },
            error: function (error) {
                console.log(error);
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
        }
    });
}
        