/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.EspochWs.FacultadCarrera;
import com.Graficos.DataUsuario;
import com.espoch.comedorln.Response;
import com.espoch.comedorln.ResumenFacultades;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
@Stateless
@Path("com.espoch.comedorln.resumenfacultades")
public class ResumenFacultadesFacadeREST extends AbstractFacade<ResumenFacultades> {

    @PersistenceContext(unitName = "com.espoch_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    JSONObject JSONResponse = new JSONObject();
    Response response = new Response();

    public ResumenFacultadesFacadeREST() {
        super(ResumenFacultades.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String create(ResumenFacultades entity) {
        try {
            String res = super.create(entity);
            if (res.equals("Ingreso Correcto")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.create() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, ResumenFacultades entity) {
        try {
            String res = super.edit(entity);
            if (res.equals("Edicion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.edit() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String remove(@PathParam("id") Integer id) {
        try {
            String res = super.remove(super.find(id));
            if (res.equals("Eliminacion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.remove() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ResumenFacultades find(@PathParam("id") Integer id) {
        try {
            ResumenFacultades resumen = super.find(id);
            return (resumen == null ? new ResumenFacultades() : resumen);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.find() " + ex);
            return new ResumenFacultades();
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<ResumenFacultades> findAll() {
        try {
            return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.findAll() " + ex);
            return new ArrayList<>();
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ResumenFacultades> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.findRange() " + ex);
            return new ArrayList<>();
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public String countREST() {
        try {
            Integer numeroDatos = super.count();

            if (numeroDatos != -1) {
                response.getResponse(JSONResponse, "ok", numeroDatos.toString());
            } else {
                response.getResponse(JSONResponse, "error", "Error en el conteo");
            }
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.countREST() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    //SELECT DISTINCT genero FROM persons;
    @GET
    @Path("/graficos/barras/facultades")
    @Produces({MediaType.APPLICATION_JSON})
    public List<ResumenFacultades> historicoDatosFacultades() {
        try {
            ResumenFacultades resumen = new ResumenFacultades();
            String sql = "SELECT * FROM resumen_facultades ORDER BY ID DESC LIMIT (SELECT DISTINCT COUNT(codigo) FROM facultades_carreras WHERE idpadre IS NULL);";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return resumen.convertirLista(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.historicoDatosFacultades() " + ex);
            return null;
        }
    }

    @GET
    @Path("/reportes/data/facultades")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DataUsuario> listadoDatosUsuarioFacultades(
            @QueryParam("facultad") String facultad,
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin
    ) {
        try {
            DataUsuario data = new DataUsuario();
            String sql = "SELECT * FROM public.datos_usuarios_facultades('" + facultad + "', '" + fechaInicio + "', '" + fechaFin + "');";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return data.convertirListaConteo(dataList);
        } catch (Exception ex) {
            System.out.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.listadoDatosUsuarioFacultades() " + ex);
            return null;
        }
    }

    @GET
    @Path("/reportes/carreras")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DataUsuario> listadoUsuarioCarrera(
            @QueryParam("carrera") String carrera,
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin
    ) {
        try {
            DataUsuario data = new DataUsuario();
            String sql = "SELECT * FROM public.datos_usuarios_carreras('" + carrera + "', '" + fechaInicio + "', '" + fechaFin + "');";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return data.convertirListaConteo(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.listadoUsuarioCarrera() " + ex);
            return null;
        }
    }

    @GET
    @Path("/facultades/listado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<FacultadCarrera> listadoFacultades() {
        try {
            FacultadCarrera fac = new FacultadCarrera();
            String sql = "SELECT DISTINCT * FROM facultades_carreras WHERE idpadre IS NULL;";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return fac.convertirListaConteoFacultad(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.listadoFacultades() " + ex);
            return null;
        }
    }

    @GET
    @Path("/carreras/listado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<FacultadCarrera> listadoCarrera(
            @QueryParam("facultad") String facultad
    ) {
        try {
            FacultadCarrera fac = new FacultadCarrera();
            String sql = "SELECT DISTINCT * FROM facultades_carreras WHERE idpadre = '" + facultad + "';";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return fac.convertirListaConteoFacultad(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.listadoFacultades() " + ex);
            return null;
        }
    }

    @GET
    @Path("/generos/listado")
    @Produces({MediaType.APPLICATION_JSON})
    public List<String> listadoGeneros() {
        try {
            String sql = "SELECT DISTINCT genero FROM persons;";
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.ResumenFacultadesFacadeREST.listadoGeneros() " + ex);
            return null;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
