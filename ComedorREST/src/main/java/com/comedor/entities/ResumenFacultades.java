/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comedor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex4
 */
@Entity
@Table(name = "resumen_facultades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResumenFacultades.findAll", query = "SELECT r FROM ResumenFacultades r")
    , @NamedQuery(name = "ResumenFacultades.findById", query = "SELECT r FROM ResumenFacultades r WHERE r.id = :id")
    , @NamedQuery(name = "ResumenFacultades.findByFacultad", query = "SELECT r FROM ResumenFacultades r WHERE r.facultad = :facultad")
    , @NamedQuery(name = "ResumenFacultades.findByEnero", query = "SELECT r FROM ResumenFacultades r WHERE r.enero = :enero")
    , @NamedQuery(name = "ResumenFacultades.findByFebrero", query = "SELECT r FROM ResumenFacultades r WHERE r.febrero = :febrero")
    , @NamedQuery(name = "ResumenFacultades.findByMarzo", query = "SELECT r FROM ResumenFacultades r WHERE r.marzo = :marzo")
    , @NamedQuery(name = "ResumenFacultades.findByAbril", query = "SELECT r FROM ResumenFacultades r WHERE r.abril = :abril")
    , @NamedQuery(name = "ResumenFacultades.findByMayo", query = "SELECT r FROM ResumenFacultades r WHERE r.mayo = :mayo")
    , @NamedQuery(name = "ResumenFacultades.findByJunio", query = "SELECT r FROM ResumenFacultades r WHERE r.junio = :junio")
    , @NamedQuery(name = "ResumenFacultades.findByJulio", query = "SELECT r FROM ResumenFacultades r WHERE r.julio = :julio")
    , @NamedQuery(name = "ResumenFacultades.findByAgosto", query = "SELECT r FROM ResumenFacultades r WHERE r.agosto = :agosto")
    , @NamedQuery(name = "ResumenFacultades.findBySeptiembre", query = "SELECT r FROM ResumenFacultades r WHERE r.septiembre = :septiembre")
    , @NamedQuery(name = "ResumenFacultades.findByOctubre", query = "SELECT r FROM ResumenFacultades r WHERE r.octubre = :octubre")
    , @NamedQuery(name = "ResumenFacultades.findByNoviembre", query = "SELECT r FROM ResumenFacultades r WHERE r.noviembre = :noviembre")
    , @NamedQuery(name = "ResumenFacultades.findByDiciembre", query = "SELECT r FROM ResumenFacultades r WHERE r.diciembre = :diciembre")
    , @NamedQuery(name = "ResumenFacultades.findByFecha", query = "SELECT r FROM ResumenFacultades r WHERE r.fecha = :fecha")})
public class ResumenFacultades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "facultad")
    private String facultad;
    @Column(name = "enero")
    private Integer enero;
    @Column(name = "febrero")
    private Integer febrero;
    @Column(name = "marzo")
    private Integer marzo;
    @Column(name = "abril")
    private Integer abril;
    @Column(name = "mayo")
    private Integer mayo;
    @Column(name = "junio")
    private Integer junio;
    @Column(name = "julio")
    private Integer julio;
    @Column(name = "agosto")
    private Integer agosto;
    @Column(name = "septiembre")
    private Integer septiembre;
    @Column(name = "octubre")
    private Integer octubre;
    @Column(name = "noviembre")
    private Integer noviembre;
    @Column(name = "diciembre")
    private Integer diciembre;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private String fecha;
    private Integer total;
    private Double promedio;
    private Double porcentaje;

    public ResumenFacultades() {
    }

    public ResumenFacultades(Integer id) {
        this.id = id;
    }

    public ResumenFacultades(Integer id, String facultad) {
        this.id = id;
        this.facultad = facultad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public Integer getEnero() {
        return enero;
    }

    public void setEnero(Integer enero) {
        this.enero = enero;
    }

    public Integer getFebrero() {
        return febrero;
    }

    public void setFebrero(Integer febrero) {
        this.febrero = febrero;
    }

    public Integer getMarzo() {
        return marzo;
    }

    public void setMarzo(Integer marzo) {
        this.marzo = marzo;
    }

    public Integer getAbril() {
        return abril;
    }

    public void setAbril(Integer abril) {
        this.abril = abril;
    }

    public Integer getMayo() {
        return mayo;
    }

    public void setMayo(Integer mayo) {
        this.mayo = mayo;
    }

    public Integer getJunio() {
        return junio;
    }

    public void setJunio(Integer junio) {
        this.junio = junio;
    }

    public Integer getJulio() {
        return julio;
    }

    public void setJulio(Integer julio) {
        this.julio = julio;
    }

    public Integer getAgosto() {
        return agosto;
    }

    public void setAgosto(Integer agosto) {
        this.agosto = agosto;
    }

    public Integer getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(Integer septiembre) {
        this.septiembre = septiembre;
    }

    public Integer getOctubre() {
        return octubre;
    }

    public void setOctubre(Integer octubre) {
        this.octubre = octubre;
    }

    public Integer getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(Integer noviembre) {
        this.noviembre = noviembre;
    }

    public Integer getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(Integer diciembre) {
        this.diciembre = diciembre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResumenFacultades)) {
            return false;
        }
        ResumenFacultades other = (ResumenFacultades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public List<ResumenFacultades> convertirLista(List<Object[]> dataList) {
        List<ResumenFacultades> result = new ArrayList<>();
        try {
            for (Object[] object : dataList) {
                String facultad = (String) object[0];
                Integer enero = (Integer) object[1];
                Integer febrero = (Integer) object[2];
                Integer marzo = (Integer) object[3];
                Integer abril = (Integer) object[4];
                Integer mayo = (Integer) object[5];
                Integer junio = (Integer) object[6];
                Integer julio = (Integer) object[7];
                Integer agosto = (Integer) object[8];
                Integer septiembre = (Integer) object[9];
                Integer octubre = (Integer) object[10];
                Integer noviembre = (Integer) object[11];
                Integer diciembre = (Integer) object[12];

                ResumenFacultades resumen = new ResumenFacultades();

                resumen.setFacultad(facultad);
                resumen.setEnero(enero);
                resumen.setFebrero(febrero);
                resumen.setMarzo(marzo);
                resumen.setAbril(abril);
                resumen.setMayo(mayo);
                resumen.setJunio(junio);
                resumen.setAgosto(agosto);
                resumen.setSeptiembre(septiembre);
                resumen.setOctubre(octubre);
                resumen.setNoviembre(noviembre);
                resumen.setDiciembre(diciembre);

                result.add(resumen);
            }
            return result;
        } catch (Exception ex) {
            System.err.println("com.espoch.comedorln.ResumenFacultades.convertirLista() " + ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return "com.espoch.comedorln.ResumenFacultades[ id=" + id + " ]";
    }

}
