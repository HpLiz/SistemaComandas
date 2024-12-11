/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author crist
 */
@Named(value = "admProducto")
@SessionScoped
public class AdmProducto implements Serializable {

    /**
     * Creates a new instance of AdmProducto
     */
    public AdmProducto() {
    }
    
}
