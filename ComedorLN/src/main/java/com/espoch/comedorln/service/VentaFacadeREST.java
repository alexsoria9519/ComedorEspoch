/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.espoch.comedorln.Venta;
import com.espoch.comedorln.VentaProcedure;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
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
 * @author corebitsas
 */
@Stateless
@Path("com.espoch.comedorln.venta")
public class VentaFacadeREST extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "com.mycompany_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    JSONObject JSONResponse = new JSONObject();

    public VentaFacadeREST() {
        super(Venta.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(Venta entity) {
        try {
            String res = super.create(entity);
            if (res.equals("Ingreso Correcto")) {
                JSONResponse.put("ok", true);
                JSONResponse.put("data", res);
            } else {
                JSONResponse.put("ok", false);
                JSONResponse.put("error", res);
            }
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.VentaFacadeREST.create() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, Venta entity) {
        try {
            String res = super.edit(entity);
            if (res.equals("Ingreso Correcto")) {
                JSONResponse.put("ok", true);
                JSONResponse.put("data", res);
            } else {
                JSONResponse.put("ok", false);
                JSONResponse.put("error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.VentaFacadeREST.edit() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") Integer id) {
        try {
            String res = super.remove(super.find(id));
            if (res.equals("Eliminacion Correcta")) {
                JSONResponse.put("ok", true);
                JSONResponse.put("data", res);
            } else {
                JSONResponse.put("ok", false);
                JSONResponse.put("error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostoFacadeREST.remove() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Venta find(@PathParam("id") Integer id) {
        try {
            return super.find(id);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.VentaFacadeREST.find() " + ex);
            return null;
        }
    }

    @POST
    @Path("/ventaporfecha")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String ventasPorFecha(VentaProcedure ventaProcedure) {
        JSONObject resultJSON = new JSONObject();
        try {
            String resultadoProcedure;

            ventaProcedure.setAccion("cantidad");
            resultadoProcedure = ventaProcedure(ventaProcedure);
            if (!resultadoProcedure.equals("Se produjo un error en el procedimiento almacenado") && !resultadoProcedure.equals("0")) {
                resultJSON.put("cantidad", Double.parseDouble(ventaProcedure(ventaProcedure)));
                ventaProcedure.setAccion("valor");
                resultadoProcedure = ventaProcedure(ventaProcedure);
                if (!resultadoProcedure.equals("Se produjo un error en el procedimiento almacenado") && !resultadoProcedure.isEmpty()) {
                    resultJSON.put("valor", Double.parseDouble(ventaProcedure(ventaProcedure)));
                    resultJSON.put("detalle", ventaProcedure.getTipomenu() + " " + ventaProcedure.getTipousuario());
                } else {
                    resultJSON.put("mensaje", resultadoProcedure);
                }
            } else {
                resultJSON.put("mensaje", "No existen datos");
            }
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.VentaFacadeREST.ventasPorFecha() " + ex);
            resultJSON.put("mensaje", "Se produjo un error en el procedimiento almacenado");
        }
        return resultJSON.toString();
    }

    private String ventaProcedure(VentaProcedure ventaProcedure) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("datosventafecha");
            storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN).setParameter(1, ventaProcedure.getTipomenu());
            storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN).setParameter(2, ventaProcedure.getTipousuario());
            storedProcedure.registerStoredProcedureParameter(3, Date.class, ParameterMode.IN).setParameter(3, ventaProcedure.getFecha());
            storedProcedure.registerStoredProcedureParameter(4, String.class, ParameterMode.IN).setParameter(4, ventaProcedure.getAccion());
            storedProcedure.registerStoredProcedureParameter("respuesta", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.execute();
            return storedProcedure.getOutputParameterValue("respuesta").toString();
        } catch (Exception e) {
            System.err.println("com.comedorln.service.VentaFacadeREST.ventaProcedure() " + e);
            return "Se produjo un error en el procedimiento almacenado";
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Venta> findAll() {
        try {

            return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.VentaFacadeREST.findAll() " + ex);
            return null;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Venta> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.VentaFacadeREST.findRange() " + ex);
            return null;
        }
    }

    @POST
    //@Path("/findByAllData/{data}")
    @Path("/buscarFecha")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Venta> findByFecha(Venta entity) {
        try {
            Query query = em.createNamedQuery("Venta.findByDtfecha");
            query.setParameter("dtfecha", entity.getDtfecha());
            return query.getResultList();
        } catch (Exception ex) {
            System.out.println("com.comedorln.service.VentaFacadeREST.findByFecha() " + ex);
            return null;
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            Integer numeroDatos = super.count();

            if (numeroDatos != -1) {
                JSONResponse.put("ok", true);
                JSONResponse.put("data", numeroDatos);
            } else {
                JSONResponse.put("ok", false);
                JSONResponse.put("error", "Error en el conteo");
            }
        } catch (Exception ex) {
            System.out.println("com.comedorln.service.VentaFacadeREST.countREST() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", "Error en el conteo");
        }
        return JSONResponse.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
