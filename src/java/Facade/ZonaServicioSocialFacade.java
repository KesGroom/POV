/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.ZonaServicioSocial;
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
public class ZonaServicioSocialFacade extends AbstractFacade<ZonaServicioSocial> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZonaServicioSocialFacade() {
        super(ZonaServicioSocial.class);
    }
    
    public List<ZonaServicioSocial> consultarZonaServicioSocial(int estado) {
        Query q = em.createQuery("SELECT z FROM ZonaServicioSocial z WHERE z.estado=:estado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }
   
    
    public ZonaServicioSocial obtenerZona(int id){
        ZonaServicioSocial zona = null;
        try {
        Query q = em.createQuery("SELECT z FROM ZonaServicioSocial z WHERE z.estado=1 AND z.idZonaSS=:id");
        q.setParameter("id", id); 
        zona=(ZonaServicioSocial)q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return zona;
    }
}
