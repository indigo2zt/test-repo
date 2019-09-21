/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import p.lodz.p.spjava.endpoints.LekarzEndpoint;
import pl.lodz.p.spjava.ejb.facade.LekarzFacade;
import pl.lodz.p.spjava.entity.Lekarz;

/**
 *
 * @author java
 */
@RequestScoped
@Named("LekarzBean")

public class LekarzBean implements Serializable {

    private Lekarz lekarz = new Lekarz();
    @Inject
    private LekarzFacade lekarzFacade;
    @Inject
    private LekarzEndpoint lekarzEndpoint;

    private List<Lekarz> lekarze = new ArrayList<>();

    public LekarzBean() {
    }

    public Lekarz getLekarz() {
        return lekarz;
    }

    public void setLekarz(Lekarz lekarz) {
        this.lekarz = lekarz;
    }

    public String dodaj() {

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
        lekarze = lekarzEndpoint.findAll();

    }

    public List<Lekarz> getLekarze() {
        return lekarze;
    }

    private Lekarz selectLekarz;

    public String edytuj() {
        if (selectLekarz != null) {
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectLekarz", selectLekarz);
            return "Edytuj";
        }

        return "";

    }

    //public String usun() {
    public List<Lekarz> usun() {
        this.lekarzEndpoint.remove(selectLekarz);
        return lekarze;

    }

    public Lekarz getSelectLekarz() {
        return selectLekarz;
    }

    public void setSelectLekarz(Lekarz selectLekarz) {
        this.selectLekarz = selectLekarz;
    }

}
