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

import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Lekarz;
import pl.lodz.p.spjava.entity.Wizyta;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class WizytaFacade extends AbstractFacade<Wizyta> {

    @PersistenceContext(unitName = "Przychodnielekarskie")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WizytaFacade() {
        super(Wizyta.class);
    }

    public List<Wizyta> findAllByRange(Date data)
    {
        Date poczatek = wygenerowacDate(9, data);
        Date koniec = wygenerowacDate(18, data);
        return getEntityManager().createNamedQuery("Wizyta.findAllByRange", Wizyta.class).setParameter("pierwszaData", poczatek).setParameter("drugaData", koniec).getResultList();
    }

    public List<Wizyta> findAllByLekarz(int lekarz)
    {
        return getEntityManager().createNamedQuery("Wizyta.findByLekarz", Wizyta.class).setParameter("lekarz", lekarz).getResultList();
    }

    public List<Wizyta> findAllByPacjent(int pacjent)
    {
        return getEntityManager().createNamedQuery("Wizyta.findByPacjent", Wizyta.class).setParameter("pacjent", pacjent).getResultList();
    }

    private Date wygenerowacDate(int godzina, Date data){
        Calendar now = Calendar.getInstance();
        now.setTime(data);
        now.set(Calendar.HOUR_OF_DAY, godzina);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

}
