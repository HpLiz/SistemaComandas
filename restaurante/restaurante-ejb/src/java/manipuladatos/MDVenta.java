/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.VentaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Venta;

/**
 *
 * @author hdzli
 */
@Stateless
@LocalBean
public class MDVenta {

    @EJB
    private VentaFacade ventaFacade;
    
<<<<<<< HEAD
    public List<Venta> getEnProgreso(){
        return ventaFacade.ventas('P');
    }

=======
    public List<Venta> ventas(){
        return ventaFacade.findAll();
    }
    
    public List<Venta> ventasEstado(char e){
        System.out.println("buscando 2...");
        return ventaFacade.ventas(e);
    }

    public void registrarVenta(Venta v){
        ventaFacade.create(v);
    }
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
