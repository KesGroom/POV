/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Salaserviciosocial;
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
public class SalaserviciosocialFacade extends AbstractFacade<Salaserviciosocial> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalaserviciosocialFacade() {
        super(Salaserviciosocial.class);
    }

    public Salaserviciosocial obtenerSala(int idZona, int idEstudiante) {
        Salaserviciosocial sala = null;
        try {
            Query q = em.createQuery("SELECT s FROM Salaserviciosocial s WHERE s.estudiante.idUsuario=:idEstudiante AND s.zonaServicio.idZonaSS=:idZona AND s.estadoServicio='En espera'");
            q.setParameter("idEstudiante", idEstudiante);
            q.setParameter("idZona", idZona);
            sala = (Salaserviciosocial) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return sala;
    }

    public List<Salaserviciosocial> consultarSalaServicioSocial(int estado) {
        Query q = em.createQuery("SELECT s FROM Salaserviciosocial s WHERE s.idSSS =:idSSS");
        q.setParameter("estado", estado);
        return q.getResultList();
    }
}
