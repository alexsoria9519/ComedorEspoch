/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.Costos;

import com.comedor.TipoMenu.TipoMenuLN;
import com.comedor.TipoUsuario.TipoUsuarioLN;
import com.comedor.entities.Costo;
import com.comedor.entities.Costousuario;
import com.comedor.entities.Tipomenu;
import com.comedor.entities.Tipousuario;
import com.comedor.servicios.CostoWS;
import com.comedor.utilidades.Utilidades;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.ClientErrorException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author COREBITSAS
 */
public class CostoLN {

    CostoWS costoWs = new CostoWS();
    JSONObject resJson = new JSONObject();
    Gson gson = new Gson();

    public CostoLN() {
    }

    public String insertCosto(String jsonData) {
        Utilidades utilidades = new Utilidades();
        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataCosto = utilidades.getDataJson(resquestJson, "costo");
            Costo costo = gson.fromJson(dataCosto, Costo.class);
            if (validarDatosIngreso(costo) && validarCostoRepetido(costo)) {
                costoWs.create(costo);
                resJson.put("success", "ok");
                resJson.put("data", "Ingreso Correcto");
            }
        } catch (JsonSyntaxException | ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en el ingreso");
            System.err.println("com.comedor.Costos.CostoLN.insertCosto() " + ex);
        }
        return resJson.toString();
    }

    public Boolean validarDatosIngreso(Costo costo) {
        // Utilidades utilidades = new Utilidades();
        try {
            if (costo.getStrdetalle() == null || "".equals(costo.getStrdetalle())) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el detalle del costo");
                return false;
            } else if (costo.getMnvalor() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el valor del costo");
                return false;
            } else if (costo.getMnvalor() < 0) {
                resJson.put("success", "validacion");
                resJson.put("data", "El valor del costo debe ser mayor a 0");
                return false;
            } else if (costo.getDtfecha() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el valor de la fecha");
                return false;
//        } else if(!utilidades.validarFecha(costo.getDtfecha().toString())){
//            resJson.put("success", "validacion");
//            resJson.put("data", "Se debe ingresar una fecha válida");
//            return false;
            } else if (costo.getBlnestado() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe proporcionar el estado del costo");
                return false;
            }
            return validarTipoMenu(costo) && validarTipoUsuario(costo);
        } catch (Exception ex) {
            System.err.println("com.comedor.Costos.CostoLN.validarDatosIngreso() " + ex);
            return false;
        }
    }

    private Boolean validarTipoMenu(Costo costo) {
        TipoMenuLN tipoMenuLn = new TipoMenuLN();
        try {
            if (costo.getIntidtipomenu() == null || costo.getIntidtipomenu().getIntidtipo() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el tipo de menú");
                return false;
            } else {
                String resAll = tipoMenuLn.getTipoMenu(costo.getIntidtipomenu().getIntidtipo());
                JSONObject respJson = new JSONObject(resAll);
                if (respJson.getString("success").equals("no existe")) {
                    resJson.put("success", "validacion");
                    resJson.put("data", "No existe el tipo de menú, por favor ingrese otro tipo de menú");
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            System.err.println("com.comedor.Costos.CostoLN.validarTipoMenu() " + ex);
            return false;
        }
    }

    private Boolean validarTipoUsuario(Costo costo) {
        TipoUsuarioLN tipoUsuarioLn = new TipoUsuarioLN();
        try {
            if (costo.getIntidtipousuario() == null || costo.getIntidtipousuario().getIntidtipo() == null) {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe ingresar el tipo de usuario");
                return false;
            } else {
                String resAll = tipoUsuarioLn.getTipoUsuario(costo.getIntidtipousuario().getIntidtipo());
                JSONObject respJson = new JSONObject(resAll);
                if (respJson.getString("success").equals("no existe")) {
                    resJson.put("success", "validacion");
                    resJson.put("data", "No existe el tipo de usuario, por favor ingrese otro tipo de usuario");
                    return false;
                }
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.Costos.CostoLN.validarTipoUsuario() " + ex);
        }
        return true;
    }

    public Boolean validarCostoRepetido(Costo costo) {

        try {
            String resAll = costoWs.findByStrDetalle(String.class, costo.getStrdetalle());

            if (!"{}".equals(resAll)) {

                if (costo.getIntidcosto() != null) {

                    Costo costoRepetido = new Costo();
                    costoRepetido = gson.fromJson(resAll, Costo.class);

                    if (costoRepetido.getIntidcosto() != null && costoRepetido.getIntidcosto() == costo.getIntidcosto()) {
                        return true;
                    } else {
                        resJson.put("success", "validacion");
                        resJson.put("data", "Ya existe registro con el detalle " + costo.getStrdetalle());
                        return false;
                    }
                } else {
                    resJson.put("success", "validacion");
                    resJson.put("data", "Ya existe registro con el detalle " + costo.getStrdetalle());
                    return false;
                }
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.Costos.CostoLN.validarCostoRepetido() " + ex);
            return false;
        }
        return true;
    }

    public String getCosto(Integer idTipo) {
        String resAll = "";
        try {
            resAll = costoWs.find(String.class, idTipo.toString());
            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No existen datos de ese codigo");
            } else {
                resJson.put("success", "ok");
                resJson.put("Costo", resAll);
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la busqueda");
            System.err.println("com.comedor.Costos.CostoLN.getCosto() " + ex);
        }
        return resJson.toString();
    }

    public String getCostoByDetalle(String detalle) {
        String resAll = "";
        try {
            resAll = costoWs.findByStrDetalle(String.class, detalle);

            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No se encontro ningun registro del tipo " + detalle);
            } else {
                resJson.put("success", "ok");
                resJson.put("Costo", resAll);
            }
        } catch (ClientErrorException | JSONException ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la busqueda");
            System.err.println("com.comedor.Costos.CostoLN.getCostoByDetalle() " + ex);
        }
        return resJson.toString();
    }

    public String updateCosto(String jsonData) {
        Utilidades utilidades = new Utilidades();

        try {
            JSONObject resquestJson = new JSONObject(jsonData);
            String dataCosto = utilidades.getDataJson(resquestJson, "costo");
            Costo costo = gson.fromJson(dataCosto, Costo.class);

            if (validarCostoRepetido(costo)) {
                costoWs.edit(costo, costo.getIntidcosto().toString());
                resJson.put("success", "ok");
                resJson.put("data", "Modificacion Correcta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la modificacion");
            System.err.println("com.comedor.Costos.CostoLN.updateCosto() " + ex);
        }
        return resJson.toString();
    }

    public String activarDesactivarCosto(Integer idCosto, String estado) {
        Utilidades utilidades = new Utilidades();
        try {

            if (idCosto != null && idCosto > 0) {

                String resAll = getCosto(idCosto);
                JSONObject respJson = new JSONObject(resAll);

                if (respJson.getString("success") != null && respJson.getString("success").equals("ok")) {
                    Costo costo = gson.fromJson(respJson.getString("Costo"), Costo.class);

                    if (estado.equals("activar")) {
                        costo.setBlnestado(true);
                        edicionCosto(costo);
                    } else if (estado.equals("desactivar")) {
                        costo.setBlnestado(false);
                        edicionCosto(costo);
                    } else {
                        resJson.put("success", "validacion");
                        resJson.put("data", "Se debe ingresar un estado valido");
                        resJson.remove("Costo");
                    }

                }
            } else {
                resJson.put("success", "validacion");
                resJson.put("data", "Se debe proporcionar un código");
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.Costos.CostoLN.activarDesactivarCosto() " + ex);
        }

        return resJson.toString();
    }

    private void edicionCosto(Costo costo) {
        costoWs.edit(costo, costo.getIntidcosto().toString());
        resJson.put("success", "ok");
        resJson.put("data", "Modificacion Correcta");
        resJson.put("Costo", gson.toJson(costo));
    }

    public String deleteCosto(Integer idTipo) {
        String resAll = "";
        try {

            resAll = costoWs.find(String.class, idTipo.toString());

            if ("{}".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "No se puede eliminar porque no existen datos del codigo proporcionado");
            } else {
                costoWs.remove(idTipo.toString());
                resJson.put("success", "ok");
                resJson.put("data", "Eliminacion Correcta");
            }
        } catch (Exception ex) {
            resJson.put("success", "error");
            resJson.put("data", "Error en la eliminacion");
        }
        return resJson.toString();
    }

    public String tiposUsuariosUtilizados(Integer idTipoUsuario) {
        String resAll = "";
        Costo costo = new Costo();
        Tipousuario tipoUsuario = new Tipousuario();
        try {
            tipoUsuario.setIntidtipo(idTipoUsuario);
            costo.setIntidtipousuario(tipoUsuario);

            resAll = costoWs.getTiposUsuariosUtilizados(costo, String.class);

            if ("[]".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "El codigo ingresado no esta utilizado");
            } else {
                resJson.put("success", "ok");
                resJson.put("data", resAll);
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.CostoUsuario.CostoUsuarioLN.tiposUsuariosUtilizados()" + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al buscar el tipo de usuario utilizado");
        }
        return resJson.toString();
    }

    public String tiposMenusUtilizados(Integer idTipoMenu) {
        String resAll = "";
        Costo costo = new Costo();
        Tipomenu tipoMenu = new Tipomenu();
        try {
            tipoMenu.setIntidtipo(idTipoMenu);
            costo.setIntidtipomenu(tipoMenu);

            resAll = costoWs.getTiposMenusUtilizados(costo, String.class);

            if ("[]".equals(resAll)) {
                resJson.put("success", "no existe");
                resJson.put("data", "El codigo ingresado no esta utilizado");
            } else {
                resJson.put("success", "ok");
                resJson.put("data", resAll);
            }
        } catch (Exception ex) {
            System.err.println("com.comedor.CostoUsuario.CostoUsuarioLN.tiposUsuariosUtilizados()" + ex);
            resJson.put("success", "error");
            resJson.put("data", "Error al buscar el tipo de usuario utilizado");
        }
        return resJson.toString();
    }

    public Boolean validarTipoUsuarioUtilizado(Integer idTipoUsuario) {
        try {
            String resAll = tiposUsuariosUtilizados(idTipoUsuario);
            JSONObject respJson = new JSONObject(resAll);
            return respJson.getString("success").equals("ok");
        } catch (JSONException ex) {
            System.err.println("com.comedor.CostoUsuario.CostoLN.validarTipoUsuarioUtitlizado()" + ex);
            return false;
        }
    }

    public Boolean validarTipoMenuUtilizado(Integer idTipoMenu) {
        try {
            String resAll = tiposMenusUtilizados(idTipoMenu);
            JSONObject respJson = new JSONObject(resAll);
            return respJson.getString("success").equals("ok");
        } catch (JSONException ex) {
            System.err.println("com.comedor.CostoUsuario.CostoLN.validarTipoUsuarioUtitlizado()" + ex);
            return false;
        }
    }

}
