/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package adm;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import manipuladatos.MDUsuario;
import modelo.Usuario;

/**
 *
 * @author smf3r
 */
@Named(value = "admUsuario")
@SessionScoped
public class AdmUsuario implements Serializable {

    @EJB
    private MDUsuario mDUsuario;

    /**
     * Creates a new instance of AdmUsuario
     */
    private Usuario usuario;
    String mensaje = "";
    private String pagina = "acceso";
    String msjEstatura = "";

    public String pagina() {
        return pagina;
    }

    public boolean acceso() {
        return (usuario = mDUsuario.usuarioUP(usuario)) != null;
    }

    public String autenticar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        if (acceso()) {
            pagina = "index";
        } else {
            FacesMessage msj = new FacesMessage("usuario o password invalido");
            contexto.addMessage(null, msj);
            creaUsuario();
            pagina = "acceso";
        }
        return pagina;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void registrarUsuario() {
        if (!registrado()) {
            mDUsuario.insertaUsuario(usuario);
            mensaje = "Se registró correctamente";
        } else {
            mensaje = "El usuario ya existe";
            creaUsuario();
        }
    }

    public void creaUsuario() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return mDUsuario.usuarios();
    }

    public boolean registrado() {
        return mDUsuario.usuarioUP(usuario) != null;
    }

    /*public boolean registradoUsuario() {
        return mDUsuario.personaU(usuario) != null;
    }*/

    public void cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession(); // Invalida la sesión actual
        try {
            facesContext.getExternalContext().redirect("acceso.xhtml"); // Redirige a la página de inicio de sesión
        } catch (IOException e) {
            e.printStackTrace(); // Maneja cualquier excepción de redirección
        }
    }

    public String irRegistro() {
        return "registro_usuario.xhtml?faces-redirect=true";
    }

    public String irAcceder() {
        return "acceso.xhtml?faces-redirect=true";
    }
    public AdmUsuario() {
        creaUsuario();
    }
    
}
