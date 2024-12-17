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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import manipuladatos.MDUsuario;
import modelo.Usuario;

/**
 *
 * @author hdzli
 */
@Named(value = "admUsuario")
@SessionScoped
public class AdmUsuario implements Serializable {

    @EJB
    private MDUsuario mDUsuario;
    
    private Usuario usuario;
    private List<Usuario> usuarios;
    private String pagina = "acceso";
    private String mensaje = "";
    private String gestion[] = {"false","false","false"};

    public String pagina(){
        return pagina;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios(){
        return mDUsuario.usuarios();
    }
    public void cargarUsuarios(){
        this.usuarios = mDUsuario.usuarios();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void gestion(int ng, Usuario u){
        setUsuario(u);
        for(int n=0; n<3; n++)
            if(n==ng)
                gestion[ng]="true";
            else
                gestion[n]="false";
    }
    public String getGestion(int ng){
        return gestion[ng];
    }
    
    public Usuario acceso(){
        System.out.println(usuario.getUsuario() + " " + usuario.getPassword());
        return mDUsuario.usuarioUP(usuario);
                 
    }
    public String autenticar(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        usuario = acceso();
        if(usuario!=null){
            String puesto = usuario.getPuesto();
            switch (puesto) {
                case "Cocina":
                    pagina = "cocina";          
                    break;
                case "Bebidas":
                    pagina = "bebidas";          
                    break;
                case "Mesero":
                    pagina = "mesas";          
                    break;
                case "admin":
                    pagina = "dashboard";          
                    break;
                default:
                    throw new AssertionError();
            }
            
        }else{
            FacesMessage msj = new FacesMessage("Usuario o password incorrectos");
            contexto.addMessage(null, msj);
            creaUsuario();
            pagina = "acceso";
        }
        return pagina;
    }
    
    public void creaUsuario(){
        usuario = new Usuario();
    }
    /**
     * Creates a new instance of AdmUsuario
     */
    public AdmUsuario() {
        creaUsuario();
    }
    public boolean registrado(){
        return mDUsuario.usuarioU(usuario)!=null;
    }
    
    public void registroPersona(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        if(!registrado()){
            mDUsuario.insertaUsuario(usuario);
            creaUsuario();
            FacesMessage msj = new FacesMessage("Usuario registrado correctamente");
            contexto.addMessage(null, msj);
        }else{
            FacesMessage msj = new FacesMessage("Usuario existente");
            contexto.addMessage(null, msj);
            mDUsuario.insertaUsuario(usuario);
        }
    }
    public void modificarPersona(){
        FacesContext contexto = FacesContext.getCurrentInstance();
        mDUsuario.modificaUsuario(usuario);
        FacesMessage msj = new FacesMessage("Datos modificados correctamente");
        contexto.addMessage(null, msj);
    }
    
}
