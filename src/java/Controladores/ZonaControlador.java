/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Elementoslista;
import Entidades.Estudiante;
import Entidades.Salaserviciosocial;
import Entidades.ZonaServicioSocial;
import Facade.ElementoslistaFacade;
import Facade.EstudianteFacade;
import Facade.SalaserviciosocialFacade;
import Facade.ZonaServicioSocialFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author jusag
 */
@Named(value = "zonaControlador")
@SessionScoped
public class ZonaControlador implements Serializable {

    /**
     * Creates a new instance of zonaControlador
     */
    public ZonaControlador() {
        zona = new ZonaServicioSocial();
        elLista = new Elementoslista();
        sala = new Salaserviciosocial();
        estudiante = new Estudiante();
    }

    private ZonaServicioSocial zona;
    private Salaserviciosocial sala;
    private Elementoslista elLista;
    private String nmb;
    private String lug;
    private String gado;
    private String tservi;
    private String labores;
    private Estudiante estudiante;
    private String contItem;
    private String[] diaSemana;

    @EJB
    ZonaServicioSocialFacade zonaFacade;

    @EJB
    SalaserviciosocialFacade salaFacade;

    @EJB
    ElementoslistaFacade elFacade;

    @EJB
    EstudianteFacade estFacade;

    @Inject
    AlertasControlador alerta;

    public String registrar() {
        zona.setEstado(1);
        String diaSem = "";
        for (int i = 0; i < diaSemana.length; i++) {
            if (i == diaSemana.length - 1) {
                diaSem = diaSem + diaSemana[i];
            } else {
                diaSem = diaSem + diaSemana[i] + ", ";
            }
        }
        zona.setDiaServicio(diaSem);
        zonaFacade.create(zona);
        alerta.setMensaje("AlertaToast('Zona creada exitosamente','success');");
        String texto = labores;
        List<String> Elementos = new ArrayList<>();
        String item;
        for (int i = 0; i < zona.getCantidadLabores(); i++) {
            item = devuelveFrase2("<", ">", texto);
            Elementos.add(item);
            String searchText = devuelveFrase1("<", ">", texto);
            String newText = reemplazar(texto, searchText, "");
            texto = newText;
        }
        for (int i = 0; i < Elementos.size(); i++) {
            elLista.setZonaServicio(zona);
            elLista.setItemList(Elementos.get(i));
            elFacade.create(elLista);
        }
        this.zona = new ZonaServicioSocial();
        this.labores = "";
        this.elLista = new Elementoslista();
        return "ZonasSS";
    }

    public String preActualizar(ZonaServicioSocial zonaServicioSocialActualizar) {
        zona = zonaServicioSocialActualizar;
        return "ActualizarZona";
    }

    public String actualizar() {
        String diaSem = "";
        for (int i = 0; i < diaSemana.length; i++) {
            if (i == diaSemana.length - 1) {
                diaSem = diaSem + diaSemana[i];
            } else {
                diaSem = diaSem + diaSemana[i] + ", ";
            }
        }
        zona.setDiaServicio(diaSem);
        zonaFacade.edit(zona);
        alerta.setMensaje("AlertaToast('Cambio de exitoso','success');");
        return "ZonasSS";
    }

    public void Remover(ZonaServicioSocial zonaRemover) {
        zona = zonaRemover;
        zona.setEstado(2);
        zonaFacade.edit(zona);
    }

    public List<ZonaServicioSocial> consultarZona() {
        return zonaFacade.consultarZonaServicioSocial(1);
    }

    public List<Elementoslista> retornarLista(int id) {
        return elFacade.obtenerElementosZona(id);
    }

    //Metodos para la separación de los elementos de la lista
    public static String reemplazar(String cadena, String busqueda, String reemplazo) {
        return cadena.replaceAll(busqueda, reemplazo);
    }

    public static String devuelveFrase1(String palabraInicio, String palabraFinal, String texto) {
        String fraseCompleta;
        int indexPrimera = texto.indexOf(palabraInicio);
        int indexUltima = texto.indexOf(palabraFinal) + palabraFinal.length();

        fraseCompleta = texto.substring(indexPrimera, indexUltima);

        return fraseCompleta;
    }

    public static String devuelveFrase2(String palabraInicio, String palabraFinal, String texto) {
        String fraseCompleta;
        int indexPrimera = texto.indexOf(palabraInicio) + palabraInicio.length();
        int indexUltima = texto.indexOf(palabraFinal);

        fraseCompleta = texto.substring(indexPrimera, indexUltima);

        return fraseCompleta;
    }

    public String preEditarLabor(Elementoslista editarLabor, ZonaServicioSocial zon) {
        zona = zon;
        elLista = editarLabor;
        return "ActualizarLabor";
    }

    public String editarLabor() {
        elLista.setZonaServicio(zona);
        elFacade.edit(elLista);
        alerta.setMensaje("AlertaToast('Cambio de labor exitosa','success');");
        return "ZonasSS";
    }

    public ZonaServicioSocial getZona() {
        return zona;
    }

    public void setZona(ZonaServicioSocial zona) {
        this.zona = zona;
    }

    public String getNmb() {
        return nmb;
    }

    public void setNmb(String nmb) {
        this.nmb = nmb;
    }

    public String getLug() {
        return lug;
    }

    public void setLug(String lug) {
        this.lug = lug;
    }

    public String getGado() {
        return gado;
    }

    public void setGado(String gado) {
        this.gado = gado;
    }

    public String getTservi() {
        return tservi;
    }

    public void setTservi(String tservi) {
        this.tservi = tservi;
    }

    public String getLabores() {
        return labores;
    }

    public void setLabores(String labores) {
        this.labores = labores;
    }

    public Salaserviciosocial getSala() {
        return sala;
    }

    public void setSala(Salaserviciosocial sala) {
        this.sala = sala;
    }

    public Elementoslista getElLista() {
        return elLista;
    }

    public void setElLista(Elementoslista elLista) {
        this.elLista = elLista;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getContItem() {
        return contItem;
    }

    public void setContItem(String contItem) {
        this.contItem = contItem;
    }

    public String[] getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String[] diaSemana) {
        this.diaSemana = diaSemana;
    }

}
