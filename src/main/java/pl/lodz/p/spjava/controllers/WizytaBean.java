/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.endpoints.PacjentEndpoint;
import pl.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.endpoints.WizytaEndpoint;
import pl.lodz.p.spjava.entity.*;
import pl.lodz.p.spjava.exception.DbException;

/**
 * @author java
 */
@ViewScoped
@Named("WizytaBean")
public class WizytaBean implements Serializable {
    private Wizyta wizyta;
    private List<Pacjent> pacjentList;
    private int lekarz;
    private int pacjent;
    private Date data;
    private String pokoj = "";
    private String wybranaData;
    private List<Date> listaDat = new ArrayList<>();

    @Inject
    private PacjentEndpoint pacjentEndpoint;

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;

    @Inject
    private WizytaEndpoint wizytaEndpoint;

    @Inject
    private KontoEndpoint kontoEndpoint;


    public WizytaBean() {

    }

    @PostConstruct
    public void init() {
    }

    public void generateListaPacjentow() {
        pacjentList = pacjentEndpoint.findAllByPrzychodnia(przychodniaEndpoint.findByLekarz(lekarz));

    }

    public List<Wizyta> usun() {
        wizytaEndpoint.remove(wizyta);
        return getWizytaList();
    }

    public String edytuj() {
        if (wizyta != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectWizyta", wizyta);
            return "Edytuj wizyte";
        }
        return "";
    }

    public String edytujPacjent() {
        if (wizyta != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectWizyta", wizyta);
            return "Edytuj wizte pacjent";
        }
        return "";
    }



    public String addWizyta() throws ParseException {
        wizyta = new Wizyta();
        wizyta.setWizytaPK(new WizytaPK());
        wizyta.getWizytaPK().setLekarz(lekarz);
        wizyta.getWizytaPK().setPacjent(pacjent);
        wizyta.getWizytaPK().setData(DateUtils.addHours(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(wybranaData), 1));
        wizyta.setPokoj(pokoj);
        wizytaEndpoint.create(wizyta);
        return "Pokaz wizyty pracownik";
    }

    public String addWizytaPacjent() throws DbException.BadExecution, ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.getRemoteUser() != null) {
            try {

                pacjent = pacjentEndpoint.findByKonto(kontoEndpoint.znajdzLogin(request.getRemoteUser())).getId();
            } catch (Exception e) {
                throw new DbException.BadExecution("Problem z pobieraniem danych pacjenta z bazy");
            }
        }
        wizyta = new Wizyta();
        wizyta.setPokoj("1");
        wizyta.setWizytaPK(new WizytaPK());
        wizyta.getWizytaPK().setLekarz(lekarz);
        wizyta.getWizytaPK().setPacjent(pacjent);
        wizyta.getWizytaPK().setData(DateUtils.addHours(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(wybranaData), 1));
        //wizyta.getWizytaPK().setData(data);
        wizytaEndpoint.create(wizyta);
        return "Strona glowna";
    }

    public void generateListaDat() {
        listaDat = getListOfDates();
    }

    private List<Wizyta> getListOfWizyta() {
        return wizytaEndpoint.findAllForRange(data);
    }

    private List<Date> getListOfDates() {
        Date dateTime1 = wygenerowacDate(9, data);
        Date dateTime2 = wygenerowacDate(18, data);

        List<Date> allDates = new ArrayList();
        List<Wizyta> wszystkieWizyty = getListOfWizyta();

        while (dateTime1.before(dateTime2)) {
            allDates.add(dateTime1);
            dateTime1 = DateUtils.addMinutes(dateTime1, 30);

        }
        return allDates.stream().filter(date -> inListOfExist(date, wszystkieWizyty)).collect(Collectors.toList());
    }

    private boolean inListOfExist(Date data, List<Wizyta> wizyty) {
        AtomicBoolean isValid = new AtomicBoolean(true);
        wizyty.stream().map(wizyta1 -> wizyta1.getWizytaPK().getData()).forEach(dataZBazy -> {
            if ((dataZBazy.after(data) || data.equals(dataZBazy))) {
                if (dataZBazy.before(DateUtils.addMinutes(data, 30)) || dataZBazy.equals(DateUtils.addMinutes(data, 30))) {
                    isValid.set(false);
                }

            }
        });
        return isValid.get();
    }

    public String konwertowacWFormatGodzinaMinuta(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public String konwertowacDate(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm").format(date);
    }

    private Date wygenerowacDate(int godzina, Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.HOUR_OF_DAY, godzina);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }



    public Wizyta getWizyta() {
        return wizyta;
    }

    public void setWizyta(Wizyta wizyta) {
        this.wizyta = wizyta;
    }

    public List<Pacjent> getPacjentList() {
        return pacjentList;
    }

    public List<Wizyta> getWizytaList() {
        return wizytaEndpoint.findAll();
    }

    public List<Wizyta> getListaMoichWizyt() throws DbException.BadExecution {
        List<Wizyta> listaWizyt = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.getRemoteUser() != null) {
            try {

                pacjent = pacjentEndpoint.findByKonto(kontoEndpoint.znajdzLogin(request.getRemoteUser())).getId();
                listaWizyt = wizytaEndpoint.findAllByPacjent(pacjent);
            } catch (Exception e) {
                throw new DbException.BadExecution("Problem z pobieraniem danych pacjenta z bazy");
            }
        }
        return listaWizyt;
    }

    public int getLekarz() {
        return lekarz;
    }

    public void setLekarz(int lekarz) {
        this.lekarz = lekarz;
    }

    public int getPacjent() {
        return pacjent;
    }

    public void setPacjent(int pacjent) {
        this.pacjent = pacjent;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getPokoj() {
        return pokoj;
    }

    public void setPokoj(String pokoj) {
        this.pokoj = pokoj;
    }

    public List<Date> getListaDat() {
        return listaDat;
    }

    public void setListaDat(List<Date> listaDat) {
        this.listaDat = listaDat;
    }

    public String getWybranaData() {
        return wybranaData;
    }

    public void setWybranaData(String wybranaData) {
        this.wybranaData = wybranaData;
    }
}


