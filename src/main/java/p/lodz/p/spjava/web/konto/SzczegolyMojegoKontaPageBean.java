/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.web.konto;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.web.utils.KontoUtils;

/**
 *
 * @author java
 */
// */
@Named("szczegolyMojegoKontaPageBean")
@RequestScoped
public class SzczegolyMojegoKontaPageBean {

    public SzczegolyMojegoKontaPageBean() {
    }

    @Inject
    private KontoSession kontoSession;

    @PostConstruct
    private void init() {
        konto = kontoSession.pobierzMojeKonto();
    }

    private Konto konto = new Konto();

    public Konto getKonto() {
        return konto;
    }

    public boolean isKlient() {
        return KontoUtils.isPacjent(konto);
    }

    public boolean isPracownik() {
        return KontoUtils.isPracownik(konto);
    }

    public boolean isAdministrator() {
        return KontoUtils.isAdministrator(konto);
    }

}
