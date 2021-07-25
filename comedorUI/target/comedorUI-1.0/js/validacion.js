

function Matar() {
    var Tipo = $("#Logeo").text();
    $.ajax({
        url: "./final.jsp",
        type: "POST",
        dataType: "text",
        data: {opcion: 'cerrar'},
        success: function (msg) {
            if (Tipo.length === 14) {
                //INSTITUCIONAL
                var urlLogout = "https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri=";
                var urlQuery = "https://seguridad.espoch.edu.ec/cas/logout?service=https://localhost:8181/EjemploDticIU/index.jsp"; //Local
//                var urlQuery = "https://seguridad.espoch.edu.ec/cas/logout?service=https://ejemplodtic.espoch.edu.ec/EjemploDticIU/index.jsp"; //Insitucional
                urlQuery = encodeURI(urlQuery);
                window.location.replace(urlLogout + urlQuery);
            }
            if (Tipo.length === 6) {
                //BD CENTRALIZADA
                var urlLogout = "https://seguridad.espoch.edu.ec/cas/logout?service=";
                var urlQuery = "https://localhost:8181/EjemploDticIU/index.jsp"; //Local
//                var urlQuery = "https://ejemplodtic.espoch.edu.ec/EjemploDticIU/index.jsp"; //Institucional
                urlQuery = encodeURI(urlQuery);
                window.location.replace(urlLogout + urlQuery);
            }
        }
    });
}


function valCampoVacio(campo){
    return  campo.val().length > 0 ? true : false;
}


function checkEmail(campo, expresionRegular) {
return campo.val().match(expresionRegular) ? true : false;
}

function checkSelect(select) {
return select.val() ? true : false;
}

function validarDecimal(valor) {
    var RE = /^\d*(\.\d{1})?\d{0,1}$/;
    if (RE.test(valor)) {
        return true;
    } else {
        return false;
    }
}

function validarCampoFecha(fecha){
    
    Date.prototype.isDate = function (){
        return (this !== "Invalid Date" && !isNaN(this)) ? true : false;
    }
    
    return new Date(fecha).isDate();
}

function validarFechaMayor() {
    var fechaInicio = new Date($('#fechaInicio').val());
    var fechaFin = new Date($('#fechaFin').val());

    if (fechaInicio > fechaFin) {
        $('#fechasmensaje').show();
        $('#fechasmensaje').text("La fecha de inicio debe ser mayor o igual a la fecha de fin");
        return false;
    }
    return true;
}

function validarIntervaloFecha() {

    var isDateFechaInicio = validarCampoFecha($('#fechaInicio').val());
    var isDateFechaFin = validarCampoFecha($('#fechaFin').val());

    if (!isDateFechaInicio || !isDateFechaFin) {
        $('#fechasmensaje').show();
        $('#fechasmensaje').text("Se debe ingresar una fecha v\u00E1lida");
        return false;
    }
    return true;
}

function mensajeFechaInicio() {
    if (valCampoVacio($('#fechaInicio'))) {
        $('#fechasmensaje').hide();
    }
}

function mensajeFechaFin() {
    if (valCampoVacio($('#fechaFin'))) {
        $('#fechasmensaje').hide();
    }
}

