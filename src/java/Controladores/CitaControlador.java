/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Acudiente;
import Entidades.Cita;
import Entidades.Estudiante;
import Entidades.Materia;
import Facade.CitaFacade;
import Facade.EstudianteFacade;
import Facade.MateriaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

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
    }
    
    //----- Attributes -------------------------------------------------------\\
    
    private Cita cita;
    private Acudiente acudiente;
    private Estudiante estudiante;
    private Date fecha;
    
        //--List to register------------------------------------------\\
        private List<Materia> materias; 
    
    @EJB
    CitaFacade citaFacade;
    
    @EJB 
    EstudianteFacade estudianteFacade;
    
    @EJB 
    MateriaFacade materiaFacade;
    
    //----- Methods ----------------------------------------------------------\\
    
    public List<Cita> consultarCita(){
        return citaFacade.consultarCita(1);
    }
    
    public List<Estudiante> AcudienteEst(int id){
        return estudianteFacade.EstudianteAcudiente(id);
    }
    
    public void registrarCita(){
        cita.setAcudiente(acudiente);
        cita.setEstado(1);
        cita.setFechaRegistro(fecha);
        citaFacade.create(cita);
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
    
    
    
}
