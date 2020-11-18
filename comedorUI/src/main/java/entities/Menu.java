/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findByIntidmenu", query = "SELECT m FROM Menu m WHERE m.intidmenu = :intidmenu")
    , @NamedQuery(name = "Menu.findByStrcaracteristicas", query = "SELECT m FROM Menu m WHERE m.strcaracteristicas = :strcaracteristicas")
    , @NamedQuery(name = "Menu.findByBlnestado", query = "SELECT m FROM Menu m WHERE m.blnestado = :blnestado")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intidmenu")
    private Integer intidmenu;
    @Size(max = 500)
    @Column(name = "strcaracteristicas")
    private String strcaracteristicas;
    @Column(name = "blnestado")
    private Boolean blnestado;
    @OneToMany(mappedBy = "intidmenu")
    private Collection<Venta> ventaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intidmenu")
    private Collection<Planificacionmenu> planificacionmenuCollection;
    @JoinColumn(name = "intidtipo", referencedColumnName = "intidtipo")
    @ManyToOne(optional = false)
    private Tipomenu intidtipo;

    public Menu() {
    }

    public Menu(Integer intidmenu) {
        this.intidmenu = intidmenu;
    }

    public Integer getIntidmenu() {
        return intidmenu;
    }

    public void setIntidmenu(Integer intidmenu) {
        this.intidmenu = intidmenu;
    }

    public String getStrcaracteristicas() {
        return strcaracteristicas;
    }

    public void setStrcaracteristicas(String strcaracteristicas) {
        this.strcaracteristicas = strcaracteristicas;
    }

    public Boolean getBlnestado() {
        return blnestado;
    }

    public void setBlnestado(Boolean blnestado) {
        this.blnestado = blnestado;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    @XmlTransient
    public Collection<Planificacionmenu> getPlanificacionmenuCollection() {
        return planificacionmenuCollection;
    }

    public void setPlanificacionmenuCollection(Collection<Planificacionmenu> planificacionmenuCollection) {
        this.planificacionmenuCollection = planificacionmenuCollection;
    }

    public Tipomenu getIntidtipo() {
        return intidtipo;
    }

    public void setIntidtipo(Tipomenu intidtipo) {
        this.intidtipo = intidtipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intidmenu != null ? intidmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.intidmenu == null && other.intidmenu != null) || (this.intidmenu != null && !this.intidmenu.equals(other.intidmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.comedorln.Menu[ intidmenu=" + intidmenu + " ]";
    }
    
}
