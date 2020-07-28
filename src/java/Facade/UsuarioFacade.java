/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kesgr
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public List<Usuario> busquedaRol(int rol) {

        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.idRoles.idRoles=:rol AND u.estado=1");
        q.setParameter("rol", rol);

        return q.getResultList();
    }

    public Usuario UserLog(String documento, String contrasenna) {
        Usuario usuario = null;
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.numerodeDocumento=:Doc AND u.contrasenna=:Pass");
            q.setParameter("Doc", documento);
            q.setParameter("Pass", contrasenna);
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return usuario;
    }

    public List<Usuario> consultarUsuario(int estado) {
        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=:estado ORDER BY u.idUsuario DESC");
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    public List<Usuario> obtenerUsuario(int id) {
        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.idUsuario=:Id");
        q.setParameter("Id", id);
        return q.getResultList();
    }

    public List<Usuario> busqueda(String busqueda) {
        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre LIKE '%:busqueda%' OR u.apellido LIKE '%:busqueda%' OR u.numerodeDocumento LIKE '%:busqueda%'");
        q.setParameter("busqueda", busqueda);
        return q.getResultList();
    }

    public Usuario validacionDoc(String doc) {
        Usuario usuario = null;
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.numerodeDocumento=:doc");
            q.setParameter("doc", doc);
            usuario = (Usuario) q.getSingleResult();

        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }
        return usuario;
    }

    public Usuario OlvidoContra(String doc, String mail) {
        Usuario us = null;
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u Where u.numerodeDocumento=:Doc AND u.correoElectronico=:mail AND u.estado=1");
            q.setParameter("Doc", doc);
            q.setParameter("mail", mail);

            us = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return us;
    }
}
