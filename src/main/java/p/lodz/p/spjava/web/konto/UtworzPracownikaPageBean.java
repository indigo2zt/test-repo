/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.web.konto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.entity.Pracownik;
import pl.lodz.p.spjava.web.utils.ContextUtils;

/**
 *
 * @author java
 */
@Named("utworzPracownikaPageBean")
@RequestScoped
public class UtworzPracownikaPageBean {

    public UtworzPracownikaPageBean() {
    }

    @Inject
    private KontoSession kontoSession;

    private Pracownik konto = new Pracownik();

    public Pracownik getKonto() {
        return konto;
    }

    private String hasloPowtorz = "";

    public String getHasloPowtorz() {
        return hasloPowtorz;
    }

    public void setHasloPowtorz(String hasloPowtorz) {
        this.hasloPowtorz = hasloPowtorz;
    }

    public String utworz() {
        if (!(hasloPowtorz.equals(konto.getHaslo()))) {
            ContextUtils.emitInternationalizedMessage("utworzPracownikaForm:passwordRepeat", "passwords.not.matching");
            return null;
        }

        return kontoSession.utworzPracownika(konto);
    }
}
