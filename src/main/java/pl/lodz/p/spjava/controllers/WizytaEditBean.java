package pl.lodz.p.spjava.controllers;

import pl.lodz.p.spjava.endpoints.LekarzEndpoint;
import pl.lodz.p.spjava.endpoints.PacjentEndpoint;
import pl.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.endpoints.WizytaEndpoint;
import pl.lodz.p.spjava.entity.Lekarz;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.entity.Wizyta;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;

@ViewScoped
@Named("WizytaEditBean")
public class WizytaEditBean implements Serializable {

    private Wizyta wizyta;
    private List<Pacjent> pacjentList;
    private Date data = new Date();
    private String wybranaData;
    private List<Date> listaDat = new ArrayList<>();

    @Inject
    private PacjentEndpoint pacjentEndpoint;

    @Inject
    private PrzychodniaEndpoint przychodniaEndpoint;

    @Inject
    private WizytaEndpoint wizytaEndpoint;

    @PostConstruct
    public void init(){
        this.wizyta = (Wizyta) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectWizyta");
    }

    public Wizyta getWizyta() {
        return wizyta;
    }

    public List<Pacjent> getPacjentList() {
        return pacjentList;
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

    private Date wygenerowacDate(int godzina, Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.HOUR_OF_DAY, godzina);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

    public String saveWizyta() throws ParseException {
        wizyta.getWizytaPK().setData(DateUtils.addHours(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(wybranaData), 1));
        wizytaEndpoint.edit(wizyta);
        return "Pokaz wizyty pracownik";
    }

    public String saveWizytaPacjent() throws ParseException {
        wizyta.getWizytaPK().setData(DateUtils.addHours(new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(wybranaData), 1));
        wizytaEndpoint.edit(wizyta);
        return "Pokaz wizyty pacjent";
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getWybranaData() {
        return wybranaData;
    }

    public void generateListaPacjentow() {
        pacjentList = pacjentEndpoint.findAllByPrzychodnia(przychodniaEndpoint.findByLekarz(wizyta.getWizytaPK().getLekarz()));

    }


    public void setWybranaData(String wybranaData) {
        this.wybranaData = wybranaData;
    }

    public void setWizyta(Wizyta wizyta) {
        this.wizyta = wizyta;
    }

    public void setPacjentList(List<Pacjent> pacjentList) {
        this.pacjentList = pacjentList;
    }

    public List<Date> getListaDat() {
        return listaDat;
    }

    public void setListaDat(List<Date> listaDat) {
        this.listaDat = listaDat;
    }
}
