/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Elementoslista;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jusag
 */
@Stateless
public class ElementoslistaFacade extends AbstractFacade<Elementoslista> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementoslistaFacade() {
        super(Elementoslista.class);
    }
    
    public List<Elementoslista> obtenerElementosZona(int id){
        Query q = em.createQuery("SELECT e FROM Elementoslista e WHERE e.zonaServicio.idZonaSS=:id");
        q.setParameter("id", id);
        return q.getResultList();
    } 
    public List<Elementoslista> obtenerElementosArea(int id){
        Query q = em.createQuery("SELECT e FROM Elementoslista e WHERE e.area.idArea=:id");
        q.setParameter("id", id);
        return q.getResultList();
    } 
    public List<Elementoslista> obtenerElementosMateria(int id){
        Query q = em.createQuery("SELECT e FROM Elementoslista e WHERE e.materia.idMateria=:id");
        q.setParameter("id", id);
        return q.getResultList();
    } 
        public List<Elementoslista> consultarElementos(int estado) {
        Query q = em.createQuery("SELECT e FROM ZonaServicioSocial e WHERE e.estado=:estado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }
}
