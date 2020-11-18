/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspochWS;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.XML;

/**
 *
 * @author corebitsas
 */
public class Carreras {
    
    private List<Carrera> carreras = new ArrayList<Carrera>();

    public Carreras() {
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    
    
    
    
    public String getTodasCarrerasEspochWS(){
        
        String Respuesta = "";
        try {
            String texto = "";

            String url = "http://academico.espoch.edu.ec/OAS_Interop/InfoGeneral.asmx?op=GetTodasCarreras";
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
                    + "      <acad:GetTodasCarreras/>\n"
                    + "   </soapenv:Body>\n"
                    + "</soapenv:Envelope>";

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

                System.out.println(responsed.toString());

            }

            in.close();
            //  LA VARIABLE responsed TIENE TODO EL XML 
            //System.out.println("response:" + responsed.toString());
            // System.out.println("response:" +inputLine );
            texto = responsed.toString();

            Respuesta = responsed.toString();
            System.out.println(Respuesta);
        } catch (Exception e) {
            Respuesta = e.toString();
        }
        return "{ \"carreras\" : "+ xmlToJson(Respuesta) +"}";
    }
    
    
    private String xmlToJson(String Respuesta){
        JSONObject json = XML.toJSONObject(Respuesta);

        json = json.getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("GetTodasCarrerasResponse");

        String datosCarreras;
        if (!json.isNull("GetTodasCarrerasResult")) {
            //datosCarreras = json.getJSONObject("GetTodasCarrerasResult").toString();
            datosCarreras = json.getJSONObject("GetTodasCarrerasResult").getJSONArray("UnidadAcademica").toString();
            System.out.println(datosCarreras);
        } else {
            datosCarreras = "{\"error\": \"No hay datos\" }";
        }
        return datosCarreras;
    }
    
    
    
    
}
