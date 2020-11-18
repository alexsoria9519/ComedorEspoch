/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author corebitsas
 */
@Entity
@Table(name = "costo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Costo.findAll", query = "SELECT c FROM Costo c")
    , @NamedQuery(name = "Costo.findByIntidcosto", query = "SELECT c FROM Costo c WHERE c.intidcosto = :intidcosto")
    , @NamedQuery(name = "Costo.findByStrdetalle", query = "SELECT c FROM Costo c WHERE c.strdetalle = :strdetalle")
    , @NamedQuery(name = "Costo.findByMnvalor", query = "SELECT c FROM Costo c WHERE c.mnvalor = :mnvalor")
    , @NamedQuery(name = "Costo.findByBlnestado", query = "SELECT c FROM Costo c WHERE c.blnestado = :blnestado")
    , @NamedQuery(name = "Costo.updateByBlnestado", query = "UPDATE Costo AS c SET c.blnestado=:blnestado  WHERE c.intidcosto = :intidcosto")
    , @NamedQuery(name = "Costo.tiposUsuariosUtilizados", query = "SELECT c FROM Costo c JOIN c.intidtipousuario t WHERE t.intidtipo= :intidtipousuario")  
})
public class Costo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intidcosto")
    private Integer intidcosto;
    @Size(max = 500)
    @Column(name = "strdetalle")
    private String strdetalle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mnvalor")
    private Double mnvalor;
    @Column(name = "dtfecha")
    @Temporal(TemporalType.DATE)
    private Date dtfecha;
    @Column(name = "blnestado")
    private Boolean blnestado;
    @JoinColumn(name = "intidtipomenu", referencedColumnName = "intidtipo")
    @ManyToOne(optional = false)
    private Tipomenu intidtipomenu;
    @JoinColumn(name = "intidtipousuario", referencedColumnName = "intidtipo")
    @ManyToOne(optional = false)
    private Tipousuario intidtipousuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intidcosto")
    private Collection<Costousuario> costousuarioCollection;

    public Costo() {
    }

    public Costo(Integer intidcosto) {
        this.intidcosto = intidcosto;
    }

    public Integer getIntidcosto() {
        return intidcosto;
    }

    public void setIntidcosto(Integer intidcosto) {
        this.intidcosto = intidcosto;
    }

    public String getStrdetalle() {
        return strdetalle;
    }

    public void setStrdetalle(String strdetalle) {
        this.strdetalle = strdetalle;
    }

    public Double getMnvalor() {
        return mnvalor;
    }

    public void setMnvalor(Double mnvalor) {
        this.mnvalor = mnvalor;
    }

    public Date getDtfecha() {
        return dtfecha;
    }

    public void setDtfecha(Date dtfecha) {
        this.dtfecha = dtfecha;
    }

    public Boolean getBlnestado() {
        return blnestado;
    }

    public void setBlnestado(Boolean blnestado) {
        this.blnestado = blnestado;
    }

    public Tipomenu getIntidtipomenu() {
        return intidtipomenu;
    }

    public void setIntidtipomenu(Tipomenu intidtipomenu) {
        this.intidtipomenu = intidtipomenu;
    }

    public Tipousuario getIntidtipousuario() {
        return intidtipousuario;
    }

    public void setIntidtipousuario(Tipousuario intidtipousuario) {
        this.intidtipousuario = intidtipousuario;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Costousuario> getCostousuarioCollection() {
        return costousuarioCollection;
    }

    public void setCostousuarioCollection(Collection<Costousuario> costousuarioCollection) {
        this.costousuarioCollection = costousuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intidcosto != null ? intidcosto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Costo)) {
            return false;
        }
        Costo other = (Costo) object;
        if ((this.intidcosto == null && other.intidcosto != null) || (this.intidcosto != null && !this.intidcosto.equals(other.intidcosto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.espoch.comedorln.Costo[ intidcosto=" + intidcosto + " ]";
    }
    
}
