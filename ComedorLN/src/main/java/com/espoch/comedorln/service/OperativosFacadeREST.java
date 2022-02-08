/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.espoch.comedorln.Operativos;
import com.espoch.comedorln.Response;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
@Stateless
@Path("com.espoch.comedorln.operativos")
public class OperativosFacadeREST extends AbstractFacade<Operativos> {

    @PersistenceContext(unitName = "com.espoch_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    JSONObject JSONResponse = new JSONObject();
    Response response = new Response();

    public OperativosFacadeREST() {
        super(Operativos.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String create(Operativos entity) {
        try {
            String res = super.create(entity);
            if (res.equals("Ingreso Correcto")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.OperativosFacadeREST.create() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, Operativos entity) {
        try {
            String res = super.edit(entity);
            if (res.equals("Edicion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.OperativosFacadeREST.edit() " + ex);
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
            System.err.println("com.espoch.comedorln.service.OperativosFacadeREST.remove() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Operativos find(@PathParam("id") Integer id) {
        try {
            Operativos operativos = super.find(id);
            return (operativos == null ? new Operativos() : operativos);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.OperativosFacadeREST.find() " + ex);
            return new Operativos();
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Operativos> findAll() {
        try {
            return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.OperativosFacadeREST.findAll() " + ex);
            return new ArrayList<>();
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Operativos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.OperativosFacadeREST.findRange() " + ex);
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
            System.err.println("com.espoch.comedorln.service.OperativosFacadeREST.countREST() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
