/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> origin/POV-by-Oscar
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
<<<<<<< HEAD
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
=======
>>>>>>> origin/POV-by-Oscar
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
<<<<<<< HEAD
 * @author kesgr
=======
 * @author Oscar M Jara C
>>>>>>> origin/POV-by-Oscar
 */
@Entity
@Table(name = "salaserviciosocial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salaserviciosocial.findAll", query = "SELECT s FROM Salaserviciosocial s")
    , @NamedQuery(name = "Salaserviciosocial.findByIdSSS", query = "SELECT s FROM Salaserviciosocial s WHERE s.idSSS = :idSSS")
    , @NamedQuery(name = "Salaserviciosocial.findByEstadoServicio", query = "SELECT s FROM Salaserviciosocial s WHERE s.estadoServicio = :estadoServicio")
<<<<<<< HEAD
    , @NamedQuery(name = "Salaserviciosocial.findByFechaPostulacion", query = "SELECT s FROM Salaserviciosocial s WHERE s.fechaPostulacion = :fechaPostulacion")})
=======
    , @NamedQuery(name = "Salaserviciosocial.findByEstado", query = "SELECT s FROM Salaserviciosocial s WHERE s.estado = :estado")})
>>>>>>> origin/POV-by-Oscar
public class Salaserviciosocial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSSS")
    private Integer idSSS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "EstadoServicio")
    private String estadoServicio;
    @Basic(optional = false)
    @NotNull
<<<<<<< HEAD
    @Column(name = "FechaPostulacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPostulacion;
=======
    @Column(name = "Estado")
    private int estado;
>>>>>>> origin/POV-by-Oscar
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salaDeServicio", fetch = FetchType.LAZY)
    private List<BitacoraServicioSocial> bitacoraServicioSocialList;
    @JoinColumn(name = "Estudiante", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Estudiante estudiante;
    @JoinColumn(name = "ZonaServicio", referencedColumnName = "Id_ZonaSS")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ZonaServicioSocial zonaServicio;

    public Salaserviciosocial() {
    }

    public Salaserviciosocial(Integer idSSS) {
        this.idSSS = idSSS;
    }

<<<<<<< HEAD
    public Salaserviciosocial(Integer idSSS, String estadoServicio, Date fechaPostulacion) {
        this.idSSS = idSSS;
        this.estadoServicio = estadoServicio;
        this.fechaPostulacion = fechaPostulacion;
=======
    public Salaserviciosocial(Integer idSSS, String estadoServicio, int estado) {
        this.idSSS = idSSS;
        this.estadoServicio = estadoServicio;
        this.estado = estado;
>>>>>>> origin/POV-by-Oscar
    }

    public Integer getIdSSS() {
        return idSSS;
    }

    public void setIdSSS(Integer idSSS) {
        this.idSSS = idSSS;
    }

    public String getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(String estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

<<<<<<< HEAD
    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
=======
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
>>>>>>> origin/POV-by-Oscar
    }

    @XmlTransient
    public List<BitacoraServicioSocial> getBitacoraServicioSocialList() {
        return bitacoraServicioSocialList;
    }

    public void setBitacoraServicioSocialList(List<BitacoraServicioSocial> bitacoraServicioSocialList) {
        this.bitacoraServicioSocialList = bitacoraServicioSocialList;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public ZonaServicioSocial getZonaServicio() {
        return zonaServicio;
    }

    public void setZonaServicio(ZonaServicioSocial zonaServicio) {
        this.zonaServicio = zonaServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSSS != null ? idSSS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salaserviciosocial)) {
            return false;
        }
        Salaserviciosocial other = (Salaserviciosocial) object;
        if ((this.idSSS == null && other.idSSS != null) || (this.idSSS != null && !this.idSSS.equals(other.idSSS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Salaserviciosocial[ idSSS=" + idSSS + " ]";
    }
    
}
