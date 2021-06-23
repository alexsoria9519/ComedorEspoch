/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.google.gson.Gson;
import entities.Costo;
import entities.CostosUsuarios;
import entities.Costousuario;
import entities.Menu;
import entities.Planificacionmenu;
import entities.Tipomenu;
import entities.Tipousuario;
import entities.Venta;
import entities.Ventas;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;
import servicios.ComedorWS;
import servicios.VentaWS;

/**
 *
 * @author corebitsas
 */
public class TestClass {

    JSONObject requestJSON = new JSONObject();
    VentaWS ventaWs = new VentaWS();
    ComedorWS comedorWs = new ComedorWS();
    Gson gson = new Gson();

    public TestClass() {
    }

    public void ingresoDatos(Integer numeroDias, Integer numeroVentasDiarias, Date fechaInicio) {
//        Tipousuario tipoUsuario = new Tipousuario();
//        Tipomenu tipoMenu = new Tipomenu();
//
//        tipoUsuario.setStrtipo("Estudiante");
//        ingresoTipoUsuario(tipoUsuario);
//        tipoUsuario.setStrtipo("Docente");
//        ingresoTipoUsuario(tipoUsuario);
//        tipoUsuario.setStrtipo("Funcionario");
//        ingresoTipoUsuario(tipoUsuario);
//
//        tipoMenu.setStrtipo("Desayuno");
//        ingresoTipoMenu(tipoMenu);
//        tipoMenu.setStrtipo("Almuerzo");
//        ingresoTipoMenu(tipoMenu);
//        tipoMenu.setStrtipo("Merienda");
//        ingresoTipoMenu(tipoMenu);
//
//        ingresarDatosCosto();
//        ingresoDatosMenu();
//        ingresoDatosCostoUsuario();
        ingresoRegistrosRangoFechas(numeroDias, numeroVentasDiarias, fechaInicio);

    }

    public void ingresarDatosCosto() {
        Tipousuario tipoUsuario = new Tipousuario();
        String usuario = "", menu = "";
        Tipomenu tipoMenu = new Tipomenu();
        Costo costo = new Costo();
        String descripcion;
        costo.setDtfecha(new Date());
        costo.setBlnestado(true);
        for (int i = 0; i < 3; i++) {

            tipoMenu.setIntidtipo(i + 1);
            switch (i) {
                case 0:
                    menu = "Desayuno ";
                    break;
                case 1:
                    menu = "Almuerzo ";
                    break;
                case 2:
                    menu = "Merienda ";
                    break;
            }
            for (int j = 0; j < 3; j++) {
                Double valor = (double) Math.round((Math.random() * 5 + 1) * 100) / 100;
                costo.setMnvalor(valor);
                tipoUsuario.setIntidtipo(j + 1);
                switch (j) {
                    case 0:
                        usuario = "Estudiante";
                        break;
                    case 1:
                        usuario = "Docente";
                        break;
                    case 2:
                        usuario = "Funcionario";
                        break;
                }
                costo.setStrdetalle(menu + usuario);
                costo.setIntidtipomenu(tipoMenu);
                costo.setIntidtipousuario(tipoUsuario);
                ingresoCosto(costo);
            }
        }
    }

    public void ingresoDatosMenu() {
        Menu menu = new Menu();
        Tipomenu tipomenu = new Tipomenu();
        String descripcion = " ";
        menu.setBlnestado(true);

        for (int i = 0; i < 3; i++) {

            tipomenu.setIntidtipo(i + 1);
            switch (i) {
                case 0:
                    descripcion = "Desayuno ";
                    break;
                case 1:
                    descripcion = "Almuerzo ";
                    break;
                case 2:
                    descripcion = "Merienda ";
                    break;
            }
            menu.setIntidtipomenu(tipomenu);
            for (int j = 0; j < 3; j++) {
                menu.setStrcaracteristicas(descripcion + (i + 1));
                ingresoMenu(menu);
            }
        }
    }

    public void ingresoDatosCostoUsuario() {

        try {
            Tipousuario tipoUsuario = new Tipousuario();

            tipoUsuario.setIntidtipo(1);
            datosCostoUnUsuario("1721796066", tipoUsuario, 0, 3);
            datosCostoUnUsuario("0605575646", tipoUsuario, 0, 3);
            tipoUsuario.setIntidtipo(2);
            datosCostoUnUsuario("1713057667", tipoUsuario, 3, 6);
            datosCostoUnUsuario("1708761125", tipoUsuario, 3, 6);
            tipoUsuario.setIntidtipo(3);
            datosCostoUnUsuario("2300629132", tipoUsuario, 6, 9);
        } catch (Exception ex) {

        }
    }

