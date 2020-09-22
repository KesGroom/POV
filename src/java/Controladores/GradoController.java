/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Curso;
import Entidades.Grado;
import Entidades.Area;
import Entidades.Materia;
import Facade.AreaFacade;
import Facade.CursoFacade;
import Facade.GradoFacade;
import Facade.MateriaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author kesgr
 */
@Named(value = "gradoController")
@SessionScoped
public class GradoController implements Serializable {

    /**
     * Creates a new instance of GradoController
     */
    public GradoController() {
        grado = new Grado();
        curso = new Curso();
        materia = new Materia();
        area = new Area();
    }

    private Grado grado;

    private Curso curso;

    private Area area;

    private Materia materia;

    private String nombreGrado;

    private String nombreCurso;

    private String salon;

    private String nivel;
    
    private List<Grado> gradoList;
    
            

    @EJB
    GradoFacade gradoFacade;

    @EJB
    CursoFacade cursoFacade;

    @EJB
    AreaFacade areaFacade;

    @EJB
    MateriaFacade materiaFacade;

    @Inject
    AlertasControlador alerta;

    public void registrarGrado() {
        Grado prueba = gradoFacade.consultGrado(1, nombreGrado);
        if (prueba == null) {
            grado.setEstado(1);
            grado.setGrado(nombreGrado);
            gradoFacade.create(grado);
            alerta.setMensaje("AlertaToast('Grado registrado','success');");
            this.nombreGrado = "";
            this.grado = new Grado();
        } else {
            alerta.setMensaje("AlertaPopUp('Error en el registro','Este grado ya existe en el sistema','error');");
        }
    }

    public void registrarCurso() {
        Curso cursoPrueba = cursoFacade.obtenerCurso(nombreCurso);
        if (cursoPrueba == null) {
            Curso salonPrueba = cursoFacade.validarSalon(salon);
            if (salonPrueba == null) {
                curso.setCurso(nombreCurso);
                curso.setSalon(salon);
                curso.setEstado(1);
                curso.setGrado(grado);
                cursoFacade.create(curso);
                alerta.setMensaje("AlertaToast('Curso registrado','success');");
                this.curso = new Curso();
                this.grado = null;
                this.nombreCurso = "";
                this.salon = "";
            } else {
                alerta.setMensaje("AlertaPopUp('Error en el registro','Un curso ya ha sido asignado a este salon','error');");
            }
        } else {
            alerta.setMensaje("AlertaPopUp('Error en el registro','Este curso ya existe en el sistema','error');");
        }
    }

    public void cambioGrade(AjaxBehaviorEvent event) {
        if (!"0".equals(nivel)) {
            gradoList = new ArrayList<>();
            for (Grado g : gradoFacade.consultarGrado(1)) {
                if (g.getEducacion() == null ? nivel == null : g.getEducacion().equals(nivel)) {
                    gradoList.add(g);
                }
            }
        }
    }

    public List<Grado> consultarTodos() {
        return gradoFacade.consultarGrado(1);
    }

    public String consultarGradoName(String name) {
        gradoFacade.consultGrado(1, name);

        return "";
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getNombreGrado() {
        return nombreGrado;
    }

    public void setNombreGrado(String nombreGrado) {
        this.nombreGrado = nombreGrado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public List<Grado> getGradoList() {
        return gradoList;
    }

    public void setGradoList(List<Grado> gradoList) {
        this.gradoList = gradoList;
    }

}
