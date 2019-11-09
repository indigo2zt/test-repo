package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.ejb.facade.PrzychodniaFacade;
import pl.lodz.p.spjava.entity.Przychodnia;

/**
 *
 * @author java
 */
@ViewScoped
@Named("PrzychodniaUsunBean")
public class PrzychodniaUsunBean implements Serializable {

    private Przychodnia przychodnia;

    @Inject
    private PrzychodniaFacade facade;

    public Przychodnia getPrzychodnia() {
        return przychodnia;
    }

    public void setPrzychodnia(Przychodnia przychodnia) {
        this.przychodnia = przychodnia;
    }

    @PostConstruct
    public void reset() {

    }

    public String usunPrzychodnie() {
        facade.remove(przychodnia);
        return "";
    }

}
