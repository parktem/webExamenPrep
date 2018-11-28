/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2h
 */
@Entity
@Table(name = "peliculasactores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peliculasactores.findAll", query = "SELECT p FROM Peliculasactores p"),
    @NamedQuery(name = "Peliculasactores.findByCodPelicula", query = "SELECT p FROM Peliculasactores p WHERE p.peliculasactoresPK.codPelicula = :codPelicula"),
    @NamedQuery(name = "Peliculasactores.findByCodActor", query = "SELECT p FROM Peliculasactores p WHERE p.peliculasactoresPK.codActor = :codActor"),
    @NamedQuery(name = "Peliculasactores.findByCache", query = "SELECT p FROM Peliculasactores p WHERE p.cache = :cache"),
    @NamedQuery(name = "Peliculasactores.findByNomPersonaje", query = "SELECT p FROM Peliculasactores p WHERE p.nomPersonaje = :nomPersonaje"),
    @NamedQuery(name = "Peliculasactores.findByFoto", query = "SELECT p FROM Peliculasactores p WHERE p.foto = :foto")})
public class Peliculasactores implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeliculasactoresPK peliculasactoresPK;
    @Column(name = "cache")
    private Integer cache;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomPersonaje")
    private String nomPersonaje;
    @Size(max = 50)
    @Column(name = "foto")
    private String foto;
    @JoinColumn(name = "codPelicula", referencedColumnName = "codPelicula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Peliculas peliculas;
    @JoinColumn(name = "codActor", referencedColumnName = "codAutor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Actores actores;

    public Peliculasactores() {
    }

    public Peliculasactores(PeliculasactoresPK peliculasactoresPK) {
        this.peliculasactoresPK = peliculasactoresPK;
    }

    public Peliculasactores(PeliculasactoresPK peliculasactoresPK, String nomPersonaje) {
        this.peliculasactoresPK = peliculasactoresPK;
        this.nomPersonaje = nomPersonaje;
    }

    public Peliculasactores(int codPelicula, int codActor) {
        this.peliculasactoresPK = new PeliculasactoresPK(codPelicula, codActor);
    }

    public PeliculasactoresPK getPeliculasactoresPK() {
        return peliculasactoresPK;
    }

    public void setPeliculasactoresPK(PeliculasactoresPK peliculasactoresPK) {
        this.peliculasactoresPK = peliculasactoresPK;
    }

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    public String getNomPersonaje() {
        return nomPersonaje;
    }

    public void setNomPersonaje(String nomPersonaje) {
        this.nomPersonaje = nomPersonaje;
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

    public Actores getActores() {
        return actores;
    }

    public void setActores(Actores actores) {
        this.actores = actores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peliculasactoresPK != null ? peliculasactoresPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peliculasactores)) {
            return false;
        }
        Peliculasactores other = (Peliculasactores) object;
        if ((this.peliculasactoresPK == null && other.peliculasactoresPK != null) || (this.peliculasactoresPK != null && !this.peliculasactoresPK.equals(other.peliculasactoresPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Peliculasactores[ peliculasactoresPK=" + peliculasactoresPK + " ]";
    }
    
}
