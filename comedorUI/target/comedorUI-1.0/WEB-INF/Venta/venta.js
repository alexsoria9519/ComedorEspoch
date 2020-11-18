/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$( window ).on( "load", function() { 
    console.log( "ready!" );
    //formulario();
    //ponleFocus();
    
    //listado();
});


function datosCedula(event){
    event.preventDefault();
    formulario($('#cedula').val());
    //alert($('#cedula').val());
}



function formulario(cedula){
    $.ajax({
        url: "ventaControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'buscardatos',
               'cedula': cedula },
        //data: {'accionPermiso': 'nuevo'},
        success: function (resultado) {
            //alert(resultado);
            console.log(resultado);
            $("#contenidoInferior").html(resultado);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function listado(){
    console.log("Listado");
    $.ajax({
        url: "costoControlador.jsp",
        type: "GET",
        dataType: "text",
        data: {'accion': 'listado'},
        //data: {'accionPermiso': 'nuevo'},
        success: function (resultado) {
            //alert(resultado);
            console.log(resultado);
            $("#contenidoInferior").html(resultado);
        },
        error: function (error) {
            console.log(error);
        }
    });
}