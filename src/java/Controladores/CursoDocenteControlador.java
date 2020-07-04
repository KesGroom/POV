/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Curso;
import Entidades.CursoDocente;
import Entidades.Docente;
import Facade.CursoDocenteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author PC
 */
@Named(value = "cursoDocenteControlador")
@SessionScoped
public class CursoDocenteControlador implements Serializable {

    /**
     * Creates a new instance of CursoDocenteControlador
     */
    public CursoDocenteControlador() {
        cursoDocente = new CursoDocente();
        docente  =  new Docente();
        curso = new Curso();
    }
    
    CursoDocente cursoDocente;
    Docente docente;
    Curso curso;
    
    @EJB
    CursoDocenteFacade cursoDocenteFacade;

    public CursoDocente getCursoDocente() {
        return cursoDocente;
    }

    public void setCursoDocente(CursoDocente cursoDocente) {
        this.cursoDocente = cursoDocente;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public List<CursoDocente> consultarCursoDocente(){
        return cursoDocenteFacade.consultarCursoDocente(1);
    }
    
    public List<CursoDocente> consultarCursoxDoc(int Doc){
        return cursoDocenteFacade.consultarCursoxDoc(1, Doc);
    }
    
    public void registrarCursoDocente(){
        cursoDocente.setIdCurso(curso);
        cursoDocente.setIdDocente(docente);
        cursoDocenteFacade.create(cursoDocente);
    }
    
}
