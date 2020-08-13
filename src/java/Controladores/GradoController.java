/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Grado;
import Facade.GradoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;


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
        gradoFacade = new GradoFacade();
    }
    
    private Grado grado;
    
    @EJB
    GradoFacade gradoFacade;
    
    public void registrar(){
        gradoFacade.create(grado);
    }
 
    public List<Grado> consultarTodos(){
        return gradoFacade.consultarGrado(1);
    }
    
    public String consultarGradoName(String name){
        gradoFacade.consultGrado(1, name);
        
        return "";
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }
    
    
    
}
