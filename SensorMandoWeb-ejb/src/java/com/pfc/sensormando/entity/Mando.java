/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "MANDO", uniqueConstraints =
@UniqueConstraint(columnNames = {"DIRECCION", "ID_RECEPTOR"}))
@NamedQueries({
    @NamedQuery(name = "Mando.findAll", query = "SELECT m FROM Mando m"),
    @NamedQuery(name = "Mando.findById", query = "SELECT m FROM Mando m WHERE m.id = :id"),
    @NamedQuery(name = "Mando.findByDireccion", query = "SELECT m FROM Mando m WHERE m.direccion = :direccion"),
    @NamedQuery(name = "Mando.findByNombre", query = "SELECT m FROM Mando m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Mando.findByReceptor", query = "SELECT m FROM Mando m WHERE m.idReceptor = :idReceptor")})
public class Mando implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Min(value = 0)
    @Max(value = 65535)
    @Basic(optional = false)
    @NotNull
    @Column(name = "DIRECCION")
    private Integer direccion;
    @Basic(optional = false)
    @NotNull
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @NotNull
    @JoinColumn(name = "ID_RECEPTOR", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Receptor idReceptor;

    public Mando() {
    }

    public Mando(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDireccion() {
        return direccion;
    }

    public void setDireccion(Integer direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Receptor getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(Receptor idReceptor) {
        this.idReceptor = idReceptor;
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
        if (!(object instanceof Mando)) {
            return false;
        }
        Mando other = (Mando) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pfc.sensormando.entity.Mando[ id=" + id + " ]";
    }
}
