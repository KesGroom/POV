/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Grado;
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
public class GradoFacade extends AbstractFacade<Grado> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GradoFacade() {
        super(Grado.class);
    }

    public List<Grado> consultarGrado(int estado) {
        Query q = em.createQuery("SELECT g FROM Grado g WHERE g.estado=:estado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }
    public List<Grado> consultarGradoXnivel(String nivel) {
        Query q = em.createQuery("SELECT g FROM Grado g WHERE g.educacion=:nivel AND g.estado=1");
        q.setParameter("nivel", nivel);
        return q.getResultList();
    }

    public Grado consultGrado(int estado, String name) {
        Grado grado = null;
        try {
            Query q = em.createQuery("SELECT g FROM Grado g WHERE g.estado=:estado AND g.grado=:name");
            q.setParameter("estado", estado);
            q.setParameter("name", name);
            grado = (Grado) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error "+e.getMessage());
        }
        return grado;
    }
}
