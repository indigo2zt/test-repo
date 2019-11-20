/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.ejb.facade;

import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.exception.DbException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GrupaFacade extends AbstractFacade<Grupa> {

    @PersistenceContext(unitName = "Przychodnielekarskie")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupaFacade() {
        super(Grupa.class);
    }

    public List<Grupa> znajdzPoLoginie(Konto konto) throws DbException.BadExecution {
        try{
            return getEntityManager().createNamedQuery("Grupa.findByLogin", Grupa.class).setParameter("login", konto).getResultList();
        }catch (Exception e){
            throw new DbException.BadExecution(String.format("Nie udało się znaleźć listę grup z loginem: %s", konto.getLogin()));
        }

    }

    public List<Grupa> znajdzWszystkichPacjentow(){
        return getEntityManager().createNamedQuery("Grupa.findAllPacjent", Grupa.class).getResultList();
    }

    public void usunByKonto(Konto konto){
        getEntityManager().createNamedQuery("Grupa.usunByKonto", Grupa.class).setParameter("konto", konto).executeUpdate();
    }

    
}
