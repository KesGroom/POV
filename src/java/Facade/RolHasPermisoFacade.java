/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.RolHasPermiso;
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
public class RolHasPermisoFacade extends AbstractFacade<RolHasPermiso> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolHasPermisoFacade() {
        super(RolHasPermiso.class);
    }

    public List<RolHasPermiso> validarPermiso(int id) {
        Query q = em.createQuery("SELECT rh FROM RolHasPermiso rh WHERE rh.estado=1 AND rh.rol.idRoles=:id");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public RolHasPermiso validarPermiso2(int id, int per) {
        RolHasPermiso rh = null;
        try {
            Query q = em.createQuery("SELECT rh FROM RolHasPermiso rh WHERE rh.estado=1 AND rh.rol.idRoles=:id AND rh.permiso.idPermiso=:per");
            q.setParameter("id", id);
            q.setParameter("per", per);
            rh = (RolHasPermiso) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return rh;
    }
    public RolHasPermiso validarPermiso3(int id, int per) {
        RolHasPermiso rh = null;
        try {
            Query q = em.createQuery("SELECT rh FROM RolHasPermiso rh WHERE rh.estado=2 AND rh.rol.idRoles=:id AND rh.permiso.idPermiso=:per");
            q.setParameter("id", id);
            q.setParameter("per", per);
            rh = (RolHasPermiso) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        return rh;
    }

}
