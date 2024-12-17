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
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import modelo.Venta;

/**
 *
 * @author hdzli
 */
@Named(value = "admMesa")
@SessionScoped
public class AdmMesa implements Serializable {
    @Inject
    private AdmPedido admPedido;
    @Inject
    private AdmVenta admVenta;
    
    private ArrayList<Mesa> mesas;

    /**
     * Creates a new instance of AdmMesa
     */
    public AdmMesa() {
        mesas = new ArrayList();
    }
    @PostConstruct
    public void init() {
        addMesas();
        setEstados();
    }
    public Mesa getMesa(int a){
        return mesas.get(a-1);
    }
    public ArrayList<Mesa> getMesas(){
        reset();
        return mesas;
    }
    
    public void setEstados(){
        
        List<Venta> ventas = admVenta.getVentasPorEstado('p');
        System.out.println("tamanio de ventas: "+ventas.size());
        if (ventas == null) {
            System.out.println("La lista de ventas es nula");
            return;
        }
        if (!ventas.isEmpty()) {
            for (Venta v : ventas) {
                int numMesa = v.getNummesa();
                if (numMesa >= 1 && numMesa <= mesas.size()) {
                    mesas.get(numMesa - 1).setEstado(false);
                } else {
                    System.out.println("Número de mesa fuera de rango: " + numMesa);
                }
            }
        }
    }
    
    public void reset(){
        mesas.clear();
        addMesas();
        setEstados();
    }
    
    
    public void addMesas(){
        mesas.add(new Mesa(1, true, 4));
        mesas.add(new Mesa(2, true, 5));
        mesas.add(new Mesa(3, true, 2));
        mesas.add(new Mesa(4, true, 3));
        mesas.add(new Mesa(5, true, 2));
        mesas.add(new Mesa(6, true, 2));
        mesas.add(new Mesa(7, true, 2));
        mesas.add(new Mesa(8, true, 4));
        mesas.add(new Mesa(9, true, 6));
        mesas.add(new Mesa(10, true, 3));
        mesas.add(new Mesa(11, true, 3));
        mesas.add(new Mesa(12, true, 2));
        mesas.add(new Mesa(13, true, 2));
    }
    
    public class Mesa{
        private int numero;
        private boolean estado;
        private int capacidad;

        public Mesa(int numero, boolean estado, int capacidad) {
            this.numero = numero;
            this.estado = estado;
            this.capacidad = capacidad;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public boolean isEstado() {
            reset();
            return estado;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }

        public int getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }
        
        
    }
}
