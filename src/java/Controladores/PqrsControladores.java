/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Acudiente;
import Entidades.Docente;
import java.util.Date;
import Entidades.Pqrs;
import Entidades.Usuario;
import Facade.PqrsFacade;
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
@Named(value = "pqrsControladores")
@SessionScoped
public class PqrsControladores implements Serializable {

    /**
     * Creates a new instance of pqrsControladores
     */
    public PqrsControladores() {
        pqrs = new Pqrs();
        acudiente = new Acudiente();
        coordinador = new Usuario();
        
    }
    
    private Pqrs pqrs;
    private Acudiente acudiente;
    private Usuario coordinador;
    private Date fecha = new Date();
    
    @EJB
    PqrsFacade pqrsFacade;

    public Pqrs getPqrs() {
        return pqrs;
    }

    public void setPqrs(Pqrs pqrs) {
        this.pqrs = pqrs;
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }
   
    public void registrarPqrs(){
        pqrs.setIdAcudiente(acudiente);
        pqrs.setEstado(1);
        pqrs.setFecha(fecha);
        pqrsFacade.create(pqrs);
        pqrs = new Pqrs();
    }
    public List<Pqrs> consultarPqrs(){
        return pqrsFacade.consultarPQRS(1);
    }
    public String preActualizar(Pqrs pqrsActualizar){
        pqrs = pqrsActualizar;
        acudiente = pqrsActualizar.getIdAcudiente();
        return "ResponderPQRS";
                
    }
    public String preActualizarAcu(Pqrs pqrsActualizar){
        pqrs = pqrsActualizar;
        acudiente = pqrsActualizar.getIdAcudiente();
        return "ActualizarPQRS";
                
    }
    public String actualizarPqrs(){
        pqrs.setIdCoordinador(coordinador);
        pqrs.setIdAcudiente(acudiente);
        pqrsFacade.edit(pqrs);
        pqrs = new Pqrs();
        return "PQRS";
    }
    public String actualizarAcuPqrs(){
        pqrs.setIdAcudiente(acudiente);
        pqrsFacade.edit(pqrs);
        pqrs = new Pqrs();
        return "PQRS";
    }
    
    public void Remover(Pqrs pqrsEliminar){
        pqrs=pqrsEliminar;
        pqrs.setEstado(2);
        pqrsFacade.edit(pqrs);
    }
   
    public List<Pqrs> consultarCategoria(String cat){
        return pqrsFacade.consultarCategoria(cat);
    }
    
    public List<Pqrs> consultarAcudiente(int id){
        return pqrsFacade.consultarAcudiente(id);
    }

    public Usuario getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Usuario coordinador) {
        this.coordinador = coordinador;
    }

    public java.util.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.util.Date fecha) {
        this.fecha = fecha;
    }
    
    
     
}
