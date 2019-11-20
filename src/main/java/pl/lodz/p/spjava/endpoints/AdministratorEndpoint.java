/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.endpoints;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import pl.lodz.p.spjava.ejb.facade.AdministratorFacade;
import pl.lodz.p.spjava.entity.Administrator;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AdministratorEndpoint {

    @Inject
    private AdministratorFacade administratorFacade;
    
    public void edit(Administrator administrator){
        administratorFacade.edit(administrator);
    }

    public void create(Administrator administrator) {
        administratorFacade.create(administrator);
    }

    public List<Administrator> findAll() {
        return administratorFacade.findAll();
    }

    public void remove(Administrator administrator) {
        administratorFacade.remove(administrator);
    }
}

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

