/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "planificacionmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planificacionmenu.findAll", query = "SELECT p FROM Planificacionmenu p")
    , @NamedQuery(name = "Planificacionmenu.findByIntid", query = "SELECT p FROM Planificacionmenu p WHERE p.intid = :intid")
    , @NamedQuery(name = "Planificacionmenu.findByDtfechainicio", query = "SELECT p FROM Planificacionmenu p WHERE p.dtfechainicio = :dtfechainicio")
    , @NamedQuery(name = "Planificacionmenu.findByDtfechafin", query = "SELECT p FROM Planificacionmenu p WHERE p.dtfechafin = :dtfechafin")})
public class Planificacionmenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intid")
    private Integer intid;
    @Column(name = "dtfechainicio")
    @Temporal(TemporalType.DATE)
    private Date dtfechainicio;
    @Column(name = "dtfechafin")
    @Temporal(TemporalType.DATE)
    private Date dtfechafin;
    @JoinColumn(name = "intidmenu", referencedColumnName = "intidmenu")
    @ManyToOne(optional = false)
    private Menu intidmenu;

    public Planificacionmenu() {
    }

    public Planificacionmenu(Integer intid) {
        this.intid = intid;
    }

    public Integer getIntid() {
        return intid;
    }

    public void setIntid(Integer intid) {
        this.intid = intid;
    }

    public Date getDtfechainicio() {
        return dtfechainicio;
    }

    public void setDtfechainicio(Date dtfechainicio) {
        this.dtfechainicio = dtfechainicio;
    }

    public Date getDtfechafin() {
        return dtfechafin;
    }

    public void setDtfechafin(Date dtfechafin) {
        this.dtfechafin = dtfechafin;
    }
    
    public Menu getIntidmenu() {
        return intidmenu;
    }

    public void setIntidmenu(Menu intidmenu) {
        this.intidmenu = intidmenu;
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
        if (!(object instanceof Planificacionmenu)) {
            return false;
        }
        Planificacionmenu other = (Planificacionmenu) object;
        if ((this.intid == null && other.intid != null) || (this.intid != null && !this.intid.equals(other.intid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.comedorln.Planificacionmenu[ intid=" + intid + " ]";
    }
    
}
