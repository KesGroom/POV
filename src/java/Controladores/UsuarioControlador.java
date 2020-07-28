/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Rol;
import Entidades.Tipo;
import Entidades.Usuario;
import Facade.RolFacade;
import Facade.TipoFacade;
import Facade.UsuarioFacade;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.Integer.getInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jusag
 */
@Named(value = "usuarioControlador")
@SessionScoped
public class UsuarioControlador implements Serializable {

    /**
     * Creates a new instance of usuarioControlador
     */
    public UsuarioControlador() {
        rol = new Rol();
        tipo = new Tipo();
        usuario = new Usuario();
        usuarioPrueba = new Usuario();
        usuarioFacade = new UsuarioFacade();
    }

    private Usuario usuario;
    private Usuario usuarioPrueba;
    private Rol rol;
    private Tipo tipo;
    private String FotoSeleccionada;
    private String numDocumento;

    //Variables para el olvido de contraseña
    private String Documento;
    private String Correo;
    private String Codigo = "MC-";
    private String Code;
    private String digito;
    private String Cod = "MC-" + Code;

    @Inject
    AlertasControlador alerta;

    @EJB
    UsuarioFacade usuarioFacade;

    @EJB
    RolFacade rolFacade;

    @EJB
    TipoFacade tipoFacade;

    public String registrarUsu() {
        usuarioPrueba = usuarioFacade.validacionDoc(numDocumento);
        if (usuarioPrueba == null) {
            usuario.setEstado(1);
            usuario.setNumerodeDocumento(numDocumento);
            usuario.setFoto("../img/imgPerfil/default-user.png");
            usuario.setIdRoles(rol);
            usuario.setIdTipo(tipo);
            usuarioFacade.create(usuario);
            usuario = new Usuario();
            alerta.setMensaje("AlertaToast('Usuario registrado con éxito','success');");

            return "Usuarios";

        } else {
            alerta.setMensaje("AlertaPopUp('Usuario existente','El usuario que desea registrar ya existe en el sistema','error');");
            return "Usuarios";
        }

    }

    public String Remover(Usuario usuarioRemover) {
        usuario = usuarioRemover;
        if (usuario.getIdRoles().getIdRoles() != 1) {
            usuario.setEstado(2);
            usuarioFacade.edit(usuario);
            alerta.setMensaje("AlertaToast('El usuario "+usuario.getNombre()+" ha sido eliminado','success');");
            return "Usuarios";
        }
        else{
            alerta.setMensaje("AlertaPopUp('Error al eliminar','El usuario "+usuario.getNombre()+" no puede ser eliminado porque es un Coordinador','error');");
            return "Usuarios";
        }
    }

    public String preActualizar(Usuario usuarioPre) {
        usuario = usuarioPre;
        rol = usuarioPre.getIdRoles();
        tipo = usuarioPre.getIdTipo();

        return "UsuariosActualizar";
    }

    public String actualizar() {
        usuario.setIdRoles(rolFacade.find(rol.getIdRoles()));
        usuario.setIdTipo(tipoFacade.find(tipo.getIdTipo()));
        usuarioFacade.edit(usuario);
        return "Usuarios";
    }

    public String actualizarPerfil(Usuario usuarioActualizar) {
        usuario = usuarioActualizar;
        rol = usuarioActualizar.getIdRoles();
        tipo = usuarioActualizar.getIdTipo();

        return "ActualizarPerfil";
    }

    public String actualizarPhoto(Usuario usuarioActualizar) {
        usuario = usuarioActualizar;
        rol = usuarioActualizar.getIdRoles();
        tipo = usuarioActualizar.getIdTipo();

        return "Avatar";
    }

    public String actualizarP() {
        usuario.setIdRoles(rol);
        usuario.setIdTipo(tipo);
        usuarioFacade.edit(usuario);
        usuario = new Usuario();

        return "Perfil";
    }

    public String actualizarFoto() {
        usuario.setIdRoles(rol);
        usuario.setIdTipo(tipo);
        usuarioFacade.edit(usuario);
        usuario = new Usuario();

        return "Perfil";
    }

    public void fotoPrueba() {

        this.FotoSeleccionada = this.usuario.getFoto();

    }

    public String olvido() throws NoSuchProviderException, MessagingException {

        usuarioPrueba = usuarioFacade.OlvidoContra(Documento, Correo);

        if (usuarioPrueba != null) {

            for (int i = 0; i < 6; i++) {

                int num = (int) Math.floor(Math.random() * 10);
                digito = String.valueOf(num);

                Codigo = Codigo + digito;

            }

            Properties propiedad = new Properties();
            propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedad.setProperty("mail.smtp.starttls.enable", "true");
            propiedad.setProperty("mail.smtp.port", "587");
            propiedad.setProperty("mail.smtp.auth", "true");

            Session sesion = Session.getDefaultInstance(propiedad);

            String correoEnvia = "mariacano.pov@gmail.com";
            String contrasena = "PovGaes7";
            String destinatario = Correo;
            String asunto = "Código de reestablecimiento de contraseña";
            String mensaje = "Estimad@ " + usuarioPrueba.getNombre() + " se ha solicitado realizar el reestablecimiento de su contraseña.\n"
                    + "Si usted no ha realizado está solicitud le recomendamos ingresar a su perfil y validar su información, "
                    + "y comunicarse lo antes posible con la institución a tráves del correo MariaCano.Pov@gmail.com o llamando al 372 6691.\n\n"
                    + "\t Su código de válidación es: " + Codigo;

            MimeMessage mail = new MimeMessage(sesion);

            try {
                mail.setFrom(new InternetAddress(correoEnvia));
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
                mail.setSubject(asunto);
                mail.setText(mensaje);

                Transport transporte = sesion.getTransport("smtp");
                transporte.connect(correoEnvia, contrasena);
                transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                transporte.close();

            } catch (AddressException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            return "Validacion";
        } else {
            return "";
        }

    }

    public String validacion() {
        if (Codigo.equals(Cod)) {
            return "DefinirContra";
        } else {
            return "";
        }
    }

    public List<Usuario> conseguirUsuario(int id) {
        return usuarioFacade.obtenerUsuario(id);
    }

    public List<Usuario> consultarUsuarios() {
        return usuarioFacade.consultarUsuario(1);
    }

    public List<Usuario> buscarRol(int rol) {
        return usuarioFacade.busquedaRol(rol);
    }

    //Getters and Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getFotoSeleccionada() {
        return FotoSeleccionada;
    }

    public void setFotoSeleccionada(String FotoSeleccionada) {
        this.FotoSeleccionada = FotoSeleccionada;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public Usuario getUsuarioPrueba() {
        return usuarioPrueba;
    }

    public void setUsuarioPrueba(Usuario usuarioPrueba) {
        this.usuarioPrueba = usuarioPrueba;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getCod() {
        return Cod;
    }

    public void setCod(String Cod) {
        this.Cod = Cod;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

}
