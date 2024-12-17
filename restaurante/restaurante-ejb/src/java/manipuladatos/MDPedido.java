/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.PedidoFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Pedido;
import modelo.Venta;

/**
 *
 * @author hdzli
 */
@Stateless
@LocalBean
public class MDPedido {

    @EJB
    private PedidoFacade pedidoFacade;

    public List<Pedido> getPedidos(Venta v) {
        return pedidoFacade.pedidos(v);
    }

    public void registrarPedido(Pedido p) {
        pedidoFacade.create(p);
    }
    public void actualizarPedido(Pedido p) {
        pedidoFacade.edit(p);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Pedido> pedidos() {
        return pedidoFacade.findAll();
    }
    public List<Pedido> pedidosPendientes() {
        char estado = 'p';
        return pedidoFacade.pedidosPendientes(estado);
    }
    public List<Pedido> pedidosEstadoVenta(Venta v,char estado) {
        return pedidoFacade.pedidosEstadoVenta(v,estado);
    }
    
    public List<Pedido> pedidos_por_periodo(Date fi, Date ff){
        return pedidoFacade.pedidos_por_periodo(fi, ff);
    }
    public List<Object[]> cantidad_por_periodo(Date fi, Date ff){
        return pedidoFacade.cantidad_por_periodo(fi, ff);
    }
    public List<Object[]> cantidad_por_periodo_y_tipo(Date fi, Date ff){
        return pedidoFacade.cantidad_por_periodo_y_tipo(fi, ff);
    }

}
