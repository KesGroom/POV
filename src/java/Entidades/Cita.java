/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
<<<<<<< HEAD
 * @author kesgr
=======
 * @author Oscar M Jara C
>>>>>>> origin/POV-by-Oscar
 */
@Entity
@Table(name = "citas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
    , @NamedQuery(name = "Cita.findByIdCita", query = "SELECT c FROM Cita c WHERE c.idCita = :idCita")
    , @NamedQuery(name = "Cita.findByAtencionArea", query = "SELECT c FROM Cita c WHERE c.atencionArea = :atencionArea")
    , @NamedQuery(name = "Cita.findByAtencionCurso", query = "SELECT c FROM Cita c WHERE c.atencionCurso = :atencionCurso")
    , @NamedQuery(name = "Cita.findByFechaRegistro", query = "SELECT c FROM Cita c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cita.findByFechaCita", query = "SELECT c FROM Cita c WHERE c.fechaCita = :fechaCita")
    , @NamedQuery(name = "Cita.findByEstado", query = "SELECT c FROM Cita c WHERE c.estado = :estado")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Cita")
    private Integer idCita;
    @Column(name = "AtencionArea")
    private Integer atencionArea;
    @Column(name = "AtencionCurso")
    private Integer atencionCurso;
    @Column(name = "Fecha_Registro")
<<<<<<< HEAD
    @Temporal(TemporalType.DATE)
=======
    @Temporal(TemporalType.TIMESTAMP)
>>>>>>> origin/POV-by-Oscar
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Cita")
<<<<<<< HEAD
    @Temporal(TemporalType.DATE)
=======
    @Temporal(TemporalType.TIMESTAMP)
>>>>>>> origin/POV-by-Oscar
    private Date fechaCita;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Asunto")
    private String asunto;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "Acudiente", referencedColumnName = "Id_Usuario")
<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
=======
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
>>>>>>> origin/POV-by-Oscar
    private Acudiente acudiente;

    public Cita() {
    }

    public Cita(Integer idCita) {
        this.idCita = idCita;
    }

    public Cita(Integer idCita, Date fechaCita, String asunto) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.asunto = asunto;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public Integer getAtencionArea() {
        return atencionArea;
    }

    public void setAtencionArea(Integer atencionArea) {
        this.atencionArea = atencionArea;
    }

    public Integer getAtencionCurso() {
        return atencionCurso;
    }

    public void setAtencionCurso(Integer atencionCurso) {
        this.atencionCurso = atencionCurso;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCita != null ? idCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.idCita == null && other.idCita != null) || (this.idCita != null && !this.idCita.equals(other.idCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cita[ idCita=" + idCita + " ]";
    }
    
}
