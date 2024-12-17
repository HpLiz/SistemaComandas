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

    private List<String> tipospagos = new ArrayList(Arrays.asList("Efectivo", "Transferencia"));
    private String tipopago;
    private Double pago; // Getters y setters 
    private boolean pagoCorrecto;

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        try {
            this.pago = pago;

        } catch (Exception e) {
            System.out.println("setpago" + e);
        }
    }

    public AdmVenta() {
        venta = new Venta();
        mesas = new ArrayList<Integer>();
        for (int i = 1; i < 21; i++) {
            mesas.add(i);
        }

        tipopago = "";
        pago = 0.0;

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

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public void seleccionMesa(int m) {//setmesa
        mesa = m;
        venta = new Venta();
        ventas = mDVenta.ventas_por_estado('p');
        for (Venta v : ventas) {
            if (v.getNummesa() == m) {
                venta = v;
                System.out.println("encontrada");
            }
        }

        System.err.println("cambiado" + venta.getNummesa() + seleccion);
        if (venta.getNummesa() != m) {
            venta.setNummesa(mesa);
        }
        seleccion = true;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public List<Venta> getVentasPorEstado(char e) {
        char estado = e;
        return ventas = mDVenta.ventas_por_estado(e);
    }

    public List<Venta> getVentasPorMesa(int m) {
        int mesa = m;
        return mDVenta.ventas_por_mesa(m);
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

    public boolean isEfectivo() {
        boolean sies = tipopago.equals("Efectivo");
        //System.out.println("efectivo?"+sies);
        return sies;
    }

    public Double cambio(Double importe) {
        Double cambio = 0.0;
        cambio= pago-importe;
        return cambio;
    }

    public void finalizar(Double importe) {
        pagoCorrecto = false;
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensaje = new FacesMessage("registrando...");
        System.out.println("impore y mas" + importe + "|" + pago + "|" + tipopago);
        boolean finalizar = true;
        if (isEfectivo()) {
            if (this.pago < importe) {
                finalizar = false;
                mensaje = new FacesMessage("Ingrese un importe valido...");
                contexto.addMessage(null, mensaje);
                System.out.println("pago invalido");
            }

        }
        if (finalizar) {
            System.out.println("pagando...");
            try {
                venta.setEstado('t');
                mDVenta.actualizarVenta(venta);
                pagoCorrecto = true;
                System.out.println("finalizado");
                mensaje = new FacesMessage("Pedido Finalizado...");
                contexto.addMessage(null, mensaje);
            } catch (Exception e) {
                pagoCorrecto = false;
                System.out.println("error finalizar" + e);
                mensaje = new FacesMessage("Error al finalizar...");
                contexto.addMessage(null, mensaje);
            }
        }
    }

    public List<String> getTipospagos() {
        return tipospagos;
    }

    public void setTipospagos(List<String> tipospagos) {
        this.tipospagos = tipospagos;
    }

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        //System.out.println("si");
        this.tipopago = tipopago;
    }

    public boolean isPagoCorrecto() {
        return pagoCorrecto;
    }

}
