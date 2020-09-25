/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Estudiante;
import Entidades.Usuario;
import Facade.EstudianteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author PC
 */
@Named(value = "estudianteControladores")
@SessionScoped
public class EstudianteControladores implements Serializable {

//----- Builders ---------------------------------------------------------\\

    public EstudianteControladores() {
        estudiante = new Estudiante();
        usuario = new Usuario();
    }


//----- Attributes -------------------------------------------------------\\

    private Estudiante estudiante;
    private Usuario usuario;

    @EJB
    EstudianteFacade estudianteFacade;

//----- Methods ----------------------------------------------------------\\
    
    public int countStudent(int id){
        return estudianteFacade.countStudent(id);
    }

    public List<Estudiante> consultarEstudiante() {
        return estudianteFacade.consultarEstudiante(1);
    }
    
    public List<Estudiante> estudianteBitacora() {
        return estudianteFacade.EstudianteBitacora();
    }
    
    public List<Estudiante> estudianteCurso(String Cur) {
        return estudianteFacade.EstudianteCurso(Cur);
    }
    public Estudiante buscarEstudiante(Usuario id) {
        return estudianteFacade.EstudianteDoc(id);
    }


//----- Getters and Setters ----------------------------------------------\\

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
