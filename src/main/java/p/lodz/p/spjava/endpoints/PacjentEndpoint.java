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
import pl.lodz.p.spjava.ejb.facade.PacjentFacade;
import pl.lodz.p.spjava.entity.Pacjent;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PacjentEndpoint {

    @Inject
    private PacjentFacade pacjentFacade;

    public void edit(Pacjent pacjent) {
        pacjentFacade.edit(pacjent);
    }

    public void create(Pacjent pacjent) {
        pacjentFacade.create(pacjent);
    }

    public List<Pacjent> findAll() {
        return pacjentFacade.findAll();
    }

    public void remove(Pacjent pacjent) {
        pacjentFacade.remove(pacjent);
    }
}

// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
