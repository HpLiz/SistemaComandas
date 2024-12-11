/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDProducto;
import modelo.Producto;

/**
 *
 * @author crist
 */
@Named(value = "admProducto")
@SessionScoped
public class AdmProducto implements Serializable {

    @EJB
    private MDProducto mDProducto;

    private List<Producto> productos;
    private String[] tipos={"Comida","Bebida","Postre"};
    
    private String tipo;
    
    /**
     * Creates a new instance of AdmProducto
     */
    public AdmProducto() {
    }

    public List<Producto> getProductos() {
        return productos=mDProducto.productos();
    }

    public String[] getTipos() {
        return tipos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
