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
<<<<<<< HEAD
    private String[] tipos={"Comida","Bebida","Postre"};
    
    private String tipo;
    
=======
    private Producto producto;
    private String[] tipos = {"Comida", "Bebida", "Postre"};

    private String tipo;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
    /**
     * Creates a new instance of AdmProducto
     */
    public AdmProducto() {
    }

    public List<Producto> getProductos() {
<<<<<<< HEAD
        return productos=mDProducto.productos();
=======
        return productos = mDProducto.productos();
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
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

<<<<<<< HEAD
=======
    
    public Producto findProducto(int id){
        for(Producto p:productos){
            if(p.getIdproducto()==id)
                return p;
        }
        return null;
    }
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
}
