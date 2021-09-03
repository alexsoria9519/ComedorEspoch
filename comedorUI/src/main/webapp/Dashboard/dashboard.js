/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

loadCardPanel();
//loadInfoVentaDia();
function loadCardPanel() {
    var dataInfo = requestCardsInfo();

    dataInfo.then(data => {
        var fechaActual = formatDate(new Date());

        console.log('Data Cards', data);

        loadInfoVentaDia(data.valoresDataVenta, fechaActual);
        loadCantidadVentasDetalleDia(data.cantidadDataVenta, fechaActual);

    }).catch(err => {
        console.log("Error ", err);
    });
}

function requestCardsInfo() {
    return new Promise((resolve, reject) => {
        var data = new Object();

        var fechaActual = formatDate(new Date());
        data.fecha = fechaActual;
        console.log("Fecha Actual ", fechaActual);
        $.ajax({
            url: "Dashboard/dashboardControlador.jsp",
            type: "GET",
            data: {'accion': 'datosPanelCard',
                'datos': JSON.stringify(data)},
            success: function (resultado) {
                resolve(resultado);
            },
            complete: function () {
//                cargaCompleta();
            },
            error: function (error) {
//                cargaCompleta();
                reject(error);
            }
        });
    });
}



function loadInfoVentaDia(data, fechaActual) {
    if (data.success === "ok") {
        $('#titleTotalVentas').html(`<div id='titleTotalVentas' class='text-xs font-weight-bold text-primary text-uppercase mb-1'>Ventas(${fechaActual})</div>`);
        $('#totalVentasDiario').html(`<h5 id='totalVentasDiario'><strong> Total: </strong>  $ ${data.valorVentas} </h5>`);
        $('#totalIvaDiario').html(`<h5 id='totalIvaDiario'><strong> IVA: </strong>  $ ${data.IVA} </h5>`);
    }
}


function loadCantidadVentasDetalleDia(data, fechaActual) {
    if (data.success === "ok") {
        var datosDetalleVenta = JSON.parse(data.detalleVentas);
        var htmlData = "";
        htmlData += "<div class='col-12 col-md-12' >"
        for (var venta of datosDetalleVenta.ventasProcedure) {
            htmlData += `<div class='col-6 col-md-6' ><h5 ><strong> ${venta.nombrecostousuario}: </strong>  ${venta.cantidadvendidos}</h5></div>`;
        }
        htmlData += "</div>"
        $("#titleCantidadVentas").html(`Ventas(${fechaActual})`);
        $('#cantidadDetalleVenta').html(htmlData);
    }
    console.log("Resultado ", data);
}

function formatDate(date) {
    if (date == null) {
        return;
    }
    var dd = date.getDate();
    var mm = date.getMonth() + 1;

    if (dd < 10)
    {
        dd = '0' + dd;
    }

    if (mm < 10)
    {
        mm = '0' + mm;
    }
    return date.getFullYear() + "-" + mm + "-" + dd;
}