    public void datosCostoUnUsuario(String cedula, Tipousuario tipoUsuario, Integer inicio, Integer fin) {
        Costousuario costousuario = new Costousuario();
        Costo costo = new Costo();
        try {
            costousuario.setIntidtipo(tipoUsuario);
            costousuario.setStrcedula(cedula);
            for (int i = inicio; i < fin; i++) {
                costo.setIntidcosto(i + 1);
                costousuario.setIntidcosto(costo);
                ingresoCostoUsuario(costousuario);
            }
        } catch (Exception ex) {

        }
    }

    public void ingresoTipoUsuario(Tipousuario tipoUsuario) {
        try {
            requestJSON.put("tipoUsuario", gson.toJson(tipoUsuario));
            comedorWs.insertTipoUsuario(requestJSON.toString());
        } catch (Exception ex) {
            System.err.println("Test.TestClass.ingresoTipoUsuario() " + ex);
        }
    }

    public void ingresoTipoMenu(Tipomenu tipoMenu) {
        try {
            requestJSON.put("tipoMenu", gson.toJson(tipoMenu));
            comedorWs.insertTipoMenu(requestJSON.toString());
        } catch (Exception ex) {
            System.err.println("Test.TestClass.ingresoTipoMenu() " + ex);
        }
    }

    public void ingresoCosto(Costo costo) {
        try {
            requestJSON.put("costo", gson.toJson(costo));
            comedorWs.insertCosto(requestJSON.toString());
        } catch (Exception ex) {
            System.err.println("Test.TestClass.ingresoCosto() " + ex);
        }
    }

    public void ingresoCostoUsuario(Costousuario costoUsuario) {
        try {
            requestJSON.put("costoUsuario", gson.toJson(costoUsuario));
            comedorWs.insertCostoUsuario(requestJSON.toString());
        } catch (Exception ex) {
            System.out.println("Test.TestClass.ingresoCostoUsuario() " + ex);
        }
    }

    public void ingresoMenu(Menu menu) {
        try {
            requestJSON.put("menu", gson.toJson(menu));
            comedorWs.insertMenu(requestJSON.toString());
        } catch (Exception ex) {
            System.err.println("Test.TestClass.ingresoMenu() " + ex);
        }
    }

    public void ingresoPlanificacionMenu(Planificacionmenu planificacionmenu) {
        try {
            requestJSON.put("planificacionMenu", gson.toJson(planificacionmenu));
            comedorWs.insertPlanificacionMenu(requestJSON.toString());
        } catch (Exception ex) {
            System.err.println("Test.TestClass.ingresoPlanificacionMenu() " + ex);
        }
    }

    public void ingresoVenta(Venta venta) {
        try {
            requestJSON.put("venta", gson.toJson(venta));
//            comedorWs.insertVenta(requestJSON.toString());
            ventaWs.insert(venta);
        } catch (Exception ex) {
            System.err.println("Test.TestClass.ingresoVenta() " + ex);
        }
    }

    public void ingresoRegistrosRangoFechas(Integer numeroDias, Integer numerVentasDiarias, Date fechaInicio) {
        try {
            Date fecha = fechaInicio;
            Calendar c = Calendar.getInstance();
            for (int i = 0; i < numeroDias; i++) {
                System.err.println("fecha " + fecha);
                datosVentasDia(numerVentasDiarias, fecha);
                c.setTime(fecha);
                c.add(Calendar.DATE, 1);
                fecha = c.getTime();
            }
        } catch (Exception ex) {
            System.err.println("Test.TestClass.ingresoRegistrosRangoFechas() " + ex);
        }
    }

    public void datosVentasDia(Integer numeroVentas, Date fecha) {
        try {
            Venta venta = new Venta();
            Costousuario costousuario = new Costousuario();
            Random random = new Random();
            Integer idCostoUsuario;

            venta.setBlnestado(true);
            venta.setBlnreserva(false);
            venta.setDtfecha(fecha);
            venta.setIntcantidad(1);
            for (int i = 1; i <= numeroVentas; i++) {
                idCostoUsuario = random.ints(1, 15).findFirst().getAsInt();
                costousuario.setIntidcostousuario(idCostoUsuario);
                venta.setIntidcostousuario(costousuario);
                ingresoVenta(venta);
            }
        } catch (Exception ex) {
            System.err.println("Test.TestClass.datosVentasDia() " + ex);
        }
    }

}
