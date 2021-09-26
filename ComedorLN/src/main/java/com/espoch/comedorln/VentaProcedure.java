/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author corebitsas
 */
@XmlRootElement
public class VentaProcedure implements Serializable {

    @XmlElement
    private String tipomenu;
    @XmlElement
    private String tipousuario;
    @XmlElement
    private String accion;
    @XmlElement
    private Date fecha;
    @XmlElement
    private Integer cantidad;
    @XmlElement
    private Double valor;
    @XmlElement
    private String mensaje;

    @XmlElement
    private Integer idcostousuario;
    @XmlElement
    private String nombrecostousuario;
    @XmlElement
    private Integer cantidadvendidos;
    @XmlElement
    private Long totalTicketsVendidos;
    @XmlElement
    private BigDecimal costounitario;
    @XmlElement
    private BigDecimal total;
    @XmlElement
    private Date fechaventa;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipomenu() {
        return tipomenu;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void setTipomenu(String tipomenu) {
        this.tipomenu = tipomenu;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getIdcostousuario() {
        return idcostousuario;
    }

    public void setIdcostousuario(Integer idcostousuario) {
        this.idcostousuario = idcostousuario;
    }

    public String getNombrecostousuario() {
        return nombrecostousuario;
    }

    public void setNombrecostousuario(String nombrecostousuario) {
        this.nombrecostousuario = nombrecostousuario;
    }

    public Integer getCantidadvendidos() {
        return cantidadvendidos;
    }

    public void setCantidadvendidos(Integer cantidadvendidos) {
        this.cantidadvendidos = cantidadvendidos;
    }

    public BigDecimal getCostounitario() {
        return costounitario;
    }

    public void setCostounitario(BigDecimal costounitario) {
        this.costounitario = costounitario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        this.fechaventa = fechaventa;
    }

    public Long getTotalTicketsVendidos() {
        return totalTicketsVendidos;
    }

    public void setTotalTicketsVendidos(Long totalTicketsVendidos) {
        this.totalTicketsVendidos = totalTicketsVendidos;
    }

    public List<VentaProcedure> convertirLista(List<Object[]> dataList) {
        List<VentaProcedure> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                VentaProcedure datosVenta = new VentaProcedure();
                String nombreCostoUsuario = (String) object[0];
                Integer cantidadVendidosDia = (Integer) object[1];
                BigDecimal costoUnitario = (BigDecimal) object[2];
                BigDecimal valorTotalVenta = (BigDecimal) object[3];

                datosVenta.setNombrecostousuario(nombreCostoUsuario);
                datosVenta.setCantidadvendidos(cantidadVendidosDia);
                datosVenta.setCostounitario(costoUnitario);
                datosVenta.setTotal(valorTotalVenta);
                result.add(datosVenta);
            }
            return result;
        } catch (Exception ex) {
            System.out.println("com.espoch.comedorln.VentaProcedure.convertirLista() " + ex);
            return null;
        }
    }

    public List<VentaProcedure> convertirListaConteo(List<Object[]> dataList) {
        List<VentaProcedure> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                VentaProcedure datosVenta = new VentaProcedure();
                String nombreCostoUsuario = (String) object[0];
                Integer cantidadVendidosDia = (Integer) object[1];

                datosVenta.setNombrecostousuario(nombreCostoUsuario);
                datosVenta.setCantidadvendidos(cantidadVendidosDia);
                result.add(datosVenta);
            }
            return result;
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.VentaProcedure.convertirListaConteo() " + ex);
            return null;
        }
    }

    public List<VentaProcedure> convertirListaConteoGraficos(List<Object[]> dataList) {
        List<VentaProcedure> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                VentaProcedure datosVenta = new VentaProcedure();
                String nombreCostoUsuario = (String) object[1];
                Long cantidadVendidosDia = (Long) object[0];

                datosVenta.setNombrecostousuario(nombreCostoUsuario);
                datosVenta.setTotalTicketsVendidos(cantidadVendidosDia);
                result.add(datosVenta);
            }
            return result;
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.VentaProcedure.convertirListaConteoGraficos() " + ex);
            return null;
        }
    }

}
