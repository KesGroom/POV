/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Tipo;
import Facade.TipoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author jusag
 */
@Named(value = "tipoControlador")
@SessionScoped
public class TipoControlador implements Serializable {

    /**
     * Creates a new instance of tipoControlador
     */
    public TipoControlador() {
        tipo = new Tipo();
        tipoPrueba = new Tipo();
        tipoFacade = new TipoFacade();
    }
    private Tipo tipo;
    private Tipo tipoPrueba;
    private String tipoCrear;

    @Inject
    AlertasControlador alerta;

    @EJB
    TipoFacade tipoFacade;

    public String registrar() {
        tipoPrueba = tipoFacade.busquedaTipo(tipoCrear);
        if (tipoPrueba == null) {
            tipo.setTipo(tipoCrear);
            tipo.setEstado(1);
            tipoFacade.create(tipo);
            alerta.setMensaje("AlertaToast('Registro realizado con éxito','success');");
            return "Roles";
        } else {
            alerta.setMensaje("AlertaPopUp('Error al registrar','Este tipo de documento ya existe en el sistema','error');");
            return "Roles";
        }

    }

    public void remove(Tipo tipoRemover) {
        tipo = tipoRemover;
        if (tipo.getIdTipo() != 1 && tipo.getIdTipo() != 2 && tipo.getIdTipo() != 3 && tipo.getIdTipo() != 4) {
            tipo.setEstado(2);
            tipoFacade.edit(tipo);
            alerta.setMensaje("AlertaToast('Eliminación realizada con éxito','success');");
        }
        else{
            alerta.setMensaje("AlertaPopUp('Error al eliminar','El tipo de documento " + tipo.getTipo() + " no puede ser eliminado, ya que es un valor predeterminado','error');");
        }

    }

    public void consultarId(int id) {
        tipoFacade.find(id);
    }

    public List<Tipo> consultarTodos() {
        return tipoFacade.consultarTipo(1);
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(Tipo tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public String getTipoCrear() {
        return tipoCrear;
    }

    public void setTipoCrear(String tipoCrear) {
        this.tipoCrear = tipoCrear;
    }

}
