/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
<<<<<<< HEAD
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDPedido;
import modelo.Pedido;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import manipuladatos.MDPedido;
import modelo.Extra;
import modelo.Pedido;
import modelo.Producto;
import modelo.Venta;
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5

/**
 *
 * @author crist
 */
@Named(value = "admPedido")
@SessionScoped
public class AdmPedido implements Serializable {

    @EJB
    private MDPedido mDPedido;

    private List<Pedido> pedidos;
<<<<<<< HEAD
    private Pedido pedido;
=======
    private List<Pedido> nuevos;
    private Pedido pedido;
    private Venta venta;

    private int idProducto;
    private int idExtra;

    private boolean actualizar = false;

    public boolean isActualizar() {
        return actualizar;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(int idExtra) {
        this.idExtra = idExtra;
    }
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5

    /**
     * Creates a new instance of AdmPedido
     */
    public AdmPedido() {
        pedido = new Pedido();
<<<<<<< HEAD
    }

    public List<Pedido> getPedidos() {
        return pedidos;
=======
        nuevos = new ArrayList<Pedido>();
        pedidos = new ArrayList<Pedido>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;//= mDPedido.pedidos();
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void addPedido(int m) {
<<<<<<< HEAD
        System.out.println("pedido agregado");
        if (m > 0) {
            pedidos.add(pedido);
=======
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensaje = new FacesMessage("cargando...");
        System.out.println("pedido agregado");
        try {
            if (m > 0) {
                pedido.setEstado('p');
                pedido.setIdventa(venta);

                boolean valid = true;

                if (Objects.isNull(pedido.getIdproducto())) {
                    valid = false;
                    mensaje = new FacesMessage("Seleccione un producto...");
                    contexto.addMessage(null, mensaje);
                }
                if (valid) {
                    idExtra = 0;
                    idProducto = 0;
                    pedidos.add(pedido);
                    nuevos.add(pedido);
                    pedido = new Pedido();
                    //System.out.println("pedidos" + pedidos.toString());
                    //System.out.println("nuevos" + nuevos.toString());
                }
            } else {
                mensaje = new FacesMessage("Seleccione una mesa...");
                contexto.addMessage(null, mensaje);
            }
        } catch (Exception e) {
            System.out.println("Error al agregar" + e);
        }
    }

    public void actualizarVenta(Venta v) {
        this.venta = v;
        pedido.setIdventa(venta);
        pedidos.clear();
        pedidos = mDPedido.getPedidos(venta);
        actualizar = false;
        if (pedidos.size() > 0) {
            actualizar = true;
        }
        System.out.println("Venta actualizada" + venta.getNummesa());
    }

    public void setProducto(Producto p) {
        System.out.println("producto cambiado" + p.getNombre());
        pedido.setIdproducto(p);
    }

    public void setExtra(Extra e) {
        System.out.println("Extra cambiado" + e.getNombre());
        pedido.setIdextra(e);
    }

    public String estado(char e) {
        switch (e) {
            case 'p':
                return "Pendiente";
            case 'f':
                return "Finalizado";
            case 'c':
                return "Cancelado";
            default:
                return "";
        }
    }

    public void generarOrden() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensaje = new FacesMessage("registrando...");

        boolean condicionCumplida = false;
        int tiempoMaximoEspera = 5000; // 5 segundos
        int intervaloChequeo = 100; // Intervalo de chequeo en milisegundos
        long tiempoInicio = System.currentTimeMillis();

        while (!condicionCumplida && (System.currentTimeMillis() - tiempoInicio) < tiempoMaximoEspera) {
            // Simular chequeo de condición
            condicionCumplida = venta.getIdventa() != null;

            if (!condicionCumplida) {
                //contexto.addMessage(null, mensaje);
                try {
                    Thread.sleep(intervaloChequeo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            if (venta.getIdventa() == null || nuevos.get(0).getIdventa() == null) {
                mensaje = new FacesMessage("Error al registrar...");
                contexto.addMessage(null, mensaje);
            } else {
                mensaje = new FacesMessage("Registrado Correctamente...");
                contexto.addMessage(null, mensaje);
                nuevos.forEach(p -> {
                    mDPedido.registrarPedido(p);
                });
                nuevos.clear();
                actualizar = true;
            }

        } catch (Exception e) {
            System.out.println("error registrar pedido" + e);
            mensaje = new FacesMessage("Error al registrar pedido...");
            contexto.addMessage(null, mensaje);
        }
    }

    public boolean guardarPedido() {//verifica si es necesario generar la venta (que si hayan elementos que añadir al pedido)
        if (nuevos.size() > 0) {
            return true;
        }
        return false;
    }

    public void actualizarOrden() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        FacesMessage mensaje = new FacesMessage("actualizando...");
        contexto.addMessage(null, mensaje);
        
        try {
            if (venta.getIdventa() == null || nuevos.get(0).getIdventa() == null) {
                mensaje = new FacesMessage("Error al registrar...");
                contexto.addMessage(null, mensaje);
            } else {
                mensaje = new FacesMessage("Actualizado Correctamente...");
                contexto.addMessage(null, mensaje);
                nuevos.forEach(p -> {
                    mDPedido.registrarPedido(p);
                });
                nuevos.clear();
            }

        } catch (Exception e) {
            System.out.println("error registrar pedido" + e);
            mensaje = new FacesMessage("Error al registrar pedido...");
            contexto.addMessage(null, mensaje);
>>>>>>> 1308215506b6d74a9f78e65cff2862eecddc76e5
        }
    }

}
