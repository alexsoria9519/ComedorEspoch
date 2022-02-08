//$(document).ready(function () {
////
////console.log("Ready!!!");
////
////});

//loadGraficoCantidadVentasFechas();
//loadChartDonut();
//loadChartBar();

function loadGraficoCantidadVentasFechas(graficoData) {
    // Area Chart Example


    var datos = getDataCantidaVentasFechas(graficoData);

    var datosGrafico = new Object();

    console.log("loadGraficoCantidadVentasFechas ", datos);

    if (datos) {
        if (datos.labels && datos.cantidades) {
            datosGrafico.labels = datos.labels;
            datosGrafico.cantidades = datos.cantidades;
        }
    } else {
        datosGrafico.labels = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        datosGrafico.cantidades = [0, 10000, 5000, 15000, 10000, 20000, 15000, 25000, 20000, 30000, 25000, 40000];
    }


    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
        type: 'line',
        data: {
//            labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            labels: datosGrafico.labels,
            datasets: [{
                    label: "Cantidad de ventas",
                    lineTension: 0.3,
                    backgroundColor: "rgba(78, 115, 223, 0.05)",
                    borderColor: "rgba(78, 115, 223, 1)",
                    pointRadius: 3,
                    pointBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointBorderColor: "rgba(78, 115, 223, 1)",
                    pointHoverRadius: 3,
                    pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
//                    data: [0, 10000, 5000, 15000, 10000, 20000, 15000, 25000, 20000, 30000, 25000, 40000],
                    data: datosGrafico.cantidades
                }],
        },
        options: {
            maintainAspectRatio: false,
            layout: {
                padding: {
                    left: 10,
                    right: 25,
                    top: 25,
                    bottom: 0
                }
            },
            scales: {
                xAxes: [{
                        time: {
                            unit: 'date'
                        },
                        gridLines: {
                            display: false,
                            drawBorder: false
                        },
                        ticks: {
                            maxTicksLimit: 7
                        }
                    }],
                yAxes: [{
                        ticks: {
                            maxTicksLimit: 5,
                            padding: 10,
                            // Include a dollar sign in the ticks
                            callback: function (value, index, values) {
                                return number_format(value);
                            }
                        },
                        gridLines: {
                            color: "rgb(234, 236, 244)",
                            zeroLineColor: "rgb(234, 236, 244)",
                            drawBorder: false,
                            borderDash: [2],
                            zeroLineBorderDash: [2]
                        }
                    }],
            },
            legend: {
                display: false
            },
            tooltips: {
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                titleMarginBottom: 10,
                titleFontColor: '#6e707e',
                titleFontSize: 14,
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                intersect: false,
                mode: 'index',
                caretPadding: 10,
                callbacks: {
                    label: function (tooltipItem, chart) {
                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
//                        return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
                        return datasetLabel + ' ' + number_format(tooltipItem.yLabel);
                    }
                }
            }
        }
    }
    );
}

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

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

function loadChartTiposUsuarios(graficoData) {
    // Pie Chart Example


    var datos = getData(graficoData);

    var datosGrafico = new Object();

    console.log("loadChartTiposUsuarios ", datos);

    if (datos) {
        if (datos.labels && datos.cantidades) {
            datosGrafico.labels = datos.labels;
            datosGrafico.cantidades = datos.cantidades;
            datosGrafico.colores = datos.colores;
        }
    } else {
        datosGrafico.labels = ["Direct", "Referral", "Social"];
        datosGrafico.cantidades = [55, 30, 15];
        datosGrafico.colores = ['#4e73df', '#1cc88a', '#36b9cc'];
    }


    var ctx = document.getElementById("myPieChart");
    var myPieChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: datosGrafico.labels,
            datasets: [{
                    data: datosGrafico.cantidades,
                    backgroundColor: datosGrafico.colores,
                    hoverBackgroundColor: datosGrafico.colores,
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                }],
        },
        options: {
            maintainAspectRatio: false,
            tooltips: {
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,
                callbacks: {
                    label: function (tooltipItem, data) {
                        var indice = tooltipItem.index;
                        var porcentaje = 0;
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var meta = dataset._meta[Object.keys(dataset._meta)[0]];
                        var total = meta.total;

                        if (data.datasets[0].data[indice] > 0) {
                            porcentaje = (data.datasets[0].data[indice] * 100) / total;
                        }

                        return  data.labels[indice] + ': ' + data.datasets[0].data[indice] + ' (' + porcentaje.toFixed(2) + ' %)';
                    }
                },

            },
            legend: {
                position: 'top',
            },
//            title: {
//                display: true,
//                text: 'Porcentaje de usuarios'
//            },
            cutoutPercentage: 70,
        },
    });

}

