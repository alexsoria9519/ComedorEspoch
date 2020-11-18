/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")
    , @NamedQuery(name = "Venta.findByIntidventa", query = "SELECT v FROM Venta v WHERE v.intidventa = :intidventa")
    , @NamedQuery(name = "Venta.findByDtfecha", query = "SELECT v FROM Venta v WHERE v.dtfecha = :dtfecha")
    , @NamedQuery(name = "Venta.findByIntcantidad", query = "SELECT v FROM Venta v WHERE v.intcantidad = :intcantidad")
    , @NamedQuery(name = "Venta.findByBlnestado", query = "SELECT v FROM Venta v WHERE v.blnestado = :blnestado")
    , @NamedQuery(name = "Venta.findByIntticketsconfirmados", query = "SELECT v FROM Venta v WHERE v.intticketsconfirmados = :intticketsconfirmados")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intidventa")
    private Integer intidventa;
    @Column(name = "dtfecha")
    @Temporal(TemporalType.DATE)
    private Date dtfecha;
    @Column(name = "intcantidad")
    private Integer intcantidad;
    @Column(name = "blnestado")
    private Boolean blnestado;
    @Column(name = "intticketsconfirmados")
    private Integer intticketsconfirmados;
    @JoinColumn(name = "intidcostousuario", referencedColumnName = "intidcostousuario")
    @ManyToOne
    private Costousuario intidcostousuario;
    @JoinColumn(name = "intidmenu", referencedColumnName = "intidmenu")
    @ManyToOne
    private Menu intidmenu;

    public Venta() {
    }

    public Venta(Integer intidventa) {
        this.intidventa = intidventa;
    }

    public Integer getIntidventa() {
        return intidventa;
    }

    public void setIntidventa(Integer intidventa) {
        this.intidventa = intidventa;
    }

    public Date getDtfecha() {
        return dtfecha;
    }

    public void setDtfecha(Date dtfecha) {
        this.dtfecha = dtfecha;
    }

    public Integer getIntcantidad() {
        return intcantidad;
    }

    public void setIntcantidad(Integer intcantidad) {
        this.intcantidad = intcantidad;
    }

    public Boolean getBlnestado() {
        return blnestado;
    }

    public void setBlnestado(Boolean blnestado) {
        this.blnestado = blnestado;
    }

    public Integer getIntticketsconfirmados() {
        return intticketsconfirmados;
    }

    public void setIntticketsconfirmados(Integer intticketsconfirmados) {
        this.intticketsconfirmados = intticketsconfirmados;
    }

    public Costousuario getIntidcostousuario() {
        return intidcostousuario;
    }

    public void setIntidcostousuario(Costousuario intidcostousuario) {
        this.intidcostousuario = intidcostousuario;
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
        hash += (intidventa != null ? intidventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.intidventa == null && other.intidventa != null) || (this.intidventa != null && !this.intidventa.equals(other.intidventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.comedorln.Venta[ intidventa=" + intidventa + " ]";
    }
    
}
