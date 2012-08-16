/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.entity;

import java.io.Serializable;
import java.util.Set;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "RECEPTOR")
@NamedQueries({
    @NamedQuery(name = "Receptor.findAll", query = "SELECT r FROM Receptor r"),
    @NamedQuery(name = "Receptor.findById", query = "SELECT r FROM Receptor r WHERE r.id = :id"),
    @NamedQuery(name = "Receptor.findByDireccion", query = "SELECT r FROM Receptor r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "Receptor.findByNombre", query = "SELECT r FROM Receptor r WHERE r.nombre = :nombre")})
public class Receptor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Min(value = 0)
    @Max(value = 65535)
    @Column(name = "DIRECCION")
    private Integer direccion;
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @JoinColumn(name = "RED", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Red red;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReceptor", fetch = FetchType.EAGER)
    private Set<Mando> mandoSet;

    public Receptor() {
    }

    public Receptor(Integer id) {
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

    public Red getRed() {
        return red;
    }

    public void setRed(Red red) {
        this.red = red;
    }

    public Set<Mando> getMandoSet() {
        return mandoSet;
    }

    public void setMandoSet(Set<Mando> mandoSet) {
        this.mandoSet = mandoSet;
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
        if (!(object instanceof Receptor)) {
            return false;
        }
        Receptor other = (Receptor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pfc.sensormando.entity.Receptor[ id=" + id + " ]";
    }
}
