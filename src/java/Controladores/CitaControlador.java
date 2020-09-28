/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Acudiente;
import Entidades.Area;
import Entidades.Areagrado;
import Entidades.Cita;
import Entidades.Curso;
import Entidades.Docente;
import Entidades.Estudiante;
import Entidades.Materia;
import Facade.AreaFacade;
import Facade.AreaGradoFacade;
import Facade.AtencionareaFacade;
import Facade.AtencioncursoFacade;
import Facade.CitaFacade;
import Facade.EstudianteFacade;
import Facade.MateriaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author PC
 */
@Named(value = "citaControlador")
@SessionScoped
public class CitaControlador implements Serializable {

    //----- Builders ---------------------------------------------------------\\
    public CitaControlador() {
        cita = new Cita();
        acudiente = new Acudiente();
        area = new Area();
        curso = new Curso();
        docente = new Docente();
    }

    //----- Attributes -------------------------------------------------------\\
    private Cita cita;
    private Acudiente acudiente;
    private Estudiante estudiante;
    private Date fecha;
    private Area area;
    private Curso curso;
    private Docente docente;
    private Date fechaCita;
    private int atencion;
    private int idEst;

    //--List to register------------------------------------------\\
    private List<Materia> materias;
    private List<Area> areaList;
    private List<Docente> docenteList;
    private List<Integer> diaList;
    

    @EJB
    CitaFacade citaFacade;

    @EJB
    EstudianteFacade estudianteFacade;
    
    @EJB
    AreaFacade areaFacade;
    
    @EJB
    AreaGradoFacade agFacade;
    
    @EJB 
    AtencionareaFacade aaFacade;
    
    @EJB
    AtencioncursoFacade acFacade;

    @EJB
    MateriaFacade materiaFacade;

    //----- Methods ----------------------------------------------------------\\
    public List<Cita> consultarCita() {
        return citaFacade.consultarCita(1);
    }

    public List<Estudiante> AcudienteEst(int id) {
        return estudianteFacade.EstudianteAcudiente(id);
    }

    public void registrarCita() {
        cita.setAcudiente(acudiente);
        cita.setEstado(1);
        cita.setFechaRegistro(fecha);
        citaFacade.create(cita);
    }
    
    //----- Ajax ------------------------------------------------------\\
    public void obtenerAreas(AjaxBehaviorEvent event){
        System.out.println(idEst);
        /*    curso = estudiante.getIdCurso();
        areaList = new ArrayList<>();
        for(Areagrado a : agFacade.consultarAreaGrado(1)){
            if(curso.getGrado() == a.getGrado()){
                areaList.add(a.getArea());
                for (int i = 1; i < areaList.size(); i++) {
                    if(areaList.get(i)== areaList.get(i-1)){
                        areaList.remove(i);
                    }
                }
            }
        }*/
    }
    
    public void obtenerDocentes(AjaxBehaviorEvent event){
        
    }
    public void obtenerDias(AjaxBehaviorEvent event){
        
    }
    public void obtenerHorarios(AjaxBehaviorEvent event){
        
    }

    //----- Getters and Setters ----------------------------------------------\\
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    public List<Integer> getDiaList() {
        return diaList;
    }

    public void setDiaList(List<Integer> diaList) {
        this.diaList = diaList;
    }

    public int getAtencion() {
        return atencion;
    }

    public void setAtencion(int atencion) {
        this.atencion = atencion;
    }

    public int getIdEst() {
        return idEst;
    }

    public void setIdEst(int idEst) {
        this.idEst = idEst;
    }

    
    
    
}
