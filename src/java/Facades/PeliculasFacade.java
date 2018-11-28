/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import entities.Directores;
import entities.Peliculas;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author 2h
 */
@Stateless
public class PeliculasFacade extends AbstractFacade<Peliculas> {

    @PersistenceContext(unitName = "CinematicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeliculasFacade() {
        super(Peliculas.class);
    }

    public List<Peliculas> peliculas_director(Directores director) {
        EntityManager em = getEntityManager();
        Query q;
        if(director != null){
            q = em.createNamedQuery("Peliculas.findByDirector").setParameter("elDirector", director);
            return q.getResultList();
        }
        
        return null;
    }
    
}
