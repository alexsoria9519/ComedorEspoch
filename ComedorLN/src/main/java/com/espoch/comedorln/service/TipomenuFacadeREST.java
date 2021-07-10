/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.espoch.comedorln.Response;
import com.espoch.comedorln.Tipomenu;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
@Stateless
@Path("com.espoch.comedorln.tipomenu")
public class TipomenuFacadeREST extends AbstractFacade<Tipomenu> {

    @PersistenceContext(unitName = "com.espoch_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    JSONObject JSONResponse = new JSONObject();
    Response response = new Response();

    public TipomenuFacadeREST() {
        super(Tipomenu.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String create(Tipomenu entity) {
        try {
            String res = super.create(entity);
            if (res.equals("Ingreso Correcto")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.TipomenuFacadeREST.create() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, Tipomenu entity) {
        try {
            String res = super.edit(entity);
            if (res.equals("Edicion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.TipomenuFacadeREST.edit() " + ex);
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
            System.err.println("com.comedorln.service.TipomenuFacadeREST.remove() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Tipomenu find(@PathParam("id") Integer id) {
        try {
            Tipomenu tipo = super.find(id);
            return (tipo == null ? new Tipomenu() : tipo);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.TipomenuFacadeREST.find() " + ex);
            return new Tipomenu();
        }
    }

    @GET
    @Path("/findbytipo/{tipo}")
    @Produces({MediaType.APPLICATION_JSON})
    public Tipomenu findByStrTipo(@PathParam("tipo") String strTipo) {
        try {
            Query query = em.createNamedQuery("Tipomenu.findByStrtipo");
            query.setParameter("strtipo", strTipo);
            return (Tipomenu) query.getSingleResult();

        } catch (NoResultException ex) {
            System.err.println("com.comedorln.service.TipomenuFacadeREST.findByStrTipo() " + ex);
            return new Tipomenu();
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Tipomenu> findAll() {
        try {
            return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.TipomenuFacadeREST.findAll() " + ex);
            return new ArrayList<>();
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Tipomenu> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.TipomenuFacadeREST.findRange() " + ex);
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
            System.err.println("com.comedorln.service.CostoFacadeREST.countREST() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
