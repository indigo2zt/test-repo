/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import p.lodz.p.spjava.endpoints.WizytaEndpoint;
import pl.lodz.p.spjava.ejb.facade.WizytaFacade;
import pl.lodz.p.spjava.entity.Wizyta;
import pl.lodz.p.spjava.entity.WizytaPK;

/**
 *
 * @author java
 */
@ViewScoped
@Named("WizytaBean")
public class WizytaBean implements Serializable {

    private Wizyta wizyta;
    private Wizyta staraWizyta;
    private int przychodniaID;
    private List<Wizyta> listaWizyt;
    
     @Inject
    private WizytaFacade wizytaFacade;
     
     @Inject
    private WizytaEndpoint wizytaEndpoint;

    public WizytaBean() {
    }

    private void inicjujWizyte() {
        this.listaWizyt = new ArrayList<Wizyta>();
        this.listaWizyt = wizytaFacade.findAll();
        this.wizyta = listaWizyt.get(0);
    }
    
    @PostConstruct
    public void init() {
        this.listaWizyt = new ArrayList<Wizyta>();
        listaWizyt = wizytaEndpoint.findAll();
        this.wizyta = listaWizyt.get(0);

    }

    public Wizyta getWizyta() {
        return wizyta;
    }

    public void setWizyta(Wizyta wizyta) {
        this.wizyta = wizyta;
    }

    public int getPrzychodniaID() {
        return przychodniaID;
    }

    public void setPrzychodniaID(int przychodniaID) {
        this.przychodniaID = przychodniaID;
    }
    
    public List<Wizyta> getListaWizyt() {
        for(Wizyta name : listaWizyt) {  
        System.out.println(name.toString());
    }  
        return  listaWizyt;
    }

    public void setListaWizyt(List<Wizyta> lista) {
        this.listaWizyt = lista;
    }
    
    public String dodaj() {
        wizytaFacade.create(wizyta);
        return "Dodaj wizyte";
    }
    
    public String usun() {
        wizytaFacade.remove(wizyta);
        return "Dodaj wizyte"; 
    }
    
    public Wizyta getRowData(String rowKey) {  
    for(Wizyta name : listaWizyt) {  
        System.out.println(name.toString());
         if(name.toString().equals(rowKey))  
              return name;  
    }  
        
    return null;  
}

public Object getRowKey(Wizyta name) {  
    return name.toString() ;  
}  

//
//   
////
////    private void inicjujWizyte() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
//
//    private void inicjujWizyte() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//        
}
