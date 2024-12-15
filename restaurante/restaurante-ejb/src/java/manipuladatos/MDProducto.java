/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.ProductoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Producto;

/**
 *
 * @author hdzli
 */
@Stateless
@LocalBean
public class MDProducto {

    @EJB
    private ProductoFacade productoFacade;

    public List<Producto> productos(){
        return productoFacade.findAll();
    }
<<<<<<< HEAD
=======
    
    public Producto productoID(int id){
        return productoFacade.produtoID(id);
    }
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
}
