/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p.lodz.p.spjava.web.konto;

/**
 *
 * @author java
 */
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.entity.Konto;

@RequestScoped
@Named("listaKontPageBean")
public class ListaKontPageBean {

    static final String GENERAL_MSG_ID = "listaKontForm:listaKont";

    public ListaKontPageBean() {
    }

    @Inject
    private KontoSession kontoSession;

    @PostConstruct
    private void initModel() {
        konta = kontoSession.dopasujKonta(szukanyLogin, szukaneImie, szukaneNazwisko, szukanyEmail);
        kontaDataModel = new ListDataModel<>(konta);
    }
    private List<Konto> konta;
    private DataModel<Konto> kontaDataModel;

    public DataModel<Konto> getKontaDataModel() {
        return kontaDataModel;
    }

    private String szukanyLogin = "";
    private String szukaneImie = "";
    private String szukaneNazwisko = "";
    private String szukanyEmail = "";

    public String getSzukanyEmail() {
        return szukanyEmail;
    }

    public void setSzukanyEmail(String szukanyEmail) {
        this.szukanyEmail = szukanyEmail;
    }

    public String getSzukaneImie() {
        return szukaneImie;
    }

    public void setSzukaneImie(String szukaneImie) {
        this.szukaneImie = szukaneImie;
    }

    public String getSzukaneNazwisko() {
        return szukaneNazwisko;
    }

    public void setSzukaneNazwisko(String szukaneNazwisko) {
        this.szukaneNazwisko = szukaneNazwisko;
    }

    public String getSzukanyLogin() {
        return szukanyLogin;
    }

    public void setSzukanyLogin(String szukanyLogin) {
        this.szukanyLogin = szukanyLogin;
    }

    public void odswiez() {
        initModel();
    }

    public void wyczysc() {
        szukanyLogin = "";
        szukaneImie = "";
        szukaneNazwisko = "";
        szukanyEmail = "";
    }

    public void aktywujKonto() {
        kontoSession.aktywujKonto(kontaDataModel.getRowData());
        initModel();
    }

    public void deaktywujKonto() {
        kontoSession.deaktywujKonto(kontaDataModel.getRowData());
        initModel();
    }

    public void potwierdzKonto() {
        kontoSession.potwierdzKonto(kontaDataModel.getRowData());
        initModel();
    }

    public String edytujKonto() {
        return kontoSession.pobierzKontoDoEdycji(kontaDataModel.getRowData());
    }

    public String rozpocznijZmianeHasla() {
        return kontoSession.rozpocznijZmianeHasla(kontaDataModel.getRowData());
    }
}
