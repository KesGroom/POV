/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Rol;
import Entidades.Tipo;
import Entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=:estado");
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
            System.err.println("Error: " + e.getMessage());
        }
        return usuario;
    }

    public List<Usuario> busquedaUsuario(String doc) {
        Query q;
        q = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=1 AND u.numerodeDocumento LIKE '%:doc%'");
        q.setParameter("doc", doc);
        return q.getResultList();
    }

    public Usuario SearchUser(String doc) {
        Usuario usuario = null;
        try {
            Query q;
            q = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=1 AND u.numerodeDocumento=:doc");
            q.setParameter("doc", doc);            
            usuario = (Usuario) q.getSingleResult();          
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return usuario;
    }
    public Usuario SearchUserId(int id) {
        Usuario usuario = null;
        try {
            Query q;
            q = em.createQuery("SELECT u FROM Usuario u WHERE u.estado=1 AND u.idUsuario=:doc");
            q.setParameter("doc", id);
            
            usuario = (Usuario) q.getSingleResult();
            
        } catch (Exception e) {
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

    public List<Usuario> findByDoc(String doc, Rol rol) { 
        int $estado = 1;
        Rol $rol = rol;
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.equal(user.get("idRoles"), $rol),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }
    
    public List<Usuario> findByName(String name, String lastname, String doc) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        int $estado = 1;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public List<Usuario> findByGender(String name, String lastname, String doc, String genero) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        String $genero = "%" + genero.replaceAll(" ", "%") + "%";
        int $estado = 1;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.like(user.get("genero").as(String.class), $genero),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public List<Usuario> findByRolG(String name, String lastname, String doc, String genero, Rol rol) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        String $genero = "%" + genero.replaceAll(" ", "%") + "%";
        int $estado = 1;
        Rol $rol = rol;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.like(user.get("genero").as(String.class), $genero),
                criteriaBuilder.equal(user.get("idRoles"), $rol),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public List<Usuario> findByRol(String name, String lastname, String doc, Rol rol) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        int $estado = 1;
        Rol $rol = rol;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.equal(user.get("idRoles"), $rol),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public List<Usuario> findByTipoG(String name, String lastname, String doc, String genero, Tipo tipo) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        String $genero = "%" + genero.replaceAll(" ", "%") + "%";
        int $estado = 1;
        Tipo $tipo = tipo;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.like(user.get("genero").as(String.class), $genero),
                criteriaBuilder.equal(user.get("idTipo"), $tipo),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public List<Usuario> findByTipo(String name, String lastname, String doc, Tipo tipo) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        int $estado = 1;
        Tipo $tipo = tipo;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.equal(user.get("idTipo"), $tipo),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public List<Usuario> findByTipoRolG(String name, String lastname, String doc, String genero, Rol rol, Tipo tipo) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        String $genero = "%" + genero.replaceAll(" ", "%") + "%";
        int $estado = 1;
        Rol $rol = rol;
        Tipo $tipo = tipo;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.like(user.get("genero").as(String.class), $genero),
                criteriaBuilder.equal(user.get("idRoles"), $rol),
                criteriaBuilder.equal(user.get("idTipo"), $tipo),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public List<Usuario> findByTipoRol(String name, String lastname, String doc, Rol rol, Tipo tipo) {
        String $nombre = "%" + name.replaceAll(" ", "%") + "%";
        int $estado = 1;
        Rol $rol = rol;
        Tipo $tipo = tipo;
        String $apellido = "%" + lastname.replaceAll(" ", "%") + "%";
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> user = criteriaQuery.from(Usuario.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("nombre").as(String.class), $nombre),
                criteriaBuilder.like(user.get("apellido").as(String.class), $apellido),
                criteriaBuilder.like(user.get("numerodeDocumento").as(String.class), $doc),
                criteriaBuilder.equal(user.get("idRoles"), $rol),
                criteriaBuilder.equal(user.get("idTipo"), $tipo),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Usuario> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public int contarUsuario(int id) {
        Query q = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.estado=1 AND u.idRoles.idRoles=:id");
        q.setParameter("id", id);
        return ((Long) q.getSingleResult()).intValue();

    }

}
