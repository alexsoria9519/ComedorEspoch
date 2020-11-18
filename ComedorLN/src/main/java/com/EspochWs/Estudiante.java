/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EspochWs;

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
public class Estudiante {
    private String Cedula;
    private String Nombres;
    private String Apellidos;
    private String CedMilitar;
    private String FechaNac;
    private String LugarNac;
    private String Nacionalidad;
    private String Direccion;
    private String Telefono;
    private String Email;
    private String Sexo;
    private String EstadoCivil;
    private String Padre;
    private String Madre;
    private String Ciudad;
    private String Provincia;

    public Estudiante() {
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getCedMilitar() {
        return CedMilitar;
    }

    public void setCedMilitar(String CedMilitar) {
        this.CedMilitar = CedMilitar;
    }

    public String getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(String FechaNac) {
        this.FechaNac = FechaNac;
    }

    public String getLugarNac() {
        return LugarNac;
    }

    public void setLugarNac(String LugarNac) {
        this.LugarNac = LugarNac;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(String EstadoCivil) {
        this.EstadoCivil = EstadoCivil;
    }

    public String getPadre() {
        return Padre;
    }

    public void setPadre(String Padre) {
        this.Padre = Padre;
    }

    public String getMadre() {
        return Madre;
    }

    public void setMadre(String Madre) {
        this.Madre = Madre;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public String getProvincia() {
        return Provincia;
    }

    public void setProvincia(String Provincia) {
        this.Provincia = Provincia;
    }
    
    public String getDatosEstudiante(String cedula){
         char s;
        s = cedula.charAt(cedula.length() - 1);
        cedula = cedula.substring(0, cedula.length() - 1);
        cedula = cedula.concat("-" + s);

        System.out.println(cedula);

        String respuesta = "";
        try {

            String url = "http://academico.espoch.edu.ec/OAS_Interop/InfoCarrera.asmx?op=GetDatosCompletosEstudiante";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", " text/xml; charset=utf-8");
            String xml = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:acad=\"http://academico.espoch.edu.ec/\">\n"
                    + "   <soap:Header>\n"
                    + "      <acad:credentials>\n"
                    + "         <!--Optional:-->\n"
                    + "         <acad:username>matricula</acad:username>\n"
                    + "         <!--Optional:-->\n"
                    + "         <acad:password>matricula</acad:password>\n"
                    + "      </acad:credentials>\n"
                    + "   </soap:Header>\n"
                    + "   <soap:Body>\n"
                    + "      <acad:GetDatosCompletosEstudiante>\n"
                    + "         <!--Optional:-->\n"
                    + "         <acad:strCedula>" + cedula + "</acad:strCedula>\n"
                    + "      </acad:GetDatosCompletosEstudiante>\n"
                    + "   </soap:Body>\n"
                    + "</soap:Envelope>";

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
            respuesta = responsed.toString();
        } catch (Exception ex) {
            System.err.println("com.EspochWs.Estudiante.getDatosEstudiante() "+ex);
            return "{\"error\": \"No hay datos\" ";
        }
        return xmlToJson(respuesta);
    }
    
    
    
    
    private String xmlToJson(String respuesta){
        
        JSONObject json = XML.toJSONObject(respuesta);

        json = json.getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("GetDatosCompletosEstudianteResponse");

        String datosEstudiante;
        if (!json.isNull("GetDatosCompletosEstudianteResult")) {
            datosEstudiante = json.getJSONObject("GetDatosCompletosEstudianteResult").toString();
        } else {
            datosEstudiante = "{\"error\": \"No hay datos\" }";
        }
        
        return datosEstudiante;
    }
    
}
