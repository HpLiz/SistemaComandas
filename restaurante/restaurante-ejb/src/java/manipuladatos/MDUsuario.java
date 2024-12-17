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

    public void insertaUsuario(Usuario u){
        System.out.println("Insertando "+ u.getUsuario());
        usuarioF.create(u);
    }
    public void modificaUsuario(Usuario u){
        if(u != null){
        System.out.println("Modificando "+ u.getUsuario());
        usuarioF.edit(u);
        }else{
            System.out.println("Error: usuario nulo");
        }
    }
    public List<Usuario> usuarios(){
        return usuarioF.findAll();
    }
    
    public Usuario usuarioUP(Usuario u){
        return usuarioF.persona_usu_pas(u.getUsuario(), u.getPassword());
    }
    
    public Usuario usuarioU(Usuario u){
        return usuarioF.persona_usuario(u.getUsuario());
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
