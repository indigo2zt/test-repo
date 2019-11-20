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
import pl.lodz.p.spjava.endpoints.GrupaEndpoint;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.endpoints.PacjentEndpoint;
import pl.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.entity.Przychodnia;
import pl.lodz.p.spjava.exception.DbException;
import pl.lodz.p.spjava.web.utils.EmailUtils;

/**
 * @author java
 */
@ViewScoped
@Named("PacjentBean")

public class PacjentBean implements Serializable {

    @Inject
    private PacjentEndpoint pacjentEndpoint;//tego nie powinno byc

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;//powinienn byc endpoint

    @Inject
    private GrupaEndpoint grupaEndpoint;

    @Inject
    private KontoEndpoint kontoEndpoint;

    private Pacjent pacjent = new Pacjent();
    private List<Pacjent> pacjenci = new ArrayList<>();
    private List<Przychodnia> przychodnie = new ArrayList<>();
    private Integer selectedPrzychodnia;
    private Pacjent selectPacjent;
    private Konto konto = new Konto();

    @Inject
    private EmailUtils emailUtils ;

    @PostConstruct
    public void init() {
        pacjenci = pacjentEndpoint.findAll();
        przychodnie.addAll(przychodniaEndpoint.findAll());//uzyc endpointa
        selectedPrzychodnia = 0;

    }

    public List<Przychodnia> getPrzychodnie() {
        return przychodnie;
    }

    public void setPrzychodnie(List<Przychodnia> przychodnie) {
        this.przychodnie = przychodnie;
    }

    public Integer getSelectedPrzychodnia() {
        return selectedPrzychodnia;
    }

    public void setSelectedPrzychodnia(Integer selectedPrzychodnia) {
        this.selectedPrzychodnia = selectedPrzychodnia;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public Pacjent getPacjentById(int id) {
        return pacjentEndpoint.findById(id);
    }

    public List<Pacjent> getPacjenci() {
        return pacjentEndpoint.findAll();
    }

    public String getFormattedImieNazwisko(Pacjent pacjent){
        return String.format("%s %s", pacjent.getKonto().getImie(), pacjent.getKonto().getNazwisko());
    }


    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }


    public Pacjent getSelectPacjent() {
        return selectPacjent;
    }

    public void setSelectPacjent(Pacjent selectPacjent) {
        this.selectPacjent = selectPacjent;
    }

    public String dodaj() {
        //wyszukac przychodnie o id selectedPrzychodnia
        Przychodnia przychodnia = przychodniaEndpoint.findById(selectedPrzychodnia);
        pacjent.setPrzychodnia(przychodnia);
        pacjent.setKonto(konto);
        konto.setPotwierdzone(true);
        konto.setAktywne(true);
        kontoEndpoint.utworzKonto(konto);
        pacjent.setImie(konto.getImie());
        pacjent.setNazwisko(konto.getNazwisko());
        pacjentEndpoint.create(pacjent);
        Grupa grupa = new Grupa();
        grupa.setLogin(konto);
        grupa.setNazwagrupy("Pacjent");
        grupaEndpoint.dodajUzytkownika(grupa);

        return "Lista pacjentow";
    }

    public String edytuj() {
        if (selectPacjent != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectPacjent", selectPacjent);
            return "Edytuj pacjenta";
        }
        return "";
    }

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s, ""));

    }

    public List<Pacjent> usun() {
        this.pacjentEndpoint.remove(selectPacjent);
        return pacjenci;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }
}

