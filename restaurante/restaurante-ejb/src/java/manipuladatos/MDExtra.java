/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.ExtraFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Extra;

/**
 *
 * @author hdzli
 */
@Stateless
@LocalBean
public class MDExtra {

    @EJB
    private ExtraFacade extraFacade;

    public List<Extra> extras(){
        return extraFacade.findAll();
    }
}
