/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

loadInfoVentaDia();

function loadInfoVentaDia() {

    var dataInfoVentaDia = new Promise((resolve, reject) => {

        var data = new Object();

        var fechaActual = formatDate(new Date());
        data.fecha = fechaActual;
        console.log("Fecha Actual ", fechaActual);
        $.ajax({
            url: "Dashboard/dashboardControlador.jsp",
            type: "GET",
            data: {'accion': 'valorVentasDia',
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

    dataInfoVentaDia.then(data => {
        var fechaActual = formatDate(new Date());
        if (data.success === "ok") {
            $('#titleTotalVentas').html("<div id='titleTotalVentas' class='text-xs font-weight-bold text-primary text-uppercase mb-1'>Ventas(" + fechaActual + ")</div>");
            $('#totalVentasDiario').html("<h5 id='totalVentasDiario'><strong> Total: </strong>  $" + data.valorVentas + "</h5>");
            $('#totalIvaDiario').html("<h5 id='totalIvaDiario'><strong> IVA: </strong>  $" + data.IVA + "</h5>");
        }
        console.log("Resultado ", data);
    }).catch(err => {
        console.log("Error ", err);
    });

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

