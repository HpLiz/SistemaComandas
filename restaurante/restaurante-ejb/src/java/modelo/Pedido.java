/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hdzli
 */
@Entity
@Table(name = "PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p"),
    @NamedQuery(name = "Pedido.findByIdpedido", query = "SELECT p FROM Pedido p WHERE p.idpedido = :idpedido"),
    @NamedQuery(name = "Pedido.findByVenta", query = "SELECT p FROM Pedido p WHERE p.idventa = :idventa"),
    @NamedQuery(name = "Pedido.findByEstado", query = "SELECT p FROM Pedido p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pedido.findByEstadoMesa", query = "SELECT p FROM Pedido p WHERE p.estado = :estado"),
    @NamedQuery(name = "Pedido.findByEstadoMesaVenta", query = "SELECT p FROM Pedido p WHERE p.estado = :estado AND p.idventa = :idventa"),
    @NamedQuery(name = "Pedido.findByPeriodo", query = "SELECT p FROM Pedido p WHERE p.idventa.fecha BETWEEN :fechaInicio AND :fechaFin"),
    @NamedQuery(name = "Pedido.findCantidadPeriodo", query = "SELECT p.idventa.fecha as fecha, p.idproducto.idproducto  ,SUM(p.cantidad) as total"+
                                " FROM Pedido p WHERE p.idventa.fecha BETWEEN :fechaInicio AND :fechaFin"+
                                " GROUP BY p.idproducto.idproducto , p.idventa.fecha"),
    @NamedQuery(name = "Pedido.findCantidadPeriodoTipo", query = "SELECT p.idventa.fecha as fecha, p.idproducto.tipo  ,SUM(p.cantidad) as total"+
                                " FROM Pedido p WHERE p.idventa.fecha BETWEEN :fechaInicio AND :fechaFin"+
                                " GROUP BY p.idproducto.tipo , p.idventa.fecha")

})
public class Pedido implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private Character estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPEDIDO")
    private Integer idpedido;
    @JoinColumn(name = "IDEXTRA", referencedColumnName = "IDEXTRA")
    @ManyToOne(optional = false)
    private Extra idextra;
    @JoinColumn(name = "IDPRODUCTO", referencedColumnName = "IDPRODUCTO")
    @ManyToOne(optional = false)
    private Producto idproducto;
    @JoinColumn(name = "IDVENTA", referencedColumnName = "IDVENTA")
    @ManyToOne(optional = false)
    private Venta idventa;

    public Pedido() {
    }

    public Pedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Pedido(Integer idpedido, Character estado) {
        this.idpedido = idpedido;
        this.estado = estado;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }


    public Extra getIdextra() {
        return idextra;
    }

    public void setIdextra(Extra idextra) {
        this.idextra = idextra;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    public Venta getIdventa() {
        return idventa;
    }

    public void setIdventa(Venta idventa) {
        this.idventa = idventa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pedido[ idpedido=" + idpedido + " ]";
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
