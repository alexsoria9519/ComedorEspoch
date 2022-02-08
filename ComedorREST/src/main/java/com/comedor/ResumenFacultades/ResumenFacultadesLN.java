/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.ResumenFacultades;

import com.Graficos.DataUsuario;
import com.Graficos.DataUsuarios;
import com.Graficos.FacultadesCarreras;
import com.comedor.entities.ResumenFacultades;
import com.comedor.entities.ResumenesFacultades;
import com.comedor.servicios.ResumenFacultadesWS;
import com.comedor.utilidades.Utilidades;
import com.google.gson.Gson;
import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author alex4
 */
public class ResumenFacultadesLN {

    ResumenFacultadesWS resumenWS = new ResumenFacultadesWS();
    JSONObject resJson = new JSONObject();
    Gson gson = new Gson();

    public String historicoDatosMeses() {
        try {
            Integer totalDatos = 0;

            String resAll = resumenWS.historicoDatosFacultades(String.class);
            if (resAll != null && resAll != "null") {

                ResumenesFacultades dataResumenes = gson.fromJson("{ \"resumenes\" : " + resAll + " }", ResumenesFacultades.class);
                for (ResumenFacultades resumen : dataResumenes.getResumenes()) {
                    totalDatos += resumen.getEnero();
                    totalDatos += resumen.getFebrero();
                    totalDatos += resumen.getMarzo();
                    totalDatos += resumen.getAbril();
                    totalDatos += resumen.getMayo();
                    totalDatos += resumen.getJunio();
                    totalDatos += resumen.getJulio();
                    totalDatos += resumen.getAgosto();
                    totalDatos += resumen.getSeptiembre();
                    totalDatos += resumen.getOctubre();
                    totalDatos += resumen.getNoviembre();
                    totalDatos += resumen.getDiciembre();
                    resumen.setPromedio(totalDatos / 12.0);
                    resumen.setTotal(totalDatos);

                }

                resJson.put("success", "ok");
                resJson.put("detalleResumen", gson.toJson(dataResumenes));
                resJson.put("totalDatos", totalDatos);

            } else {
                resJson.put("success", "error");
                resJson.put("data", "No existen datos del gráfico");
            }

        } catch (Exception ex) {
            System.err.println("com.comedor.ResumenFacultades.ResumenFacultadesLN.historicoDatosGenero() " + ex);
            resJson.put("success", "error");
            resJson.put("detalleResumen", new ResumenesFacultades());
            resJson.put("data", "Error al obtener la cantidad de datos");
            resJson.put("totalDatos", 0);
        }
        return resJson.toString();
    }

    public String listadoUsuariosFacultad(String facultad, String fechaInicio, String fechaFin) {
        try {
            Integer totalVentas = 0;
            if (validarIntervaloFechas(fechaInicio, fechaFin)) {
                if (facultad != null && !facultad.equals("")) {
                    String resAll = resumenWS.listadoDatosUsuarioFacultades(String.class, fechaInicio, fechaFin, facultad);
                    if (resAll != null && resAll != "null") {
                        DataUsuarios dataUsuarios = gson.fromJson("{ \"dataUsuarios\" : " + resAll + " }", DataUsuarios.class);
                        for (DataUsuario data : dataUsuarios.getDataUsuarios()) {
                            totalVentas += data.getCantidadventas();
                        }
                        resJson.put("success", "ok");
                        resJson.put("detalleVenta", gson.toJson(dataUsuarios));
                        resJson.put("totalVentas", totalVentas);
                        resJson.put("totalUsuarios", dataUsuarios.getDataUsuarios().size());
                    }
                } else {
                    resJson.put("success", "error");
                    resJson.put("data", "Se debe ingresar una facultad válida");
                    resJson.put("detalleVenta", "{ \"dataUsuarios\" : [] }");
                    resJson.put("totalVentas", 0);
                    resJson.put("totalUsuarios", 0);
                }
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Se debe ingresar un intervalo de fechas válido");
                resJson.put("detalleVenta", "{ \"dataUsuarios\" : [] }");
                resJson.put("totalVentas", 0);
                resJson.put("totalUsuarios", 0);
            }

        } catch (Exception ex) {
            System.err.println("com.comedor.ResumenFacultades.ResumenFacultadesLN.listadoUsuariosFacultad() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos");
            resJson.put("detalleVenta", "{ \"dataUsuarios\" : [] }");
            resJson.put("totalVentas", 0);
            resJson.put("totalUsuarios", 0);
        }
        return resJson.toString();
    }

