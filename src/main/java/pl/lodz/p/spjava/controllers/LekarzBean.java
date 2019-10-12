
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import p.lodz.p.spjava.endpoints.LekarzEndpoint;
import p.lodz.p.spjava.endpoints.PrzychodniaEndpoint;
import pl.lodz.p.spjava.ejb.facade.LekarzFacade;
import pl.lodz.p.spjava.ejb.facade.PrzychodniaFacade;
import pl.lodz.p.spjava.entity.Lekarz;
import pl.lodz.p.spjava.entity.Przychodnia;


/**
 *
 * @author java
 */
@ViewScoped
@Named("LekarzBean")

public class LekarzBean implements Serializable {

    private Lekarz lekarz = new Lekarz();
   
    @Inject
    private LekarzFacade lekarzFacade;//tego nie powinno byc

    @Inject
    private PrzychodniaFacade przychodniaFacade;//powinienn byc endpoint
//    private PrzychodniaEndpoint przychodniaEndpoint;

    private List<Lekarz> lekarze = new ArrayList<>();

    private List<Przychodnia> przychodnie = new ArrayList<>();

    public List<Przychodnia> getPrzychodnie() {
        return przychodnie;
    }

    public void setPrzychodnie(List<Przychodnia> przychodnie) {
        this.przychodnie = przychodnie;
    }

    private Integer selectedPrzychodnia;

    public Integer getSelectedPrzychodnia() {
        return selectedPrzychodnia;
    }

    public void setSelectedPrzychodnia(Integer selectedPrzychodnia) {
        this.selectedPrzychodnia = selectedPrzychodnia;
    }

    public LekarzBean() {
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public String dodaj() {
        //wyszukac przychodnie o id selectedPrzychodnia
        Przychodnia przychodnia = przychodniaFacade.find(selectedPrzychodnia);
        lekarz.setPrzychodnia(przychodnia);
        lekarzFacade.create(lekarz);
        return "Dodaj";
    }
//    public String dodaj() {
//        EntityManager em = (EntityManager) DBManager.getManager().createEntityManagerFactory();
//                em.getTransaction().begin();
//                lekarz.setId(null);
//                em.persist(lekarz);
//                em.getTransaction().commit();
//                em.close();
//                

    public void dodajInformacje(String s) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, s, ""));

    }

    @PostConstruct
    public void init() {
        lekarze = lekarzFacade.findAll();
        przychodnie.addAll(przychodniaFacade.findAll());//uzyc endpointa
        selectedPrzychodnia = 0;

    }

    public List<Lekarz> getLekarze() {
        return lekarze;
    }

    private Lekarz selectLekarz;

    public String edytuj() {
        if (selectLekarz != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectLekarz", selectLekarz);
            return "EDYTUJ";
        }

        return "";

    }

    //public String usun() {
    public List<Lekarz> usun() {
        this.lekarzFacade.remove(selectLekarz);
        return lekarze;

    }

    public Lekarz getSelectLekarz() {
        return selectLekarz;
    }

    public void setSelectLekarz(Lekarz selectLekarz) {
        this.selectLekarz = selectLekarz;
    }

}
