package pl.lodz.p.spjava.controllers;

import pl.lodz.p.spjava.ejb.intereceptor.LoggingInterceptor;
import pl.lodz.p.spjava.endpoints.GrupaEndpoint;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.entity.Przychodnia;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.exception.DbException;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named("AdministratorBean")
@Interceptors(LoggingInterceptor.class)
public class AdministratorBean implements Serializable {

    private List<Konto> konta;
    private Konto selectedKonto;
    private List<Grupa> uprawnienia;
    private Grupa selectedGrupa;
    private List<String> grupy = new ArrayList<>();
    private String login;
    private String nazwagrupy;

    @Inject
    private KontoEndpoint kontoEndpoint;

    @Inject
    private GrupaEndpoint grupaEndpoint;

    @PostConstruct
    public void init() {
        konta = kontoEndpoint.pobierzWszystkieKonta();
        uprawnienia = grupaEndpoint.znajdzWszystkie();
        selectedKonto = (Konto) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectKonto");
        selectedGrupa = (Grupa) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectGrupa");
        if(selectedGrupa == null){
            selectedGrupa = new Grupa();
        }
        grupy.add("Pracownik");
        grupy.add("Lekarz");
        grupy.add("Pacjent");
        grupy.add("Administrator");
    }

    public String znajdzUprawnienia(Konto konto) throws DbException.BadExecution {
        return grupaEndpoint.znajdzPoLoginie(konto);
    }

    public String dodajGrupe() throws DbException.BadExecution {
        Konto konto = kontoEndpoint.znajdzLogin(login);
        selectedGrupa.setLogin(konto);
        selectedGrupa.setNazwagrupy(nazwagrupy);
        grupaEndpoint.dodajUzytkownika(selectedGrupa);
        return "Lista uprawnien";
    }

    public String edytujKonto() {
        if (selectedKonto != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectKonto", selectedKonto);
            return "Edytuj konto";
        }

        return "";

    }

    public String edytujGrupe() {
        if (selectedGrupa != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectGrupa", selectedGrupa);
            return "Edytuj grupe";
        }

        return "";

    }

    public String zapiszGrupe(){
        grupaEndpoint.zapiszZmiany(selectedGrupa);
        return "Lista uprawnien";
    }

    public String zapiszKonto() throws AppBaseException {
        kontoEndpoint.zapiszKontoPoEdycji(selectedKonto);
        return "Zarzadzanie kontami";
    }

    public List<Grupa> usunGrupe() {
        this.grupaEndpoint.usunGrupe(selectedGrupa);
        uprawnienia = grupaEndpoint.znajdzWszystkie();
        return uprawnienia;
    }

    public List<Konto> usunKonto() throws DbException.BadExecution {
        this.kontoEndpoint.usunKonto(selectedKonto);
        konta = kontoEndpoint.pobierzWszystkieKonta();
        return konta;
    }



    public String dodajUprawnienia() {

        return "Dodaj";
    }

    public List<Konto> getKonta() {
        return konta;
    }

    public void setKonta(List<Konto> konta) {
        this.konta = konta;
    }

    public Konto getSelectedKonto() {
        return selectedKonto;
    }

    public void setSelectedKonto(Konto selectedKonto) {
        this.selectedKonto = selectedKonto;
    }

    public List<Grupa> getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(List<Grupa> uprawnienia) {
        this.uprawnienia = uprawnienia;
    }

    public void setSelectedGrupa(Grupa selectedGrupa) {
        this.selectedGrupa = selectedGrupa;
    }

    public Grupa getSelectedGrupa() {
        return selectedGrupa;
    }

    public List<String> getGrupy() {
        return grupy;
    }

    public void setGrupy(List<String> grupy) {
        this.grupy = grupy;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNazwagrupy() {
        return nazwagrupy;
    }

    public void setNazwagrupy(String nazwagrupy) {
        this.nazwagrupy = nazwagrupy;
    }
}
