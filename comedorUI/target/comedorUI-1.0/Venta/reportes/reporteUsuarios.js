/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

loadDataReporteUsuario();

function loadDataReporteUsuario() {

    var facultad = sessionStorage.getItem("descripcionFacultad");
    var carrera = sessionStorage.getItem("descripcionCarrera");
    var dataRequestReporte = sessionStorage.getItem("requestReporteUsuarios");
    var request = JSON.parse(dataRequestReporte);
    console.log('loadDataReporteUsuario ', dataRequestReporte);
    sessionStorage.clear();

    $("#facultad").text(`Facultad: ${facultad}`);
    $("#carrera").text(`Carrera: ${carrera}`);
    if (request.carrera === 'Ninguna') {
        dataReporteUsuarios(dataRequestReporte, 'reporteFacultad');
        $("#buttonPdf").html("  <button type='button' class='btn  btn-danger' onclick=\"pdfReporteFacultadesCarreras('reporteUsuariosFacultad')\"><i class=\"fas fa-file-pdf\"></i></button>");
    } else {
        dataReporteUsuarios(dataRequestReporte, 'reporteCarrera');
        $("#buttonPdf").html("<button type='button' class='btn  btn-danger' onclick=\"pdfReporteFacultadesCarreras('reporteUsuariosCarrera')\"><i class=\"fas fa-file-pdf\"></i></button>");
    }



}


function dataReporteUsuarios(dataRequestReporte, identificador) {
    llamadoCarga();
    $.ajax({
        url: "reportesVentasControlador.jsp",
        type: "GET",
        data: {'accion': identificador,
            'datos': dataRequestReporte},
        success: function (resultado) {
            var data = JSON.parse(resultado);
            createTableUsuarios(data);
            $("#totalVentas").text(`Cantidad de ventas ${data.totalVentas}`);
            $("#totalUsuarios").text(`Cantidad de usuarios ${data.totalUsuarios}`);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}


function createTableUsuarios(dataListado) {

    console.log('createTableUsuarios ', dataListado);

    var dataUsuarios = JSON.parse(dataListado.listadoUsuarios);

    $('#dataUsuarios').DataTable({
        data: dataUsuarios.dataUsuarios,
        columns: [
            {"data": "nombres"},
            {"data": "apellidos"},
            {"data": "cantidadventas"}
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
}

function pdfReporteFacultadesCarreras(identificador) {
    event.preventDefault();
    $.ajax({
        url: "reportesVentasControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'pdfReporteFacultadesCarrera',
            'datos': identificador
        },
        success: function (resultado) {
            downloadBase64File('application/pdf', resultado, "reporte-" + identificador);
        },
        complete: function () {
            cargaCompleta();
        },
        error: function (error) {
            cargaCompleta();
        }
    });
}


