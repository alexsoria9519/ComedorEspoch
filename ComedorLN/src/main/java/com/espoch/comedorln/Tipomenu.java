/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espoch.comedorln;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex4
 */
@Entity
@Table(name = "tipomenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomenu.findAll", query = "SELECT t FROM Tipomenu t")
    , @NamedQuery(name = "Tipomenu.findByIntidtipo", query = "SELECT t FROM Tipomenu t WHERE t.intidtipo = :intidtipo")
    , @NamedQuery(name = "Tipomenu.findByStrtipo", query = "SELECT t FROM Tipomenu t WHERE t.strtipo = :strtipo")})
public class Tipomenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intidtipo")
    private Integer intidtipo;
    @Size(max = 128)
    @Column(name = "strtipo")
    private String strtipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intidtipomenu")
    private Collection<Costo> costoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intidtipomenu")
    private Collection<Menu> menuCollection;

    public Tipomenu() {
    }

    public Tipomenu(Integer intidtipo) {
        this.intidtipo = intidtipo;
    }

    public Integer getIntidtipo() {
        return intidtipo;
    }

    public void setIntidtipo(Integer intidtipo) {
        this.intidtipo = intidtipo;
    }

    public String getStrtipo() {
        return strtipo;
    }

    public void setStrtipo(String strtipo) {
        this.strtipo = strtipo;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Costo> getCostoCollection() {
        return costoCollection;
    }

    public void setCostoCollection(Collection<Costo> costoCollection) {
        this.costoCollection = costoCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intidtipo != null ? intidtipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipomenu)) {
            return false;
        }
        Tipomenu other = (Tipomenu) object;
        if ((this.intidtipo == null && other.intidtipo != null) || (this.intidtipo != null && !this.intidtipo.equals(other.intidtipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espoch.comedorln.Tipomenu[ intidtipo=" + intidtipo + " ]";
    }
    
}
