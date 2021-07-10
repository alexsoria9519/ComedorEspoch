/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln;

import java.io.Serializable;
import java.math.BigDecimal;
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
    , @NamedQuery(name = "Operativos.findByStrinstitucion", query = "SELECT o FROM Operativos o WHERE o.strinstitucion = :strinstitucion")
    , @NamedQuery(name = "Operativos.findByStrruc", query = "SELECT o FROM Operativos o WHERE o.strruc = :strruc")
    , @NamedQuery(name = "Operativos.findByStrdireccion", query = "SELECT o FROM Operativos o WHERE o.strdireccion = :strdireccion")
    , @NamedQuery(name = "Operativos.findByStrautsri", query = "SELECT o FROM Operativos o WHERE o.strautsri = :strautsri")
    , @NamedQuery(name = "Operativos.findByIva", query = "SELECT o FROM Operativos o WHERE o.iva = :iva")})
public class Operativos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intid")
    private Integer intid;
    @Size(max = 500)
    @Column(name = "strinstitucion")
    private String strinstitucion;
    @Size(max = 15)
    @Column(name = "strruc")
    private String strruc;
    @Size(max = 2147483647)
    @Column(name = "strdireccion")
    private String strdireccion;
    @Column(name = "strautsri")
    private Boolean strautsri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private BigDecimal iva;

    public Operativos() {
    }

    public Operativos(Integer intid) {
        this.intid = intid;
    }

    public Operativos(Integer intid, BigDecimal iva) {
        this.intid = intid;
        this.iva = iva;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public String getStrinstitucion() {
        return strinstitucion;
    }

    public void setStrinstitucion(String strinstitucion) {
        this.strinstitucion = strinstitucion;
    }

    public String getStrruc() {
        return strruc;
    }

    public void setStrruc(String strruc) {
        this.strruc = strruc;
    }

    public String getStrdireccion() {
        return strdireccion;
    }

    public void setStrdireccion(String strdireccion) {
        this.strdireccion = strdireccion;
    }

    public Boolean getStrautsri() {
        return strautsri;
    }

    public void setStrautsri(Boolean strautsri) {
        this.strautsri = strautsri;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
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
