/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.espoch.comedorln.Costousuario;
import com.espoch.comedorln.Menu;
import com.espoch.comedorln.Planificacionmenu;
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
 * @author alex4
 */
@Stateless
@Path("com.espoch.comedorln.planificacionmenu")
public class PlanificacionmenuFacadeREST extends AbstractFacade<Planificacionmenu> {

    @PersistenceContext(unitName = "com.espoch_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    JSONObject JSONResponse = new JSONObject();

    public PlanificacionmenuFacadeREST() {
        super(Planificacionmenu.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(Planificacionmenu entity) {
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
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.create() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @POST
    @Path("/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    public String ingreso(Planificacionmenu entity) {
        Integer codRespuesta = 0;
        try {
            String sqlInsert = "INSERT INTO planificacionmenu(intidmenu, dtfechainicio, dtfechafin)\n"
                    + "	VALUES (" + entity.getIntidmenu().getIntidmenu() + ", '" + entity.getDtfechainicio() + "', '" + entity.getDtfechafin() + "' ) returning intid;";
            System.err.println("sqlInsert" + sqlInsert);
            Query query = em.createNativeQuery(sqlInsert);
            codRespuesta = (Integer) query.getSingleResult();
            JSONResponse.put("ok", true);
            JSONResponse.put("data", codRespuesta);

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.ingreso() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", codRespuesta);
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, Planificacionmenu entity) {
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
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.edit() " + ex);
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
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.remove()");
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Planificacionmenu find(@PathParam("id") Integer id) {
        try {
            Planificacionmenu planificacionMenu = super.find(id);
            return (planificacionMenu == null ? new Planificacionmenu() : planificacionMenu);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.find() " + ex);
            return new Planificacionmenu();
        }
    }

    @GET
    @Path("/listmenusfechas/{idMenu}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Planificacionmenu> listPlanificacionMenuByIdMenu(@PathParam("idMenu") Integer idMenu) {
        try {
            Menu menu = new Menu();
            menu.setIntidmenu(idMenu);
            Planificacionmenu entity = new Planificacionmenu();
            entity.setIntidmenu(menu);
            Query query = em.createNamedQuery("Planificacionmenu.findByIdMenu");
            query.setParameter("intidmenu", entity.getIntidmenu().getIntidmenu());
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.listMenusByFecha() " + ex);
            return null;
        }
    }

    @GET
    @Path("/listmenusfechas/")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Planificacionmenu> listMenusByFecha() {
        try {
            Query query = em.createQuery("SELECT p FROM Planificacionmenu p WHERE current_date >= p.dtfechainicio AND current_date <= p.dtfechafin");
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.listMenusByFecha() " + ex);
            return null;
        }
    }

    @POST
    @Path("/listplanificacionintervalos/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Planificacionmenu listMenusFechasIntervalo(Planificacionmenu entity) {

        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("get_numeros_planificacion_fecha");

            storedProcedure.registerStoredProcedureParameter(1, Date.class, ParameterMode.IN).setParameter(1, entity.getDtfechainicio());
            storedProcedure.registerStoredProcedureParameter(2, Date.class, ParameterMode.IN).setParameter(2, entity.getDtfechafin());
            storedProcedure.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN).setParameter(3, entity.getIntidmenu().getIntidmenu());
            storedProcedure.registerStoredProcedureParameter("countregistros", Long.class, ParameterMode.OUT);

            storedProcedure.execute();

            Integer idPlanificacionMenu = (Integer) storedProcedure.getOutputParameterValue("countregistros");

            if (idPlanificacionMenu > 0) {
                Planificacionmenu planificacionMenu = super.find(idPlanificacionMenu);
                return planificacionMenu;
            } else {
                return new Planificacionmenu();
            }

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findByAllData() " + ex);
            return new Planificacionmenu();
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Planificacionmenu> findAll() {
        try {
            return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.findAll() " + ex);
            return null;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Planificacionmenu> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.PlanificacionmenuFacadeREST.findRange() " + ex);
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
            System.err.println("com.comedorln.service.CostoFacadeREST.countREST() " + ex);
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
