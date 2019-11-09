/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.endpoints;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.spjava.ejb.facade.WizytaFacade;
import pl.lodz.p.spjava.entity.Wizyta;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class WizytaEndpoint {

    @Inject
    private WizytaFacade wizytaFacade;

    public void edit(Wizyta wizyta) {
        wizytaFacade.edit(wizyta);
    }

    public void create(Wizyta wizyta) {
        wizytaFacade.create(wizyta);
    }

    public List<Wizyta> findAll() {
        return wizytaFacade.findAll();
    }

    public void remove(Wizyta wizyta) {
        wizytaFacade.remove(wizyta);
    }
}

// Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")

