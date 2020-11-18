/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EspochWs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Alex
 */
public class Persona {
    
    public int eci_id;
    public int etn_id;
    public int gen_id;
    public String per_email;
    public long per_id;
    public String carnetDiscapacidad[];
    public String cuenta[];
    public String direccion[];
    public String documentoPersonal[];
    public String estadoCivil;
    public String etnia;
    public String genero;
    public String imagen;
    public String instruccionFormal[];
    public int lugarProcedencia;
    public String nacionalidadPersona[];
    public String parroquia;
    public String per_AfiliacionIESS;
    public long per_creadoPor;
    public String per_emailAlternativo;
    public String per_fechaCreacion;   //date
    public String per_fechaModificacion; //date 
    public String per_fechaNacimiento;  //date
    public long per_modificadoPor;
    public String per_nombres;
    public String per_primerApellido;
    public String per_segundoApellido;
    public String per_procedencia;
    public String per_telefonoCasa;
    public String per_telefonoCelular;
    public String per_telefonoOficina;
    public String personaPlurinacionalidad[];
    public int sex_id;
    public String sexo;
    public String tipoSangre;
    public int tsa_id;

    public int getEci_id() {
        return eci_id;
    }

    public void setEci_id(int eci_id) {
        this.eci_id = eci_id;
    }

    public int getEtn_id() {
        return etn_id;
    }

    public void setEtn_id(int etn_id) {
        this.etn_id = etn_id;
    }

    public int getGen_id() {
        return gen_id;
    }

    public void setGen_id(int gen_id) {
        this.gen_id = gen_id;
    }

    public String getPer_email() {
        return per_email;
    }

    public void setPer_email(String per_email) {
        this.per_email = per_email;
    }

    public long getPer_id() {
        return per_id;
    }

    public void setPer_id(long per_id) {
        this.per_id = per_id;
    }

    public String[] getCarnetDiscapacidad() {
        return carnetDiscapacidad;
    }

    public void setCarnetDiscapacidad(String[] carnetDiscapacidad) {
        this.carnetDiscapacidad = carnetDiscapacidad;
    }

    public String[] getCuenta() {
        return cuenta;
    }

    public void setCuenta(String[] cuenta) {
        this.cuenta = cuenta;
    }

    public String[] getDireccion() {
        return direccion;
    }

    public void setDireccion(String[] direccion) {
        this.direccion = direccion;
    }

    public String[] getDocumentoPersonal() {
        return documentoPersonal;
    }

    public void setDocumentoPersonal(String[] documentoPersonal) {
        this.documentoPersonal = documentoPersonal;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String[] getInstruccionFormal() {
        return instruccionFormal;
    }

    public void setInstruccionFormal(String[] instruccionFormal) {
        this.instruccionFormal = instruccionFormal;
    }

    public int getLugarProcedencia() {
        return lugarProcedencia;
    }

    public void setLugarProcedencia(int lugarProcedencia) {
        this.lugarProcedencia = lugarProcedencia;
    }

    public String[] getNacionalidadPersona() {
        return nacionalidadPersona;
    }

    public void setNacionalidadPersona(String[] nacionalidadPersona) {
        this.nacionalidadPersona = nacionalidadPersona;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getPer_AfiliacionIESS() {
        return per_AfiliacionIESS;
    }

    public void setPer_AfiliacionIESS(String per_AfiliacionIESS) {
        this.per_AfiliacionIESS = per_AfiliacionIESS;
    }

    public long getPer_creadoPor() {
        return per_creadoPor;
    }

    public void setPer_creadoPor(long per_creadoPor) {
        this.per_creadoPor = per_creadoPor;
    }

    public String getPer_emailAlternativo() {
        return per_emailAlternativo;
    }

    public void setPer_emailAlternativo(String per_emailAlternativo) {
        this.per_emailAlternativo = per_emailAlternativo;
    }

    public String getPer_fechaCreacion() {
        return per_fechaCreacion;
    }

    public void setPer_fechaCreacion(String per_fechaCreacion) {
        this.per_fechaCreacion = per_fechaCreacion;
    }

    public String getPer_fechaModificacion() {
        return per_fechaModificacion;
    }

    public void setPer_fechaModificacion(String per_fechaModificacion) {
        this.per_fechaModificacion = per_fechaModificacion;
    }

    public String getPer_fechaNacimiento() {
        return per_fechaNacimiento;
    }

    public void setPer_fechaNacimiento(String per_fechaNacimiento) {
        this.per_fechaNacimiento = per_fechaNacimiento;
    }

    public long getPer_modificadoPor() {
        return per_modificadoPor;
    }

    public void setPer_modificadoPor(long per_modificadoPor) {
        this.per_modificadoPor = per_modificadoPor;
    }

    public String getPer_nombres() {
        return per_nombres;
    }

    public void setPer_nombres(String per_nombres) {
        this.per_nombres = per_nombres;
    }

    public String getPer_primerApellido() {
        return per_primerApellido;
    }

    public void setPer_primerApellido(String per_primerApellido) {
        this.per_primerApellido = per_primerApellido;
    }

    public String getPer_segundoApellido() {
        return per_segundoApellido;
    }

    public void setPer_segundoApellido(String per_segundoApellido) {
        this.per_segundoApellido = per_segundoApellido;
    }

    public String getPer_procedencia() {
        return per_procedencia;
    }

    public void setPer_procedencia(String per_procedencia) {
        this.per_procedencia = per_procedencia;
    }

    public String getPer_telefonoCasa() {
        return per_telefonoCasa;
    }

    public void setPer_telefonoCasa(String per_telefonoCasa) {
        this.per_telefonoCasa = per_telefonoCasa;
    }

    public String getPer_telefonoCelular() {
        return per_telefonoCelular;
    }

    public void setPer_telefonoCelular(String per_telefonoCelular) {
        this.per_telefonoCelular = per_telefonoCelular;
    }

    public String getPer_telefonoOficina() {
        return per_telefonoOficina;
    }

    public void setPer_telefonoOficina(String per_telefonoOficina) {
        this.per_telefonoOficina = per_telefonoOficina;
    }

    public String[] getPersonaPlurinacionalidad() {
        return personaPlurinacionalidad;
    }

    public void setPersonaPlurinacionalidad(String[] personaPlurinacionalidad) {
        this.personaPlurinacionalidad = personaPlurinacionalidad;
    }

    public int getSex_id() {
        return sex_id;
    }

    public void setSex_id(int sex_id) {
        this.sex_id = sex_id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public int getTsa_id() {
        return tsa_id;
    }

    public void setTsa_id(int tsa_id) {
        this.tsa_id = tsa_id;
    }
    
    public String findDatosPersona(String cedula){
        String strJson = "";
        try {
            //SERVICIO
            URL url = new URL("http://servicioscentral.espoch.edu.ec/Central/ServiciosPersona.svc/ObtenerPorDocumento/" + cedula);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            InputStream content = (InputStream) connection.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(content, "UTF-8"));

            String line;

            
            while ((line = in.readLine()) != null) {
                strJson = line;
            }
            connection.disconnect();
        } catch (Exception ex) {
            System.err.println("com.EspochWs.Persona.findDatosPersona() " +ex);
            return "{\"error\": \"No hay datos\" ";
        }
        return strJson;
    }
    
    
    public String getRolPersona(String cedula) throws IOException{
        
        Carreras carreras = new Carreras();
                 
        
         System.err.println(carreras.getRolUsuarioCarrera(""));
         //System.err.println(carreras.getTodasCarrerasEspochWS());
        
        return "HOLA";
    }
    
}
