/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.ejb.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import pl.lodz.p.spjava.entity.Przychodnia;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PrzychodniaFacade extends AbstractFacade<Przychodnia> {

    @PersistenceContext(unitName = "Przychodnielekarskie")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrzychodniaFacade() {
        super(Przychodnia.class);
    }

    public int findByLekarz(int lekarz){
        return getEntityManager().createNamedQuery("Przychodnia.findByLekarz", Przychodnia.class).setParameter("lekarzId", lekarz).getSingleResult().getId();
    }
}