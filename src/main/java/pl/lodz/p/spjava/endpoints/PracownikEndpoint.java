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
import pl.lodz.p.spjava.ejb.facade.PracownikFacade;
import pl.lodz.p.spjava.entity.Pracownik;

/**
 *
 * @author java
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PracownikEndpoint {
     @Inject
    private PracownikFacade pracownikFacade;
    
    public void edit(Pracownik pracownik){
        pracownikFacade.edit(pracownik);
    }

    public void create(Pracownik pracownik) {
        pracownikFacade.create(pracownik);
    }

    public List<Pracownik> findAll() {
        return pracownikFacade.findAll();
    }

    public void remove(Pracownik pracownik) {
        pracownikFacade.remove(pracownik);
    }
}


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

