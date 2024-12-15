/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Producto;

/**
 *
 * @author hdzli
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "restaurante-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
<<<<<<< HEAD
=======
    public Producto produtoID(int id) {
        Producto produto=null;
        try {
            Query consultaup = em.createNamedQuery("Producto.findByIdproducto");
            consultaup.setParameter("idproducto", id);
            produto = (Producto) consultaup.getResultList();
        } catch (Exception e) {
            System.out.println("Error fecade");
            return null;
        }
        return produto;
    }
    
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
    
}
