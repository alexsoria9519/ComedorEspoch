/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Venta;

import com.comedor.CostoUsuario.CostoUsuarioLN;
import com.comedor.entities.VentaProcedure;
import com.comedor.entities.Ventas;
import com.comedor.entities.VentasProcedure;
import com.comedor.servicios.VentaWS;
import com.comedor.utilidades.CedulaIdentidad;
import com.comedor.utilidades.Utilidades;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author corebitsas
 */
public class VentasLN {
    
    VentaWS ventaWS = new VentaWS();
    JSONObject resJson = new JSONObject();
    Gson gson = new Gson();
    
    public VentasLN() {
    }
    
    public String getTodasVentas() {
        String resAll = "";
        
        try {
            resAll = ventaWS.findAll(String.class);
            Ventas ventas = gson.fromJson("{ \"ventas\" : " + resAll + " }", Ventas.class);
            resJson.put("ventas", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", ventas.getVentas().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }
    
    public String getventasUsuarioFecha(String cedula, String fecha) {
        String resAll = "";
        try {
            resAll = ventaWS.ventaUsuarioCedula(String.class, fecha, cedula);
            Ventas ventas = gson.fromJson("{ \"ventas\" : " + resAll + " }", Ventas.class);
            resJson.put("ventas", resAll);
            resJson.put("success", "ok");
            resJson.put("cantidad", ventas.getVentas().size());
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }
    
    public String tieneReservasUsuario(String cedula) {
        CedulaIdentidad cedulaIdentidad = new CedulaIdentidad();
        String resAll;
        try {
            String resCedula = cedulaIdentidad.validarCedula(cedula);
            JSONObject jsonResCed = new JSONObject(resCedula);
            if (!jsonResCed.getBoolean("valido")) {
                resJson.put("success", "validacion");
                resJson.put("data", jsonResCed.getString("data"));
            } else {
                
                resAll = ventaWS.reservaVentaCedula(String.class, cedula);
                Ventas ventas = gson.fromJson("{ \"ventas\" : " + resAll + " }", Ventas.class);
                resJson.put("ventas", resAll);
                resJson.put("success", "ok");
                resJson.put("cantidad", ventas.getVentas().size());
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el listado");
        }
        return resJson.toString();
    }
    
    public String datosVentasDiarias(String fecha) {
        String resAll;
        Double sumaVentas = 0.0;
        Integer cantidadVentasDia = 0;
        try {
            resAll = ventaWS.datosVentaDiaria(String.class, fecha);
            
            VentasProcedure ventasDiarias = gson.fromJson("{ \"ventasProcedure\" : " + resAll + " }", VentasProcedure.class);
            
            if (ventasDiarias.getVentasProcedure().size() > 0) {
                
                for (VentaProcedure ventasdiariaCosto : ventasDiarias.getVentasProcedure()) {
                    sumaVentas += ventasdiariaCosto.getTotal().doubleValue();
                    cantidadVentasDia += ventasdiariaCosto.getCantidadvendidos();
                }
                resJson.put("ventasDiarias", resAll);
                resJson.put("success", "ok");
                resJson.put("totalVentas", sumaVentas);
                resJson.put("cantidadVentas", cantidadVentasDia);
                
            } else {
                resJson.put("ventas", "[]");
                resJson.put("success", "ok");
                resJson.put("totalVentas", 0.0);
                resJson.put("cantidadVentas", 0);
                
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.datosVentasDiarias() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos de las ventas del día");
        }
        return resJson.toString();
    }
    
    public String datosVentasIntervaloFechas(String fechaInicio, String fechaFin) {
        String resAll;
        Double sumaVentas = 0.0;
        Integer cantidadVentasIntervalo = 0;
        Utilidades utilidades = new Utilidades();
        try {
            
            if (utilidades.validarFecha("yyyy-mm-dd", fechaInicio) && utilidades.validarFecha("yyyy-mm-dd", fechaFin)) {
                resAll = ventaWS.datosVentasIntervaloFechas(String.class, fechaInicio, fechaFin);
                VentasProcedure ventasDiarias = gson.fromJson("{ \"ventasProcedure\" : " + resAll + " }", VentasProcedure.class);
                
                if (ventasDiarias.getVentasProcedure().size() > 0) {
                    
                    for (VentaProcedure ventasdiariaCosto : ventasDiarias.getVentasProcedure()) {
                        sumaVentas += ventasdiariaCosto.getTotal().doubleValue();
                        cantidadVentasIntervalo += ventasdiariaCosto.getCantidadvendidos();
                    }
                    resJson.put("ventasDiarias", resAll);
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", sumaVentas);
                    resJson.put("cantidadVentas", cantidadVentasIntervalo);
                    
                } else {
                    resJson.put("ventas", "[]");
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", 0.0);
                    resJson.put("cantidadVentas", 0);
                    
                }
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Se debe ingresar fechas válidas");
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.datosVentasIntervaloFechas() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos de las ventas del día");
        }
        return resJson.toString();
    }
    
    public String datosVentasIntervaloFechasMenu(String fechaInicio, String fechaFin, Integer idTipoMenu) {
        String resAll;
        Double sumaVentas = 0.0;
        Integer cantidadVentasIntervalo = 0;
        Utilidades utilidades = new Utilidades();
        try {
            
            if (utilidades.validarFecha("yyyy-mm-dd", fechaInicio) && utilidades.validarFecha("yyyy-mm-dd", fechaFin)) {
                resAll = ventaWS.datosVentasIntervaloFechasMenu(String.class, fechaInicio, idTipoMenu.toString(), fechaFin);
                VentasProcedure ventasDiarias = gson.fromJson("{ \"ventasProcedure\" : " + resAll + " }", VentasProcedure.class);
                
                if (ventasDiarias.getVentasProcedure().size() > 0) {
                    
                    for (VentaProcedure ventasdiariaCosto : ventasDiarias.getVentasProcedure()) {
                        sumaVentas += ventasdiariaCosto.getTotal().doubleValue();
                        cantidadVentasIntervalo += ventasdiariaCosto.getCantidadvendidos();
                    }
                    resJson.put("ventasDiarias", resAll);
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", sumaVentas);
                    resJson.put("cantidadVentas", cantidadVentasIntervalo);
                    
                } else {
                    resJson.put("ventas", "[]");
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", 0.0);
                    resJson.put("cantidadVentas", 0);
                    
                }
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Se debe ingresar fechas válidas");
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.datosVentasIntervaloFechasMenu() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos de las ventas del día");
        }
        return resJson.toString();
    }
    
    public String datosVentasIntervaloFechasUsuario(String fechaInicio, String fechaFin, Integer idTipoUsuario) {
        String resAll;
        Double sumaVentas = 0.0;
        Integer cantidadVentasIntervalo = 0;
        Utilidades utilidades = new Utilidades();
        try {
            
            if (utilidades.validarFecha("yyyy-mm-dd", fechaInicio) && utilidades.validarFecha("yyyy-mm-dd", fechaFin)) {
                resAll = ventaWS.datosVentasIntervaloFechasUsuario(String.class, idTipoUsuario.toString(), fechaInicio, fechaFin);
                VentasProcedure ventasDiarias = gson.fromJson("{ \"ventasProcedure\" : " + resAll + " }", VentasProcedure.class);
                
                if (ventasDiarias.getVentasProcedure().size() > 0) {
                    
                    for (VentaProcedure ventasdiariaCosto : ventasDiarias.getVentasProcedure()) {
                        sumaVentas += ventasdiariaCosto.getTotal().doubleValue();
                        cantidadVentasIntervalo += ventasdiariaCosto.getCantidadvendidos();
                    }
                    resJson.put("ventasDiarias", resAll);
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", sumaVentas);
                    resJson.put("cantidadVentas", cantidadVentasIntervalo);
                    
                } else {
                    resJson.put("ventas", "[]");
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", 0.0);
                    resJson.put("cantidadVentas", 0);
                    
                }
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Se debe ingresar fechas válidas");
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.datosVentasIntervaloFechasMenu() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos de las ventas del día");
        }
        return resJson.toString();
    }
    
    public String datosVentasUsuario(String cedulaUsuario) {
        String resAll;
        Double sumaVentas = 0.0;
        Integer cantidadVentasIntervalo = 0;
        try {
            CedulaIdentidad cedula = new CedulaIdentidad();
            resAll = cedula.validarCedula(cedulaUsuario);
            System.err.println("Cedula " + resAll);
            
            JSONObject respCedula = new JSONObject(resAll);
            
            if (respCedula.getBoolean("valido")) {
                resAll = ventaWS.dataVentasUsuario(String.class, cedulaUsuario);
                VentasProcedure ventasUsuario = gson.fromJson("{ \"ventasProcedure\" : " + resAll + " }", VentasProcedure.class);
                
                if (ventasUsuario.getVentasProcedure().size() > 0) {
                    
                    for (VentaProcedure ventasdiariaCosto : ventasUsuario.getVentasProcedure()) {
                        sumaVentas += ventasdiariaCosto.getTotal().doubleValue();
                        cantidadVentasIntervalo += ventasdiariaCosto.getCantidadvendidos();
                    }
                    resJson.put("ventasDiarias", resAll);
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", sumaVentas);
                    resJson.put("cantidadVentas", cantidadVentasIntervalo);
                } else {
                    resJson.put("ventas", "[]");
                    resJson.put("success", "ok");
                    resJson.put("totalVentas", 0.0);
                    resJson.put("cantidadVentas", 0);
                }
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Se debe ingresar un número de cédula válido");
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.datosVentasUsuario() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos de las ventas del día");
        }
        return resJson.toString();
    }
    
    public String datosVentasUsuarioFechas(String cedulaUsuario, String fechaInicio, String fechaFin) {
        String resAll;
        Double sumaVentas = 0.0;
        Integer cantidadVentasIntervalo = 0;
        try {
            CedulaIdentidad cedula = new CedulaIdentidad();
            CostoUsuarioLN costoUsuarioLN = new CostoUsuarioLN();
            resAll = cedula.validarCedula(cedulaUsuario);
            System.err.println("Cedula " + resAll);
            
            JSONObject respCedula = new JSONObject(resAll);
            
            if (respCedula.getBoolean("valido")) {
                if (validarIntervaloFechas(fechaInicio, fechaFin)) {
                    resAll = ventaWS.dataVentasUsuarioFechas(String.class, fechaInicio, cedulaUsuario, fechaFin);
                    VentasProcedure ventasUsuario = gson.fromJson("{ \"ventasProcedure\" : " + resAll + " }", VentasProcedure.class);
                    resJson.put("ventasDiarias", resAll);
                    if (ventasUsuario.getVentasProcedure().size() > 0) {
                        for (VentaProcedure ventasdiariaCosto : ventasUsuario.getVentasProcedure()) {
                            sumaVentas += ventasdiariaCosto.getTotal().doubleValue();
                            cantidadVentasIntervalo += ventasdiariaCosto.getCantidadvendidos();
                        }
                        resAll = costoUsuarioLN.datosPersona(cedulaUsuario);
                        resJson.put("datosPersona", resAll);
                        resJson.put("success", "ok");
                        resJson.put("totalVentas", sumaVentas);
                        resJson.put("cantidadVentas", cantidadVentasIntervalo);
                    } else {
                        resJson.put("ventas", "[]");
                        resJson.put("success", "ok");
                        resJson.put("totalVentas", 0.0);
                        resJson.put("cantidadVentas", 0);
                        resJson.put("datosPersona", "{}");
                    }
                } else {
                    resJson.put("success", "error");
                    resJson.put("data", "Se debe ingresar un intervalo de fechas válido");
                }
            } else {
                resJson.put("success", "error");
                resJson.put("data", "Se debe ingresar un número de cédula válido");
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.datosVentasUsuario() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener los datos de las ventas del usuario");
        }
        return resJson.toString();
    }
    
    public String valorVentasDia(String fecha) {
        try {
            
            String resAll = ventaWS.valorVentaDia(String.class, fecha);
            System.out.println("com.comedor.Venta.VentasLN.valorVentasDia  resAll " + resAll);
            if (resAll != null && resAll != "null") {
                BigDecimal valorVentas = new BigDecimal(resAll);
                Double iva = valorVentas.doubleValue() * 0.12;
                resJson.put("success", "ok");
                resJson.put("valorVentas", to2Decimal(valorVentas.doubleValue()));
                resJson.put("IVA", to2Decimal(iva));
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.valorVentasDia() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener el total de ventas del día");
            resJson.put("valorVentas", 0.00);
            resJson.put("IVA", 0.00);
        }
        return resJson.toString();
    }
    
    public String cantidadVentasDetalladosDia(String fecha) {
        try {
            Utilidades utilidades = new Utilidades();
            Integer totalVentasDia = 0;
            if (utilidades.validarFecha("yyyy-MM-dd", fecha)) {
                String resAll = ventaWS.cantidadTicketsDias(String.class, fecha);
                if (resAll != null && resAll != "null") {
                    VentasProcedure ventasUsuario = gson.fromJson("{ \"ventasProcedure\" : " + resAll + " }", VentasProcedure.class);
                    for (VentaProcedure ventaProcedure : ventasUsuario.getVentasProcedure()) {
                        totalVentasDia += ventaProcedure.getCantidadvendidos();
                    }
                    resJson.put("success", "ok");
                    resJson.put("detalleVenta", gson.toJson(ventasUsuario));
                    resJson.put("totalVentas", totalVentasDia);
                }
            } else {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar una fecha válida");
                resJson.put("total", 0);
            }
            
        } catch (Exception ex) {
            System.err.println("com.comedor.Venta.VentasLN.cantidadVentasDetalladosDia() " + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al obtener la cantidad de datos");
            resJson.put("total", 0);
        }
        return resJson.toString();
    }
    
    private Boolean validarIntervaloFechas(String fechaInicio, String fechaFin) {
        Utilidades utilidades = new Utilidades();
        return (utilidades.validarFecha("yyyy-MM-dd", fechaInicio) && utilidades.validarFecha("yyyy-MM-dd", fechaFin));
    }

//    Double toDoubleRounded(Double value) {
//        try {
//            BigDecimal bigDecimal = new BigDecimal(value);
//            bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
//            System.err.println("Valor del decimal " + bigDecimal.toString());
//            return bigDecimal.doubleValue();
//        } catch (Exception ex) {
//            System.err.println("com.comedorui.ReporteVentasUI.toDoubleRounded() " + ex);
//            return 0.0;
//        }
//    }
    private String to2Decimal(Double value) {
        DecimalFormat dc = new DecimalFormat("0.00");
        return dc.format(value);
    }
    
}
