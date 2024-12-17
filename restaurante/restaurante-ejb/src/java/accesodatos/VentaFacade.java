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
import modelo.Venta;

/**
 *
 * @author hdzli
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "restaurante-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }

    public List<Venta> ventas_por_estado(char est) {
        List<Venta> ventas=null;
        try {
            Query consultaup = em.createNamedQuery("Venta.findByEstado");
            consultaup.setParameter("estado", est);
            ventas = (List<Venta>) consultaup.getResultList();
        } catch (Exception e) {
            System.out.println("Error sql"+e);
            return null;
        }
        return ventas;
    }
    public List<Venta> ventas_por_mesa(int m) {
        List<Venta> mesas=null;
        try {
            Query consultaup = em.createNamedQuery("Venta.findByNummesa");
            consultaup.setParameter("mesa", m);
            mesas = (List<Venta>) consultaup.getResultList();
        } catch (Exception e) {
            System.out.println("Error sql"+e);
            return null;
        }
        return mesas;
    }
    public List<Venta> ventas_por_periodo(Date fi, Date ff) {
        List<Venta> ventas=null;
        try {
            Query consulta = em.createNamedQuery("Venta.findBetweenFecha");
            consulta.setParameter("fechaInicio", fi);
            consulta.setParameter("fechaFin", ff);
            ventas = (List<Venta>) consulta.getResultList();
        } catch (Exception e) {
            System.out.println("Error sql"+e);
            return null;
        }
        return ventas;
    }
    
    public List<Object[]> cantidad_mesa_por_periodo(Date fi, Date ff) {
        List<Object[]> cantidades = null;
        try {
            Query consulta = em.createNamedQuery("Venta.findTiempoMesas");
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
