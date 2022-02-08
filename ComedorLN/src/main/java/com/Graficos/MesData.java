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
public class MesData {

    private Date mes;
    private Long cantidad;
    private String mesDescripcion;

    public MesData() {
    }

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getMesDescripcion() {
        return mesDescripcion;
    }

    public void setMesDescripcion(String mesDescripcion) {
        this.mesDescripcion = mesDescripcion;
    }

    public List<MesData> convertirListaConteo(List<Object[]> dataList) {
        List<MesData> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                MesData mesData = new MesData();
                Calendar cal = Calendar.getInstance();

                Date mes = (Date) object[0];
                Long cantidad = (Long) object[1];
                cal.setTime(mes);
                mesData.setMes(mes);
                mesData.setCantidad(cantidad);
                mesData.setMesDescripcion(getMonthName(cal.get(Calendar.MONTH)));
                result.add(mesData);
            }
            return result;
        } catch (Exception ex) {
            System.err.println("com.Graficos.MesData.convertirListaConteo() " + ex);
            return null;
        }
    }

    private static String getMonthName(int month) {

        String monthName = null;
        switch (month) {
            case 0:
                monthName = "Enero";
                break;
            case 1:
                monthName = "Febreo";
                break;
            case 2:
                monthName = "Marzo";
                break;
            case 3:
                monthName = "Abril";
                break;
            case 4:
                monthName = "Mayo";
                break;
            case 5:
                monthName = "Junio";
                break;
            case 6:
                monthName = "Julio";
                break;
            case 7:
                monthName = "Agosto";
                break;
            case 8:
                monthName = "Septiembre";
                break;
            case 9:
                monthName = "Octubre";
                break;
            case 10:
                monthName = "Noviembre";
                break;
            case 11:
                monthName = "Deciembre";
                break;
        }

        return monthName;
    }

}
