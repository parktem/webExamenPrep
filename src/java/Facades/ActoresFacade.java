/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facades;

import entities.Actores;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2h
 */
@Stateless
public class ActoresFacade extends AbstractFacade<Actores> {

    @PersistenceContext(unitName = "CinematicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActoresFacade() {
        super(Actores.class);
    }
    
}
