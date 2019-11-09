/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.web.konto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.web.utils.ContextUtils;

/**
 *
 * @author java
 */
@Stateless
public class ZmienMojeHasloPageBean {

    public ZmienMojeHasloPageBean() {
    }

    @Inject
    private KontoSession kontoSession;

    private Konto konto = new Konto();

    public Konto getKonto() {
        return konto;
    }

    private String hasloPowtorz = "";

    public String getHasloPowtorz() {
        return hasloPowtorz;
    }

    public void setHasloPowtorz(String hasloPowtorz) {
        this.hasloPowtorz = hasloPowtorz;
    }

    private String stareHaslo = "";

    public String getStareHaslo() {
        return stareHaslo;
    }

    public void setStareHaslo(String stareHaslo) {
        this.stareHaslo = stareHaslo;
    }

    public String zmienHaslo() {
        if (!(hasloPowtorz.equals(konto.getHaslo()))) {
            ContextUtils.emitInternationalizedMessage("zmienMojeHasloForm:passwordRepeat", "passwords.not.matching");
            return null;
        }

        return kontoSession.zmienMojeHaslo(stareHaslo, konto.getHaslo());
    }

}
