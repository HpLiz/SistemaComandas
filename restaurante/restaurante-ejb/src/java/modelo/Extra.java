/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hdzli
 */
@Entity
@Table(name = "EXTRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extra.findAll", query = "SELECT e FROM Extra e"),
    @NamedQuery(name = "Extra.findByIdextra", query = "SELECT e FROM Extra e WHERE e.idextra = :idextra"),
    @NamedQuery(name = "Extra.findByNombre", query = "SELECT e FROM Extra e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Extra.findByPrecio", query = "SELECT e FROM Extra e WHERE e.precio = :precio"),
    @NamedQuery(name = "Extra.findByDescripcion", query = "SELECT e FROM Extra e WHERE e.descripcion = :descripcion")})
public class Extra implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO")
    private double precio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDEXTRA")
    private Integer idextra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idextra")
    private List<Pedido> pedidoList;

    public Extra() {
    }

    public Extra(Integer idextra) {
        this.idextra = idextra;
    }

    public Extra(Integer idextra, String nombre, double precio, String descripcion) {
        this.idextra = idextra;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Integer getIdextra() {
        return idextra;
    }

    public void setIdextra(Integer idextra) {
        this.idextra = idextra;
    }


    @XmlTransient
    public List<Pedido> getPedidoList() {
        return pedidoList;
    }

    public void setPedidoList(List<Pedido> pedidoList) {
        this.pedidoList = pedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idextra != null ? idextra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Extra)) {
            return false;
        }
        Extra other = (Extra) object;
        if ((this.idextra == null && other.idextra != null) || (this.idextra != null && !this.idextra.equals(other.idextra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Extra[ idextra=" + idextra + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
