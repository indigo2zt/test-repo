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
import pl.lodz.p.spjava.web.utils.ContextUtils;

/**
 *
 * @author java
 */
@Named("zmienHasloKontaPageBean")
@RequestScoped
public class ZmienHasloKontaPageBean {

    public ZmienHasloKontaPageBean() {
    }

    @Inject
    private KontoSession kontoSession;

    private Konto konto;

    public Konto getKonto() {
        return konto;
    }

    @PostConstruct
    private void init() {
        konto = kontoSession.getKontoZmienHaslo();
        konto.setHaslo(new String());
    }

    private String hasloPowtorz = "";

    public String getHasloPowtorz() {
        return hasloPowtorz;
    }

    public void setHasloPowtorz(String hasloPowtorz) {
        this.hasloPowtorz = hasloPowtorz;
    }

    public String zmienHaslo() {
        if (!(hasloPowtorz.equals(konto.getHaslo()))) {
            ContextUtils.emitInternationalizedMessage("zmienHasloKontaForm:passwordRepeat", "passwords.not.matching");
            return null;
        }

        return kontoSession.zmienHasloKonta(konto.getHaslo());
    }

}
