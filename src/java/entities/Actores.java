/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
 * @author 2h
 */
@Entity
@Table(name = "actores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actores.findAll", query = "SELECT a FROM Actores a"),
    @NamedQuery(name = "Actores.findByCodAutor", query = "SELECT a FROM Actores a WHERE a.codAutor = :codAutor"),
    @NamedQuery(name = "Actores.findByNomAutor", query = "SELECT a FROM Actores a WHERE a.nomAutor = :nomAutor"),
    @NamedQuery(name = "Actores.findByLugarNacimiento", query = "SELECT a FROM Actores a WHERE a.lugarNacimiento = :lugarNacimiento")})
public class Actores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codAutor")
    private Integer codAutor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomAutor")
    private String nomAutor;
    @Size(max = 50)
    @Column(name = "lugarNacimiento")
    private String lugarNacimiento;
    @ManyToMany(mappedBy = "actoresList")
    private List<Fotos> fotosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actores")
    private List<Peliculasactores> peliculasactoresList;

    public Actores() {
    }

    public Actores(Integer codAutor) {
        this.codAutor = codAutor;
    }

    public Actores(Integer codAutor, String nomAutor) {
        this.codAutor = codAutor;
        this.nomAutor = nomAutor;
    }

    public Integer getCodAutor() {
        return codAutor;
    }

    public void setCodAutor(Integer codAutor) {
        this.codAutor = codAutor;
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public void setNomAutor(String nomAutor) {
        this.nomAutor = nomAutor;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    @XmlTransient
    public List<Fotos> getFotosList() {
        return fotosList;
    }

    public void setFotosList(List<Fotos> fotosList) {
        this.fotosList = fotosList;
    }

    @XmlTransient
    public List<Peliculasactores> getPeliculasactoresList() {
        return peliculasactoresList;
    }

    public void setPeliculasactoresList(List<Peliculasactores> peliculasactoresList) {
        this.peliculasactoresList = peliculasactoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codAutor != null ? codAutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actores)) {
            return false;
        }
        Actores other = (Actores) object;
        if ((this.codAutor == null && other.codAutor != null) || (this.codAutor != null && !this.codAutor.equals(other.codAutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Actores[ codAutor=" + codAutor + " ]";
    }
    
}
