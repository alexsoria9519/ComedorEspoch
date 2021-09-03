/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln.service;

import com.Graficos.DataFecha;
import com.espoch.comedorln.Costousuario;
import com.espoch.comedorln.Venta;
import com.espoch.comedorln.VentaProcedure;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
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
@Path("com.espoch.comedorln.venta")
public class VentaFacadeREST extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "com.espoch_ComedorLN_war_1.0-SNAPSHOTPU")
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

    @POST
    @Path("insert")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insert(Venta entity) {
        Integer codRespuesta = 0;
        try {
            String pattern = "yyyy-MM-dd";
            DateFormat df = new SimpleDateFormat(pattern);

            String date = df.format(entity.getDtfecha());
            String sqlInsert = "INSERT INTO venta(blnestado, blnreserva, dtfecha, intcantidad, intidcostousuario)\n"
                    + "	VALUES (" + entity.getBlnestado() + ", " + entity.getBlnreserva() + ", '" + date
                    + "', " + entity.getIntcantidad() + ", " + entity.getIntidcostousuario().getIntidcostousuario() + ") RETURNING intidventa;";
            Query query = em.createNativeQuery(sqlInsert);
            codRespuesta = (Integer) query.getSingleResult();
            JSONResponse.put("ok", true);
            JSONResponse.put("data", codRespuesta);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.insert()" + ex);
            JSONResponse.put("ok", false);
            JSONResponse.put("error", codRespuesta);
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
    @Path("/data/diarios/{fecha}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VentaProcedure> datosVentaDiaria(@PathParam("fecha") String fecha) {
        try {
            VentaProcedure datosVenta = new VentaProcedure();
            Query query = em.createNativeQuery("SELECT * FROM datos_venta_dia('" + fecha + "');");
            List<Object[]> dataList = query.getResultList();
            return datosVenta.convertirLista(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.datosVentaDiaria() " + ex);
            return null;
        }
    }

    @GET
    @Path("/data/intervalo")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VentaProcedure> datosVentasIntervaloFechas(@QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin) {
        try {
            VentaProcedure datosVenta = new VentaProcedure();
            String sql = "SELECT * FROM datos_ventas_intervalo_fechas('" + fechaInicio + "','" + fechaFin + "');";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return datosVenta.convertirLista(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.datosVentaDiaria() " + ex);
            return null;
        }
    }

    @GET
    @Path("/data/intervalo/menu")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VentaProcedure> datosVentasIntervaloFechasMenu(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin,
            @QueryParam("idTipoMenu") Integer idTipoMenu) {
        try {
            VentaProcedure datosVenta = new VentaProcedure();
            String sql = "SELECT * FROM datos_ventas_tipo_menu('" + fechaInicio + "','" + fechaFin + "', " + idTipoMenu + ");";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return datosVenta.convertirLista(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.datosVentasIntervaloFechasMenu() " + ex);
            return null;
        }
    }

    @GET
    @Path("/data/usuario/fechas")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VentaProcedure> dataVentasUsuarioFechas(
            @QueryParam("cedula") String cedula,
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin) {
        try {
            VentaProcedure datosVenta = new VentaProcedure();
            String sql = "SELECT * FROM public.datos_ventas_usuario_fechas(\n"
                    + "	'" + cedula + "', \n"
                    + "	'" + fechaInicio + "', \n"
                    + "	'" + fechaFin + "'\n"
                    + ")";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return datosVenta.convertirLista(dataList);
        } catch (Exception ex) {
            System.out.println("com.espoch.comedorln.service.VentaFacadeREST.datosVentasUsuario() " + ex);
            return null;
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

    @GET
    @Path("find/reserva/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Venta> reservaVentaCedula(@PathParam("cedula") String cedula) {
        try {
            String sql = "SELECT v FROM Venta v JOIN v.intidcostousuario c  WHERE c.strcedula = '" + cedula + "' AND v.blnreserva = :blnreserva";
            TypedQuery<Venta> query = em.createQuery(sql, Venta.class);
            query.setParameter("blnreserva", true);
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.reservaVentaCedula() " + ex);
            return null;
        }
    }

    @GET
    @Path("find/venta/usuario/fecha")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Venta> ventaUsuarioCedula(
            @QueryParam("cedula") String cedula,
            @QueryParam("fecha") String fecha) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            String sql = "SELECT v FROM Venta v JOIN v.intidcostousuario c  WHERE c.strcedula = '" + cedula + "' AND v.dtfecha = :dtfecha";
            TypedQuery<Venta> query = em.createQuery(sql, Venta.class);
            query.setParameter("dtfecha", date);
            return query.getResultList();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.ventaUsuarioCedula() " + ex);
            return null;
        }
    }

    @GET
    @Path("/data/intervalo/usuario")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VentaProcedure> datosVentasIntervaloFechasUsuario(
            @QueryParam("fechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin,
            @QueryParam("idTipoUsuario") Integer idTipoUsuario) {
        try {
            VentaProcedure datosVenta = new VentaProcedure();
            String sql = "SELECT * FROM datos_ventas_tipo_usuario('" + fechaInicio + "','" + fechaFin + "', " + idTipoUsuario + ");";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return datosVenta.convertirLista(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.datosVentasIntervaloFechasUsuario() " + ex);
            return null;
        }
    }

    @GET
    @Path("/data/usuario")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VentaProcedure> dataVentasUsuario(
            @QueryParam("cedula") String cedula) {
        try {
            VentaProcedure datosVenta = new VentaProcedure();
            String sql = "SELECT * FROM public.datos_ventas_usuario('" + cedula + "');";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return datosVenta.convertirLista(dataList);
        } catch (Exception ex) {
            System.out.println("com.espoch.comedorln.service.VentaFacadeREST.datosVentasUsuario() " + ex);
            return null;
        }
    }

    @GET
    @Path("/graficos/fechas")
    @Produces({MediaType.APPLICATION_JSON})
    public List<DataFecha> datosGraficosIntervaloFechas(
            @QueryParam("FechaInicio") String fechaInicio,
            @QueryParam("fechaFin") String fechaFin
    ) {
        try {
            DataFecha dataFecha = new DataFecha();
            String sql = "SELECT DATE_TRUNC('month',dtfecha) AS fecha,\n"
                    + "COUNT(*) AS numventas\n"
                    + "FROM venta\n"
                    + " WHERE dtfecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'\n"
                    + " GROUP BY 1\n"
                    + " ORDER BY 1";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return dataFecha.convertirLista(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.datosGraficosIntervaloFechas() " + ex);
            return null;
        }
    }

    @GET
    @Path("/graficos/ventasDia")
    @Produces({MediaType.TEXT_PLAIN})
    public BigDecimal valorVentaDia(
            @QueryParam("fecha") String fecha
    ) {
        try {
            String sql = "SELECT CASE WHEN SUM(co.mnvalor) > 0 THEN SUM(co.mnvalor)\n"
                    + "         ELSE 0.00 END\n"
                    + "         AS valor_venta\n"
                    + "FROM venta v, costousuario cu, costo co \n"
                    + "WHERE v.intidcostousuario = cu.intidcostousuario AND cu.intidcosto = co.intidcosto\n"
                    + "AND v.dtfecha = '" + fecha + "';";
            Query query = em.createNativeQuery(sql);
            return (BigDecimal) query.getSingleResult();
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.valorVentaDia() " + ex);
            return null;
        }
    }

    @GET
    @Path("/graficos/tickets/dia")
    @Produces({MediaType.APPLICATION_JSON})
    public List<VentaProcedure> cantidadTicketsDias(
            @QueryParam("fecha") String fecha
    ) {
        try {
            VentaProcedure datosVenta = new VentaProcedure();
//            String sql = "SELECT * FROM conteo_ventas_menu";
            String sql = "SELECT * FROM contar_ventas_dia_tipo_menu('" + fecha + "');";
            Query query = em.createNativeQuery(sql);
            List<Object[]> dataList = query.getResultList();
            return datosVenta.convertirListaConteo(dataList);
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.service.VentaFacadeREST.cantidadTicketsDias() " + ex);
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
