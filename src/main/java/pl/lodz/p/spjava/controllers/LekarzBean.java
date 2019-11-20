
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import pl.lodz.p.spjava.endpoints.*;
import pl.lodz.p.spjava.entity.*;
import pl.lodz.p.spjava.exception.DbException;


/**
 * @author java
 */
@ViewScoped
@Named("LekarzBean")

public class LekarzBean implements Serializable {

    private Lekarz lekarz = new Lekarz();
    private Konto konto = new Konto();
    private List<Lekarz> lekarze = new ArrayList<>();
    private List<Przychodnia> przychodnie = new ArrayList<>();
    private Integer wybranaPrzychodnia;
    private Lekarz selectLekarz;
    private ScheduleModel scheduleModel;
    private String specjalizacja;

    @Inject
    private LekarzEndpoint lekarzEndpoint;//tego nie powinno byc

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;//powinienn byc endpoint

    @Inject
    private KontoEndpoint kontoEndpoint;

    @Inject
    private GrupaEndpoint grupaEndpoint;

    @Inject
    private WizytaEndpoint wizytaEndpoint;

    @PostConstruct
    public void init() {
        scheduleModel = new DefaultScheduleModel();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.getRemoteUser() != null) {
            try {
                lekarz = kontoEndpoint.znajdzLogin(request.getRemoteUser()).getLekarz();
            } catch (DbException.BadExecution badExecution) {
                badExecution.printStackTrace();
            }
        }
        if(lekarz != null){
            List<Wizyta> date = wizytaEndpoint.findAllByLekarz(lekarz.getId());
            date.forEach(date1 -> {
                DefaultScheduleEvent event = new DefaultScheduleEvent(date1.getPacjent1().getImie() + date1.getPacjent1().getNazwisko(), date1.getWizytaPK().getData(), DateUtils.addMinutes(date1.getWizytaPK().getData(), 30));
                scheduleModel.addEvent(event);
            });
        }

        lekarze = lekarzEndpoint.findAll();
        przychodnie.addAll(przychodniaEndpoint.findAll());//uzyc endpointa
        wybranaPrzychodnia = 0;

    }

    public List<Przychodnia> getPrzychodnie() {
        return przychodnie;
    }

    public void setPrzychodnie(List<Przychodnia> przychodnie) {
        this.przychodnie = przychodnie;
    }

    public Integer getWybranaPrzychodnia() {
        return wybranaPrzychodnia;
    }

    public void setWybranaPrzychodnia(Integer wybranaPrzychodnia) {
        this.wybranaPrzychodnia = wybranaPrzychodnia;
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public Lekarz getLekarzById(int id) {
        return lekarzEndpoint.findById(id);
    }

    public String getFormattedImieNazwisko(Lekarz lekarz) {
        return String.format("%s %s", lekarz.getImie(), lekarz.getNazwisko());
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public String dodaj() {
        //wyszukac przychodnie o id selectedPrzychodnia
        Przychodnia przychodnia = przychodniaEndpoint.findById(wybranaPrzychodnia);
        Lekarz nowyLekarz = new Lekarz();
        konto.setPotwierdzone(true);
        konto.setAktywne(true);
        kontoEndpoint.utworzKonto(konto);
        nowyLekarz.setPrzychodnia(przychodnia);
        nowyLekarz.setKonto(konto);
        nowyLekarz.setImie(konto.getImie());
        nowyLekarz.setNazwisko(konto.getNazwisko());
        nowyLekarz.setSpecjalizacja(specjalizacja);
        lekarzEndpoint.create(nowyLekarz);
        Grupa grupa = new Grupa();
        grupa.setLogin(konto);
        grupa.setNazwagrupy("Lekarz");
        grupaEndpoint.dodajUzytkownika(grupa);
        return "Pokaz lekarzy";
    }

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s, ""));

    }

    public List<Lekarz> getLekarze() {
        lekarze = lekarzEndpoint.findAll();
        return lekarze;
    }

    public String edytuj() {
        if (selectLekarz != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectLekarz", selectLekarz);
            return "Edytuj lekarza";
        }

        return "";

    }

    public void usun() throws DbException.BadExecution {
        String login = selectLekarz.getKonto().getLogin();
        grupaEndpoint.usunGrupeKonta(selectLekarz.getKonto());
        lekarzEndpoint.remove(selectLekarz);
        kontoEndpoint.usunKonto(kontoEndpoint.znajdzLogin(login));

        lekarze = lekarzEndpoint.findAll();

    }

    public Lekarz getSelectLekarz() {
        return selectLekarz;
    }

    public void setSelectLekarz(Lekarz selectLekarz) {
        this.selectLekarz = selectLekarz;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public void setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
    }

    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }
}
