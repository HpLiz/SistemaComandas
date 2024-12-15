/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package manipuladatos;

import accesodatos.UsuarioFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import modelo.Usuario;

/**
 *
 * @author hdzli
 */
@Stateless
@LocalBean
public class MDUsuario {

    @EJB
    private UsuarioFacade usuarioF;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void insertaUsuario(Usuario u){
        usuarioF.create(u);
    }
    public List<Usuario> usuarios(){
        return usuarioF.findAll();
    }
    public Usuario usuarioUP(Usuario u){
        return usuarioF.usuario_usu_pass(u.getUsuario(), u.getPassword());
    }
}
