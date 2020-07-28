/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Rol;
import Facade.RolFacade;
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
@Named(value = "rolControlador")
@SessionScoped
public class RolControlador implements Serializable {

    /**
     * Creates a new instance of rolControlador
     */
    public RolControlador() {
        rol = new Rol();
        rolPrueba = new Rol();
        rolFacade = new RolFacade();
    }
    private Rol rol;
    private Rol rolPrueba;
    private String rolCrear;

    @EJB
    RolFacade rolFacade;

    @Inject
    AlertasControlador alerta;

    public String registrar() {
        rolPrueba = rolFacade.busquedaRol(rolCrear);
        if (rolPrueba == null) {

            rol.setEstado(1);
            rol.setRol(rolCrear);
            rolFacade.create(rol);

            rol = new Rol();
            alerta.setMensaje("AlertaToast('Registro realizado con éxito','success');");
            return "Roles";
        }
        else{
            alerta.setMensaje("AlertaPopUp('Error al registrar','Este rol ya existe en el sistema','error');");
            return "Roles";
        }
    }

    public List<Rol> consultarRol() {
        return rolFacade.consultarRol(1);
    }

    public void remover(Rol rolRemover) {
        rol = rolRemover;
        if (rol.getIdRoles() != 1 && rol.getIdRoles() != 2 && rol.getIdRoles() != 3 && rol.getIdRoles() != 4) {
            rol.setEstado(2);
            rolFacade.edit(rol);
            alerta.setMensaje("AlertaToast('Eliminación realizada con éxito','success');");
        } else {
            alerta.setMensaje("AlertaPopUp('Error al eliminar','El rol " + rol.getRol() + " no puede ser eliminado, ya que es un rol predeterminado','error');");
        }
    }

    public void consultarId(int id) {
        rol = rolFacade.find(id);
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getRolCrear() {
        return rolCrear;
    }

    public void setRolCrear(String rolCrear) {
        this.rolCrear = rolCrear;
    }

    public Rol getRolPrueba() {
        return rolPrueba;
    }

    public void setRolPrueba(Rol rolPrueba) {
        this.rolPrueba = rolPrueba;
    }

}
