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
 * @author kesgr
 */
@Entity
@Table(name = "bitacora_servicio_social")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BitacoraServicioSocial.findAll", query = "SELECT b FROM BitacoraServicioSocial b")
    , @NamedQuery(name = "BitacoraServicioSocial.findByIdBitacoraServicio", query = "SELECT b FROM BitacoraServicioSocial b WHERE b.idBitacoraServicio = :idBitacoraServicio")
    , @NamedQuery(name = "BitacoraServicioSocial.findByFechaRegistro", query = "SELECT b FROM BitacoraServicioSocial b WHERE b.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "BitacoraServicioSocial.findByTiempoPrestado", query = "SELECT b FROM BitacoraServicioSocial b WHERE b.tiempoPrestado = :tiempoPrestado")
    , @NamedQuery(name = "BitacoraServicioSocial.findByEstado", query = "SELECT b FROM BitacoraServicioSocial b WHERE b.estado = :estado")})
public class BitacoraServicioSocial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Bitacora_Servicio")
    private Integer idBitacoraServicio;
    @Column(name = "Fecha_Registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Tiempo_Prestado")
    private int tiempoPrestado;
    @Lob
    @Size(max = 65535)
    @Column(name = "Observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Labores_Realizadas")
    private String laboresRealizadas;
    @Column(name = "Estado")
    private Integer estado;
    @JoinColumn(name = "Coordinador", referencedColumnName = "Id_Usuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario coordinador;
    @JoinColumn(name = "SalaDeServicio", referencedColumnName = "idSSS")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Salaserviciosocial salaDeServicio;

    public BitacoraServicioSocial() {
    }

    public BitacoraServicioSocial(Integer idBitacoraServicio) {
        this.idBitacoraServicio = idBitacoraServicio;
    }

    public BitacoraServicioSocial(Integer idBitacoraServicio, int tiempoPrestado, String laboresRealizadas) {
        this.idBitacoraServicio = idBitacoraServicio;
        this.tiempoPrestado = tiempoPrestado;
        this.laboresRealizadas = laboresRealizadas;
    }

    public Integer getIdBitacoraServicio() {
        return idBitacoraServicio;
    }

    public void setIdBitacoraServicio(Integer idBitacoraServicio) {
        this.idBitacoraServicio = idBitacoraServicio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getTiempoPrestado() {
        return tiempoPrestado;
    }

    public void setTiempoPrestado(int tiempoPrestado) {
        this.tiempoPrestado = tiempoPrestado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getLaboresRealizadas() {
        return laboresRealizadas;
    }

    public void setLaboresRealizadas(String laboresRealizadas) {
        this.laboresRealizadas = laboresRealizadas;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuario getCoordinador() {
        return coordinador;
    }

    public void setCoordinador(Usuario coordinador) {
        this.coordinador = coordinador;
    }

    public Salaserviciosocial getSalaDeServicio() {
        return salaDeServicio;
    }

    public void setSalaDeServicio(Salaserviciosocial salaDeServicio) {
        this.salaDeServicio = salaDeServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacoraServicio != null ? idBitacoraServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraServicioSocial)) {
            return false;
        }
        BitacoraServicioSocial other = (BitacoraServicioSocial) object;
        if ((this.idBitacoraServicio == null && other.idBitacoraServicio != null) || (this.idBitacoraServicio != null && !this.idBitacoraServicio.equals(other.idBitacoraServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BitacoraServicioSocial[ idBitacoraServicio=" + idBitacoraServicio + " ]";
    }
    
}
