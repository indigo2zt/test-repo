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
import pl.lodz.p.spjava.ejb.facade.PrzychodniaFacade;
import pl.lodz.p.spjava.entity.Przychodnia;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PrzychodniaEndpoint {

    @Inject
    private PrzychodniaFacade przychodniaFacade;

    public void edit(Przychodnia przychodnia) {
        przychodniaFacade.edit(przychodnia);
    }

    public void create(Przychodnia przychodnia) {
        przychodniaFacade.create(przychodnia);
    }

    public List<Przychodnia> findAll() {
        return przychodniaFacade.findAll();
    }

    public void remove(Przychodnia przychodnia) {
        przychodniaFacade.remove(przychodnia);
    }
}
