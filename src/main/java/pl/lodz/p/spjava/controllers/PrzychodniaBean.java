package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import pl.lodz.p.spjava.entity.Przychodnia;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.lodz.p.spjava.endpoints.PrzychodniaEndpoint;

/**
 * @author java
 */
@ViewScoped
@Named("PrzychodniaBean")
public class PrzychodniaBean implements Serializable {

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;

    private List<Przychodnia> przychodnie = new ArrayList<>();
    private Przychodnia selectPrzychodnia;
    private Przychodnia przychodnia = new Przychodnia();

    @PostConstruct
    public void init() {
        przychodnie = przychodniaEndpoint.findAll();
    }

    public List<Przychodnia> getPrzychodnie() {
        return przychodnie;
    }

    public void setPrzychodnie(List<Przychodnia> przychodnie) {
        this.przychodnie = przychodnie;
    }

    public Przychodnia getSelectPrzychodnia() {
        return selectPrzychodnia;
    }

    public void setSelectPrzychodnia(Przychodnia selectPrzychodnia) {
        this.selectPrzychodnia = selectPrzychodnia;
    }

    public Przychodnia getPrzychodnia() {
        return przychodnia;
    }

    public int getPrzychodniaByLekarz(int lekarz) {
        return przychodniaEndpoint.findByLekarz(lekarz);
    }

    public void setPrzychodnia(Przychodnia przychodnia) {
        this.przychodnia = przychodnia;
    }

    public String dodaj() {
        przychodniaEndpoint.create(przychodnia);
        return "ListaPrzychodni";
    }


    public String edytuj() {
        if (selectPrzychodnia != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectPrzychodnia", selectPrzychodnia);
            return "Edytuj";
        }

        return "";

    }

    public void usun() {

        this.przychodniaEndpoint.remove(selectPrzychodnia);
        przychodnie = przychodniaEndpoint.findAll();


    }
}
