/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.spjava.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.lodz.p.spjava.entity.Wizyta;
import pl.lodz.p.spjava.entity.WizytaPK;

/**
 *
 * @author java
 */
@SessionScoped
@Named("WizytaBean")
    public class WizytaBean implements Serializable{
        private Wizyta wizyta;
        private Wizyta staraWizyta;
        private int przychodniaID;
      
        public WizytaBean() {
            this.inicjujWizyte();
        }
        private void inicjujWizyte() {
            this.wizyta = new Wizyta();
            this.wizyta.setWizytaPK(new WizytaPK());
            this.staraWizyta = null;
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
