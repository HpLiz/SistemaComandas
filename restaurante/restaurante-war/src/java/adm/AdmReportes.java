/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDPedido;
import manipuladatos.MDVenta;
import modelo.Pedido;
import modelo.Producto;
import modelo.Venta;

/**
 *
 * @author hdzli
 */
@Named(value = "admReportes")
@SessionScoped
public class AdmReportes implements Serializable {

    @EJB
    private MDPedido mDPedido;
    @EJB
    private MDVenta mDVenta;
    
    private List<Pedido> pedidos;
    private List<Venta> ventas;
    private List<Producto> productos;
    private List<Object[]> multiuso;
    private double importeTotal = 0;
    private Date fechaInicio;
    private Date fechaFin;
    private String gestion[] = {"false","false","false","false","false"};
    
    public void gestion(int ng){
        for(int n=0; n<gestion.length; n++)
            if(n==ng)
                gestion[ng]="true";
            else
                gestion[n]="false";
    }
    public String getGestion(int ng){
        return gestion[ng];
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Object[]> getMultiuso() {
        return multiuso;
    }

    public void setMultiuso(List<Object[]> multiuso) {
        this.multiuso = multiuso;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
    
    public void cargarPedidos(){
        System.out.println("Cargando pedidos...");
        this.pedidos = mDPedido.pedidos_por_periodo(fechaInicio, fechaFin);
        System.out.println("pedidos encontrados..."+pedidos.size());
        
    }
    
    public void cargarProductos(){
        System.out.println("Cargando productos...");
        multiuso = mDPedido.cantidad_por_periodo(fechaInicio, fechaFin);
        System.out.println("productos encontrados..."+multiuso.size());
        
    }
    public void cargarMesas(){
        System.out.println("Cargando mesas...");
        multiuso = mDVenta.cantidad_mesa_por_periodo(fechaInicio, fechaFin);
        System.out.println("mesas encontrados..."+multiuso.size());
        
    }
    
    public void cargarVentas(){
        System.out.println("Cargando ventas...");
        this.ventas = mDVenta.ventas_por_periodo(fechaInicio, fechaFin);
        System.out.println("ventas encontradas..."+ventas.size());
    }
    
    public double calcularImporte(int c, double p){
        return c * p;
    }
    /**
     * Creates a new instance of AdmReportes
     */
    
    public Date getFechaActual() {
        return new Date();
    }
    public AdmReportes() {
        crearReporte();
    }
    public void crearReporte(){
    }
    

    
    // Consultas
    
    
}
