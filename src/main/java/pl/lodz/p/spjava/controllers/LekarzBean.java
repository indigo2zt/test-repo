/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import pl.lodz.p.spjava.ejb.facade.LekarzFacade;
import pl.lodz.p.spjava.entity.Lekarz;

/**
 *
 * @author java
 */
@RequestScoped
@Named("LekarzBean")

public class LekarzBean implements Serializable{
    
 
    private Lekarz lekarz = new Lekarz();
    @Inject
    private LekarzFacade lekarzFacade;
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
//                this.dodajInformacje("Dodano lekarza!");
//                this.lekarz = new Lekarz();
//                return null;
//    }

    public void dodajInformacje(String s) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,s,""));
        
    }
    }
    