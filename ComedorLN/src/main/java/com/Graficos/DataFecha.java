/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Graficos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alex4
 */
public class DataFecha {
    
    private Date fecha;
    private Long numVentas;
    private String data;
    
    public DataFecha() {
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Long getNumVentas() {
        return numVentas;
    }
    
    public void setNumVentas(Long numVentas) {
        this.numVentas = numVentas;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public List<DataFecha> convertirLista(List<Object[]> dataList) {
        List<DataFecha> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                DataFecha datosVenta = new DataFecha();
                Date fecha = (Date) object[0];
                Long numeroVentas = (Long) object[1];
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                
                Integer year = calendar.get(Calendar.YEAR);
                Integer month = calendar.get(Calendar.MONTH) + 1;
                
                datosVenta.setFecha(fecha);
                datosVenta.setNumVentas(numeroVentas);
                datosVenta.setData(year.toString() + "/" + getMes(month));
                result.add(datosVenta);
            }
            return result;
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.VentaProcedure.convertirLista() " + ex);
            return null;
        }
    }
    
    private String getMes(Integer numMes) {
        String mes = "";
        switch (numMes) {
            case 1:
                mes = "Enero";
                break;
            case 2:
                mes = "Febrero";
                break;
            case 3:
                mes = "Marzo";
                break;
            case 4:
                mes = "Abril";
                break;
            case 5:
                mes = "Mayo";
                break;
            case 6:
                mes = "Junio";
                break;
            case 7:
                mes = "Julio";
                break;
            case 8:
                mes = "Agosto";
                break;
            case 9:
                mes = "Septiembre";
                break;
            case 10:
                mes = "Octubre";
                break;
            case 11:
                mes = "Noviembre";
                break;
            case 12:
                mes = "Diciembre";
                break;
        }
        return mes;
    }
    
}
