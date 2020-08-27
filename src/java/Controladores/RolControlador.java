/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entidades.Permiso;
import Entidades.Rol;
import Entidades.RolHasPermiso;
import Facade.PermisoFacade;
import Facade.RolFacade;
import Facade.RolHasPermisoFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        permiso = new Permiso();
        rh = new RolHasPermiso();
    }
    private Rol rol;
    private Rol rolPrueba;
    private RolHasPermiso rh;
    private Permiso permiso;
    private String rolCrear;
    private int[] usuario;
    private int[] servicio;
    private int[] pqrs;
    private int[] citas;
    private int[] administracion;
    private int[] noticias;
    private int[] portafolio;
    private int[] agenda;
    private int[] horario;
    private int[] planillas;
    private int[] consultasAca;
    private List<Permiso> padreList;
    private List<RolHasPermiso> rolPermisoList;
    private int rolSelect;

    //----- Variables para actualizar permisos ------------------------\\
    private int[] permisosEliminados;
    private int[] permisosAgregados;
    private List<RolHasPermiso> permisosRegistrados;
    private List<Permiso> permisosNoRegistrados;

    @EJB
    RolFacade rolFacade;

    @EJB
    RolHasPermisoFacade RHFacade;

    @EJB
    PermisoFacade permisoFacade;

    @EJB
    UsuarioFacade usuarioFacade;

    @Inject
    AlertasControlador alerta;

    @Inject
    CorreoControlador correo;

    public String registrar() {
        if (!"".equals(rolCrear)) {
            rolPrueba = rolFacade.busquedaRol(rolCrear);
            if (rolPrueba == null) {
                rol.setEstado(1);
                rol.setRol(rolCrear);

                if (rolSelect != 0) {

                    int listaPermisos[] = new int[usuario.length + servicio.length + pqrs.length + citas.length
                            + administracion.length + noticias.length + portafolio.length + agenda.length
                            + horario.length + planillas.length + consultasAca.length];

                    System.arraycopy(usuario, 0, listaPermisos, 0, usuario.length);
                    System.arraycopy(servicio, 0, listaPermisos, usuario.length, servicio.length);
                    System.arraycopy(pqrs, 0, listaPermisos, usuario.length + servicio.length, pqrs.length);
                    System.arraycopy(citas, 0, listaPermisos, usuario.length + servicio.length + pqrs.length, citas.length);
                    System.arraycopy(administracion, 0, listaPermisos, usuario.length + servicio.length + pqrs.length + citas.length, administracion.length);
                    System.arraycopy(noticias, 0, listaPermisos, usuario.length + servicio.length + pqrs.length + citas.length + administracion.length, noticias.length);
                    System.arraycopy(portafolio, 0, listaPermisos, usuario.length + servicio.length + pqrs.length + citas.length + administracion.length
                            + noticias.length, portafolio.length);
                    System.arraycopy(agenda, 0, listaPermisos, usuario.length + servicio.length + pqrs.length + citas.length + administracion.length
                            + noticias.length + portafolio.length, agenda.length);
                    System.arraycopy(horario, 0, listaPermisos, usuario.length + servicio.length + pqrs.length + citas.length + administracion.length
                            + noticias.length + portafolio.length + agenda.length, horario.length);
                    System.arraycopy(planillas, 0, listaPermisos, usuario.length + servicio.length + pqrs.length + citas.length + administracion.length
                            + noticias.length + portafolio.length + agenda.length + horario.length, planillas.length);
                    System.arraycopy(consultasAca, 0, listaPermisos, usuario.length + servicio.length + pqrs.length + citas.length + administracion.length
                            + noticias.length + portafolio.length + agenda.length + horario.length + planillas.length, consultasAca.length);

                    if (listaPermisos != null) {
                        rolFacade.create(rol);
                        padreList = new ArrayList<>();
                        for (int i = 0; i < listaPermisos.length; i++) {
                            permiso = permisoFacade.obtenerPermiso(listaPermisos[i]);
                            padreList.add(permiso);
                            for (int j = 1; j < padreList.size(); j++) {
                                if (Objects.equals(padreList.get(j).getPermisoPadre().getIdPermiso(), padreList.get(j - 1).getPermisoPadre().getIdPermiso())) {
                                    padreList.remove(j);
                                }
                            }
                        }

                        for (int i = 0; i < listaPermisos.length; i++) {
                            permiso = permisoFacade.obtenerPermiso(listaPermisos[i]);
                            rh.setRol(rol);
                            rh.setPermiso(permiso);
                            rh.setEstado(1);
                            RHFacade.create(rh);
                        }

                        for (int i = 0; i < padreList.size(); i++) {
                            permiso = padreList.get(i);
                            rh.setRol(rol);
                            rh.setPermiso(permiso);
                            rh.setEstado(1);
                            RHFacade.create(rh);
                        }

                    } else {
                        alerta.setMensaje("AlertaPopUp('Error al registrar','No se han seleccionado permisos para este rol','error');");
                        return "";
                    }
                    rol = new Rol();
                    alerta.setMensaje("AlertaToast('Registro realizado con éxito','success');");
                    return "Roles";
                } else {
                    rolFacade.create(rol);
                    this.rolPermisoList = RHFacade.validarPermiso(rolSelect);
                    for (int i = 0; i < rolPermisoList.size(); i++) {
                        rh.setEstado(1);
                        rh.setRol(rol);
                        permiso = permisoFacade.obtenerPermiso(rolPermisoList.get(i).getPermiso().getIdPermiso());
                        rh.setPermiso(permiso);
                        RHFacade.create(rh);
                    }
                    rol = new Rol();
                    alerta.setMensaje("AlertaToast('Registro realizado con éxito','success');");
                    return "Roles";
                }
            } else {
                alerta.setMensaje("AlertaPopUp('Error al registrar','Este rol ya existe en el sistema','error');");
                return "Roles";
            }
        } else {
            alerta.setMensaje("AlertaPopUp('Error al registrar','Debe ingresar un nombre para el rol','error');");
            return "";
        }
    }

    public List<Rol> consultarRol() {
        return rolFacade.consultarRol(1);
    }

    public String preActualizarPermisos(Rol rolAct) {
        rol = rolAct;

        permisosRegistrados = RHFacade.validarPermiso(rol.getIdRoles());
        for (int i = 0; i < permisosRegistrados.size(); i++) {
            if (permisosRegistrados.get(i).getPermiso().getPermisoPadre() == null) {
                permisosRegistrados.remove(i);
            }
        }
        permisosNoRegistrados = permisoFacade.consultarPermiso(1);
        for (int i = 0; i < permisosNoRegistrados.size(); i++) {
            if (permisosNoRegistrados.get(i).getPermisoPadre() == null) {
                permisosNoRegistrados.remove(i);
            }
        }

        for (int j = 0; j < permisosRegistrados.size(); j++) {
            for (int i = 0; i < permisosNoRegistrados.size(); i++) {
                if (Objects.equals(permisosNoRegistrados.get(i).getIdPermiso(), permisosRegistrados.get(j).getPermiso().getIdPermiso())) {
                    this.permisosNoRegistrados.remove(i);
                }
            }
        }

        return "ActualizarPermiso";
    }

    public String actualizarPermisos() {
        if (permisosEliminados != null || permisosAgregados != null) {
            if (permisosEliminados != null) {
                for (int i = 0; i < permisosEliminados.length; i++) {
                    RolHasPermiso rhp = RHFacade.validarPermiso2(permisosEliminados[i], rol.getIdRoles());
                    rhp.setEstado(2);
                    RHFacade.edit(rhp);
                }
            }
            if (permisosAgregados != null) {
                for (int i = 0; i < permisosAgregados.length; i++) {
                    RolHasPermiso rhp = RHFacade.validarPermiso3(permisosAgregados[i], rol.getIdRoles());
                    if (rhp == null) {
                        rhp.setRol(rol);
                        rhp.setEstado(1);
                        Permiso paco = permisoFacade.obtenerPermiso(permisosAgregados[i]);
                        rhp.setPermiso(paco);
                        RHFacade.create(rhp);
                    } else {
                        rhp.setEstado(1);
                        RHFacade.edit(rhp);
                    }
                }
            }
            alerta.setMensaje("AlertaToast('Actualización realizada con exito','success');");
        } else {
            alerta.setMensaje("AlertaToast('No se ha realizado ningún cambio','info');");
        }

        return "Roles";
    }

    //----- Contadores ----------------------------------------------------\\
    public List<Integer> contadorUsuRol() {
        List<Integer> data = new ArrayList<>();
        List<Rol> roleList = rolFacade.consultarRol(1);
        for (int i = 0; i < roleList.size(); i++) {
            int dataUsu = usuarioFacade.contarUsuario(roleList.get(i).getIdRoles());
            data.add(dataUsu);
        }
        return data;
    }

    public List<String> consultarRolNombre() {
        List<String> nombres = new ArrayList<>();
        List<Rol> roleList = rolFacade.consultarRol(1);
        for (int i = 0; i < roleList.size(); i++) {
            String nameRol = roleList.get(i).getRol();
            nombres.add('"' + nameRol + '"');
        }

        return nombres;
    }

    public List<String> generarColores() {
        int cont = rolFacade.contarRol(1);
        return correo.colores(cont);
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

    public List<Permiso> consultarPermiso(int id) {
        return permisoFacade.consultarHijos(id);
    }

    public List<Permiso> consultarPer() {
        return permisoFacade.consultarHijos(3);
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

    public int[] getUsuario() {
        return usuario;
    }

    public void setUsuario(int[] usuario) {
        this.usuario = usuario;
    }

    public int[] getServicio() {
        return servicio;
    }

    public void setServicio(int[] servicio) {
        this.servicio = servicio;
    }

    public int[] getPqrs() {
        return pqrs;
    }

    public void setPqrs(int[] pqrs) {
        this.pqrs = pqrs;
    }

    public int[] getCitas() {
        return citas;
    }

    public void setCitas(int[] citas) {
        this.citas = citas;
    }

    public int[] getAdministracion() {
        return administracion;
    }

    public void setAdministracion(int[] administracion) {
        this.administracion = administracion;
    }

    public int[] getNoticias() {
        return noticias;
    }

    public void setNoticias(int[] noticias) {
        this.noticias = noticias;
    }

    public int[] getPortafolio() {
        return portafolio;
    }

    public void setPortafolio(int[] portafolio) {
        this.portafolio = portafolio;
    }

    public int[] getAgenda() {
        return agenda;
    }

    public void setAgenda(int[] agenda) {
        this.agenda = agenda;
    }

    public int[] getHorario() {
        return horario;
    }

    public void setHorario(int[] horario) {
        this.horario = horario;
    }

    public int[] getPlanillas() {
        return planillas;
    }

    public void setPlanillas(int[] planillas) {
        this.planillas = planillas;
    }

    public int[] getConsultasAca() {
        return consultasAca;
    }

    public void setConsultasAca(int[] consultasAca) {
        this.consultasAca = consultasAca;
    }

    public RolHasPermiso getRh() {
        return rh;
    }

    public void setRh(RolHasPermiso rh) {
        this.rh = rh;
    }

    public List<Permiso> getPadreList() {
        return padreList;
    }

    public void setPadreList(List<Permiso> padreList) {
        this.padreList = padreList;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public int getRolSelect() {
        return rolSelect;
    }

    public void setRolSelect(int rolSelect) {
        this.rolSelect = rolSelect;
    }

    public List<RolHasPermiso> getRolPermisoList() {
        return rolPermisoList;
    }

    public void setRolPermisoList(List<RolHasPermiso> rolPermisoList) {
        this.rolPermisoList = rolPermisoList;
    }

    public int[] getPermisosEliminados() {
        return permisosEliminados;
    }

    public void setPermisosEliminados(int[] permisosEliminados) {
        this.permisosEliminados = permisosEliminados;
    }

    public int[] getPermisosAgregados() {
        return permisosAgregados;
    }

    public void setPermisosAgregados(int[] permisosAgregados) {
        this.permisosAgregados = permisosAgregados;
    }

    public List<RolHasPermiso> getPermisosRegistrados() {
        return permisosRegistrados;
    }

    public void setPermisosRegistrados(List<RolHasPermiso> permisosRegistrados) {
        this.permisosRegistrados = permisosRegistrados;
    }

    public List<Permiso> getPermisosNoRegistrados() {
        return permisosNoRegistrados;
    }

    public void setPermisosNoRegistrados(List<Permiso> permisosNoRegistrados) {
        this.permisosNoRegistrados = permisosNoRegistrados;
    }

}
