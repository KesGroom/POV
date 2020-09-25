/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Rol;
import Facade.RolFacade;
import Facade.UsuarioFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author kesgr
 */
@Named(value = "reportesControlador")
@Dependent
public class ReportesControlador {

    //----- Builders ---------------------------------------------------------\\
    public ReportesControlador() {

    }

    //----- Attributes -------------------------------------------------------\\
    private List<String> labels;
    private List<Integer> data;
    private List<String> colores;
    private String entidad = "Ejemplo";
    private String tipoGrafica = "Dona";
    private String grafica;
    private String infoJS;

    //Variables para grafica de ejemplo
    int cantElementos;

    @EJB
    RolFacade rolFacade;

    @EJB
    UsuarioFacade usuarioFacade;

    @Inject
    CorreoControlador utilidades;

    @Inject
    AlertasControlador alertas;

    //----- Methods ----------------------------------------------------------\\
    public void obtenerGrafica() {
        cantElementos = 5;
        System.out.println(cantElementos);

        if ("Ejemplo".equals(entidad)) {
            this.data = new ArrayList<>();
            for (int i = 0; i < cantElementos; i++) {
                int itemData = (int) Math.floor(Math.random() * 21);
                this.data.add(itemData);
            }
            infoJS = "var data = " + this.data + ";";
            this.labels = new ArrayList<>();
            for (int i = 0; i < cantElementos; i++) {
                int iter = i + 1;
                String itemLabels = "Elemento " + iter;
                this.labels.add('"' + itemLabels + '"');
            }
            colores = utilidades.colores(cantElementos);
            infoJS = infoJS + "var labels =" + this.labels + ";var colores = " + this.colores + ";";
            if ("Dona".equals(tipoGrafica)) {
                grafica = "graphicDougnout('grafica', labels, data, colores);";
                infoJS = infoJS + grafica;
                System.out.println(infoJS);
                alertas.setGrafica(infoJS);
            }
        } else if ("Rol".equals(entidad)) {
            this.data = new ArrayList<>();
            List<Rol> roleList = rolFacade.consultarRol(1);
            for (int i = 0; i < roleList.size(); i++) {
                int dataUsu = usuarioFacade.contarUsuario(roleList.get(i).getIdRoles());
                this.data.add(dataUsu);
            }

            this.labels = new ArrayList<>();
            for (int i = 0; i < roleList.size(); i++) {
                String nameRol = roleList.get(i).getRol();
                this.labels.add('"' + nameRol + '"');
            }
            int cont = rolFacade.contarRol(1);
            colores = utilidades.colores(cont);
        }

    }

    public List<Integer> data() {
        if ("Ejemplo".equals(entidad)) {
            data = new ArrayList<>();
            if (cantElementos == 1 || cantElementos == 0) {
                this.cantElementos = cantElementos + 3;
            }
            for (int i = 0; i < cantElementos; i++) {
                int itemData = (int) Math.floor(Math.random() * 21);
                data.add(itemData);
            }

        } else if ("Rol".equals(entidad)) {
            data = new ArrayList<>();
            List<Rol> roleList = rolFacade.consultarRol(1);
            for (int i = 0; i < roleList.size(); i++) {
                int dataUsu = usuarioFacade.contarUsuario(roleList.get(i).getIdRoles());
                data.add(dataUsu);
            }
        }
        return data;
    }

    public List<String> labels() {
        if ("Ejemplo".equals(entidad)) {
            labels = new ArrayList<>();
            if (cantElementos == 1 || cantElementos == 0) {
                this.cantElementos = cantElementos + 3;
            }
            for (int i = 0; i < cantElementos; i++) {
                int iter = i + 1;
                String itemLabels = "Elemento " + iter;
                labels.add('"' + itemLabels + '"');
            }
        } else if ("Rol".equals(entidad)) {
            labels = new ArrayList<>();
            List<Rol> roleList = rolFacade.consultarRol(1);
            for (int i = 0; i < roleList.size(); i++) {
                String nameRol = roleList.get(i).getRol();
                labels.add('"' + nameRol + '"');
            }
        }
        return labels;
    }

    public List<String> colores() {
        if ("Ejemplo".equals(entidad)) {
            data = new ArrayList<>();
            if (cantElementos == 1 || cantElementos == 0) {
                this.cantElementos = cantElementos + 3;
            }
            colores = utilidades.colores(cantElementos);
        } else if ("Rol".equals(entidad)) {
            int cont = rolFacade.contarRol(1);
            colores = utilidades.colores(cont);
        }
        return colores;
    }

    public void generarGrafica() {

    }
    //----- Getters and Setters ----------------------------------------------\\

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public List<String> getColores() {
        return colores;
    }

    public void setColores(List<String> colores) {
        this.colores = colores;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getTipoGrafica() {
        return tipoGrafica;
    }

    public void setTipoGrafica(String tipoGrafica) {
        this.tipoGrafica = tipoGrafica;
    }

    public String getGrafica() {
        return grafica;
    }

    public void setGrafica(String grafica) {
        this.grafica = grafica;
    }

    public String getInfoJS() {
        return infoJS;
    }

    public void setInfoJS(String infoJS) {
        this.infoJS = infoJS;
    }

}
