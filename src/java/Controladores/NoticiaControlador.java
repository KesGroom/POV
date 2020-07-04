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
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author kesgr
 */
@Named(value = "noticiaControlador")
@SessionScoped
public class NoticiaControlador implements Serializable {

    /**
     * Creates a new instance of NoticiaControlador
     */
    public NoticiaControlador() {
        noticia = new Noticia();
        coordinador = new Usuario();
        categoria = new Categorianoticia();
        
    }
    
    private Noticia noticia;
    private Usuario coordinador;
    private Categorianoticia categoria;
    
    @EJB
    NoticiaFacade noticiaFacade;
    
    public String registrar(){
        
        noticia.setCategoria(categoria);
        noticia.setCoordinador(coordinador);
        noticia.setEstado(1);
        if(noticia.getImagen() == null){
            noticia.setImagen("../../Resources/img/Noticias/ESCUDO.jpg");
        }
        noticiaFacade.create(noticia);
        
        return "Noticias";
    }
    
    public void remover(Noticia noticiaRemover){
        noticia = noticiaRemover;
        noticia.setEstado(2);
        noticiaFacade.edit(noticia);
    }
    
    public List<Noticia> consultarNoticias(int categoria){
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
    
    
    
}
