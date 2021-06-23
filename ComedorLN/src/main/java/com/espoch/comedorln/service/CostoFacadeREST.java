/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.espoch.comedorln.Costo;
import com.espoch.comedorln.Response;
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
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
@Stateless
@Path("com.espoch.comedorln.costo")
public class CostoFacadeREST extends AbstractFacade<Costo> {

    @PersistenceContext(unitName = "com.espoch_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    JSONObject JSONResponse = new JSONObject();
    Response response = new Response();

    public CostoFacadeREST() {
        super(Costo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String create(Costo entity) {
//        JSONObject JSONResponse = new JSONObject();
        try {
            String res = super.create(entity);
            if (res.equals("Ingreso Correcto")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }
        } catch (Exception ex) {
            System.out.println("com.espoch.comedorln.service.CostoFacadeREST.create() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, Costo entity) {
        try {
            String res = super.edit(entity);
            if (res.equals("Edicion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.edit() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String remove(@PathParam("id") Integer id) {
//        JSONObject JSONResponse = new JSONObject();
        try {
            String res = super.remove(super.find(id));
            if (res.equals("Eliminacion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.remove() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Costo find(@PathParam("id") Integer id) {
        try {
            Costo costo = super.find(id);
            return (costo == null ? new Costo() : costo);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.find() " + ex);
            return new Costo();
        }
    }

    @GET
    @Path("costo/{detalle}")
    @Produces({MediaType.APPLICATION_JSON})
    public Costo findByStrDetalle(@PathParam("detalle") String strDetalle) {
        try {
            Query query = em.createNamedQuery("Costo.findByStrdetalle");
            query.setParameter("strdetalle", strDetalle);
            return (Costo) query.getSingleResult();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.findByStrDetalle() " + ex);
            return new Costo();
        }

    }

    @PUT
    @Path("/updateestado/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String editEstado(@PathParam("id") Integer id, Costo entity) {
//        JSONObject JSONResponse = new JSONObject();
        try {
            Query query = em.createNamedQuery("Costo.updateByBlnestado");
            query.setParameter("intidcosto", entity.getIntidcosto());
            query.setParameter("blnestado", entity.getBlnestado());
            query.executeUpdate();
            response.getResponse(JSONResponse, "ok", "Modificacion Correcta");
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.editEstado() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("costo/like/{strDetalle}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Costo> tipoLikeDetalle(@PathParam("strDetalle") String strDetalle) {
//        JSONObject JSONResponse = new JSONObject();
        try {
            Query query = em.createQuery("SELECT c FROM Costo c WHERE c.strdetalle LIKE '%" + strDetalle + "%'");
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.tipoLikeDetalle() " + ex);
            return new ArrayList<>();
        }
    }

    @POST
    @Path("/tiposusuariosutilizados")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Costo> getTiposUsuariosUtilizados(Costo entity) {
        try {
            Query query = em.createNamedQuery("Costo.tiposUsuariosUtilizados");
            query.setParameter("intidtipousuario", entity.getIntidtipousuario().getIntidtipo());
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.getTiposUsuariosUtilizados() " + ex);
            return null;
        }
    }

    @POST
    @Path("/tiposmenusutilizados")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Costo> getTiposMenusUtilizados(Costo entity) {

        System.err.println("Costo getTiposMenusUtilizados");

        try {
            Query query = em.createNamedQuery("Costo.tiposMenusUtilizados");
            query.setParameter("intidtipomenu", entity.getIntidtipomenu().getIntidtipo());
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println("com.espoch.comedorln.service.CostoFacadeREST.getTiposMenusUtilizados() " + ex);
            return null;
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Costo> findAll() {
        try {
            return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.findAll() " + ex);
            return new ArrayList<>();
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Costo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.findRange() " + ex);
            return new ArrayList<>();
        }
    }

    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON})
    public String countREST() {
        try {
            Integer numeroDatos = super.count();

            if (numeroDatos != -1) {
                response.getResponse(JSONResponse, "ok", numeroDatos.toString());
            } else {
                response.getResponse(JSONResponse, "error", "Error en el conteo");
            }
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.CostoFacadeREST.countREST() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
