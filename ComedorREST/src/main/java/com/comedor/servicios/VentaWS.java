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
 * Jersey REST client generated for REST resource:VentaFacadeREST
 * [com.espoch.comedorln.venta]<br>
 * USAGE:
 * <pre>
 *        VentaWS client = new VentaWS();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author alex4
 */
public class VentaWS {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ComedorLN/webresources";

    public VentaWS() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("com.espoch.comedorln.venta");
    }

    public <T> T ventaUsuarioCedula(Class<T> responseType, String fecha, String cedula) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (fecha != null) {
            resource = resource.queryParam("fecha", fecha);
        }
        if (cedula != null) {
            resource = resource.queryParam("cedula", cedula);
        }
        resource = resource.path("find/venta/usuario/fecha");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T reservaVentaCedula(Class<T> responseType, String cedula) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("find/reserva/{0}", new Object[]{cedula}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String edit(Object requestEntity, String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public <T> T findRange(Class<T> responseType, String from, String to) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String insert(Object requestEntity) throws ClientErrorException {
        return webTarget.path("insert").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String ventasPorFecha(Object requestEntity) throws ClientErrorException {
        return webTarget.path("ventaporfecha").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public <T> T datosVentaDiaria(Class<T> responseType, String fecha) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("data/diarios/{0}", new Object[]{fecha}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findAll(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String remove(String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(String.class);
    }

    public <T> T datosVentasIntervaloFechasUsuario(Class<T> responseType, String idTipoUsuario, String fechaInicio, String fechaFin) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idTipoUsuario != null) {
            resource = resource.queryParam("idTipoUsuario", idTipoUsuario);
        }
        if (fechaInicio != null) {
            resource = resource.queryParam("fechaInicio", fechaInicio);
        }
        if (fechaFin != null) {
            resource = resource.queryParam("fechaFin", fechaFin);
        }
        resource = resource.path("data/intervalo/usuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T datosVentasUsuario(Class<T> responseType, String cedula) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (cedula != null) {
            resource = resource.queryParam("cedula", cedula);
        }
        resource = resource.path("data/usuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String countREST() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("count");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T findByFecha(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.path("buscarFecha").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public <T> T datosVentasIntervaloFechasMenu(Class<T> responseType, String fechaInicio, String idTipoMenu, String fechaFin) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (fechaInicio != null) {
            resource = resource.queryParam("fechaInicio", fechaInicio);
        }
        if (idTipoMenu != null) {
            resource = resource.queryParam("idTipoMenu", idTipoMenu);
        }
        if (fechaFin != null) {
            resource = resource.queryParam("fechaFin", fechaFin);
        }
        resource = resource.path("data/intervalo/menu");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String create(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public <T> T datosVentasIntervaloFechas(Class<T> responseType, String fechaInicio, String fechaFin) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (fechaInicio != null) {
            resource = resource.queryParam("fechaInicio", fechaInicio);
        }
        if (fechaFin != null) {
            resource = resource.queryParam("fechaFin", fechaFin);
        }
        resource = resource.path("data/intervalo");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
