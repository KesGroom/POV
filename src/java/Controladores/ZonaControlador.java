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
    private String nombreZona;
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
        ZonaServicioSocial zonaPrueba = zonaFacade.obtenerZonaNombre(nombreZona);
        if (zonaPrueba == null) {
            zona.setEstado(1);
            String diasem = "";
            for (int i = 0; i < diaSemana.length; i++) {
                if (i == 0) {
                    diasem = diaSemana[i] + ", ";
                } else if (i > 0 && i < diaSemana.length - 1) {
                    diasem = diasem + diaSemana[i] + ", ";
                } else {
                    diasem = diasem + diaSemana[i];
                }
            }
            zona.setDiaServicio(diasem);
            zona.setNombre(nombreZona);
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
            return "ZonasSS.xhtml";
        } else {
            alerta.setMensaje("AlertaPopUp('Error al realizar el registro','Ya existe una zona servicio con el nombre','error');");
            this.nombreZona = "";
            return "RegistroZona.xhtml";
        }
    }

    public String preActualizar(ZonaServicioSocial zonaServicioSocialActualizar) {
        zona = zonaServicioSocialActualizar;
        return "ActualizarZona";
    }

    public String actualizar() {

        String diasem = "";
        for (int i = 0; i < diaSemana.length; i++) {
            if (i == 0) {
                diasem = diaSemana[i] + ", ";
            } else if (i > 0 && i < diaSemana.length - 1) {
                diasem = diasem + diaSemana[i] + ", ";
            } else {
                diasem = diasem + diaSemana[i];
            }
        }
        zona.setDiaServicio(diasem);
        zonaFacade.edit(zona);
        return "ZonasSS";
    }

    public void Remover(ZonaServicioSocial zonaRemover) {
        zona = zonaRemover;
        zona.setEstado(2);
        zonaFacade.edit(zona);
    }
    public void rechazado(Salaserviciosocial salaRechazo) {
        sala = salaRechazo;
        zona.setEstado(2);
        zonaFacade.edit(zona);
    }

    public List<ZonaServicioSocial> consultarZona() {
        return zonaFacade.consultarZonaServicioSocial(1);
    }

    public List<Elementoslista> retornarLista(int id) {
        return elFacade.obtenerElementosZona(id);
    }

    public List<Salaserviciosocial> consultarSala() {
        return salaFacade.findAll();
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

    public void postular(int zonaPostular, int estu) {
        Salaserviciosocial salaPrueba = salaFacade.obtenerSala(zonaPostular, estu);
        if (salaPrueba == null) {
            sala.setEstado(1);
            sala.setEstadoServicio("En espera");
            estudiante = estFacade.EstudianteDocIn(estu);
            sala.setEstudiante(estudiante);
            zona = zonaFacade.obtenerZona(zonaPostular);
            sala.setZonaServicio(zona);
            salaFacade.create(sala);
            alerta.setMensaje("AlertaToast('Postulación exitosa','success');");
            this.sala = new Salaserviciosocial();
            this.zona = null;
        } else {
            alerta.setMensaje("AlertaPopUp('Ha ocurrido un error','Ya se ha postulado a esta zona, por favor espere una respuesta','error');");
        }
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

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public String[] getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String[] diaSemana) {
        this.diaSemana = diaSemana;
    }

}
