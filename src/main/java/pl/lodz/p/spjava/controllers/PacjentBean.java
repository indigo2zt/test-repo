/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.lodz.p.spjava.ejb.facade.PacjentFacade;
import pl.lodz.p.spjava.entity.Pacjent;

/**
 *
 * @author java
 */
@Named(value = "pacjentBean")
@ViewScoped
public class PacjentBean implements Serializable{
    private Pacjent pacjent = new Pacjent();
    @Inject
    private PacjentFacade pacjentFacade;
    public PacjentBean() {
    }
        public Pacjent getPacjent() {
        return pacjent;
    }

    public void setPacjent(Pacjent pacjent) {
        this.pacjent = pacjent;
    }
   
    
    public String dodaj() {

        pacjentFacade.create(pacjent);
        return "Dodaj";
    }
    public void dodajInformacje(String s) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,s,""));
    }
}
