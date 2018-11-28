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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "peliculas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peliculas.findAll", query = "SELECT p FROM Peliculas p"),
    @NamedQuery(name = "Peliculas.findByCodPelicula", query = "SELECT p FROM Peliculas p WHERE p.codPelicula = :codPelicula"),
    @NamedQuery(name = "Peliculas.findByTitulo", query = "SELECT p FROM Peliculas p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Peliculas.findByAno", query = "SELECT p FROM Peliculas p WHERE p.ano = :ano"),
    @NamedQuery(name = "Peliculas.findByTrailer", query = "SELECT p FROM Peliculas p WHERE p.trailer = :trailer"),
    @NamedQuery(name = "Peliculas.findByDirector", query = "SELECT p FROM Peliculas p WHERE p.codDirector = :elDirector")})
public class Peliculas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codPelicula")
    private Integer codPelicula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano")
    private int ano;
    @Size(max = 50)
    @Column(name = "trailer")
    private String trailer;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "codDirector", referencedColumnName = "codDirector")
    @OneToOne(optional = false)
    private Directores codDirector;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peliculas")
    private List<Peliculasactores> peliculasactoresList;

    public Peliculas() {
    }

    public Peliculas(Integer codPelicula) {
        this.codPelicula = codPelicula;
    }

    public Peliculas(Integer codPelicula, String titulo, int ano, String comentario) {
        this.codPelicula = codPelicula;
        this.titulo = titulo;
        this.ano = ano;
        this.comentario = comentario;
    }

    public Integer getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(Integer codPelicula) {
        this.codPelicula = codPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Directores getCodDirector() {
        return codDirector;
    }

    public void setCodDirector(Directores codDirector) {
        this.codDirector = codDirector;
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
        hash += (codPelicula != null ? codPelicula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peliculas)) {
            return false;
        }
        Peliculas other = (Peliculas) object;
        if ((this.codPelicula == null && other.codPelicula != null) || (this.codPelicula != null && !this.codPelicula.equals(other.codPelicula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Peliculas[ codPelicula=" + codPelicula + " ]";
    }
    
}
