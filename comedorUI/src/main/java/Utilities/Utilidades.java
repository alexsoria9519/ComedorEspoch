/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Alex
 */
public class Utilidades {

    public String estado(Boolean estado) {
        return ((estado) ? "Activo" : "Inactivo");
    }

    public String fecha(Date fecha) {
        String fechaFormateada;
        try {
            Locale esLocale = new Locale("es", "ES");
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, esLocale);
            fechaFormateada = dateFormat.format(fecha);
            return Character.toUpperCase(fechaFormateada.charAt(0)) + fechaFormateada.substring(1); // Colocar la primera letra en mayuscula
        } catch (Exception ex) {
            System.err.println("Utilities.Utilidades.fecha()");
            System.err.println(ex);
        }
        return null;

    }

    public String fechaFormularioEdicion(Date fecha) {
        String fechaFormateada;
        fechaFormateada = fecha(fecha);
        if(fechaFormateada != null )
            return fechaFormateada.replace(" de ", "-");
        return null;
    }

    public Date stringToDate(String strFecha) throws ParseException {
        /*
        Date fecha = new Date();
        
        try{
            Locale esLocale = new Locale("es", "ES");
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, esLocale);
            fecha = dateFormat.parse(strFecha);
        }catch (ParseException ex){
            System.err.println(ex);
        }

        return fecha;
         */
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date fecha = (Date) formatter.parse(strFecha);
        return fecha;

    }

    public String fechaFormatoHtml(Date fecha) {
        SimpleDateFormat fechaSimple = new SimpleDateFormat("yyyy-MM-dd");
        return fechaSimple.format(fecha);
    }

    public Integer diaFecha(Date fecha) {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(fecha);

        return (c1.get(Calendar.DATE));
    }

    public Boolean diaHabil(Date fecha) {
        return (diaFecha(fecha) != 1 && diaFecha(fecha) != 2);
    }

    
    public Boolean validarError(String dataResponse) {
        
        System.err.println("Data Response "+ dataResponse);
        
        try {
            JSONObject respJson = new JSONObject(dataResponse);
            String error = respJson.getString("error");
            return false;
        } catch (Exception ex) {
            System.err.println("com.comedorui.TipoUsuarioUI.validarError() " + ex);
            return true;
        }
    }
}