function loadChartGeneros(graficoData) {
    // Pie Chart Example


    var datos = getData(graficoData);

    var datosGrafico = new Object();

    console.log("loadChartTiposUsuarios ", datos);

    if (datos) {
        if (datos.labels && datos.cantidades) {
            datosGrafico.labels = datos.labels;
            datosGrafico.cantidades = datos.cantidades;
            datosGrafico.colores = datos.colores;
        }
    } else {
        datosGrafico.labels = ["Direct", "Referral", "Social"];
        datosGrafico.cantidades = [55, 30, 15];
        datosGrafico.colores = ['#4e73df', '#1cc88a', '#36b9cc'];
    }


    var ctx = document.getElementById("myPieChartGenero");
    var myPieChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: datosGrafico.labels,
            datasets: [{
                    data: datosGrafico.cantidades,
                    backgroundColor: datosGrafico.colores,
                    hoverBackgroundColor: datosGrafico.colores,
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                }],
        },
        options: {
            maintainAspectRatio: false,
            tooltips: {
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,
                callbacks: {
                    label: function (tooltipItem, data) {
                        var indice = tooltipItem.index;
                        var porcentaje = 0;
                        var dataset = data.datasets[tooltipItem.datasetIndex];
                        var meta = dataset._meta[Object.keys(dataset._meta)[0]];
                        var total = meta.total;

                        if (data.datasets[0].data[indice] > 0) {
                            porcentaje = (data.datasets[0].data[indice] * 100) / total;
                        }

                        return  data.labels[indice] + ': ' + data.datasets[0].data[indice] + ' (' + porcentaje.toFixed(2) + ' %)';
                    }
                },
            },
            legend: {
                position: 'top',
            },
//            title: {
//                display: true,
//                text: 'Porcentaje de usuarios'
//            },
            cutoutPercentage: 80,
        },
    });

}

function loadChartBarMeses(graficoData) {

    var datos = getDataMeses(graficoData);

    var datosGrafico = new Object();
//
    console.log("loadChartTiposUsuarios ", datos);

    var defaultDatasets = [{
            label: "UNA",
            backgroundColor: "#4e73df",
            hoverBackgroundColor: "#2e59d9",
            borderColor: "#4e73df",
            data: [4215, 5312, 6251, 7841, 9821, 14984, 4215, 5312, 6251, 7841, 9821, 14984],
        },
        {
            label: "FIE",
            backgroundColor: "#4e73df",
            hoverBackgroundColor: "#2e59d9",
            borderColor: "#4e73df",
            data: [4215, 5312, 6251, 7841, 9821, 14984, 4215, 5312, 6251, 7841, 9821, 14984],
        },
        {
            label: "FIE",
            backgroundColor: "#4e73df",
            hoverBackgroundColor: "#2e59d9",
            borderColor: "#4e73df",
            data: [4215, 5312, 6251, 7841, 9821, 14984, 4215, 5312, 6251, 7841, 9821, 14984],
        },
        {
            label: "FIE",
            backgroundColor: "#4e73df",
            hoverBackgroundColor: "#2e59d9",
            borderColor: "#4e73df",
            data: [4215, 5312, 6251, 7841, 9821, 14984, 4215, 5312, 6251, 7841, 9821, 14984],
        },
        {
            label: "FIE",
            backgroundColor: "#4e73df",
            hoverBackgroundColor: "#2e59d9",
            borderColor: "#4e73df",
            data: [4215, 5312, 6251, 7841, 9821, 14984, 4215, 5312, 6251, 7841, 9821, 14984],
        }, ];

    if (datos) {
        if (datos.datasets && datos.datasets.length > 0) {
            datosGrafico.datasets = datos.datasets;
        } else {
            datosGrafico.datasets = defaultDatasets;
        }
    } else {
        datosGrafico.datasets = defaultDatasets;
    }






    // Bar Chart Example
    var ctx = document.getElementById("myBarChart");
    var myBarChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Enero", "Febreo", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
            datasets: datosGrafico.datasets,
        },
        options: {
            maintainAspectRatio: false,
            layout: {
                padding: {
                    left: 10,
                    right: 25,
                    top: 25,
                    bottom: 0
                }

            },
            legend: {
                display: true
            },
            tooltips: {
                titleMarginBottom: 10,
                titleFontColor: '#6e707e',
                titleFontSize: 14,
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,
                callbacks: {
                    label: function (tooltipItem, chart) {
                        var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                        return datasetLabel + ': ' + number_format(tooltipItem.yLabel) + ' Tickets';
                    }
                }
            },
        }
    });

}


