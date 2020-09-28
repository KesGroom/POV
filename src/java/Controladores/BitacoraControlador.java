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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

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
    }
    
    private BitacoraServicioSocial bitacora;
    private Estudiante estudiante;
    private ZonaServicioSocial zona;
    private Usuario us;
    private Date fechaRegistro = new Date();
    private String coo;
    private String est;
    private String tpres;
    private Salaserviciosocial sala;
    
    @EJB
    BitacoraServicioSocialFacade bitacoraFacade;
    
    
    
    public String registrar(){
        bitacora.setSalaDeServicio(sala);
        bitacora.setCoordinador(us);

        
        bitacora.setEstado(1);
        bitacoraFacade.create(bitacora);
        bitacora = new BitacoraServicioSocial();// nose olvide d esscjnxs jv xjvn cxnsjv dsnvj ds --Kevin: Kha?
        return "BitacoraSS";
    }

    
     public void Remover(BitacoraServicioSocial bitacoraRemover) {
        bitacora = bitacoraRemover;
        bitacora.setEstado(2);
        bitacoraFacade.edit(bitacora);
    }
    
     public String preActualizar(BitacoraServicioSocial biPre){
        biPre = bitacora;
        return "ActualizarBitacora";
    }
    public void actualizar() {
        bitacoraFacade.edit(bitacora);
    }

     
     
    public List<BitacoraServicioSocial> consultarBitacora(){
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
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

}