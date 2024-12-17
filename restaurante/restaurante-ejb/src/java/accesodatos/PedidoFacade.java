/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import java.util.Date;
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
        List<Pedido> pedidos = null;
        try {
            Query consultaup = em.createNamedQuery("Pedido.findByVenta");
            consultaup.setParameter("idventa", v);
            pedidos = (List<Pedido>) consultaup.getResultList();
        } catch (Exception e) {
            return null;
        }
        return pedidos;
    }

    //Este metodo recupera los pedidos con estado pendiente
    public List<Pedido> pedidosPendientes(char estado) {
        List<Pedido> pedidos = null;
        try {
            Query consulta = em.createNamedQuery("Pedido.findByEstadoMesa");
            consulta.setParameter("estado", estado);
            pedidos = (List<Pedido>) consulta.getResultList();
        } catch (Exception e) {
            System.out.println("Error sql" + e);

            return null;
        }
        return pedidos;
    }

    public List<Pedido> pedidosEstadoVenta(Venta v, char estado) {
        List<Pedido> pedidos = null;
        try {
            Query consulta = em.createNamedQuery("Pedido.findByEstadoMesaVenta");
            consulta.setParameter("estado", estado);
            consulta.setParameter("idventa", v);
            pedidos = (List<Pedido>) consulta.getResultList();
        } catch (Exception e) {
            System.out.println("Error sql" + e);
            return null;
        }
        return pedidos;
    }

    public List<Pedido> pedidos_por_periodo(Date fi, Date ff) {
        List<Pedido> pedidos = null;
        try {
            Query consulta = em.createNamedQuery("Pedido.findByPeriodo");
            consulta.setParameter("fechaInicio", fi);
            consulta.setParameter("fechaFin", ff);
            pedidos = (List<Pedido>) consulta.getResultList();
        } catch (Exception e) {
            System.out.println("Error sql" + e);
            return null;
        }
        return pedidos;
    }

    public List<Object[]> cantidad_por_periodo(Date fi, Date ff) {
        List<Object[]> cantidades = null;
        try {
            Query consulta = em.createNamedQuery("Pedido.findCantidadPeriodo");
            consulta.setParameter("fechaInicio", fi);
            consulta.setParameter("fechaFin", ff);
            cantidades = (List) consulta.getResultList();
        } catch (Exception e) {
            System.out.println("Error sql" + e);
            return null;
        }
        return cantidades;
    }
}
