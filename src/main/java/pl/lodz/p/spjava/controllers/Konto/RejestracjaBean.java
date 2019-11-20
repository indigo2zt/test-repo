package pl.lodz.p.spjava.controllers.Konto;

import org.apache.commons.lang3.RandomStringUtils;
import pl.lodz.p.spjava.endpoints.GrupaEndpoint;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.entity.Grupa;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.web.utils.EmailUtils;
import pl.lodz.p.spjava.web.utils.KontoUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@ViewScoped
@Named("RejestracjaBean")
public class RejestracjaBean implements Serializable {

    private String email;
    private String haslo;
    private String confirmPassword;
    private String login;
    private String imie;
    private String nazwisko;

    @Inject
    private KontoEndpoint kontoEndpoint;

    @Inject
    private GrupaEndpoint grupaEndpoint;

    @Inject
    private EmailUtils emailUtils;

    private KontoUtils kontoUtils;

    public void validatePassword(ComponentSystemEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();


        UIInput uiInputPassword = (UIInput) components.findComponent("haslo");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("potwierdzhaslo");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Podane hasła nie zgadzają się");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(passwordId, msg);
            facesContext.renderResponse();
        }

    }

    public String register() {
        String wygenerowanyKlucz = RandomStringUtils.randomAlphabetic(10);
        Konto konto = new Konto();
        Grupa grupa = new Grupa();
        konto.setEmail(email);
        konto.setLogin(login);
        konto.setHaslo(haslo);
        konto.setImie(imie);
        konto.setNazwisko(nazwisko);
        konto.setKodaktywacji(wygenerowanyKlucz);
        grupa.setLogin(konto);
        grupa.setNazwagrupy("Pacjent");
        kontoEndpoint.utworzKonto(konto);
        grupaEndpoint.dodajUzytkownika(grupa);
        wyslijEmailZKluczemPotwierdzenia(wygenerowanyKlucz);
        return "Udana rejestracja";
    }

    private void wyslijEmailZKluczemPotwierdzenia(String klucz) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        //emailUtils.sendEmail(email, String.format("%s:%s%s/aktywacjakonta?klucz=%s", request.getServerName(), request.getServerPort(), request.getContextPath(), klucz));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
