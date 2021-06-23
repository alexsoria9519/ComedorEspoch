/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.espoch.comedorln.Menu;
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
@Path("com.espoch.comedorln.menu")
public class MenuFacadeREST extends AbstractFacade<Menu> {

    @PersistenceContext(unitName = "com.espoch_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    JSONObject JSONResponse = new JSONObject();

    public MenuFacadeREST() {
        super(Menu.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(Menu entity) {
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
            System.err.println("com.comedorln.service.MenuFacadeREST.create() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @POST
    @Path("/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    public String ingreso(Menu entity) {
        Integer codRespuesta = 0;
        try {
            String sqlInsert = "INSERT INTO menu( intidtipomenu, strcaracteristicas, blnestado) VALUES "
                    + "(" + entity.getIntidtipomenu().getIntidtipo() + ", '" + entity.getStrcaracteristicas() + "', " + entity.getBlnestado() + ") RETURNING intidmenu; ";
            Query query = em.createNativeQuery(sqlInsert);
            codRespuesta = (Integer) query.getSingleResult();
            JSONResponse.put("ok", true);
            JSONResponse.put("data", codRespuesta);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.MenuFacadeREST.ingreso() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", codRespuesta);
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, Menu entity) {
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
            System.err.println("com.comedorln.service.MenuFacadeREST.edit() " + ex);
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
            System.err.println("com.comedorln.service.MenuFacadeREST.remove() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Menu find(@PathParam("id") Integer id) {
        try {
        return super.find(id);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostoFacadeREST.find() " + ex);
            return null;
    }
    }

    @PUT
    @Path("/updateestado/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public String editEstado(@PathParam("id") Integer id, Menu entity) {
        try {
            Query query = em.createNamedQuery("Menu.updateByBlnestado");
            query.setParameter("intidmenu", entity.getIntidmenu());
            System.err.println(entity.getBlnestado());
            query.setParameter("blnestado", entity.getBlnestado());
            query.executeUpdate();
            JSONResponse.put("ok", true);
            JSONResponse.put("data", "Modificacion Correcta");
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.MenuFacadeREST.editEstado() " + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", ex);
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("/findbycaracteristicas/{caracteristica}")
    @Produces({MediaType.APPLICATION_JSON})
    public Menu findByStrCaracteristicas(@PathParam("caracteristica") String strCarateristicas) {
        try {
            Query query = em.createNamedQuery("Menu.findByStrcaracteristicas");
            query.setParameter("strcaracteristicas", strCarateristicas);
            return (Menu) query.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("com.comedorln.service.MenuFacadeREST.findByStrCaracteristicas() " + ex);
            return new Menu();
        }
    }

    @POST
    @Path("/findbycaracteristicasTipo")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Menu findByCaracteristicasTipo(Menu entity) {

        try {
            Query query = em.createNamedQuery("Menu.findByStrCaracteristicasTipo");
            query.setParameter("strcaracteristicas", entity.getStrcaracteristicas());
            query.setParameter("intidtipo", entity.getIntidtipomenu());
            return (Menu) query.getSingleResult();

        } catch (NoResultException ex) {
            System.err.println("com.comedorln.service.MenuFacadeREST.findByCaracteristicasTipo() " + ex);
            return null;
        }
    }

    @GET
    @Path("/menusactivos/")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Menu> findByBlnestado() {
        try {
            Query query = em.createNamedQuery("Menu.findByBlnestado");
            query.setParameter("blnestado", true);
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.MenuFacadeREST.findByBlnestado() " + ex);
            return null;
        }
    }

    @POST
    @Path("/tiposmenusutilizados/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public List<Menu> findByTipoMenu(Menu entity) {
        try {
            System.err.println("findByTipoMenuWS " + entity.getIntidtipomenu().getIntidtipo());
            Query query = em.createNamedQuery("Menu.tiposUtilizados");
            query.setParameter("intidtipomenu", entity.getIntidtipomenu().getIntidtipo());
            // Query query = em.createQuery("SELECT COUNT(m.strcaracteristicas) FROM Menu m JOIN m.intidtipo t WHERE t.intidtipo = :intidtipo");
//        Query query = em.createQuery("SELECT COUNT(m.strcaracteristicas) FROM Menu m JOIN m.intidtipo t WHERE t.intidtipo = 1");
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.MenuFacadeREST.findByTipoMenu() " + ex);
            return null;
        }
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Menu> findAll() {
        try {
        return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.MenuFacadeREST.findAll() " + ex);
            return null;
    }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Menu> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
        return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.out.println("com.comedorln.service.MenuFacadeREST.findRange() " + ex);
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
