/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Categorianoticia;
import Entidades.Noticia;
import Entidades.Usuario;
import Facade.NoticiaFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author kesgr
 */
@Named(value = "noticiaControlador")
@SessionScoped
public class NoticiaControlador implements Serializable {

    public NoticiaControlador() {
        noticia = new Noticia();
        coordinador = new Usuario();
        categoria = new Categorianoticia();
        utilidades = new CorreoControlador();
    }

    private Noticia noticia;
    private Usuario coordinador;
    private CorreoControlador utilidades;
    private Categorianoticia categoria;
    private Date fecha = new Date();

    @EJB
    NoticiaFacade noticiaFacade;

    @Inject
    AlertasControlador alerta;

    public String registrar() {
        noticia.setCategoria(categoria);
        noticia.setCoordinador(coordinador);
        noticia.setEstado(1);
        noticia.setFechaPublicacion(fecha);
        if (utilidades.getImagen() != null) {
            utilidades.subirImagen();
            noticia.setImagen("../../Resources/img/Noticias/" + utilidades.getImagen().getSubmittedFileName());
        } else {
            noticia.setImagen("../../Resources/img/Noticias/ESCUDO.jpg");
        }
        noticiaFacade.create(noticia);
        return "Noticias";
    }

    public void remover(Noticia noticiaRemover) {
        noticia = noticiaRemover;
        noticia.setEstado(2);
        noticiaFacade.edit(noticia);
    }

    public List<Noticia> consultarNoticias(int categoria) throws IOException {
        /*for (Noticia n : noticiaFacade.findAll()) {
            int diferencia;
            if (n.getFechaPublicacion().getDate() >= fecha.getDate()) {
                diferencia = n.getFechaPublicacion().getDate() - fecha.getDate();
            } else {
                diferencia = fecha.getDate() - n.getFechaPublicacion().getDate();
            }
            if (diferencia == 14) {
                String search = "Noticias/";
                int inicio = n.getImagen().indexOf(search)+search.length();
                String nombreImg = n.getImagen().substring(inicio);
                Path p = Paths.get(utilidades.getRuta() + nombreImg);
                Files.deleteIfExists(p);
                noticiaFacade.remove(n);
            }
        }*/
        return noticiaFacade.consultarNoticia(1, categoria);
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Usuario getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Usuario coordinador) {
        this.coordinador = coordinador;
    }

    public Categorianoticia getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorianoticia categoria) {
        this.categoria = categoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CorreoControlador getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(CorreoControlador utilidades) {
        this.utilidades = utilidades;
    }

}
