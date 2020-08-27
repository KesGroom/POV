/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Grado;
import Entidades.Materia;
import Entidades.Rol;
import Facade.GradoFacade;
import Facade.MateriaFacade;
import Facade.RolFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author kesgr
 */
@Named(value = "correoControlador")
@SessionScoped
public class CorreoControlador implements Serializable {

    /**
     * Creates a new instance of CorreoControlador
     */
    public CorreoControlador() {
    }

    private String[] color;
    private int[] coloresPreferidos;
    private int[] coloresPreferidos2;
    private List<Rol> listaColores;
    private String nivel2 = "0";
    private String nivel = "0";
    private String grado = "0";
    private List<Grado> gradoList;
    private List<Materia> materiaList;
    private List<Materia> materiaList2;

    private int area = 0;
    private int area2 = 0;

    
    //----- Gráficas -------------------------------------------------------\\
    //----- Colores ------------------------------------\\
    public List<String> colores(int cont) {
        List<String> colores = new ArrayList<>();
        for (int i = 0; i < cont; i++) {
            String colorFinal = "'rgb(";
            for (int j = 0; j < 3; j++) {
                int num = (int) Math.floor(Math.random() * 256);
                String digito = String.valueOf(num);
                if (j != 2) {
                    colorFinal = colorFinal + digito + ",";
                } else {
                    colorFinal = colorFinal + digito;
                }
            }
            String cierre = ")'";
            colorFinal = colorFinal + cierre;
            colores.add(colorFinal);
        }
        
        return colores;
    }
    

    @EJB
    MateriaFacade materiaFacade;

    @EJB
    RolFacade rolFacade;

    @EJB
    GradoFacade gradoFacade;

    @EJB
    MateriaFacade matFacade;

    public void cambioMaterias(AjaxBehaviorEvent event) {
        if (!"0".equals(nivel2) && area != 0) {
            materiaList = new ArrayList<>();
            for (Materia m : matFacade.consultarMateria(1)) {
                if (m.getIdArea().getArea().getIdArea() == area && m.getIdArea().getGrado().getEducacion().equals(nivel2)) {
                    materiaList.add(m);
                }
            }

        }
    }

    public void cambioMaterias2(AjaxBehaviorEvent event) {
        if (!"0".equals(nivel) && area2 != 0) {
            materiaList2 = new ArrayList<>();
            for (Materia m : matFacade.consultarMateria(1)) {
                if (m.getIdArea().getArea().getIdArea() == area && m.getIdArea().getGrado().getEducacion().equals(nivel2)) {
                    materiaList2.add(m);
                }
            }

        }
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public String getColorString() {
        int a[] = new int[coloresPreferidos.length + coloresPreferidos2.length + coloresPreferidos.length];
        System.arraycopy(coloresPreferidos, 0, a, 0, coloresPreferidos.length);
        System.arraycopy(coloresPreferidos2, 0, a, coloresPreferidos.length, coloresPreferidos2.length);
        System.arraycopy(coloresPreferidos, 0, a, coloresPreferidos.length + coloresPreferidos2.length, coloresPreferidos.length);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        return "Materias elegidas: " + Arrays.toString(coloresPreferidos) + "\n" + "Roles elegidos: " + Arrays.toString(coloresPreferidos2);
    }

    public int[] getColoresPreferidos() {
        return coloresPreferidos;
    }

    public void setColoresPreferidos(int[] coloresPreferidos) {
        this.coloresPreferidos = coloresPreferidos;
    }

    public List<Rol> getListaColores() {
        return listaColores;
    }

    public void setListaColores(List<Rol> listaColores) {
        this.listaColores = listaColores;
    }

    public String getNivel2() {
        return nivel2;
    }

    public void setNivel2(String nivel2) {
        this.nivel2 = nivel2;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public List<Grado> getGradoList() {
        return gradoList;
    }

    public void setGradoList(List<Grado> gradoList) {
        this.gradoList = gradoList;
    }

    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getArea2() {
        return area2;
    }

    public void setArea2(int area2) {
        this.area2 = area2;
    }

    public List<Materia> getMateriaList2() {
        return materiaList2;
    }

    public void setMateriaList2(List<Materia> materiaList2) {
        this.materiaList2 = materiaList2;
    }

    public int[] getColoresPreferidos2() {
        return coloresPreferidos2;
    }

    public void setColoresPreferidos2(int[] coloresPreferidos2) {
        this.coloresPreferidos2 = coloresPreferidos2;
    }

}
