/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

loadCardPanel();
loadGraficosSection2();
//loadInfoVentaDia();
function loadCardPanel() {
    var dataInfo = requestCardsInfo();

    dataInfo.then(data => {
        var fechaActual = formatDate(new Date());

        console.log('Data Cards', data);

        loadInfoVentaDia(data.valoresDataVenta, fechaActual);
        loadCantidadVentasDetalleDia(data.cantidadDataVenta, fechaActual);
        loadCantidadVentasDetalleTipoUsuario(data.cantidadDataTipoUsuarioVenta, fechaActual);
        loadCantidadReservas(data.cantidadDataReservas, fechaActual);


        var dataGraficos1 = requestGraphicsSection1();

        dataGraficos1.then(data => {
            var date = new Date();
            console.log("dataGraficos1 ", data);


            if (data.graficoLineasFechas.totalVentas) {
                $("#titleGraficoLineasTickets").html(`Tickets vendidos(${date.getFullYear()}) - Total: ${number_format(data.graficoLineasFechas.totalVentas)}`);
            } else {
                $("#titleGraficoLineasTickets").html(`Tickets vendidos(${date.getFullYear()})`);
            }


            loadGraficoCantidadVentasFechas(JSON.parse(data.graficoLineasFechas.detalleVentas));
            $("#titleGraficoTiposUsuarios").html(`Usuarios(${date.getFullYear()}) `);
            loadChartTiposUsuarios(JSON.parse(data.graficoPastelTiposUsuarios.detalleVentas));
        }).catch(err => {
            console.log("Error ", err);
        });


    }).catch(err => {
        var dataGraficos1 = requestGraphicsSection1();
        dataGraficos1.then(data => {
            var date = new Date();
            console.log("dataGraficos1 ", data);


            if (data.graficoLineasFechas.totalVentas) {
                $("#titleGraficoLineasTickets").html(`Tickets vendidos(${date.getFullYear()}) - Total: ${number_format(data.graficoLineasFechas.totalVentas)}`);
            } else {
                $("#titleGraficoLineasTickets").html(`Tickets vendidos(${date.getFullYear()})`);
            }


            loadGraficoCantidadVentasFechas(JSON.parse(data.graficoLineasFechas.detalleVentas));
            loadChartTiposUsuarios(JSON.parse(data.graficoPastelTiposUsuarios.detalleVentas));
        }).catch(err => {
            console.log("Error ", err);
        });
    });
}

function loadGraficosSection2() {
    var dataInfo = requestGraphicsSection2();

    dataInfo.then(data => {
        console.log("loadGraficosSection2 ", data);
        loadChartGeneros(JSON.parse(data.graficoPastelGenero.detalleVentas));
        loadChartBarMeses(JSON.parse(data.graficoBarrasMeses.detalleResumen));
    }).catch(err => {
        console.log("Error loadGraficosSection2", err);
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

function requestGraphicsSection1() {
    //
    return new Promise((resolve, reject) => {
        var data = new Object();
        data.fechaInicioLineas = getDateInicioGrafico1();
        data.fechaFinLineas = formatDate(new Date());

        console.log("Fechas requestGraphicsSection1", data);
        $.ajax({
            url: "Dashboard/dashboardControlador.jsp",
            type: "GET",
            data: {'accion': 'graphicsSection1',
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

function requestGraphicsSection2() {
    //
    return new Promise((resolve, reject) => {
        var data = new Object();
        //data.fechaInicioLineas = getDateInicioGrafico1();
        data.fechaActual = formatDate(new Date());

//        console.log("Fechas requestGraphicsSection1", data);
        $.ajax({
            url: "Dashboard/graficos.jsp",
            type: "GET",
            data: {'accion': 'graphicsSection',
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
            htmlData += `<div class='col-6 col-md-6 col-sm-6' ><h5 ><strong> ${venta.nombrecostousuario}: </strong>  ${venta.cantidadvendidos}</h5></div>`;
        }
        htmlData += "</div>"
        $("#titleCantidadVentas").html(`Ventas(${fechaActual})`);
        $('#cantidadDetalleVenta').html(htmlData);
    }
    console.log("Resultado ", data);
}

function loadCantidadVentasDetalleTipoUsuario(data, fechaActual) {
    if (data.success === "ok") {
        var datosDetalleVenta = JSON.parse(data.detalleVentas);
        var htmlData = "";
        htmlData += "<div class='col-12 col-md-12' >"
        for (var venta of datosDetalleVenta.ventasProcedure) {
            htmlData += `<div class='col-6 col-md-6 col-sm-6' ><h5 ><strong> ${venta.nombrecostousuario}: </strong>  ${venta.cantidadvendidos}</h5></div>`;
        }
        htmlData += "</div>"
        $("#titleCantidadTiposUsuariosVentas").html(`Ventas Usuarios(${fechaActual})`);
        $('#cantidadDetalleTipoUsuarioVenta').html(htmlData);
    }
    console.log("Resultado ", data);
}


function loadCantidadReservas(data, fechaActual) {
    if (data.success === "ok") {
        var datosDetalleVenta = JSON.parse(data.detalleVentas);
        var cantidad = datosDetalleVenta.ventasProcedure[0].totalTicketsVendidos;

        if (cantidad) {
            $('#cantidadReservasDia').html(` ${cantidad} tickets reservados`);
        } else {
            $('#cantidadReservasDia').html(` 0 tickets reservados`);
        }

        $("#titleCantidadDetalleReservas").html(`Reservas(${fechaActual})`);

    }
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

function getDateInicioGrafico1() {
    var date = new Date();
    return `${date.getFullYear()}-01-01`
}

function number_format(number, decimals, dec_point, thousands_sep) {
    // *     example: number_format(1234.56, 2, ',', ' ');
    // *     return: '1 234,56'
    number = (number + '').replace(',', '').replace(' ', '');
    var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
            sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
            dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
            s = '',
            toFixedFix = function (n, prec) {
                var k = Math.pow(10, prec);
                return '' + Math.round(n * k) / k;
            };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

