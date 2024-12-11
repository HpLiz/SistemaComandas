/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import manipuladatos.MDExtra;
import modelo.Extra;

/**
 *
 * @author crist
 */
@Named(value = "admExtra")
@SessionScoped
public class AdmExtra implements Serializable {

    @EJB
    private MDExtra mDExtra;

    
    private List<Extra> extras;
    
    /**
     * Creates a new instance of AdmExtra
     */
    public AdmExtra() {
    }

    public List<Extra> getExtras() {
        return extras=mDExtra.extras();
    }

    
    
    
}