function getDataCantidaVentasFechas(dataCantidades) {
    var datos = new Object();
    var arrayLabels = new Array();
    var arrayCantidades = new Array();

    for (var i = 0, max = dataCantidades.mesesData.length; i < max; i++) {
        arrayLabels.push(dataCantidades.mesesData[i].mesDescripcion);
        arrayCantidades.push(dataCantidades.mesesData[i].cantidad);
    }

    datos.labels = arrayLabels;
    datos.cantidades = arrayCantidades;
    return datos;
}

//function getDataPorcentaje(dataCantidades) {
//    var datos = new Object();
//    var arrayLabels = new Array();
//    var arrayCantidades = new Array();
//    var arrayColores = new Array();
//
//    for (var i = 0, max = dataCantidades.ventasProcedure.length; i < max; i++) {
//        arrayLabels.push(dataCantidades.ventasProcedure[i].nombrecostousuario);
//        arrayCantidades.push(dataCantidades.ventasProcedure[i].porcentaje.toFixed(2));
//        arrayColores.push(getColor(i));
//    }
//
//    datos.labels = arrayLabels;
//    datos.cantidades = arrayCantidades;
//    datos.colores = arrayColores;
//    return datos;
//}

function getData(dataCantidades) {
    var datos = new Object();
    var arrayLabels = new Array();
    var arrayCantidades = new Array();
    var arrayColores = new Array();

    for (var i = 0, max = dataCantidades.ventasProcedure.length; i < max; i++) {
        arrayLabels.push(dataCantidades.ventasProcedure[i].nombrecostousuario);
        arrayCantidades.push(dataCantidades.ventasProcedure[i].totalTicketsVendidos);
        arrayColores.push(getColor(i));
    }

    datos.labels = arrayLabels;
    datos.cantidades = arrayCantidades;
    datos.colores = arrayColores;
    return datos;
}

function getDataMeses(dataCantidades) {
    var datos = new Object();
    var arrayDatasets = new Array();
    var dataFecha = dataCantidades.resumenes[0].fecha;

    for (var i = 0, max = dataCantidades.resumenes.length; i < max; i++) {
        arrayDatasets.push(getDataset(dataCantidades.resumenes[i], i));
    }
    datos.datasets = arrayDatasets;



    if (dataFecha) {
        const myArr = dataFecha.split("Z");

        var date = new Date(myArr);
        $("#dataTitleDatosFacultades").html(`<h6>Datos del comedor ESPOCH Matriz  - Fecha del reporte: ${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()} </h6>`);
    }

    return datos;
}



function getDataset(dataFacultad, identificador) {

    var dataset = new Object();
    dataset.label = dataFacultad.facultad;
    dataset.backgroundColor = getColor(identificador);
    dataset.hoverBackgroundColor = getColor(identificador);
    dataset.borderColor = getColor(identificador);
    dataset.data = [dataFacultad.enero, dataFacultad.febrero, dataFacultad.marzo, dataFacultad.abril, dataFacultad.mayo, dataFacultad.junio, dataFacultad.julio,
        dataFacultad.agosto, dataFacultad.septiembre, dataFacultad.octubre, dataFacultad.noviembre, dataFacultad.diciembre];

    return dataset;
}




function getColor(identificador) {
    var colores = ['#4e73df', '#1cc88a', '#36b9cc', '#d8b749', '#d84949', '#af49d8', '#d849b4', '#1f0a19', '#5a4b56', '#af49d8', '#2c064e', '#49d8b4'];
    if ((colores.length - 1) >= identificador) {
        return colores[identificador];
    }

    return colores[identificador - colores.length];

}