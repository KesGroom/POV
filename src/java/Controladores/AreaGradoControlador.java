/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Area;
import Entidades.Areagrado;
import Entidades.Grado;
import Facade.AreaFacade;
import Facade.AreaGradoFacade;
import Facade.GradoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author kesgr
 */
@Named(value = "areaGradoControlador")
@SessionScoped
public class AreaGradoControlador implements Serializable {


    //----- Builders ---------------------------------------------------------\\

    public AreaGradoControlador() {
        area = new Area();
        grado = new Grado();
        AG = new Areagrado();
        Primero = false;
        Segundo = false;
        Tercero = false;
        Cuarto = false;
        Quinto = false;
        Sexto = false;
        Septimo = false;
        Octavo = false;
        Noveno = false;
        Decimo = false;
        Once = false;
    }


    //----- Attributes -------------------------------------------------------\\

    private Area area;
    private Grado grado;
    private Areagrado AG;
    
      //----Booleans Grades ------------------------------------------------\\
        private boolean Primero;
        private boolean Segundo;
        private boolean Tercero;
        private boolean Cuarto;
        private boolean Quinto;
        private boolean Sexto;
        private boolean Septimo;
        private boolean Octavo;
        private boolean Noveno;
        private boolean Decimo;
        private boolean Once;
    
    @EJB
    AreaFacade areaFacade;
    
    @EJB
    GradoFacade gradoFacade;
    
    @EJB
    AreaGradoFacade AGFacade;
    
    
    @Inject
    AlertasControlador alerta;
    
    //----- Methods ----------------------------------------------------------\\

    public void registrar(){
        area.setEstado(1);
        areaFacade.create(area);
        if(Primero){
            grado.setIdGrado(1);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Segundo){
            grado.setIdGrado(2);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Tercero){
            grado.setIdGrado(3);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Cuarto){
            grado.setIdGrado(4);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Quinto){
            grado.setIdGrado(5);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Sexto){
            grado.setIdGrado(6);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Septimo){
            grado.setIdGrado(7);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Octavo){
            grado.setIdGrado(8);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Noveno){
            grado.setIdGrado(9);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Decimo){
            grado.setIdGrado(10);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        if(Once){
            grado.setIdGrado(11);
            AG.setArea(area);
            AG.setGrado(grado);
            AG.setEstado(1);
            AGFacade.create(AG);
        }
        
        this.Primero = false;
        this.Segundo = false;
        this.Tercero = false;
        this.Cuarto = false;
        this.Quinto = false;
        this.Sexto = false;
        this.Septimo = false;
        this.Octavo = false;
        this.Noveno = false;
        this.Decimo = false;
        this.Once = false;
        this.area = new Area();
        this.AG = new Areagrado();

    }
    
    public List<Area> consultarArea(){
        return areaFacade.consultarArea(1);
    }

    //----- Getters and Setters ----------------------------------------------\\

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Areagrado getAG() {
        return AG;
    }

    public void setAG(Areagrado AG) {
        this.AG = AG;
    }

    public boolean isPrimero() {
        return Primero;
    }

    public void setPrimero(boolean Primero) {
        this.Primero = Primero;
    }

    public boolean isSegundo() {
        return Segundo;
    }

    public void setSegundo(boolean Segundo) {
        this.Segundo = Segundo;
    }

    public boolean isTercero() {
        return Tercero;
    }

    public void setTercero(boolean Tercero) {
        this.Tercero = Tercero;
    }

    public boolean isCuarto() {
        return Cuarto;
    }

    public void setCuarto(boolean Cuarto) {
        this.Cuarto = Cuarto;
    }

    public boolean isQuinto() {
        return Quinto;
    }

    public void setQuinto(boolean Quinto) {
        this.Quinto = Quinto;
    }

    public boolean isSexto() {
        return Sexto;
    }

    public void setSexto(boolean Sexto) {
        this.Sexto = Sexto;
    }

    public boolean isSeptimo() {
        return Septimo;
    }

    public void setSeptimo(boolean Septimo) {
        this.Septimo = Septimo;
    }

    public boolean isOctavo() {
        return Octavo;
    }

    public void setOctavo(boolean Octavo) {
        this.Octavo = Octavo;
    }

    public boolean isNoveno() {
        return Noveno;
    }

    public void setNoveno(boolean Noveno) {
        this.Noveno = Noveno;
    }

    public boolean isDecimo() {
        return Decimo;
    }

    public void setDecimo(boolean Decimo) {
        this.Decimo = Decimo;
    }

    public boolean isOnce() {
        return Once;
    }

    public void setOnce(boolean Once) {
        this.Once = Once;
    }
    
    
    
}
