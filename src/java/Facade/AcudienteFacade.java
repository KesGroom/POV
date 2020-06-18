/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Acudiente;
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
public class AcudienteFacade extends AbstractFacade<Acudiente> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcudienteFacade() {
        super(Acudiente.class);
    }
    
    public List<Acudiente> consultarAcudiente(int estado){
        Query q = em.createQuery("SELECT acu FROM Acudiente acu WHERE acu.estado=:estado");
                q.setParameter("estado", estado);
                return q.getResultList();
    }
    
}