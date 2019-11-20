/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.ejb.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Pacjent;

import java.util.List;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
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

    public List<Pacjent> getByPrzychodnia(int przychodnia){
        return getEntityManager().createNamedQuery("Pacjent.findByPrzychodnia", Pacjent.class).setParameter("przychodniaId", przychodnia).getResultList();
    }

    public Pacjent findByLogin(String login){
        return getEntityManager().createNamedQuery("Pacjent.findByLogin", Pacjent.class).setParameter("login", login).getSingleResult();
    }

    public Pacjent findByKonto(Konto konto){
        return getEntityManager().createNamedQuery("Pacjent.findByKonto", Pacjent.class).setParameter("konto", konto).getSingleResult();
    }
    
}
