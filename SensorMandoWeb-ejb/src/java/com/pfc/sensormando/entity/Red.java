/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfc.sensormando.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Raul
 */
@Entity
@Table(name = "RED", uniqueConstraints =
@UniqueConstraint(columnNames = {"CANAL", "ID_RED"}))
@NamedQueries({
    @NamedQuery(name = "Red.findAll", query = "SELECT r FROM Red r"),
    @NamedQuery(name = "Red.findById", query = "SELECT r FROM Red r WHERE r.id = :id"),
    @NamedQuery(name = "Red.findByCanal", query = "SELECT r FROM Red r WHERE r.canal = :canal"),
    @NamedQuery(name = "Red.findByIdRed", query = "SELECT r FROM Red r WHERE r.idRed = :idRed"),
    @NamedQuery(name = "Red.findByIp", query = "SELECT r FROM Red r WHERE r.ip = :ip"),
    @NamedQuery(name = "Red.findByNombre", query = "SELECT r FROM Red r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Red.findByPuerto", query = "SELECT r FROM Red r WHERE r.puerto = :puerto")})
public class Red implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANAL")
    private int canal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_RED")
    private int idRed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IP")
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PUERTO")
    private String puerto;
    @OneToMany(mappedBy = "red", fetch = FetchType.EAGER)
    private Set<Receptor> receptorSet;

    public Red() {
    }

    public Red(Integer id) {
        this.id = id;
    }

    public Red(Integer id, int canal, int idRed, String ip, String nombre, String puerto) {
        this.id = id;
        this.canal = canal;
        this.idRed = idRed;
        this.ip = ip;
        this.nombre = nombre;
        this.puerto = puerto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCanal() {
        return canal;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public int getIdRed() {
        return idRed;
    }

    public void setIdRed(int idRed) {
        this.idRed = idRed;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public Set<Receptor> getReceptorSet() {
        return receptorSet;
    }

    public void setReceptorSet(Set<Receptor> receptorSet) {
        this.receptorSet = receptorSet;
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
        if (!(object instanceof Red)) {
            return false;
        }
        Red other = (Red) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pfc.sensormando.entity.Red[ id=" + id + " ]";
    }
}
