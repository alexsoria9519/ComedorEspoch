/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EspochWs;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author corebitsas
 */
public class Docente {

    public String datosDocente(String cedula) {
        String respuesta = "{\"error\": \"No hay datos\" }";
        Carreras carreras = new Carreras();
        Gson gson = new Gson();
        
        carreras = gson.fromJson(carreras.getTodasCarrerasEspochWS(), Carreras.class);
        
        for(int i=0; i<carreras.getCarreras().size(); i++){
           respuesta = datosDocenteWS(carreras.getCarreras().get(i).getCodEscuela(), cedula);
           if(!respuesta.equals("{\"error\": \"No hay datos\" }")){
               break;
           }
        }
        return respuesta;
    }

    public String datosDocenteWS(String codCarrera, String cedula) {
        char s;
        s = cedula.charAt(cedula.length() - 1);
        cedula = cedula.substring(0, cedula.length() - 1);
        cedula = cedula.concat("-" + s);

        String respuesta = "";
        try {

            String url = "http://academico.espoch.edu.ec/OAS_Interop/InfoCarrera.asmx?op=GetDatosDocente";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", " text/xml; charset=utf-8");
            String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:acad=\"http://academico.espoch.edu.ec/\">\n"
                    + "   <soapenv:Header>\n"
                    + "      <acad:credentials>\n"
                    + "         <!--Optional:-->\n"
                    + "         <acad:username>matricula</acad:username>\n"
                    + "         <!--Optional:-->\n"
                    + "         <acad:password>matricula</acad:password>\n"
                    + "      </acad:credentials>\n"
                    + "   </soapenv:Header>\n"
                    + "   <soapenv:Body>\n"
                    + "      <acad:GetDatosDocente>\n"
                    + "         <!--Optional:-->\n"
                    + "         <acad:CodCarrera>"+ codCarrera +"</acad:CodCarrera>\n"
                    + "         <!--Optional:-->\n"
                    + "         <acad:Cedula>"+ cedula +"</acad:Cedula>\n"
                    + "      </acad:GetDatosDocente>\n"
                    + "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer responsed = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                responsed.append(inputLine);
            }

            in.close();
            //  LA VARIABLE responsed TIENE TODO EL XML 
            respuesta = responsed.toString();
        } catch (Exception e) {
            respuesta = e.toString();
        }

        
        return xmlToJson(respuesta);
    }
    
    private String xmlToJson(String respuesta){
        JSONObject json = XML.toJSONObject(respuesta);

        json = json.getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("GetDatosDocenteResponse");

        String datosEstudiante;
        if (!json.isNull("GetDatosDocenteResult")) {
            datosEstudiante = json.getJSONObject("GetDatosDocenteResult").toString();
        } else {
            datosEstudiante = "{\"error\": \"No hay datos\" }";
        }
        return datosEstudiante;
    }
    
}
