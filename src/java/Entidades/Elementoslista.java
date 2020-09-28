/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jusag
 */
@Entity
@Table(name = "elementoslista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elementoslista.findAll", query = "SELECT e FROM Elementoslista e")
    , @NamedQuery(name = "Elementoslista.findById", query = "SELECT e FROM Elementoslista e WHERE e.id = :id")})
public class Elementoslista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "itemList")
    private String itemList;
    @JoinColumn(name = "area", referencedColumnName = "idAreaGrado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Areagrado area;
    @JoinColumn(name = "materia", referencedColumnName = "Id_Materia")
    @ManyToOne(fetch = FetchType.LAZY)
    private Materia materia;
    @JoinColumn(name = "zonaServicio", referencedColumnName = "Id_ZonaSS")
    @ManyToOne(fetch = FetchType.LAZY)
    private ZonaServicioSocial zonaServicio;

    public Elementoslista() {
    }

    public Elementoslista(Integer id) {
        this.id = id;
    }

    public Elementoslista(Integer id, String itemList) {
        this.id = id;
        this.itemList = itemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemList() {
        return itemList;
    }

    public void setItemList(String itemList) {
        this.itemList = itemList;
    }

    public Areagrado getArea() {
        return area;
    }

    public void setArea(Areagrado area) {
        this.area = area;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elementoslista)) {
            return false;
        }
        Elementoslista other = (Elementoslista) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Elementoslista[ id=" + id + " ]";
    }
    
}
