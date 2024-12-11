/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
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

    public void seleccionMesa(int m) {
        mesa = m;
        seleccion = true;
        System.err.println("cambiado"+m+seleccion);
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
}
