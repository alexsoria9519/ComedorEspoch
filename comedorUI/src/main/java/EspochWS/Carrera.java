/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspochWS;

/**
 *
 * @author corebitsas
 */
public class Carrera {
    private String Nombre;
    private String Codigo;
    private String CodEscuela;
    private String CodEstado;

    public Carrera() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCodEscuela() {
        return CodEscuela;
    }

    public void setCodEscuela(String CodEscuela) {
        this.CodEscuela = CodEscuela;
    }

    public String getCodEstado() {
        return CodEstado;
    }

    public void setCodEstado(String CodEstado) {
        this.CodEstado = CodEstado;
    }
    
    
}
