/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Curso;
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
public class CursoFacade extends AbstractFacade<Curso> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }

    public Curso obtenerCurso(String nombre) {
        Curso curso = null;
        try {
            Query q = em.createQuery("SELECT c FROM Curso c WHERE c.estado=1 AND c.curso=:nombre");
            q.setParameter("nombre", nombre);
            curso = (Curso) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return curso;
    }
    public Curso validarSalon(String salon) {
        Curso curso = null;
        try {
            Query q = em.createQuery("SELECT c FROM Curso c WHERE c.estado=1 AND c.salon=:salon");
            q.setParameter("salon", salon);
            curso = (Curso) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return curso;
    }

    public List<Curso> consultarCurso(int estado) {
        Query q = em.createQuery("SELECT c FROM Curso c WHERE c.estado=:estado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    public List<Curso> consultarCursoxGrado(int grado) {
        Query q = em.createQuery("SELECT c FROM Curso c WHERE c.estado=1 AND c.grado.idGrado=:grado");
        q.setParameter("grado", grado);
        return q.getResultList();
    }

}
