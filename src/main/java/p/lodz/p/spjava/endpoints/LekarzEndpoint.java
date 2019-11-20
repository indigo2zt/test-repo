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
import pl.lodz.p.spjava.ejb.facade.LekarzFacade;
import pl.lodz.p.spjava.entity.Lekarz;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class LekarzEndpoint {

    @Inject
    private LekarzFacade lekarzFacade;
    
    public void edit(Lekarz lekarz){
        lekarzFacade.edit(lekarz);
    }

    public void create(Lekarz lekarz) {
        lekarzFacade.create(lekarz);
    }

    public List<Lekarz> findAll() {
        return lekarzFacade.findAll();
    }

    public void remove(Lekarz lekarz) {
        lekarzFacade.remove(lekarz);
    }
}
