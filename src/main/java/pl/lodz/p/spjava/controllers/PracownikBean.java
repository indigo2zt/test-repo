package pl.lodz.p.spjava.controllers;

import pl.lodz.p.spjava.endpoints.GrupaEndpoint;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.endpoints.LekarzEndpoint;
import pl.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Lekarz;
import pl.lodz.p.spjava.entity.Przychodnia;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@ViewScoped
@Named("PracownikBean")
public class PracownikBean implements Serializable {


    private List<Konto> konta;
    private Grupa wybranyUzytkownik;
    private List<Grupa> uzytkownicy;
    private Lekarz lekarz = new Lekarz();
    private String specjalizacja;
    private int wybranaPrzychodnia;

    @Inject
    private KontoEndpoint kontoEndpoint;

    @Inject
    private GrupaEndpoint grupaEndpoint;

    @Inject
    PrzychodniaEndpoint przychodniaEndpoint;

    @Inject
    LekarzEndpoint lekarzEndpoint;

    @PostConstruct
    private void init() {
        konta = kontoEndpoint.pobierzWszystkieKonta();
        wybranyUzytkownik = (Grupa) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectGrupa");
        uzytkownicy = grupaEndpoint.znajdzWszystkichPacjentow();
    }

    public String dodajLekarza(){
        if (wybranyUzytkownik != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectGrupa", wybranyUzytkownik);
            return "Wprowadzenie danych lekarza";
        }

        return "";

    }

    public String dodaj(){
        Grupa grupa = new Grupa();
        Lekarz lekarz = new Lekarz();
        lekarz.setPrzychodnia(przychodniaEndpoint.findById(wybranaPrzychodnia));
        //lekarz.setKonto(wybranyUzytkownik.getKonto());
        lekarz.setSpecjalizacja(specjalizacja);
        grupa.setLogin(wybranyUzytkownik.getLogin());
        grupa.setNazwagrupy("Lekarz");
        grupaEndpoint.usunGrupeKonta(wybranyUzytkownik.getLogin());
        grupaEndpoint.dodajUzytkownika(grupa);
        lekarzEndpoint.create(lekarz);
        return "";
    }

    public List<Grupa> getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(List<Grupa> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }

    public Grupa getWybranyUzytkownik() {
        return wybranyUzytkownik;
    }

    public void setWybranyUzytkownik(Grupa wybranyUzytkownik) {
        this.wybranyUzytkownik = wybranyUzytkownik;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public int getWybranaPrzychodnia() {
        return wybranaPrzychodnia;
    }

    public void setWybranaPrzychodnia(int wybranaPrzychodnia) {
        this.wybranaPrzychodnia = wybranaPrzychodnia;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }
}
