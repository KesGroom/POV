/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Actividad;

import Entidades.DocenteMateria;
import Facade.ActividadFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author PC
 */
@Named(value = "actividadControlador")
@SessionScoped
public class ActividadControlador implements Serializable {

//----- Builders ---------------------------------------------------------\\

    public ActividadControlador() {
        actividad = new Actividad();

        docenteMateria = new DocenteMateria();
    }


//----- Attributes -------------------------------------------------------\\

    Actividad actividad;

    DocenteMateria docenteMateria;
    
    @EJB
    ActividadFacade actividadFacade;


//----- Methods ----------------------------------------------------------\\

    public List<Actividad> consultarActividad(){
        return actividadFacade.consultarActividad(1);
    }
    
    public String registrarActividad(){
        actividad.setIdDocenteMateria(docenteMateria);
        actividadFacade.create(actividad);
        return "Actividades";
    }
    
    public String Remover(Actividad actividadRemover) {
        actividad = actividadRemover;
        actividad.setEstado(2);
        actividadFacade.edit(actividad);
        return "Actividades";
    }
    
    public List<Actividad> actividadxMateria(int id){
        return actividadFacade.consultarActividadXMat(1, id);
    }


//----- Getters and Setters ----------------------------------------------\\
    

    
    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public DocenteMateria getDocenteMateria() {
        return docenteMateria;
    }

    public void setDocenteMateria(DocenteMateria docenteMateria) {
        this.docenteMateria = docenteMateria;
    }
    
}
