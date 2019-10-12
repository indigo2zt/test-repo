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
import pl.lodz.p.spjava.ejb.facade.PacjentFacade;
import pl.lodz.p.spjava.ejb.facade.PrzychodniaFacade;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.entity.Przychodnia;

/**
 *
 * @author java
 */
@ViewScoped
@Named("PacjentBean")

public class PacjentBean implements Serializable {

    private Pacjent pacjent = new Pacjent();
   
    @Inject
    private PacjentFacade pacjentFacade;//tego nie powinno byc

    @Inject
    private PrzychodniaFacade przychodniaFacade;//powinienn byc endpoint
//    private PrzychodniaEndpoint przychodniaEndpoint;

    private List<Pacjent> pacjenci = new ArrayList<>();

    private List<Przychodnia> przychodnie = new ArrayList<>();

    public List<Przychodnia> getPrzychodnie() {
        return przychodnie;
    }

    public void setPrzychodnie(List<Przychodnia> przychodnie) {
        this.przychodnie = przychodnie;
    }

    private Integer selectedPrzychodnia;

    public Integer getSelectedPrzychodnia() {
        return selectedPrzychodnia;
    }

    public void setSelectedPrzychodnia(Integer selectedPrzychodnia) {
        this.selectedPrzychodnia = selectedPrzychodnia;
    }

    public PacjentBean() {
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }

    public String dodaj() {
        //wyszukac przychodnie o id selectedPrzychodnia
        Przychodnia przychodnia = przychodniaFacade.find(selectedPrzychodnia);
        pacjent.setPrzychodnia(przychodnia);
        pacjentFacade.create(pacjent);
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
        pacjenci = pacjentFacade.findAll();
        przychodnie.addAll(przychodniaFacade.findAll());//uzyc endpointa
        selectedPrzychodnia = 0;

    }

    public List<Pacjent> getPacjenci() {
        return pacjenci;
    }

    private Pacjent selectPacjent;

    public String edytuj() {
        if (selectPacjent != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectPacjent", selectPacjent);
            return "EDYTUJ";
        }

        return "";

    }

    //public String usun() {
    public List<Pacjent> usun() {
        this.pacjentFacade.remove(selectPacjent);
        return pacjenci;

    }

    public Pacjent getSelectPacjent() {
        return selectPacjent;
    }

    public void setSelectPacjent(Pacjent selectPacjent) {
        this.selectPacjent = selectPacjent;
    }

}

