/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Rol;
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
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public List<Rol> consultarRol(int estado) {
        Query q = em.createQuery("SELECT r FROM Rol r WHERE r.estado=:estado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }
    
    public Rol busquedaRol(String rel) {
        Rol rol = null;
        try {
            Query q = em.createQuery("SELECT r FROM Rol r WHERE r.rol=:rel");
            q.setParameter("rel", rel);
            rol = (Rol) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return rol;
    }

    public int contarRol(int estado) {
        Query q = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.estado=:estado");
        q.setParameter("estado", estado);
        return ((Long) q.getSingleResult()).intValue();

    }
    
}
