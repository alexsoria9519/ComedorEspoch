/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.espoch.comedorln.CostoProcedure;
import com.espoch.comedorln.Costousuario;
import com.espoch.comedorln.Response;
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
import com.EspochWs.Docente;
import com.EspochWs.Estudiante;
import com.EspochWs.Persona;

/**
 *
 * @author corebitsas
 */
@Stateless
@Path("com.espoch.comedorln.costousuario")
public class CostousuarioFacadeREST extends AbstractFacade<Costousuario> {

    @PersistenceContext(unitName = "com.mycompany_ComedorLN_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    JSONObject JSONResponse = new JSONObject();
    Response response = new Response();

    public CostousuarioFacadeREST() {
        super(Costousuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String create(Costousuario entity) {
        try {
            String res = super.create(entity);
            if (res.equals("Ingreso Correcto")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.create() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String edit(@PathParam("id") Integer id, Costousuario entity) {
        try {
            String res = super.edit(entity);
            if (res.equals("Edicion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.edit() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @DELETE
    @Path("{id}")
    public String remove(@PathParam("id") Integer id) {
        try {
            String res = super.remove(super.find(id));
            if (res.equals("Eliminacion Correcta")) {
                response.getResponse(JSONResponse, "ok", res);
            } else {
                response.getResponse(JSONResponse, "error", res);
            }

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.remove() " + ex);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Costousuario find(@PathParam("id") Integer id) {
        try {
            return super.find(id);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.find() " + ex);
            return new Costousuario();
        }
    }

    @GET
    @Path("/userExterno/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findExterno(@PathParam("cedula") String cedula) {
        System.err.println("userExterno");
        try {
            Persona persona = new Persona();
            //persona.getRolPersona(cedula);
            return persona.findDatosPersona(cedula);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findExterno() " + ex);
            return "{\"error\": \"No hay datos\" ";
        }
    }

    @GET
    @Path("/test")
    @Produces({MediaType.APPLICATION_JSON})
    public String test(@PathParam("cedula") String cedula) {
        System.err.println("userExterno");
        try {
            Persona persona = new Persona();
            return persona.getRolPersona(cedula);

        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.test() " + ex);
            return "{\"error\": \"No hay datos\" ";
        }
    }

    @GET
    @Path("/estudiante/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findSiEsEstudiante(@PathParam("cedula") String cedula) {
        try {
            Estudiante estudiante = new Estudiante();
            return estudiante.getDatosEstudiante(cedula);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findSiEsEstudiante() " + ex);
            return "{\"error\": \"No hay datos\" ";
        }
    }

    @GET
    @Path("/docente/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public String findSiEsDocente(@PathParam("cedula") String cedula) {
        try {
            Docente docente = new Docente();
            return docente.datosDocente(cedula);
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findSiEsDocente() " + ex);
            return "{\"error\": \"No hay datos\" ";
        }
    }

    @GET
    @Path("/findbystrcedula/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Costousuario> findByStrCedula(@PathParam("cedula") String cedula) {
        try {
            
            Query query = em.createNamedQuery("Costousuario.findByStrcedula");
            query.setParameter("strcedula", cedula);
            System.err.println("Result findByStrCedula" + query.getResultList().toString());
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findByStrCedula() " + ex);
            return null;
        }
    }

//    @POST
//    @Path("/tiposusuariosutilizados")
//    @Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<Costousuario> getTiposusuariosUtilizados(Costousuario entity) {
//        try {
//            Query query = em.createNamedQuery("Costousuario.tiposUsuariosUtilizados");
//            query.setParameter("intidtipo", entity.getIntidtipo().getIntidtipo());
//            return query.getResultList();
//        } catch (Exception ex) {
//            System.err.println("com.comedorln.service.CostousuarioFacadeREST.getTiposusuariosUtilizados() " + ex);
//            return null;
//        }
//    }
    @POST
    //@Path("/findByAllData/{data}")
    @Path("/findByAllData")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    //public Costousuario findByAllData(@PathParam("data") String dataJson) {
    public Costousuario findByAllData(CostoProcedure costoProcedure) {
        String datosCostoUsuario;

        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("datoscostousuario");

            storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN).setParameter(1, costoProcedure.getTipomenus());
            storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN).setParameter(2, costoProcedure.getCedula());
            storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN).setParameter(3, costoProcedure.getTipousuario());
            storedProcedure.registerStoredProcedureParameter("idcostousuario", Long.class, ParameterMode.OUT);

            storedProcedure.execute();
            datosCostoUsuario = storedProcedure.getOutputParameterValue("idcostousuario").toString();

            Query query = em.createNamedQuery("Costousuario.findByIntidcostousuario");
            query.setParameter("intidcostousuario", Integer.parseInt(datosCostoUsuario));

            return (Costousuario) query.getSingleResult();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findByAllData() " + ex);
            return null;
        }

    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Costousuario> findAll() {
        try {
            return super.findAll();
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findAll() " + ex);
            return null;
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Costousuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            return super.findRange(new int[]{from, to});
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.findRange() " + ex);
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
                response.getResponse(JSONResponse, "ok", numeroDatos.toString());
            } else {
                response.getResponse(JSONResponse, "error", "Error en el conteo");
            }
        } catch (Exception ex) {
            System.err.println("com.comedorln.service.CostousuarioFacadeREST.countREST() " + ex);
            JSONResponse.put("ok", false);
            response.getResponse(JSONResponse, "error", ex.toString());
        }
        return JSONResponse.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
