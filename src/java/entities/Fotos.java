/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2h
 */
@Entity
@Table(name = "fotos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fotos.findAll", query = "SELECT f FROM Fotos f"),
    @NamedQuery(name = "Fotos.findByNomArchivo", query = "SELECT f FROM Fotos f WHERE f.nomArchivo = :nomArchivo")})
public class Fotos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomArchivo")
    private String nomArchivo;
    @JoinTable(name = "actoresfotos", joinColumns = {
        @JoinColumn(name = "nomArchivo", referencedColumnName = "nomArchivo")}, inverseJoinColumns = {
        @JoinColumn(name = "codAutor", referencedColumnName = "codAutor")})
    @ManyToMany
    private List<Actores> actoresList;

    public Fotos() {
    }

    public Fotos(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    public String getNomArchivo() {
        return nomArchivo;
    }

    public void setNomArchivo(String nomArchivo) {
        this.nomArchivo = nomArchivo;
    }

    @XmlTransient
    public List<Actores> getActoresList() {
        return actoresList;
    }

    public void setActoresList(List<Actores> actoresList) {
        this.actoresList = actoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nomArchivo != null ? nomArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotos)) {
            return false;
        }
        Fotos other = (Fotos) object;
        if ((this.nomArchivo == null && other.nomArchivo != null) || (this.nomArchivo != null && !this.nomArchivo.equals(other.nomArchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fotos[ nomArchivo=" + nomArchivo + " ]";
    }
    
}
