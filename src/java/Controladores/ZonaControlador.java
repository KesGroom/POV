/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.ZonaServicioSocial;
import Facade.ZonaServicioSocialFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author jusag
 */
@Named(value = "zonaControlador")
@SessionScoped
public class ZonaControlador implements Serializable {

    /**
     * Creates a new instance of zonaControlador
     */
    public ZonaControlador() {
        zona = new ZonaServicioSocial();
        zonaFacade = new ZonaServicioSocialFacade();
    }

    private ZonaServicioSocial zona;
    private String nmb;
    private String lug;
    private String gado;
    private String tservi;

    @EJB
    ZonaServicioSocialFacade zonaFacade;

    public String registrar() {
        zona.setEstado(1);
        zonaFacade.create(zona);
        zona = new ZonaServicioSocial();
        return "ZonasSS";
    }

     public String preActualizar(ZonaServicioSocial zonaServicioSocialActualizar){
        zona = zonaServicioSocialActualizar;
        return "ActualizarZona";
    }

    public void actualizar() {
        zonaFacade.edit(zona);
    }

    public void Remover(ZonaServicioSocial zonaRemover) {
        zona = zonaRemover;
        zona.setEstado(2);
        zonaFacade.edit(zona);
    }

    public List<ZonaServicioSocial> consultarZona() {
        return zonaFacade.consultarZonaServicioSocial(1);
    }

    public ZonaServicioSocial getZona() {
        return zona;
    }

    public void setZona(ZonaServicioSocial zona) {
        this.zona = zona;
    }

    public String getNmb() {
        return nmb;
    }

    public void setNmb(String nmb) {
        this.nmb = nmb;
    }

    public String getLug() {
        return lug;
    }

    public void setLug(String lug) {
        this.lug = lug;
    }

    public String getGado() {
        return gado;
    }

    public void setGado(String gado) {
        this.gado = gado;
    }

    public String getTservi() {
        return tservi;
    }

    public void setTservi(String tservi) {
        this.tservi = tservi;
    }
    
    
}
    
