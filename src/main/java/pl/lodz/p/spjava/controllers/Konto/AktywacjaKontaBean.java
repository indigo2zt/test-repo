package pl.lodz.p.spjava.controllers.Konto;


import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.spjava.endpoints.KontoEndpoint;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@ViewScoped
@Named("AktywacjaKontaBean")
public class AktywacjaKontaBean implements Serializable {


    @Inject
    private KontoEndpoint kontoEndpoint;

    private boolean zaakceptowane;

    @PostConstruct
    public void aktuwujKonto(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String klucz = request.getParameter("klucz");
        if(StringUtils.isNotBlank(klucz)){
            kontoEndpoint.potwierdzKontoPoKodzie(klucz);
            zaakceptowane = true;
        }else{
            zaakceptowane = false;
        }

    }
    public boolean isZaakceptowane() {
        return zaakceptowane;
    }

    public void setZaakceptowane(boolean zaakceptowane) {
        this.zaakceptowane = zaakceptowane;
    }
}
