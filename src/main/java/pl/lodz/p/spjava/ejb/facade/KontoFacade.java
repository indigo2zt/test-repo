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
import pl.lodz.p.spjava.entity.Konto;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class KontoFacade extends AbstractFacade<Konto> {

    @PersistenceContext(unitName = "Przychodnielekarskie")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KontoFacade() {
        super(Konto.class);
    }

    public Konto znajdzLogin(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Konto> dopasujKonta(String loginWzor, String imieWzor, String nazwiskoWzor, String emailWzor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
