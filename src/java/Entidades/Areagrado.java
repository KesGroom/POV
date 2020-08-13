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
import javax.persistence.Lob;
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
@Table(name = "areagrado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areagrado.findAll", query = "SELECT a FROM Areagrado a")
    , @NamedQuery(name = "Areagrado.findByIdAreaGrado", query = "SELECT a FROM Areagrado a WHERE a.idAreaGrado = :idAreaGrado")
    , @NamedQuery(name = "Areagrado.findByEstado", query = "SELECT a FROM Areagrado a WHERE a.estado = :estado")})
public class Areagrado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAreaGrado")
    private Integer idAreaGrado;
    @Lob
    @Size(max = 65535)
    @Column(name = "Competencias")
    private String competencias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Estado")
    private int estado;
    @JoinColumn(name = "Area", referencedColumnName = "Id_Area")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area area;
    @JoinColumn(name = "Grado", referencedColumnName = "Id_Grado")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grado grado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArea", fetch = FetchType.LAZY)
    private List<Materia> materiaList;

    public Areagrado() {
    }

    public Areagrado(Integer idAreaGrado) {
        this.idAreaGrado = idAreaGrado;
    }

    public Areagrado(Integer idAreaGrado, int estado) {
        this.idAreaGrado = idAreaGrado;
        this.estado = estado;
    }

    public Integer getIdAreaGrado() {
        return idAreaGrado;
    }

    public void setIdAreaGrado(Integer idAreaGrado) {
        this.idAreaGrado = idAreaGrado;
    }

    public String getCompetencias() {
        return competencias;
    }

    public void setCompetencias(String competencias) {
        this.competencias = competencias;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }

    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreaGrado != null ? idAreaGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areagrado)) {
            return false;
        }
        Areagrado other = (Areagrado) object;
        if ((this.idAreaGrado == null && other.idAreaGrado != null) || (this.idAreaGrado != null && !this.idAreaGrado.equals(other.idAreaGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Areagrado[ idAreaGrado=" + idAreaGrado + " ]";
    }
    
}
