/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 2h
 */
@Embeddable
public class PeliculasactoresPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "codPelicula")
    private int codPelicula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codActor")
    private int codActor;

    public PeliculasactoresPK() {
    }

    public PeliculasactoresPK(int codPelicula, int codActor) {
        this.codPelicula = codPelicula;
        this.codActor = codActor;
    }

    public int getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(int codPelicula) {
        this.codPelicula = codPelicula;
    }

    public int getCodActor() {
        return codActor;
    }

    public void setCodActor(int codActor) {
        this.codActor = codActor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codPelicula;
        hash += (int) codActor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeliculasactoresPK)) {
            return false;
        }
        PeliculasactoresPK other = (PeliculasactoresPK) object;
        if (this.codPelicula != other.codPelicula) {
            return false;
        }
        if (this.codActor != other.codActor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PeliculasactoresPK[ codPelicula=" + codPelicula + ", codActor=" + codActor + " ]";
    }
    
}
