/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Area;
import Entidades.Atencionarea;
import Entidades.Atencioncurso;
import Entidades.Curso;
import Entidades.Docente;
import Facade.AtencionareaFacade;
import Facade.AtencioncursoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author PC
 */
@Named(value = "atencionAreaControlador")
@SessionScoped
public class AtencionAreaControlador implements Serializable {

    /**
     * Creates a new instance of AntecionAreaControlador
     */
    public AtencionAreaControlador() {
        atencionarea = new Atencionarea();
        atencioncurso = new Atencioncurso();
        area = new Area();
        curso = new Curso();
        docente = new Docente();
    }
    
    private Atencionarea atencionarea;
    private Atencioncurso atencioncurso;
    private Area area;
    private Curso curso;
    private Docente docente;
    
    @EJB
    AtencionareaFacade atencionAreaFacade;
    
    @EJB
    AtencioncursoFacade atencionCursoFacade;

    public void registrarAtencionArea(){
        atencionarea.setEstado(1);
        atencionarea.setDocenteACargo(docente);
        atencionarea.setArea(area);
        atencionAreaFacade.create(atencionarea);
    }
    
    public void registrarAtencionCurso(){
        atencioncurso.setEstado(1);
        atencioncurso.setDocenteACargo(docente);
        atencioncurso.setCurso(curso);
        atencionCursoFacade.create(atencioncurso);
    }
    
    public List<Atencionarea> consultarAtencionarea(){
        return atencionAreaFacade.consultarAtencionarea(1);
    }
    
    public Atencionarea getAtencionarea() {
        return atencionarea;
    }

    public void setAtencionarea(Atencionarea atencionarea) {
        this.atencionarea = atencionarea;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Atencioncurso getAtencioncurso() {
        return atencioncurso;
    }

    public void setAtencioncurso(Atencioncurso atencioncurso) {
        this.atencioncurso = atencioncurso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    

}
