


package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import manipuladatos.MDVenta;
import modelo.Venta;

/**
 *
 * @author crist
*/
@Named(value = "admVenta")
@SessionScoped
public class AdmVenta implements Serializable {

    @EJB
    private MDVenta mDVenta;

    
    private List<Venta> ventas;
    private Venta venta;

    private ArrayList<Integer> mesas;
    private int mesa = 0;
    private boolean seleccion = false;

    public AdmVenta() {
        venta = new Venta();
        mesas = new ArrayList<Integer>();
        for(int i=1;i<21;i++)
            mesas.add(i);
        
    }

    public ArrayList<Integer> getMesas() {
        return mesas;
    }

    public void addMesa() {
        if (mesas.size() < 21) {
            mesas.add(mesas.size() + 1);
        }
    }

    public int getMesa() {
        return mesa;
    }

    public void seleccionMesa(int m) {//setmesa
        mesa = m;
        venta = new Venta();
        ventas = mDVenta.ventasEstado('p');
        for (Venta v : ventas) {
            if (v.getNummesa() == m) {
                venta = v;
                System.out.println("encontrada");
            }
        }

        if (venta.getNummesa() != m) {
            venta.setNummesa(mesa);
        }
        seleccion = true;
        System.err.println("cambiado" + m + seleccion);
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    
    /////
    public void generarOrden(boolean v) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensaje = new FacesMessage("registrando...");
        if (v) {
            try {
                if (venta.getNummesa() <= 0) {
                    mensaje = new FacesMessage("Error al registrar...");
                    contexto.addMessage(null, mensaje);
                } else {
                    venta.setFecha(new Date());
                    venta.setEstado('p');
                    mDVenta.registrarVenta(venta);
                    System.out.println("venta iniciada" + venta.getIdventa());
                }

            } catch (Exception e) {
                System.out.println("error registrar venta" + e);
                mensaje = new FacesMessage("Error al registrar venta...");
                contexto.addMessage(null, mensaje);
            }
        }
    }
}

 