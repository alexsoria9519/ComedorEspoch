/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.ws.rs.ClientErrorException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import servicios.CostoUsuarioWS;
import servicios.TipoUsuarioWS;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "costousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Costousuario.findAll", query = "SELECT c FROM Costousuario c")
    , @NamedQuery(name = "Costousuario.findByIntidcostousuario", query = "SELECT c FROM Costousuario c WHERE c.intidcostousuario = :intidcostousuario")
    , @NamedQuery(name = "Costousuario.findByStrcedula", query = "SELECT c FROM Costousuario c WHERE c.strcedula = :strcedula")})
public class Costousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "intidcostousuario")
    private Integer intidcostousuario;
    @Size(max = 10)
    @Column(name = "strcedula")
    private String strcedula;
    @OneToMany(mappedBy = "intidcostousuario")
    private Collection<Venta> ventaCollection;
    @JoinColumn(name = "intidcosto", referencedColumnName = "intidcosto")
    @ManyToOne(optional = false)
    private Costo intidcosto;
    @JoinColumn(name = "intidtipo", referencedColumnName = "intidtipo")
    @ManyToOne(optional = false)
    private Tipousuario intidtipo;

    public Costousuario() {
    }

    public Costousuario(Integer intidcostousuario) {
        this.intidcostousuario = intidcostousuario;
    }

    public Integer getIntidcostousuario() {
        return intidcostousuario;
    }

    public void setIntidcostousuario(Integer intidcostousuario) {
        this.intidcostousuario = intidcostousuario;
    }

    public String getStrcedula() {
        return strcedula;
    }

    public void setStrcedula(String strcedula) {
        this.strcedula = strcedula;
    }

    @XmlTransient
    public Collection<Venta> getVentaCollection() {
        return ventaCollection;
    }

    public void setVentaCollection(Collection<Venta> ventaCollection) {
        this.ventaCollection = ventaCollection;
    }

    public Costo getIntidcosto() {
        return intidcosto;
    }

    public void setIntidcosto(Costo intidcosto) {
        this.intidcosto = intidcosto;
    }

    public Tipousuario getIntidtipo() {
        return intidtipo;
    }

    public void setIntidtipo(Tipousuario intidtipo) {
        this.intidtipo = intidtipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (intidcostousuario != null ? intidcostousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Costousuario)) {
            return false;
        }
        Costousuario other = (Costousuario) object;
        if ((this.intidcostousuario == null && other.intidcostousuario != null) || (this.intidcostousuario != null && !this.intidcostousuario.equals(other.intidcostousuario))) {
            return false;
        }
        return true;
    }

    public String ingresarCostoUsuario(String jsonDatosVenta) {
        String ingreso;
        Gson gson = new Gson();
        Costousuario costoUsuario = new Costousuario();
        Costos costos = new Costos();
        TipoUsuarioWS tipoUsuarioWs = new TipoUsuarioWS();
        CostoUsuarioWS costoUsuarioWS = new CostoUsuarioWS();

        JsonParser parser = new JsonParser();

        try {
            JsonElement elementoJson = parser.parse(jsonDatosVenta);
            JsonObject objJson = elementoJson.getAsJsonObject();

            costoUsuario.intidtipo = tipoUsuarioWs.findByStrTipo(Tipousuario.class, objJson.get("tipousuario").getAsString());
            System.out.println(costoUsuario.intidtipo);
            costoUsuario.strcedula = objJson.get("cedula").getAsString();
            System.out.println(costoUsuario.strcedula);

            costos = gson.fromJson(costos.costosTipoUsuarios(objJson.get("tipousuario").getAsString()), Costos.class);

            for (int i = 0; i < costos.getCostos().size(); i++) {
                costoUsuario.intidcosto = costos.getCostos().get(i);
                costoUsuarioWS.create(costoUsuario);
            }
            
            ingreso = "Ingreso Correcto";
        } catch (JsonSyntaxException | ClientErrorException ex) {
            System.err.println(ex);
            ingreso = "Error en el Ingreso";
        }
        return ingreso;
    }

    @Override
    public String toString() {
        return "com.comedorln.Costousuario[ intidcostousuario=" + intidcostousuario + " ]";
    }

}
