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
import manipuladatos.MDPedido;
import modelo.Pedido;

/**
 *
 * @author crist
 */
@Named(value = "admPedido")
@SessionScoped
public class AdmPedido implements Serializable {

    @EJB
    private MDPedido mDPedido;

    private List<Pedido> pedidos;
    private Pedido pedido;

    /**
     * Creates a new instance of AdmPedido
     */
    public AdmPedido() {
        pedido = new Pedido();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void addPedido(int m) {
        System.out.println("pedido agregado");
        if (m > 0) {
            pedidos.add(pedido);
        }
    }

}
