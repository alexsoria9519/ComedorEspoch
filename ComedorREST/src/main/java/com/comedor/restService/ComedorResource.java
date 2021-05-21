/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.restService;

import com.comedor.CostoUsuario.CostoUsuarioLN;
import com.comedor.CostoUsuario.CostosUsuariosLN;
import com.comedor.Costos.CostoLN;
import com.comedor.Costos.CostosLN;
import com.comedor.Menu.MenuLN;
import com.comedor.Menu.MenusLN;
import com.comedor.PlanificacionMenu.PlanificacionMenuLN;
import com.comedor.PlanificacionMenu.PlanificacionesMenusLN;
import com.comedor.TipoMenu.TipoMenuLN;
import com.comedor.TipoMenu.TiposMenusLN;
import com.comedor.TipoUsuario.TipoUsuarioLN;
import com.comedor.TipoUsuario.TiposUsuariosLN;
import com.comedor.Venta.VentaLN;
import com.comedor.Venta.VentasLN;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author COREBITSAS
 */
@Path("comedor")
public class ComedorResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ComedorResource
     */
    public ComedorResource() {
    }

    @GET
    @Path("tipousuario/todos")
    @Produces({MediaType.APPLICATION_JSON})
    public String getListadoTiposUsuarios() {
        TiposUsuariosLN tiposUsuariosLn = new TiposUsuariosLN();
        return tiposUsuariosLn.getTiposUsuarios();
    }

    @POST
    @Path("tipousuario/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insertTipoUsuario(String jsonData) {
        TipoUsuarioLN tipoUsuarioLn = new TipoUsuarioLN();
        return tipoUsuarioLn.insertTipoUsuario(jsonData);
    }

    @GET
    @Path("tipousuario/gettipo/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTipoUsuario(
            @PathParam("id") Integer idTipo
    ) {
        TipoUsuarioLN tipoUsuarioLn = new TipoUsuarioLN();
        return tipoUsuarioLn.getTipoUsuario(idTipo);
    }

    @GET
    @Path("tipousuario/tipo/{tipo}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTipoUsuarioByTipo(
            @PathParam("tipo") String strTipo
    ) {
        TipoUsuarioLN tipoUsuarioLn = new TipoUsuarioLN();
        return tipoUsuarioLn.BuscarPorTipo(strTipo);
    }

    @PUT
    @Path("tipousuario/editar")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String editarTipoUsuario(String jsonData) {
        TipoUsuarioLN tipoUsuarioLn = new TipoUsuarioLN();
        return tipoUsuarioLn.updateTipoUsuario(jsonData);
    }

    @DELETE
    @Path("tipousuario/eliminar/{id}/{tipoEliminacion}")
    @Produces({MediaType.APPLICATION_JSON})
    public String eliminarTipoUsuario(
            @PathParam("id") Integer idTipo,
            @PathParam("tipoEliminacion") String tipoEliminacion
    ) {
        TipoUsuarioLN tipoUsuarioLn = new TipoUsuarioLN();
        return tipoUsuarioLn.deleteTipoUsuario(idTipo, tipoEliminacion);
    }

    /**
     * ****** Tipos Usuarios ****
     *////
    /**
     * ****** Tipos Menu ****
     *////
    @GET
    @Path("tipomenus/todos")
    @Produces({MediaType.APPLICATION_JSON})
    public String getListadoTiposMenus() {
        TiposMenusLN tiposMenusLn = new TiposMenusLN();
        return tiposMenusLn.getTiposMenus();
    }

    @POST
    @Path("tipomenus/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insertTipoMenu(String jsonData) {
        TipoMenuLN tipoMenuLn = new TipoMenuLN();
        return tipoMenuLn.insertTipoMenu(jsonData);
    }

    @GET
    @Path("tipomenus/gettipo/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTipoMenu(
            @PathParam("id") Integer idTipo
    ) {
        TipoMenuLN tipoMenuLn = new TipoMenuLN();
        return tipoMenuLn.getTipoMenu(idTipo);
    }

    @GET
    @Path("tipomenus/bytipo/{tipo}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTipoMenuByTipo(
            @PathParam("tipo") String tipo
    ) {
        TipoMenuLN tipoMenuLn = new TipoMenuLN();
        return tipoMenuLn.getTipoMenuByTipo(tipo);
    }

    @PUT
    @Path("tipomenus/editar")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String editarTipoMenu(String jsonData) {
        TipoMenuLN tipoMenuLn = new TipoMenuLN();
        return tipoMenuLn.updateTipoMenu(jsonData);
    }

    @DELETE
    @Path("tipomenus/eliminar/{idTipo}/{tipoEliminacion}")
    @Produces({MediaType.APPLICATION_JSON})
    public String eliminarTipoMenu(
            @PathParam("idTipo") Integer idTipo,
            @PathParam("tipoEliminacion") String tipo
    ) {
        TipoMenuLN tipoMenuLn = new TipoMenuLN();
        return tipoMenuLn.deleteTipoMenu(idTipo, tipo);
    }

    /**
     * ****** Tipos Menu ****
     *////
    /**
     * ****** Costos ****
     *////
    @GET
    @Path("costos/todos")
    @Produces({MediaType.APPLICATION_JSON})
    public String getListadoCostos() {
        CostosLN costosLn = new CostosLN();
        return costosLn.getCostos();
    }

    @POST
    @Path("costos/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insertCosto(String jsonData) {
        CostoLN costoLn = new CostoLN();
        return costoLn.insertCosto(jsonData);
    }

    @GET
    @Path("costos/getcosto/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCosto(
            @PathParam("id") Integer idTipo
    ) {
        CostoLN costoLn = new CostoLN();
        return costoLn.getCosto(idTipo);
    }

    @GET
    @Path("costos/costo/{detalle}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCostoByDetalle(
            @PathParam("detalle") String detalle
    ) {
        CostoLN costoLn = new CostoLN();
        return costoLn.getCostoByDetalle(detalle);
    }

    @PUT
    @Path("costos/editar")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String editarCosto(String jsonData) {
        CostoLN costoLn = new CostoLN();
        return costoLn.updateCosto(jsonData);
    }

    @PUT
    @Path("costos/editarestado/{idCosto}/{estado}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String activarDesactivarCosto(
            @PathParam("idCosto") Integer idCosto,
            @PathParam("estado") String estado
    ) {
        CostoLN costoLn = new CostoLN();
        return costoLn.activarDesactivarCosto(idCosto, estado);
    }

    @DELETE
    @Path("costos/eliminar/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String eliminarCosto(
            @PathParam("id") Integer idTipo
    ) {
        CostoLN costoLn = new CostoLN();
        return costoLn.deleteCosto(idTipo);
    }

    /**
     * ****** Costos ****
     *////
    /**
     * ****** Costos Usuarios****
     *////
    @GET
    @Path("costoUsuario/todos")
    @Produces({MediaType.APPLICATION_JSON})
    public String getListadoCostosUsuarios() {
        CostosUsuariosLN costosUsuariosLn = new CostosUsuariosLN();
        return costosUsuariosLn.getCostosUsuarios();
    }

    @POST
    @Path("costoUsuario/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insertCostoUsuario(String jsonData) throws Exception {
        CostoUsuarioLN costoUsuarioLn = new CostoUsuarioLN();
        return costoUsuarioLn.insertCostoUsuario(jsonData);
    }

    @GET
    @Path("costoUsuario/getcostousuario")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCostoUsuario(
            @QueryParam("idCostoUsuario") Integer idTipo
    ) {
        CostoUsuarioLN costoUsuarioLn = new CostoUsuarioLN();
        return costoUsuarioLn.getCostoUsuario(idTipo);
    }

    @DELETE
    @Path("costoUsuario/eliminar")
    @Produces({MediaType.APPLICATION_JSON})
    public String eliminarCostoUsuario(
            @QueryParam("idCostoUsuario") Integer idTipo
    ) {
        CostoUsuarioLN costoUsuarioLn = new CostoUsuarioLN();
        return costoUsuarioLn.deleteCostoUsuario(idTipo);
    }

    /**
     * ****** Costos Usuarios****
     *////
    /**
     * ****** Menus ******
     *////
    @GET
    @Path("menus/todos")
    @Produces({MediaType.APPLICATION_JSON})
    public String getListadoMenus() {
        MenusLN menusLn = new MenusLN();
        return menusLn.getMenus();
    }

    @POST
    @Path("menus/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insertMenu(String jsonData) throws Exception {
        MenuLN menuLn = new MenuLN();
        return menuLn.insertMenu(jsonData);
    }

    @GET
    @Path("menus/getmenu")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMenu(
            @QueryParam("idMenu") Integer idTipo
    ) {
        MenuLN menuLn = new MenuLN();
        return menuLn.getMenu(idTipo);
    }

    @PUT
    @Path("menus/editar")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String editarMenu(String jsonData) {
        MenuLN menuLn = new MenuLN();
        return menuLn.updateMenu(jsonData);
    }

    @PUT
    @Path("menus/cambiarEstado")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String cambiarEstadoMenu(String jsonData) {
        MenuLN menuLn = new MenuLN();
        return menuLn.cambiarEstadoMenu(jsonData);
    }

    @DELETE
    @Path("menus/eliminar/{idMenu}")
    @Produces({MediaType.APPLICATION_JSON})
    public String eliminarMenu(@PathParam("idMenu") Integer idMenu) {
        MenuLN menuLn = new MenuLN();
        return menuLn.deleteMenu(idMenu);
    }

    /**
     * ****** Menus ******
     *////
    /**
     * ****** Planificacion Menus ******
     *////
    @GET
    @Path("planificacionmenus/todos")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPlanificacionMenus() {
        PlanificacionesMenusLN planificacionesLn = new PlanificacionesMenusLN();
        return planificacionesLn.getPlanificacionesMenus();
    }

    @POST
    @Path("planificacionmenus/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insertPlanificacionMenu(String jsonData) throws Exception {
        PlanificacionMenuLN planificacionMenu = new PlanificacionMenuLN();
        return planificacionMenu.insertPlanificacion(jsonData);
    }

    @GET
    @Path("planificacionmenus/fechaactual")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPlanificacionMenusFechaActual() {
        PlanificacionesMenusLN planificacionesLn = new PlanificacionesMenusLN();
        return planificacionesLn.getFechasPlanificacionesByFechaActual();
    }

    @DELETE
    @Path("planificacionmenus/eliminar/{idPlanificacion}")
    @Produces({MediaType.APPLICATION_JSON})
    public String desactivarPlanificacionMenu(@PathParam("idPlanificacion") Integer idPlanificacion) {
        PlanificacionMenuLN planificacionMenu = new PlanificacionMenuLN();
        return planificacionMenu.desactivarPlanificacionMenu(idPlanificacion);
    }

    @GET
    @Path("planificacionmenus/menu/{idMenu}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPlanificacionMenusByIdMenu(@PathParam("idMenu") Integer idMenu) {
        PlanificacionesMenusLN planificacionesLn = new PlanificacionesMenusLN();
        return planificacionesLn.getFechasPlanificacionesByIdMenu(idMenu);
    }

    /**
     * ****** Planificacion Menus ******
     *////
    /**
     * ****** Costo Usuario ******
     *////
    @GET
    @Path("costousuarios/validar/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public String validarCedulaUsuario(@PathParam("idMenu") Integer idMenu) {
        PlanificacionesMenusLN planificacionesLn = new PlanificacionesMenusLN();
        return planificacionesLn.getFechasPlanificacionesByIdMenu(idMenu);
    }

    @GET
    @Path("costousuario/costos/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public String costosUsuario(@PathParam("cedula") String cedula) {
        CostoUsuarioLN costousuarioLN = new CostoUsuarioLN();
        return costousuarioLN.costosUsuarioCedula(cedula);
    }

    @GET
    @Path("costousuario/{idCostoUsuario}")
    @Produces({MediaType.APPLICATION_JSON})
    public String buscarCostoUsuarioID(@PathParam("idCostoUsuario") Integer idCostoUsuario) {
        CostoUsuarioLN costousuarioLN = new CostoUsuarioLN();
        return costousuarioLN.getCostoUsuario(idCostoUsuario);
    }

    @GET
    @Path("ventas/todas")
    @Produces({MediaType.APPLICATION_JSON})
    public String listadoTodasVentas() {
        VentasLN ventasLN = new VentasLN();
        return ventasLN.getTodasVentas();
    }

    @GET
    @Path("venta/formulario/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public String datosFormularioVenta(@PathParam("cedula") String cedula) {
        VentaLN ventaLN = new VentaLN();
        return ventaLN.getDatosFormularioVenta(cedula);
    }

    @GET
    @Path("venta/reservas/{cedula}")
    @Produces({MediaType.APPLICATION_JSON})
    public String reservasUsuario(@PathParam("cedula") String cedula) {
        VentasLN ventasLN = new VentasLN();
        return ventasLN.tieneReservasUsuario(cedula);
    }

    @POST
    @Path("venta/ingreso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String insertVenta(String jsonData) throws Exception {
        VentaLN ventaLN = new VentaLN();
        return ventaLN.insertVenta(jsonData);
    }

    @GET
    @Path("ventas/diarias/{fecha}")
    @Produces({MediaType.APPLICATION_JSON})
    public String listadoVentasDiarias(@PathParam("fecha") String fecha) {
        VentasLN ventasLN = new VentasLN();
        return ventasLN.datosVentasDiarias(fecha);
    }

    @GET
    @Path("ventas/intervalofechas")
    @Produces({MediaType.APPLICATION_JSON})
    public String listadoVentasIntervaloFechas(@QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin) {
        VentasLN ventasLN = new VentasLN();
        return ventasLN.datosVentasIntervaloFechas(fechaInicio, fechaFin);
    }
    
    @GET
    @Path("ventas/intervalofechas/menu")
    @Produces({MediaType.APPLICATION_JSON})
    public String listadoVentasIntervaloFechasMenu(
            @QueryParam("fechaInicio") String fechaInicio, 
            @QueryParam("fechaFin") String fechaFin,
            @QueryParam("idTipoMenu") Integer idTipoMenu) {
        VentasLN ventasLN = new VentasLN();
        return ventasLN.datosVentasIntervaloFechasMenu(fechaInicio, fechaFin, idTipoMenu);
    }

}
