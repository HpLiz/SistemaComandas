/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesodatos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * @author hdzli
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "restaurante-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario usuario_usu_pass(String usuario, String password){
        Usuario persona=null;
        try{        
            Query consultaup=em.createNamedQuery("Usuario.findByUsuarioPassword");
            consultaup.setParameter("usuario", usuario);
            consultaup.setParameter("password", password);
            persona = (Usuario) consultaup.getSingleResult();
        }catch (Exception e){return null;}
        return persona;
    }
    
}
