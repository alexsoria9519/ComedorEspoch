/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@XmlRootElement
public class CostoProcedure {
    
     @XmlElement String tipomenus;
    @XmlElement String cedula;
    @XmlElement String tipousuario;

    public String getTipomenus() {
        return tipomenus;
    }

    public void setTipomenus(String tipomenus) {
        this.tipomenus = tipomenus;
    }


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }
    
    
    
}
