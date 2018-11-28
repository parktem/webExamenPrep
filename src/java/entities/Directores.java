/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2h
 */
@Entity
@Table(name = "directores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Directores.findAll", query = "SELECT d FROM Directores d"),
    @NamedQuery(name = "Directores.findByCodDirector", query = "SELECT d FROM Directores d WHERE d.codDirector = :codDirector"),
    @NamedQuery(name = "Directores.findByNomDirector", query = "SELECT d FROM Directores d WHERE d.nomDirector = :nomDirector"),
    @NamedQuery(name = "Directores.findByFoto", query = "SELECT d FROM Directores d WHERE d.foto = :foto")})
public class Directores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codDirector")
    private Integer codDirector;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomDirector")
    private String nomDirector;
    @Size(max = 30)
    @Column(name = "foto")
    private String foto;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codDirector")
    private Peliculas peliculas;

    public Directores() {
    }

    public Directores(Integer codDirector) {
        this.codDirector = codDirector;
    }

    public Directores(Integer codDirector, String nomDirector) {
        this.codDirector = codDirector;
        this.nomDirector = nomDirector;
    }

    public Integer getCodDirector() {
        return codDirector;
    }

    public void setCodDirector(Integer codDirector) {
        this.codDirector = codDirector;
    }

    public String getNomDirector() {
        return nomDirector;
    }

    public void setNomDirector(String nomDirector) {
        this.nomDirector = nomDirector;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Peliculas getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Peliculas peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDirector != null ? codDirector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Directores)) {
            return false;
        }
        Directores other = (Directores) object;
        if ((this.codDirector == null && other.codDirector != null) || (this.codDirector != null && !this.codDirector.equals(other.codDirector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Directores[ codDirector=" + codDirector + " ]";
    }
    
}
