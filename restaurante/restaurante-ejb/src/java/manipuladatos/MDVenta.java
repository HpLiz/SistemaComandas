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
    
    public List<Venta> getEnProgreso(){
        return ventaFacade.ventas('P');
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
