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

import Facade.BitacoraServicioSocialFacade;
import Facade.EstudianteFacade;
import Facade.SalaserviciosocialFacade;
import Facade.ZonaServicioSocialFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author jusag
 */
@Named(value = "bitacoraControlador")
@SessionScoped
public class BitacoraControlador implements Serializable {

    /**
     * Creates a new instance of bitacoraControlador
     */
    public BitacoraControlador() {
        estudiante = new Estudiante();
        zona = new ZonaServicioSocial();
        us = new Usuario();
        bitacora = new BitacoraServicioSocial();
        sala = new Salaserviciosocial();
    }

    private BitacoraServicioSocial bitacora;
    private Estudiante estudiante;
    private ZonaServicioSocial zona;
    private Usuario us;
    private String fechaRegistro;
    private String coo;
    private String est;
    private String tpres;
    private Salaserviciosocial sala;
    private int idZona;
    private int idEst;
    private List<Estudiante> estList;
    

    @EJB
    BitacoraServicioSocialFacade bitacoraFacade;

    @EJB
    SalaserviciosocialFacade ssFacade;

    @EJB
    ZonaServicioSocialFacade zonaFacade;

    @EJB
    EstudianteFacade estFacade;
    
    @Inject
    AlertasControlador alerta;
    
    public String registrar() {
        bitacora.setCoordinador(us);
        sala = ssFacade.obtenerSalaActiva(idEst);
        bitacora.setSalaDeServicio(sala);
        bitacora.setEstado(1);
        bitacoraFacade.create(bitacora);
        alerta.setMensaje("AlertaToast('Se registro correctamente','success');");
        estudiante = sala.getEstudiante();
        int tiempo = estudiante.getTiempoServicio()+bitacora.getTiempoPrestado();
        estudiante.setTiempoServicio(tiempo);
        estFacade.edit(estudiante);
        bitacora = new BitacoraServicioSocial();// nose olvide d esscjnxs jv xjvn cxnsjv dsnvj ds --Kevin: Kha?
        return "BitacoraSS";
    }

    public void Remover(BitacoraServicioSocial bitacoraRemover) {
        bitacora = bitacoraRemover;
        bitacora.setEstado(2);
        bitacoraFacade.edit(bitacora);
        alerta.setMensaje("AlertaToast('Eliminación exitosa','success');");
    }

    public String preActualizar(BitacoraServicioSocial biPre) {
        bitacora = biPre;
        sala = bitacora.getSalaDeServicio();
        us = bitacora.getCoordinador();
        return "ActualizarBitacora";
    }

    public String actualizar() {
        bitacora.setCoordinador(us);
        bitacora.setSalaDeServicio(sala);
        bitacoraFacade.edit(bitacora);
        alerta.setMensaje("AlertaToast('Se actualizo correctamente','success');");
        return "BitacoraSS";
    }

    public void obtenerEst(AjaxBehaviorEvent event) {
        if (idZona != 0) {
            estList = new ArrayList<>();
            for (Salaserviciosocial s : ssFacade.findAll()) {
                if (s.getZonaServicio().getIdZonaSS() == idZona && "Aceptado".equals(s.getEstadoServicio())) {
                    estList.add(s.getEstudiante());
                }
            }

        }
    }

    public List<BitacoraServicioSocial> consultarBitacora() {
        return bitacoraFacade.consultarBitacoraServicioSocial(1);
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

    public ZonaServicioSocial getZona() {
        return zona;
    }

    public void setZona(ZonaServicioSocial zona) {
        this.zona = zona;
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCoo() {
        return coo;
    }

    public void setCoo(String coo) {
        this.coo = coo;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public String getTpres() {
        return tpres;
    }

    public void setTpres(String tpres) {
        this.tpres = tpres;
    }

    public Salaserviciosocial getSala() {
        return sala;
    }

    public void setSala(Salaserviciosocial sala) {
        this.sala = sala;
    }

    public int getIdZona() {
        return idZona;
    }

    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    public int getIdEst() {
        return idEst;
    }

    public void setIdEst(int idEst) {
        this.idEst = idEst;
    }

    public List<Estudiante> getEstList() {
        return estList;
    }

    public void setEstList(List<Estudiante> estList) {
        this.estList = estList;
    }

}
