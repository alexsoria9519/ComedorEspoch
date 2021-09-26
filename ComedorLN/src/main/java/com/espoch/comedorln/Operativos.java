/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex4
 */
@Entity
@Table(name = "operativos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operativos.findAll", query = "SELECT o FROM Operativos o")
    , @NamedQuery(name = "Operativos.findByIntid", query = "SELECT o FROM Operativos o WHERE o.intid = :intid")
    , @NamedQuery(name = "Operativos.findByStridentificador", query = "SELECT o FROM Operativos o WHERE o.stridentificador = :stridentificador")
    , @NamedQuery(name = "Operativos.findByStrdetalle", query = "SELECT o FROM Operativos o WHERE o.strdetalle = :strdetalle")})
public class Operativos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intid")
    private Integer intid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "stridentificador")
    private String stridentificador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "strdetalle")
    private String strdetalle;

    public Operativos() {
    }

    public Operativos(Integer intid) {
        this.intid = intid;
    }

    public Operativos(Integer intid, String stridentificador, String strdetalle) {
        this.intid = intid;
        this.stridentificador = stridentificador;
        this.strdetalle = strdetalle;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public String getStridentificador() {
        return stridentificador;
    }

    public void setStridentificador(String stridentificador) {
        this.stridentificador = stridentificador;
    }

    public String getStrdetalle() {
        return strdetalle;
    }

    public void setStrdetalle(String strdetalle) {
        this.strdetalle = strdetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intid != null ? intid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operativos)) {
            return false;
        }
        Operativos other = (Operativos) object;
        if ((this.intid == null && other.intid != null) || (this.intid != null && !this.intid.equals(other.intid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espoch.comedorln.Operativos[ intid=" + intid + " ]";
    }
    
}
