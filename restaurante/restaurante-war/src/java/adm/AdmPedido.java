/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import manipuladatos.MDPedido;
import modelo.Extra;
import modelo.Pedido;
import modelo.Producto;
import modelo.Venta;

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
    private List<Pedido> nuevos;
    private List<Pedido> comidas;
    private List<Pedido> bebidas;
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

    /**
     * Creates a new instance of AdmPedido
     */
    public AdmPedido() {
        pedido = new Pedido();
        pedido.setCantidad(1);
        nuevos = new ArrayList<Pedido>();
        pedidos = new ArrayList<Pedido>();
    }

    @PostConstruct
    public void init() {
        comidas = getComidasPendientes(venta);
        bebidas = getBebidasPendientes(venta);
    }

    public List<Pedido> getPedidos() {
        return pedidos;//= mDPedido.pedidos();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void addPedido(int m) {
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

                if (pedido.getCantidad() < 1) {
                    valid = false;
                    mensaje = new FacesMessage("La cantidad debe ser mayor a 0...");
                    contexto.addMessage(null, mensaje);
                }
                if (valid) {
                    idExtra = 0;
                    idProducto = 0;
                    pedidos.add(pedido);
                    nuevos.add(pedido);
                    pedido = new Pedido();
                    pedido.setCantidad(1);
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
        try {
            this.venta = v;
            pedido.setIdventa(venta);
            pedidos.clear();
            pedidos = mDPedido.getPedidos(venta);
            System.out.println("cantidad" + pedidos.size());
            actualizar = false;
            if (!pedidos.isEmpty()) {
                this.actualizar = true;
            }
            System.out.println("Venta actualizada" + venta.getNummesa());

        } catch (Exception e) {
            System.out.println("error cambiando venta pedido" + e);
        }

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
            case 't':
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
        }
    }

    // Mostrar y actualizar pedidos pendientes
    // Para comidas y postres -> ambos van a la vista de cocina
    public List<Pedido> getComidasPendientes(Venta v) {
        List<Pedido> cm = mDPedido.pedidosEstadoVenta(v, 'p');
        List<Pedido> auxc = new ArrayList<Pedido>();
        for (Pedido ped : cm) {
            if (!ped.getIdproducto().getTipo().equals("Bebida")) {
                auxc.add(ped);
            }
        }
        return auxc;
    }

    public List<Pedido> getBebidasPendientes(Venta v) {
        List<Pedido> bb = mDPedido.pedidosEstadoVenta(v,'p');
        List<Pedido> auxb = new ArrayList<Pedido>();
        for (Pedido ped : bb) {
            if (ped.getIdproducto().getTipo().equals("Bebida")) {
                auxb.add(ped);
            }
        }
        return auxb;
    }

    public void actualizarEstadoP(Pedido p) {
        p.setEstado('t');
        mDPedido.actualizarPedido(p);
    }

    public void actualizarEstadoP(Pedido p, char est) {
        p.setEstado(est);
        mDPedido.actualizarPedido(p);
    }

    public Double getImporte() {
        Double importe = 0.0;

        for (Pedido p : pedidos) {
            if (p.getEstado() == 't') {
                importe += p.getIdproducto().getPrecio() * p.getCantidad();
                if (p.getIdextra() != null) {
                    importe += p.getIdextra().getPrecio() * p.getCantidad();
                }
            }
        }
        return importe;
    }

    public void cancelarPedido(Pedido p) {
        p.setEstado('c');
        if (!nuevos.contains(p)) {
            mDPedido.actualizarPedido(p);
        }
    }

    public void cancelarPorFinalizacion(Venta v) {

        boolean condicionCumplida = false;
        int tiempoMaximoEspera = 200; // 5 segundos
        int intervaloChequeo = 10; // Intervalo de chequeo en milisegundos
        long tiempoInicio = System.currentTimeMillis();

        while (!condicionCumplida && (System.currentTimeMillis() - tiempoInicio) < tiempoMaximoEspera) {
            // Simular chequeo de condición
            condicionCumplida = venta.getEstado() == 'c';

            if (!condicionCumplida) {
                //contexto.addMessage(null, mensaje);
                try {
                    Thread.sleep(intervaloChequeo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        if (v.getEstado() == 'c' || v.getEstado() == 't') {
            actualizarVenta(v);
            for (Pedido p : pedidos) {
                if (p.getEstado() == 'p') {
                    actualizarEstadoP(p, 'c');
                }
            }
        }
    }

}
