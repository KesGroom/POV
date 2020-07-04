/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.AgendaWeb;
import Entidades.DocenteMateria;
import Entidades.Estudiante;
import Entidades.PlanMejoramiento;
import Entidades.Actividad;
import Facade.AgendaWebFacade;
import Facade.EstudianteFacade;
import Facade.ActividadFacade;
import Facade.PlanMejoramientoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author PC
 */
@Named(value = "agendaWebControlador")
@SessionScoped
public class AgendaWebControlador implements Serializable {

    /**
     * Creates a new instance of AgendaWebControlador
     */
    public AgendaWebControlador() {
        agendaWeb = new AgendaWeb();
        docenteMateria = new DocenteMateria();
        estudiante = new Estudiante();
        actividad = new Actividad();
        plan = new PlanMejoramiento();
    }

    private AgendaWeb agendaWeb;
    private DocenteMateria docenteMateria;
    private Estudiante estudiante;
    private Actividad actividad;
    private PlanMejoramiento plan;
    //Variables para actualizar la lista de estudiantes
    private String SeleccionCurso;
    private List<Estudiante> estuCur;
    
    //Variables para actualizar la lista de Actividades
    private List<Actividad> actMat;
    
    //Variables para actualizar la lista de Planes
    private List<PlanMejoramiento> actPlan;

    @EJB
    AgendaWebFacade agendaWebFacade;

    @EJB
    EstudianteFacade estudianteFacade;
    
    @EJB
    ActividadFacade actividadFacade;
    
    @EJB
    PlanMejoramientoFacade planFacade;

    
    

    public List<AgendaWeb> consultarAgendaWeb() {
        return agendaWebFacade.consultarAgenda(1);
    }

    public void registrarAgendaWeb() {
        agendaWeb.setEstado(1);
        agendaWeb.setEstudiante(estudiante);
        agendaWeb.setDocenteMateria(docenteMateria);
        agendaWebFacade.create(agendaWeb);
    }
    public void registrarAgendaWebAct() {
        agendaWeb.setEstudiante(estudiante);
        agendaWeb.setEstado(1);
        agendaWeb.setActividad(actividad);
        agendaWeb.setDocenteMateria(docenteMateria);
        agendaWebFacade.create(agendaWeb);
    }
    public void registrarAgendaWebPlan() {
        agendaWeb.setEstado(1);
        agendaWeb.setEstudiante(estudiante);
        agendaWeb.setPlanMejoramiento(plan);
        agendaWeb.setDocenteMateria(docenteMateria);
        agendaWebFacade.create(agendaWeb);
    }

    public void Cambio() {
        if (this.SeleccionCurso != null && !this.SeleccionCurso.equals("0")) {
            this.estuCur = new ArrayList<>();
            for(Estudiante e : estudianteFacade.consultarEstudiante(1)){
              if (e.getIdCurso().getCurso().equals(this.SeleccionCurso)){
                  this.estuCur.add(e);
              }  
            }
        }
    }
    
    public void CambioAct() {
        if (this.docenteMateria.getIdDocMat() != null && !this.docenteMateria.getIdDocMat().equals(0)) {
            this.actMat = new ArrayList<>();
            for(Actividad a : actividadFacade.consultarActividad(1)){
              if (a.getIdDocenteMateria().getIdDocMat().equals(this.docenteMateria.getIdDocMat())){
                  this.actMat.add(a);
              }  
            }
        }
    }
    
    public void CambioPlan() {
        if (this.docenteMateria.getIdDocMat() != null && !this.docenteMateria.getIdDocMat().equals(0)) {
            this.actPlan = new ArrayList<>();
            for(PlanMejoramiento p : planFacade.consultarPlanMejoramiento(1)){
              if (p.getDocenteAsigna().getIdDocMat().equals(this.docenteMateria.getIdDocMat())){
                  this.actPlan.add(p);
              }  
            }
        }
    }
    
    public List<AgendaWeb> ConsultarCat(int categoria){
        return agendaWebFacade.consultarAgendaCat(categoria);
    }

    //Getters and setters
    public AgendaWeb getAgendaWeb() {
        return agendaWeb;
    }

    public void setAgendaWeb(AgendaWeb agendaWeb) {
        this.agendaWeb = agendaWeb;
    }

    public DocenteMateria getDocenteMateria() {
        return docenteMateria;
    }

    public void setDocenteMateria(DocenteMateria docenteMateria) {
        this.docenteMateria = docenteMateria;
    }

    public List<Estudiante> getEstuCur() {
        return estuCur;
    }

    public void setEstuCur(List<Estudiante> estuCur) {
        this.estuCur = estuCur;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getSeleccionCurso() {
        return SeleccionCurso;
    }

    public void setSeleccionCurso(String SeleccionCurso) {
        this.SeleccionCurso = SeleccionCurso;
    }
    
    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public PlanMejoramiento getPlan() {
        return plan;
    }

    public void setPlan(PlanMejoramiento plan) {
        this.plan = plan;
    }

    public List<Actividad> getActMat() {
        return actMat;
    }

    public void setActMat(List<Actividad> actMat) {
        this.actMat = actMat;
    }

    public List<PlanMejoramiento> getActPlan() {
        return actPlan;
    }

    public void setActPlan(List<PlanMejoramiento> actPlan) {
        this.actPlan = actPlan;
    }
    

}
