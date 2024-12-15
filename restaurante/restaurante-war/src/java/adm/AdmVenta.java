


package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
<<<<<<< HEAD
import java.util.List;
import javax.ejb.EJB;
=======
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
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

<<<<<<< HEAD
=======
    
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
    private List<Venta> ventas;
    private Venta venta;

    private ArrayList<Integer> mesas;
    private int mesa = 0;
    private boolean seleccion = false;

<<<<<<< HEAD
    /**
     * Creates a new instance of AdmVenta
     */
    public AdmVenta() {
        try {
            ventas = mDVenta.getEnProgreso();
        } catch (Exception e) {
            ventas=new ArrayList<Venta>(0);
        }
        mesas = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

        if (ventas != null && ventas.size()>0) {
            int mayorMesa = ventas.stream().mapToInt(Venta::getNummesa).max().orElse(-1);
            if (mayorMesa > 5) {
                for (int i = 5; i < mayorMesa; i++) {
                    addMesa();
                }
            }
        }

=======
    public AdmVenta() {
        venta = new Venta();
        mesas = new ArrayList<Integer>();
        for(int i=1;i<21;i++)
            mesas.add(i);
        
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
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

<<<<<<< HEAD
    public void seleccionMesa(int m) {
        mesa = m;
        seleccion = true;
        System.err.println("cambiado"+m+seleccion);
=======
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
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
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
<<<<<<< HEAD
=======

    
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
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
}

 