/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.servicios;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ResumenFacultadesFacadeREST
 * [com.espoch.comedorln.resumenfacultades]<br>
 * USAGE:
 * <pre>
 *        ResumenFacultadesWS client = new ResumenFacultadesWS();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author alex4
 */
public class ResumenFacultadesWS {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ComedorLN/webresources";

    public ResumenFacultadesWS() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("com.espoch.comedorln.resumenfacultades");
    }

    public String edit(Object requestEntity, String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public <T> T findRange(Class<T> responseType, String from, String to) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T listadoDatosUsuarioFacultades(Class<T> responseType, String fechaInicio, String fechaFin, String facultad) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (fechaInicio != null) {
            resource = resource.queryParam("fechaInicio", fechaInicio);
        }
        if (fechaFin != null) {
            resource = resource.queryParam("fechaFin", fechaFin);
        }
        if (facultad != null) {
            resource = resource.queryParam("facultad", facultad);
        }
        resource = resource.path("reportes/data/facultades");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String remove(String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(String.class);
    }

    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public <T> T listadoGeneros(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("generos/listado");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String create(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public <T> T listadoFacultades(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("facultades/listado");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T listadoCarrera(Class<T> responseType, String facultad) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (facultad != null) {
            resource = resource.queryParam("facultad", facultad);
        }
        resource = resource.path("carreras/listado");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T historicoDatosFacultades(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("graficos/barras/facultades");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T listadoUsuarioCarrera(Class<T> responseType, String fechaInicio, String carrera, String fechaFin) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (fechaInicio != null) {
            resource = resource.queryParam("fechaInicio", fechaInicio);
        }
        if (carrera != null) {
            resource = resource.queryParam("carrera", carrera);
        }
        if (fechaFin != null) {
            resource = resource.queryParam("fechaFin", fechaFin);
        }
        resource = resource.path("reportes/carreras");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}