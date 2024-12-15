/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Pedido;
import modelo.Venta;

/**
 *
 * @author hdzli
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "restaurante-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    public List<Pedido> pedidos(Venta v) {
        List<Pedido> pedidos=null;
        try {
            Query consultaup = em.createNamedQuery("Pedido.findByVenta");
            consultaup.setParameter("idventa", v);
            pedidos = (List<Pedido>) consultaup.getResultList();
        } catch (Exception e) {
            return null;
        }
        return pedidos;
    }
}
