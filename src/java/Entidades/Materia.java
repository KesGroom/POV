/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kesgr
 */
@Entity
@Table(name = "materias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria")
    , @NamedQuery(name = "Materia.findByMateria", query = "SELECT m FROM Materia m WHERE m.materia = :materia")
    , @NamedQuery(name = "Materia.findByEstado", query = "SELECT m FROM Materia m WHERE m.estado = :estado")
    , @NamedQuery(name = "Materia.findByCantCompetencias", query = "SELECT m FROM Materia m WHERE m.cantCompetencias = :cantCompetencias")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Materia")
    private Integer idMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Materia")
    private String materia;
    @Column(name = "Estado")
    private Integer estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantCompetencias")
    private int cantCompetencias;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria", fetch = FetchType.LAZY)
    private List<DocenteMateria> docenteMateriaList;
    @OneToMany(mappedBy = "materia", fetch = FetchType.LAZY)
    private List<Elementoslista> elementoslistaList;
    @JoinColumn(name = "Id_Area", referencedColumnName = "idAreaGrado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Areagrado idArea;

    public Materia() {
    }

    public Materia(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Materia(Integer idMateria, String materia, int cantCompetencias) {
        this.idMateria = idMateria;
        this.materia = materia;
        this.cantCompetencias = cantCompetencias;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public int getCantCompetencias() {
        return cantCompetencias;
    }

    public void setCantCompetencias(int cantCompetencias) {
        this.cantCompetencias = cantCompetencias;
    }

    @XmlTransient
    public List<DocenteMateria> getDocenteMateriaList() {
        return docenteMateriaList;
    }

    public void setDocenteMateriaList(List<DocenteMateria> docenteMateriaList) {
        this.docenteMateriaList = docenteMateriaList;
    }

    @XmlTransient
    public List<Elementoslista> getElementoslistaList() {
        return elementoslistaList;
    }

    public void setElementoslistaList(List<Elementoslista> elementoslistaList) {
        this.elementoslistaList = elementoslistaList;
    }

    public Areagrado getIdArea() {
        return idArea;
    }

    public void setIdArea(Areagrado idArea) {
        this.idArea = idArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Materia[ idMateria=" + idMateria + " ]";
    }
    
}
