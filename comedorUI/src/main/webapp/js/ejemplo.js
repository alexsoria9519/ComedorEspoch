//Querido colega programador: 
//Cuando escribí este código, solo Dios y yo sabíamos cómo funcionaba.
//Ahora solo dios lo sabe!!
//
//Así que si estas tratando de ‘OPTIMIZAR’ esta rutina 
//y fracasas (seguramente), por favor, incrementa 
//el siguiente contador como una advertencia para el siguiente colega:
//
//Total_horas_perdidas_aqui = 0


/*P E R I O D O*/

function clicLlamarEjemploHTML() {
    $.ajax({
        url: "ejemploHTML.jsp",
        type: "GET",
        data: {},
        contentType: "application/json ; charset=UTF-8",
        success: function (datos) {
            $("#contenidoDinamico").html("");
            $("#contenidoDinamico").html(datos);
        }
        ,
        error: function (error) {
            location.reload();
        }
    });
}

function clicAgregaPeriodo(idUser) {
    var datos = convertirObjetoJson('FormularioXXXXX');
    var uscape = decodeURIComponent(datos);
    datos = "";
    datos = uscape;

    $.ajax({
        url: "controlador.jsp",
        type: "GET",
        data: {opc: 'Periodo', tsk: 'agregarPeriodo', idUser: idUser, datos: datos},
        contentType: "application/json ; charset=UTF-8",
        success: function (datos) {
            clicPeriodo(idUser);
            alert('Se ha ingresado con éxito');
        }
        ,
        error: function (error) {
            location.reload();
        }
    });
}

function clicEliminaPeriodo(idUser, idDato) {

    $.ajax({
        url: "controladorCertificados.jsp",
        type: "GET",
        data: {opc: 'Periodo', tsk: 'eliminarPeriodo', idUser: idUser, idDato: idDato},
        contentType: "application/json ; charset=UTF-8",
        success: function (datos) {
            clicPeriodo(idUser);
            alert('Se ha eliminado con éxito');
        }
        ,
        error: function (error) {
            location.reload();
        }
    });

}


function clicMostEdPeriodo(idUser, idDato) {
    $.ajax({
        url: "AdminPeriodosEdit.jsp",
        type: "GET",
        data: {idUser: idUser, idDato: idDato},
        contentType: "application/json ; charset=UTF-8",
        success: function (datos) {
            $("#contenidoDinamico").html("");
            $("#contenidoDinamico").html(datos);
        }
        ,
        error: function (error) {
            location.reload();
        }
    });
}

function clicEditaPeriodo(idUser, idDato) {
    var datos = convertirObjetoJson('FrmEditPeriodo');
    var uscape = decodeURIComponent(datos);
    datos = "";
    datos = uscape;
    $.ajax({
        url: "controladorCertificados.jsp",
        type: "GET",
        data: {opc: 'Periodo', tsk: 'editaPeriodo', idUser: idUser, idDato: idDato, datos: datos},
        contentType: "application/json ; charset=UTF-8",
        success: function (datos) {
            clicPeriodo(idUser);
            alert('Se ha editado con éxito');
        }
        ,
        error: function (error) {
            location.reload();
        }
    });
}

