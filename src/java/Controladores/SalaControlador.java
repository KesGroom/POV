/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.BitacoraServicioSocial;
import Entidades.Estudiante;
import Entidades.Salaserviciosocial;
import Entidades.Usuario;
import Entidades.ZonaServicioSocial;
import Facade.EstudianteFacade;
import Facade.SalaserviciosocialFacade;
import Facade.UsuarioFacade;
import Facade.ZonaServicioSocialFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author kesgr
 */
@Named(value = "salaControlador")
@SessionScoped
public class SalaControlador implements Serializable {

    //----- Builders ---------------------------------------------------------\\
    public SalaControlador() {
        sala = new Salaserviciosocial();
        zona = new ZonaServicioSocial();
        bitacora = new BitacoraServicioSocial();
        estudiante = new Estudiante();
    }
    //----- Attributes -------------------------------------------------------\\
    private Salaserviciosocial sala;
    private ZonaServicioSocial zona;
    private BitacoraServicioSocial bitacora;
    private Estudiante estudiante;
    private Date fecha = new Date();
    private Usuario usuarioPrueba;
    private String Documento;
    private String Correo;

    @Inject
    AlertasControlador alerta;

    @EJB
    SalaserviciosocialFacade salaFacade;

    @EJB
    ZonaServicioSocialFacade zonaFacade;

    @EJB
    EstudianteFacade estFacade;

    @EJB
    UsuarioFacade usuarioFacade;

    @Inject
    CorreoControlador1 correo;

    //----- Methods ----------------------------------------------------------\\
    public void postular(int zonaPostular, int estu) throws NoSuchProviderException, MessagingException {
        usuarioPrueba = usuarioFacade.OlvidoContra(Documento, Correo);
        Salaserviciosocial salaPrueba = salaFacade.obtenerSala(estu);
        if (salaPrueba == null) {
            sala.setFechaPostulacion(fecha);
            sala.setEstadoServicio("En espera");
            estudiante = estFacade.EstudianteDocIn(estu);
            sala.setEstudiante(estudiante);
            zona = zonaFacade.obtenerZona(zonaPostular);
            sala.setZonaServicio(zona);
            salaFacade.create(sala);
            correo.setAsunto("Su postulación a la zona "+sala.getZonaServicio().getNombre()+ " fue registrada");
            correo.setCuerpo("Si dentro de 5 dias no recibe una respuesta podra postularse a otra zona");
            correo.enviarEmail(getEstudiante().getUsuario().getCorreoElectronico(), "", correo.pagina());
            alerta.setMensaje("AlertaToast('Postulación exitosa','success');");
            this.sala = new Salaserviciosocial();
            this.zona = null;

        } else {
            alerta.setMensaje("AlertaPopUp('Ha ocurrido un error','Ya se ha postulado a una zona de servicio, por favor espere una respuesta','error');");
        }
    }

    public void sinRespuesta() {
        int milisecondsByDay = 86400000;
        for (Salaserviciosocial s : salaFacade.findAll()) {
            int dias = (int) ((s.getFechaPostulacion().getTime() - fecha.getTime()) / milisecondsByDay);
            if (dias < 0) {
                dias = (int) ((fecha.getTime() - s.getFechaPostulacion().getTime()) / milisecondsByDay);
            }
            if (dias >= 5 && "En espera".equals(s.getEstadoServicio())) {
                s.setEstadoServicio("Rechazado");
                salaFacade.edit(s);
            }
        }
    }

    public void aceptar(Salaserviciosocial salaAcep) throws NoSuchProviderException, MessagingException {
        sala = salaAcep;
        sala.setEstadoServicio("Aceptado");
        salaFacade.edit(sala);
        estudiante = sala.getEstudiante();
        estudiante.setEstadoServicioSocial(2);
        estFacade.edit(estudiante);
        correo.setAsunto("Fuiste aceptado");
        correo.setCuerpo("Querido estudiante " + sala.getEstudiante().getUsuario().getNombre() + " " + sala.getEstudiante().getUsuario().getApellido() + " la zona " + sala.getZonaServicio().getNombre() + " acepto tu postulación");
        correo.enviarEmail(getEstudiante().getUsuario().getCorreoElectronico(), "", correo.pagina());
        alerta.setMensaje("AlertaToast('Estudiante rechazado','succces');");
    }

    public void rechazar(Salaserviciosocial salaAcep) throws NoSuchProviderException, MessagingException {
        sala = salaAcep;
        sala.setEstadoServicio("Rechazado");
        salaFacade.edit(sala);
        correo.setAsunto("Fuiste rechazado");
        correo.setCuerpo("Querido estudiante " + sala.getEstudiante().getUsuario().getNombre() + " " + sala.getEstudiante().getUsuario().getApellido() + " la zona " + sala.getZonaServicio().getNombre() + " a rechazado tu postulación");
        correo.enviarEmail(getEstudiante().getUsuario().getCorreoElectronico(), "", correo.pagina());
        alerta.setMensaje("AlertaToast('Estudiante rechazado','error');");
    }

    public List<Salaserviciosocial> consultarSala(int id) {
        return salaFacade.consultarSalaServicioSocial(id);
    }

    public List<Salaserviciosocial> consultarSalaCo() {
        return salaFacade.consultarSalaServicioSocialCo();
    }

    //----- Getters and Setters ----------------------------------------------\\
    public Salaserviciosocial getSala() {
        return sala;
    }

    public void setSala(Salaserviciosocial sala) {
        this.sala = sala;
    }

    public ZonaServicioSocial getZona() {
        return zona;
    }

    public void setZona(ZonaServicioSocial zona) {
        this.zona = zona;
    }

    public BitacoraServicioSocial getBitacora() {
        return bitacora;
    }

    public void setBitacora(BitacoraServicioSocial bitacora) {
        this.bitacora = bitacora;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
