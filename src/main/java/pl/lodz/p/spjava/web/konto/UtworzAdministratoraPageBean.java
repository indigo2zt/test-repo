/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.web.konto;

/**
 *
 * @author java
 */
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.entity.Administrator;
import pl.lodz.p.spjava.web.utils.ContextUtils;

/**
 *
 * @author java
 */
@Named("utworzAdministratoraPageBean")
@RequestScoped
public class UtworzAdministratoraPageBean {
    
    public UtworzAdministratoraPageBean() {
    }
    
    @Inject
    private KontoSession kontoSession;

    private Administrator konto =  new Administrator();

    public Administrator getKonto() {
        return konto;
    }

    private String hasloPowtorz = "";

    public String getHasloPowtorz() {
        return hasloPowtorz;
    }

    public void setHasloPowtorz(String hasloPowtorz) {
        this.hasloPowtorz = hasloPowtorz;
    }
    
    public String utworz() {
        if (!(hasloPowtorz.equals(konto.getHaslo()))){
            ContextUtils.emitInternationalizedMessage("utworzAdministratoraForm:passwordRepeat", "passwords.not.matching");
            return null;
        }
            
        return kontoSession.utworzAdministratora(konto);
    }

}
