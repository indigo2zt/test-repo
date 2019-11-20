package pl.lodz.p.spjava.controllers.Konto;


import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.exception.AppBaseException;
import pl.lodz.p.spjava.exception.DbException;
import pl.lodz.p.spjava.web.utils.KontoUtils;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@ViewScoped
@Named("KontoBean")
public class KontoBean implements Serializable {

    private String login;
    private String haslo;
    private String noweHaslo;
    private Konto mojeKonto;


    @Inject
    private KontoEndpoint kontoEndpoint;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if(request.getRemoteUser() != null){
            try {
                mojeKonto = kontoEndpoint.znajdzLogin(request.getUserPrincipal().getName());
            } catch (DbException.BadExecution badExecution) {
                badExecution.printStackTrace();
            }
        }
    }

    public String zmienHaslo() throws DbException.BadExecution {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Konto konto = kontoEndpoint.znajdzLogin(request.getUserPrincipal().getName());
        try {
            if(StringUtils.equals(konto.getHaslo(), KontoUtils.kodojWSha256(haslo))){
                konto.setHaslo(KontoUtils.kodojWSha256(noweHaslo));
                kontoEndpoint.zapiszKontoPoEdycji(konto);
                return "Lista pacjentow";
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | AppBaseException e) {
            e.printStackTrace();
        }
        return "Logowanie";

    }

    public void wyzerojHaslo() {

    }

    public void aktywuj() {

    }

    public void wylaczKonto() {

    }

    public void zapiszZmiany() throws AppBaseException {
        kontoEndpoint.zapiszKontoPoEdycji(mojeKonto);
    }

    public boolean uzytkownikZalogowany() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getRemoteUser() != null;
    }

    public boolean uzytkownikJestPacjentem() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole("Pacjent");
    }

    public boolean uzytkownikJestAdministratorem() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole("Administrator");
    }

    public boolean uzytkownikJestPracownikiem() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole("Pracownik");
    }

    public boolean uzytkownikJestLekarzem() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.isUserInRole("Lekarz");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getNoweHaslo() {
        return noweHaslo;
    }

    public void setNoweHaslo(String noweHaslo) {
        this.noweHaslo = noweHaslo;
    }

    public Konto getMojeKonto() {
        return mojeKonto;
    }

    public void setMojeKonto(Konto mojeKonto) {
        this.mojeKonto = mojeKonto;
    }
}
