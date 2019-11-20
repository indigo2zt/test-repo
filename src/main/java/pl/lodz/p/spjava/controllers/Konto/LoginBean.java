package pl.lodz.p.spjava.controllers.Konto;

import pl.lodz.p.spjava.ejb.intereceptor.LoggingInterceptor;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;
import pl.lodz.p.spjava.entity.Konto;
import pl.lodz.p.spjava.exception.DbException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

@ViewScoped
@Named("LoginBean")
public class LoginBean implements Serializable {

    private String login;
    private String haslo;

    private Konto konto;

    @Inject
    private KontoEndpoint kontoEndpoint;

    public void przekierowanieZalogowanego(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if(request.getRemoteUser() != null){
            try {
                context.getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String login() throws DbException.BadExecution {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if(!kontoEndpoint.znajdzLogin(login).isPotwierdzone()){
            return "Aktywacja konta";
        }

        try {
            request.login(login, haslo);
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
            return "Logowanie";
        }

        Principal principal = request.getUserPrincipal();

        this.konto = kontoEndpoint.znajdzLogin(principal.getName());


        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("Konto", konto);

        return "Strona glowna";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            this.konto = null;
            request.logout();
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {

        }

        return "Logowanie";
    }

    public Konto getAuthenticatedUser() {
        return konto;
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

}
