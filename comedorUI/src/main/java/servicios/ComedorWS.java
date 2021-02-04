/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ComedorResource [comedor]<br>
 * USAGE:
 * <pre>
 *        ComedorWS client = new ComedorWS();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author corebitsas
 */
public class ComedorWS {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/ComedorREST/webresources";

    public ComedorWS() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("comedor");
    }

    public String insertCostoUsuario(Object requestEntity) throws ClientErrorException {
        return webTarget.path("costoUsuario/ingreso").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String getTipoUsuarioByTipo(String tipo) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("tipousuario/tipo/{0}", new Object[]{tipo}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String insertCosto(Object requestEntity) throws ClientErrorException {
        return webTarget.path("costos/ingreso").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String getCosto(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("costos/getcosto/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String eliminarTipoMenu(String idTipo, String tipoEliminacion) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("tipomenus/eliminar/{0}/{1}", new Object[]{idTipo, tipoEliminacion})).request().delete(String.class);
    }

    public String getPlanificacionMenusByIdMenu(String idMenu) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("planificacionmenus/menu/{0}", new Object[]{idMenu}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getListadoTiposMenus() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("tipomenus/todos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getTipoMenuByTipo(String tipo) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("tipomenus/bytipo/{0}", new Object[]{tipo}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getListadoCostos() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("costos/todos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String eliminarCosto(String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("costos/eliminar/{0}", new Object[]{id})).request().delete(String.class);
    }

    public String getListadoTiposUsuarios() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("tipousuario/todos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getTipoUsuario(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("tipousuario/gettipo/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String editarTipoUsuario(Object requestEntity) throws ClientErrorException {
        return webTarget.path("tipousuario/editar").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String getMenu(String idMenu) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idMenu != null) {
            resource = resource.queryParam("idMenu", idMenu);
        }
        resource = resource.path("menus/getmenu");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getTipoMenu(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("tipomenus/gettipo/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String eliminarTipoUsuario(String id, String tipoEliminacion) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("tipousuario/eliminar/{0}/{1}", new Object[]{id, tipoEliminacion})).request().delete(String.class);
    }

    public String getPlanificacionMenusFechaActual() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("planificacionmenus/fechaactual");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String insertPlanificacionMenu(Object requestEntity) throws ClientErrorException {
        return webTarget.path("planificacionmenus/ingreso").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String getCostoByDetalle(String detalle) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("costos/costo/{0}", new Object[]{detalle}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String editarTipoMenu(Object requestEntity) throws ClientErrorException {
        return webTarget.path("tipomenus/editar").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String editarCosto(Object requestEntity) throws ClientErrorException {
        return webTarget.path("costos/editar").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String insertMenu(Object requestEntity) throws ClientErrorException {
        return webTarget.path("menus/ingreso").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String insertTipoUsuario(Object requestEntity) throws ClientErrorException {
        return webTarget.path("tipousuario/ingreso").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String cambiarEstadoMenu(Object requestEntity) throws ClientErrorException {
        return webTarget.path("menus/cambiarEstado").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String editarMenu(Object requestEntity) throws ClientErrorException {
        return webTarget.path("menus/editar").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String eliminarMenu(String idMenu) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("menus/eliminar/{0}", new Object[]{idMenu})).request().delete(String.class);
    }

    public String desactivarPlanificacionMenu(String idPlanificacion) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("planificacionmenus/eliminar/{0}", new Object[]{idPlanificacion})).request().delete(String.class);
    }

    public String eliminarCostoUsuario() throws ClientErrorException {
        return webTarget.path("costoUsuario/eliminar").request().delete(String.class);
    }

    public String getListadoMenus() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("menus/todos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String activarDesactivarCosto(Object requestEntity, String idCosto, String estado) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("costos/editarestado/{0}/{1}", new Object[]{idCosto, estado})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String getListadoCostosUsuarios() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("costoUsuario/todos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getCostoUsuario(String idCostoUsuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (idCostoUsuario != null) {
            resource = resource.queryParam("idCostoUsuario", idCostoUsuario);
        }
        resource = resource.path("costoUsuario/getcostousuario");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String insertTipoMenu(Object requestEntity) throws ClientErrorException {
        return webTarget.path("tipomenus/ingreso").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String getPlanificacionMenus() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("planificacionmenus/todos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
