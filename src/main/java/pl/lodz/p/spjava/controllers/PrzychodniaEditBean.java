/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.ejb.facade.PrzychodniaFacade;
import pl.lodz.p.spjava.entity.Przychodnia;

/**
 *
 * @author java
 */
@ViewScoped
@Named("PrzychodniaEditBean")
public class PrzychodniaEditBean implements Serializable {

    private Przychodnia przychodnia;

    @Inject
    private PrzychodniaFacade facade;
    
    public Przychodnia getPrzychodnia() {
        return przychodnia;
    }

    public void setPrzychodnia(Przychodnia przychodnia) {
        this.przychodnia = przychodnia;
    }

    @PostConstruct
    public void init() {
        przychodnia = (Przychodnia) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectPrzychodnia");
    }

    public String edytujPrzychodnie(){
        facade.edit(przychodnia);
        return "ListaPrzychodni";
    }
    
}
