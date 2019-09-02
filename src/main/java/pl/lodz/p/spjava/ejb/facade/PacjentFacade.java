/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.ejb.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.spjava.entity.Pacjent;

/**
 *
 * @author java
 */
@Stateless
public class PacjentFacade extends AbstractFacade<Pacjent> {

    @PersistenceContext(unitName = "Przychodnielekarskie")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacjentFacade() {
        super(Pacjent.class);
    }
    
}