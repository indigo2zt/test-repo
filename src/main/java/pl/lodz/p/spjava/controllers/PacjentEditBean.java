package pl.lodz.p.spjava.controllers;

import pl.lodz.p.spjava.endpoints.*;
import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.web.utils.KontoUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


@ViewScoped
@Named("PacjentEditBean")
public class PacjentEditBean implements Serializable {

    private Pacjent pacjent;
    private int selectedPrzychodnia;


    @Inject
    private PacjentEndpoint pacjentEndpoint;

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;

    @Inject
    private GrupaEndpoint grupaEndpoint;

    @Inject
    private KontoEndpoint kontoEndpoint;


    @PostConstruct
    public void init(){
        this.pacjent = (Pacjent) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectPacjent");
    }

    public String savePacjent() throws AppBaseException, UnsupportedEncodingException, NoSuchAlgorithmException {
        pacjent.getKonto().setHaslo(KontoUtils.kodojWSha256(pacjent.getKonto().getHaslo()));
        kontoEndpoint.zapiszKontoPoEdycji(pacjent.getKonto());
        pacjent.setPrzychodnia(przychodniaEndpoint.findById(selectedPrzychodnia));
        pacjent.setImie(pacjent.getKonto().getImie());
        pacjent.setNazwisko(pacjent.getKonto().getNazwisko());
        pacjentEndpoint.edit(this.pacjent);
        return "Lista pacjentow";
    }

    public int getSelectedPrzychodnia() {
        return selectedPrzychodnia;
    }

    public void setSelectedPrzychodnia(int selectedPrzychodnia) {
        this.selectedPrzychodnia = selectedPrzychodnia;
    }

    public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }
}
