/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import p.lodz.p.spjava.endpoints.LekarzEndpoint;
import p.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.ejb.facade.LekarzFacade;
import pl.lodz.p.spjava.entity.Lekarz;
import pl.lodz.p.spjava.entity.Przychodnia;

/**
 *
 * @author java
 */
@ViewScoped
@Named("LekarzBean")

public class LekarzBean implements Serializable {

    private Lekarz lekarz = new Lekarz();
    @Inject
    private LekarzFacade lekarzFacade;
    @Inject
    private LekarzEndpoint lekarzEndpoint;

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;

    private List<Lekarz> lekarze = new ArrayList<>();

    private List<Przychodnia> przychodnie = new ArrayList<>();

    public List<Przychodnia> getPrzychodnie() {
        return przychodnie;
    }

    public void setPrzychodnie(List<Przychodnia> przychodnie) {
        this.przychodnie = przychodnie;
    }

    private Przychodnia selectedPrzychodnia;

    public Przychodnia getSelectedPrzychodnia() {
        return selectedPrzychodnia;
    }

    public void setSelectedPrzychodnia(Przychodnia selectedPrzychodnia) {
        this.selectedPrzychodnia = selectedPrzychodnia;
    }

    public LekarzBean() {
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public String dodaj() {

        lekarzFacade.create(lekarz);
        return "Dodaj";
    }
//    public String dodaj() {
//        EntityManager em = (EntityManager) DBManager.getManager().createEntityManagerFactory();
//                em.getTransaction().begin();
//                lekarz.setId(null);
//                em.persist(lekarz);
//                em.getTransaction().commit();
//                em.close();
//                

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s, ""));

    }

    @PostConstruct
    public void init() {
        lekarze = lekarzEndpoint.findAll();
        przychodnie.addAll(przychodniaEndpoint.findAll());
        selectedPrzychodnia = przychodnie.get(0);

    }

    public List<Lekarz> getLekarze() {
        return lekarze;
    }

    private Lekarz selectLekarz;

    public String edytuj() {
        if (selectLekarz != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectLekarz", selectLekarz);
            return "Edytuj";
        }

        return "";

    }

    //public String usun() {
    public List<Lekarz> usun() {
        this.lekarzEndpoint.remove(selectLekarz);
        return lekarze;

    }

    public Lekarz getSelectLekarz() {
        return selectLekarz;
    }

    public void setSelectLekarz(Lekarz selectLekarz) {
        this.selectLekarz = selectLekarz;
    }

}
