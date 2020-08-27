/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entidades.Estudiante;
import Entidades.Rol;
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
public class EstudianteFacade extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "POV_Gaes7PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    public List<Estudiante> consultarEstudiante(int estado) {
        Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.estado=:estado");
        q.setParameter("estado", estado);
        return q.getResultList();
    }

    public List<Estudiante> EstudianteBitacora() {
        Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.estado=1 AND e.estadoServicioSocial=2");
        return q.getResultList();
    }

    public List<Estudiante> EstudianteCurso(String Curso) {
        Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.estado=1 AND e.idCurso.curso=:Curso ORDER  BY e.usuario.apellido ASC");
        q.setParameter("Curso", Curso);
        return q.getResultList();
    }

    public List<Estudiante> EstudianteAcudiente(int id) {
        Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.estado=1 AND e.idAcudiente.idUsuario=:id");
        q.setParameter("id", id);
        return q.getResultList();
    }

    public Estudiante EstudianteDoc(Usuario id) {
        Estudiante estudiante = null;
        try {
            Query q = em.createQuery("SELECT e FROM Estudiante e WHERE e.estado=1 AND e.usuario=:id");
            q.setParameter("id", id);
            estudiante = (Estudiante) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return estudiante;
    }

    public List<Estudiante> findByDoc(String doc) {
        int $estado = 1;
        String $doc = "%" + doc.replaceAll(" ", "%") + "%";
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Estudiante> criteriaQuery = criteriaBuilder.createQuery(Estudiante.class);
        Root<Estudiante> user = criteriaQuery.from(Estudiante.class);
        criteriaQuery.select(user);
        criteriaQuery.where(criteriaBuilder.like(user.get("IdUsuario").as(String.class), $doc),
                criteriaBuilder.equal(user.get("estado"), $estado));
        List<Estudiante> list = em.createQuery(criteriaQuery).getResultList();
        return list;
    }

    public int countStudent(int id) {
        Query q = em.createQuery("SELECT COUNT(e) FROM Estudiante e WHERE e.estado=1 AND e.idCurso.idCurso=:id");
        q.setParameter("id", id);
        return ((Long) q.getSingleResult()).intValue();
    }

}
