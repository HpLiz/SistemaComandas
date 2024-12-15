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

    public List<Venta> ventas(char est) {
        List<Venta> ventas=null;
        try {
            Query consultaup = em.createNamedQuery("Venta.findByEstado");
            consultaup.setParameter("estado", est);
            ventas = (List<Venta>) consultaup.getResultList();
        } catch (Exception e) {
            return null;
        }
        return ventas;
    }
}
