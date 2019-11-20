package pl.lodz.p.spjava.controllers;

import pl.lodz.p.spjava.endpoints.PacjentEndpoint;
import pl.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.endpoints.WizytaEndpoint;
import pl.lodz.p.spjava.entity.Pacjent;
import pl.lodz.p.spjava.entity.Wizyta;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@ViewScoped
@Named("JezykBean")
public class JezykBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String kod;

    private Map<String,String> panstwa = new LinkedHashMap<String,String>();



    public String getKod() {
        return kod;
    }


    public void setKod(String kod) {
        this.kod = kod;
    }

    public  Map<String, String> getPanstwa() {
        panstwa.put("English", "en");
        panstwa.put("Polish", "pl");
        return panstwa;

    }

    public void setPanstwa(Map<String, String> panstwa) {
        this.panstwa = panstwa;

    }

    public void zmianaKoduPanstwa(ValueChangeEvent e){

        String newLocaleValue = e.getNewValue().toString();

        for (Map.Entry<String, String> entry : panstwa.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)){

                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale(new Locale(entry.getValue()));

            }
        }
    }


}
