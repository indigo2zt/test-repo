/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.web.konto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.web.utils.KontoUtils;

/**
 *
 * @author java
 */
@Named("edytujKontoPageBean")
@RequestScoped
public class EdytujKontoPageBean {
    
    public EdytujKontoPageBean() {
    }
    
    @Inject
    private KontoSession kontoSession;

    @PostConstruct
    private void init() {
        konto = kontoSession.getKontoEdytuj();
    }

    private Konto konto =  new Konto();

    public Konto getKonto() {
        return konto;
    }
    
    public boolean isPacjent() {
        return KontoUtils.isPacjent(konto);
    }

    public boolean isPracownik() {
        return KontoUtils.isPracownik(konto);
    }

    public boolean isAdministrator() {
        return KontoUtils.isAdministrator(konto);
    }
    
    public String zapiszKonto() throws AppBaseException {
        return kontoSession.zapiszKontoPoEdycji(konto);
    }

}
