package pl.lodz.p.spjava.controllers;

import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.endpoints.LekarzEndpoint;
import pl.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.entity.Lekarz;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.exception.DbException;
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
@Named("LekarzEditBean")
public class LekarzEditBean implements Serializable {

    private Lekarz lekarz;
    private int selectedPrzychodnia;


    @Inject
    private LekarzEndpoint lekarzEndpoint;
    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;

    @Inject
    private KontoEndpoint kontoEndpoint;

    @PostConstruct
    public void init(){
        this.lekarz = (Lekarz) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectLekarz");
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public String saveLekarz() throws AppBaseException, UnsupportedEncodingException, NoSuchAlgorithmException {
            lekarz.setPrzychodnia(przychodniaEndpoint.findById(selectedPrzychodnia));
            lekarz.getKonto().setHaslo(KontoUtils.kodojWSha256(lekarz.getKonto().getHaslo()));
            kontoEndpoint.zapiszKontoPoEdycji(lekarz.getKonto());
            lekarzEndpoint.edit(lekarz);
            //kontoEndpoint.zapiszKontoPoEdycji(lekarz.getKonto());

        return "Pokaz lekarzy";
    }

    public int getSelectedPrzychodnia() {
        return selectedPrzychodnia;
    }

    public void setSelectedPrzychodnia(int selectedPrzychodnia) {
        this.selectedPrzychodnia = selectedPrzychodnia;
    }
}