    public String listadoUsuariosCarrera(String carrera, String fechaInicio, String fechaFin) {
        try {
            Integer totalVentas = 0;
            if (validarIntervaloFechas(fechaInicio, fechaFin)) {
                if (carrera != null && !carrera.equals("")) {
                    String resAll = resumenWS.listadoUsuarioCarrera(String.class, fechaInicio, carrera, fechaFin);
                    if (resAll != null && resAll != "null") {
                        DataUsuarios dataUsuarios = gson.fromJson("{ \"dataUsuarios\" : " + resAll + " }", DataUsuarios.class);
                        for (DataUsuario data : dataUsuarios.getDataUsuarios()) {
                            totalVentas += data.getCantidadventas();
                        }
                        resJson.put("success", "ok");
                        resJson.put("detalleVenta", gson.toJson(dataUsuarios));
                        resJson.put("totalVentas", totalVentas);
                        resJson.put("totalUsuarios", dataUsuarios.getDataUsuarios().size());
                    }
                } else {
                    resJson.put("success", "error");
                    resJson.put("data", "Se debe ingresar una carrera válida");
                    resJson.put("detalleVenta", "{ \"dataUsuarios\" : [] }");
                    resJson.put("totalVentas", 0);
                    resJson.put("totalUsuarios", 0);
                }
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Se debe ingresar un intervalo de fechas válido");
                resJson.put("detalleVenta", "{ \"dataUsuarios\" : [] }");
                resJson.put("totalVentas", 0);
                resJson.put("totalUsuarios", 0);
            }

        } catch (Exception ex) {
            System.err.println("com.comedor.ResumenFacultades.ResumenFacultadesLN.listadoUsuariosCarrera() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos");
            resJson.put("detalleVenta", "{ \"dataUsuarios\" : [] }");
            resJson.put("totalVentas", 0);
            resJson.put("totalUsuarios", 0);
        }
        return resJson.toString();
    }

    private Boolean validarIntervaloFechas(String fechaInicio, String fechaFin) {
        Utilidades utilidades = new Utilidades();
        return (utilidades.validarFecha("yyyy-MM-dd", fechaInicio) && utilidades.validarFecha("yyyy-MM-dd", fechaFin));
    }

    public String listadoFacultades() {
        try {

            String resAll = resumenWS.listadoFacultades(String.class);
            if (resAll != null && resAll != "null") {
                FacultadesCarreras facultadesCarreras = gson.fromJson("{ \"facultadesCarreras\" : " + resAll + " }", FacultadesCarreras.class);
                resJson.put("success", "ok");
                resJson.put("detalleVenta", gson.toJson(facultadesCarreras));
                resJson.put("cantidadFacultades", facultadesCarreras.getFacultadesCarreras().size());

            } else {
                resJson.put("success", "error");
                resJson.put("data", "Error al obtener los datos");
            }

        } catch (Exception ex) {
            System.out.println("com.comedor.ResumenFacultades.ResumenFacultadesLN.listadoFacultades() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos");
            resJson.put("detalleVenta", new FacultadesCarreras());
            resJson.put("cantidadFacultades", 0);
        }
        return resJson.toString();
    }

    public String listadoCarreras(String facultad) {
        try {

            String resAll = resumenWS.listadoCarrera(String.class, facultad);
            if (resAll != null && resAll != "null") {
                FacultadesCarreras facultadesCarreras = gson.fromJson("{ \"facultadesCarreras\" : " + resAll + " }", FacultadesCarreras.class);
                resJson.put("success", "ok");
                resJson.put("detalleVenta", gson.toJson(facultadesCarreras));
                resJson.put("cantidadCarreras", facultadesCarreras.getFacultadesCarreras().size());

            } else {
                resJson.put("success", "error");
                resJson.put("data", "No existen datos del reportes");
            }

        } catch (Exception ex) {
            System.err.println("com.comedor.ResumenFacultades.ResumenFacultadesLN.listadoCarreras() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos");
            resJson.put("detalleVenta", new FacultadesCarreras());
            resJson.put("cantidadFacultades", 0);
        }
        return resJson.toString();
    }
}
